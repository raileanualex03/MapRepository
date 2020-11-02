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

import java.util.Scanner;

import static java.lang.System.exit;


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

    public static void chooseProgram(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------PROGRAMS----------");
        System.out.println("1.int v; v=2;Print(v)");
        System.out.println("2.int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3.bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("Enter your choice: ");
        Integer input = scanner.nextInt();
        switch (input){
            case 1:
                Repository repository1 = new Repository(View.example1());
                Controller controller1 = new Controller(repository1);
                runSteps(controller1);
            case 2:
                Repository repository2 = new Repository(View.example2());
                Controller controller2 = new Controller(repository2);
                runSteps(controller2);
            case 3:
                Repository repository3 = new Repository(View.example3());
                Controller controller3 = new Controller(repository3);
                runSteps(controller3);

            default:
                System.out.println("Error: Bad input");

        }

    }
    public static void runSteps(Controller controller){
        System.out.println("---------- INFO------------");
        System.out.println("1.All steps");
        System.out.println("2.One step");
        System.out.println("Enter your choice : ");
        Scanner scanner = new Scanner(System.in);
        Integer choice = scanner.nextInt();
        switch (choice){
            case 1:
                try{
                    controller.allStep();
                    exit(0);
                } catch (MyException e) {
                    System.out.println(e.toString());
                }
            case 2:
                try{
                    controller.oneStep(controller.getMainProgram());
                }
                catch (MyException e){
                    System.out.println(e.toString());
                }
        }
    }

    public static void main(String[] args){

        chooseProgram();

        // CODE: 9586
    }
}
