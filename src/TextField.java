import javax.swing.*;

public class TextField extends JTextField {
    private static Integer safetyValue; // value for strings where no parsed int value
    public TextField(String enterTaskName, int i) {
        super(enterTaskName,i);
    }

    public String getSafeTextForParseInt(){
        return super.getText().isEmpty() ? ""+safetyValue : super.getText();
    }

    public static void setSafetyValue(Integer setUpLink) {
        TextField.safetyValue = setUpLink;
    }
}
