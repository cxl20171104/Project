package com.alphasta.controller.work.ags;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.ExcelException;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.statisticsUtils.SqlMaker10;
import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.HssfUtil;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.en.Repeat_order;
import com.alphasta.mapper.DictMapper;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.ReflectedPersonMapper;
import com.alphasta.model.Delete_Clues;
import com.alphasta.model.Dict;
import com.alphasta.model.ExcelPojo;
import com.alphasta.model.ListParam;
import com.alphasta.model.Organization;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.TransModel;
import com.alphasta.service.AgsService;
import com.alphasta.service.ProblemCluesManagerServices;
@Controller
@RequestMapping("/ags")
public class AgsController extends BaseController{
	@Autowired
	private  AgsService agsService;
	@Autowired
	private static final String ONEXCLEDIT ="/probleClues/probleCluesOnExclEdit";
	@Autowired
	private  ProblemCluesManagerServices problemCluesManagerService;
	@Autowired
	private  DictMapper dictMapper;	
	@Autowired
	private ProgressMapper progressMapper;
	 @Autowired
     private OrganizationMapper organizationMapper;
	/**
	 * 添加或修改线索  被反映人  反应人
	 * @param problemClues
	 * @param proTime
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/clues_add")
	@ResponseBody
	public Object add(ProblemClues problemClues) throws ParseException {
		     problemClues = agsService.add_clues(problemClues,request);
		     Result result =new Result();
			 if(problemClues.getWhereFrom().equals("repeart")) {
					//如果存在重复问题  先将案件放到上下文中
					ServletContext servletContext = request.getServletContext();
					servletContext.setAttribute(getCurrentUserId()+"P", problemClues);
					result.setMsg("repeat");
					result.setObj(problemClues.getReflectedPerson().getReflectedName());
					result.setSuccess(true);
					return result;
			}else if(!StringUtils.isEmpty(problemClues.getId())) {
				  result.setSuccess(true);
				  result.setObj(problemClues.getId());
				  result.setMsg("线索添加成功！");
				  
				  return result;
			}
		  return renderError("添加失败");
	}
	/**
	 * 所有与将要添加案件被反映人重复的线索
	 * @param problemClues
	 * @return
	 */
	@RequestMapping(value = "/repeater_clues")
	@ResponseBody
	public ModelAndView repeater_clues(ReflectedPerson reflectedPerson) {
		//当前案件
		 ProblemClues problemClues=(ProblemClues)request.getServletContext().getAttribute(getCurrentUserId()+"P");
		List<ProblemClues> repeater_clues = problemCluesManagerService.find_repeat(reflectedPerson);
		ModelAndView mv=new ModelAndView("/probleClues/repeater");
		//重复案件列表
		mv.addObject("repeater_clues", repeater_clues);
		mv.addObject("problemClues",problemClues);
		return mv;
	}
	
	/**
	 * 添加重复件
	 * @param problemClues_before 之前库里就存在的线索
	 * @param order
	 * @return
	 */
	@RequestMapping("/add_repeat")
	@ResponseBody
	public Object add_repeat_clues(ProblemClues problemClues_before,Repeat_order order) {
		
		int add_repeat = agsService.add_repeat(problemClues_before, order, request);
		
		if(add_repeat!=0) { 
		   return renderSuccess("添加成功");
		}
		return renderError("添加失败");
		
	}
	
	/**案件线索更新**/
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Object edit(ProblemClues problemClues) {
		Result result = new Result();
		if(problemClues !=null){
			//开始更新案件
			problemCluesManagerService.updateMainService(problemClues);
			System.out.println(problemClues.toString());
			result.setObj(problemClues.getId());
			result.setSuccess(true);
			result.setMsg("线索更新完成");
			return result;
		}
		 
		 
		return  renderError("线索更新失败");
	}
	
	
	/**
	 * 案件线索导入
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public Object importExcel(MultipartHttpServletRequest req,String value) {
		    MultipartFile file = req.getFile("fileData");
			try {			
				return problemCluesManagerService.imporByExcel(file,request);
			} catch(ExcelException e){
				String exceptionName=e.getExceptionMsg();
				return renderSuccess("导入失败:"+exceptionName+"");
			}catch(Exception e){
				return renderSuccess("导入失败，请重试");
			}
		
		
	}
	
	
	/**
	 * 线索导出编辑界面
	 */
	@RequestMapping(value = "/OnExclEdit", method = RequestMethod.GET)
	public ModelAndView onExclEdit(String ids,String sqlStore) {
		ModelAndView result = new ModelAndView(ONEXCLEDIT);
		//选中要导出的条目
		if(ids!=null&&!"".equals(ids)){
			result.addObject("ids", ids);
		};
		if(sqlStore!=null&&!sqlStore.equals("")) {
			result.addObject("sqlStore",sqlStore);
		}
		
		return result;
	}
	
