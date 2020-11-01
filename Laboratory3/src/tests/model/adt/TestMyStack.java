package tests.model.adt;

import model.adt.MyStack;
import model.statement.IStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyStack {

    @Test
    public void EmptyStack__ReturnsTrue(){
        MyStack<IStatement> stack = new MyStack();
        assertTrue(stack.isEmpty(), "The stack is empty!");
    }

    @Test
    public void EmptyStack__AddFirstElement(){
        MyStack<Integer> stack = new MyStack();
        stack.push(3);
        assertFalse(stack.isEmpty(), "The stack is not empty!");
    }

    @Test
    public void Stack__PopLastElement(){
        MyStack<Integer> stack = new MyStack();
        stack.push(3);
        stack.pop();
        assertTrue(stack.isEmpty(), "The stack is empty!");
    }
}
