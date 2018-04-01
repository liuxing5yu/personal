$(function() {

	$('.selectpicker').selectpicker({});

	$.ajax({
		type : "POST",
		url : _path + "/menu/getAllMenu",
		cache : false, //禁用缓存
		//data: JSON.stringify(param),  //传入已封装的参数
		contentType : 'application/json;charset=utf-8',
		dataType : "json",
		success : function(result) {
			if (result.status == "S") {
				if (result.menu) {
					var rootArr = [];
					rootArr.push(result.menu);
					var d = result.list;
					if (d && d.length && d.length > 0) {
						$("#name").val(d[0].text);
						$("#url").val(d[0].url);
						$("#sequenceNo").val(d[0].sequenceNo);
						$("#iconClass").val(d[0].icon);
						$("#id").val(d[0].id);
						console.log(d[0].permissionFk);
						$("#permission").selectpicker('val', d[0].permissionFk);
					}
					var $tree = $('#treeview').treeview({
						color : "#428bca",
						expandIcon : 'glyphicon glyphicon-chevron-right',
						collapseIcon : 'glyphicon glyphicon-chevron-down',
						nodeIcon : 'glyphicon glyphicon-bookmark',
						showBorder : false,
						data : rootArr,
						onNodeSelected : function(event, node) {
							$("#name").val(node.text);
							$("#url").val(node.url);
							$("#sequenceNo").val(node.sequenceNo);
							$("#iconClass").val(node.icon);
							$("#id").val(node.id);
							$("#parentId").val(node.pId);
							if (node.permissionFk) {
								$("#permission").selectpicker('val', node.permissionFk);
							} else {
								$("#permission").selectpicker('val', '-1');
							}

						},
						onNodeCollapsed : function(event, node) {
							return false;
						},
						onNodeExpanded : function(event, node) {
							return false;
						}
					});
					var menu = new BootstrapMenu('.node-treeview', {
						fetchElementData : function($rowElem) {
							var rowId = $rowElem.attr('id');
							for (var i = 0; i < d.length; i++) {
								if (d[i].id == rowId) {
									return d[i]
								}
							}
						},
						actions : [ {
							name : "新增",
							iconClass : 'fa fa-plus',
							onClick : function(row) {
								$("#name").val("");
								$("#url").val("");
								$("#sequenceNo").val("");
								$("#iconClass").val("");
								$("#id").val("");
								$("#parentId").val(row.id);
								$("#permission").selectpicker('val', '-1');
							}
						}, {
							name : '删除',
							iconClass : 'fa fa-trash',
							onClick : function(row) {
								if (!row)
									return;
								$.dialog({
									content : '确定删除<span class="text-danger">' + row.text + '</span>?',
									max : false,
									min : false,
									opacity : 0.5,
									title : "看准在删",
									ok : function() {
										$.ajax({
											type : "POST",
											url : _path + "/menu/" + row.id + "/deleteMenu",
											cache : false, //禁用缓存
											//data: param,	//传入已封装的参数
											contentType : 'application/json;charset=utf-8',
											dataType : "json",
											success : function(result) {
												toastr.info(result.message);
												if (result.status == "S") {
													var node = $tree.treeview('findNodes', [ row.id, 'id' ]);
													$tree.treeview('removeNode', [ node, {
														silent : true
													} ]);
												}
											},
											error : function(XMLHttpRequest, textStatus, errorThrown) {
												//$.dialog.alert("查询失败");
											}
										});
									},
									cancel : true
								});

							}
						} ]
					});

					$("#save").click(function(e) {
						var name = $("#name").val();
						if (!name) {
							toastr.error("菜单名称不能为空！");
							return;
						}
						var url = $("#url").val();
						if (!url) {
							toastr.error("菜单访问路径不能为空！");
							return;
						}

						var sequenceNo = $("#sequenceNo").val();
						var icon = $("#iconClass").val();
						var pId = $("#parentId").val();
						var id = $("#id").val();
						var permissionFk = $("#permission").val();
						var param = {};
						param.permissionFk = permissionFk;
						param.id = id;
						param.name = name;
						param.url = url;
						param.sequenceNo = sequenceNo;
						param.icon = icon;
						param.pId = pId;
						$("#save").attr({
							"disabled" : true
						});
						if (id) {//编辑
							$.ajax({
								type : "POST",
								url : _path + "/menu/updateMenu",
								cache : false, //禁用缓存
								data : JSON.stringify(param), //传入已封装的参数
								contentType : 'application/json;charset=utf-8',
								dataType : "json",
								success : function(result) {
									if (result.status == "S") {
										$("#save").attr({
											"disabled" : false
										});
										for (var i = 0; i < d.length; i++) {
											if (d[i].id == result.menu.id) {
												d[i].text = result.menu.text;
												d[i].url = result.menu.url;
												d[i].icon = result.menu.icon;
												d[i].sequenceNo = result.menu.sequenceNo;
												d[i].permissionFk = result.menu.permissionFk;
											}
										}
										var newNode = result.menu;
										var oldNode = $tree.treeview('findNodes', [ id, 'id' ]);

										$tree.treeview('updateNode', [ oldNode, newNode, {
											silent : true
										} ]);
										var f = $tree.treeview('findNodes', [ id, 'id' ]);
										$tree.treeview('selectNode', f);

										toastr.success("菜单修改成功");
									}
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									//$.dialog.alert("查询失败");
								}
							});
						} else {//新增
							$.ajax({
								type : "POST",
								url : _path + "/menu/addMenu",
								cache : false, //禁用缓存
								data : JSON.stringify(param), //传入已封装的参数
								contentType : 'application/json;charset=utf-8',
								dataType : "json",
								success : function(result) {
									if (result.status == "S") {
										$("#save").attr({
											"disabled" : false
										});
										d.push(result.menu);
										var newNode = result.menu;
										var parentNode = $tree.treeview('findNodes', [ newNode.pId, 'id' ]);
										$tree.treeview("addNode", [ newNode, parentNode, newNode.sequenceNo ]);
										toastr.success("菜单添加成功");
										$("#name").val("");
										$("#url").val("");
										$("#sequenceNo").val("");
										$("#iconClass").val("");
										$("#permission").selectpicker('val', '-1');
									}
								},
								error : function(XMLHttpRequest, textStatus, errorThrown) {
									//$.dialog.alert("查询失败");
								}
							});
						}

					});

				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			//$.dialog.alert("查询失败");
		}
	});

});
