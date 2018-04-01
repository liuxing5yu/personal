/**
 * 这里定义一些工具方法
 * 将来想办法管理方法
 */

/**
 * @param parmas	JS对象，包含一下内容
 *  codekind	代码类别
 *  status	代码状态 0-不可用 1-可用，可选，默认为1
 *  async		是否异步，可选，默认false
 *  successCallback	成功回调方法
 * 
 */
function getCodeList(params) {
	// 取出参数

	var entity = {};
	entity.codekind = params.codekind;
	entity.status = params.status || '1';
	var param = JSON.stringify(entity);

	var async = params.async || false;

	$.ajax({
		type : "POST",
		url : _path + "/codelist/get",
		cache : false, // 禁用缓存
		async : async, // 是否异步
		data : param, // 传入已封装的参数
		contentType : 'application/json;charset=utf-8',
		dataType : "json",
		success : function(result) {
			if (result.status == "S") {
				params.successCallback(result.data);
				return result.data;
			} else {
				toastr['error'](result.message);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			toastr['error']('发生错误');
		}
	});
}