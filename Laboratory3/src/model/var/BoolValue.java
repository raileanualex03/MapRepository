package model.var;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{

    boolean value;

    public BoolValue(boolean val){
        value = val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Value val) {
        BoolValue v = (BoolValue) val;
        return v.value == this.value;
    }

    public String toString(){
        return "BoolValue: " + value;
    }

    public boolean getValue(){
        return value;
    }

}
