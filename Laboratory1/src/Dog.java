public class Dog extends Animal{
    // This class Dog extends the abstract class Animal
    // Has all the properties of the base class and overrides methods: ->bark()- which is an abstract one
    //                                                                 ->toString() - which is not abstract
    public Dog(String n, int a) {
        super(n, a);
    }

    @Override
    void bark() {
        System.out.println("Ham! Ham! I'm a dog.");
    }

    @Override
    public String toString(){
        return "Dog: " + this.name;
    }
}
