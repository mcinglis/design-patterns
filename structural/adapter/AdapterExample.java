package structural.adapter;

interface Person { void talk(); void eat(); }

class Gentleman implements Person {
    public void talk() { System.out.println("Good day."); }
    public void eat() { System.out.println("Delicious. Thank you."); }
}

// A dog doesn't talk or eat, it barks and gnaws!
interface Dog { void bark(); void gnaw(); }

class Greyhound implements Dog {
    public void bark() { System.out.println("Woof!"); }
    public void gnaw() { System.out.println("gnaw, gnaw, ruf!"); }
}

class Chihuahua implements Dog {
    public void bark() { System.out.println("Yap yap!"); }
    public void gnaw() { System.out.println("rarrr, yap, rarr"); }
}

// But we have the technology - we can adapt!
class DogToPersonAdapter implements Person {
    Dog dog;
    DogToPersonAdapter(Dog d) { dog = d; }
    public void talk() { dog.bark(); }
    public void eat() { dog.gnaw(); }
}

public class AdapterExample {
    public static void main(String[] args) {
        Person person = new Gentleman();
        person.talk();
        person.eat();
        
        person = new DogToPersonAdapter(new Greyhound());
        person.talk();
        person.eat();
        
        person = new DogToPersonAdapter(new Chihuahua());
        person.talk();
        person.eat();
    }
}
