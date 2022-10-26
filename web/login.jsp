<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %>
<%@ page import="save.data.Login" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="loginBean" class="save.data.Login" scope="session"/>
<HEAD><%@ include file="head.txt" %>
<%Date date=new Date();%>
</HEAD>
<title>登录页面</title>
<style>
   #tom{
      font-family:宋体;font-size:30;color:black 
   }
</style>
<HTML>
<body id=tom background =image/back.jpg>
<div align="center">
<table id=tom border=1>
<tr><th>登录</th></tr>
<form action="HandleLogin" method="post">
<tr><td>身份证号码:<input type=text id=tom name="num"></td></tr>
<tr><td>输入密码&nbsp&nbsp:<input type=password id=tom name="password"></td></tr>
</table>
<input type=submit id=tom value="提交">
</form></div >
<div align="center" >
登录反馈信息:<br>
<jsp:getProperty name="loginBean" property="backNews"/>
<div>
<tr><td>余额<jsp:getProperty name="loginBean" property="rmb"/></td></tr><br>
<form action="HandleRecharge" method="post">
<input type=text id=tom name="money" style="width:50px">
<input type="submit" id=tom value="充值">
</form>

<form action="End">
<input type="submit" id=tom value="上机">
</form>
<form  action="BeginCount">
<input type="submit" id=tom value="下机">
</form>

上机时间:<jsp:getProperty name="loginBean" property="hours"/>时<jsp:getProperty name="loginBean" property="minutes"/>分<jsp:getProperty name="loginBean" property="seconds"/>秒<br>
当前时间:<%=date.getHours()%>时<%=date.getMinutes() %>分<%=date.getSeconds()%>秒
</body></HTML>
