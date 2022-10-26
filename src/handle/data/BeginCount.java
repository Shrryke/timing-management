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
 * Servlet implementation class BeginCount
 */
@WebServlet("/BeginCount")
public class BeginCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeginCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Date date=new Date();
		Login login=(Login)request.getSession().getAttribute("loginBean");
		if(login.getHours()==0&&login.getMinutes()==0&&login.getSeconds()==0){
			login.setBackNews("请先上机");
			request.getSession().setAttribute("loginBean", login);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else
		{
			int a=date.getHours()-login.getHours();
			int b=date.getMinutes()-login.getMinutes();
			int c=date.getSeconds()-login.getSeconds();
			if(c<0) {
				b-=1;
				c+=60;
			}
			if(b<0) {
				a-=1;
				b+=60;
			}
			if(b!=0||c!=0)
				a+=1;
			int cost=a*10;
			int rest=0;
			
			rest=login.getrmb()-cost;
		      String sql="update user set rmb=?,hours=?,minutes=?,seconds=? where username=?";
		      Object[]params= {rest,0,0,0,login.getnum()};
		      if(Utils.addupdel(sql, params)) {
		    	  login.setrmb(rest);
		    	  login.setHours(0);
		    	  login.setMinutes(0);
		    	  login.setSeconds(0);
		    	  request.getSession().setAttribute("loginBean",login);
		    	  request.removeAttribute("signal");
		    	  request.getRequestDispatcher("login.jsp").forward(request, response);
		      }
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
