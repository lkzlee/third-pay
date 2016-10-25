package com.lkzlee.pay.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {
	public static void renderJsonText(HttpServletResponse response,String json) throws IOException{
		response.setHeader("Cache-Control", "no-cache");   
        response.setContentType("application/json");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out =null;
		try{
	        out = response.getWriter();  
	        out.write(json);
        }finally{
        	if(out!=null){
        		out.close();
        	}
        }
	}
	public static void renderXMLText(HttpServletResponse response,String xml) throws IOException{
		response.setHeader("Cache-Control", "no-cache");   
        response.setContentType("application/xml;charset=UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out = response.getWriter();  
        out.write(xml);
	}
}
