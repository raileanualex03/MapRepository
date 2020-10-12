package service;

import model.domain.Car;
import model.domain.Motorcycle;
import model.domain.Truck;
import model.domain.Vehicle;
import exceptions.MemoryError;
import exceptions.NotFoundException;
import repository.FixedSizeArray;
import repository.InMemoryRepository;


public class Service {
    InMemoryRepository repository;

    public Service(InMemoryRepository repo){
        this.repository = repo;
    }

    public boolean addElement(int typeCode, int id, int year, String brand, int repairingCost) throws NotFoundException, MemoryError {
        Vehicle vehicle = null;
        if ( typeCode == 0){
            vehicle = new Car(id, brand, repairingCost, year);
        }
        else if(typeCode==1){
            vehicle = new Truck(id, brand, repairingCost, year);
        }
        else if (typeCode == 2){
            vehicle = new Motorcycle(id, brand, repairingCost, year);
        }

        //TODO: make validations
        boolean result = this.repository.add(vehicle);
        if (!result){
            throw new NotFoundException("Vehicle found with this ID");
        }
        return true;
    }
    public boolean removeElement(int id) throws NotFoundException {
        Vehicle vehicle = null;
        FixedSizeArray vehicles = this.getElements();
        for (int index=0;index<vehicles.getSize();index++) {
            if (vehicles.atPosition(index).getID() == id){
                vehicle = vehicles.atPosition(index);
            }
        }
        if ( vehicle == null){
            throw new NotFoundException("No vehicle found with this ID");
        }

        boolean result = this.repository.remove(vehicle);
        if (!result){
            throw new NotFoundException("No vehicle found with this ID");
        }
        return true;
    }

    public FixedSizeArray getElements()
    {
        return this.repository.get();
    }

    public FixedSizeArray filterByCost(int cost) throws NotFoundException, MemoryError {
        FixedSizeArray vehicles;
        vehicles = this.getElements();
        FixedSizeArray requiredVehicles = new FixedSizeArray(200);
        for (int index = 0;index < vehicles.getSize(); index++) {
            if (vehicles.atPosition(index).getRepairingCost() < cost){
                requiredVehicles.add(vehicles.atPosition(index));
            }
        }
        return requiredVehicles;
    }

}
