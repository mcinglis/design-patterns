package structural.bridge;

class Point {
    double x, y;
    Point(double x, double y) { this.x = x; this.y = y; }
    public String toString() { return String.format("(%f, %f)", x, y); }
}

// Implementor
interface CircleDrawer { void circle(Point p, double radius); }
interface SquareDrawer { void square(Point tl, double sides); }
interface Drawer extends CircleDrawer, SquareDrawer {}

// Concrete implementor
class LocalDrawer implements Drawer {
    public void circle(Point p, double r) {
        System.out.println("Drawing local circle at " + p + ", radius " + r);
    }
    public void square(Point tl, double s) {
        System.out.println("Drawing local square at " + tl + ", sides " + s);
    }
}

// Concrete implementor
class RemoteDrawer implements Drawer {
    public void circle(Point p, double r) {
        System.out.println("Drawing remote circle at " + p + ", radius " + r);
    }
    public void square(Point tl, double s) {
        System.out.println("Drawing remote square at " + tl + ", sides " + s);
    }
}

// Abstractions
interface Drawable { void draw(); }
interface Scalable { Scalable scale(double factor); }
interface Shape extends Drawable, Scalable { Shape scale(double factor); }

// Refined abstraction
class Circle implements Shape {
    CircleDrawer drawer; Point point; double radius;
    Circle(CircleDrawer d, Point p, double r) {
        drawer = d; point = p; radius = r;
    }
    public void draw() { drawer.circle(point, radius); }
    public Circle scale(double factor) {
        return new Circle(drawer, point, radius * factor);
    }
}

class Square implements Shape {
    SquareDrawer drawer; Point topLeft; double sides;
    Square(SquareDrawer d, Point tl, double s) {
        drawer = d; topLeft = tl; sides = s;
    }
    public void draw() { drawer.square(topLeft, sides); }
    public Square scale(double f) {
        return new Square(drawer, topLeft, sides * f);
    }
}

public class BridgeExample {
    public static void main(String[] args) {
        Drawer local = new LocalDrawer(), remote = new RemoteDrawer();
        Shape[] shapes = new Shape[] {
                new Circle(local, new Point(1, 2), 5),
                new Circle(remote, new Point(5, 7), 4),
                new Square(local, new Point(3, 4), 5),
                new Square(remote, new Point(-2, -4), 10)
        };
        for (Shape shape : shapes) {
            shape.draw();
            shape.scale(-1.2).draw();
        }
    }
}
