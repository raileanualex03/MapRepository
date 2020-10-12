package model.domain;

public class Truck extends Vehicle{


    public Truck(int id, String brand, int repairingCost, int year) {
        super(id, brand, repairingCost, year);
    }

    @Override
    public String toString(){
        return "Truck: " + this.getID() + " Brand: " + this.brand + " COST: " + this.repairingCost;
    }

    @Override
    public int getRepairingCost(){
        return this.repairingCost;
    }
}
