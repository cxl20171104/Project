package com.alphasta.commons.utils;

import java.io.IOException;

public class BatRun {
	
	    public static void runbat(String path) {
	        String cmd = "cmd /c start "+path;
	        try {
	            Process ps = Runtime.getRuntime().exec(cmd);
	            ps.waitFor();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	        catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("child thread donn");
	    }

	 public static void main(String[] args) {
		 String path="C:\\关闭.bat";
		 System.out.println("关闭数据库");
		 runbat(path);
		 try {
			Thread.currentThread().sleep(20000);
			System.out.println("启动数据库");
			String path2="D:\\启动.bat";
			 runbat(path2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
