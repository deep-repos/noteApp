package noteApp.config.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import noteApp.config.bean.Note;

@Service
public class ValidateService {

	public boolean validateUsername(String username) {
		if(username==null || username.trim().length()==0) return false;
		username = username.trim();
		if(username.length()>50) {
			return false;
		}
		return true;
		
	}
	
	public boolean validatePassword(String password) {
		if(password==null || password.trim().length()==0 ||password.trim().length()<8) return false;
		password = password.trim();
			String word_regex="[\\w][\\w]*";
			String special_symbol_regex="[^\\w\\d\\s][^\\w\\d\\s]*";
			String digit_regex="[\\d][\\d]*";
			if( isRegexPresent(word_regex,password) &&
				   isRegexPresent(special_symbol_regex,password) &&
				   isRegexPresent(digit_regex,password))
				return true;
			else
				return false;
			
		 	
	}
	
	public boolean isRegexPresent(String regex,String str) {

	 	Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(str);
	    boolean matchFound = matcher.find();
	    return matchFound;
	    
	}
	
	public String getCurrentDate(String dateFormat) {
		try {
		  DateFormat formatter = new SimpleDateFormat(dateFormat);
	      Calendar obj = Calendar.getInstance();
	      String str = formatter.format(obj.getTime());
	      System.out.println("Current Date: "+str );
	      return str;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
