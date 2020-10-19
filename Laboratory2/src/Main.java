import exceptions.MemoryError;
import exceptions.NotFoundException;
import model.domain.Car;
import model.domain.Motorcycle;
import repository.InMemoryRepository;
import service.Service;
import user_interface.UserInterface;

/*

3. Intr-un service se afla in reparatie mai multe
masini, camioane si motociclete. Sa se afiseze
toate autovehiculele ce au costul de reparatie
mai mare de 1000Ron.

 */

public class Main {

    public static void main(String[] args) throws NotFoundException, MemoryError {

        InMemoryRepository repository = new InMemoryRepository();

        /* ADDING A FEW ELEMENTS*/
        Car c1 = new Car(1, "Mercedes-Benz", 3000, 2012);
        Car c2 = new Car(2, "Mercedes-Benz", 3000, 2012);
        Car c3 = new Car(3, "Mercedes-Benz", 3000, 2012);
        Car c4 = new Car(4, "Mercedes-Benz", 3000, 2012);

        Motorcycle m1 = new Motorcycle(5, "Mercedes-Benz", 3000, 2012);
        Motorcycle m2 = new Motorcycle(6, "Mercedes-Benz", 3000, 2012);
        Motorcycle m3 = new Motorcycle(7, "Mercedes-Benz", 3000, 2012);
        Motorcycle m4 = new Motorcycle(9, "Mercedes-Benz", 3000, 2012);

        repository.add(c1);
        repository.add(c2);
        repository.add(c3);
        repository.add(c4);

        repository.add(m1);
        repository.add(m2);
        repository.add(m3);
        repository.add(m4);

        /* FINISHED ADDING ELEMENTS*/
        Service service = new Service(repository);
        UserInterface ui = new UserInterface(service);
        ui.run();
    }
}
