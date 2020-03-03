package com.alphasta.controller.work.acc;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.mapper.AccessoriesMapper;
import com.alphasta.model.Accessories;
import com.alphasta.model.Attac;
import com.alphasta.service.AccessoriesService;
import com.alphasta.service.AttacService;
@Controller
@RequestMapping("/acc")
public class AccessoriesController extends BaseController {
	@Autowired
	private AccessoriesService accessoriesService;
	@Autowired
	private AttacService attacService;
	@Autowired
	private AccessoriesMapper  accessoriesMapper;
	//文书界面
	@RequestMapping("/page")
	public ModelAndView page() {
		//查询出所有的报表类别
		List<Accessories> type = accessoriesMapper.getType();
		ModelAndView mv =new ModelAndView("/probleClues/reportPage");
		mv.addObject("type",type);
		return mv;
	}
	//呈批签界面
	@RequestMapping("/page_cpq")
	public ModelAndView page_cpq() {
		//查询出所有的报表类别
		ModelAndView mv =new ModelAndView("/probleClues/reportPage_cpq");
		return mv;
	}
	//文书或呈批签界面
	@RequestMapping("/reportList")
	@ResponseBody 
	public Object  reportList(Integer page, Integer rows, String sort, String order,Accessories acc) {
		Map<String, Object> reportListService = accessoriesService.getReportListService(page,  rows,  sort,  order, acc);
	    return reportListService;
	}
	
	//上传文书界面
	@RequestMapping("/addAccDialog")
	@ResponseBody
	public ModelAndView addAccDialog(Long id){
		ModelAndView result=new ModelAndView("/caseclue/addAccDialog");
		result.addObject("caseid",id);
		return result;		
	}
	//上传呈批签界面
	@RequestMapping("/addAccDialog_cpq")
	@ResponseBody
	public ModelAndView addAccDialog_cpq(Long id){
		ModelAndView result=new ModelAndView("/caseclue/addAccDialog_cpq");
		result.addObject("caseid",id);
		return result;		
	}
	@RequestMapping("/editAccDialog")
	@ResponseBody
	public ModelAndView editAccDialog(Long id){
		Accessories acc = accessoriesService.selectByPrimaryKey(id);
		if(acc!=null) {
			ModelAndView result=new ModelAndView("/caseclue/editAccDialog");
			result.addObject("acc",acc);
			return result;	
		}
			ModelAndView result=new ModelAndView("/error/500");
		    return result;
			
	}
	/**
	 * 添加附件如果这条案件是下发的案件需要发送一条附件数据 并且该条案件的url要写成192.168.22.190:prot/CZJW/files/upload/文件名的格式
	 * @param acc
	 * @return
	 */
	@RequestMapping("/addAcc")
	@ResponseBody
	public Result addAcc(Accessories acc){
	        /**
	         * 存储单个文件
	         */
		   
		    //如果文件地址不存在不添加
		    if(acc.getUrl()!=null&&!acc.getUrl().equals("")) {
			acc.setIsdel("1");
			acc.setUploadate(new Date());
			acc.setOrganId(getCurrentUser().getOrganizationId());
			acc.setUploadername(getStaffName());
			acc.setOrganId(getCurrentOrganId());
			 accessoriesService.insertSelective(acc);
			/**
			 * 存储多个文件
			 */
			Long pid=acc.getId();
			String[] urls = acc.getUrl().split(",");
			Attac  a=new Attac();
			a.setPid(Integer.parseInt(String.valueOf(pid)));	
			for(String url:urls){
				a.setUrl(url);
				System.out.println("url:"+url);
				attacService.insert(a);
			}
		    }
		    Result result=new Result();
		    result.setMsg("添加成功");
		    result.setObj(acc.getId());
			return result;
		
	}
	@RequestMapping("/editAcc")
	@ResponseBody
	public Result editAcc(Accessories acc) {
		Result result=new Result();
		int r = accessoriesService.updateByPrimaryKey(acc);
		if(r==1) {
			result.setMsg("修改成功");
			result.setSuccess(true);
			return result;
		}else {
			result.setMsg("修改失败");
			return result;
		}
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public Object remove(Long id){
		try {
			if(id!=null){
				accessoriesService.deleteByPrimaryKey(id);
				return renderSuccess("删除成功");
			}
			return renderError("删除失败");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("删除失败");
		}
	}
}
