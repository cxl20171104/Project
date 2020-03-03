package com.alphasta.commons.utils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
public class CreatTwoDecemensionCodeImageUtil {
	private static String imgType = "jpg";//二维码图片格式
	private static int size = 6;//二维码尺寸
	private String codeType = "utf-8";
	//设置二维码中间图片的宽高
	private int imageWidth = 30;
	private int imageHeight = 30;
	
	/**
	 * 生成图片文件
	 * @param content
	 * @param imgPath
	 * @param imgType
	 * @param size
	 */
	public void encoderQRCode(String content, String imgPath, String imgType, int size){
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 生成图片
	 * @param content
	 * @param imgType
	 * @param size
	 * @return
	 */
	private BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
		Qrcode qrcodeHandler = new Qrcode();
		// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
		qrcodeHandler.setQrcodeErrorCorrect('M');
		qrcodeHandler.setQrcodeEncodeMode('B');
		// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
		qrcodeHandler.setQrcodeVersion(size);
		// 获得内容的字节数组，设置编码格式
		byte[] contentBytes = content.getBytes("utf-8");
		// 图片尺寸
		int imgSize = 67 + 12 * (size - 1);
		bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		// 设置背景颜色
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, imgSize, imgSize);

		// 设定图像颜色> BLACK
		gs.setColor(Color.BLACK);
		// 设置偏移量，不设置可能导致解析出错
		int pixoff = 2;
		// 输出内容> 二维码
		if (contentBytes.length > 0 && contentBytes.length < 800) {
		boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
		for (int i = 0; i < codeOut.length; i++) {
		for (int j = 0; j < codeOut.length; j++) {
		if (codeOut[j][i]) {
		gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
		}
		}
		}
		} else {
		throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");
		}
		gs.dispose();
		bufImg.flush();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return bufImg;
		}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args){
		CreatTwoDecemensionCodeImageUtil ctdciu = new CreatTwoDecemensionCodeImageUtil();
		ctdciu.encoderQRCode("http://cloud.mail.163.com/dfs/service/5.16.apk", "E:/aaa.jpg", imgType, size);
	}
}