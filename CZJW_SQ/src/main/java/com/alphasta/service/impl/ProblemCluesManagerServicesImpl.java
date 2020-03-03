package com.alphasta.service.impl;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alphasta.commons.annotation.ExcelFild;
import com.alphasta.commons.base.SqlService;
import com.alphasta.commons.exception.ServiceException;
import com.alphasta.commons.result.ExcelException;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.statisticsUtils.SqlMaker10;
import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.ExcelInfo;
import com.alphasta.commons.utils.ExcelUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.GsonUtil;
import com.alphasta.commons.utils.HttpClientUtil;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.en.Back_State;
import com.alphasta.en.Stage;
import com.alphasta.en.State;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.mapper.BackMapper;
import com.alphasta.mapper.Delete_CluesMapper;
import com.alphasta.mapper.DictMapper;
import com.alphasta.mapper.ExcelMapper;
import com.alphasta.mapper.GroupMapper;
import com.alphasta.mapper.LegalActMapper;
import com.alphasta.mapper.LienMapper;
import com.alphasta.mapper.MakedMapper;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.PunishmentMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.mapper.ReflectedUnitMapper;
import com.alphasta.mapper.ReflectingPersonMapper;
import com.alphasta.mapper.ReportMapper;
import com.alphasta.mapper.StaticticsMapper;
import com.alphasta.mapper.ZyViolationMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.Back;
import com.alphasta.model.BootstapTree;
import com.alphasta.model.Delete_Clues;
import com.alphasta.model.Dict;
import com.alphasta.model.ExcelPojo;
import com.alphasta.model.Group;
import com.alphasta.model.LegalAct;
import com.alphasta.model.Lien;
import com.alphasta.model.ListParam;
import com.alphasta.model.Maked;
import com.alphasta.model.MapToBean;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.Report;
import com.alphasta.model.TransModel;
import com.alphasta.model.User;
import com.alphasta.model.ZyViolation;
import com.alphasta.service.AgsService;
import com.alphasta.service.ProblemCluesManagerServices;
import com.alphasta.service.RoleService;
import com.alphasta.service.UserService;
@Service
public class ProblemCluesManagerServicesImpl implements ProblemCluesManagerServices{
	private static Logger LOGGER = LoggerFactory.getLogger(ProblemCluesManagerServicesImpl.class);
	@Autowired
	private ProblemCluesMapper problemCluesMapper;
	@Autowired
	private SqlService sqlService;
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private ProgressMapper progressMapper;
	@Autowired
	private LienMapper lienMapper;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ReflectedPersonMapper reflectedPersonMapper;
	@Autowired
	private ReflectedUnitMapper reflectedUnitMapper;
	@Autowired
	private ReflectingPersonMapper reflectingPersonMapper;
	@Autowired
	private ExcelMapper excelMapper;
	@Autowired
	UserService  userService;
	@Autowired
	OrganizationMapper  organizationMapper;
	@Autowired
	AccessoriesMapper  accessoriesMapper;
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private LegalActMapper legalActMapper;
	@Autowired
	private ZyViolationMapper zyViolationMapper;
	@Autowired
	private BackMapper       backMapper;
	@Autowired
	private AgsService agsService;
	@Autowired
	private Delete_CluesMapper delete_CluesMapper;
	public static SimpleDateFormat sdf_yyyyMMdd=new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat sdf_yyyy_MM_dd=new SimpleDateFormat("yyyy-MM-dd");
	private String currentOrganId;
	/**
     *添加案件线索 重复件处理
     */
	@Override
	public void isDoubleXin(ProblemClues problemClues) {
		  //执行存储过程	
		 SqlSessionFactory SqlSessionFactory= sqlService.getSqlSessionFactory();
				         
		 org.apache.ibatis.session.SqlSession openSession = SqlSessionFactory.openSession();
				         String statement = "com.alphasta.mapper.ProblemCluesMapper.getUserCount";//映射sql的标识字符串
				         System.out.println(problemClues.getId());
				         Map<String, Object> parameterMap = new HashMap<String,Object>();
				         parameterMap.put("bei_fan_ying_ren", problemClues.getReflectedPerson().getReflectedName());
				         parameterMap.put("zx_special", problemClues.getSpecial());
				         //parameterMap.put("bei_fan_ying_renzj", problemClues.getBeReflectRank());
				         parameterMap.put("ida", problemClues.getId());
				         openSession.selectOne(statement, parameterMap);
				         openSession.close();
	}
	
