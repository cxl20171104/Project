package com.alphasta.commons.utils;

import java.io.*;
import java.util.Date;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.*;

/**
 * 图片压缩处理
 * 
 * @author sjb
 */
public class ImgCompress {

	private Image img;
	private int width;
	private int height;
	private String subBath;

	public ImgCompress(String fileName) throws IOException {
		File file = new File(fileName);// 读入文件
		subBath = fileName.replace(".", "_sub.");
		img = ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	/**
	 * 按照宽度还是高度进行压缩
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 */
	public void resizeFix(int w, int h) throws IOException {
		if (width / height > w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 
	 * @param w
	 *            int 新宽度
	 */
	public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 */
	public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 */
	public void resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(subBath);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
	}

	public void resize(int w, int h, String path) throws IOException {
		// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		File destFile = new File(path);
		FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
		// 可以正常实现bmp、png、gif转jpg
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); // JPEG编码
		out.close();
	}

	public static String createThumbPic(File file, String realPath)
			throws IOException {
		// java.io.File pFile = new java.io.File(file);

		String path = realPath + "/" + file.getName().replace(".", "_sub.");
		String fileName = file.getName();
		java.io.File fo = new java.io.File(path); // 将要转换出的小图文件
		int nw = 100;
		AffineTransform transform = new AffineTransform();
		BufferedImage buffer = ImageIO.read(file); // 读取图片
		int width = buffer.getWidth();
		int height = buffer.getHeight();
		int nh = (nw * height) / width;
		double sx = (double) nw / width;
		double sy = (double) nh / height;
		transform.setToScale(sx, sy);
		AffineTransformOp ato = new AffineTransformOp(transform, null);
		BufferedImage bid = new BufferedImage(nw, nh,
				BufferedImage.TYPE_3BYTE_BGR);
		ato.filter(buffer, bid);
		String type = fileName.substring(fileName.indexOf('.') + 1,
				fileName.length());
		// System.out.println("type = " + type);
		ImageIO.write(bid, type, fo);
		return fo.getName();
	}
}
