package behavioral.observer;

import java.util.LinkedList;
import java.util.List;

class Listener {
    String name;
    Listener(String n) { name = n; }
    void notify(String event) {
        System.out.println(name + " received event " + event);
    }
}

class Subject {
    List<Listener> listeners = new LinkedList<>();
    void register(Listener l) { listeners.add(l); }
    void notifyListeners(String event) {
        for (Listener l : listeners) l.notify(event);
    }
}

public class ObservableDemo {
    public static void main(String[] args) {
        Subject s = new Subject();
        s.register(new Listener("Ada"));
        s.register(new Listener("Bob"));
        s.notifyListeners("a muffin");
        s.register(new Listener("Carol"));
        s.notifyListeners("a mango");
    }
}
