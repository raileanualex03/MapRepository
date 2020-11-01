package view;

import controller.Controller;
import exceptions.MyException;
import model.exp.ArithmeticExpression;
import model.exp.VarExpression;
import model.statement.*;
import model.types.BoolType;
import model.types.IntType;
import model.var.BoolValue;
import model.var.IntValue;
import model.exp.*;
import model.*;
import repository.Repository;


public class View {
    Controller controller;

    public View(Controller controller){
        this.controller = controller;
    }
    static IStatement example1(){
        return new CompStatement(new VarDeclarationStatement("v",new IntType()),
                new CompStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))), new PrintStatement(new VarExpression("v"))));
    }

    static IStatement example2(){
        return new CompStatement(
                new VarDeclarationStatement("a",new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("b", new IntType()),
                        new CompStatement(
                                new AssignStatement(
                                        "a",
                                        new ArithmeticExpression(
                                                1, new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression(3, new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)))
                                        )
                                ),
                                new CompStatement(
                                        new AssignStatement("b", new ArithmeticExpression(1, new VarExpression("a"), new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VarExpression("b"))
                                )
                        )
                )
        );
    }

    static IStatement example3(){
        return new CompStatement(
                new VarDeclarationStatement("a", new BoolType()),
                new CompStatement(
                        new VarDeclarationStatement("v", new IntType()),
                        new CompStatement(
                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompStatement(
                                        new IfStatement(new VarExpression("a"), new AssignStatement("v", new ValueExpression(new IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VarExpression("v"))
                                )
                        )
                )
        );
    }

    public static void main(String[] args){
        Repository repository = new Repository(View.example3());
        Controller controller = new Controller(repository);
        try{
            controller.allStep();
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        System.out.println(controller.getMainProgram().getOriginalProgram().toString());
    }
}
