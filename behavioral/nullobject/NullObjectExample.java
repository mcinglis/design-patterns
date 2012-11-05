package behavioral.nullobject;

interface Person {
    String name();
    void doSomething();
}

class Ada implements Person {
    public String name() { return "Ada Lovelace"; }
    public void doSomething() { System.out.println("Ada doing something..."); }
}

class Bob implements Person {
    public String name() { return "Bob Lee"; }
    public void doSomething() { System.out.println("Bob doing something..."); }
}

class NullPerson implements Person {
    public String name() { return "Mr. Null"; }
    public void doSomething() {}
}

public class NullObjectExample {
    static int counter = 0;
    static Person getPerson() {
        switch(counter++ % 3) {
        case 0: return new Ada();
        case 1: return new Bob();
        default: return new NullPerson();
        }
    }
    public static void main(String[] args) {
        getPerson().doSomething();
        getPerson().doSomething();
        getPerson().doSomething();
        // Not (x3):
        // Person p = getPerson();
        // if (p != null) p.doSomething();
        
        System.out.println(getPerson().name());
        System.out.println(getPerson().name());
        System.out.println(getPerson().name());
        // Not (x3):
        // if (getPerson() != null) System.out.println(getPerson().name());
        // else System.out.println("Mr. Null");
    }
}
