package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheck {
	
	public boolean checkPhoneNo(String check) {
		
		String phoneNoPattern = "^[0-9]{8}$|^(\\+)[0-9]{10}$";
		
		Pattern pattern = Pattern.compile(phoneNoPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
	}
	
	public boolean checkDateString(String check) {
		
		//yyyy-mm-dd = year must have each char between 0-9, month must have first char between 0-1 and 
			//second char between 0-9 to be able to make fx june=07 and december=12, day follows the same principle
		String dateStringPattern = "^[0-9]{4}(\\-)([01][0-9])(\\-)([0-3][0-9])$";
		
		Pattern pattern = Pattern.compile(dateStringPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
	}
	
	//for crud employee/customer
	public boolean checkEmail(String check) {
		
		String emailPattern = "^([a-zA-Z0-9_.])+\\@([a-zA-Z0-9_.])+\\.([a-zA-Z])+$";
		
		Pattern pattern = Pattern.compile(emailPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
		
	}

}
