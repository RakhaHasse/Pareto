import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField {

    String placeHolder;

    Document[] states = {this.getDocument(), /* for input */
            this.getDocument()}; /* for placeholder */


    public TextField(String placeholder, int i) {
        super("",i);
        placeHolder = placeholder;

        TextField textField = this;
        // Add FocusListener, for show placeholder
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeHolder)) {
                    textField.setText("");
                    if (!textField.states[0].equals(textField.states[1]))
                        textField.setDocument(states[0]);
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    if (!textField.states[0].equals(textField.states[1]))
                        textField.setDocument(textField.states[1]);
                    textField.setText(placeHolder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }

    @Override
    public void setText(String t) {
            super.setText(t);
    }

}
