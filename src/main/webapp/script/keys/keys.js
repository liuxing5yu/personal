$(document).ready(function() {

	var manage = {
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
	var $table = $('#tp');
	var _table = $table.dataTable($.extend(true, {}, DATATABLES_DEFAULT_OPTIONS, {

		ajax : function(data, callback, settings) {
			var param = JSON.stringify(manage.getQueryCondition(data));
			$.ajax({
				type : "POST",
				url : _path + "/keys/search",
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
			data : null,
			title : "序号",
			width : '8%'
		}, {
			data : "key",
			title : "快捷键",
		}, {
			data : "desc",
			title : "描述",
		}, {
			data : "rawDesc",
			title : "官方描述",
		}, {
			"data" : null,
			"title" : "操作",
			"defaultContent" : "<button class='edit-btn btn btn-primary' type='button'>编辑</button><button class='delete-btn btn btn-danger' type='button'>删除</button>"
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

	var todoManage = {
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

	// 添加
	$('#saveRow').on('click', function() {
		var entity = {};
		entity.key = $('#key').val();
		entity.rawDesc = $('#rawDesc').val();
		entity.desc = $('#desc').val();

		var param = JSON.stringify(entity);

		// 判断快捷键是否已存在
		$.ajax({
			type : "POST",
			url : _path + "/keys/checkExist",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					// 快捷键未存在时添加
					$.ajax({
						type : "POST",
						url : _path + "/keys/add",
						cache : false, // 禁用缓存
						data : param, // 传入已封装的参数
						contentType : 'application/json;charset=utf-8',
						dataType : "json",
						success : function(result) {
							if (result.status == "S") {
								_table.draw();
								toastr['success'](result.message);

								// 清空表单内容
								$('#keyForm')[0].reset();
								clearKeyInput();
							} else {
								toastr['error'](result.message);
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							toastr['error']('发生错误');
						}
					});
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});
	});

	// 行内修改
	$("#tp tbody").on("click", ".edit-btn", function() {
		var tds = $(this).parents("tr").children();
		$.each(tds, function(i, val) {
			var jqob = $(val);
			if (i < 1 || jqob.has('button').length) {
				return true;
			}// 跳过第1项 序号,按钮
			var txt = jqob.text();
			var put = $("<input type='text'>");
			put.val(txt);
			jqob.html(put);
		});
		$(this).html("保存");
		$(this).toggleClass("edit-btn");
		$(this).toggleClass("save-btn");
	});

	// 行内保存
	$("#tp tbody").on("click", ".save-btn", function() {
		var row = _table.row($(this).parents("tr"));
		var tds = $(this).parents("tr").children();
		$.each(tds, function(i, val) {
			var jqob = $(val);
			// 把input变为字符串
			if (!jqob.has('button').length) {
				var txt = jqob.children("input").val();
				jqob.html(txt);
				_table.cell(jqob).data(txt);// 修改DataTables对象的数据
			}
		});

		var param = JSON.stringify(row.data());

		$.ajax({
			type : "POST",
			url : _path + "/keys/edit",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					_table.draw();
					toastr['success'](result.message);

					// 清空表单内容
					$('#keyForm')[0].reset();
					$('#key').focus();
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});

		$(this).html("编辑");
		$(this).toggleClass("edit-btn");
		$(this).toggleClass("save-btn");
	});

	// 行内删除
	$("#tp tbody").on("click", ".delete-btn", function() {
		var id = _table.row($(this).parents("tr")).data().id;

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
							url : _path + "/keys/delete/" + id,
							cache : false, // 禁用缓存
							//data : param, // 传入已封装的参数
							contentType : 'application/json;charset=utf-8',
							dataType : "json",
							success : function(result) {
								if (result.status == "S") {
									_table.draw();
									toastr['success'](result.message);

									// 清空表单内容
									$('#keyForm')[0].reset();
									$('#key').focus();
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

	/**
	 * 清空表
	 */
	$('#clearTable').on('click', function() {
		$.confirm({
			title : 'Confirm!',
			content : '确定要清空表吗？',
			buttons : {
				confirm : {
					text : '确定',
					btnClass : 'btn-danger',
					action : function() {
						$.ajax({
							type : "POST",
							url : _path + "/keys/clearTable",
							cache : false, // 禁用缓存
							// data : param, //传入已封装的参数
							contentType : 'application/json;charset=utf-8',
							dataType : "json",
							success : function(result) {
								if (result.status == "S") {
									_table.draw();
									toastr['success'](result.message);

									// 清空表单内容
									$('#keyForm')[0].reset();
									$('#key').focus();
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

	// 批量点击编辑按钮
	$("#batch-edit-btn").click(function() {
		$(".edit-btn").click();
	});
	$("#batch-save-btn").click(function() {
		$(".save-btn").click();
	});

	// 添加TODO
	$('#addTodoBtn').on('click', function() {
		var entity = {};
		entity.content = $('#todoInput').val();

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

	//------------------------------------------------------------------------------------------------

	// 映射键值和对应显示的值: keyCode/*key*/: showValue
	var keyCode2value = {
		17 /* Control */: 'Ctrl',
		27 /* Escape */: 'Esc',
		32 /* */: 'Space',
		38 /* ArrowUp */: 'Up',
		40 /* ArrowDown */: 'Down',
		37 /* ArrowLeft */: 'Left',
		39 /* ArrowRight */: 'Right',
		107 /* + */: 'Numpad_Add',
		109 /*-*/: 'Numpad_Subtract',
		106 /*-*/: 'Numpad_Multiply',
		111 /*-*/: 'Numpad_Divide',
	};

	var keys = [];

	// 在快捷键输入框按下键时，显示在输入框中
	$('#key').keydown(function() {
		$(this).val('');

		if (multiMode) {
			keys = [];
		}

		return false;
	}).keyup(function(event) {
		var eventKey = event.key;
		var eventKeyCode = event.keyCode;

		console.log('key: ' + eventKey + '  keyCode: ' + eventKeyCode);

		// 处理显示的键值
		// 映射显示值
		if (keyCode2value[eventKeyCode]) {
			eventKey = keyCode2value[eventKeyCode];
		}

		// a-z
		var reg = /^[a-z]$/;
		if (reg.test(eventKey)) {
			eventKey = eventKey.toUpperCase();
		}

		// 是否为加级模式
		if (!addKeyMode && !addOrMode) {
			keys.push(eventKey);
			// 调整键位显示顺序
			sortKeys(keys);
			$(this).val(keys.join('+'));
		} else {
			if (addKeyMode) {
				$(this).val(keys.join('+') + ', ' + eventKey);
			} else if (addOrMode) {
				$(this).val(keys.join('+') + '/' + eventKey);
			}
		}
		return false;
	});

	// 默认为多键模式
	$('#multiKeyModeBtn').addClass('active');

	var multiMode = true;

	// 绑定单/多键按钮事件
	$('#singleKeyModeBtn,#multiKeyModeBtn').on('click', function() {
		if (!$(this).hasClass('active')) {
			toggleMode();
		}
	});

	/**
	 * 切换单/多键模式
	 */
	function toggleMode() {
		multiMode = !multiMode;
		$('#singleKeyModeBtn').toggleClass('active');
		$('#multiKeyModeBtn').toggleClass('active');

		addOrMode = false;
		addKeyMode = false;
		$('#addOrBtn').removeClass('active');
		$('#addKeyBtn').removeClass('active');

		// 清空Input
		clearKeyInput();

	}

	// 加或模式
	var addOrMode = false;

	// 绑定加级按钮
	$('#addOrBtn').on('click', function() {
		$('#key').focus();

		if (!keys.length) {
			return false;
		}

		addOrMode = true;
		addKeyMode = false;
		$('#addOrBtn').addClass('active');
		$('#addKeyBtn').removeClass('active');

		// 手动切换为单键模式
		if (multiMode) {
			multiMode = false;
			$('#singleKeyModeBtn').toggleClass('active');
			$('#multiKeyModeBtn').toggleClass('active');
		}
	});

	// 加级模式
	var addKeyMode = false;

	// 绑定加级按钮
	$('#addKeyBtn').on('click', function() {
		$('#key').focus();

		if (!keys.length) {
			return false;
		}

		addOrMode = false;
		addKeyMode = true;
		$('#addOrBtn').removeClass('active');
		$('#addKeyBtn').addClass('active');

		// 手动切换为单键模式
		if (multiMode) {
			multiMode = false;
			$('#singleKeyModeBtn').toggleClass('active');
			$('#multiKeyModeBtn').toggleClass('active');
		}
	});

	// 绑定清除按钮
	$('#clearInputBtn').on('click', function() {
		clearKeyInput();
	});

	// 绑定还原按钮
	$('#reduceBtn').on('click', function() {
		// 还原为多键模式
		multiMode = true;
		$('#singleKeyModeBtn').removeClass('active');
		$('#multiKeyModeBtn').addClass('active');

		// 还原或、加
		addOrMode = false;
		addKeyMode = false;
		$('#addOrBtn').removeClass('active');
		$('#addKeyBtn').removeClass('active');

		// 设置焦点
		clearKeyInput();
	});

	/**
	 * 清除绑定键输入框中的内容
	 */
	function clearKeyInput() {
		$('#key').val('');
		keys = [];

		$('#key').focus();
	}

	/**
	 * 为keys数组排序，确保Ctrl、Alt、Shift在其他键位之前
	 */
	function sortKeys(keys) {
		if (!keys.length) {
			return false;
		}
		var curIndex = 0;

		var commands = [ 'Ctrl', 'Alt', 'Shift' ];
		for ( var cmdIdx in commands) {
			console.log('curIndex...' + curIndex);
			var index = keys.indexOf(commands[cmdIdx]);
			if (index != -1) {
				keys[index] = keys[curIndex];
				keys[curIndex] = commands[cmdIdx];
				curIndex++;
			}
		}
	}

});