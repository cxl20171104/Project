package com.alphasta.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alphasta.commons.annotation.AppLog;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.model.Version;
import com.alphasta.service.VersionService;

@Controller
public class APPVersionController {
	@Autowired
	private VersionService versionService;

	@RequestMapping("/version.json")
	@ResponseBody
	public Result getlastVersionTime(HttpServletRequest request,String wgtVer) {
		Result result = new Result();
		try {
			Version version = versionService.getLastVersion(wgtVer);
			if (version != null) {
				version=versionService.selectVersion(wgtVer);     //查出他该升的级
				if(version!=null){
					result.setObj(version.getVerNum());
					String url = FileUtils.getBathUrl(request) + version.getUrl();
					result.setMsg(url);
					result.setSuccess(true);
					return result;
				}
				
			}
			result.setSuccess(false);
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			return result;
		}
	};
/*	@RequestMapping("getNextVersion.json")
	@ResponseBody
	public Result getNextVersion(HttpServletRequest request,String vNum){
		Result result = new Result();
		Version version = versionService.getLastVersion();
		if(vNum==null||"".equals(vNum)){
			result.setSuccess(false);
			return result;
		}
		if(version!=null&&version.getVerNum().equals(vNum)){
			result.setSuccess(false);
		};
		if(version!=null&&!version.getVerNum().equals(vNum)){
			Version nextVersion = versionService.getNextVersion(vNum);
			if(nextVersion!=null){
				result.setSuccess(true);
				String url = FileUtils.getBathUrl(request) + nextVersion.getUrl();
				result.setObj(url);
			}else{
				result.setSuccess(false);
			}
		};
		return result;
	}*/

}
