package model.types;

public class IntType implements Type{

    @Override
    public boolean equals(Type other) {
        return other instanceof IntType;
    }

    public String toString(){
        return "Integer";
    }
}
