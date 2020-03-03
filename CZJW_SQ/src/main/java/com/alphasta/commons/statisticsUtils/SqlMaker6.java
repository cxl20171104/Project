package com.alphasta.commons.statisticsUtils;
//此类用于生成 纪委案件处理情况表  处置线索件数 立案件总数（有厅局级）  处分件数（有厅局级）
public class SqlMaker6 {
	public static String maker(String start,String end,String fields){
   	 
   	 StringBuffer sb=new StringBuffer(); 
   	 
   	 sb.append(" SELECT ");
   	 sb.append("d.name as name,if(t.A is null,0,t.A)as A,if(t.B is null,0,t.B)as B,if(t.C is null,0,t.C)as C,if(t.D is null,0,t.D)as D,if(t.E is null,0,t.E)as E,");

   	 sb.append("	 if(t.F is null,0,t.F)as F ");
   			 sb.append("FROM(SELECT c.problemLand AS problemLand,sum(c.A) AS A,sum(c.B) AS B,sum(c.C) AS C,");
   			 sb.append("sum(c.D) AS D,sum(c.E) AS E,sum(c.F) AS F ");
   	 sb.append("FROM(SELECT problemLand,");
   	 sb.append("IF (pro.detail is null,0,1) AS A,");
   	 sb.append("IF (pro.detail='1' ,1,0) AS B,");
   	 sb.append("IF (pro.detail='1'and red.rank=1,1,0) AS C,");
   	 sb.append("IF (p.finalState=-1,0,1) AS D,");
   	 sb.append("IF (pu.cfResult IS not NULL,0,1) AS E,");
   	 sb.append("IF (pu.cfResult IS not NULL AND red.rank=1,1,0) AS F");

   	 sb.append(" FROM problemclues p join progress pro on p.id=pro.causeId and pro.pointValue=25 join  reflectedperson red on p.reflectedPersonId=red.id  join  punishment pu on p.reflectedPersonId=pu.reflectedId  WHERE p.receiveDate BETWEEN '"+start+"' AND '"+end+"' "+fields+") c GROUP BY c.problemLand) t ");
   	 sb.append("RIGHT JOIN dict d ON d.value= t.problemLand WHERE d.dictPid = '0108'");
   	 return sb.toString();
   	 
    }

      public static void main(String[] args) {
	//	System.out.println(maker("2018-01-01", "2018-12-31"));
	}
}
