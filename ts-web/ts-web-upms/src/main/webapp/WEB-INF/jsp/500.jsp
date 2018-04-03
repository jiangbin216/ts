<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="common/IncHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="common/IncMeta.jsp"%>
    <title>内部服务器错误！</title>
</head>
<body>
<div style="text-align: center; width: 92%; margin: 0 auto">
    <p>错误代码：500</p>
    <p>您访问的页面有错误！</p>
    <p>错误原因：${error.message}</p>
    <p>错误内容：${error}</p>
    <p><!--页面将在<span id="stime">5</span>秒后-->跳转到<a href="${pageContext.request.contextPath}/">首页</a>！</p>
</div>
<%
    /**
     监控出错人的IP
     String ip = request.getHeader(" x-forwarded-for");
     if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
     ip = request.getHeader(" Proxy-Client-IP"); // 获取代理ip
     }
     if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
     ip = request.getHeader(" WL-Proxy-Client-IP"); // 获取代理ip
     }
     if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
     ip = request.getRemoteAddr(); // 获取真实ip
     }
     //out.println(ip+"<br/><br/>你的地址是：<br/><br/>");

     Document doc = Jsoup.connect("http://ip.chinaz.com/?IP="+ip).timeout(9000).get();
     Element e = doc.select("#status").first();
     //out.println(e);
     */
%>
</body>
</html>