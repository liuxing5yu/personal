$(document).ready(function() {
	var app = 'todo';

	var todoManage = {
		getQueryCondition : function(data) {
			var param = {};

			// 页面参数
			param.app = $('#appSelect').val();
			param.status = $('#statusSelect').val();

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
	var $todoTable = $('#todoDt');
	var _todoTable = $todoTable.dataTable($.extend(true, {}, DATATABLES_DEFAULT_OPTIONS, {

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
			title : "APP",
			data : 'app',
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

	// 添加TODO
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
					_todoTable.draw();
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
		var data = _todoTable.row($(this).parents("tr")).data();
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
									_todoTable.draw();
									toastr['success'](result.message);

									// 清空表单内容
									$('#todoInput').val('');
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

	// 使用Datatables原生搜索功能搜索内容
	$('#searchText').on('keyup click', function() {
		_todoTable.search($('#searchText').val()).draw();
	});

	// 查询按钮事件
	$('#btnSearch').on('click', function() {
		_todoTable.draw();
	});

	// 重置按钮事件
	$('#btnReset').on('click', function() {
		$('#appSelect').selectpicker('val', '-1');
		$('#statusSelect').selectpicker('val', '-1');
	});

});