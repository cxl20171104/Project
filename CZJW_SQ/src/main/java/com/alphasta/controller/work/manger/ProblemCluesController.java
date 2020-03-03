package com.alphasta.controller.work.manger;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.ExcelException;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.commons.statisticsUtils.SqlMaker10;
import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.GsonUtil;
import com.alphasta.commons.utils.HssfUtil;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.en.Repeat_order;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.model.Back;
import com.alphasta.model.BootstapTree;
import com.alphasta.model.Delete_Clues;
import com.alphasta.model.ExcelPojo;
import com.alphasta.model.Group;
import com.alphasta.model.LegalAct;
import com.alphasta.model.Lien;
import com.alphasta.model.ListParam;
import com.alphasta.model.Organization;
import com.alphasta.model.PageControl;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.TransModel;
import com.alphasta.model.User;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.ProblemCluesManagerServices;
import com.alphasta.service.PunishmentService;
import com.alphasta.service.RemindService;
import com.alphasta.service.StateService;
import com.alphasta.service.UserService;

@Controller
@RequestMapping("/probleClues")
public class ProblemCluesController  extends BaseController{
	@Autowired
	private  ProblemCluesManagerServices problemCluesManagerService;
	@Autowired
	private ProgressMapper progressMapper;
	@Autowired
	private UserService userService;
	 @Autowired
     private OrganizationMapper organizationMapper;
	private static final String DETAIL = "/probleClues/cluesDetail";
	private static final String SEARCHINFO = "/probleClues/probleCluesSearchInfo";
    /**
	 * 消息提醒:消息格式   消息内容_organId+"X"
	 */
	public void  sendMessage(String msg,HttpServletRequest request) {
		       //============================================向前台页面发送消息=======================================
				//WebSocketTest w=new WebSocketTest();
				//ChatMessageInbound createWebSocketInbound = (ChatMessageInbound)w.createWebSocketInbound("dd", request);
				 ByteBuffer bb = ByteBuffer.allocate(10000);
				 CharBuffer cb = bb.asCharBuffer();
//				try {
//					
//					//createWebSocketInbound.onTextMessage(cb.put(msg));
//					System.out.println("发送消息____"+msg);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				//============================================向前台页面发送消息=======================================
	}
	/**
	 * 案件线索管理页
	 * @return 
	 *
	 * @return
	 */
	@RequestMapping(value = "/cxl/{id}", method = RequestMethod.GET)
	public ModelAndView manager2(@PathVariable String id,String zddb,ProblemClues problemClues,PageControl pageControl) {
		ModelAndView result = new ModelAndView("/probleClues/"+id);
		System.out.println(zddb);
        if(zddb!=null) {
        	result.addObject("zddb", zddb);
        }
		
		if(id.equals("timeOver")) {
			String buttonHas = problemCluesManagerService.buttonHas();
			result.addObject("pageName", buttonHas);
			
		}else {
			
			result.addObject("pageName", id);
		}
		 //线索来源部门id
		 result.addObject("fromId", problemClues.getFromId());
		 result.addObject("finalState",problemClues.getFinalState());
		 //界面控制器
		 result.addObject("control",pageControl.getControl());
		 //部门id
		    //如果organId是y表示 要使用当前用户的部门id
			if(problemClues!=null&&problemClues.getOrganId()!=null&&problemClues.getOrganId().equals("y")) {
				 result.addObject("organId",getCurrentOrganId());
			}else {
				 result.addObject("organId", problemClues.getOrganId());
			}
		   
		//向前台传下拉选的参数
		return result;
	}
	
