package com.alphasta.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alphasta.commons.base.BaseController;
import com.alphasta.commons.result.Result;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.commons.utils.PageInfo;
import com.alphasta.model.Attachment;
import com.alphasta.model.Version;
import com.alphasta.service.AttachmentService;
import com.alphasta.service.VersionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/att")
public class AttachmentController extends BaseController {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private VersionService versionService;

	@RequestMapping("/toMain")
	public ModelAndView toMain() {
		return new ModelAndView("/work/attUpload");
	}

	@RequestMapping("/save")
	@ResponseBody
	public Object save(Attachment att, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			att.setType(0);
			if (att.getUrl().toLowerCase().endsWith(".jpg")
					|| att.getUrl().toLowerCase().endsWith(".jpeg")
					|| att.getUrl().toLowerCase().endsWith(".png"))
				att.setFiletype("1");
			else
				att.setFiletype("2");
			att.setId(GetIdUtil.getId());
			att.setOwner(getCurrentUser().getId().toString());
			att.setUploader(getUserId().toString());
			att.setUtime(new Date());
			attachmentService.save(att);
			return 1;
		} catch (Exception e) {
			return 2;
			// TODO Auto-generated catch block
		}
	}

	@RequestMapping("/toList")
	public ModelAndView toList() {

		return new ModelAndView("/work/attList");
	}

	@RequestMapping("/page")
	@ResponseBody
	public Object page(Attachment att, Integer page, Integer rows, String sort,
			String order) {
		Page<Attachment> pagehelper = PageHelper.startPage(page, rows);
		attachmentService.page(att);
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setRows(pagehelper.getResult());
		pageInfo.setTotal(Integer.valueOf(Long.valueOf(pagehelper.getTotal())
				.toString()));
		return pageInfo;
	}

	@RequestMapping("/download")
	public ModelAndView download(String url, HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getServletContext().getRealPath("files")
				+ url;
		// path是指欲下载的文件的路径。
		File file = new File(path);
		ModelAndView m=new ModelAndView("/work/msg");
		if(!file.exists()){
			m.addObject("msg","没有文件");
			return m;
		}else{
		try {
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		   m.addObject("msg","文件下载完成");
		   return m;
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String ids) {
		try {
			attachmentService.delete(ids);
			return renderSuccess("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return renderError("请重试");

		}
	}

	@RequestMapping("/updateApp")
	public ModelAndView updateApp() {
		ModelAndView result = new ModelAndView("/work/updateApp");
		return result;
	}

	@RequestMapping("/getNewestTime")
	@ResponseBody
	public Result getlastVersionTime(HttpServletRequest request) {
		Result result = new Result();
	
			Version ios = versionService.getLastVersion(".1");
			Version ad = versionService.getLastVersion("adr");
			Map<String,Object> map=new HashMap<String, Object>();
			if (ios!= null) {
				map.put("itime", ios.getCtime());
				map.put("ivernum", ios.getVerNum());
				String url = FileUtils.getBathUrl(request) + ios.getUrl();
				map.put("iurl", url);
			}
			if(ad!=null){
				
				map.put("atime", ad.getCtime());
				map.put("avernum", ad.getVerNum());
				String url = FileUtils.getBathUrl(request) + ad.getUrl();
				map.put("aurl", url);
				
			}
			
			if(ios==null&&ad==null){
				result.setSuccess(false);
				return result;
			}else{
				result.setObj(map);
				result.setSuccess(true);
				return result;
			}
			
		
	}

	@RequestMapping("/addVersion")
	@ResponseBody
	public Result addVersion(String url, String verNum) {
		Version version = new Version();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		
			if (url != null && !"".equals(url)) {
				version.setId(GetIdUtil.getId());
				Date d = new Date();
				version.setCtime(d);
				version.setUrl(url);
				// verNum=sdf.format(d);
				version.setVerNum(verNum);
			} else {
				return (Result) renderError("更新失败");
			}

			versionService.addVersion(version);
			return (Result) renderSuccess();

		
	}
}
