<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>快捷键</title>
<script type="text/javascript" src="${path }/script/keys/keys.js"></script>

<style type="text/css">
table.dataTable tbody tr.selected input {
	color: black;
}
</style>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">快捷键管理程序</div>
			<div class="panel-body">为了更方便的添加快捷键</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">所在场景</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12" id="sceneDiv"></div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<form id="keyForm" class="form form-horizontal">
					<input type="hidden" id="scene" name="scene" value="0" />

					<!-- <div class="form-group">
						<label class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputPassword" placeholder="Password">
						</div>
					</div> -->

					<div class="row">
						<div class="col-md-5">
							<div class="input-group">
								<span class="input-group-addon">快捷键</span>
								<input type="text" class="form-control input-md" readonly id="key" name="key" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-5">
							<div class="input-group">
								<span class="input-group-addon">描述</span>
								<textarea class="form-control input-md" id="desc" name="desc"></textarea>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-5">
							<div class="input-group">
								<span class="input-group-addon">官方描述</span>
								<textarea class="form-control input-md" id="rawDesc" name="rawDesc"></textarea>
							</div>
						</div>
					</div>
				</form>

				<div class="row">
					<div class="col-md-12">
						<div class="btn-group">
							<button id="singleKeyModeBtn" type="button" class="btn btn-default" title="单键模式">单</button>
							<button id="multiKeyModeBtn" type="button" class="btn btn-default" title="多键模式">多</button>
							<button id="addOrBtn" type="button" class="btn btn-default" title="加或模式">或</button>
							<button id="addKeyBtn" type="button" class="btn btn-default" title="二级按键">加</button>
							<button id="clearInputBtn" type="button" class="btn btn-default" title="清空内容">清</button>
							<button id="reduceBtn" type="button" class="btn btn-default" title="还原设置">原</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-1">
						<div class="btn-group">
							<button id="saveRow" type="button" class="btn btn-success" title="保存">保存</button>
						</div>
					</div>
				</div>
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
							<div class="col-md-1">
								<button id="clearTable" type="button" class="btn btn-primary" title="清空表">清空表</button>
							</div>
							<div class="col-md-3 btn-group">
								<button type="button" id="batch-edit-btn" class="btn btn-primary">批量编辑</button>
								<button type="button" id="batch-save-btn" class="btn btn-success">批量保存</button>
							</div>
						</div>

						<!-- 清除浮动 -->
						<div class="clearfix" style="margin-bottom: 10px;"></div>

						<div class="row">
							<div class="col-md-12">
								<table id="tp" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>序号</th>
											<th>快捷键</th>
											<th>描述</th>
											<th>官方描述</th>
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
</body>
</html>