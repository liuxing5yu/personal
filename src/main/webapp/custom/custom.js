/**
 * Datatables初始化配置
 */
var DATATABLES_DEFAULT_OPTIONS = {
	language : {
		"sProcessing" : "处理中...",
		"sLengthMenu" : "显示 _MENU_ 项结果",
		"sZeroRecords" : "没有匹配结果",
		"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
		"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
		"sInfoPostFix" : "",
		"sSearch" : "搜索:",
		"sUrl" : "",
		"sEmptyTable" : "表中数据为空",
		"sLoadingRecords" : "载入中...",
		"sInfoThousands" : ",",
		"oPaginate" : {
			"sFirst" : "首页",
			"sPrevious" : "上页",
			"sNext" : "下页",
			"sLast" : "末页"
		},
		"oAria" : {
			"sSortAscending" : ": 以升序排列此列",
			"sSortDescending" : ": 以降序排列此列"
		}
	},
	stripeClasses : [ "odd", "even" ], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
	dom : 'rt<"bottom"iflp<"clear">>',
	stateSave : false,
	searching : false, // 禁用原生搜索
	serverSide : true, //启用服务器端分页
	ordering : false
};

/**
 * Toastr
 */
toastr.options = {
	"debug" : true,
	"preventDuplicates" : true
}