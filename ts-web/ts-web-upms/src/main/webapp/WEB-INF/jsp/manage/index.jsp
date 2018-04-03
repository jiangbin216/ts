<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../common/IncHeader.jsp" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <%@ include file="../common/IncMeta.jsp" %>
    <title>权限管理系统</title>
    <link rel="stylesheet" href="${ctx}/webjars/bootstrap-3.3.0/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${ctx}/webjars/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css"/>
    <link rel="stylesheet" href="${ctx}/webjars/waves-0.7.5/waves.min.css"/>
    <link rel="stylesheet" href="${ctx}/webjars/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"/>
    <link rel="stylesheet" href="${ctx}/resources/css/admin.css"/>
    <style type="text/css">
        #header, .content_tab, .s-profile > a {
            background: #6F5499;
        }
    </style>
</head>
<body>
<header id="header">
    <ul id="menu">
        <li id="guide" class="line-trigger toggled">
            <div class="line-wrap">
                <div class="line top"></div>
                <div class="line center"></div>
                <div class="line bottom"></div>
            </div>
        </li>
        <li id="logo" class="hidden-xs">
            <a href="${ctx}/manage/index">
                <img src="${ctx}/resources/images/logo.png"/>
            </a>
            <span id="system_title">权限管理系统</span>
        </li>
        <li class="pull-right">
            <ul class="hi-menu">
                <!-- 搜索 -->
                <%--<li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:void(0);">
                        <i class="him-icon zmdi zmdi-search"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <form id="search-form" class="form-inline">
                            <div class="input-group">
                                <input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索"/>
                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-default"><span
                                            class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>
                        </form>
                    </ul>
                </li>--%>
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:void(0);">
                        <i class="him-icon zmdi zmdi-dropbox"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="skin-switch hidden-xs">
                            请选择系统切换
                        </li>
                        <li class="divider hidden-xs"></li>
                        <c:forEach var="upmsSystem" items="${upmsSystems}">
                            <li>
                                <a class="waves-effect switch-systems" href="javascript:void(0);"
                                   systemid="${upmsSystem.id}" systemname="${upmsSystem.name}"
                                   systemtitle="${upmsSystem.title}"><i
                                        class="${upmsSystem.icon}"></i> ${upmsSystem.title}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:void(0);">
                        <i class="him-icon zmdi zmdi-more-vert"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="hidden-xs">
                            <a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i
                                    class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
                        </li>
                        <%--<li>
                            <a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:void(0);"><i
                                    class="zmdi zmdi-delete"></i> 清除缓存</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:void(0);"><i class="zmdi zmdi-face"></i> 隐私管理</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:void(0);"><i class="zmdi zmdi-settings"></i> 系统设置</a>
                        </li>--%>
                        <li>
                            <a class="waves-effect" href="${ctx}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</header>
