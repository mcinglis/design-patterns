package structural.proxy;

class Image implements Drawable {
    String name = "<unnamed>";
    Image(String name) { this.name = name; load(); }
    void load() { System.out.println("Loading " + name); }
    public void draw() { System.out.println("Drawing " + name); }
}

class ProxyImage implements Drawable {
    Image image = null;
    String name = "<unnamed>";
    ProxyImage(String name) { this.name = name; }
    public void draw() {
        if (image == null) image = new Image(name);
        image.draw();
    }
}

public class ImageExample {
    public static void main(String[] args) {
        Drawable image1 = new ProxyImage("10mb_photo1"),
                image2 = new ProxyImage("10mb_photo2");
        image1.draw(); // "Loading" then "Drawing"
        image1.draw(); // "Drawing"
        image2.draw(); // "Loading" then "Drawing"
        image2.draw(); // "Drawing"
        image1.draw(); // "Drawing"
    }
}
