import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class TextField extends JTextField {

    public TextField(String enterTaskName, int i) {
        super(enterTaskName,i);

    }

    @Override
    public void setText(String t) {
        super.setText(t);
    }
}
