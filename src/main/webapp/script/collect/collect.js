$(document).ready(function() {
	var app = "collect";

	// 聚焦
	$('#title').focus();

	var colelctManage = {
		getQueryCondition : function(data) {
			var param = {};

			// 页面参数

			// 组装分页参数
			param.pageNow = data.start / data.length + 1;
			param.pageSize = data.length;

			var columns = data.columns;
			var order = data.order;

			// 组装排序参数
			if (order && order.length) {
				var sortField = columns[order[0].column].name;
				if (sortField) {
					var sortString = sortField + "." + order[0].dir;
					param.sortString = sortString;
				}
			}
			return param;
		}
	};

	// 获取datatable数据
	var $collectDt = $('#collectDt');
	var _collectDt = $collectDt.dataTable($.extend(true, {}, DATATABLES_DEFAULT_OPTIONS, {

		ajax : function(data, callback, settings) {
			var param = JSON.stringify(colelctManage.getQueryCondition(data));
			$.ajax({
				type : "POST",
				url : _path + "/collect/search",
				cache : false, // 禁用缓存
				data : param, // 传入已封装的参数
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				success : function(result) {
					if (result.status == "S") {
						var returnData = {};
						returnData.draw = data.draw;// 这里直接自行返回了draw计数器,应该由后台返回
						returnData.recordsTotal = result.recordsTotal;
						returnData.recordsFiltered = result.recordsFiltered;// 后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.data;
						// 调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
						// 此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
						callback(returnData);
					} else {
						toastr['error'](result.message);
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					toastr['error']('发生错误');
				}
			});
		},
		columns : [ {
			title : "序号",
			width : '5%',
			data : null,
		}, {
			title : "标题",
			width : '40%',
			data : 'title',
			render : function(data, type, row, meta) {
				return "<a target='_blank' href='" + row.url + "'>" + data + "</a>";
			}
		}, {
			title : "标签",
			width : '30%',
			data : 'tags',
			render : function(data, type, row, meta) {
				var htmlStr = '';
				$.each(data, function(index, item) {
					htmlStr += '<button class="btn btn-primary btn-sm">' + item.name + '</button>';
				});
				return htmlStr;
			}
		}, {
			title : "状态",
			width : '5%',
			data : "status",
			render : function(data, type, row, meta) {
				if (data == '1') {
					return "<button class='status-btn btn btn-success' type='button'>已读</button>";
				} else {
					return "<button class='status-btn btn btn-danger' type='button'>未读</button>";
				}
			}
		}, {
			title : "操作",
			width : '20%',
			data : null,
			defaultContent : "<button class='tag-btn btn btn-primary' type='button' data-target='#tagModal'>标签</button><button class='delete-btn btn btn-danger' type='button'>删除</button>"
		} ],

		// 自增长序号
		fnDrawCallback : function() {
			var api = this.api();
			var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
			api.column(0).nodes().each(function(cell, i) {
				cell.innerHTML = startIndex + i + 1;
			});
		}

	})).api();

	// 添加
	$('#saveRow').on('click', function() {
		var title = $('#title').val();
		var url = $('#url').val();
		if (!(title && url)) {
			toastr['error']('标题和URL不能为空');
			return;
		}

		var entity = {};
		entity.title = title;
		entity.url = url;

		var tagIds = [];
		// 获取所有选中的tag
		$(".tagBtnGroupDiv .tag-btn.btn-primary").each(function(index, domEle) {
			tagIds.push($(domEle).attr('data-tagId'));
		});
		entity.tagIds = tagIds;

		var param = JSON.stringify(entity);

		$.ajax({
			type : "POST",
			url : _path + "/collect/add",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					_collectDt.draw();
					toastr['success'](result.message);

					// 清空表单内容
					$('#title').focus();
					$('#title').val('');
					$('#url').val('http://');
					$(".tagBtnGroupDiv .tag-btn").removeClass('btn-primary');

				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});

	});

	// 改变状态
	$("#collectDt tbody").on("click", ".status-btn", function() {
		var data = _collectDt.row($(this).parents("tr")).data();
		var id = data.id;
		var status = data.status == '1' ? '0' : '1';

		$.confirm({
			title : 'Confirm!',
			content : '确定？',
			buttons : {
				confirm : {
					text : '确定',
					btnClass : 'btn-danger',
					action : function() {

						$.ajax({
							type : "POST",
							url : _path + "/collect/changeStatus/" + id + "/" + status,
							cache : false, // 禁用缓存
							//data : param, // 传入已封装的参数
							contentType : 'application/json;charset=utf-8',
							dataType : "json",
							success : function(result) {
								if (result.status == "S") {
									_collectDt.draw();
									toastr['success'](result.message);

								} else {
									toastr['error'](result.message);
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								toastr['error']('发生错误');
							}
						});
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-default',
					action : function() {
					}
				}
			}
		});

	});

	// 行内删除
	$("#collectDt tbody").on("click", ".delete-btn", function() {
		var id = _collectDt.row($(this).parents("tr")).data().id;

		$.confirm({
			title : 'Confirm!',
			content : '确定要删除这条记录吗？',
			buttons : {
				confirm : {
					text : '确定',
					btnClass : 'btn-danger',
					action : function() {

						$.ajax({
							type : "POST",
							url : _path + "/collect/delete/" + id,
							cache : false, // 禁用缓存
							//data : param, // 传入已封装的参数
							contentType : 'application/json;charset=utf-8',
							dataType : "json",
							success : function(result) {
								if (result.status == "S") {
									_collectDt.draw();
									toastr['success'](result.message);
								} else {
									toastr['error'](result.message);
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								toastr['error']('发生错误');
							}
						});
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-default',
					action : function() {
					}
				}
			}
		});

	});

	// 修改收藏的标签
	$("#collectDt tbody").on("click", ".tag-btn", function() {
		// 清除模态框中所有按钮样式
		$("#tagModal .tagBtnGroupDiv .tag-btn").removeClass('btn-primary');

		var data = _collectDt.row($(this).parents("tr")).data();
		var id = data.id;
		var tags = data.tags;

		// 为模态框隐藏collectId赋值，为了保存时使用
		$('#tagModal #collectId').val(id);

		// 为已有标签添加样式
		$('#tagModal .tagBtnGroupDiv button').each(function(index, domEle) {
			$.each(tags, function(index, item) {
				if ($(domEle).attr('data-tagId') == item.id) {
					$(domEle).addClass('btn-primary');
				}
			})
		})

		// 显示模态框
		$('#tagModal').modal('show');
	});

	/**
	 * 模态框保存事件
	 */
	$('#saveTagBtn').on('click', function() {
		// 获取隐藏的id
		var collectId = $('#tagModal #collectId').val();

		// 获取模态框中选中的tag
		var tagIds = [];
		// 获取所有选中的tag
		$("#tagModal .tagBtnGroupDiv .tag-btn.btn-primary").each(function(index, domEle) {
			tagIds.push($(domEle).attr('data-tagId'));
		});

		var entity = {};
		entity.id = collectId;
		entity.tagIds = tagIds;

		var param = JSON.stringify(entity);

		$.ajax({
			type : "POST",
			url : _path + "/collect/modifyTags",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					toastr['success'](result.message);
					_collectDt.draw();
					// 关闭模态框
					$('#tagModal').modal('hide');
					// 清除tag选中样式，防止添加收藏时报错
					$("#tagModal .tagBtnGroupDiv .tag-btn").removeClass('btn-primary');
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});

	})
	// ---------------------Tag BEGIN------------------------------------------------
	showAllTags();
	// 查询所有的Tag
	function showAllTags() {
		var entity = {};
		entity.idValid = '1';

		var param = JSON.stringify(entity);

		$.ajax({
			type : "POST",
			url : _path + "/collectTag/search",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					// 清空所有tag button
					$('.tagBtnGroupDiv').empty();

					// 遍历数组，添加标签button
					$.each(result.data, function(index, item) {
						var addBtnStr = '<button type="button" class="btn btn-default btn-sm tag-btn" data-tagId=' + item.id + '>' + item.name + '</button>';
						$('.tagBtnGroupDiv').append($(addBtnStr));
						$('.tagBtnGroupDiv').html($('.tagBtnGroupDiv').html() + "&nbsp;&nbsp;");
					});
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});
	}

	// 添加Tag
	$('#addTagBtn').on('click', function() {
		var entity = {};
		entity.name = $('#tagInput').val();
		entity.app = app;

		var param = JSON.stringify(entity);

		$.ajax({
			type : "POST",
			url : _path + "/collectTag/add",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					toastr['success'](result.message);
					// 添加进tag组中
					showAllTags();

					// 清空输入内容
					$('#tagInput').val('');
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});

	});

	// 添加收藏时，点击标签按钮，增加事件
	$(".tagBtnGroupDiv").on("click", ".tag-btn", function() {
		// 获取选中的tagId
		$(this).toggleClass('btn-primary');
	});
	// ---------------------Tag END------------------------------------------------	

	// ---------------------Todo BEGIN------------------------------------------------
	var todoManage = {
		getQueryCondition : function(data) {
			var param = {};

			// 页面参数
			param.app = app;

			// 组装分页参数
			param.pageNow = data.start / data.length + 1;
			param.pageSize = data.length;

			var columns = data.columns;
			var order = data.order;

			// 组装排序参数
			if (order && order.length) {
				var sortField = columns[order[0].column].name;
				if (sortField) {
					var sortString = sortField + "." + order[0].dir;
					param.sortString = sortString;
				}
			}
			return param;
		}
	};

	// 获取datatable数据
	var $todoDt = $('#todoDt');
	var _todoDt = $todoDt.dataTable($.extend(true, {}, DATATABLES_DEFAULT_OPTIONS, {

		ajax : function(data, callback, settings) {
			var param = JSON.stringify(todoManage.getQueryCondition(data));
			$.ajax({
				type : "POST",
				url : _path + "/todo/search",
				cache : false, // 禁用缓存
				data : param, // 传入已封装的参数
				contentType : 'application/json;charset=utf-8',
				dataType : "json",
				success : function(result) {
					if (result.status == "S") {
						var returnData = {};
						returnData.draw = data.draw;// 这里直接自行返回了draw计数器,应该由后台返回
						returnData.recordsTotal = result.recordsTotal;
						returnData.recordsFiltered = result.recordsFiltered;// 后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.data;
						// 调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
						// 此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
						callback(returnData);
					} else {
						toastr['error'](result.message);
					}

				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					toastr['error']('发生错误');
				}
			});
		},

		columns : [ {
			title : "序号",
			data : null,
			width : '8%'
		}, {
			title : "TODO",
			data : "content",
		}, {
			title : "状态",
			data : "status",
			render : function(data, type, row, meta) {
				if (data == '1') {
					return "<button class='status-btn btn btn-success' type='button'>Done</button>";
				} else {
					return "<button class='status-btn btn btn-danger' type='button'>TODO</button>";
				}
			}
		} ],

		// 自增长序号
		fnDrawCallback : function() {
			var api = this.api();
			var startIndex = api.context[0]._iDisplayStart;// 获取到本页开始的条数
			api.column(0).nodes().each(function(cell, i) {
				cell.innerHTML = startIndex + i + 1;
			});
		}

	})).api();

	// 添加todo
	$('#addTodoBtn').on('click', function() {
		var entity = {};
		entity.content = $('#todoInput').val();
		entity.app = app;

		var param = JSON.stringify(entity);

		$.ajax({
			type : "POST",
			url : _path + "/todo/add",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					_todoDt.draw();
					toastr['success'](result.message);

					// 清空输入内容
					$('#todoInput').val('');
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});

	});

	// 改变TODO状态
	$("#todoDt tbody").on("click", ".status-btn", function() {
		var data = _todoDt.row($(this).parents("tr")).data();
		var id = data.id;
		var status = data.status == '1' ? '0' : '1';

		$.confirm({
			title : 'Confirm!',
			content : '确定？',
			buttons : {
				confirm : {
					text : '确定',
					btnClass : 'btn-danger',
					action : function() {

						$.ajax({
							type : "POST",
							url : _path + "/todo/changeStatus/" + id + "/" + status,
							cache : false, // 禁用缓存
							//data : param, // 传入已封装的参数
							contentType : 'application/json;charset=utf-8',
							dataType : "json",
							success : function(result) {
								if (result.status == "S") {
									_todoDt.draw();
									toastr['success'](result.message);

									// 清空表单内容
									$('#collectInput').val('');
								} else {
									toastr['error'](result.message);
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								toastr['error']('发生错误');
							}
						});
					}
				},
				cancel : {
					text : '取消',
					btnClass : 'btn-default',
					action : function() {
					}
				}
			}
		});

	});

	// ---------------------Todo END------------------------------------------------
});