<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>快捷键</title>
<script type="text/javascript" src="${path }/script/todo/todo.js"></script>

<style type="text/css">
table.dataTable tbody tr.selected input {
	color: black;
}
</style>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">TODO</div>
			<div class="panel-body">Go To Do</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>ToDo List</h2>
			</div>
			<div class="panel-body">

				<div class="row">
					<div class="col-md-3">
						<div class="input-group">
							<div class="input-group-addon">APP</div>
							<select id="appSelect" class="selectpicker show-tick form-control" data-live-search="true">
								<option value="-1" selected="selected">全部</option>
							</select>
						</div>
					</div>
					<div class="col-md-3">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select id="statusSelect" class="selectpicker show-tick form-control">
								<option value="-1" selected="selected">全部</option>
								<option value="0">TODO</option>
								<option value="1">Done</option>
							</select>
						</div>
					</div>
					<div class="col-md-3">
						<div class="input-group" style="float: right;">
							<input type="text" id="searchText" class="form-control" />
						</div>
					</div>
					<div class="col-md-3">
						<div class="input-group" style="float: right;">
							<button class="btn btn-success" id="btnSearch">
								<i class="fa fa-search"></i> 查询
							</button>
							<button class="btn btn-primary" id="btnReset">
								<i class="fa fa-reply"></i> 重置
							</button>
						</div>
					</div>
				</div>

				<!-- 清除浮动 -->
				<div class="clearfix" style="margin-bottom: 10px;"></div>

				<div class="row">
					<div class="col-md-12">
						<table id="todoDt" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>序号</th>
									<th>APP</th>
									<th>TODO</th>
									<th>状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>