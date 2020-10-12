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
    public static void main(String[] args){
//        InMemoryRepository repo = new InMemoryRepository();
//
//        Car c1 = new Car(1, "Mercedes-Benz", 3000, 2012);
//        Car c2 = new Car(2, "Mercedes-Benz", 3000, 2012);
//        Car c3 = new Car(3, "Mercedes-Benz", 3000, 2012);
//        Car c4 = new Car(4, "Mercedes-Benz", 3000, 2012);
//
//        Motorcycle m1 = new Motorcycle(5, "Mercedes-Benz", 3000, 2012);
//        Motorcycle m2 = new Motorcycle(6, "Mercedes-Benz", 3000, 2012);
//        Motorcycle m3 = new Motorcycle(7, "Mercedes-Benz", 3000, 2012);
//        Motorcycle m4 = new Motorcycle(9, "Mercedes-Benz", 3000, 2012);
//
//        repo.add(c1);
//        repo.add(c2);
//        repo.add(c3);
//        repo.add(c4);
//
//        repo.add(m1);
//        repo.add(m2);
//        repo.add(m3);
//        repo.add(m4);
//
//        for( Vehicle v: repo.get()){
//            System.out.println(v.toString());
//        }


        InMemoryRepository repository = new InMemoryRepository();
        Service service = new Service(repository);
        UserInterface ui = new UserInterface(service);
        ui.run();
    }
}
