package com.alphasta.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocPrintJob; 
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JFileChooser;

public class PrintDemo {
	public static void main(String[] args) {  
/*        JFileChooser fileChooser = new JFileChooser(); // ������ӡ��ҵ  
        File file = new File("D:/c.doc"); // ��ȡѡ����ļ�  
        // ������ӡ�������Լ�  
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();  
        // ���ô�ӡ��ʽ����Ϊδȷ�����ͣ�����ѡ��autosense  
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  
        // ��λĬ�ϵĴ�ӡ����  
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();  
        InputStream fis = null;  
        try {  
            DocPrintJob job = defaultService.createPrintJob(); // ������ӡ��ҵ  
            fis = new FileInputStream(file); // ������ӡ���ļ���  
            DocAttributeSet das = new HashDocAttributeSet();  
            Doc doc = new SimpleDoc(fis, flavor, das);  
            job.print(doc, pras);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (fis != null) {  
                try {  
                    fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  */
    }  
	
	public  static void  printDoc(File file){
		JFileChooser fileChooser = new JFileChooser();
       
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();  
       
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  
         
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();  
        InputStream fis = null;  
        try {  
            DocPrintJob job = defaultService.createPrintJob(); 
            fis = new FileInputStream(file);
            DocAttributeSet das = new HashDocAttributeSet();  
            Doc doc = new SimpleDoc(fis, flavor, das);  
            job.print(doc, pras);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (fis != null) {  
                try {  
                    fis.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
}
