<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>收藏文章整理</title>
<script type="text/javascript" src="${path }/script/collect/collect.js"></script>

<style type="text/css">
table.dataTable tbody tr.selected input {
	color: black;
}
</style>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">收藏文章整理程序</div>
			<div class="panel-body">为了更方便的管理源源不断的收藏文章</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="panel panel-default">
					<div class="panel-heading">标签</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-11">
								<input type="text" class="form-control" id="tagInput" name="tagInput" />
							</div>
							<div class="col-md-1">
								<button type="button" id="addTagBtn" class="btn btn-success">添加</button>
							</div>
						</div>

						<!-- 清除浮动 -->
						<div class="clearfix" style="margin-bottom: 10px;"></div>

						<div class="row">
							<div class="col-md-11 tagBtnGroupDiv"></div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<form id="addForm" class="form form-horizontal">
					<div class="form-group">
						<label class="col-sm-1 control-label">标题</label>
						<div class="col-sm-11">
							<input type="text" class="form-control" id="title" placeholder="标题">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-1 control-label">URL</label>
						<div class="col-sm-11">
							<input type="text" class="form-control" id="url" placeholder="URL" onclick="this.select()" value="http://">
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
							<button id="saveRow" type="button" class="btn btn-success">添加</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">操作</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-1"></div>
						</div>

						<!-- 清除浮动 -->
						<div class="clearfix" style="margin-bottom: 10px;"></div>

						<div class="row">
							<div class="col-md-12">
								<table id="collectDt" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>序号</th>
											<th>标题</th>
											<th>标签</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>ToDo List</h2>
			</div>
			<div class="panel-body">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">操作</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-11">
								<input type="text" class="form-control" id="todoInput" name="todoInput" />
							</div>
							<div class="col-md-1">
								<button type="button" id="addTodoBtn" class="btn btn-success">添加</button>
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
		</div>
	</div>

	<div class="modal fade" id="tagModal" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel">标签</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<input type="hidden" id="collectId" name="collectId" />
						<div class="col-md-11 tagBtnGroupDiv"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button id="saveTagBtn" type="button" class="btn btn-primary">Save</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>