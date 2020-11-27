package model.types;

import model.var.BoolValue;
import model.var.Value;

public class BoolType implements Type{

    @Override
    public boolean equals(Object other) {
        return other instanceof BoolType;
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    public String toString(){
        return "Boolean";
    }
}
