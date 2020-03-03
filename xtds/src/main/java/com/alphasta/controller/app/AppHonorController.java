package com.alphasta.controller.app;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Honor;
import com.alphasta.model.User;
import com.alphasta.service.HonorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
public class AppHonorController {
	
	@Autowired
    public HonorService honorService;
	private List<Honor> conutNum;

 /**
 * 
 * @param request
 * @param time
 * @param type
 * @param yearly  年度
 * @param level   1  市局   2.  区县
 * @param oid     区县时  所在支部或总支的Id
 * @param dictid  
 * @param sort
 * @param order
 * @return
 */
	@RequestMapping("/honorlist.json")
	@ResponseBody
	public Result getHonorlist(HttpServletRequest request,String type,
			String yearly,String level,String oid,String dictid){
		PageInfo pageinfo=new PageInfo();
		Result result=new Result();
		try {
			Map<String,Object> map=new HashMap<String, Object>();
		
		    if(dictid!=null)map.put("jb", dictid);  
		
			if(yearly!=null){
				map.put("yearly", yearly);
			}
			if(type!=null){
				map.put("type", type);
			}
			if(level!=null){
				map.put("level", level);	
			}
			//区县  个人
			if(level!=null&&"2".equals(level)){
				map.put("oid", oid);
			}
			pageinfo.setCondition(map);
			List<Honor> list = honorService.getByPageInfo(pageinfo);
			String bathUrl = FileUtils.getBathUrl(request);
			for(Honor h:list){
				if(h.getImgurl()!=null&&!"".equals(h.getImgurl())){
					h.setImgurl(bathUrl+h.getImgurl());
				}
			}
			result.setSuccess(true);
			result.setObj(list);
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("系统繁忙,重新登录");
		}
		return result;
	}
	/**
	 * 加载最近三年的荣誉统计
	 * @param type   1 个人 2 集体
	 * @param level  1 市局 2 区县
	 * @param oid    区县登录人 部门
	 * @return
	 */
	@RequestMapping("/lasthonor.json")
	@ResponseBody
	public Result getNearYearsHonors(String type,String level,String oid){
		Result result=new Result();
		Map<String,Object> map=new HashMap<String, Object>();
		
		if(type!=null){
			map.put("type", type);
		};
		if(level!=null){
			map.put("level", level);
		};
		//区县
		if(level!=null&&"2".equals(level)){
			map.put("oid", oid);
		};
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR)-3;
		map.put("stime", year);
		try {
			result.setObj(honorService.getConutNum(map));
		
			System.out.println();
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
		};
		return result;
	}
	/**
	 * 
	 * @param id
	 * @param type 1 个人  2 集体
	 * @return
	 */
	@RequestMapping("/honorbyid.json")
	@ResponseBody
    public  Result getHonorByid(String id,String type){
    	Result result=new Result();
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
    		map.put("id", id);
        	map.put("type", type);
        	Honor honor = honorService.getByid(map);
        	result.setSuccess(true);
        	result.setObj(honor);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.setMsg("系统繁忙,重新登录");
		}
    	return result;
    }

}
