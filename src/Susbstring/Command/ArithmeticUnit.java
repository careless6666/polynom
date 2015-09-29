package Susbstring.Command;

import Susbstring.Operand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 9/27/2015.
 */
public class ArithmeticUnit {
    private List<ICommand> commands = new ArrayList<ICommand>();

    public ArithmeticUnit(){
        commands.add(new AddCommand());
        commands.add(new SubCommand());
        commands.add(new DivCommand());
        commands.add(new MulCommand());
    }

    public List<Operand> Run(char operationCode, Operand a, Operand b){
        List<Operand> res = new ArrayList<Operand>();

        switch (operationCode)
        {
            case '+':
                res = commands.get(0).Execute(a,b);
                break;
            case '-':
                res = commands.get(1).Execute(a,b);
                break;
            case '*':
                res = commands.get(2).Execute(a,b);
                break;
            case '/':
                res = commands.get(3).Execute(a,b);
                break;
            default:
                throw new Error("Unrecognized operation");
        };
        return res;
    }

    public List<Operand> Run(char operationCode, List<Operand> a, Operand b){
        List<Operand> res = new ArrayList<Operand>();

        switch (operationCode)
        {
            case '+':
                commands.get(0).Execute(a,b);
                break;
            case '-':
                commands.get(1).Execute(a,b);
                break;
            case '*':
                commands.get(2).Execute(a,b);
                break;
            case '/':
                commands.get(3).Execute(a,b);
                break;
            default:
                throw new Error("Unrecognized operation");
        };
        return res;
    }
}



