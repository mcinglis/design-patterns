package behavioral.visitor;

interface CarVisitor {
    void visit(Wheel wheel);
    void visit(Engine engine);
    void visit(Body body);
    void visit(Car car);
}

interface CarVisitable { void accept(CarVisitor visitor); }

class Wheel implements CarVisitable {
    String name;
    Wheel(String name) { this.name = name; }
    String name() { return this.name; }
    public void accept(CarVisitor v) { v.visit(this); }
}

class Engine implements CarVisitable {
    public void accept(CarVisitor v) { v.visit(this); }
}

class Body implements CarVisitable {
    public void accept(CarVisitor v) { v.visit(this); }
}

class Car implements CarVisitable {
    CarVisitable[] components;
    Car() {
        components = new CarVisitable[] {
                new Wheel("front left"), new Wheel("front right"),
                new Wheel("back left"), new Wheel("back right"), new Body(),
                new Engine()
        };
    }
    public void accept(CarVisitor v) {
        for (CarVisitable c : components) c.accept(v);
        v.visit(this);
    }
}

class CarPrinterVisitor implements CarVisitor {
    void print(String s) { System.out.println(s); }
    public void visit(Wheel w) { print("Visiting " + w.name() + " wheel"); }
    public void visit(Engine e) { print("Visiting engine"); }
    public void visit(Body b) { print("Visiting body"); }
    public void visit(Car c) { print("Visiting car"); }
}

class CarStarterVisitor implements CarVisitor {
    void print(String s) { System.out.println(s); }
    public void visit(Wheel w) { print("Kicking my " + w.name() + " wheel"); }
    public void visit(Engine e) { print("Starting my engine"); }
    public void visit(Body b) { print("Moving my body"); }
    public void visit(Car c) { print("Starting my car"); }
}

public class CarExample {
    public static void main(String[] args) {
        Car car = new Car();
        car.accept(new CarPrinterVisitor());
        car.accept(new CarStarterVisitor());
    }
}
