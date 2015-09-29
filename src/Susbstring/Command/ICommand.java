package Susbstring.Command;

import java.util.List;
import Susbstring.Operand;

public interface ICommand {

	public List<Operand> Execute(Operand a, Operand b);

	List<Operand> Execute(List<Operand> a, Operand b);
	
	public List<Operand> UnExecute(Operand a, Operand b);
}
