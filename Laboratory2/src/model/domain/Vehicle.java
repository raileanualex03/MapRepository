package model.domain;

public class Vehicle {
    int id;
    String brand;
    int repairingCost;
    int year;
    public Vehicle(int id, String brand, int repairingCost, int year){
        this.id = id;
        this.brand = brand;
        this.repairingCost = repairingCost;
        this.year = year;
    }
    public int getID(){
        return this.id;
    }

    public int getRepairingCost(){
        return this.repairingCost;
    }
    public String toString(){
        return "Vehicle: " + this.getID() + " brand: " + this.brand;
    }
}
