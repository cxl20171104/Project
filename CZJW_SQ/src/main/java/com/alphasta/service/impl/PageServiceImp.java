package com.alphasta.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.mapper.PageMapper;
import com.alphasta.mapper.UserMapper;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.Report;
import com.alphasta.model.User;
import com.alphasta.service.BaseService;
import com.alphasta.service.PageService;

@Service
public class PageServiceImp extends BaseService implements PageService {
    @Autowired
    PageMapper pageMapper;
    @Autowired
    UserMapper userMapper;
	@Override
	public PageInfo havingPageService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> heavingPageData = pageMapper.heavingPageData(pageInfo);
		pageInfo.setRows(heavingPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.heavingPageData(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		
		return pageInfo;
	}
	@Override
	public PageInfo neweCluesService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> newCluesPageData = pageMapper.newCluesPageData(pageInfo);
				pageInfo.setRows(newCluesPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.newCluesPageData(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo workingService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> workingPageData = pageMapper.workingPageData(pageInfo);
				pageInfo.setRows(workingPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.workingPageData(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo agsWorkingService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				condition.put("organId",getCurrentOrganId());
				pageInfo.setCondition(condition);
				List<ProblemClues> workingPageData = pageMapper.agsWorkingPageData(pageInfo);
				pageInfo.setRows(workingPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.agsWorkingPageData(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo initialService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> verifyPageData = pageMapper.initialServiceM(pageInfo);
				pageInfo.setRows(verifyPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.initialServiceM(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo libraryService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> verifyPageData = pageMapper.libraryPageData(pageInfo);
				pageInfo.setRows(verifyPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.libraryPageData(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo overingService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				if(!getCurrentOrganId().equals(Param.agsId)) {
					condition.put("organId", getCurrentOrganId());
				}
				pageInfo.setCondition(condition);
				List<ProblemClues> overingPageData = pageMapper.overingPageData(pageInfo);
				pageInfo.setRows(overingPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overingPageData(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo overTimePage_czjyService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> overTimePageData_czjy = pageMapper.overTimePageData_czjy(pageInfo);
				pageInfo.setRows(overTimePageData_czjy);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_czjy(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo overTimePage_lascService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> overTimePageData_lasc = pageMapper.overTimePageData_lasc(pageInfo);
				pageInfo.setRows(overTimePageData_lasc);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_lasc(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo overTimePage_ajslService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> overTimePageData_ajsl = pageMapper.overTimePageData_ajsl(pageInfo);
				pageInfo.setRows(overTimePageData_ajsl);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_ajsl(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override//(新添)
	public PageInfo overTimePage_ldckService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> overTimePageData_ldck = pageMapper.overTimePageData_ldck(pageInfo);
				pageInfo.setRows(overTimePageData_ldck);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_ldck(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo overTimePage_zcdcService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				condition.put("zcdc", "暂存待查");
				pageInfo.setCondition(condition);
				List<ProblemClues> overTimePageData_zcdc = pageMapper.overTimePageData_zcdc(pageInfo);
				pageInfo.setRows(overTimePageData_zcdc);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_zcdc(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	@Override
	public PageInfo overTimePage_thhxService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				condition.put("thhx", "谈话函询时间");
				pageInfo.setCondition(condition);				
				List<ProblemClues> overTimePageData_thhx = pageMapper.overTimePageData_thhx(pageInfo);
				pageInfo.setRows(overTimePageData_thhx);
				
				
				
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_thhx(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				
				return pageInfo;
	}
	@Override//处分决定执行期限到期
	public PageInfo overTimePage_takeEffectService(Map<String,Object>condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> overTimePageData_takeEffect = pageMapper.overTimePageData_takeEffect(pageInfo);
				pageInfo.setRows(overTimePageData_takeEffect);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.overTimePageData_takeEffect(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	
	//装载参数用的
	@Override
	public Map<String, Object> MakeMap(ProblemClues problemClues, ReflectedPerson reflectedPerson,
			ReflectingPerson reflectingPerson,Report report, String startDate, String endDate, int page, int rows, String sort,
			String order, String zddb) {
		// TODO Auto-generated method stub
		
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuffer suf=new StringBuffer();
		//有人使用查询
		if(problemClues!=null){
	    Class c=problemClues.getClass();
		
		Field[] fields = c.getDeclaredFields();
		//生成查询条件=====仅用于dataGrid 其他业务不要往这里写
		
		suf.append("");
		for(Field f:fields){
			f.setAccessible(true);
			try {
				if(f.getName()=="serialVersionUID") {
					continue;
				}
				if(f.get(problemClues)!=null&&!f.get(problemClues).toString().equals("")){
					    String str=(String)f.get(problemClues);
					    Pattern pattern = Pattern.compile("^-?[1-9]\\d*$");
			             Matcher isNum = pattern.matcher(str);
			             //如果是领域因为他是多项需要特殊处理
			             if(f.getName().equals("fields")) {
			        	  suf.append(" and LOCATE(CONCAT(',',"+f.get(problemClues)+",','),CONCAT(',',p.fields,','))!=0   ");
			        	  continue;
			             }
			             //通过finalState来传递焦主任 要求的查询  其中 finalState包含3部分信信，并用逗号
	        		     //连接
	        		     if(f.getName().equals("finalState")) {
	        		    	 if(f.get(problemClues).toString().indexOf(",")>=0) {
	        		    		 String[]msgs=f.get(problemClues).toString().split(",");
	        		    		 if(msgs.length==0) {
	        		    			 continue;
	        		    		 }
	        		    	 }
	        		    	 if(f.get(problemClues).toString().indexOf("LZ")>=0) {
	        		    		 suf.append(" JOIN lien l on rd.id= l.reflectedId  ");
	        		    		 if(f.get(problemClues).toString().indexOf(",")>=0) {
	        		    			 String[]msgs=f.get(problemClues).toString().split(",");
	        		    			 if(!msgs[1].equals("NO")) {
			        		    	     suf.append(" and DATE_FORMAT(l.lienTime,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
			        		         }
	        		    			 if(!msgs[2].equals("NO")) {
	        		    				 suf.append(" and DATE_FORMAT(l.lienRelieveTime,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
	        		    			 }
	        		    		 }
	        		    	 }else if(f.get(problemClues).toString().indexOf("CH")>=0) {
	        		    		 suf.append("  JOIN progress pro on pro.causeId= p.id and pro.detail='初步核实时间' ");
	        		    		 if(f.get(problemClues).toString().indexOf(",")>=0) {
	        		    			 String[]msgs=f.get(problemClues).toString().split(",");
	        		    			 if(!msgs[1].equals("NO")) {
			        		    	     suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
			        		         }
	        		    			 if(!msgs[2].equals("NO")) {
	        		    				 suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
	        		    			 }
	        		    		 }
	        		    	 
	        		    	 }else if(f.get(problemClues).toString().indexOf("LA")>=0) {
	        		    		 suf.append("  JOIN progress pro on pro.causeId= p.id and LOCATE('立案时间',pro.detail)!=0 ");
	        		    		 if(f.get(problemClues).toString().indexOf(",")>=0) {
	        		    			 String[]msgs=f.get(problemClues).toString().split(",");
	        		    			 if(!msgs[1].equals("NO")) {
			        		    	     suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
			        		         }
	        		    			 if(!msgs[2].equals("NO")) {
	        		    				 suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
	        		    			 }
	        		    		 }
	        		    		 
	        		    		 
	        		    	 }else if(f.get(problemClues).toString().indexOf("JA")>=0) {
	        		    		 suf.append("  JOIN progress pro on pro.causeId= p.id and pointName='审理室处理结果' ");
	        		    		 if(f.get(problemClues).toString().indexOf(",")>=0) {
	        		    		 String[]msgs=f.get(problemClues).toString().split(",");
     		    			 if(!msgs[1].equals("NO")) {
		        		    	     suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
		        		         }
     		    			 if(!msgs[2].equals("NO")) {
     		    				 suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
     		    			 }
	        		    	 }
	        		    	 
	        		    	 }
	        		    	 else {
	        		    		  //如果是名称使用like
					        	  suf.append("and p."+f.getName()+" like '%"+f.get(problemClues)+"%'   ");  
	        		    	 }
	        		     }
			             if(!isNum.matches()) {
			        	  if(str.indexOf(",")!=-1) {
			        	  //如果有逗号表示多个使用in
			        		  if(!f.getName().equals("finalState")) {
			        			  suf.append("and p."+f.getName()+" in ("+f.get(problemClues)+")");
			        		  }
			        	 
			        	  }else if(str.startsWith("and")){
			        	      suf.append(f.get(problemClues));
			        	  }else {
			        		    	  //如果是名称使用like
						       suf.append("and p."+f.getName()+" like '%"+f.get(problemClues)+"%'   ");  
			        	  }
			              }else {
			        	        //如果是单个数字使用=
			        	        suf.append("and p."+f.getName()+"='"+f.get(problemClues)+"'  ");
			              }
			         
				       }
				       if(f.get(problemClues)!=null&&f.get(problemClues).equals("special")) {
					        suf.append("rd."+f.getName()+"='"+f.get(problemClues)+"' and ");
				       }
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
			if(reflectedPerson!=null){
				
			
		 Class c2=reflectedPerson.getClass();
		 Field[] fields2 = c2.getDeclaredFields();
		 
		 for(Field f:fields2){
				f.setAccessible(true);
				try {
					if(f.get(reflectedPerson)!=null&&!f.get(reflectedPerson).toString().equals("")){
						String str=(String)f.get(reflectedPerson);
						 //监督室处理结果插寻用
						 if(f.getName().equals("jd")) {
							String[]msgs=str.split("_");
							String coreString="";
							if(str.indexOf("1")>=0) {
								coreString=	"谈话函询时间";							
							}
							if(str.indexOf("2")>=0) {
								coreString=	"初步核实时间";							
							}
							if(str.indexOf("3")>=0) {
								coreString=	"暂存待查时间";							
							}
							if(str.indexOf("4")>=0) {
								coreString=	"予以了结时间";							
							}
							if(str.indexOf("5")>=0) {
								coreString=	"诫勉谈话时间";							
							}
							if(str.indexOf("6")>=0) {
								coreString=	"了结澄清时间";							
							}
							if(str.indexOf("7")>=0) {
								coreString=	"谈话提醒时间";							
							}
							if(str.indexOf("8")>=0) {
								coreString=	"诫勉谈话时间";							
							}
							if(str.indexOf("9")>=0) {
								coreString=	"责令检查批评教育时间";							
							}
							if(!coreString.equals("")) {
								
								String a=" join (select p.pointValue as poi,p.causeId as cid,p.time as time,p.detail as detail,p.timeForday as timeForday from  progress p join (select max(pro.time) as maxtime,pro.causeId as cid  from progress pro where pro.pointValue ='3.6' and pro.detail='"+coreString+"' group by pro.causeId) m on "+
										 " p.causeId=m.cid and p.time=m.maxtime )pp on p.id=pp.cid ";  
								     suf.append(a);
	        		    			 if(!msgs[1].equals("NO")) {
			        		    	     suf.append(" and DATE_FORMAT(pp.timeForday,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
			        		         }
	        		    			 if(!msgs[2].equals("NO")) {
	        		    				 suf.append(" and DATE_FORMAT(pp.timeForday,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
	        		    			 }
							}
						
								 continue;
						 }
						 //处分结果查询用
						 if(f.getName().equals("cf")) {
							 String[]msgs=str.split("_");
							    str=msgs[0];
							    if(!str.equals("NO")) {
							    	String coreStr="";
							    	if(str.indexOf(",")>=0) {
							    		String[]strs=str.split(",");
							    		StringBuffer strbuf=new StringBuffer();
							    		int i=0;
							    		for(String s:strs) {
							    			strbuf.append("LOCATE('"+s+"',pro.detail)!=0 ");
							    			if(i!=strs.length-1) {
							    				strbuf.append(" or ");
							    			}
							    			
							    			i++;
							    		}
							    		coreStr=strbuf.toString();
							    	}else {
							    		coreStr="LOCATE('"+str+"',pro.detail)!=0 ";
							    	}
							    	String a=" join progress pro on pro.causeId= p.id and pro.pointValue=14 and pro.pointName='审理室处理结果' and ("+coreStr+")";
									suf.append(a);
		        		    			 if(!msgs[1].equals("NO")) {
				        		    	     suf.append(" and DATE_FORMAT(pu.cfTime,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"'  ");
				        		         }
		        		    			 if(!msgs[2].equals("NO")) {
		        		    				 suf.append(" and DATE_FORMAT(pu.cfTime,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
		        		    			 }
							    }
								
								 continue;
						
						 }
						 //审查室处理
						 if(f.getName().equals("sc")) {
							    String[]msgs=str.split("_");
								String coreString="";
								if(str.indexOf("1")>=0) {
									     suf.append("  JOIN progress pro on pro.causeId= p.id and if(rd.partyMember=1,LOCATE('纪委立案时间',pro.detail)!=0,LOCATE('监委立案时间',pro.detail)!=0) ");
		        		    			 if(!msgs[1].equals("NO")) {
				        		    	     suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
				        		         }
		        		    			 if(!msgs[2].equals("NO")) {
		        		    				 suf.append(" and DATE_FORMAT(pro.timeForday,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
		        		    			 }
		        		    		 continue;
								}
								if(str.indexOf("2")>=0) {
									coreString=	"予以了结时间";							
								}
								if(str.indexOf("3")>=0) {
									coreString=	"谈话提醒时间";							
								}
								if(str.indexOf("4")>=0) {
									coreString=	"暂存待查时间";							
								}
								if(str.indexOf("5")>=0) {
									coreString=	"移送有关党组织处理时间";							
								}
								if(str.indexOf("6")>=0) {
									coreString=	"诫勉谈话时间";							
								}
								
								if(!coreString.equals("")) {
									String a=" join (select p.pointValue as poi,p.causeId as cid,p.time as time,p.detail as detail,p.timeForday as timeForday from  progress p join (select max(pro.time) as maxtime,pro.causeId as cid  from progress pro where pro.pointValue ='26' and pro.detail='"+coreString+"' group by pro.causeId) m on "+
											 " p.causeId=m.cid and p.time=m.maxtime )pp on p.id=pp.cid ";  
									     suf.append(a);
		        		    			 if(!msgs[1].equals("NO")) {
				        		    	     suf.append(" and DATE_FORMAT(pp.timeForday,'%Y-%m-%d')>='"+msgs[1].substring(0, 10)+"' ");
				        		         }
		        		    			 if(!msgs[2].equals("NO")) {
		        		    				 suf.append(" and DATE_FORMAT(pp.timeForday,'%Y-%m-%d')<='"+msgs[2].substring(0, 10)+"' ");
		        		    			 }
								}
								 continue;
						 }
						 
						 
						 
						 Pattern pattern = Pattern.compile("[0-9]*");
				          Matcher isNum = pattern.matcher(str);
				          if(!isNum.matches()) {
				        	  suf.append("and rd."+f.getName()+" like '%"+f.get(reflectedPerson)+"%'  ");
				        	  
				          }else {
				        	  
				        	  suf.append("and rd."+f.getName()+"='"+f.get(reflectedPerson)+"'  ");
				          }
					}
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			if(reflectingPerson!=null){
				
			
		 Class c3=reflectingPerson.getClass();
		 Field[] fields3 = c3.getDeclaredFields();
		 boolean mainSQl=true;
		 for(Field f:fields3){
			 f.setAccessible(true);
			 try {
				 if(f.get(reflectingPerson)!=null&&!f.get(reflectingPerson).toString().equals("")){
					 String str=(String)f.get(reflectingPerson);
					  Pattern pattern = Pattern.compile("[0-9]*");
			          Matcher isNum = pattern.matcher(str);
			          if(isNum.matches()) {
			        	  if(mainSQl) {
			        		  suf.append(" JOIN reflectingperson ring ON ring.id = p.reflectingPersonId  and p.reflectingPersonId is not null  ");
			        		  mainSQl=false;
			        	  }
			        	  suf.append("and  ring."+f.getName()+"='"+f.get(reflectingPerson)+"' ");
			          }else {
			        	  if(mainSQl) {
			        		  suf.append(" JOIN reflectingperson ring ON ring.id = p.reflectingPersonId  and p.reflectingPersonId is not null  ");
			        		  mainSQl=false;
			        	  }
			        	  suf.append("and ring."+f.getName()+" like '%"+f.get(reflectingPerson)+"%'  ");
			          }
				 }
			 } catch (IllegalArgumentException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 } catch (IllegalAccessException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
	     }
		 if(report!=null) {
			 Class c3=report.getClass();
			 Field[] fields3 = c3.getDeclaredFields();
			 for(Field f:fields3){
				 f.setAccessible(true);
				 try {
					 if(f.get(report)!=null&&!f.get(report).toString().equals("")){
						 String str=(String)f.get(report);
						  Pattern pattern = Pattern.compile("[0-9]*");
				          Matcher isNum = pattern.matcher(str);
				          if(isNum.matches()) {
				        	  
				        	  suf.append("and  r."+f.getName()+"='"+f.get(report)+"' ");
				          }else {
				        	  suf.append("and r."+f.getName()+" like '%"+f.get(report)+"%'  ");
				          }
					 }
				 } catch (IllegalArgumentException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 } catch (IllegalAccessException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
			 }
		 }
		if(startDate!=null&&!startDate.equals("")) {
			suf.append("  and p.receiveDate >='"+ startDate+"'");
		}
		if(endDate!=null&&!endDate.equals("")) {
			suf.append("  and p.receiveDate <='"+endDate+"'");
		}
		//在重点督办界面显示重点督办数据
		System.out.println(problemClues.getIsImport()==null);
		if(zddb!=null&&zddb.equals("YES")&&problemClues!=null&&problemClues.getIsImport()==null&&problemClues.getIsResult()==null) {
			 suf.append("and p.isImport='1' or p.isResult='1'  ");
		}
		
		   //判断是否是案管室的
		   ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		   List<Long> roleList = user.roleList;
		   for(Long l:roleList) {
			 if(l==9l) {
				 condition.put("isAG", "YES");
				 break;
			 }
		   }
		
		condition.put("problemClues", problemClues);
		condition.put("sql", suf.toString());
		//页面加载用
		condition.put("page", page);
		condition.put("rows", rows);
		condition.put("sort", sort);
		condition.put("order", order);
		return condition;
	}
	@Override
	public PageInfo xqService(Map<String, Object> condition) {
		// 需要的数据
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> xq = pageMapper.xqData(pageInfo);
		pageInfo.setRows(xq);
		//查询总数
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.xqData(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
		
	}

	@Override
	public PageInfo temporaryService(Map<String, Object> condition) {
		PageInfo pageInfo = new PageInfo((Integer) condition.get("page"), (Integer) condition.get("rows"),
				(String) condition.get("sort"), (String) condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> xq = pageMapper.temporaryData(pageInfo);
		pageInfo.setRows(xq);
		
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.temporaryData(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	@Override
	public PageInfo report(Map<String, Object> condition) {
		PageInfo pageInfo = new PageInfo((Integer) condition.get("page"), (Integer) condition.get("rows"),
				(String) condition.get("sort"), (String) condition.get("order"));
		condition.put("organId", getCurrentOrganId());
		pageInfo.setCondition(condition);
		List<ProblemClues> xq = pageMapper.report(pageInfo);
		pageInfo.setRows(xq);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.report(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	@Override
	public PageInfo returnService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				condition.put("organId", getCurrentOrganId());
				pageInfo.setCondition(condition);
				List<ProblemClues> verifyPageData = pageMapper.returnServiceM(pageInfo);
				pageInfo.setRows(verifyPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.returnServiceM(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	//本部门退回案件
	@Override
	public PageInfo rtingList(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		condition.put("organId", getCurrentOrganId());
		pageInfo.setCondition(condition);
		List<ProblemClues> verifyPageData = pageMapper.rtingList(pageInfo);
		pageInfo.setRows(verifyPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.rtingList(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	@Override
	public PageInfo tj_detail(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> verifyPageData = pageMapper.tj_detail(pageInfo);
		pageInfo.setRows(verifyPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.tj_detail(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	//信访室新案件
	@Override
	public PageInfo xfClueServices(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> verifyPageData = pageMapper.xfClues(pageInfo);
		pageInfo.setRows(verifyPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.xfClues(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	//已有重复件
	@Override
	public PageInfo repeatService(Map<String, Object> condition) {
		// TODO Auto-generated method stub
				PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
				pageInfo.setCondition(condition);
				List<ProblemClues> verifyPageData = pageMapper.repeatList(pageInfo);
				pageInfo.setRows(verifyPageData);
				condition.put("count", "count");
				PageInfo count = new PageInfo();
				count.setCondition(condition);
				List<ProblemClues> countr = pageMapper.repeatList(pageInfo);
				pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
				return pageInfo;
	}
	//所有重复件
	@Override
	public PageInfo repeatAllService(Map<String, Object> condition) {
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> verifyPageData = pageMapper.repeatAllList(pageInfo);
		pageInfo.setRows(verifyPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.repeatAllList(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	@Override
	public PageInfo tonganList(Map<String, Object> condition) {
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		pageInfo.setCondition(condition);
		List<ProblemClues> verifyPageData = pageMapper.tonganList(pageInfo);
		pageInfo.setRows(verifyPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.tonganList(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
	@Override
	public PageInfo del_clues(Map<String, Object> condition) {
		PageInfo pageInfo = new PageInfo((Integer)condition.get("page"), (Integer)condition.get("rows"), (String)condition.get("sort"), (String)condition.get("order"));
		condition.put("organId", getCurrentOrganId());
		pageInfo.setCondition(condition);
		List<ProblemClues> verifyPageData = pageMapper.del_clues(pageInfo);
		pageInfo.setRows(verifyPageData);
		condition.put("count", "count");
		PageInfo count = new PageInfo();
		count.setCondition(condition);
		List<ProblemClues> countr = pageMapper.del_clues(pageInfo);
		pageInfo.setTotal(Integer.valueOf(countr.get(0).getWhereFrom()));
		return pageInfo;
	}
    
    
}
