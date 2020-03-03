package com.alphasta.commons.statisticsUtils;

public class SqlMaker0 {
     public static String maker(String start,String end,String startLastYear,String endLastYear,String fields){
    	  String rsult= "select n.`name` as name,(n.A+n.B+n.C+n.D+n.E+n.F+n.G)as total,"+
    			  " n.A as A,(case when o.A is null and n.A !=0 then '100%' when o.A =0 and n.A!=0 then '100%' when  n.A=0 then '0%' else CONCAT( FORMAT((n.A-o.A)/o.A*100,0),'%') end) as A1,"+
    	           " n.B as B,(case when o.B is null and n.B !=0 then '100%' when o.B =0 and n.B!=0 then '100%' when  n.B=0 then '0%' else CONCAT( FORMAT((n.B-o.B)/o.B*100,0),'%') end) as B1,"+
    	           " n.C as C,(case when o.C is null and n.C !=0 then '100%' when o.C =0 and n.C!=0 then '100%' when  n.C=0 then '0%' else CONCAT( FORMAT((n.C-o.C)/o.C*100,0),'%') end) as C1,"+
    	           " n.D as D,(case when o.D is null and n.D !=0 then '100%' when o.D =0 and n.D!=0 then '100%' when  n.D=0 then '0%' else CONCAT( FORMAT((n.D-o.D)/o.D*100,0),'%') end) as D1,"+
    	           " n.E as E,(case when o.E is null and n.E !=0 then '100%' when o.E =0 and n.E!=0 then '100%' when  n.E=0 then '0%' else CONCAT( FORMAT((n.E-o.E)/o.E*100,0),'%') end) as E1,"+
    	           " n.F as F,(case when o.F is null and n.F !=0 then '100%' when o.F =0 and n.F!=0 then '100%' when  n.F=0 then '0%' else CONCAT( FORMAT((n.F-o.F)/o.F*100,0),'%') end) as F1,"+
    	           " n.G as G,(case when o.G is null and n.G !=0 then '100%' when o.G =0 and n.G!=0 then '100%' when  n.G=0 then '0%' else CONCAT( FORMAT((n.G-o.G)/o.G*100,0),'%') end) as G1"+
	       " from ("+makeCommon(start,end,fields)+")n "+
			"left join" 
			+"("+makeCommon(startLastYear,endLastYear,fields)+")o  on n.be=o.be";
    	 
    	 return rsult;
     }
     
     public static String makeCommon(String start,String end,String fields){
    	 StringBuffer sb=new StringBuffer(); 
    	 sb.append("select m.be as be,d.name as name,");
    	 sb.append("IF(m.A is null,0,m.A) as A,");
    	 sb.append("IF(m.B is null,0,m.B) as B,");
    	 sb.append("IF(m.C is null,0,m.C) as C,");
    	 sb.append("IF(m.D is null,0,m.D) as D,");
    	 sb.append( "IF(m.E is null,0,m.E) as E,");
    	 sb.append("IF(m.F is null,0,m.F) as F,");
    	 sb.append("IF(m.G is null,0,m.G) as G ");
    	 sb.append("from dict d  left join (");
    	 sb.append("select be, sum(t.A)as A,sum(t.B) as B,sum(t.C) as C,sum(t.D)as D,sum(t.E)as E,sum(t.F)as F,sum(t.G)as G ");
    	 sb.append("from (	SELECT");
         sb.append(" r.rank AS be,");
   		 sb.append("IF (LOCATE(1,  z.zyResult) = 0 OR LOCATE(1, z.zyResult) IS NULL,0,1) AS A,");
   		 sb.append("IF (LOCATE(2,  z.zyResult) = 0 OR LOCATE(2, z.zyResult) IS NULL,0,1) AS B,");
         sb.append("IF (LOCATE(3,  z.zyResult) = 0 OR LOCATE(3, z.zyResult) IS NULL,0,1) AS C,");
         sb.append("IF (LOCATE(4,   z.zyResult) = 0 OR LOCATE(4,  z.zyResult) IS NULL,0,1) AS D,");
         sb.append(" IF (LOCATE(5,  z.zyResult) = 0 OR LOCATE(5,  z.zyResult) IS NULL,0,1) AS E,");
         sb.append(" IF (LOCATE(6,  z.zyResult) = 0 OR LOCATE(6,  z.zyResult) IS NULL,0,1) AS F,");
         sb.append(" IF (LOCATE(7,  z.zyResult) = 0 OR LOCATE(7,  z.zyResult) IS NULL,0,1) AS G ");
         sb.append("FROM");
         sb.append(" problemclues p  join zyviolation z on z.reflectedId=p.reflectedPersonId JOIN reflectedperson r on r.id=p.reflectedPersonId  where p.receiveDate BETWEEN '"+start+"' and '"+end+"' "+fields+") t GROUP BY t.be");
         sb.append(")m  on m.be=d.value where d.dictPid='0106'");
         return sb.toString();
     }
     
     public static void main(String[] args) {
		
	}
}
