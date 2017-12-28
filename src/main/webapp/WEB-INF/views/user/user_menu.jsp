<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>用户列表</title>

<script type="text/javascript" src="${path }/script/user/user_menu.js?201704"></script>

</head>
<body>
	<div class="container-fluid">

		<div class="row wrapper border-bottom white-bg page-heading" style="padding-top: 20px;">
			<div class="col-sm-12">
				<ol class="breadcrumb">
					<li><strong class="text-primary">用户</strong></li>
					<li><strong class="text-success">菜单管理</strong></li>
				</ol>
			</div>
		</div>
		<div class="panel panel-default" style="border: none;">
			<div class="panel-body">
				<input type="hidden" id="id" value="" />
				<input type="hidden" id="parentId" value="" />
				<div class="row">
					<div class="col-sm-5">
						<div class="panel panel-info">
							<div class="panel-heading">菜单</div>
							<div class="panel-body">
								<div id="treeview"></div>

							</div>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="panel panel-danger">
							<div class="panel-heading">操作</div>
							<div class="panel-body">
								<form class="form searchform" name="searchForm" id="searchForm">
									<div class="row">

										<div class="col-sm-6">
											<div class="form-group">
												<div class="input-group">
													<div class="input-group-addon">
														名称<span style="color: red;">*</span>
													</div>
													<input class="form-control" id="name" name="name" value='' />
												</div>
											</div>
										</div>

										<div class="col-sm-6">
											<div class="form-group">
												<div class="input-group">
													<div class="input-group-addon">图标</div>
													<input class="form-control" id="iconClass" name="iconClass" value='' />
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<div class="input-group">
													<div class="input-group-addon">
														路径<span style="color: red;">*</span>
													</div>
													<input class="form-control" id="url" name="url" value='' />
												</div>
											</div>
										</div>

										<div class="col-sm-6">
											<div class="form-group">
												<div class="input-group">
													<div class="input-group-addon">排序</div>
													<input class="form-control" id="sequenceNo" name="sequenceNo" value='' />
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-sm-12">
											<div class="text-center">
												<input readOnly class="btn btn-danger btn-sm" id="save" name="save" value='保存' />
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>