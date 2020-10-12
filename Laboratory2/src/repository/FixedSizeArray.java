package repository;

import model.domain.Vehicle;
import exceptions.MemoryError;
import exceptions.NotFoundException;

public class FixedSizeArray {
    Vehicle[] vehicles;
    int size;
    public FixedSizeArray(int MAX_CAPACITY){
        vehicles = new Vehicle[MAX_CAPACITY];
        size = 0;
    }
    public void add(Vehicle vehicle) throws MemoryError {
        if (size == vehicles.length){
            throw new MemoryError("Limit of array reached!");
        }
        vehicles[size++] = vehicle;
    }

    public void remove(Vehicle vehicle) throws NotFoundException{
        for(int index=0;index<size;index++){
            if(vehicles[index].getID() == vehicle.getID()){
                vehicles[index] = vehicles[size];
                vehicles[size] = null;
                this.size -=1;
                return;
            }
        }
        throw new NotFoundException("Not found!");
    }

    public int getSize(){
        return this.size;
    }

    public Vehicle atPosition(int position) throws NotFoundException{
        if (position < 0 || position>this.size){
            throw new NotFoundException("Wrong position");
        }

        return this.vehicles[position];
    }


}
