<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>首页</title>
<style>
body {
	background: #fff url(${path}/img/dashboard.jpg) no-repeat;
	background-size: 100% 100%;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row" style="height: 20%;">
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">最近在做的事情</div>
					<div class="panel-body" style="height: 100%;">整理收藏</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">最近的学习目标</div>
					<div class="panel-body" style="height: 100%;">英语7000单词</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">最近在看的专业书</div>
					<div class="panel-body" style="height: 100%;">MyBatis中文官方文档</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">最近在看的业余书</div>
					<div class="panel-body" style="height: 100%;">人性的弱点</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">最近在看的美剧</div>
					<div class="panel-body" style="height: 100%;">权力的游戏</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>