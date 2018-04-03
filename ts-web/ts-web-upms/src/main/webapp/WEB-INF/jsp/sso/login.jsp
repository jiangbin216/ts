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
    <link rel="stylesheet" href="${ctx}/webjars/checkbix/css/checkbix.min.css"/>
    <link rel="stylesheet" href="${ctx}/resources/css/login.css"/>
</head>
<body>
<div id="login-window">
    <div class="input-group m-b-20">
        <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
        <input id="username" type="text" class="form-control" name="username" placeholder="帐号" required autofocus
               value="admin">
    </div>
    <div class="input-group m-b-20">
        <span class="input-group-addon"><i class="zmdi zmdi-lock"></i></span>
        <input id="password" type="password" class="form-control" name="password" placeholder="密码" required
               value="123456">
    </div>
    <div class="clearfix">
    </div>
    <div class="checkbox">
        <input id="rememberMe" type="checkbox" class="checkbix" data-text="自动登录" name="rememberMe">
    </div>
    <a id="login-bt" href="javascript:void(0);" class="waves-effect waves-button waves-float"><i
            class="zmdi zmdi-arrow-forward"></i></a>
</div>
<script src="${ctx}/webjars/jquery/jquery.1.12.4.min.js"></script>
<script src="${ctx}/webjars/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${ctx}/webjars/waves-0.7.5/waves.min.js"></script>
<script src="${ctx}/webjars/checkbix/js/checkbix.min.js"></script>
<script>var BASE_PATH = '${ctx}';</script>
<script>var BACK_URL = '${param.backurl}';</script>
<script src="${ctx}/resources/js/login.js"></script>
<script>
    <c:if test="${param.forceLogout == 1}">
    alert('您已被强制下线！');
    top.location.href = '${ctx}/sso/login';
    </c:if>
</script>
</body>
</html>