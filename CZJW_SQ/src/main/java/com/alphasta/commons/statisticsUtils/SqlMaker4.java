package com.alphasta.commons.statisticsUtils;
//该类用于生成 纪委案件处理情况表 处理结果件数数据
public class SqlMaker4 {
	 public static String maker(String start,String end,String fields){
    	 StringBuffer sb=new StringBuffer(); 
    	sb.append("SELECT ");
    	sb.append("if(t.A is null,0,t.A)as A,if(t.B is null,0,t.B)as B,if(t.C is null,0,t.C)as C,if(t.D is null,0,t.D)as D,if(t.E is null,0,t.E)as E ");
    	sb.append("FROM(SELECT c.problemLand AS problemLand,sum(c.A) AS A,sum(c.B) AS B,sum(c.C) AS C,sum(c.D) AS D,sum(c.E) AS E FROM ");
    	sb.append("(SELECT problemLand,IF (LOCATE('0119',pu.cfResult) = 0 OR LOCATE('0119',pu.cfResult) IS NULL ,0,1) AS A,IF (LOCATE('0118',pu.cfResult) = 0 OR LOCATE('0118',pu.cfResult) IS NULL,0,1) AS B,");
    	sb.append("IF (LOCATE('0117',pu.cfResult)=0 OR LOCATE('0117',pu.cfResult)=0 IS NULL,0,1) AS C,");
    	sb.append("IF (LOCATE('0116',pu.cfResult) = 0 OR LOCATE('0116',pu.cfResult) IS NULL,0,1) AS D,");
    	sb.append("IF (LOCATE('0121', pu.cfResult) = 0 OR LOCATE('0121', pu.cfResult) IS NULL,0,1) AS E  ");
    	sb.append("FROM problemclues p join  punishment pu on p.reflectedPersonId=pu.reflectedId  WHERE p.receiveDate BETWEEN '"+start+"' AND '"+end+"'  " +fields+") c GROUP BY c.problemLand) t");
    	sb.append(" RIGHT JOIN dict d ON d.value= t.problemLand WHERE d.dictPid = '0108'");

         return sb.toString();
    }

    
    public static void main(String[] args) {
    	System.out.println(maker("2018-01-01", "2018-12-31","4"));
	}
    
}
