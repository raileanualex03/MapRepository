package model.var;

import model.types.RefType;
import model.types.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public int getAddress(){
        return address;
    }

    @Override
    public boolean equals(Value val) {
        return false;
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType);
    }

    public String toString(){
        return "RefValue ( " + getAddress()  + ',' + getType() + ')';
    }

}
