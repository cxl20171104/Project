package com.alphasta.commons.statisticsUtils;
//此类用于生成  违纪行为分类情况表 的党纪处分部分
public class SqlMaker7 {
	public static String maker(String start,String end,String fields){
    	
    	StringBuffer sb=new StringBuffer();
    	sb.append("SELECT");
    	sb.append(" d.name as name,if(t.A is null,0,t.A)as A,if(t.B is null,0,t.B)as B,if(t.C is null,0,t.C)as C,if(t.D is null,0,t.D)as D,if(t.E is null,0,t.E)as E,");

    			sb.append(" if(t.F is null,0,t.F)as F,if(t.G is null,0,t.G)as G,if(t.H is null,0,t.H)as H,if(t.I is null,0,t.H)as I");
    					sb.append(" FROM(SELECT c.blResult_djcf AS blResult_djcf,sum(c.A) AS A,sum(c.B) AS B,sum(c.C) AS C,");
    			sb.append(" sum(c.D) AS D,sum(c.E) AS E,sum(c.F) AS F,sum(c.G) AS G ,sum(c.H) AS H ,sum(c.I) AS I  ");
    	sb.append(" FROM(SELECT IF (LOCATE('0119', pu.cfResult) = 0 ,0,SUBSTRING(pu.cfResult,LOCATE('0119', pu.cfResult),6)) AS blResult_djcf,");
    	sb.append(" IF (pu.cfResult IS not NULL,1,0) AS A,");
    	sb.append(" IF (LOCATE('1', z.zyResult) = 0 OR LOCATE('1', z.zyResult) IS NULL,0,1) AS B,");
    	sb.append(" IF (LOCATE('2', z.zyResult) = 0 OR LOCATE('2', z.zyResult) IS NULL,0,1) AS C,");
    	sb.append(" IF (LOCATE('3', z.zyResult) = 0 OR LOCATE('3', z.zyResult) IS NULL,0,1) AS D,");
    	sb.append(" IF (LOCATE('4', z.zyResult) = 0 OR LOCATE('4', z.zyResult) IS NULL,0,1) AS E,");
    	sb.append(" IF (LOCATE('5', z.zyResult) = 0 OR LOCATE('5', z.zyResult) IS NULL,0,1) AS F,");
    	sb.append(" IF (LOCATE('6', z.zyResult) = 0 OR LOCATE('6', z.zyResult) IS NULL,0,1) AS G,");
    	sb.append(" IF (LOCATE('7', z.zyResult) = 0 OR LOCATE('7', z.zyResult) IS NULL,0,1) AS H,");
    	sb.append(" IF (pro.detail=1,1,0) AS I");
    	sb.append(" FROM problemclues p  join  zyviolation z on z.reflectedId=p.reflectedPersonId  join punishment pu on pu.reflectedId=p.reflectedPersonId join progress pro on pro.causeId=p.id and pro.pointValue=25  WHERE p.receiveDate BETWEEN '"+start+"' AND '"+end+"' "+fields+") c GROUP BY c.blResult_djcf) t");
    	sb.append(" RIGHT JOIN dict d ON d.value= t.blResult_djcf WHERE d.dictPid = '0119'");
    	return sb.toString();
    }

}
