<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>主页</title>
<link href="${path}/style/user/style.min.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resource/metisMenu/jquery.metisMenu.js"></script>
<script type="text/javascript" src="${path}/resource/slimscroll/1.3.0/jquery.slimscroll.js"></script>
<script type="text/javascript" src="${path}/script/user/hplus.min.js"></script>
<script type="text/javascript" src="${path}/script/user/contabs.min.js"></script>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
	<div id="wrapper">
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">

					<c:forEach var="menu" items="${menus}">
						<c:if test="${menu.nodes eq null}">
							<li><a class="J_menuItem" href="<%=path%>${menu.url}">
									<i class="${menu.icon}"></i> <span class="nav-label">${menu.text}</span>
								</a></li>
						</c:if>
						<c:if test="${fn:length(menu.nodes) gt 0}">
							<li><a href="<%=path%>/menu/list">
									<i class="${menu.icon}"></i> <span class="nav-label">${menu.text}</span> <span class="fa arrow"></span>

								</a> <c:if test="${fn:length(menu.nodes) gt 0}">
									<ul class="nav nav-second-level">
										<c:forEach var="childMenu" items="${menu.nodes}">
											<c:if test="${fn:length(childMenu.nodes) gt 0}">
												<li><a href="#">
														<i class="${childMenu.icon}"></i>${childMenu.text}<span class="fa arrow"></span>
													</a>
													<ul class="nav nav-third-level">
														<c:forEach var="grandsonMenu" items="${childMenu.nodes}">
															<li><a class="J_menuItem" href="<%=path%>${grandsonMenu.url}">
																	<i class="${grandsonMenu.icon}"></i>${grandsonMenu.text}</a></li>
														</c:forEach>
													</ul></li>
											</c:if>

											<c:if test="${childMenu.nodes eq null}">
												<li><a class="J_menuItem" href="<%=path%>${childMenu.url}">
														<i class="${childMenu.icon}"></i>${childMenu.text}</a>
											</c:if>
										</c:forEach>
									</ul>

								</c:if></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</nav>


		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg">

			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="${path }/logout" class="roll-nav roll-right J_tabExit">
					<i class="fa fa fa-sign-out"></i> 退出
				</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${path }/dashboard" frameborder="0" data-id="index_v1.html" seamless></iframe>
			</div>
			<div class="footer">
				<div class="text-right"></div>
			</div>
		</div>

	</div>
</body>
</html>