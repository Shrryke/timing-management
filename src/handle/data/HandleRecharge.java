package handle.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import save.data.Login;

/**
 * Servlet implementation class HandleRecharge
 */
@WebServlet("/HandleRecharge")
public class HandleRecharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleRecharge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int money=Integer.parseInt(request.getParameter("money"));
	    Login login=(Login)request.getSession().getAttribute("loginBean");
	      String username=login.getnum();
	      System.out.println(username+","+login.getrmb());
	      
	      money+=login.getrmb();
	      
	      String sql="update user set rmb=? where username=?";
	      Object[]params= {money,username};
	      if(Utils.addupdel(sql, params)) {
	    	  login.setrmb(money);
	    	  request.getSession().setAttribute("loginBean",login);
	    	  request.getRequestDispatcher("login.jsp").forward(request, response);
	      }
	      
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
