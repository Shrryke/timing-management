<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %>
<jsp:useBean id="userBean" class="save.data.Register" scope="request"/>
<HEAD><%@ include file="head.txt" %></HEAD>
<title>注册页面</title>
<style>
   #ok{
      font-family:宋体;font-size:26;color:black; 
   }
   #yes{
      font-family:黑体;font-size:18;color:black; 
   }
</style>

<HTML><body id=ok background =image/back.jpg>
<div align="center">
<form action="HandleRegister" method="post">
<table id=ok>
    
   <tr><td>*身份证号码:</td>
       <td><input type=text id=ok name="num" /></td>
       <td>*密码:</td><td><input type=password id=ok name="password">
       </td></tr>
   <tr><td>*重复密码:</td><td>
       <input type=password id=ok name="again_password"></td>
       <td>真实姓名:</td><td><input type=text id=ok name="realname"/></td>
       <td><input type=submit  id=ok value="提交"></td> </tr>
</table>
</form>
</div >
<div align="center">
注册反馈：
<jsp:getProperty name="userBean"  property="backNews" /> 
<table id=yes border=3>
     <tr><td>身份证号码:</td>
     <td><jsp:getProperty name="userBean" property="num"/></td>
     </tr>
     <tr><td>姓名:</td>
     <td><jsp:getProperty name="userBean" property="realname"/></td>
     </tr>
   
</table></div >
</body></HTML>

