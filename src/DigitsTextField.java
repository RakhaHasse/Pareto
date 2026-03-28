public class DigitsTextField extends TextField{
    public static void setSafetyValue(Integer setUpLink) {
        DigitsTextField.safetyValue = setUpLink;
    }
    private static Integer safetyValue; // value for strings where no parsed int value
    public DigitsTextField() {
        super("Enter Value", 10);
        this.setDocument(new DigitsSchema());
    }
    public String getSafeTextForParseInt(){
        return super.getText().isEmpty() ? ""+safetyValue : super.getText();
    }
}
