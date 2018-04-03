<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="common/IncHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="common/IncMeta.jsp"%>
    <title>没有权限！</title>
</head>
<body>
<c:if test="${requestHeader == 'ajax'}">
    <h5 style="padding-bottom: 10px;">没有权限！</h5>
</c:if>
<c:if test="${requestHeader != 'ajax'}">
    <% Exception e = (Exception) request.getAttribute("ex"); %>
    <h2>错误: <%= e.getClass().getSimpleName()%>
    </h2>
    <hr/>
    <h5>错误描述：</h5>
    <p><%= e.getMessage()%>
    </p>
</c:if>
</body>
</html>