import javax.swing.*;

public class TextFieldAdopted extends JTextField {
    public TextFieldAdopted(String enterTaskName, int i) {
        super(enterTaskName,i);
    }

    @Override
    public String getText() {
        return super.getText().equalsIgnoreCase("")? "0" : super.getText();
    }
}
