package behavioral.visitor;

import java.lang.reflect.Method;
import java.util.Collection;

interface Visitor { void visit(Object o); }
interface Visitable { void accept(Visitor v); }

class VisitableString implements Visitable {
    String s;
    VisitableString(String s) { this.s = s; }
    public void accept(Visitor v) { v.visit(this.s); }
}

class VisitableDouble implements Visitable {
    Double d;
    VisitableDouble(Double d) { this.d = d; }
    public void accept(Visitor v) { v.visit(this.d); }
}

class PrinterVisitor implements Visitor {
    void print(String s) { System.out.println(s); }
    public void visit(Collection<Object> c) {
        for (Object o : c) {
            if (o instanceof Visitable) ((Visitable) o).accept(this);
        }
    }
    public void visit(String s) { print("'" + s + "'"); }
    public void visit(Double d) { print(d + "d"); }
    public void visit(Object o) {
        try {
            Method method = getMethod(o.getClass());
            method.invoke(this, new Object[] { o });
        } catch (ReflectiveOperationException e) {}
    }
    Method getMethod(Class c) {
        Method m = null;
        // Try the superclasses
        while (m == null && c != Object.class) {
            String name = c.getName();
            name = "visit" + name.substring(name.lastIndexOf('.') + 1);
            try {
                m = getClass().getMethod(name, new Class[] { c });
            } catch (ReflectiveOperationException e) {
                c = c.getSuperclass();
            }
        }
        // Try the interfaces.
        if (c == Object.class) {
            Class[] interfaces = c.getInterfaces();
            for (Class i : interfaces) {
                String name = i.getName();
                name = "visit" + name.substring(name.lastIndexOf('.') + 1);
                try {
                    m = getClass().getMethod(name, new Class[] { i });
                } catch (ReflectiveOperationException e) {}
            }
        }
        if (m == null) {
            try {
                m = getClass().getMethod("visitObject",
                        new Class[] { Object.class });
            } catch (ReflectiveOperationException e) {
                // can't happen
            }
        }
        return m;
    }
}

public class ReflectiveExample {
    public static void main(String[] args) {
    }
}
