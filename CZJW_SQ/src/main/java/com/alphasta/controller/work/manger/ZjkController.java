package com.alphasta.controller.work.manger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.echartsUtil.Bxt;
import com.alphasta.commons.echartsUtil.TbUtil;
import com.alphasta.commons.echartsUtil.Zxt;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.DateUtil;
import com.alphasta.commons.utils.ExcelInfo;
import com.alphasta.commons.utils.ExcelUtils;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.commons.utils.WordUtils;
import com.alphasta.model.Zjk;
import com.alphasta.service.CaseClueService;
import com.alphasta.service.ZjkService;
import com.alphasta.service.impl.ZjkServiceImpl;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * @description：专家库列表查询
 */
@Controller
@RequestMapping("/zjk")
public class ZjkController extends BaseController {

	@Autowired
	private ZjkService zjkService;
	
	private static final String ZJK = "/admin/zjklist";
	private static final String INFO = "/admin/zjkInfo";
	private static final String TJ="/admin/zjkTj";
	private static final String SEARCHINFO = "/admin/zjkSearch";
	/**
	 * 专家库列表页
	 *
	 * @return
	 */
	@RequestMapping(value = "/zjklist", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(ZJK);
	/*	List<Zjk> list = zjkService.findZjk1();
		System.out.println(">>>>>>>>>>"+list);*/
		return result;
	}
	
	/**
	 * 查询专家库信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/findZjk")
	@ResponseBody
	public Object findZjk(Integer page, Integer rows, String sort, String order, Zjk zjk,
			
			
			String birthday_s,String birthday_e,
			String working_time_s,String working_time_e,
			String party_time_s,String party_time_e) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		System.out.println(zjk.getSex());
		if(zjk!=null){
			condition.put("name", zjk.getName());
			condition.put("sex", "0".equals(zjk.getSex())?null:zjk.getSex());
			condition.put("nation", zjk.getNation());
			condition.put("birthday_s", birthday_s);
			condition.put("birthday_e", birthday_e);
			condition.put("place", zjk.getPlace());
			condition.put("bir_place", zjk.getBir_place());
			condition.put("education", "0".equals(zjk.getEducation())?null:zjk.getEducation());
			condition.put("graduated", zjk.getGraduated());
			condition.put("major", zjk.getMajor());
			condition.put("zai_zhi", zjk.getZai_zhi());
			condition.put("is_health", "0".equals(zjk.getIs_health())?null:zjk.getIs_health());
			condition.put("working_time_s", working_time_s);
			condition.put("working_time_e", working_time_e);
			condition.put("over_unit", zjk.getOver_unit());
			condition.put("post", zjk.getPost());
			condition.put("rank", zjk.getRank());
			condition.put("party_member", "0".equals(zjk.getParty_member())?null:zjk.getParty_member());
			condition.put("party_time_s", party_time_s);
			condition.put("party_time_e", party_time_e);
			condition.put("tell", zjk.getTell());
			condition.put("quan_ri_zhi", zjk.getQuan_ri_zhi());
		}
		
		
		pageInfo.setCondition(condition);
		zjkService.findCompanyPageCondition(pageInfo);
		return renderSuccess(pageInfo);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(Long id) {
		ModelAndView result = new ModelAndView(INFO);
		Zjk zjk = new Zjk();
		if (id != null) {
			zjk = zjkService.findZjkById(id);
		}
		result.addObject("zjk", zjk);
		return result;
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Zjk zjk) {
		zjkService.insert(zjk);
		return renderSuccess("新增成功");
	}

	/**
	 * 更新专家信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Object edit(Zjk zjk) {
		zjkService.update(zjk);
		return renderSuccess("更新成功");
	}

	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String ids) {
		zjkService.deleteByIds(ids);
		return renderSuccess("删除成功");
	}

	/**
	 * 导入案件线索信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/importZjk")
	@ResponseBody
	public Object importZjk(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("fileData");
		try {
			Map<String, String> map = new HashMap<String, String>();
			ExcelInfo excelInfo = ExcelUtils.readExcel(file.getInputStream(),file.getOriginalFilename());
			map.put("name", "姓名");
			map.put("sex", "性别");
			map.put("title", "职称");
			map.put("birthday", "出生年月");
			map.put("nation", "民族");
			map.put("place", "籍贯");
			map.put("bir_place", "出生地");
			map.put("party_member", "是否党员");
			map.put("party_time", "入党时间");
			map.put("working_time", "参见工作时间");
			map.put("is_health", "健康状况");
			map.put("tell", "联系方式");
			map.put("over_unit", "工作单位");
			map.put("post", "职务");
			map.put("rank", "职级");
			map.put("quan_ri_zhi", "全日制教育");
			map.put("zai_zhi", "在职教育");
			map.put("graduated", "毕业院校");
			map.put("major", "专业");
			map.put("specialty", "办案专长");
			map.put("education", "学历");
			map.put("experience", "工作简历");
			map.put("ji_lv_shen_cha", "纪律审查方面的工作经历");
			map.put("jiang_cheng", "奖惩情况");
			map.put("remark", "备注");
			List<Object> list = excelInfo.dataToModel("Sheet1", 1, Zjk.class.getName(),map);
			zjkService.importJzk(list,this.getCurrentUser());
		} catch (IOException e) {
			e.printStackTrace();
			return renderError("解析excel失败");
		}
		catch (Exception e) {
			e.printStackTrace();
			return renderError(e.getMessage());
		}
		return renderSuccess("导入成功");
	}
	/**
	 * 统计分析
	 */
	@RequestMapping(value = "/tjfx")
	@ResponseBody
	public Object tjfx(Zjk zjk){
		Result result = new Result();
		System.out.println("请求收到");
		return result;
	}
	
