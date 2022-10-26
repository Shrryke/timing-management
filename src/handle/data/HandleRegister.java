package handle.data;
import save.data.Register;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
@WebServlet("/HandleRegister")
public class HandleRegister extends HttpServlet {
   public void init(ServletConfig config) throws ServletException { 
      super.init(config);
   }
   public  void  service(HttpServletRequest request,
                         HttpServletResponse response) 
                         throws ServletException,IOException {
      request.setCharacterEncoding("utf-8");
      Register userBean=new Register();  //创建bean。
      request.setAttribute("userBean",userBean);
      String num=request.getParameter("num").trim();
      String password=request.getParameter("password").trim();
      String again_password=request.getParameter("again_password").trim();
      String realname=request.getParameter("realname").trim();
      if(num==null)
           num="";
      if(password==null)
           password="";
      if(!password.equals(again_password)) { 
         userBean.setBackNews("两次密码不同，注册失败，");
         RequestDispatcher dispatcher= 
         request.getRequestDispatcher("inputRegisterMess.jsp");
         dispatcher.forward(request, response);//转发
         return;
      }
      boolean isLD=true;
      for(int i=0;i<num.length();i++){
          char c=num.charAt(i);
          if(!(Character.isLetterOrDigit(c)||c=='_')) 
             isLD=false;
      } 
      boolean boo=num.length()>0&&password.length()>0&&isLD;
      String backNews="";
      try{   
             String sql="INSERT INTO user VALUES (?,?,?,?,?,?,?)";
             Object[]params= {num,password,realname,0,0,0,0};
               if(Utils.addupdel(sql, params)){
                  backNews="注册成功";
                  userBean.setBackNews(backNews);
                  userBean.setnum(num);
                  userBean.setRealname(realname);
               }
               else {
                 backNews="信息填写不完整或名字中有非法字符";
                 userBean.setBackNews(backNews);  
             }
      }
      catch(Exception e){
    	  e.printStackTrace();
      }
      RequestDispatcher dispatcher= 
      request.getRequestDispatcher("inputRegisterMess.jsp");
      dispatcher.forward(request, response);//转发
   }
}