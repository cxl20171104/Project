package com.alphasta.controller.First_Page_Tj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.mapper.ProblemCluesMapper;
import com.alphasta.mapper.ProgressMapper;
import com.alphasta.mapper.PunishmentMapper;
import com.alphasta.model.Dict;
import com.alphasta.model.Param;
import com.alphasta.model.ProblemClues;
import com.alphasta.model.Progress;
import com.alphasta.model.Punishment;
@Controller
@RequestMapping("/firstPage")
public class First_Page_TjController extends BaseController{
   @RequestMapping("/getData")
   @ResponseBody
   public Object getData() {
	   Result result=new Result();
	   ServletContext ctx = request.getServletContext();
	   Map<String,Object>obj=new HashMap<String,Object>();
	   obj.put("total", ctx.getAttribute("total"));
	   obj.put("jsClues", ctx.getAttribute("jsClues"));
	   obj.put("fbClues", ctx.getAttribute("fbClues"));
	   obj.put("laClues", ctx.getAttribute("laClues"));
	   obj.put("cfClues", ctx.getAttribute("cfClues"));
	   obj.put("ysClues", ctx.getAttribute("ysClues"));
	   obj.put("bjClues", ctx.getAttribute("bjClues"));
	   obj.put("zx_name", ctx.getAttribute("zx_name"));
	   obj.put("zx_num", ctx.getAttribute("zx_num"));
	   obj.put("fieldClues", ctx.getAttribute("fieldClues"));
	   obj.put("cluesClues", ctx.getAttribute("cluesClues"));
	   obj.put("monthClues", ctx.getAttribute("monthClues"));
	   obj.put("problemLandClues", ctx.getAttribute("problemLandClues"));
	   obj.put("errorMessage", ctx.getAttribute("errorMessage"));
	   result.setObj(obj);
	   return result;
   }
   
}
