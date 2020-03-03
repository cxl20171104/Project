package com.alphasta.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
import com.alphasta.commons.utils.Config;
import com.alphasta.commons.utils.FileUtils;
import com.alphasta.model.Signature;
import com.alphasta.service.SignatureService;
import com.alphasta.service.SysParamService;

/**
 * 签名管理
 * 
 * @author LiYunhao
 * 
 */
@Controller
@RequestMapping("/signature")
public class SignatureController extends BaseController {

	@Autowired
	private SignatureService signatureService;
	@Autowired
	private SysParamService sysParamService;

	private static final String SIGNATURE = "/admin/signature";

	/**
	 * 签名管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView manager() {
		ModelAndView result = new ModelAndView(SIGNATURE);
		return result;
	}

	/**
	 * 保存签名信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(Signature signature, MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("fileData");
		String filePath = FileUtils.uploadFile(file,
				sysParamService.findSysParamValueByKey(Config.IMAGEPATH));
		signature.setFilePath(filePath);
		signatureService.save(signature);
		return renderSuccess("保存成功");
	}

	/**
	 * 查询签名信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findSignature")
	@ResponseBody
	public Object findSignature(Signature signature) {
		List<Signature> list = signatureService.findSignature(signature);
		return renderSuccess(list);
	}

	/**
	 * 删除签名信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(String ids) {
		signatureService.deleteByIds(ids);
		return renderSuccess("删除成功");
	}

	/**
	 * 获取图片数据
	 * 
	 * @param tplj
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getImageData")
	public void getImageData(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FileInputStream fileIs = null;
		try {
			fileIs = new FileInputStream(request.getParameter("filePath"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		int i = fileIs.available(); // 得到文件大小
		byte data[] = new byte[i];
		fileIs.read(data); // 读数据
		response.setContentType("image/*"); // 设置返回的文件类型
		OutputStream outStream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
		outStream.write(data); // 输出数据
		outStream.flush();
		outStream.close();
		fileIs.close();
	}

}
