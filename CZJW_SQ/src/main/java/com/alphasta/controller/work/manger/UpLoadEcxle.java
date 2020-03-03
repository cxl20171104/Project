package com.alphasta.controller.work.manger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.model.Zjk;
import com.alphasta.service.ZjkService;
@Controller
@RequestMapping("/excle")
public class UpLoadEcxle extends BaseController{
	
	@Autowired
	private ZjkService zjkService;

	private static final String ZJK = "/admin/excleInfo";
	private static final String ZJKLIST = "/admin/zjklist";
	private static final int version2003 = 2003;
    private static final int version2007 = 2007;
    private static int version = version2003;
    private static Workbook wb = null;
	
	@RequestMapping(value = "/excle", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(ZJK);
		return result;
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ModelAndView read(@RequestParam(value="filePath") String filePath)
            throws Exception {
        try {
        	POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
        	if (filePath.endsWith(".xls"))
                version = version2003;
            else if (filePath.endsWith(".xlsx"))
                version = version2007;
        	InputStream stream=null;
        	if (version == version2003) {
        		stream = new FileInputStream(filePath);
        		wb = (Workbook) new HSSFWorkbook(stream);
        	} else if (version == version2007) {
        		wb = (Workbook) new XSSFWorkbook(filePath);
        	}
            System.out.println("工作表个数 :" + wb.getNumberOfSheets());
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                // 创建工作表
            	Sheet sheet = wb.getSheetAt(i);
                int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
                if (rows > 0) {
                    sheet.getMargin(HSSFSheet.TopMargin);
                    for (int r = 0; r < rows; r++) { // 行循环
                        Row row = sheet.getRow(r);
                        if (row != null && r != 0 && r != 1) {// 不取第一行
                            int cells = row.getLastCellNum();// 获得列数
                            // 定义集合datas用于存Excel中一个行的数据
                            Vector datas = new Vector();
                            for (short c = 0; c < cells; c++) { // 列循环
                                Cell cell = row.getCell(c);
                                if (cell != null && c != 0) {
                                    String value = getValue(cell);
                                    datas.add(value);
                                }
                            }
                            System.out.println("<<<<<<<<"+datas);
                            String name = datas.get(0).toString(); //姓名	   NAME
							String age = datas.get(1).toString();  //年龄	AGE
							String over_unit = datas.get(2).toString();  //过度单位   	OVER_UNIT
							String education = datas.get(3).toString();   //学历   EDUCATION
							String major = datas.get(4).toString();    //专业   MAJOR
							String place = datas.get(5).toString();     //籍贯    PLACE
							String working_time = datas.get(6).toString();   //从业时间    WORKING_TIME
							String party_member = datas.get(7).toString();    //是否党员    PARTY_MEMBER
							String graduated = datas.get(8).toString();   //毕业院校   GRADUATED
							String specialty = datas.get(9).toString();   //特长   SPECIALTY
							String experience = datas.get(10).toString();   //个人经历   EXPERIENCE
							
							
							Zjk zjk = new Zjk();
							zjk.setName(name);
							zjk.setOver_unit(over_unit);
							zjk.setEducation(education);
							zjk.setMajor(major);
							zjk.setSpecialty(specialty);
							zjk.setPlace(place);
							zjk.setWorking_time(working_time);
							zjk.setParty_member(party_member);
							zjk.setGraduated(graduated);
							zjk.setExperience(experience);
							zjkService.insertData(zjk);
                        }
                    }
                } else {
                    break;
                }
            }
 
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("readExcel 方法异常：" + ex);
            throw ex;
        }
        ModelAndView result = new ModelAndView(ZJKLIST);
		return result;
    }
	
	public String getValue(Cell cell) throws ParseException {
        String value = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                // 如果是date类型则 ，获取该cell的date值
                value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue())
                        .toString();
                java.util.Date date1 = new Date(value);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                value = format.format(date1);
            } else {// 纯数字
            	int a = (int) cell.getNumericCellValue();
                value = String.valueOf(a);
            }
            break;
        /* 此行表示单元格的内容为string类型 */
        case HSSFCell.CELL_TYPE_STRING: // 字符串型
            value = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_FORMULA:// 公式型
            // 读公式计算值
            value = String.valueOf(cell.getNumericCellValue());
            if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                value = cell.getStringCellValue().toString();
            }
            cell.getCellFormula();
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
            value = " " + cell.getBooleanCellValue();
            break;
        /* 此行表示该单元格值为空 */
        case HSSFCell.CELL_TYPE_BLANK: // 空值
            value = "";
            break;
        case HSSFCell.CELL_TYPE_ERROR: // 故障
            value = "";
            break;
        default:
            value = cell.getStringCellValue().toString();
        }
        return value;
    }
}
