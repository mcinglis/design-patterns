package behavioral.state;

interface Mouseable {
    void move(Point p);
    void down(Point p);
    void up(Point p);
}

class Point {}

abstract class Tool implements Mouseable {
    boolean down = false;
    public void down(Point p) { down = true; }
    public void up(Point p) { down = false; }
    public abstract void move(Point p);
}

class Pen extends Tool {
    Point last;
    public void move(Point p) {
        if (down) { /* draw a line from last to p */ }
        last = p;
    }
}

class Selector extends Tool {
    Point start = null;
    public void down(Point p) { super.down(p); start = p; }
    public void move(Point p) {
        if (down) { /* select the rectangle between start and p */ }
    }
}

class Cursor implements Mouseable {
    Tool tool = new Pen();
    public void move(Point p) { tool.move(p); }
    public void down(Point p) { tool.down(p); }
    public void up(Point p) { tool.up(p); }
    void tool(Tool tool) { this.tool = tool; }
}
