$(function() {
	generatePanel();

	/**
	 * 生成所有面板
	 */
	function generatePanel() {
		$('#rowDiv').empty();

		$.ajax({
			type : "POST",
			url : _path + "/recentdo/selectAll",
			cache : false, // 禁用缓存
			//		data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					$.each(result.data, function(index, item) {
						$('#rowDiv').append($('<div class="col-md-3">' + '<div class="panel panel-default" data-sid="' + item.id + '">' + '<div class="panel-heading">' + item.title + '</div>' + '<div class="panel-body" style="height: 100%;">' + item.content + '</div>' + '</div>' + '</div>'));
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

	/**
	 * 为面板定义上下文菜单
	 */
	var menu = new BootstrapMenu('.panel', {
		fetchElementData : function($dom) {
			return $dom.data('sid');
		},
		actions : [ {
			name : "新增",
			iconClass : 'fa fa-plus',
			onClick : function(obj) {
				$('#dashModal').modal('show');
			}
		}, {
			name : '编辑',
			iconClass : 'fa fa-pencil',
			onClick : function(obj) {
				$('#dashModal').modal('show');

				$.ajax({
					type : "POST",
					url : _path + "/recentdo/selectOne/" + obj,
					cache : false, // 禁用缓存
					// data : param, // 传入已封装的参数
					contentType : 'application/json;charset=utf-8',
					dataType : "json",
					success : function(result) {
						if (result.status == "S") {
							var data = result.data;
							$('#modalForm #sid').val(data.id);
							$('#modalForm #title').val(data.title);
							$('#modalForm #content').val(data.content);
						} else {
							toastr['error'](result.message);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						toastr['error']('发生错误');
					}
				});
			}
		}, {
			name : '删除',
			iconClass : 'fa fa-trash',
			onClick : function(obj) {
				$.confirm({
					title : 'Confirm!',
					content : '确定删除？',
					buttons : {
						confirm : {
							text : '确定',
							btnClass : 'btn-danger',
							action : function() {
								$.ajax({
									type : "POST",
									url : _path + "/recentdo/delete/" + obj,
									cache : false, // 禁用缓存
									//data : param, // 传入已封装的参数
									contentType : 'application/json;charset=utf-8',
									dataType : "json",
									success : function(result) {
										if (result.status == "S") {
											toastr['success'](result.message);
											generatePanel();
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
			}
		} ]
	});

	/**
	 * 模态框的保存按钮
	 */
	$('#saveTagBtn').on('click', function() {
		var entity = $('#modalForm').serializeJSON();
		entity.id = entity.sid;
		delete entity['sid'];

		var param = JSON.stringify(entity);

		$.ajax({
			type : "POST",
			url : _path + "/recentdo/save",
			cache : false, // 禁用缓存
			data : param, // 传入已封装的参数
			contentType : 'application/json;charset=utf-8',
			dataType : "json",
			success : function(result) {
				if (result.status == "S") {
					toastr['success'](result.message);
					generatePanel();
					$('#dashModal').modal('hide');
				} else {
					toastr['error'](result.message);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				toastr['error']('发生错误');
			}
		});
	});

	/**
	 * 模态框触发事件
	 */
	$('#dashModal').on('hidden.bs.modal', function(e) {
		$('#modalForm')[0].reset();

		// form reset 不能重置隐藏域
		$('#modalForm #sid').val('');
	});
});
