package com.alphasta.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.model.SysParam;
import com.alphasta.service.SysParamService;

/**
 * @description：日志管理
 * @author：liyunhao
 * @date：2015/10/30 18:06
 */
@Controller
@RequestMapping("/sysParam")
public class SysParamController extends BaseController{

    @Autowired
    private SysParamService sysParamService;
    private static final String SYSPARAM = "/admin/sysParam";

    /**
	 * 系统参数页
	 *
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(SYSPARAM);
		return result;
	}
	
	/**
	 * 更新采样排班信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Object edit(SysParam sysParam) {
		sysParamService.update(sysParam);
		return renderSuccess("更新成功");
	}
	
	/**
	 * 查询采样排班信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/findSysParam")
	@ResponseBody
	public Object findSysParam(SysParam sysParam) {
		List<SysParam> list = sysParamService.findSysParam(sysParam);
		return renderSuccess(list);
	}

}
