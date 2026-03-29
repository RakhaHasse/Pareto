public class DigitsTextField extends TextField{
    public static void setSafetyValue(Integer setUpLink) {
        DigitsTextField.safetyValue = setUpLink;
    }
    private static Integer safetyValue; // value for strings where no parsed int value
    public DigitsTextField(String placeholder, int i) {
        super(placeholder, i,new DigitsSchema());
    }
    public String getSafeTextForParseInt(){
        return (super.getText().isEmpty() || placeholderIsOn) ?
                ""+safetyValue : super.getText();
    }
}
