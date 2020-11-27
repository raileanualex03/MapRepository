package model.adt;

import model.var.Value;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements IHeap {
    HashMap<Integer, Value> elements;

    public MyHeap(){
        elements = new HashMap<>();
    }
    private int generateKey(){
        int counter = 1;
        while(this.elements.containsKey(counter))
            counter += 1;
        return counter;
    }

    @Override
    public int add(Value value) {
        int address = this.generateKey();
        elements.put(address, value);
        return address;
    }

    @Override
    public void remove(int address) {
        elements.remove(address);
    }

    @Override
    public Value search(int address) {
        return elements.get(address);
    }

    @Override
    public void update(int address, Value newValue) {
        elements.remove(address);
        elements.put(address, newValue);
    }

    @Override
    public boolean containsKey(int address) {
        return elements.containsKey(address);
    }

    public String toString(){
        return elements.toString();
    }

    public HashMap<Integer, Value> getContent(){
        return elements;
    }

    public void setContent(Map<Integer, Value> newElements){
        elements = new HashMap<>(newElements);
    }
}
