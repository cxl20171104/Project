package com.alphasta.servlet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alphasta.commons.shiro.ShiroUser;
import com.alphasta.model.Dict;
import com.alphasta.model.Organization;
import com.alphasta.model.Param;
import com.alphasta.model.User;
import com.alphasta.service.DictService;
import com.alphasta.service.OrganizationService;
import com.alphasta.service.UserService;

public class Option extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("==========================加载下拉选============================");
		super.init(config); 
		ServletContext servletContext = this.getServletContext();  
		WebApplicationContext wac = null;   
	    wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);  
	    DictService dictService = (DictService)wac.getBean("dictService");
	    OrganizationService organization=(OrganizationService)wac.getBean("organization");
	    Dict dict =new Dict();
	    System.out.println("=========================线索表下拉选===========================");
		dict.setDictPid("0109");///四种状态
		List<Dict> x = dictService.findDictByDictPid(dict);
		servletContext.setAttribute("fourForms",x);
		dict.setDictPid("0114");//领域
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("fields",x);
		dict.setDictPid("08");//处置方式
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("czMethod",x);
		dict.setDictPid("0103");//线索状态
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("xxstate",x);
		dict.setDictPid("0112");//督办单位
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("superviseCompany",x);
		dict.setDictPid("0105");//反映问题性质
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("fyzvviolation",x);
		dict.setDictPid("0108");
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("problemLand",x);
		dict.setDictPid("0104");//线索来源
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("clues",x);
		
		dict.setDictPid("0116");//其他处理
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("blResult_qtcl",x);
		dict.setDictPid("0117");//组织处理
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("blResult_zzcl",x);
		dict.setDictPid("0118");//政务处分
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("blResult_zjcf",x);
		dict.setDictPid("0119");//党纪处分
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("blResult_djcf",x);
		dict.setDictPid("0122");//专项行动
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("special",x);
		//承办部门
		List<Organization> organ = organization.findOrganizationByPid(12l);
		servletContext.setAttribute("oId",organ);
		//基层单位
		List<Organization> jc = organization.findOrganizationByPid(32l);
		servletContext.setAttribute("jc",jc);
		
		dict.setDictPid("0124");//审查室处置建议
		x = dictService.findDictByDictPid(dict);
		servletContext.setAttribute("scsMethod",x);
		
		
		dict.setDictPid("06");//审理室处置建议
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("slMethod",x);
		dict.setDictPid("0126");//干部管理权限
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("cadre",x);
		dict.setDictPid("0127");//人大代表
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("np",x);
		dict.setDictPid("0128");//政协委员
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("zx",x);
		dict.setDictPid("0129");//部门分类
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("departmenType",x);
		dict.setDictPid("0130");//企业性质
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("natureOfenterprise",x);
		dict.setDictPid("0131");//企业级别
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("classOfenterprise",x);
		dict.setDictPid("0132");//岗位
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("post",x);
		dict.setDictPid("0133");//企业人员类别
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("posType",x);
		dict.setDictPid("0150");//类型
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("type",x);
		dict.setDictPid("0134");//一把手违纪
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("topDiscipline",x);
		dict.setDictPid("0121");//移送司法
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("blResult_yjsf",x);
		System.out.println("======================被反映人多选===============================");
		dict.setDictPid("0106");
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("rank",x);
		System.out.println("=====================其他下拉选=================================");
		dict.setDictPid("0120");//报表格式
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("reportGs",x);		
		dict.setDictPid("0135");//身份(公务员)
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("civilServant",x);
		dict.setDictPid("0136");//政治面貌
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("political",x);
		dict.setDictPid("013801");//贪污贿赂罪
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("twhl",x);
		dict.setDictPid("013802");//渎职犯罪
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("dzfz",x);
		dict.setDictPid("013803");//侵犯公民罪
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("qfgm",x);
		dict.setDictPid("013804");//其他侵权罪
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("qtly",x);
		dict.setDictPid("0139");//非党员非监察对象
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("pMSupervisoryObject",x);
		dict.setDictPid("0140");//证件类型
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("documentType",x);
		dict.setDictPid("0141");//中共党代表
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("partyRepresent",x);
		dict.setDictPid("0142");//党委委员
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("dwMember",x);
		dict.setDictPid("0143");//纪委委员
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("jwMember",x);
		dict.setDictPid("0144");//国家监察对象
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("supervision",x);
		dict.setDictPid("0145");//其他纪检监察机关立案后移送
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("otherTransfer",x);
		dict.setDictPid("0146");//职务违法犯罪行为
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("legalAct1",x);
		dict.setDictPid("014601");//贪污贿赂
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawTWHL",x);
		dict.setDictPid("014602");//渎职侵权
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawDZQQ",x);
		dict.setDictPid("014603");//其他职务违法犯罪行为
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawQTFZ",x);
		dict.setDictPid("01460103");//受贿
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawSH",x);
		dict.setDictPid("01460104");//行贿
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawXH",x);
		dict.setDictPid("0147");//其他违法犯罪行为
		x = dictService.findDictByDictPid(dict);
		servletContext.setAttribute("legalAct2",x);
		//涉法行为
		servletContext.setAttribute("fylegalAct",x);
		dict.setDictPid("014701");//侵犯财产
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawQFCC",x);
		dict.setDictPid("014702");//破坏社会主义市场经济秩序
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawPHZX",x);
		dict.setDictPid("014703");//妨害社会管理秩序
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("lawFHZX",x);
		dict.setDictPid("0149");//公检法等机关处理内容
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("handlingContent",x);
		dict.setDictPid("014901");//公检法等机关处理内容1
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("handlingContentJT1",x);
		dict.setDictPid("014902");//公检法等机关处理内容2
		x=dictService.findDictByDictPid(dict);
		servletContext.setAttribute("handlingContentJT2",x);
		 //webapp的路径
		Param.webapp=servletContext.getRealPath("webapp");
		//获取本机ip
		InetAddress address;
		try {
			 address = InetAddress.getLocalHost();
			 String ip=address .getHostAddress().toString(); 
			 servletContext.setAttribute("ip", ip);
			 Param.ip=ip;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    		
		}
	   
	
	   
    
}
