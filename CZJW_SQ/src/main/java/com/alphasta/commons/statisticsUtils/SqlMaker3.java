package com.alphasta.commons.statisticsUtils;

public class SqlMaker3 {
   public static  String maker(String start,String end ,String startLastYear,String endLastYear,String fields){
	   
	  
	   
	   
	   
	  String result= "select x1.name as name,"
			  +" if(x1.A is null,0,x1.A) as A, "
			  +"  if(x1.B is null,0,x1.B) as B,"
			  +"  if(x1.C is null,0,x1.C) as C,"
			  +" if( x1.D is null,0,x1.D) as D,"
			  +"  if(x1.E is null,0,x1.E) as E,"
			  +"  if( x1.F is null,0,x1.F) as F,"
			  +" if((x1.A+x1.C+x1.D+x1.E+x1.F) is null,0,(x1.A+x1.C+x1.D+x1.E+x1.F)) as total,"
			  +" if((x1.A+x1.C+x1.D+x1.E+x1.F) is null,0,(x1.A+x1.C+x1.D+x1.E+x1.F)) as totalA,"
			  +" if((x1.A+x1.C+x1.D+x1.E) is null,0,(x1.A+x1.C+x1.D+x1.E)) as totalB,"
			  +"   if( FORMAT((x1.A-x2.A)/x2.A*100,0) is null,0,FORMAT((x1.A-x2.A)/x2.A*100,0)) as A1,"
			  +"  if(  FORMAT((x1.B-x2.B)/x2.B*100,0) is null,0,FORMAT((x1.B-x2.B)/x2.B*100,0)) as B1,"
			  +"  if( FORMAT((x1.C-x2.C)/x2.C*100,0) is null,0,FORMAT((x1.C-x2.C)/x2.C*100,0)) as C1,"
			  +"   if( FORMAT((x1.D-x2.D)/x2.D*100,0) is null,0,FORMAT((x1.D-x2.D)/x2.D*100,0)) as D1,"
			  +"   if( FORMAT((x1.E-x2.E)/x2.E*100,0) is null,0,FORMAT((x1.E-x2.E)/x2.E*100,0)) as E1,"
			  +"  if( FORMAT((x1.F-x2.F)/x2.F*100,0) is null,0,FORMAT((x1.F-x2.F)/x2.F*100,0)) as F1,"
			  +"  if( FORMAT((x1.A+x1.B+x1.C+x1.D+x1.E+x1.F-x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)/(x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)*100,0) is null,0,FORMAT((x1.A+x1.B+x1.C+x1.D+x1.E+x1.F-x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)/(x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)*100,0)) as total1,"
			  +"  if( FORMAT((x1.A+x1.B+x1.C+x1.D+x1.E+x1.F-x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)/(x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)*100,0) is null,0,FORMAT((x1.A+x1.B+x1.C+x1.D+x1.E+x1.F-x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)/(x2.A+x2.B+x2.C+x2.D+x2.E+x2.F)*100,0)) as totalA1,"
			  +"  if( FORMAT((x1.A+x1.B+x1.C+x1.D+x1.E-x2.A+x2.B+x2.C+x2.D+x2.E)/(x2.A+x2.B+x2.C+x2.D+x2.E)*100,0) is null,0,FORMAT((x1.A+x1.B+x1.C+x1.D+x1.E-x2.A+x2.B+x2.C+x2.D+x2.E)/(x2.A+x2.B+x2.C+x2.D+x2.E)*100,0)) as totalB1"
			  +"  from (    "+makeCommon(start,end,fields)+"   )x1"
              +" left join "
              +" ("+makeCommon(startLastYear,endLastYear,fields)+")x2 on x1.problemLand=x2.problemLand";

              return result;
   }
   
   
   
 public static String makeCommon(String start,String  end,String fields){
	   
	   String common="select * from(SELECT"
				+   "  c.problemLand as problemLand,"
			        
	+"			                        sum(c.A)as A,"
					+"			        sum(c.B)as B,"
				         
	+"		                            sum(c.C)as C,"
						  +"            sum(c.D)as D,"
				         
	+"		                            sum(c.E)as E,"
						  +"			sum(c.F)as F"
							+"		FROM"

	+"		("
	
	
							
							
							
    +"			select 	problemLand, "

	+"		    IF (LOCATE('2',pro.detail)=0, 0, 1)  AS A,"

	+"		    IF (LOCATE('3',pro.detail)=0, 0, 1) AS B, "

	+"		    IF (LOCATE('4',pro.detail)=0 , 0, 1) AS C,"
 
	+"	        IF (LOCATE('5',pro.detail)=0, 0, 1) AS D,"

	+"	        IF (LOCATE('6',pro.detail)=0, 0, 1) AS E,"

     +"         IF (LOCATE('7',pro.detail)=0 , 0, 1) AS F"

				  
	                +"	FROM"
					+"	problemclues p join progress pro on pro.causeId=p.id and pro.pointValue=25   where p.receiveDate  BETWEEN '"+start+"' and '"+end+"' "+fields+""
					+"	) c"
					
					
	                
	                
	                
	                
	                
	                
	                
	                +"	GROUP BY"
					+" c.problemLand)t right join dict d on d.value=t.problemLand  where d.dictPid='0108'";
	   return common;
	   
   }

     
     
}
