package model.var;

import model.types.Type;

public interface Value {
    Type getType();
    boolean equals(Value val);
    public Value deepCopy();
}