	/*
	 * 案件线索导出
	 */	
	@RequestMapping(value = "/OnImportExcel")
	public void OnImportExcel2(String columns,String choseIds,String sqlStore){
		/*columns ="id,"+columns;*/
		//字段名称
		String[] strs =columns.split(",");
		//区县线索库 切换数据源
		String ids=choseIds;
		if(choseIds.indexOf("_")>=0) {
			//切换数据源
			Organization organ = organizationMapper.findOrganizationById(Long.valueOf(choseIds.split("_")[1]));
			DataSourceContextHolder.setDbType(organ.getAddress());
			choseIds=choseIds.split("_")[0];
			
		}
		//生成查询sql语句
		String sql=SqlMaker10.maker(strs, choseIds,sqlStore);
		//从视图查询数据
		List<ExcelPojo> list =problemCluesManagerService.excelData(sql);
		//区县线索库 切换回数据源
		if(ids.indexOf("_")>=0) {
			DataSourceContextHolder.setDbType("master_dataSource");			
		}
			if(list.size()>0){
				//处理多选问题
				for(ExcelPojo e:list) {
					String fields = e.getFields();
					if(fields!=null) {
						e.setFields(SqlMaker10.num_to_world(fields, "fields",dictMapper.findByDictPid("0114")));
					}
					String zyName = e.getZyName();
					if(zyName!=null) {
						e.setZyName(SqlMaker10.num_to_world_dictId(zyName, "zyName",dictMapper.findByDictPid("0105")));						
					}
					String legalact_zw = e.getLegalact_zw();
					if(legalact_zw!=null) {
						e.setLegalact_zw((SqlMaker10.num_to_world_dictId(legalact_zw, "legalact_zw",dictMapper.findLikeByDictPid("0146"))));
					}
					String legalact_qt = e.getLegalact_qt();
					if(legalact_qt!=null) {
						e.setLegalact_qt((SqlMaker10.num_to_world_dictId(legalact_qt, "legalact_qt",dictMapper.findLikeByDictPid("0147"))));
					}
					String sls_meaResult = e.getSls_meaResult();
					if(sls_meaResult!=null) {
						e.setSls_meaResult((SqlMaker10.num_to_world_dictId(sls_meaResult, "sls_meaResult",dictMapper.findLikeByDictPid("0148"))));
					}
					
				}
				if(list.size()>0) {
					//处理处置方式问题
					for(ExcelPojo e:list) {
						String czMethod_All = "";
						List<Progress> findProgressByCid = progressMapper.findProgressByCid(e.getId());
						for (Progress progress : findProgressByCid) {
							if(progress.getPointValue().equals("10")&&progress.getTimeForday()!=null&&czMethod_All.indexOf("初步核实")==-1) {
								czMethod_All =czMethod_All+"初步核实:"+progress.getTimeForday().substring(0, 10)+";";
							}
							if(progress.getPointValue().equals("21")&&progress.getTimeForday()!=null&&czMethod_All.indexOf("纪委立案时间")==-1) {
								if("纪委立案时间".equals(progress.getDetail())&&progress.getTimeForday()!=null) {
									czMethod_All =czMethod_All+"纪委立案时间:"+progress.getTimeForday().substring(0, 10)+";";
								}
								if("监委立案时间".equals(progress.getDetail())&&progress.getTimeForday()!=null&&czMethod_All.indexOf("监委立案时间")==-1) {
									czMethod_All =czMethod_All+"监委立案时间:"+progress.getTimeForday().substring(0, 10)+";";
								}
							}
						}
						e.setCzMethod_all(czMethod_All);
						
					}
				}
				try {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
					//Arrays.asList(strs)标题
					HssfUtil.writeExcel(response, "线索数据表格"+sdf.format(new Date())+".xls", list, ExcelPojo.class,  Arrays.asList(strs));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	
	/**
	 * 案件线索删除 
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Object delete(Delete_Clues delete_Clues) {
		if(!delete_Clues.getCauseId().equals("")){
			problemCluesManagerService.deleteByids(delete_Clues);
			return renderSuccess("删除成功");
		}else{
			return renderSuccess("删除失败，请重试。");
		}
	}
	
	/**
	 * 案管室分办操作(已加县区下发)
	 * 此请求包用处理 1.案管室分办到监督室2.案管室分办到审查室 3.市安管室分办到县案管室 4.县安管室再分办时的反馈
	 */
	@RequestMapping(value = "/agsfb")
	@ResponseBody
	public Object agsfb(ListParam  listParam) {
		List<Progress> progress=listParam.getProgress();
		ProblemClues problemClues=listParam.getProblemClues();
		int fb_clues = agsService.fb_clues(progress,problemClues,request);
		if(fb_clues==0) {
			  if(problemClues.getState().indexOf("LC")!=-1) {
				  return renderSuccess("分办信息已留存");
			  }
              if(problemClues.getState().indexOf("TJ")!=-1) {
            	  return renderSuccess("线索已分办");
			  }
			
		}
		return renderError("保存失败");
	}
	
	@RequestMapping("/ags_fb")
	@ResponseBody
	public Object ags_fb(String id,String ip) {
		Map<String, Object> ags_fg = agsService.ags_fg(id,ip);
		Result result=new Result();
		result.setObj(ags_fg);
		return result;
	}
}
