package dataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import model.Data;
import model.Hourly;
import model.Param;
import util.GetData;

public class DaoUtil {
   //插入当天的天气
   public static boolean insertToday(Data data){
	   System.out.println("====插入当天的数据=====");
	   SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
	   String today=sm.format(new Date());
	   String status="";
	   String high="";
	   String low="";
	   String fx=Param.fx;//风向
	   String fs=Param.fs;//风速
	   String time=today;
	   String id="1";
	   List<Hourly> hourly = data.getHourly();
	   for(Hourly h:hourly){
	   if(h.getTime().indexOf("14:00")!=-1&&h.getTime().indexOf(today)!=-1) {
		    high=h.getTemperature();
	   }
	   if(h.getTime().indexOf("07:00")!=-1&&h.getTime().indexOf(today)!=-1) {
		    status+=GetData.changeString(h.getText())+"~";
		    low=h.getTemperature();
	   }
	  
	   if(h.getTime().indexOf("17:00")!=-1&&h.getTime().indexOf(today)!=-1) {
		   status+=GetData.changeString(h.getText());
	   }
	   }
	   String sql = "update weather set status='"+status+"', high='"+high+"', low='"+low+"',fx='"+fx+"',fs='"+fs+"',time='"+time+"' where id="+id;
	   System.out.println(sql);
	   dao dao=new dao();
	   Connection conn=dao.openConn();
	  
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
			conn.close();
			st.close();
			dao = null;
			conn =null;
			st=null;
			System.out.println("修改数据成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	   return true;
   }
   //插入未来天气
   
   public static boolean insertFutrue(JSONArray array) {
	   System.out.println("====修改数据=====");
	   System.out.println(array);
	   int id=0;
	   for(int i=0;i<5;i++){
		   Object object = array.get(i);
		   JSONObject obj=JSONObject.parseObject(object.toString());
		   String status=obj.getString("text");
		   String high=obj.getString("high");
		   String low=obj.getString("low");
		   String fx="S";
		   String fs=obj.getString("wind");
		   String time=obj.getString("date");
		   id++;
		   String sql = "update weather set status='"+status+"', high='"+high+"', low='"+low+"',fx='"+fx+"',fs='"+fs+"',time='"+time+"' where id="+id;
		   System.out.println(sql);
		   dao dao=new dao();
		   Connection conn=dao.openConn();
		   try {
			Statement st=conn.createStatement();
			st.executeUpdate(sql);
			conn.close();
			st.close();
			dao = null;
			conn =null;
			st=null;
			System.out.println("修改数据成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }//end loop
	  
	   return false;
   }
   
}
