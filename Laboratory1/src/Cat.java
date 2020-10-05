public class Cat extends Animal{
    // This class Cat extends the abstract class Animal
    // Has all the properties of the base class and overrides method: ->bark()- which is an abstract one

    public Cat(String n, int a) {
        super(n, a);
    }

    @Override
    void bark() {
        System.out.println("Meow! Meow! I'm a cat.");
    }
}
