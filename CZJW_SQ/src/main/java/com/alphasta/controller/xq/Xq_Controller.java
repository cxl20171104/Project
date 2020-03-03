package com.alphasta.controller.xq;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.dataSource.DataSourceContextHolder;
import com.alphasta.mapper.OrganizationMapper;
import com.alphasta.model.Organization;
import com.alphasta.model.PageControl;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.ReflectedPerson;
import com.alphasta.model.ReflectingPerson;
import com.alphasta.model.Report;
import com.alphasta.service.PageService;

/**
 * 用于界面每个步骤打开界面时加载数据
 * @author chenxiaoliang
 *
 */
@RestController
@RequestMapping("/xq")
public class Xq_Controller extends BaseController{
     @Autowired
     PageService pageService;
     @Autowired
     OrganizationMapper organizationMapper;
	/**
	 * 
	 * 
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param problemClues
	 * @param startDate
	 * @param endDate
	 * @param pageName
	 * @return 主要业务集中在Mapper中
	 */
	
	@RequestMapping("/getListData")
	public Object getListData(Integer page, Integer rows, String sort, String order,
			//因为嵌套关系  又因为后台不是用form提交所以用三个实体类
			ProblemClues problemClues, 
			ReflectedPerson reflectedPerson,
			ReflectingPerson reflectingPerson,
			Report report,
			String startDate,
			String endDate,
			String pageName,
			String zddb,
			PageControl pageControl,
			String oId
			
			) {
		//一级查询条件
		Map<String, Object> condition = pageService.MakeMap(problemClues,reflectedPerson,reflectingPerson,report,startDate,endDate,page,rows,sort,order,zddb);
		PageInfo pageInof=null;
		//县区线索
		if(pageName.equals("xq")) {
			Organization organ = organizationMapper.findOrganizationById(Long.valueOf(oId));
			if(StringUtils.isNotEmpty(organ.getAddress())) {
				DataSourceContextHolder.setDbType(organ.getAddress());
				String dbType = DataSourceContextHolder.getDbType();
				
				//切换ip
				pageInof=pageService.xqService(condition);	
				pageInof.setWhichDataSource(dbType);
			}else {
				pageInof=new PageInfo();
				pageInof.setTotal(0);
				List<ProblemClues>li=new ArrayList<ProblemClues>();
				pageInof.setRows(li);
				
			}
			
			
		}
		String object =(String) condition.get("sql");
		if(object.equals("")) {
			DataSourceContextHolder.setDbType("master_dataSource");
		}
		
		pageInof.setSqlStore((String)condition.get("sql"));
		return pageInof;
	}
}