	/**
	 * 各监察室各县的列表界面
	 */
	@RequestMapping("/mm")
	public ModelAndView cxl2(String id,String zddb,HttpServletRequest request) {
		//id部门id
		ModelAndView mv=new ModelAndView("/probleClues/AGSZAIBAN");
		mv.addObject("organId", id);
		if(zddb!=null) {
		mv.addObject("zddb", zddb);
		}
		return mv;
	}
    /**
	 * 案件线索信息页 添加编辑页
	 *
	 * @return
	 * @throws ParseException 、、、、、probleCluesInfo
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(String flag,String id,String special) throws ParseException {
		String tz = "";
		if("1".equals(flag)) {
			tz = "/part/saveClues";
		}else if("2".equals(flag)){
			tz = "/part/saveCluesUnit";
		}
		ModelAndView result = new ModelAndView(tz);
		//取存在options中的选项  请看servlet 中的Option
		result.addObject("pageName", "newClues");
		result.addObject("organId", getCurrentOrganId());
		        //当前用户角色
				ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
				StringBuffer buf=new StringBuffer();
				for(Long role:user.roleList) {
					buf.append(role+"_");
				}
				result.addObject("roleId",buf.toString());
		return  result;
	}
	/**
	 * 线索导入须知也
	 */
	@RequestMapping(value = "/importExcel_xz")
	public String importExcel_xz(){
		return "util/importExcel_xz";
	}
	
	
	/**
	 * 案件线索导入
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public Object importExcel(MultipartHttpServletRequest req,String value) {
		MultipartFile file = req.getFile("fileData");
		if("-1".equals(value)){
			//获取文件的压缩路径
			String path = req.getSession().getServletContext().getRealPath("JsonData3")+File.separator;
			return "没有该方法";
			
		}else{
			try {	
			     Object imporByExcel = problemCluesManagerService.imporByExcel(file,request);
				return imporByExcel;
			} catch(ExcelException e){
				String exceptionName=e.getExceptionMsg();
				return renderSuccess("导入失败:"+exceptionName+"");
			}catch(Exception e){
				return renderSuccess("导入失败，请重试");
			}
		}
		
	}
	
	

	/**
	 * 案件线索查询条件页
	 *
	 * @return
	 */
	@RequestMapping(value = "/searchInfo", method = RequestMethod.GET)
	public ModelAndView searchInfo(String special,String pageName) {
		ModelAndView result = new ModelAndView(SEARCHINFO);
		result.addObject("special2", special);
		result.addObject("pageName", pageName);
		return result;
	}
	
	
	
	
	
