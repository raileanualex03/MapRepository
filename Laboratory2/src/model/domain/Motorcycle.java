package model.domain;

public class Motorcycle extends Vehicle{

    public Motorcycle(int id, String brand, int repairingCost, int year) {
        super(id, brand, repairingCost, year);
    }

    @Override
    public String toString(){
        return "Motorcycle: " + this.getID() + " Brand: " + this.brand+ " COST: " + this.repairingCost;
    }

    @Override
    public int getRepairingCost(){
        return this.repairingCost;
    }
}
