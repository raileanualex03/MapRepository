package view;

import controller.Controller;
import exceptions.MyException;
import model.ProgramState;
import model.adt.*;
import model.exp.*;
import model.statement.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.types.StringType;
import model.var.BoolValue;
import model.var.IntValue;
import model.var.StringValue;
import model.var.Value;
import repository.Repository;
import model.statement.OpenRFileStatement;

import java.io.BufferedReader;
import java.io.IOException;

public class Interpreter {
    public static void main(String[] args) throws IOException, MyException {
        //test();
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

        IStatement st5 = example5();
        Repository repo5 = new Repository(st5, "test5.txt");
        Controller ctrl5 = new Controller(repo5);


        IStatement st6 = example6();
        Repository repo6 = new Repository(st6, "test6.txt");
        Controller ctrl6 = new Controller(repo6);


        IStatement st7 = example7();
        Repository repo7 = new Repository(st7, "test7.txt");
        Controller ctrl7 = new Controller(repo7);

        IStatement st8 = example8();
        Repository repo8 = new Repository(st8, "test8.txt");
        Controller ctrl8 = new Controller(repo8);


        IStatement st9 = example9();
        Repository repo9 = new Repository(st9, "test9.txt");
        Controller ctrl9 = new Controller(repo9);

        IStatement st10 = example10();
        Repository repo10 = new Repository(st10, "test10.txt");
        Controller ctrl10 = new Controller(repo10);

        IStatement st11 = example11();
        Repository repo11 = new Repository(st11, "test11.txt");
        Controller ctrl11 = new Controller(repo11);
        
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunCommand("1", st1.toString(), ctrl1));
        menu.addCommand(new RunCommand("2", st2.toString(), ctrl2));
        menu.addCommand(new RunCommand("3", st3.toString(), ctrl3));
        menu.addCommand(new RunCommand("4", st4.toString(), ctrl4));
        menu.addCommand(new RunCommand("5", st5.toString(), ctrl5));
        menu.addCommand(new RunCommand("6", st6.toString(), ctrl6));
        menu.addCommand(new RunCommand("7", st7.toString(), ctrl7));
        menu.addCommand(new RunCommand("8", st8.toString(), ctrl8));
        menu.addCommand(new RunCommand("9", st9.toString(), ctrl9));
        menu.addCommand(new RunCommand("10", st10.toString(), ctrl10));
        menu.addCommand(new RunCommand("11", st11.toString(), ctrl11));
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
                                        new IfStatement(new VarExpression("a"),
                                                new AssignStatement("v",
                                                        new ValueExpression(new IntValue(2))),
                                                            new AssignStatement("v", new ValueExpression(new IntValue(3)))),
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
                    new IfStatement(new RelationalExpression("!=", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(3))), new AssignStatement("a", new ValueExpression(new IntValue(10))), new AssignStatement("a", new ValueExpression(new IntValue(20)))),
                    new PrintStatement(new VarExpression("a"))
                )
        );

    }
    static IStatement example5(){
        return new CompStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompStatement(
                        new AssignStatement("varf", new ValueExpression(new StringValue("testFile.txt"))),
                        new CompStatement(
                                new OpenRFileStatement(new VarExpression("varf")),
                                new CompStatement(
                                        new VarDeclarationStatement("varc", new IntType()),
                                        new CompStatement(
                                                new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("varc")),
                                                        new CompStatement(
                                                                new ReadFileStatement(new VarExpression("varf"), "varc"),
                                                                new CompStatement(
                                                                        new PrintStatement(new VarExpression("varc")),
                                                                        new CloseRFileStatement(new VarExpression("varf"))
                                                                        )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }
    static IStatement example6(){
        return new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new WhileStatement(new RelationalExpression(">", new VarExpression("v"), new ValueExpression(new IntValue(0))),
                                            new CompStatement(
                                                    new PrintStatement(new VarExpression("v")),
                                                    new CompStatement(
                                                        new AssignStatement("v", new ArithmeticExpression(
                                                                2, new VarExpression("v"), new ValueExpression(new IntValue(1)))),
                                                        new PrintStatement(new VarExpression("v"))
                                                        ))
                                                    )

                )


        );
    }
    static IStatement example7(){
        return new CompStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement(new StringValue("v"), new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement(new StringValue("a"), new VarExpression("v")),
                                        new CompStatement(
                                                new PrintStatement(new HeapReadingExpression(new VarExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(1, new HeapReadingExpression(new HeapReadingExpression(new VarExpression("a"))), new ValueExpression(new IntValue(5))))
                                        )
                                )
                        )
                )
        );
    }

    static IStatement example8(){
        return new CompStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement(new StringValue("v"), new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new PrintStatement(new HeapReadingExpression(new VarExpression("v"))),
                                new CompStatement(
                                        new HeapWritingStatement(new StringValue("v"), new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(1, new HeapReadingExpression(new VarExpression("v")), new ValueExpression(new IntValue(5))))
                                 )
                )
        ));
    } 
    static IStatement example9(){
        return new CompStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement(new StringValue("v"), new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement(new StringValue("a"), new VarExpression("v")),
                                        new CompStatement(
                                                new HeapAllocationStatement(new StringValue("v"), new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VarExpression("a"))))
                                        )
                                )
                        )
                )
        );
    }
    static IStatement example10(){
        return new CompStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new CompStatement(
                        new HeapAllocationStatement(new StringValue("v"), new ValueExpression(new IntValue(20))),
                        new CompStatement(
                                new VarDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(
                                        new HeapAllocationStatement(new StringValue("a"), new VarExpression("v")),
                                        new CompStatement(
                                                new HeapAllocationStatement(new StringValue("v"), new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VarExpression("a"))))
                                        )
                                )
                        )
                )
        );
    }
    static IStatement example11(){
        return new CompStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompStatement(
                        new VarDeclarationStatement("a", new RefType(new IntType())),
                        new CompStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompStatement(
                                        new HeapAllocationStatement(new StringValue("a"), new ValueExpression(new IntValue(22))),
                                        new CompStatement(
                                                new ForkStatement(new CompStatement(
                                                                new HeapWritingStatement(new StringValue("a"), new ValueExpression(new IntValue(30))),
                                                                new CompStatement(
                                                                        new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompStatement(
                                                                                new PrintStatement(new VarExpression("v")),
                                                                                new PrintStatement(new HeapReadingExpression(new VarExpression("a")))
                                                                            )
                                                                        )
                                                                    )
                                                ),
                                                new CompStatement(
                                                        new PrintStatement(new VarExpression("v")),
                                                        new PrintStatement(new HeapReadingExpression(new VarExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static void test() throws IOException, MyException {
        MyStack<IStatement> exeStack = new MyStack<>();
        MyDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyList<Value> output = new MyList<>();
        MyDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyHeap heap = new MyHeap();

        IStatement openNonExistentFile = new OpenRFileStatement(new ValueExpression(new IntValue(70)));

        ProgramState programState = new ProgramState(exeStack, symbolTable, output, fileTable, heap, openNonExistentFile);

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
        Value valOfa = a.eval(programState.getSymTable(),heap );
        assert(((IntValue)valOfa).getValue() == 15);

        readSecondLine.execute(programState);
        a = new VarExpression("a");
        valOfa = a.eval(programState.getSymTable(), heap);
        assert(((IntValue)valOfa).getValue() == 50);

        closeFile.execute(programState);
        System.out.println("Tested reading from file\nEverything is fine...");


    }
}