	/**
	 * 案件线索详情页
	 *
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id) throws ParseException {
		ModelAndView result = new ModelAndView(DETAIL);
		ProblemClues problemClues = new ProblemClues();
		if (id != null) {
			problemClues = problemCluesManagerService.findProblemCluesById(id);
		}
		//取存在options中的选项  请看servlet 中的Option
		result.addObject("problemClues", problemClues);
		return result;
	}
	@RequestMapping(value="/getSameClues",method=RequestMethod.GET)
	public ModelAndView getSameClues(String id) {
		ModelAndView  result=new ModelAndView("/probleClues/cluesDetail");
		ProblemClues problemClues = problemCluesManagerService.findProblemCluesById(id);
		if(problemClues!=null) {
			result.addObject("problemClues",problemClues);
		}
		return result;
	}
	@RequestMapping("/isHaveFile")
	@ResponseBody
	public Object isHaveFile(String filePath) {
		String ysEndPath = request.getSession().getServletContext().getRealPath("files")+File.separator+filePath;
		System.out.println(ysEndPath);
		File file=new File(ysEndPath);
		if(file.exists()) {
			return renderSuccess();
		}else {
			return renderError("文件不存在");
		}
	}
   
	//案件下载
	@RequestMapping("/accDownload")
	@ResponseBody
	public void accDownload(String fileName,String filePath){
		 //判断文件是否存在
		 String ysEndPath = request.getSession().getServletContext().getRealPath("files")+File.separator+filePath;
		 fileName =fileName+filePath.substring(filePath.lastIndexOf("."));
		 boolean downFile = FileUtils.downFile(response, ysEndPath, fileName);
	}
	
	/**
	 * 打开各自的办理界面--主界面分发器
	 * 有以下情况：
	 * 1.在本市的数据显示本市办理  ip不用切换
	 * 2.在本市的数据显示县区办理  ip要切换（以本市数据为基础直接查 isXf获取ip  如果以县区数据为基础则需要 先查询本市数据获取isXf）  交办给县区的
	 */
	@RequestMapping(value="/action")
	public ModelAndView action(String id,String pageName) {
		/**
		 * 说明：案件办理过程中始终只有 一张表单 ，它代表了，该案件的所有信息。
		 * 不论在哪个科室点击线索后 ，先显示线索的详细信息。具体再点击办理的选项卡 ，再从后台获取
		 */
		//如果是县区线索库切换数据源 如果是县区线索  那么会传回来ip  格式为 id_ip
		String idc=id;
		ProblemClues problemClues = new ProblemClues();
		String ip="";
		if(id.indexOf("_")>=0) {
			//切换数据源 ip位县区的ip
			if(id.indexOf("D")>=0) {
				//从市区监督室或案管室列表查看下发到县区线索的办理详情（D）
				ip=id.split("_")[1].substring(0, id.split("_")[1].length()-1);
				pageName=pageName+"Down";
			}
			id=id.split("_")[0];
			DataSourceContextHolder.setDbType(ip);
		}
		if (id != null) {
			problemClues = problemCluesManagerService.findProblemCluesById(id);
		}
		
		ModelAndView result = new ModelAndView("/part/saveClues");
		List<Progress> findProgressByCid = progressMapper.findProgressByCid(id);
		if(findProgressByCid!=null&&!findProgressByCid.isEmpty()) {
			String time = findProgressByCid.get(0).getTime();
			result.addObject("agsSLTime",time.substring(0, 10));
		}
		//线索信息
		result.addObject("problemClues", problemClues);
		//界面名称
		result.addObject("pageName", pageName);
		//当前用户部门
		result.addObject("organId", getCurrentOrganId());
		//当前用户地区
		result.addObject("xzqh", Param.xzqh);
		//将ip也设置好
		result.addObject("dataSourceIp", ip);
		//当前用户角色
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		StringBuffer buf=new StringBuffer();
		for(Long role:user.roleList) {
			buf.append(role+"_");
		}
		result.addObject("roleId",buf.toString());
		//区县线索库 切换回数据源
		if(idc.indexOf("_")>=0) {
			DataSourceContextHolder.setDbType("master_dataSource");			
		}
		return result;
	}
	//全网查询中的县区详情使用
	@RequestMapping(value="/xction")
	public ModelAndView xction(String id,String pageName,String ip) {
		//id这里的id为县区自己添加的线索的id
		//如果要查看就要先切换数据源
	    //id包括两项内容  线索id和这条线索的部门id
		ModelAndView result = null;
		//切换数据源查询数据
		ProblemClues problemClues = new ProblemClues();
		if(StringUtils.isNotEmpty(ip)) {
			DataSourceContextHolder.setDbType(ip);
			if (id != null) {
				problemClues = problemCluesManagerService.findProblemCluesById(id);
			}
			if(problemClues==null) {
				result=new ModelAndView("/error/cluesBlank.jsp");
				return result;
			}
			result = new ModelAndView("/part/saveClues");
			result.addObject("problemClues", problemClues);
			result.addObject("pageName", pageName);
			//把县里的ip也设置进去
			result.addObject("dataSourceIp", ip);
			//数据库再换回来
			DataSourceContextHolder.setDbType("master_dataSource");	
			//返回成功界面
			return result;
		}else {
			result=new ModelAndView("/error/ipBlank.jsp");
			return result;
		}
		
	}
	
	
	//区县线索库查看 和上面不同这里不能使用 isXf要是oId查询 
		@RequestMapping(value="/fuction")
		public ModelAndView fuction(String id,String pageName) {
			//id这里的id为县区自己添加的线索的id
			//如果要查看就要先切换数据源
		    //id包括两项内容  线索id和这条线索的部门id
			String cid=id.split("_")[0];
			String xq_oid=id.split("_")[1];
			ModelAndView result = null;
			//该县区的ip
			Organization xq_organ = organizationMapper.findOrganizationById(Long.valueOf(xq_oid));
			if(xq_organ==null) {
				result=new ModelAndView("/error/noOrganId.jsp");
				return result;
			}
			String ip=xq_organ.getAddress();
			//切换数据源查询数据
			ProblemClues problemClues = new ProblemClues();
			if(StringUtils.isNotEmpty(ip)) {
				DataSourceContextHolder.setDbType(ip);
				if (cid != null) {
					problemClues = problemCluesManagerService.findProblemCluesById(cid);
				}
				if(problemClues==null) {
					result=new ModelAndView("/error/cluesBlank.jsp");
					return result;
				}
				result = new ModelAndView("/part/saveClues");
				result.addObject("problemClues", problemClues);
				result.addObject("pageName", pageName);
				//把县里的ip也设置进去
				result.addObject("dataSourceIp", ip);
				//数据库再换回来
				DataSourceContextHolder.setDbType("master_dataSource");	
				//返回成功界面
				return result;
			}else {
				result=new ModelAndView("/error/ipBlank.jsp");
				return result;
			}
			
		}
	
	
	
	
	
