package work;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dataSource.DaoUtil;
import model.Data;
import model.Param;
import util.GetData;
import util.GsonUtil;

public class Start {
    public static void main(String[] args) {
    	timer();
	}
    
    public static void timer() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7); // ����ʱ
        calendar.set(Calendar.MINUTE, 0);    // ���Ʒ�
        calendar.set(Calendar.SECOND, 0);    // ������
        Date time = calendar.getTime();     // �ó�ִ�������ʱ��,�˴�Ϊ�����12��00��00
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
          public void run() {
            try {
            	String json1 = GetData.json(Param.path1);
            	System.out.println(json1);
            	JSONObject obj=JSONObject.parseObject(json1);
            	Object object = obj.get("weather");
            	JSONArray array=JSONArray.parseArray(object.toString());
            	Object object2 = array.get(0);
            	JSONObject obj2=JSONObject.parseObject(object2.toString());
            	JSONObject obj3=JSONObject.parseObject(obj2.get("now").toString());
            	//��õ���ķ���
            	Param.fx=(String)obj3.get("wind_direction");
            	//����
            	Param.fs=(String)obj3.get("wind_speed");
            	
            	System.out.println();
				//δ������
				JSONArray future=JSONArray.parseArray(obj2.get("future").toString());
				DaoUtil.insertFutrue(future);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
          }
        }, time, 1000 * 60 * 60 * 24);// �����趨����ʱÿ��̶�ִ��
      }
}
