package model.domain;

public class Car extends Vehicle {

    public Car(int id, String brand, int repairingCost, int year) {
        super(id, brand, repairingCost, year);
    }

    @Override
    public String toString(){
        return "Car: " + this.getID() + " Brand: " + this.brand + " COST: " + this.repairingCost;
    }

    @Override
    public int getRepairingCost(){
        return this.repairingCost;
    }

}
