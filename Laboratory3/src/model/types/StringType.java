package model.types;

import model.var.StringValue;
import model.var.Value;

public class StringType implements Type{

    @Override
    public boolean equals(Type other) {
        return (other instanceof StringType);
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    public String toString(){
        return "String";
    }
}
