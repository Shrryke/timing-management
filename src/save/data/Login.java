package save.data;
import java.util.*;
public class Login {
	private String realname="";
	private int rmb=0;
    private String num="";
    private String backNews="未登录";
	private int hours=0;
	private int minutes=0;
	private int seconds=0;
    public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public void setrmb(int rmb){  
	       this.rmb = rmb;
	    }
	 public int getrmb(){  
	       return rmb;
	    }
	
	 public void setrealname(String realname){  
	       this.realname = realname;
	    }
	 public String getrealname(){  
	       return realname;
	    }

    public void setnum(String num){  
       this.num = num;
    }
    public String getnum(){
       return num;
    }
    public void setBackNews(String s) {
       backNews = s;
    } 
    public String getBackNews(){
       return backNews;
    }
}
