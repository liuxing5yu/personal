<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>首页</title>
<script type="text/javascript" src="${path }/script/user/dashboard.js"></script>

<style>
body {
	background: #fff url(${path}/img/dashboard.jpg) no-repeat;
	background-size: 100% 100%;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row" style="height: 20%;" id="rowDiv">
			<!-- <div class="col-md-3">
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
			</div> -->
		</div>
	</div>

	<div class="modal fade" id="dashModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="modalForm">
						<input type="hidden" id="sid" name="sid">
						<div class="form-group">
							<label for="title" class="col-sm-2 control-label">标题</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title">
							</div>
						</div>
						<div class="form-group">
							<label for="content" class="col-sm-2 control-label">内容</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="content" name="content">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="saveTagBtn" type="button" class="btn btn-primary">Save</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>