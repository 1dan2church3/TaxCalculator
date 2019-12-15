package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberFormatter {
	
	public static String format(String number) {
		
		number = number.replace(".", "").replace("$", "");
		
		NumberFormat usFormat = NumberFormat.getCurrencyInstance();
		
		BigDecimal bd = new BigDecimal(Double.parseDouble(number));
	    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return usFormat.format(bd).toString();
	
//		number = number.replace(",", "");
//		
//		if(number.length() >= 4) {
//			
//			StringBuilder sb = new StringBuilder(number);
//			
//			if(number.length() % 3 == 0) {
//				for(int i = 3; i < number.length(); i+=4) {
//					number = sb.insert(i, ',').toString();
//				}
//			}
//			
//			else if(number.length() % 3 == 1 ) {
//				for(int i = 1; i < number.length(); i+=4) {
//					number = sb.insert(i, ',').toString();
//				}
//			}
//			
//			else if(number.length() % 3 == 2) {
//				for(int i = 2; i < number.length(); i+=4) {
//					number = sb.insert(i, ',').toString();
//				}
//			}
//		}
		
		//return number;
	}

}
