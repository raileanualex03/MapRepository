package model.types;

public class BoolType implements Type{

    @Override
    public boolean equals(Type other) {
        return other instanceof BoolType;
    }

    public String toString(){
        return "Boolean";
    }
}
