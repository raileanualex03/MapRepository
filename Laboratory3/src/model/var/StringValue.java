package model.var;

import model.types.StringType;
import model.types.Type;

public class StringValue implements Value {
    String string;

    public StringValue (String s){
        string = s;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Value val) {
        StringValue value = (StringValue) val;
        return value.string == this.string;
    }

    @Override
    public Value deepCopy() {
        return new StringValue(string);
    }

    public String getValue(){
        return this.string;
    }

    public String toString(){
        return "String: " + string;
    }
}
