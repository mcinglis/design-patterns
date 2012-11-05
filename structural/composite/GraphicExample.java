package structural.composite;

import java.util.List;
import java.util.LinkedList;

class Ellipse implements Drawable {
	public void draw() { System.out.println("ellipse"); }
}

class Group implements Drawable {
	List<Drawable> drawables = new LinkedList<>();
	public void draw() { for (Drawable d : drawables) d.draw(); }
	void add(Drawable d) { drawables.add(d); }
}

public class GraphicExample {
	public static void main(String[] args) {
		Ellipse e1 = new Ellipse(), e2 = new Ellipse(), e3 = new Ellipse();
		Group g = new Group(), s1 = new Group(), s2 = new Group();
		s1.add(e1);
		s1.add(e2);
		s2.add(e3);
		g.add(s1);
		g.add(s2);
		// Prints "ellipse" three times
		g.draw();
	}
}
