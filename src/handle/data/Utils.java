package handle.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Utils {
    	public static Connection con =null; 
    	public static PreparedStatement ps=null; 
		public static ResultSet rs=null;
		public static boolean addupdel(String sql,Object[]params){
			try { 
			Context  context = new InitialContext();
             Context  contextNeeded = (Context)context.lookup("java:comp/env");
             DataSource ds=
             (DataSource)contextNeeded.lookup("mobileConn");
             con= ds.getConnection();
             ps=con.prepareStatement(sql);
             if(params!=null){
            	 for (int j = 0; j < params.length; j++) {
						ps.setObject(j+1, params[j]);
				}
             }
             int count=ps.executeUpdate();
				if(count>0)
					return true;
				else
					return false;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}catch(NamingException e) {
				e.printStackTrace();
				return false;
			}finally {
					try {
						if(ps!=null)ps.close();
						if(con!=null)con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
			
		}
		
		public static ResultSet  executeQuery(String sql,Object[]params) {
			try { 
			Context  context = new InitialContext();
             Context  contextNeeded = (Context)context.lookup("java:comp/env");
             DataSource ds=(DataSource)contextNeeded.lookup("mobileConn");
             con= ds.getConnection();
             ps=con.prepareStatement(sql);
             if(params!=null){
            	 for (int j = 0; j < params.length; j++) {
						ps.setObject(j+1, params[j]);
				}
             }
             rs=ps.executeQuery();
             return rs;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}catch(NamingException e) {
				e.printStackTrace();
				return null;
			}finally {
					try {
						if(ps!=null)ps.close();
						if(con!=null)con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
		public static void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn) {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(SQLException e) {
				System.out.println("关闭错误");
				e.printStackTrace();
			}
		}
}
