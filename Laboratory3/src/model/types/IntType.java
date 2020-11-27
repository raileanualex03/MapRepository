package model.types;

import model.var.IntValue;
import model.var.Value;

public class IntType implements Type{

    @Override
    public boolean equals(Object other) {
        return other instanceof IntType;
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    public String toString(){
        return "Integer";
    }
}
