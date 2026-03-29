import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextField extends JTextField {

    String placeholder;
    Document inputDocument, placeholderDocument;
    boolean placeholderIsOn = true;
    public TextField(String placeholder, int i) {
        super("",i);
        this.placeholder = placeholder;
        inputDocument = new PlainDocument();
        super.setDocument(inputDocument);
        placeholderDocument = getDocument();
        demoPlaceholder();
        TextField textField = this;
        // Add FocusListener, for show placeholder
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(TextField.this.placeholder)) {
                    textField.readyToInput();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.demoPlaceholder();
                }
            }
        });
    }

    public TextField(String placeholder, int i, Document inputDocument) {
        super("",i);
        this.placeholder = placeholder;
        this.inputDocument = inputDocument;
        super.setDocument(inputDocument);
        placeholderDocument = new PlainDocument();
        TextField textField = this;
        demoPlaceholder();
        // Add FocusListener, for show placeholder
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(TextField.this.placeholder)) {
                    textField.readyToInput();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.demoPlaceholder();
                }
            }
        });
    }

    @Override
    public void setText(String t) {
            super.setText(t);
    }

    public void readyToInput(){
        setText("");
        placeholderIsOn = false;
        if (!inputDocument.equals(placeholderDocument))
            setDocument(inputDocument);
        setForeground(Color.BLACK);
    }

    public  void demoPlaceholder (){
        placeholderIsOn = true;
        if (!inputDocument.equals(placeholderDocument))
            setDocument(placeholderDocument);
        setText(TextField.this.placeholder);
        setForeground(Color.GRAY);
    }
}
