package user_interface;

import exceptions.MemoryError;
import exceptions.NotFoundException;
import repository.FixedSizeArray;
import service.Service;

import java.util.Scanner;

public class UserInterface {
    Service service;

    public UserInterface(Service service){
        this.service = service;
    }
    public void showCommands(){
        System.out.println("**  Commands Available **");
        System.out.println("1.Add a car ( id, brand, year )");
        System.out.println("2.Add a truck ( id, brand, year )");
        System.out.println("3.Add a motorcycle ( id, brand, year )");
        System.out.println("4.Remove a vehicle ( id )");
        System.out.println("5.Show all the vehicles");
        System.out.println("6.Show all the vehicles with cost less than (cost)");
        System.out.println("0.Exit");
    }

    public void commandAdd(int typeCode, int id, String brand, int year, int repairingCost) throws NotFoundException, MemoryError {
        this.service.addElement(typeCode, id, year, brand, repairingCost);
        System.out.println("Successfully added!");
    }

    public void commandARemove( int id ) throws NotFoundException {
        this.service.removeElement( id);
        System.out.println("Successfully removed!");
    }



    public void commandShowAll() throws NotFoundException {
        System.out.println("These are all the vehicles:");
        FixedSizeArray vehicles = this.service.getElements();
        for (int index=0;index<this.service.getElements().getSize();index++){
            System.out.println(vehicles.atPosition(index).toString());
        }
    }
    public void run(){
        while(true){
            showCommands();
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();

            try {
                // code block
                switch (command) {
                    case 1 -> {
                        System.out.print("id=");int id0 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("brand=");String brand0 = scanner.nextLine();
                        // scanner.nextInt();
                        System.out.print("year=");int year0 = scanner.nextInt();
                        System.out.print("cost=");int repairingCost0 = scanner.nextInt();
                        this.commandAdd(0, id0, brand0, year0, repairingCost0);
                    }
                    case 2 -> {
                        System.out.print("id=");int id1 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("brand=");String brand1 = scanner.nextLine();
                        System.out.print("year=");int year1 = scanner.nextInt();
                        System.out.print("cost=");int repairingCost1 = scanner.nextInt();
                        this.commandAdd(1, id1, brand1, year1, repairingCost1);
                    }
                    case 3 -> {
                        System.out.print("id=");int id2 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("brand=");String brand2 = scanner.nextLine();
                        System.out.print("year=");int year2 = scanner.nextInt();
                        System.out.print("cost=");int repairingCost2 = scanner.nextInt();
                        this.commandAdd(2, id2, brand2, year2, repairingCost2);
                    }
                    case 4 -> {
                        int id3 = scanner.nextInt();
                        this.commandARemove(id3);
                    }
                    case 5 -> {
                        this.commandShowAll();
                    }
                    case 6 ->{
                        System.out.print("Cost=");
                        int cost = scanner.nextInt();
                        this.commandShowFiltered(cost);
                    }
                    case 0->{
                        System.out.println("Thanks for using this app!");
                        return;
                    }
                }
                    
                }
            catch(Exception e ){
                System.out.println(e.getMessage());

            }
            
        }
    }

    private void commandShowFiltered(int cost) throws NotFoundException, MemoryError {
        FixedSizeArray vehicles = this.service.filterByCost(cost);
        System.out.println("These are the vehicles with a repairing cost less than " + cost +":");
        for( int index = 0; index < vehicles.getSize(); index++){
            System.out.println(vehicles.atPosition(index).toString());
        }
    }

}
