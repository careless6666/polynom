package Susbstring;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        System.out.println("Input expresion");
        //Scanner in = new Scanner(System.in);
        //String s = in.nextLine();

        String test = "(x - 5)(2x^3 + x(x^2 - 9))";
        //3x^4 - 15x^3 - 9x^2 + 45x
        
        test = test.replaceAll("\\s+","");
        test = test.replace(")(",")*(");
        /*
        char[] inputChars = test.toCharArray();
        String res = ""; 
        for (int i = 0; i < inputChars.length; i++) {
        	//last symbol
        	if(i == inputChars.length - 1)
        	{
        		res += inputChars[i];
            	continue;
        	}
        	
        	if(inputChars[i] == ')' && inputChars[i+1] == '(' ){
	        	res += inputChars[i] + "*";
	        	continue;
        	}
        	
        	if(inputChars[i] != '(' && inputChars[i] != '*' && inputChars[i] != '+' && inputChars[i] != '-' 
        			&& inputChars[i] != '/' && inputChars[i+1] == '('){
	        	res += inputChars[i] + "*";
	        	continue;
        	}
        	
        	if(inputChars[i] == ')' && inputChars[i+1] != '*' && inputChars[i+1] != '+' && inputChars[i+1] != '-' 
        			&& inputChars[i+1] != '/' && inputChars[i+1] != ')'){
	        	res += inputChars[i] + "*";
	        	continue;
        	}
        	res += inputChars[i];
        }
        */
        
        Validator val = new Validator();
        val = val.Validate(test);
        if(!val.Valid){
        	System.out.println(val.Errors);
        }

        Transform tf = new Transform();
        tf.Perform(test);
        
        System.out.println("Result");
    }
}
