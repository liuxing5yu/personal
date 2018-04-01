<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>进度记录</title>

<script type="text/javascript" src="${path }/script/record/record.js"></script>
</head>
<body>
	<div class="container">
		<div class="col-md-6" style="height: 100%; overflow: auto;" id="category">
			<div class="list-group">
				<button type="button" class="list-group-item active">Cras justo odio</button>
				<button type="button" class="list-group-item">Dapibus ac facilisis in</button>
				<button type="button" class="list-group-item">Morbi leo risus</button>
				<button type="button" class="list-group-item">Porta ac consectetur ac</button>
				<button type="button" class="list-group-item">Vestibulum at eros</button>
			</div>
		</div>
		<div class="col-md-6" style="height: 100%">
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<div id="main" style="width: 600px; height: 400px;"></div>
		</div>
	</div>
</body>
</html>