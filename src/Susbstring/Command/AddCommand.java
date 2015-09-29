package Susbstring.Command;

import java.util.ArrayList;
import java.util.List;

import Susbstring.Operand;

public class AddCommand implements ICommand {

	@Override
	public List<Operand> Execute(Operand a, Operand b) {
		List<Operand> res = new ArrayList<Operand>();
		if(a.exponent == b.exponent){
			Operand c = Add(a,b);
			res.add(c);
		} else{
			res.add(a);
			res.add(b);
		}
		return res;
	}

	public List<Operand> Execute(List<Operand> a, Operand b) {
		List<Operand> res = new ArrayList<Operand>();
		List<Operand> tmpRes = new ArrayList<Operand>();
		for (int i = 0; i < a.size() ; i++) {
			if(a.get(i).exponent == b.exponent)
				tmpRes.add(a.get(i));
		}

		if(tmpRes.size() > 0){
			for (int i = 0; i < tmpRes.size(); i++) {
				b = Add(tmpRes.get(i), b);
				res.remove(tmpRes.get(i));
			}
			res.add(b);

		} else{
			a.add(b);
			res = a;
		}

		return res;
	}

	//variables with equals exponent
	Operand Add(Operand a, Operand b){
		Operand c = new Operand();
			if(a.Sign && b.Sign) {
				c.Sign = true;
				c.factor = a.factor + b.factor;
			} else{
				c.Sign = a.factor > b.factor && a.Sign;
				c.factor = a.Sign ? a.factor - b.factor : b.factor - a.factor;
			}
			c.operand = a.operand;
			c.exponent = a.exponent;
		return c;
	}

	@Override
	public List<Operand> UnExecute(Operand a, Operand b) {
		// TODO Auto-generated method stub
		return null;
	}

}