	@Override
	public void insertProblemClues(ProblemClues problemClues) {
		problemCluesMapper.insertProblemClues(problemClues);
	}
    
	@Override
	public ProblemClues findProblemCluesById(String id) {
		ProblemClues problemClues = problemCluesMapper.findProblemCluesById(id);
		return problemClues;
	}
	
	@Override
	public int update(ProblemClues problemClues) {
		int result = problemCluesMapper.update(problemClues);
		if (result != 1) {
			LOGGER.warn("更新案件线索信息失败");
			throw new ServiceException("更新失败");
		}
		return result;
	}


	@Override
	public void deleteByids(Delete_Clues delete_Clues) {
		//problemCluesMapper.deleteByids(str);
		String[] str = delete_Clues.getCauseId().split(",");
		ProblemClues problemClues=new ProblemClues();
		for(String s:str) {
			//part1:保存删除原因
			delete_Clues.setCauseId(s);
			delete_Clues.setId(GetIdUtil.getId());
			delete_Clues.setOrganId(getCurrentOrganId());
			Delete_Clues reason = delete_CluesMapper.getReason(delete_Clues);
			if(reason==null) {
				delete_CluesMapper.addReason(delete_Clues);
			}
			//part2:修改线索状态
			problemClues.setId(s);
			problemClues.setIsDel("YES");
			problemCluesMapper.update(problemClues);
		}
		
	}
	/**
	 * 线索导入 使用Excel文件添加线索
	 */
	@Override
	public   Object imporByExcel(MultipartFile file,HttpServletRequest req) throws ExcelException {
		Map<String,Object>result_map=new HashMap<String,Object>();
		StringBuffer buf_error=new StringBuffer();
		//part1:获取当前用户
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	    User currentUser = userService.findUserById(user.id);
		
	    
	    //part2:定义特殊字段
		                 //多选和单选的列名
		                //线索表多选
		String options="  clues,problemLand,isSupervise,documentType,eightSpirit,partyMember,isImport,isResult,belongToId,isPMSupervisoryObject,iSupervision"
				       + ",fields,special,fyzvviolation,type,zx,political,civilServant,departmenType,natureOfenterprise,classOfenterprise,post,posType,topDiscipline,cadre,partyRepresent,np,dwMember,jwMember,supervision,pMSupervisoryObject,sex"
				       //被反映人选择项
				       + ",rank";
		        //日期字段//需要转化
				String yyyy_MM_dd="receiveDate,resultTime,superviseTestTime";
				//只精确到月的
				String yyyy_MM="birthday,worktime,intime";
		//案件编号前缀
		currentOrganId = getCurrentOrganId();
		String suffix="";
		if(currentOrganId.equals(Param.agsId)){
			suffix=Param.ags_suffix;
		}else if(currentOrganId.equals(Param.gbjdsId)){
			suffix=Param.gbjds_suffix;
		}else if(currentOrganId.equals(Param.dfsId)){
			suffix=Param.dfs_suffix;
		}else if(currentOrganId.equals(Param.xfsId)){
			suffix=Param.xfs_suffix;
		}
		int insertNum=0;
		try {
			//part3:读取Excel表格数据
			ExcelInfo excelInfo = ExcelUtils.readExcel(file.getInputStream(),file.getOriginalFilename());
			buf_error.append(excelInfo.getMsg()+"|");
			//查出数据
		    //将数据转换成实体类
			//part4:根据ExcelPojo注解获得具体属性名和Excel中标题名的对应
			Map<String,String>fieldName_AnnotationName=new HashMap<String,String>();
            Field[] declaredFields = ExcelPojo.class.getDeclaredFields();
            for(Field  f:declaredFields) {
            	ExcelFild annotation = f.getAnnotation(ExcelFild.class);
            	System.out.println(f.getName()+"===="+annotation.title());
            	fieldName_AnnotationName.put(annotation.title(),f.getName());
            }
			Map<String, List<Map<Integer, String>>> sheets = excelInfo.getSheets();
			//part5:列编号和字段名的关联
			Map<Integer,String>num_filedName=new HashMap<Integer,String>();
			//所有选项
			ServletContext servletContext = req.getServletContext();
			//遍历Map
			Set<String> keySet = sheets.keySet();
			for(String key:keySet) {
				//哪个sheet
				System.out.println(key);
				//这个sheet的数据
				System.out.println(sheets.get(key));
				//获取所有行
				List<Map<Integer, String>> list = sheets.get(key);
				//遍历所有行
				int index=0;
				for(Map<Integer, String> m:list) {
					//ProbleClues表的数据
					ProblemClues p=new ProblemClues();
					//
					ReflectedPerson rd=new ReflectedPerson();
					//
					ReflectingPerson ring=new ReflectingPerson();
					//每一行的数据m
					//第一行是标题
					if(index==0) {
						//处理Excel标题名和字段名的关系//并将列编号和字段名关联起来
						//遍历m
						Set<Integer> keySet2 = m.keySet();
						for(Integer num:keySet2) {
							//num列编号
						    String title = m.get(num);
						    String filedName = fieldName_AnnotationName.get(title);
						    if(filedName!=null) {
						    	num_filedName.put(num, filedName);
						    }
						}
					}else {
					   //加载数据
						Set<Integer> keySet3 = m.keySet();
						for(Integer num:keySet3) {
							//生成sql
							//获得字段名
							String filedName = num_filedName.get(num);
							//当前的值
							if(filedName!=null) {
								//获取值
								String value = m.get(num);
								//单选
						    	if(value!=null&&value!=""&&(value.indexOf(",")==-1||value.indexOf("，")==-1)&&options.indexOf(filedName)!=-1) {
						    		System.out.println(filedName);
						    		//判断是否是是否多选
						    		System.out.println(filedName+"==========================="+value);
						    		if(value.equals("是")||value.equals("男")) {
						    			value="1";
						    			String whichTable = SqlMaker10.whichTable(filedName);
					    				if(whichTable.equals("p")) {
					    					Field declaredField = p.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(p, value);
					    					continue;
					    				}
					    				if(whichTable.equals("rd")) {
					    					Field declaredField = rd.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(rd, value);
					    					continue;
					    				}
					    				if(whichTable.equals("ring")) {
					    					Field declaredField = ring.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(ring, value);
					    					continue;
					    				}
						    			
						    		}
						    		
						    		if(value.equals("否")||value.equals("女")) {
						    			value="2";
						    			String whichTable = SqlMaker10.whichTable(filedName);
					    				if(whichTable.equals("p")) {
					    					Field declaredField = p.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(p, value);
					    					continue;
					    				}
					    				if(whichTable.equals("rd")) {
					    					Field declaredField = rd.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(rd, value);
					    					continue;
					    				}
					    				if(whichTable.equals("ring")) {
					    					Field declaredField = ring.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(ring, value);
					    					continue;
					    				}
						    			
						    		}
						    		int i=0;
						    		//获得选项
						    		List<Dict> list2 = (List<Dict>)servletContext.getAttribute(filedName);
						    		System.out.println(filedName);
						    		if(list2!=null) {
						    			
						    		for(Dict d:list2) {
						    			if(value!=null&&value!=""&&value.trim().equals(d.getName())){
						    				//判断是那张表的
						    				String whichTable = SqlMaker10.whichTable(filedName);
						    				if(whichTable.equals("p")) {
						    					Field declaredField = p.getClass().getDeclaredField(filedName);
						    					declaredField.setAccessible(true);
						    					declaredField.set(p, (String)d.getValue());
						    					continue;
						    				}
						    				if(whichTable.equals("rd")) {
						    					Field declaredField = rd.getClass().getDeclaredField(filedName);
						    					declaredField.setAccessible(true);
						    					if(filedName.equals("fyzvviolation")){
						    						declaredField.set(rd, (String)d.getDictId());
						    					}else{
						    						declaredField.set(rd, (String)d.getValue());
						    					}
						    					
						    					continue;
						    				}
						    				if(whichTable.equals("ring")) {
						    					Field declaredField = ring.getClass().getDeclaredField(filedName);
						    					declaredField.setAccessible(true);
						    					declaredField.set(ring, (String)d.getValue());
						    					continue;
						    				}
						    				
						    				
						    			}
						    			}
						    		}
						    	}
						    	//多选
                                if(value!=null&&value!=""&&(value.indexOf(",")!=-1||value.indexOf("，")!=-1)&&options.indexOf(filedName)!=-1) {
                                	//因为在字典表里zyViolation和fyZyViolation是相同的
                                	int i=0;
                                	StringBuffer suf=new StringBuffer();
						    		//分割字符串
                                	String[] split=null;
                                	if(value.indexOf(",")!=-1) {
                                		 split= value.split(",");
                                	}
                                	if(value.indexOf("，")!=-1) {
                                		 split=value.split("，");
                                	}
                                	int z=0;
                                	for(String str:split) {
                                		List<Dict> list2 = (List<Dict>)servletContext.getAttribute(filedName);
    						    		for(Dict d:list2) {
    						    			if(str!=null&&str.equals(d.getName())){
    						    				if(z!=split.length-1) {
    						    					if(filedName.equals("fyzvviolation")){
    						    						suf.append(d.getDictId()+",");
    						    					}else{
    						    						suf.append(d.getValue()+",");
    						    					}
    						    					
    						    				}else {
    						    					if(filedName.equals("fyzvviolation")){
    						    						suf.append(d.getDictId());
    						    					}else{
    						    						suf.append(d.getValue());
    						    					}
    						    					
    						    				}
    						    			}
    						    			
    						    		}
    						    		z++;
                                	}
                                	
					    				//判断是那张表的
                                	    //
					    				String whichTable = SqlMaker10.whichTable(filedName);
					    				if(whichTable.equals("p")) {
					    					Field declaredField = p.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(p, suf.toString());
					    					continue;
					    				}
					    				if(whichTable.equals("rd")) {
					    					Field declaredField = rd.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(rd, suf.toString());
					    					continue;
					    				}
					    				if(whichTable.equals("ring")) {
					    					Field declaredField = ring.getClass().getDeclaredField(filedName);
					    					declaredField.setAccessible(true);
					    					declaredField.set(ring, suf.toString());
					    					continue;
					    				}
					    				
						    	}
						    	//非选择
                                if(options.indexOf(filedName)==-1) {
                                	//判断是那张表的
				    				String whichTable = SqlMaker10.whichTable(filedName);
				    				if(whichTable.equals("p")) {
				    					Field declaredField = p.getClass().getDeclaredField(filedName);
				    					declaredField.setAccessible(true);
				    					//日期转化
				    					if(yyyy_MM_dd.indexOf(filedName)!=-1&&value!=null&&!value.equals("")) {
				    						value=SqlMaker10.zhTime((String)value);
				    					}
				    					if(yyyy_MM.indexOf(filedName)!=-1&&value!=null&&!value.equals("")) {
				    						value=SqlMaker10.zhTime((String)value).substring(0, 7);
				    					}
				    					if(value!=null) {
				    						value=value.trim();
				    					}
				    					declaredField.set(p,(String)value);
				    					continue;
				    				}
				    				if(whichTable.equals("rd")) {
				    					Field declaredField = rd.getClass().getDeclaredField(filedName);
				    					declaredField.setAccessible(true);
				    					//日期转化
				    					if(yyyy_MM_dd.indexOf(filedName)!=-1&&value!=null&&!value.equals("")) {
				    						value=SqlMaker10.zhTime((String)value);
				    					}
				    					if(yyyy_MM.indexOf(filedName)!=-1&&value!=null&&!value.equals("")) {
				    						value=SqlMaker10.zhTime((String)value).substring(0, 7);
				    					}
				    					if(value!=null) {
				    						value=value.trim();
				    					}
				    					declaredField.set(rd, (String)value);
				    					continue;
				    				}
				    				if(whichTable.equals("ring")) {
				    					Field declaredField = ring.getClass().getDeclaredField(filedName);
				    					declaredField.setAccessible(true);
				    					//日期转化
				    					if(yyyy_MM_dd.indexOf(filedName)!=-1&&value!=null&&!value.equals("")) {
				    						value=SqlMaker10.zhTime((String)value);
				    						
				    					}
				    					if(yyyy_MM.indexOf(filedName)!=-1&&value!=null&&!value.equals("")) {
				    						value=SqlMaker10.zhTime((String)value).substring(0, 7);
				    					}
				    					if(value!=null) {
				    						value=value.trim();
				    					}
				    					declaredField.set(ring, (String)value);
				    					continue;
				    				}
                                }
							}
							
							
							
						}
					}
					
					if(index!=0) {
						//插入被反映人
						String id = GetIdUtil.getId();
						rd.setId(id);
						reflectedPersonMapper.addReflectedPerson(rd);
						//插入反映人
						String id2 = GetIdUtil.getId();
						ring.setId(id2);
						reflectingPersonMapper.addReflectingPerson(ring);
						//插入线索
						ReflectedPerson reflectedPerson=new ReflectedPerson();
						reflectedPerson.setId(id);
						p.setReflectedPerson(reflectedPerson);
						ReflectingPerson reflectingPerson=new ReflectingPerson();
						
						reflectingPerson.setId(id2);
						p.setReflectingPerson(reflectingPerson);
						//获取案件编号
						String maxNum =agsService.findMaxNum(suffix);
						p.setCluesNum(maxNum);
						p.setWhereFrom("putong");
						String id3 = GetIdUtil.getId();
						p.setId(id3);
						p.setOrganId(String.valueOf(currentUser.getOrganizationId()));
						p.setIsGet("1");
						p.setState("CLUESTJ"+String.valueOf(currentUser.getOrganizationId()));
						//fromId
						if(currentOrganId.equals(Param.agsId)) {
							p.setFromId(Param.agsFrom);
						}else if(Param.jdsIds.indexOf(","+currentOrganId+",")>=0) {
							p.setFromId(Param.jdsFrom);
						}else if(currentOrganId.equals(Param.xfsId)) {
							p.setFromId(Param.xfsFrom);
						}else if(currentOrganId.equals(Param.gbjdsId)) {
							p.setFromId(Param.gbjdsFrom);
						}
						problemCluesMapper.insertProblemClues(p);
						//处理重复件
						isDoubleXin(p);
						//记录进度
						Progress progress = new Progress();
						String id4 = GetIdUtil.getId();
						progress.setId(id4);
						progress.setCauseId(p.getId());
						progress.setPointName(Param.agssl_name);
						progress.setPointValue(Param.agssl_value);
						progress.setDetail("excel导入");
						SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
						progress.setTime(sm.format(new Date()));
						progressMapper.addProgress(progress);
						insertNum+=1;
					}
					index++;
				}
			}
		}catch(ExcelException e){
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			buf_error.append(e.toString());
		}
		    result_map.put("error0", buf_error.toString());
		    result_map.put("msg0",insertNum+"条");
			return result_map;
	    }
	public String buttonHas() {
		  // TODO Auto-generated method stub
		  ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		  List<Long> roleList = user.roleList;
		  String result="";
		  for(Long role:roleList) {
			 List<Long> rids = roleService.findResourceIdListByRoleId(role);
			 for(Long rid:rids) {
				 //处置建议到期
                 if(rid==344) {
                	 result= "czjy";
                	 break;
                	 
					
				 }
                 //立案审查到期
                 if(rid==345) {
                	 result= "lasc";
                	 break;
				 }
                 //案件审理到期
				 if(rid==346) {
					 result= "ajsl";
					 break;
				 }
				
				
			 }
		  }
		return result;
	}
	 //更新线索和人员信息

