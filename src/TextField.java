import javax.swing.*;

public class TextField extends JTextField {
    public TextField(String enterTaskName, int i) {
        super(enterTaskName,i);
    }

    public String getSafeTextForParseInt(){
        return super.getText().isEmpty() ? "1" : super.getText();
    }
}
