<%@ page contentType="text/html; charset=utf-8" isErrorPage="true" %>
<%@ include file="common/IncHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="common/IncMeta.jsp" %>
    <title>服务器发生错误！</title>
</head>
<body>
<% Exception e = null != exception ? (Exception) exception : (Exception) request.getAttribute("ex"); %>
<h2>错误: <%= e.getClass().getSimpleName()%>
</h2>
<hr/>
<h5>错误描述：</h5>
<%= e.getMessage()%>
</pre>
</body>
</html>