		@Override
		public int updateMainService(ProblemClues problemClues) {
			// TODO Auto-generated method stub
			if(problemClues!=null) {
			//修改被反映人
			//修改反映人
	        if(problemClues.getReflectedPerson()!=null) {
	        	
	        	reflectedPersonMapper.updateReflectedPerson(problemClues.getReflectedPerson());
	        }
	        if(problemClues.getReflectingPerson()!=null) {
	        	//part1:查询反映人 没有则增加一条
	        	if(problemClues.getReflectingPerson()!=null&&StringUtils.isNotEmpty(problemClues.getReflectingPerson().getId())) {
	        		reflectingPersonMapper.updateReflectingPerson(problemClues.getReflectingPerson());
	        	}else {
	        		ReflectingPerson ref = problemClues.getReflectingPerson();
	        		ref.setId(GetIdUtil.getId());
	        		reflectingPersonMapper.addReflectingPerson(ref);
	        		problemClues.setReflectingPerson(ref);
	        	}
	        	
	        }
	        if(problemClues.getReflectedUnit()!=null) {
	        	
	        	reflectedUnitMapper.updateReflectedUnit(problemClues.getReflectedUnit());
	        }
	        problemCluesMapper.update(problemClues);
			}
			return 0;
		}
        //-------------------------
		@Override
		public List<ExcelPojo> excelData(String sql) {
			// TODO Auto-generated method stub
			List<ExcelPojo> data=excelMapper.excelData(sql);
			return data;
		}

