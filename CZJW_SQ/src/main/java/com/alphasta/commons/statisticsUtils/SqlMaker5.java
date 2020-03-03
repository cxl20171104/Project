package com.alphasta.commons.statisticsUtils;
//此类用于生成 该类用于生成 纪委案件处理情况表 违纪件数数据
public class SqlMaker5 {
	 public static String maker(String start,String end,String fields){
	    	StringBuffer sb=new StringBuffer(); 	
	    	sb.append(" SELECT ");
	    	sb.append(" if(t.A is null,0,t.A)as A,if(t.B is null,0,t.B)as B,if(t.C is null,0,t.C)as C,if(t.D is null,0,t.D)as D,if(t.E is null,0,t.E)as E,");

	    	sb.append(" if(t.F is null,0,t.F)as F,if(t.G is null,0,t.G)as G ");
	    sb.append("    FROM(SELECT c.problemLand AS problemLand,sum(c.A) AS A,sum(c.B) AS B,sum(c.C) AS C,");
	    		sb.append(" sum(c.D) AS D,sum(c.E) AS E,sum(c.F) AS F ,sum(c.G) AS G  ");
	    sb.append("  FROM(SELECT problemLand,");
	    sb.append("IF (LOCATE('1', z.zyResult) = 0 OR LOCATE('1',z.zyResult)IS NULL,0,1) AS A,");
	    sb.append("IF (LOCATE('2', z.zyResult) = 0 OR LOCATE('2',z.zyResult) IS NULL,0,1) AS B,");
	    sb.append("IF (LOCATE('3', z.zyResult) = 0 OR LOCATE('3',z.zyResult) IS NULL,0,1) AS C,");
	    sb.append("IF (LOCATE('4', z.zyResult) = 0 OR LOCATE('4', z.zyResult) IS NULL,0,1) AS D,");
	    sb.append("IF (LOCATE('5', z.zyResult) = 0 OR LOCATE('5',z.zyResult)IS NULL,0,1) AS E,");
	    sb.append("IF (LOCATE('6', z.zyResult) = 0 OR LOCATE('6',z.zyResult) IS NULL,0,1) AS F,");
	    sb.append("IF (LOCATE('7', z.zyResult) = 0 OR LOCATE('7',z.zyResult) IS NULL ,0,1) AS G ");
	    sb.append(" FROM problemclues p join zyviolation z on z.reflectedId=p.reflectedPersonId  WHERE receiveDate BETWEEN '"+start+"' AND '"+end+"' "+fields+") c GROUP BY c.problemLand) t");
	    sb.append("  RIGHT JOIN dict d ON d.value= t.problemLand WHERE d.dictPid = '0108'");
	    	
	     return sb.toString();
	    	
	    }
        public static void main(String[] args) {
        	String a="1";
        	String special=" and LOCATE('"+a+"',p.fields)!=0";
        	System.out.println(SqlMaker5.maker("2018-01-01", "2018-12-031", special));
		}
}
