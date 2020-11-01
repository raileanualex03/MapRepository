package model.var;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{

    int value;

    public IntValue(int val){
        value = val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Value val) {
        IntValue v = (IntValue) val;
        return v.value == this.value;
    }
    public int getValue(){
        return value;
    }

    public String toString(){
        return "IntValue: " + value;
    }

}
