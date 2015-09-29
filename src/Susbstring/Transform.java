package Susbstring;

import Susbstring.Command.ArithmeticUnit;

import java.util.*;

public class Transform {
	int counter = 0;
	List<Operand> OperandGroup;
	Map<String,List<Operand>> ListOperandGroup;
	
	public String Perform(String input){
        int counter  = 0;
        ListOperandGroup = new HashMap<String, List<Operand>>();
		while(true){
			if(input.contains("(") ||input.contains(")")){
                input = ReplaceVariable(input);
            } else{
                break;
            }

            if(counter++ > 1000){
                break;
            }
		}
		
		 
		
		return null;
	}
	
	String getNewId(){
		return "!Operand" + counter++ + "@";
	}
	
	String ReplaceVariable(String input){
		int left = input.lastIndexOf('(');
		String tmp = input.substring(left);
		int right = tmp.indexOf(')');
		String operand = input.substring(left + 1, left + right);

        List<Operand> r = parseOperands(operand);
        String id = getNewId();
        ListOperandGroup.put(id, r);

        char[] arr = input.toCharArray();
        if(left != 0 && arr[left-1] != '(' && arr[left-1] != '*' && arr[left-1] != '+' && arr[left-1] != '/'){
            id = "*" + id;
        }
        int end = left + operand.length()+2;
        if(end < input.length() && arr[end] != ')' && arr[end] != '*' && arr[end] != '+' && arr[end] != '/'){
            id = id + "*";
        }


        input = input.substring(0,left) + id + input.substring(left + operand.length() + 1, input.length() - 1);
		return input;
	}
	
	List<Operand> parseOperands(String input){
		List<Operand> res = new ArrayList<Operand>();
        //input
		//List<String> items = new ArrayList<String>();
        List<Character> operations = new ArrayList<Character>();
		//String item = "";
		//int counter = 0;
		char[] inputArray = input.toCharArray();
		String tmpOperand = "";
        //int lastSignIndex = -1;
        //int currentIndex = -1;

        char[] arrOperators = { '*', '/', '+', '-' };
        String inp = input; // Expecting this to be split on the "special" chars
        String regex = "(" + new String(arrOperators).replaceAll("(.)", "\\\\$1|").replaceAll("\\|$", ")"); // escape every char with \ and turn into "OR"
        System.out.println(regex); // For interest only
        String[] parts = inp.split(regex);
        System.out.println(Arrays.toString(parts));

        for (int i = 0; i < inputArray.length; i++) {
            if(inputArray[i] == '*' || inputArray[i] == '/' || inputArray[i] == '+' || inputArray[i] == '-'  )
                operations.add(inputArray[i]);
        }

        if(parts.length > operations.size())
            operations.add(0,'+');

        for (int i = 0; i < parts.length; i++) {
            tmpOperand = operations.get(i) + parts[i];

            if(checkExistOperand(tmpOperand)){
                res.addAll(getExistOperand(tmpOperand));
            } else{
                res.add(parseOperand(tmpOperand));
            }
        }

        if(res.size() > 1){
            ArithmeticUnit au = new ArithmeticUnit();
            if(res.size() == 2){
                res = au.Run(res.get(1).Sign ? '+' : '-', res.get(0), res.get(1));
            } else {
                List<Operand> tmpRes = au.Run(res.get(1).Sign ? '+' : '-', res.get(0), res.get(1));
                for (int i = 2; i < res.size(); i ++){
                    tmpRes = au.Run(res.get(i).Sign ? '+' : '-', tmpRes, res.get(i));
                }
            }
        }
		
		return res;
	}

	Operand parseOperand(String input){


        Operand oper = new Operand();
		//get factor
        char[] inputArr = input.toCharArray();
        String tmpVal = "";
        // check negative or positive
        if(inputArr[0] == '-' || inputArr[0] == '+')
        {
            oper.Sign = inputArr[0] == '+';
            oper.operation = inputArr[0] == '+' ? OperationType.Plus : OperationType.Minus;
            input = input.substring(1);
            inputArr = input.toCharArray();
        } else {
            oper.Sign = true;
        }

        if(inputArr[0] == '*' || inputArr[0] == '/')
        {
            oper.operation = inputArr[0] == '*' ? OperationType.Mul : OperationType.Div;
            input = input.substring(1);
            inputArr = input.toCharArray();
        } else {
            oper.Sign = true;
        }

        if(checkOperandNumber(input)){
            oper.operand = null;
            oper.factor= Float.parseFloat(input);
            oper.exponent = 1;
            return oper;
        }

        for (int i = 0; i < input.length(); i++){
            if((inputArr[i] < '0' || inputArr[i] > '9') && inputArr[i] != '.' && inputArr[i] != ','
                    && inputArr[i] != '-' && inputArr[i] != '+' ){
                oper.factor = 1;
                if(tmpVal.length() > 0) {
                    oper.factor = Float.parseFloat(tmpVal);
                    input = input.substring(tmpVal.length());
                }
                break;
            } else{
                tmpVal += inputArr[i];
            }
        }


        tmpVal = "";
        inputArr = input.toCharArray();
        //get variable name
        for (int i = 0; i < input.length(); i++){
            if(input.length() == 1)
                tmpVal = input;

            if(inputArr[i] == '^' || i == input.length() - 1 ){
                oper.operand = tmpVal;
                input = input.substring(tmpVal.length() - 1);
                break;
            } else{
                tmpVal += inputArr[i];
            }
        }

        tmpVal = "";
        //get exponent
        if(input.length() == 0 || input.length() == 1){
            oper.exponent = 1;
        } else{
            input = input.replace(oper.operand + "^","");
            inputArr = input.toCharArray();
            for (int i = 0; i < input.length(); i++) {
                if(i == input.length() - 1 ){
                    tmpVal += inputArr[i];
                    oper.exponent = Float.parseFloat(tmpVal);
                    break;
                } else{
                    tmpVal += inputArr[i];
                }
            }
        }

		return oper;
	}

    boolean checkOperandNumber(String input){
        boolean res = true;
        char[] inputArr = input.toCharArray();
        for (int i = 0; i < input.length(); i++){
            if((inputArr[i] < '0' || inputArr[i] > '9') && inputArr[i] != '.' && inputArr[i] != ',' ){
                res = false;
                break;
            }
        }
        return res;
    }

    List<Operand> getExistOperand(String input){
        char[] arr = input.toCharArray();
        if(arr[0] == '-' || arr[0] == '+' || arr[0] == '*' || arr[0] == '/') {
            input = input.substring(1);
        }
        for(Map.Entry<String, List<Operand>> entry : ListOperandGroup.entrySet()) {
            if(input.equals(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }

    boolean checkExistOperand(String input){
        char[] arr = input.toCharArray();
        if(arr[0] == '-' || arr[0] == '+' || arr[0] == '*' || arr[0] == '/') {
            input = input.substring(1);
        }
        for(Map.Entry<String, List<Operand>> entry : ListOperandGroup.entrySet()) {
            //HashMap value = entry.getValue();
            if(input.equals(entry.getKey())){
                return true;
            }
        }
        return false;
    }
}
