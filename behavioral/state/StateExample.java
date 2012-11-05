package behavioral.state;

interface State { void name(Context context, String name); }

class StateA implements State {
    public void name(Context context, String name) {
        System.out.println("State A: " + name.toLowerCase());
        context.state(new StateB());
    }
}

class StateB implements State {
    int count = 0;
    public void name(Context context, String name) {
        System.out.println("State B: " + name.toUpperCase());
        if (count++ >= 1) context.state(new StateA());
    }
}

class Context {
    State state;
    Context() { state(new StateA()); }
    void state(State state) { this.state = state; }
    void write(String name) { state.name(this, name); }
}

public class StateExample {
    public static void main(String[] args) {
        Context sc = new Context();
        sc.write("One");
        sc.write("Two");
        sc.write("Three");
        sc.write("Four");
        sc.write("Five");
        sc.write("Six");
        sc.write("Seven");
    }
}
