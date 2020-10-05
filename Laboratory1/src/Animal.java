abstract class Animal {
    // This is an abstract base class
    String name;
    int age;
    public Animal(String n, int a){
        this.name = n;
        this.age = a;
    }

    abstract void bark();

    public String toString(){
        return "name: " + name + " age:" + Integer.toString(this.age);
    }
}
