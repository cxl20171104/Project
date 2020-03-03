package com.alphasta.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.ExcelField;
import com.alphasta.commons.result.PartyMoneyOut;
import com.alphasta.commons.result.PartyMonny;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.ExcelInfo;
import com.alphasta.commons.utils.ExcelUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.HssfUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.StringEscapeEditor;
import com.alphasta.model.Organization;
import com.alphasta.model.Pay;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.PayService;


@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {
	@Autowired
	private PayService payService;
	@Autowired
	private OrganizationService organizationService;
	
	private static final String INFO = "/work/payInfo";
	private int importXls;
	
	@Override
	 @InitBinder
	    public void initBinder(ServletRequestDataBinder binder) {
	        /**
	         * 自动转换日期类型的字段格式
	         */
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

	        /**
	         * 防止XSS攻击
	         */
	        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	    }
	/**
	 *  
	 * @return  页面加所在部门ID
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView result = new ModelAndView("/work/payList");
		Integer organizationId = getCurrentUser().getOrganizationId();
		Organization organization = organizationService.findOrganizationById(organizationId.longValue());
		if(organization.getCode().length()>4){
			organization  = organizationService.findOrganizationById(organization.getPid());
			if(organization.getCode().length()>4){
				organization  = organizationService.findOrganizationById(organization.getPid());
			};
		};
		result.addObject("oid", organization.getId());
		result.addObject("oname", organization.getName());
		return result;
	}
	
	/**
	 * 增加支付信息
	 *
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Object save(String organization_id,String userid,String payTime,String payType,String money){
		Pay pay=new Pay();
		pay.setMoney(money);
		pay.setOrganization_id(organization_id);
		pay.setUserid(userid);
		pay.setPayTime(payTime);
		pay.setPayType(payType);
		pay.setId(GetIdUtil.getId());
		System.out.println(pay.toString());
		payService.save(pay);
		return renderSuccess("添加成功！");
	}
	/**
	 * 修改支付信息
	 *
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Object del(String ids){
		 String[] split = ids.split(",");
		 System.out.println(split);
		if(split.length>0){
			for(String s:split){
				payService.del(s);
			}
		}else{
			payService.del(ids);
		}
		
		return renderSuccess("删除成功！");
	}
	/**
	 * 修改支付信息
	 *
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Object edit(Pay pay){
		pay.setState("2");
		payService.update(pay);
		return renderSuccess("修改成功！");
	}
		/**
		 * 查询和显示支付情况
		 *
		 * @return
		 */
		@RequestMapping(value = "/findPayPageCondition")
		@ResponseBody
		public Object findPayPageCondition(Integer page, Integer rows, String sort, String order,String oid,String userid,String organization_id,
				Pay pay,String start,String end) {
			PageInfo pageInfo = new PageInfo(page, rows, sort, order);
			//organization_id可能是该用户所在支部的id也可能是该用户上级党委的id  因此需要查询出可能的所有下级id
			//如果没有下级id则为支部id  如果有则查出所有的支部 id 目的是直接根据  部门统计缴费情况
			//根据pid查询所有下级
			String result=null;

			if(organization_id!=null&&!"".equals(organization_id)){
			StringBuilder str=new StringBuilder();			
			find(Long.valueOf(organization_id), str);
			
			if(str.toString().equals("")){
				result="("+organization_id+")";
			}else{
				str.deleteCharAt(str.lastIndexOf(","));
				result="("+str.toString()+")";				
			}
			} else if(oid!=null&&!"".equals(oid)){
				StringBuilder str=new StringBuilder();			
				find(Long.valueOf(oid), str);
				
				if(str.toString().equals("")){
					result="("+oid+")";
				}else{
					str.deleteCharAt(str.lastIndexOf(","));
					result="("+str.toString()+")";				
				}
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			if(pay!=null){
				condition.put("start", start);
				condition.put("end", end);
				condition.put("userid", "".equals(pay.getUserid())?null:pay.getUserid());
				condition.put("organization_id", result);
			};		
			pageInfo.setCondition(condition);
			payService.findPayPageCondition(pageInfo);
			return renderSuccess(pageInfo);
		}
		
		//使用递归查询出所有下级部门的id
		public  void find(Long organization_id,StringBuilder  result){
	        List<Organization> Organ= organizationService.findOrganizationByPid(organization_id);
	        for(Organization o:Organ){
	    	List<Organization> Organ2= organizationService.findOrganizationByPid(o.getId());	
	    	if(Organ2!=null&&Organ2.size()!=0){
	    		find(o.getId(),result);
	    	}else{
	    		result.append(o.getId()+",");
	    	}
	    }
			
			
			
		}
		
		
		/**
		 * 导入案件线索信息
		 *
		 * @return
		 */
		@RequestMapping(value = "/importExcel")
		@ResponseBody
		public Object importExcel(MultipartHttpServletRequest request) {
			MultipartFile file = request.getFile("fileData");
			try {
				Map<String, String> map = new HashMap<String, String>();
				//读取Excel表格数据
				ExcelInfo excelInfo = ExcelUtils.readExcel(file.getInputStream(),file.getOriginalFilename());
				map.put("userid", "用户ID");
				map.put("paybase", "缴费基数");
				map.put("payscale", "党费比例");
				map.put("money", "月应缴金额");
				map.put("year", "年度");
				map.put("month", "月度");
				List<Object> list = excelInfo.dataToModel("Sheet0", 1, Pay.class.getName(),map);
				importXls = payService.importXls(list);
				if(importXls==1){
					return renderError("表格模板不正确");
				}
				if(importXls==2){
					return renderError("用户名或部门名不正确");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				return renderError("解析excel失败");
			} catch (Exception e) {
				e.printStackTrace();
				return renderError(e.getMessage());
			}
			return renderSuccess("导入成功");
		}

		/**
		 *  下载缴费模板
		 * @param request
		 * @param year
		 * @param month
		 * @param oid
		 * @param response
		 * @throws IllegalAccessException 
		 * @throws IOException 
		 * @throws IllegalArgumentException 
		 */
		@RequestMapping("/downfile")
		public void downFile(HttpServletRequest request,String year,String month,String oid, String oname,
				HttpServletResponse response) throws IllegalArgumentException, IOException, IllegalAccessException {	
			Map<String,Object> map=new HashMap<String, Object>();
			 Calendar now = Calendar.getInstance();
			    String state="1";
			if(year!=null&&!"".equals(year)){
				map.put("year", year);
			}else{
				map.put("year", now.get(Calendar.YEAR));
			};
			if(month!=null&&!"".equals(month)){
				int m=Integer.valueOf(month);
				if(m<10){
					month="0"+m;
				}
				map.put("month", month);
			}else{
				int m=now.get(Calendar.MONTH)+1;
				if(m<10){
					month="0"+m;
				}else{
					month=String.valueOf(m);
				}
				map.put("month", month);
			};
			System.out.println(oid);//1
			Organization o	=organizationService.findOrganizationById(Long.valueOf(oid));
			if("02".equals(o.getCode().substring(0,2))){
				o	=organizationService.findOrganizationById(Long.valueOf(o.getAddress()));
				oid=String.valueOf(o.getId());
			}
			if(oid!=null&&!"".equals(oid)){
				
				map.put("oid", oid);
			}else{
				
				map.put("oid", getCurrentUser().getId());
			};
					int m=now.get(Calendar.MONTH)+1;
					String mo;
					if(m<10){
						mo="0"+m;
					}else{
						mo=String.valueOf(m);
					}
			if(!year.equals(String.valueOf(now.get(Calendar.YEAR)))||!month.equals(mo)){
				state="3";
			};
			    map.put("state", state);
		    //查询数据
			List<Pay> list = payService.findPayByMonth(map);
			//生成表格
			HssfUtil.writeExcelWithTitle(response, year+"年度"+month+"月党费待缴台账.xls", "单位:"+oname+"#"+year+"年"+month+"月份", ExcelClass(list),PartyMonny.class);
		}
		
		public List<PartyMonny>  ExcelClass(List<Pay> list){
			List<PartyMonny> pa=new ArrayList<PartyMonny>();
			for(int i = 0; i<list.size();i++){
				 PartyMonny p=new PartyMonny();
				 Pay pay=list.get(i);
				 p.setId(i+1);
				 p.setUserid(pay.getUserid());
				 p.setName(pay.getUname());
				 p.setOname(pay.getOname());
				 p.setPaybase(pay.getPaybase());
				 p.setPayscale(pay.getPayscale());
				 p.setYear(pay.getYear());
				 p.setMonth(pay.getMonth());
				 p.setMonny(pay.getMoney());				
				 pa.add(p);				
			}
			return pa;
		}
	
		/**
		 * 下载季度缴费记录
		 * @param request
		 * @param response
		 * @param year
		 * @param quarter
		 * @param oid
		 * @throws IllegalAccessException 
		 * @throws IOException 
		 * @throws IllegalArgumentException 
		 */
		@RequestMapping("/outExcel")
		public void outExcel(HttpServletRequest request,HttpServletResponse response,String year,String quarter,String oid) throws IllegalArgumentException, IOException, IllegalAccessException{
			Map<String,Object> map=new HashMap<String, Object>();
			 Calendar now = Calendar.getInstance(); 
			if(year!=null&&!"".equals(year)){
				map.put("year", year);
			}else{
				map.put("year", now.get(Calendar.YEAR));
				
			};
			if(quarter!=null&&!"".equals(quarter)){
				map.put("quarter", quarter);
			}else{
				map.put("quarter", now.get(Calendar.MONTH)/3+1);
			};
			if(oid!=null&&!"".equals(oid)){
				map.put("oid", oid);
			}else{
				map.put("oid", getCurrentUser().getId());
			} 
		    //查询数据
			List<Pay> list = payService.findPayByQuarter(map);
			HssfUtil.writeExcel(response, year+"年度"+quarter+"季度党费收缴台账",ExcelClassOut(list),PartyMoneyOut.class);
		}
		public List<PartyMoneyOut>  ExcelClassOut(List<Pay> list){
			List<PartyMoneyOut> pa=new ArrayList<PartyMoneyOut>();
			for(int i = 0; i<list.size();i++){
				PartyMoneyOut p=new PartyMoneyOut();
				Pay pay=list.get(i);
				p.setId(i+1);
				p.setUserid(pay.getUserid());
				p.setName(pay.getUname());
				p.setOname(pay.getOname());
				p.setPaybase(pay.getPaybase());
				p.setPayscale(pay.getPayscale());
				p.setMonny(pay.getMoney());
				p.setPayTime(pay.getPayTime());
				p.setMonny(pay.getMonth());
				pa.add(p);				
			}
			return pa;
		}
}
