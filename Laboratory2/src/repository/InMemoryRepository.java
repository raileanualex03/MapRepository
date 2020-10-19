package repository;

import model.domain.Vehicle;
import exceptions.MemoryError;
import exceptions.NotFoundException;

public class InMemoryRepository implements Repository<Vehicle>{
    FixedSizeArray vehicles;

    public InMemoryRepository(){
        this.vehicles = new FixedSizeArray(100);
    }

    @Override
    public boolean add(Vehicle vehicle) throws NotFoundException, MemoryError {
        // this method verifies if there is another vehicle with this id, returns false if there is
        // if no vehicle were found with this id, the vehicle is added to the inMemoryList and the method returns true
        for (int index=0;index<this.vehicles.getSize();index++){
            if (vehicles.atPosition(index).getID() == vehicle.getID()) {
                return false;
            }
        }
        vehicles.add(vehicle);
        return true;
    }

    @Override
    public boolean remove(Vehicle vehicle) throws NotFoundException {
        // this method verifies if there is a vehicle with the specified id, returns true if found
        // returns false otherwise
        for (int index=0;index<this.vehicles.getSize();index++) {
            if (vehicles.atPosition(index).getID() == vehicle.getID()){
                this.vehicles.remove(vehicle);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Vehicle vehicle) throws NotFoundException, MemoryError {
        // because the order doesn't count, we can just simply remove the element with the vehicle's id
        // then add the new one with the same id
        boolean vehicleExisted = this.remove(vehicle);
        if (!vehicleExisted) {
            return false;
        }
        this.add(vehicle);
        return true;
    }

    @Override
    public FixedSizeArray get() {
        return this.vehicles;
    }



}
