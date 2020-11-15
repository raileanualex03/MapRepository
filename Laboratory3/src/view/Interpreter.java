package view;

import controller.Controller;
import exceptions.MyException;
import model.ProgramState;
import model.adt.*;
import model.exp.*;
import model.statement.*;
import model.types.BoolType;
import model.types.IntType;
import model.var.BoolValue;
import model.var.IntValue;
import model.var.StringValue;
import model.var.Value;
import repository.Repository;
import model.statement.OpenRFileStatement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Interpreter {
    public static void main(String[] args) throws IOException, MyException {
        test();
        //System.exit(0);


        IStatement st1 = example1();
        Repository repo1 = new Repository(st1, "test1.txt");
        Controller ctrl1 = new Controller(repo1);


        IStatement st2 = example2();
        Repository repo2 = new Repository(st2, "test2.txt");
        Controller ctrl2 = new Controller(repo2);


        IStatement st3 = example3();
        Repository repo3 = new Repository(st3, "test3.txt");
        Controller ctrl3 = new Controller(repo3);

        IStatement st4 = example4();
        Repository repo4 = new Repository(st4, "test4.txt");
        Controller ctrl4 = new Controller(repo4);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunCommand("1", st1.toString(), ctrl1));
        menu.addCommand(new RunCommand("2", st2.toString(), ctrl2));
        menu.addCommand(new RunCommand("3", st3.toString(), ctrl3));
        menu.addCommand(new RunCommand("4", st4.toString(), ctrl4));
        menu.show();
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
    static IStatement example4(){
        return new CompStatement(
                new VarDeclarationStatement("a", new IntType()),
                new CompStatement(
                    new IfStatement(new RelationalExpression("==", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(3))), new AssignStatement("a", new ValueExpression(new IntValue(10))), new AssignStatement("a", new ValueExpression(new IntValue(20)))),
                    new PrintStatement(new VarExpression("a"))
                )
        );
    }
    public static void test() throws IOException, MyException {
        MyStack<IStatement> exeStack = new MyStack<>();
        MyDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyList<Value> output = new MyList<>();
        MyDictionary<String, BufferedReader> fileTable = new MyDictionary<>();

        IStatement openNonExistentFile = new OpenRFileStatement(new ValueExpression(new IntValue(70)));

        ProgramState programState = new ProgramState(exeStack, symbolTable, output, fileTable, openNonExistentFile);

        System.out.println("Testing open file and read statements");

        StringValue path = new StringValue("testFile.txt");
        IStatement declareVariable = new VarDeclarationStatement("a", new IntType());
        IStatement openFile = new OpenRFileStatement(new ValueExpression(path));
        IStatement readFirstLine = new ReadFileStatement(new ValueExpression(path), "a");
        IStatement readSecondLine = new ReadFileStatement(new ValueExpression(path), "a");
        IStatement closeFile = new CloseRFileStatement(new ValueExpression(path));

        declareVariable.execute(programState);
        openFile.execute(programState);
        readFirstLine.execute(programState);
        VarExpression a = new VarExpression("a");
        Value valOfa = a.eval(programState.getSymTable());
        assert(((IntValue)valOfa).getValue() == 15);

        readSecondLine.execute(programState);
        a = new VarExpression("a");
        valOfa = a.eval(programState.getSymTable());
        assert(((IntValue)valOfa).getValue() == 50);

        closeFile.execute(programState);
        System.out.println("Tested reading from file\nEverything is fine...");


    }
}
