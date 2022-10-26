package handle.data;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import save.data.Login;

/**
 * Servlet implementation class End
 */
@WebServlet("/End")
public class End extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public End() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date date=new Date();
		Login login=(Login)request.getSession().getAttribute("loginBean");
		if(login.getrmb()>0) {
	      String sql="update user set hours=?,minutes=?,seconds=? where username=?";
	      Object[]params= {date.getHours(),date.getMinutes(),date.getSeconds(),login.getnum()};
	      if(Utils.addupdel(sql, params)) {
	    	  login.setHours(date.getHours());
	    	  login.setMinutes(date.getMinutes());
	    	  request.getSession().setAttribute("loginBean",login);
	    	  request.setAttribute("signal","begin");
	    	  request.getRequestDispatcher("login.jsp").forward(request, response);
	      }else {
	    	  request.getRequestDispatcher("login.jsp").forward(request, response);
	      }
		}else {
			login.setBackNews("请先充值");
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