	/**
	 * 打开立案审查页面
	 */
	@RequestMapping(value = "/laPage", method = RequestMethod.GET)
	public String laPage(String id,Model model) throws ParseException {
		//查询出案件信息
		ProblemClues problemClues = new ProblemClues();
		if (id != null) {
			problemClues = problemCluesManagerService.findProblemCluesById(id);
		}
		//取存在options中的选项  请看servlet 中的Option
		Progress progress = new Progress();
		progress.setCauseId(id);
		progress.setDetail("1");
		progress.setPointValue("25");
		Progress findProgress = progressMapper.XinfindProgress(progress);
		if(!"".equals(findProgress.getTimeForday())&&findProgress.getTimeForday()!=null) {
			model.addAttribute("laTime", findProgress.getTime());
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("causeId", id);
		map.put("pointValues", "(9)");
		List<Progress> progressByMap = progressMapper.getProgressByMap(map);
		List<User> group = userService.getUsersByOrgId((long)52);
		model.addAttribute("group",group); 
		model.addAttribute("receiveTime", progressByMap.get(0).getTime().substring(0, 10));
		model.addAttribute("problemClues", problemClues);
		return "/probleClues/lasc";
	}
	
    /**
     * 加载子项下拉
     */
	@RequestMapping("/GetExpandJson")
	@ResponseBody
	public Object GetExpandJson(String id) {
		//从字典表中查询出子项
		List<BootstapTree> data=problemCluesManagerService.GetExpandJson(id);
		if(data!=null) {
			return data;
		}else {
			return renderError("异常");
		}
	}
	
	/**
	 * 案件签收
	 */
	@RequestMapping("/get")
	@ResponseBody
	public  Object get(ProblemClues problemClues) {
		if(problemClues!=null&&!problemClues.getId().equals("")) {
			boolean problemCluesService = problemCluesManagerService.updateProblemCluesService(problemClues);
			if(problemCluesService) {
				return renderSuccess();
			}else {
				return renderError("签收失败");
			}
		}
		return renderError("签收失败");
	}
	/**
	 * 线索退回（新添加）
	 *
	 * @return
	 * @throws ParseException 、、、、、probleCluesInfo
	 */
	@RequestMapping("/back")
	@ResponseBody
	public  Object returnClues(Back back) {
		boolean return_clues_service = problemCluesManagerService.backService(back);
		if(return_clues_service) {
			return renderSuccess();
		}
		return renderError("退回失败");
	}
	
	
	/**
	 * 撤回和退回方法 打开界面用于填写 退回或撤回的信息
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("/call_back")
	public ModelAndView call_back(String id, String type) {
		ModelAndView mv=new ModelAndView("/probleClues/call_back");
		mv.addObject("id", id);
		mv.addObject("type",type);
		return mv;
	}
	/**
	 * 撤回或退回方法 专向业务处理的数据
	 * @param back
	 * @return
	 */
	@RequestMapping("/back_please")
	@ResponseBody
	public Object back_please(Back back) {
		
		boolean back_please_service = problemCluesManagerService.back_please_service( back);
		
		if(back_please_service) {
			return renderSuccess();
		}
		return renderError("退回失败");
		
	}
	@RequestMapping("/call_back_detail")
	@ResponseBody
	public ModelAndView call_back_detail(Back back) {
		Back call_back_detail = problemCluesManagerService.call_back_detail(back);
		ModelAndView mv=new ModelAndView("/probleClues/call_back_detail");
		if(call_back_detail!=null) {
			mv.addObject("back",call_back_detail);
		}
		return mv;
	}
	@RequestMapping("/del_reason")
	@ResponseBody
	public ModelAndView del_reason(Delete_Clues delete_Clues) {
		delete_Clues = problemCluesManagerService.del_reason(delete_Clues);
		ModelAndView mv=new ModelAndView("/probleClues/del_reason");
		if(delete_Clues!=null) {
			mv.addObject("delete_Clues",delete_Clues);
		}
		return mv;
	}
	@RequestMapping("/del_dialog")
	@ResponseBody
	public ModelAndView del_dialog(String ids) {
		ModelAndView mv=new ModelAndView("/probleClues/del_dialog");
		mv.addObject("ids",ids);
		return mv;
	}
}
