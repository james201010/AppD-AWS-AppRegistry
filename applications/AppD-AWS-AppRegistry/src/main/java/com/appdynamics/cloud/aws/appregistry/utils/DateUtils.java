/**
 * 
 */
package com.appdynamics.cloud.aws.appregistry.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author James Schneider
 *
 */
public class DateUtils {

//	public static void main(String[] args) {
//	
//		try {
//			
//			DateUtils.formatDateTime(new Date());
//			
//		} catch (Throwable ex) {
//			ex.printStackTrace();
//		}
//	}
	
	/**
	 * 
	 */
	public DateUtils() {
		
	}

	
	public static String formatDateTime(Date theDate) throws Throwable {
		
		String pattern = "MMM-dd-yyyy | h:mm:ss a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String strDate = simpleDateFormat.format(new Date());
		System.out.println(strDate);
		return strDate;
		
	}
}
