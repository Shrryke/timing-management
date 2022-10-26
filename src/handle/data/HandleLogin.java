
package handle.data;
import save.data.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
@WebServlet("/HandleLogin")
public class HandleLogin extends HttpServlet{
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
      request.setCharacterEncoding("utf-8");
      Connection con =null; 
      Statement sql; 
      int rmb=0,a=0,b=0,c=0;
      
      String username=null;
      String num=request.getParameter("num").trim(),
      password=request.getParameter("password").trim();
      boolean boo=(num.length()>0)&&(password.length()>0);  
      try{ 
           Context context = new InitialContext();
           Context contextNeeded=(Context)context.lookup("java:comp/env");
           DataSource ds=(DataSource)contextNeeded.lookup("mobileConn");//获得连接池。
            con= ds.getConnection();//使用连接池中的连接。
           String condition="select * from user where username = '"+num+
            "' and password ='"+password+"'";
           System.out.println(num+","+password);
           sql=con.createStatement();  
           if(boo){
              ResultSet rs=sql.executeQuery(condition);
              boolean m=false;
              
              while(rs.next()) {
            	  rmb=rs.getInt("rmb");
            	  username=rs.getString("realname");
            	  a=rs.getInt("hours");
            	  b=rs.getInt("minutes");
            	  c=rs.getInt("seconds");
            	  m=true;
              }
              
              if(m==true){ 
                  //调用登录成功的方法:
                  success(request,response,num,password,rmb,username,a,b,c);
                  
                  RequestDispatcher dispatcher=
                  request.getRequestDispatcher("login.jsp");//转发
                  dispatcher.forward(request,response);
              }
              else{
                  String backNews="您输入的用户名不存在，或密码不般配";
                  //调用登录失败的方法:
                  fail(request,response,num,backNews); 
              }
           }
           else{
                  String backNews="请输入用户名和密码";
                  fail(request,response,num,backNews);
           }
           con.close();//连接返回连接池。
      }
      catch(SQLException exp){
          String backNews=""+exp;
          fail(request,response,num,backNews);
      }
      catch(NamingException exp){
          String backNews="没有设置连接池"+exp;
          fail(request,response,num,backNews); 
      }
      finally{
        try{
             con.close();
        }
        catch(Exception ee){}
      } 
   }
   public void success(HttpServletRequest request,HttpServletResponse response,String num,String password,int rmb,String realname,int a,int b,int c) {
      Login loginBean=null;
      HttpSession session=request.getSession(true);
      try{  loginBean=(Login)session.getAttribute("loginBean");
            if(loginBean==null){
               loginBean=new Login();  //创建新的数据模型 。
               session.setAttribute("loginBean",loginBean);
               loginBean=(Login)session.getAttribute("loginBean");
            }
            String name =loginBean.getnum();
            if(name.equals(num)) {
               loginBean.setBackNews(realname+"已经登录了");
            }
            else {  //数据模型存储新的登录用户:
                loginBean.setBackNews(realname+"登录成功");
                loginBean.setrmb(rmb);
                loginBean.setnum(num);
                loginBean.setHours(a);
                loginBean.setMinutes(b);
                loginBean.setSeconds(c);
                
            }
      }
      catch(Exception ee){
            loginBean=new Login();  
            session.setAttribute("loginBean",loginBean);
            loginBean.setBackNews(ee.toString());
            loginBean.setnum(num);
      }
   }
   public void fail(HttpServletRequest request,
                    HttpServletResponse response,
                    String num,String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+num+"登录反馈结果<br>"+backNews+"</h2>") ;
            out.println("返回登录页面或主页<br>");
            out.println("<a href =login.jsp>登录页面</a>");
            out.println("<br><a href =index.jsp>主页</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}