	/**
	 * 导出
	 */
	@RequestMapping(value="/exportZjk")
	public void exportZjk(String ids,HttpServletRequest req,HttpServletResponse res){
		String[] idCount = ids.split(",");
		List<Zjk> list = new ArrayList<Zjk>();
		for(String id:idCount){
			Zjk zjk = zjkService.findZjkById(Long.valueOf(id));
			if(("1").equals(zjk.getDelete_status())){
				continue;
			}
			list.add(zjk);
		}
		Map<String,Object> dataMap =new HashMap<String,Object>();
		dataMap.put("zjkList", list);
		String floderPath=req.getSession().getServletContext().getRealPath("zjkExcel"); 
		System.out.println("floderPath:"+floderPath);
		
		File floder=new File(floderPath);
	       if(!floder.exists()){
	       	floder.mkdirs();
	       }
	       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
	       try {
			File exportMillCertificateWord = WordUtils.exportMillCertificateWord(dataMap,floderPath+File.separator+sdf.format(new Date())+".xls","zjkOut.xml");
			
			
			
			System.out.println(exportMillCertificateWord.getAbsolutePath());
			//下载
			FileUtils.downFile(res, exportMillCertificateWord.getAbsolutePath(), sdf.format(new Date())+".xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
	       
	       
	       
		   
		
		
		
		
	}
/*	//导出筛选查询页面
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/exportZjk", method = RequestMethod.POST)
	@ResponseBody
	public String  exportZjk(
	@RequestParam(value = "name", defaultValue = "", required = true) String name,
			@RequestParam(value = "delete_status", defaultValue = "", required = true) String delete_status,
			@RequestParam(value = "xl", defaultValue = "", required = true) String xl,
			@RequestParam(value = "zy", defaultValue = "", required = true) String zy,
			@RequestParam(value = "starttime", defaultValue = "", required = true) String starttime,
			@RequestParam(value = "endtime", defaultValue = "", required = true) String endtime,
			 String id,
	        HttpServletRequest request, HttpServletResponse response, ModelMap mode,
	        Integer page, Integer rows, String sort, String order) {
    	
    	PageInfo pageInfo = new PageInfo(1, 1000, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		condition.put("name", name);
		condition.put("delete_status", delete_status);
		condition.put("xl", xl);
		condition.put("zy", zy);
		condition.put("starttime", starttime);
		condition.put("endtime", endtime);
		pageInfo.setCondition(condition);
		List<Map<String,Object>> zjxx = zjkService.findZjk(pageInfo);
		
		try {
        	String realPath = request.getSession().getServletContext().getRealPath("");
          
            // 选择模板文件：
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(new File(realPath + "/print/xmxxmb.xls"))));
            //通过模板得到一个可写的SHEET
            HSSFSheet sheet=workbook.getSheet("纪律审查人才库");
             int j3=3;//控制全局的行数
             int rows3=zjxx.size();
             int startRow3=j3;
             //第四行
             for(int i=0;i<rows3;i++){//创建新的行和单元格
             	 HSSFRow sourceRow=null;
             	 HSSFRow targetRow = null;
                 HSSFCell sourceCell = null;
                 HSSFCell targetCell = null;
                 short m;
                 sourceRow = sheet.getRow(startRow3);
                 targetRow = sheet.createRow(startRow3 + 1);
                 targetRow.setHeight(sourceRow.getHeight());
                 for(m = sourceRow.getFirstCellNum(); m < sourceRow.getLastCellNum();m++){
                 	sourceCell = sourceRow.getCell(m);
                     targetCell = targetRow.createCell(m);
                     targetCell.setCellStyle(sourceCell.getCellStyle());
                     targetCell.setCellType(sourceCell.getCellType());
                 }
                 startRow3++;
             }
           for(Map one:zjxx){
            	 sheet.getRow(j3).getCell(1).setCellValue(null == one.get("NAME")?"":one.get("NAME").toString());
            	 sheet.getRow(j3).getCell(2).setCellValue(null == one.get("BIRTHDAY")?"":one.get("BIRTHDAY").toString());
            	 sheet.getRow(j3).getCell(3).setCellValue(null == one.get("OVER_UNIT")?"":one.get("OVER_UNIT").toString());
                 
                 j3++;
             }
           
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition","attachment; filename=" + URLEncoder.encode("纪律审查人才库.xls","utf-8"));
            workbook.write(response.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
	
	//废弃
	@RequestMapping(value = "/tj")
	@ResponseBody
	public  Object tj(String lx,String start,String end,String js){
		if(lx.equals("in")){
			ModelAndView result = new ModelAndView(TJ);
			return result;
			
		}
		
		//图例
		if(("0").equals(js)){
		js="months";	
		
		}
		if(("3").equals(js)){
			js="toCompany";
			
		}
		
		
		
		
		
		
		Map<String, Object> data = getData2( start, end, js);
		List<Long> seriesData=(List<Long>)data.get("count");
		List<String> legendData=(List<String>)data.get("legendData");
		Result result=new Result();
		//生成柱形图的数据
		if(lx.equals("0")){
			Zxt zxt = TbUtil.createZxt("人才库统计", seriesData,legendData);
			result.setObj(zxt);
			
		}
		//生成饼形图的数据
		if(lx.equals("1")){
	    	
		Bxt bxt=TbUtil.createBxt("人才库统计", seriesData, legendData);	
		result.setObj(bxt);
			
		}
		
		
		result.setSuccess(true);
		return result;
		
	}
       public Map<String,Object> getData2(String start,String end,String js){
		
		List<Map<String, Object>> data = zjkService.groupCaseByjsAndTime(js, start, end);
		List<Long>count=new ArrayList<Long>();
		List<String>legendData=new ArrayList<String>();;
		for(Map<String,Object> m:data){
			String len = (String)m.get(js);
			Long c=(Long)m.get("count");
			count.add(c);
			
			if(js.equals("toCompany")){
				String[]legendDataDetail=new String[]
						{"省纪委交办","中纪委交办","省委巡视组交办","上级交办其他","信访部门",
						"执纪监督部门","执纪审查部门","干部监督部门","巡视工作机构",
						"审计机关","行政执法机关","司法机关","普通案件其他"};	
				len=legendDataDetail[Integer.valueOf(len)-1];
				
			}
			legendData.add(len);
		}
		
		
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("legendData", legendData);
		map.put("count", count);
		return map;
		
	}
       
       @RequestMapping(value = "/zjkSearch", method = RequestMethod.GET)
   	public ModelAndView searchInfo() {
   		ModelAndView result = new ModelAndView(SEARCHINFO);
   		return result;
   	}
}
