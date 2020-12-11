package view;

import controller.Controller;
import exceptions.MyException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RunCommand extends Command{
    Controller controller;

    public RunCommand(String key, String desc, Controller c){
        super(key, desc);
        this.controller = c;
    }


    @Override
    public void execute() {
        try{
            controller.allStep();
        } catch (InterruptedException | MyException e) {
            e.printStackTrace();
        }
    }
}