<section id="main">
    <!-- 左侧导航区 -->
    <aside id="sidebar" class="toggled">
        <!-- 个人资料区 -->
        <div class="s-profile">
            <a class="waves-effect waves-light" href="javascript:void(0);">
                <div class="sp-pic">
                    <img src="${ctx}${upmsUser.avatar}"/>
                </div>
                <div class="sp-info">
                    ${upmsUser.realname}，您好！
                    <i class="zmdi zmdi-caret-down"></i>
                </div>
            </a>
            <ul class="main-menu">
                <%--<li>
                    <a class="waves-effect" href="javascript:void(0);"><i class="zmdi zmdi-account"></i> 个人资料</a>
                </li>
                <li>
                    <a class="waves-effect" href="javascript:void(0);"><i class="zmdi zmdi-face"></i> 隐私管理</a>
                </li>
                <li>
                    <a class="waves-effect" href="javascript:void(0);"><i class="zmdi zmdi-settings"></i> 系统设置</a>
                </li>--%>
                <li>
                    <a class="waves-effect" href="${ctx}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
                </li>
            </ul>
        </div>
        <!-- /个人资料区 -->
        <!-- 菜单区 -->
        <ul class="main-menu">
            <li>
                <a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i>
                    首页</a>
            </li>
            <c:forEach var="upmsPermission" items="${upmsPermissions}" varStatus="status">
                <c:if test="${upmsPermission.pid == 0}">
                    <li class="sub-menu system_menus system_${upmsPermission.systemId} ${status.index}"
                        <c:if test="${upmsPermission.systemId != 1}">style="display:none;"</c:if>>
                        <a class="waves-effect" href="javascript:void(0);"><i
                                class="${upmsPermission.icon}"></i> ${upmsPermission.name}</a>
                        <ul>
                            <c:forEach var="subUpmsPermission" items="${upmsPermissions}">
                                <c:if test="${subUpmsPermission.pid == upmsPermission.id}">
                                    <c:forEach var="upmsSystem" items="${upmsSystems}">
                                        <c:if test="${subUpmsPermission.systemId == upmsSystem.id}">
                                            <c:set var="systemBasePath" value="${upmsSystem.basepath}"/></c:if>
                                    </c:forEach>
                                    <li><a class="waves-effect"
                                           href="javascript:Tab.addTab('${subUpmsPermission.name}', '${systemBasePath}${subUpmsPermission.uri}');">${subUpmsPermission.name}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
            <li>
                <div class="upms-version">&copy; ts V1.0.0</div>
            </li>
        </ul>
        <!-- /菜单区 -->
    </aside>
    <!-- /左侧导航区 -->
    <section id="content">
        <div class="content_tab">
            <div class="tab_left">
                <a class="waves-effect waves-light" href="javascript:void(0);"><i
                        class="zmdi zmdi-chevron-left"></i></a>
            </div>
            <div class="tab_right">
                <a class="waves-effect waves-light" href="javascript:void(0);"><i
                        class="zmdi zmdi-chevron-right"></i></a>
            </div>
            <ul id="tabs" class="tabs">
                <li id="tab_home" data-index="home" data-closeable="false" class="cur">
                    <a class="waves-effect waves-light" href="javascript:void(0);">首页</a>
                </li>
            </ul>
        </div>
        <div class="content_main">
            <div id="iframe_home" class="iframe cur">
                <h4>通用用户权限管理系统</h4>
                <p><b>系统简介</b>：本系统是基于RBAC授权和基于用户授权的细粒度权限控制通用平台，并提供单点登录、会话管理和日志管理。接入的系统可自由定义组织、角色、权限、资源等。</p>
                <h4>系统功能概述：</h4>
                <p><b>系统组织管理</b>：系统和组织增加、删除、修改、查询功能。</p>
                <p><b>用户角色管理</b>：用户和角色增加、删除、修改、查询功能。</p>
                <p><b>资源权限管理</b>：菜单和按钮增加、删除、修改、查询功能。</p>
                <p><b>权限分配管理</b>：提供给角色和用户的权限增加、删除、修改、查询功能。</p>
                <p><b>单点登录(SSO)</b>：提供统一用户单点登录认证、用户鉴权功能。</p>
                <p><b>用户会话管理</b>：提供分布式用户会话管理</p>
                <p><b>操作日志管理</b>：提供记录用户登录、操作等日志。</p><br/>
                <h4>对外接口概述：</h4>
                <p><b>系统组织数据接口</b>、<b>用户角色数据接口</b>、<b>资源权限数据接口</b>、<b>单点登录(SSO)接口</b>、<b>用户鉴权接口</b></p><br/>
                <h4>更新日志：</h4>
                <p><b>2017-05-24：初始搭建</b></p><br/>
            </div>
        </div>
    </section>
</section>
<script type="text/javascript">var BASE_PATH = '${ctx}';</script>
<script type="text/javascript" src="${ctx}/webjars/jquery/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="${ctx}/webjars/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/webjars/waves-0.7.5/waves.min.js"></script>
<script type="text/javascript"
        src="${ctx}/webjars/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript" src="${ctx}/webjars/bootstrap-menu/BootstrapMenu.min.js"></script>
<script type="text/javascript" src="${ctx}/webjars/device/device.min.js"></script>
<script type="text/javascript" src="${ctx}/webjars/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/webjars/fullPage/jquery.jdirk.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/admin.js"></script>
</body>
</html>