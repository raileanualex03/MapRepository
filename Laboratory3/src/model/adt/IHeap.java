package model.adt;

import model.var.Value;

public interface IHeap {
    public int add(Value value);
    public void remove(int address);
    public Value search(int address);
    public void update(int address, Value newValue);
    public boolean containsKey(int address);
}
