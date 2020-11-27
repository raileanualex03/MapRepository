package model.types;

import model.var.RefValue;
import model.var.Value;

public class RefType implements Type{
    Type inner;
    public RefType(Type inner){
        this.inner = inner;
    }

    public Type getInner(){
        return this.inner;
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof RefType;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

    public String toString(){
        return "Ref";
    }
}