		@Override
		public String whichPage(String pageName,ProblemClues problemClues) {
			// TODO Auto-generated method stub
			//获得当前用户的角色 什么样的角色打开什么界面 在结合将要打开这个界面的列表页是哪个
			  String result="";
			if(pageName!=null&&(pageName.equals("working")||pageName.equals("overing")||pageName.equals("library")||pageName.equals("AGSZAIBAN")||pageName.equals("xq")||pageName.equals("report"))) {
				result="/probleClues/cluesDetail";
			}else if(pageName!=null&&"initial".equals(pageName)){
				  //案管室初核界面
				  result="/probleClues/fbClues";
			}else {
			
			   ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			  List<Long> roleList = user.roleList;
			
			  for(Long role:roleList) {
				  //案管室
				  if(role==9||role==15) {						
						//案管室和党风室线索界面
					  
				      result="/part/saveClues";
				  }
				  if(role==10) {
					  result="/probleClues/jds";
				  }
				  if(role==11) {
					  if("9".equals(problemClues.getFinalState())) {
						  result="/probleClues/sc";
					  }else if("25".equals(problemClues.getFinalState())){
						  result="/probleClues/lasc";
					  }else if("29".equals(problemClues.getFinalState())){//(新添)
						  result="/probleClues/lasc";
					  }
					 
				  }
				  if(role==12){
					  result="/probleClues/sl";
				  }
			  }
			}  
			return result;
		}
	
