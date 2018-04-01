$(document).ready(function() {

	var app = 'record';

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));

	var data = [ {
		name : '2016/12/18 6:38:08xx',
		value : [ '2016/12/19', 80 ]
	}, {
		name : '2016/12/18 16:18:18xx',
		value : [ '2016/12/20', 60 ]
	}, {
		name : '2016/12/18 19:18:18xx',
		value : [ '2016/12/21', 90 ]
	} ];

	// 指定图表的配置项和数据
	var option = {
		title : {
			text : 'ECharts 入门示例'
		},
		tooltip : {},
		xAxis : {
			type : 'time',
		},
		yAxis : {
			type : 'value'
		},
		series : [ {
			type : 'line',
			data : data
		} ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);

	$("body").niceScroll();
	$("#category").niceScroll();

	$('#category').on('click', 'button.list-group-item', function() {
		// 设置选中样式
		$(this).addClass('active').siblings().removeClass('active');
	});
});