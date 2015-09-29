package Susbstring;

import static Susbstring.OperationType.*;

public class Operand {
	
	public float factor;
	public String operand;
	public float exponent;
	public boolean Sign;
	public OperationType operation;

	public static char GetOperandType(OperationType type){
		switch (type){
			case Plus:
				return '+';
			case Minus:
				return '-';
			case Mul:
				return '*';
			case Div:
				return '/';
			default:
				return '+';
		}
	}
}

enum OperationType {Plus, Minus, Mul, Div};