		@Override
		public ProblemClues findProblemCluesByCluesNum(String cluesNum) {
			ProblemClues problemClues = problemCluesMapper.findProblemCluesByCluesNum(cluesNum);
			return problemClues;
		}

		@Override
		public List<ProblemClues> findProblemCluesRepeat(ProblemClues problemClues2) {
			return problemCluesMapper.findProblemCluesRepeat(problemClues2);
		}
		@Override
		public List<BootstapTree> GetExpandJson(String id) {
			// TODO Auto-generated method stub
			//查找下一级的
			Dict dict=new Dict();
			dict.setDictPid(id);
			List<Dict> findByDictPid = dictMapper.findDictByDictPid(dict);
			List<BootstapTree>result=new ArrayList<BootstapTree>();
			for(Dict d:findByDictPid) {
				BootstapTree b=new BootstapTree();
				b.setId(d.getDictId());
				b.setText(d.getName());
				b.setPid(d.getDictPid());
			    if(d.getState().equals("open")) {
			    	b.setNodes(null);
			    }else {
			    	List<BootstapTree>a=new ArrayList<BootstapTree>(0);
			    	b.setNodes(a);
			    }
			    result.add(b);
			}
			
			return result;
		}
		
		/**案件签收//如果是党风室//签收后要报备到党风室//如果是监督室自行发现的//签收后要报备到案管室**/
		@Override
		public boolean updateProblemCluesService(ProblemClues problemClues) {
			//如果是审理室签收  需要将审查时间改成审理到期时间
			ProblemClues findProblemCluesById = problemCluesMapper.findProblemCluesById(problemClues.getId());
			//part1:设置isGEt
			problemClues.setIsGet("1");
			//fromId
			problemClues.setFromId(findProblemCluesById.getFromId());
			//organId
			problemClues.setOrganId(findProblemCluesById.getOrganId());
			//part2:审里室签收 设置审理到期时间
			if(findProblemCluesById.getOrganId().equals(Param.slsId)) {
				//案件审理需要保存案件审理到期期限
				if(problemClues.getResultTime()!=null) {
					problemClues.setExpireTime(Param.ajslName+"_"+problemClues.getResultTime());
				}else {
					problemClues.setExpireTime(Param.ajslName+"_"+DateUtil.getDateFromNow(new Date(), Integer.valueOf(Param.ajslTime)));
				}
			}
			//part3:监督室转到案管室案件在签收后需要去掉JDSZL
            if(findProblemCluesById!=null&&findProblemCluesById.getState()!=null&&findProblemCluesById.getState().endsWith("JDSZL")) {
            	problemClues.setState("CLUESTJ"+Param.agsId);
            }
            //part4:如果案件来自党风室 需要在党风室报备
            if(findProblemCluesById.getFromId()!=null&&findProblemCluesById.getFromId().equals(Param.dfsId)) {
            	   Report report=new Report(GetIdUtil.getId(),Param.dfsId,problemClues.getId(),getCurrentOrganId());
				   reportMapper.addReport(report);
            }
			int update = problemCluesMapper.update(problemClues);
			return true;
		}
		@Override
		public String laAddLegalActService(List<LegalAct> legalAct,String reflectedPersonId) {
			// TODO Auto-generated method stub
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
			String cfTime = tempDate.format(new java.util.Date()); 
			String id=GetIdUtil.getId();
			legalAct.get(0).setId(id);
			legalAct.get(0).setReflectedId(reflectedPersonId);
			legalAct.get(0).setLawTime(cfTime);
			legalActMapper.addLegalAct(legalAct.get(0));	
			return "0";
		}
		@Override
		public String laAddZyViolationService(ZyViolation zyViolation,String reflectedPersonId) {
			// TODO Auto-generated method stub
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
			String cfTime = tempDate.format(new java.util.Date()); 
			String id=GetIdUtil.getId();
			zyViolation.setId(id);
			zyViolation.setReflectedId(reflectedPersonId);
			zyViolation.setZyTime(cfTime);
			zyViolationMapper.addZyViolation(zyViolation);
			return "0";
		}
		
