package model.adt;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    ArrayDeque<T> stack;

    public MyStack(){
        stack = new ArrayDeque<>();
    }


    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public String toString(){
        return stack.toString();
    }
}
