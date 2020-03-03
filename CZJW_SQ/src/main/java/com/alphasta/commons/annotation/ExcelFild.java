package com.alphasta.commons.annotation;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
 *  
 * @Description: excel导出注解类 
 * @author sjb
 * @date 2017年4月15日
 */  
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })  
@Retention(RetentionPolicy.RUNTIME)  
public @interface ExcelFild   
{  
    //导出字段在excel中的名字  
    String title();  
} 