		public User getCurrentUser() {
			 ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		     User currentUser = userService.findUserById(user.id);
		     return currentUser;
		}
		
		
		@Override
		public String getCurrentOrganId() {
			 ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		     User currentUser = userService.findUserById(user.id);
			return currentUser.getOrganizationId().toString();
		}

		@Override
		public boolean has_reapeat(ReflectedPerson reflectedPerson) {
			// TODO Auto-generated method stub
			List<ReflectedPerson> repeat = reflectedPersonMapper.has_repeat(reflectedPerson);
			if(repeat!=null&&!repeat.isEmpty()) {
				return true;
			}
			return false;
		}

		@Override
		public List<ProblemClues> find_repeat(ReflectedPerson reflectedPerson) {
			// TODO Auto-generated method stub
			List<ReflectedPerson> repeat = reflectedPersonMapper.has_repeat(reflectedPerson);
			List<ProblemClues>result=new ArrayList<ProblemClues>();
			for(ReflectedPerson r:repeat) {
				if(r.getId()!=null) {
					ProblemClues po = problemCluesMapper.find_clues_by_ref(r);
					if(po!=null) {
						result.add(po);
					}
					
				}
			}
			return result;
		}

		@Override
		public String get_organId_all() {
			StringBuffer suf=new StringBuffer();
			// TODO Auto-generated method stub
			/*User currentUser = getCurrentUser();
			Fg fg=new Fg();
			fg.setUserId(Integer.valueOf(String.valueOf(currentUser.getId())));
			fg.setId(-1);
			List<Fg> fg_list = fgMapper.select_fg(fg);
			
			for(Fg f:fg_list) {
				suf.append(f.getOrganId()+",");
			}*/
			String currentOrganId = getCurrentOrganId();
			suf.append(currentOrganId);
			return suf.toString();
		}

