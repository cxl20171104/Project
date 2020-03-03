package com.alphasta.commons.statisticsUtils;
//该类用于生成  纪律审查情况分析表的左侧部分
public class SqlMaker2 {
   public static String make(String start,String end,String startLastYear,String endLastYear,String fields){
	  StringBuffer sb=new StringBuffer(); 
	       sb.append("           select x1.name as name,");
	       sb.append("            if(x1.A is null,0,x1.A)as A,");
	       sb.append("            if(x1.B is null,0,x1.B) as B,");
	       sb.append("            if(x1.C is null, 0,x1.C)as C,");
    		   sb.append("        if(x1.D is null, 0,x1.D)as D,");
    		   sb.append("        if(x1.E is null, 0,x1.E)as E,");
    		   sb.append("        if( x1.F is null,0,x1.F) as F,");
    		   sb.append("        if(x1.total is null, 0,x1.total)as total,");
    		   sb.append("        if( x1.totalA is null,0,x1.totalA) as totalA,");
    		   sb.append("        if(x1.totalB is null, 0,x1.totalB)as totalB,");
    		   sb.append("        if((x1.A-x2.A)/x2.A*100 is null,0,(x1.A-x2.A)/x2.A*100) as A1,");
    		   sb.append("        if((x1.B-x2.B)/x2.B*100 is null,0,(x1.B-x2.B)/x2.B*100) as B1,");
    		   sb.append("        if( (x1.C-x2.C)/x2.C*100 is null,0,(x1.C-x2.C)/x2.C*100)as C1,");
    		  sb.append("         if((x1.D-x2.D)/x2.D*100 is null,0,(x1.D-x2.D)/x2.D*100) as D1,");
    		  sb.append("         if((x1.E-x2.E)/x2.E*100 is null,0,(x1.E-x2.E)/x2.E*100) as E1,");
    		  sb.append("         if((x1.F-x2.F)/x2.F*100 is null, 0,(x1.F-x2.F)/x2.F*100)as F1,");
    		  sb.append("         if((x1.total-x2.total)/x2.total*100 is null, 0,(x1.total-x2.total)/x2.total*100)      as total1,");
    		  sb.append("         if((x1.totalA-x2.totalA)/x2.totalA*100 is null, 0,(x1.totalA-x2.totalA)/x2.totalA*100)as totalA1,");
    		  sb.append("         if((x1.totalB-x2.totalB)/x2.totalB*100 is null, 0,(x1.totalB-x2.totalB)/x2.totalB*100)as totalB1");
    		  sb.append("     from ("+makeCommon(start,end,fields)+")x1");
              sb.append(" left join ");
              sb.append(" ("+makeCommon(startLastYear,endLastYear,fields)+")x2 on x1.problemLand=x2.problemLand");

  return sb.toString();
   }
   public static String makeCommon(String start,String end,String fields){
	   StringBuffer sb=new StringBuffer(); 
	  sb.append("select * from(SELECT ");
    		  sb.append(" c.problemLand as problemLand,");
		  sb.append("       sum(c.la_total)as total,");
        		 sb.append("	sum(c.tju_la)as A,");
			sb.append("	sum(c.xju_la)as B,");
			sb.append("        sum(c.cf_total)as totalA,");
        		 sb.append(" sum(c.tju_cf)as C,");
		  sb.append("	sum(c.xju_cf)as D,");
			sb.append("       sum(c.ja_total)as totalB,");
        		 sb.append("  sum(c.tju_ja)as E,");
		  sb.append("	sum(c.xju_ja)as F");
			sb.append(" FROM");

          sb.append("	(select 	problemLand,");
			sb.append("  IF ( pro.detail!=null and pro.detail='1' , 1, 0) AS la_total,");

          sb.append("	IF (r.rank = 1 and pro.detail!=null and pro.detail='1', 1,0)  AS tju_la,");

           sb.append("  IF (r.rank = 2 and pro.detail!=null and pro.detail='1', 1, 0) AS xju_la,");

          sb.append("   IF ( pu.cfResult!=null , 1, 0) AS cf_total,");

          sb.append("   IF (r.rank = 1 and pu.cfResult!=null, 1, 0) AS tju_cf,");

          sb.append("   IF (r.rank = 2 and pu.cfResult!=null, 1, 0) AS xju_cf,");

          sb.append("    IF ( p.finalState='-1' , 1, 0) AS ja_total,");

         sb.append("    IF (r.rank = 1 and p.finalState='-1', 1, 0) AS tju_ja,");

         sb.append("    IF (r.rank = 2 and p.finalState='-1', 1, 0) AS xju_ja");
		  sb.append(" FROM");
		  sb.append("	problemclues p join reflectedperson r on r.id=p.reflectedPersonId  join punishment pu on pu.reflectedId=p.reflectedPersonId  join  progress pro on pro.causeId=p.id and pro.pointValue='25'  where p.receiveDate BETWEEN '"+start+"' and '"+end+"'"+fields+"");
	      sb.append("	) c");

      sb.append(" GROUP BY");
		sb.append("	c.problemLand)t right join dict d on d.value=t.problemLand  where d.dictPid='0108'");
	   return sb.toString();
	   
   }
    public static void main(String[] args) {
	}
}
