package com.alphasta.commons.statisticsUtils;

public class SqlMaker9 {
   public static String maker(String start,String end){
	   StringBuffer sb=new StringBuffer(); 
	   
	   sb.append("SELECT");
	   sb.append(" d.name as name,if(t.A is null,0,t.A)as A,if(t.B is null,0,t.B)as B,if(t.C is null,0,t.C)as C,if(t.D is null,0,t.D)as D,if(t.E is null,0,t.E)as E,");
	   sb.append(" if(t.F is null,0,t.F)as F,if(t.G is null,0,t.G)as G,if(t.H is null,0,t.H)as H,if(t.I is null,0,t.I)as I, if(t.J is null,0,t.J)as J,");
	   sb.append(" if(t.K is null,0,t.K)as K,if(t.L is null,0,t.L)as L,if(t.M is null,0,t.M)as M,if(t.N is null,0,t.N)as N,if(t.O is null,0,t.O)as O,");
	   sb.append(" if(t.P is null,0,t.P)as P,if(t.Q is null,0,t.Q)as Q,if(t.R is null,0,t.R)as R,if(t.S is null,0,t.S)as S,if(t.T is null,0,t.T)as T,if(t.U is null,0,t.U)as U, if(t.A+t.B+t.C+t.D+t.E+t.F+t.G+t.H+t.I+t.J+t.K+t.L+t.M+t.N+t.O+t.P is null,0,t.A+t.B+t.C+t.D+t.E+t.F+t.G+t.H+t.I+t.J+t.K+t.L+t.M+t.N+t.O+t.P+t.Q+t.R+t.S+t.T+t.U)as total");
	   
	   
	   
	   
	   sb.append(" FROM(SELECT p.problemLand AS problemLand,sum(p.A) AS A,sum(p.B) AS B,sum(p.C) AS C,");
	   sb.append(" sum(p.D) AS D,sum(p.E) AS E,sum(p.F) AS F,sum(p.G) AS G ,sum(p.H) AS H ,sum(p.I) AS I,sum(p.J) AS J,sum(p.K) AS K,sum(p.L) AS L,");
	   sb.append(" sum(p.M) AS M,sum(p.N) AS N,sum(p.O) AS O,sum(p.P) AS P,sum(p.Q) AS Q,sum(p.R) AS R,sum(p.S) AS S,sum(p.T) AS T,sum(p.U) AS U ");       
	   sb.append(" FROM(SELECT problemLand,");
	   sb.append(" IF (LOCATE('1', fields) = 0  OR LOCATE('1', fields) IS NULL,0,1) AS A,");
	   sb.append(" IF (LOCATE('2', fields) = 0  OR LOCATE('2', fields) IS NULL,0,1) AS B,");
	   sb.append(" IF (LOCATE('3', fields) = 0  OR LOCATE('3', fields) IS NULL,0,1) AS C,");
	   sb.append(" IF (LOCATE('4', fields) = 0  OR LOCATE('4', fields) IS NULL,0,1) AS D,");
	   sb.append(" IF (LOCATE('5', fields) = 0  OR LOCATE('5', fields) IS NULL,0,1) AS E,");
	   sb.append(" IF (LOCATE('6', fields) = 0  OR LOCATE('6', fields) IS NULL,0,1) AS F,");
	   sb.append(" IF (LOCATE('7', fields) = 0  OR LOCATE('7', fields) IS NULL,0,1) AS G,");
	   sb.append(" IF (LOCATE('8', fields) = 0  OR LOCATE('8', fields) IS NULL,0,1) AS H,");
	   sb.append(" IF (LOCATE('9', fields) = 0  OR LOCATE('9', fields) IS NULL,0,1) AS I,");
	   sb.append(" IF (LOCATE('10', fields) = 0 OR LOCATE('10', fields) IS NULL,0,1) AS J,");
	   sb.append(" IF (LOCATE('11', fields) = 0 OR LOCATE('11', fields) IS NULL,0,1) AS K,");
	   sb.append(" IF (LOCATE('12', fields) = 0 OR LOCATE('12', fields) IS NULL,0,1) AS L,");
	   sb.append(" IF (LOCATE('13', fields) = 0 OR LOCATE('13', fields) IS NULL,0,1) AS M,");
	   sb.append(" IF (LOCATE('14', fields) = 0 OR LOCATE('14', fields) IS NULL,0,1) AS N,");
	   sb.append(" IF (LOCATE('15', fields) = 0 OR LOCATE('15', fields) IS NULL,0,1) AS O,");
	   sb.append(" IF (LOCATE('16', fields) = 0 OR LOCATE('16', fields) IS NULL,0,1) AS P,");
	   sb.append(" IF (LOCATE('17', fields) = 0 OR LOCATE('17', fields) IS NULL,0,1) AS Q,");
	   sb.append(" IF (LOCATE('18', fields) = 0 OR LOCATE('18', fields) IS NULL,0,1) AS R,");
	   sb.append(" IF (LOCATE('19', fields) = 0 OR LOCATE('19', fields) IS NULL,0,1) AS S,");
	   sb.append(" IF (LOCATE('20', fields) = 0 OR LOCATE('20', fields) IS NULL,0,1) AS T,");
	   sb.append(" IF (LOCATE('21', fields) = 0 OR LOCATE('21', fields) IS NULL,0,1) AS U");
	   sb.append(" FROM problemclues   WHERE receiveDate BETWEEN '"+start+"' AND '"+end+"') p GROUP BY p.problemLand) t");
	   sb.append(" RIGHT JOIN dict d ON d.value= t.problemLand WHERE d.dictPid = '0108'");
	   return sb.toString();
   }
    public static void main(String[] args) {
		System.out.println(maker("2018-01-01", "2018-12-31"));
	}
}