		@Override
		public boolean backService(Back back) {
			// TODO Auto-generated method stub
			boolean result=false;
			Progress p = new Progress();
			ProblemClues problemClues=problemCluesMapper.findProblemCluesById(back.getCluesId());
			if(back.getCluesId()!=null&&!back.getCluesId().equals("")) {
				p.setId(GetIdUtil.getId());
				p.setCauseId(back.getCluesId());
				//撤回 市里有时案件分发到县里错误  需要撤回
				if(State.CH==State.valueOf(back.getType())) {
					//下发到县区的撤回
					if(problemClues.getIsXf()!=null&&!"".equals(problemClues.getIsXf())&&problemClues.getIsXf().indexOf("D")>=0) {
						     //判断ip是否格式正确
							 String ip=problemClues.getIsXf().replace("D", "");
							 DataSourceContextHolder.setDbType(ip);
							 //删除线索
							 problemCluesMapper.deleteByids(problemClues.getId().split(","));
							 //删除被反映人
							 reflectedPersonMapper.delReflected(problemClues.getReflectedPerson().getId());
							 //删除反映人
							 if(problemClues.getReflectingPerson()!=null) {
								 reflectingPersonMapper.delReflecting(problemClues.getReflectingPerson().getId());
							 }
							
							 //数据源切换回来
							 DataSourceContextHolder.setDbType("master_dataSource");		
							 //修改本库的线索状态
							 //1.这条线索是案管室下发的
							 if(problemClues.getFromId().equals(Param.agsId)) {
								 problemClues.setFromId("0");
								 problemClues.setOrganId(getCurrentOrganId());
								 problemClues.setFinalState("0");
								 problemClues.setIsXf("XFCH");
								 problemClues.setState("CLUESLC11");
								 problemCluesMapper.update(problemClues);
							 }
							 //2.这条线索时监督室下发的
							 if(Param.jdsIds.indexOf(","+problemClues.getFromId()+",")>=0) {
								 problemClues.setFromId("-1");
								 String organId = getCurrentOrganId();
								 problemClues.setOrganId(organId);
								 problemClues.setFinalState("0");
								 problemClues.setIsXf("XFCH");
								 problemClues.setState("CLUESTJ"+organId);
								 problemCluesMapper.update(problemClues);
							 }
						
					}else {
						//本数据库内的撤回
						p.setPointName(Param.ch_name);
						p.setPointValue(Param.ch_value);
						p.setOrganId(getCurrentOrganId());
						SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
						String cfTime = tempDate.format(new java.util.Date()); 
						p.setTime(cfTime);
						Progress findLastProg = progressMapper.findLastProgress(back.getCluesId());
						p.setLastPoint(findLastProg.getId());
						progressMapper.addProgress(p);
						problemClues.setIsGet("1");
						problemClues.setOrganId(Param.organId);
						problemClues.setFinalState("31");
					}
					
				}
				//退回
				if(State.TH==State.valueOf(back.getType())) {
					//退回案件的逻辑过程  
					p.setId(GetIdUtil.getId());
					p.setCauseId(back.getCluesId());
					p.setPointName(Param.th_name);
					p.setPointValue(Param.th_value);
					p.setOrganId(Param.organId);
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
					String cfTime = tempDate.format(new java.util.Date()); 
					p.setTime(cfTime);
					Progress findLastProg = progressMapper.findLastProgress(back.getCluesId());
					p.setLastPoint(findLastProg.getId());
					progressMapper.addProgress(p);
					problemClues.setId(back.getCluesId());
					problemClues.setIsGet("1");
					problemClues.setFinalState("29");
					//要同时修改 organid和fromId
					//查询出所有线索
					//生成线索办理路径
					problemClues.setOrganId(problemClues.getFromId());
					/*if(findLastProg.getOrganId()!=null&&!"".equals(findLastProg.getOrganId())) {
						problemClues.setOrganId(findLastProg.getOrganId());
					}*/
					
				}
				//添加承办人
				problemClues.setCbr_now(getCurrentUser().getName());
				int problemCluesService = problemCluesMapper.update(problemClues);
				
				if(problemCluesService==1) {
					result=true;
				}
			}
			return result;
		}
		
		
		
		
		@Override
		public boolean back_please_service(Back back) {
			// TODO Auto-generated method stub
			//新增申请
			//保存撤回 退回信息
			back.setId(GetIdUtil.getId());
			back.setType(back.getType());
			back.setOrganId(Integer.parseInt(getCurrentOrganId()));
			back.setState(Back_State.SQ.toString());
			backMapper.add_back(back);
			//现在是两步合成一步做的
			backService(back);
			return true;
		}

		@Override
		public Back call_back_detail(Back back) {
			// TODO Auto-generated method stub
			Back select_back = null;
			if(StringUtils.isNotEmpty(back.getId())) {
				 select_back = backMapper.select_back_id(back.getId());
			}else {
				 select_back =backMapper.select_back(back).get(0);
			}
			
			return select_back;
		}

		@Override
		public Delete_Clues del_reason(Delete_Clues delete_Clues) {
			delete_Clues.setOrganId(getCurrentOrganId());
			Delete_Clues reason = delete_CluesMapper.getReason(delete_Clues);
			return reason;
			
		}
        
		
		
		
}
