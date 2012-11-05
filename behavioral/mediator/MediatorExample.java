package behavioral.mediator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Mediator {
    BookButton btnBook;
    SearchButton btnSearch;
    ViewButton btnView;
    DisplayLabel lblDisplay;
    void book() {
        btnBook.setEnabled(false);
        btnSearch.setEnabled(true);
        btnView.setEnabled(true);
        lblDisplay.setText("booking...");
    }
    void view() {
        btnView.setEnabled(false);
        btnBook.setEnabled(true);
        btnSearch.setEnabled(true);
        lblDisplay.setText("viewing...");
    }
    void search() {
        btnSearch.setEnabled(false);
        btnView.setEnabled(true);
        btnBook.setEnabled(true);
        lblDisplay.setText("searching...");
    }
}

abstract class Button extends JButton {
    private static final long serialVersionUID = 1715406001575108691L;
    Mediator mediator;
    Button(String text, ActionListener l, Mediator m) {
        super(text); addActionListener(l); mediator = m;
    }
    abstract void execute();
}

class BookButton extends Button {
    private static final long serialVersionUID = 7807264303834716365L;
    BookButton(ActionListener l, Mediator m) {
        super("Book", l, m); mediator.btnBook = this;
    }
    public void execute() { mediator.book(); }
}

class SearchButton extends Button {
    private static final long serialVersionUID = -1573132969577513123L;
    SearchButton(ActionListener l, Mediator m) {
        super("Search", l, m); mediator.btnSearch = this;
    }
    public void execute() { mediator.search(); }
}

class ViewButton extends Button {
    private static final long serialVersionUID = 8918760937782645284L;
    ViewButton(ActionListener l, Mediator m) {
        super("View", l, m); mediator.btnView = this;
    }
    public void execute() { mediator.view(); }
}

class DisplayLabel extends JLabel {
    private static final long serialVersionUID = 2159933015862185920L;
    Mediator mediator;
    DisplayLabel(Mediator m) {
        super(); mediator = m; mediator.lblDisplay = this;
    }
}

public class MediatorExample {
    public static void main(String[] args) {
        ActionListener l = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((Button) e.getSource()).execute();
            }
        };
        Mediator m = new Mediator();
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.add(new BookButton(l, m));
        p.add(new SearchButton(l, m));
        p.add(new ViewButton(l, m));
        f.getContentPane().add(new DisplayLabel(m), "North");
        f.getContentPane().add(p, "South");
        f.setSize(300, 80);
        f.setVisible(true);
    }
}
