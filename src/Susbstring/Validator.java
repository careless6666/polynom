package Susbstring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public boolean Valid;
	public String Errors;
	
	public Validator Validate(String input) {
		Validator val = new Validator();
		val.Valid = true;
		
		if(!checkBrakets(input)){
			val.Errors = "Incorrect brakets count";
			val.Valid = false;
		}
		
		if(!invalidChars(input)){
			val.Errors = "Expression contains invalid chars";
			val.Valid = false;
		}
		
		if(!checkEmpty(input)){
			val.Errors = "Empty string";
			val.Valid = false;
		}
		
		return val;
	}
	
	boolean checkBrakets(String input){
		int left = (int) input.chars().filter(ch -> ch == '(').count();
		int right = (int) input.chars().filter(ch -> ch == ')').count();
		return left == right; 
	}
	
	boolean invalidChars(String input){
		Pattern pattern = Pattern.compile("[~#@%{}<>\\[\\]|\"\\_]");
		Matcher matcher = pattern.matcher(input);
	    return !matcher.find();
	}
	
	boolean checkEmpty(String input){
		return input.length() > 0;
	}
}


