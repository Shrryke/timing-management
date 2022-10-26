package save.data;
public class Register{  
    String  num="" , 
            realname="",
            backNews="未登录";
    int rmb;
    
	 public void setrmb(int rmb){  
	       this.rmb = rmb;
	    }
	 public int getrmb(){  
	       return rmb;
	    }
	
    public void setnum(String num){  
       this.num=num;
    }
    public String getnum(){  
       return num;
    }
   
    
    public void setRealname(String realname){  
       this.realname=realname;
    }
    public String getRealname(){  
       return realname;
    }
    public void setBackNews(String backNews){  
       this.backNews=backNews;
    }
    public String getBackNews(){  
       return backNews;
    }
}
