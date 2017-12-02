<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>快捷键</title>
<script type="text/javascript" src="${path }/script/keys.js"></script>

</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">快捷键管理程序</div>
			<div class="panel-body">为了更方便的添加快捷键</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon">快捷键</span>
							<input type="text" class="form-control input-md" readonly id="keyBind" name="keyBind" />
						</div>
					</div>
					<div class="col-md-5">
						<div class="input-group">
							<span class="input-group-addon">描述</span>
							<input type="text" class="form-control input-md" id="keyEffect" name="keyEffect" />
						</div>
					</div>
					<div class="col-md-2">
						<div class="btn-group">
							<button id="singleKeyModeBtn" type="button" class="btn btn-default" title="单键模式">单</button>
							<button id="multiKeyModeBtn" type="button" class="btn btn-default" title="多键模式">多</button>
							<button id="addKeyBtn" type="button" class="btn btn-default" title="二级按键">加</button>
							<button id="clearInputBtn" type="button" class="btn btn-default" title="清空内容">清</button>
						</div>
					</div>
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
				<div class="row">
					<div class="col-md-12">
						<div class="btn-group">
							<button id="addRow" type="button" class="btn btn-default">添加行</button>
							<button id="saveRows" type="button" class="btn btn-default">保存</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<table id="tp" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>快捷键</th>
									<th>描述</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>已知问题</h2>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<li class="list-group-item">在作用描述中如果带 <>，导出时会自动转义</li>
					<li class="list-group-item">多键模式下，一些组合键会引发浏览器的行为，不能被正确捕捉，例如Ctrl+N</li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>