// *** Exercise 1 ***
/*
public class App {
    public static void main(String[] args){
        int sum = 0;
        for(String arg:args){
            sum = sum + Integer.parseInt(arg);
        }
        System.out.println("sum is: " + sum);
    }
}
*/

import java.util.ArrayList;

public class App {
    public static void main(String[] args){

        // ** Trying polymorphism, OOP and override in Java **

        ArrayList<Animal> animals = new ArrayList<Animal>();
        Dog dog1 = new Dog("Tina", 1);
        Cat cat1 = new Cat("Pisica", 3);
        animals.add(dog1);
        animals.add(cat1);

        for(Animal animal: animals) {
            animal.bark();
        }

        // ** Trying try-catch in Java **

        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[3]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        } finally {
            System.out.println("The 'try catch' is finished.");
        }
    }
}
