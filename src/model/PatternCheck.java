package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCheck {
	
public boolean checkSignedInteger(String check) {
		
		String signedIntegerPattern = "^(\\+|-)[0-9]+$";
		
		Pattern pattern = Pattern.compile(signedIntegerPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
		
		
	}
	
	public boolean checkAASequence(String check) {
		
		String aaPattern = "^(a|b)*(a){2}(a|b)*$";
		
		Pattern pattern = Pattern.compile(aaPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
	}
	
	public boolean checkIdentifierSequence(String check) {
		
		String identifierPattern = "^[a-zA-Z]([a-zA-Z0-9_])*$";
		
		Pattern pattern = Pattern.compile(identifierPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
	}
	
	public boolean checkCPRNo(String check) {
		
		String CPRNoPattern = "^([0-9]){6}\\-([0-9]){4}||([0-9]){10}$";
		
		Pattern pattern = Pattern.compile(CPRNoPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
	}
	
	public boolean checkDateTime(String check) {
		
		String dateTimePattern = "^([0-9]){4}\\-([0-9]){2}\\-([0-9]){2}\\ ([0-9]){2}\\:([0-9]){2}$";
				
		Pattern pattern = Pattern.compile(dateTimePattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
		
		
	}
	
	public boolean checkEmail(String check) {
		
		String emailPattern = "^([a-zA-Z0-9_.])+\\@([a-zA-Z0-9_.])+\\.([a-zA-Z])$";
		
		Pattern pattern = Pattern.compile(emailPattern);
		
		Matcher m = pattern.matcher(check);
		
		return m.find();
		
	}

}
