package structural.decorator;

// Implement as an interface if there is no common behavior between the base
// object and the abstract decorator.
abstract class Coffee {
	void print() { System.out.println(ingredients() + " costs " + cost()); }
	abstract double cost();
	abstract String ingredients();
}

class SimpleCoffee extends Coffee {
	double cost() { return 1; }
	String ingredients() { return "coffee"; }
}

class CoffeeDecorator extends Coffee {
	Coffee coffee;
	CoffeeDecorator(Coffee coffee) { this.coffee = coffee; }
	double cost() { return coffee.cost(); }
	String ingredients() { return coffee.ingredients(); }
}

class Milk extends CoffeeDecorator {
	Milk(Coffee coffee) { super(coffee); }
	double cost() { return super.cost() + 0.5; }
	String ingredients() { return super.ingredients() + ", milk"; }
}

class Froth extends CoffeeDecorator {
	Froth(Coffee coffee) { super(coffee); }
	double cost() { return super.cost() + 0.2; }
	String ingredients() { return super.ingredients() + ", froth"; }
}

public class CoffeeExample {
	public static void main(String[] args) {
		Coffee coffee = new SimpleCoffee();
		coffee.print();
		coffee = new Milk(coffee);
		coffee.print();
		coffee = new Froth(coffee);
		coffee.print();
		coffee = new Froth(coffee);
		coffee.print();
		new Froth(new Milk(new Froth(new SimpleCoffee()))).print();
	}
}
