package behavioral.observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

class EventSource extends Observable implements Runnable {
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while (true) {
                String response = br.readLine();
                setChanged();
                notifyObservers(response);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}

class ResponseHandler implements Observer {
    String response;
    public void update(Observable obj, Object arg) {
        response = (String) arg;
        System.out.println("Received response: " + response);
    }
}

public class UtilObservableDemo {
    public static void main(String[] args) {
        System.out.println("Enter text >> ");
        EventSource source = new EventSource();
        source.addObserver(new ResponseHandler());
        new Thread(source).start();
    }
}
