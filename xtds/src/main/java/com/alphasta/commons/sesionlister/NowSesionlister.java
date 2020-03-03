package com.alphasta.commons.sesionlister;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class NowSesionlister implements HttpSessionListener {
    private static int num=0;
	@SuppressWarnings("static-access")
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
		   HttpSession session = se.getSession();
		 
		   Object source = se.getSource();
		   
		
		    this.num++;
		    
		    
                  System.out.println("=========>"+num);                 
                          
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
          System.out.println("bbbbbb\nbbbbbbbb\nbbbbbbbb\nbbbbbbbb\nbbbbbbbbbbb\nbbbbbbbbbbbbbbbbbbbbbbbbbb");
	}

}
