package Susbstring.Command;

import java.util.ArrayList;
import java.util.List;

import Susbstring.Operand;

public class SubCommand implements ICommand {

	@Override
	public List<Operand> Execute(Operand a, Operand b) {
		List<Operand> res = new ArrayList<Operand>();
		if(a.exponent == b.exponent){
			Operand c = Sub(a, b);
			res.add(c);
		} else{
			res.add(a);
			res.add(b);
		}
		return res;
	}

	@Override
	public List<Operand> Execute(List<Operand> a, Operand b) {
		return null;
	}

	@Override
	public List<Operand> UnExecute(Operand a, Operand b) {
		// TODO Auto-generated method stub
		return null;
	}

	//variables with equals exponent
	Operand Sub(Operand a, Operand b){
		Operand c = new Operand();
		float calcFactor = (a.factor * (a.Sign ? 1 : -1)) + (b.factor * (b.Sign ? 1 : -1));
		boolean resSign = calcFactor > 0;
		c.Sign = resSign;
		c.factor = Math.abs(calcFactor);
		c.operand = a.operand;
		c.exponent = a.exponent;
		return c;
	}

}
