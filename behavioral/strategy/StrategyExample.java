package behavioral.strategy;

// Strategy
interface Computer { int compute(int a, int b); }

// Implementation
class Adder implements Computer {
    public int compute(int a, int b) {
        System.out.println("Computing " + a + " + " + b + " with Adder.");
        return a + b;
    }
}

// Implementation
class Subtractor implements Computer {
    public int compute(int a, int b) {
        System.out.println("Computing " + a + " - " + b + " with Subtractor.");
        return a - b;
    }
}

// Implementation
class Multiplier implements Computer {
    public int compute(int a, int b) {
        System.out.println("Computing " + a + " * " + b + " with Multiplier.");
        return a * b;
    }
}

class Client {
    Computer computer;
    void computer(Computer c) { computer = c; }
    int compute(int a, int b) { return computer.compute(a, b); }
}

public class StrategyExample {
    public static void main(String[] args) {
        Client c = new Client();

        c.computer(new Adder());
        System.out.println(">>> " + c.compute(3, 4));

        c.computer(new Subtractor());
        System.out.println(">>> " + c.compute(3, 4));

        c.computer(new Multiplier());
        System.out.println(">>> " + c.compute(3, 4));
    }
}
