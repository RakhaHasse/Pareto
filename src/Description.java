import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;



public class Description extends TextArea {
    String placeholder;
    boolean placeholderIsOn = true;
    public Description(String placeholder) {
        super(12,30);
        this.placeholder = placeholder;
        demoPlaceholder();
       Description description = this;
        // Add FocusListener, for show placeholder
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (description.getText().equals(description.placeholder)) {
                    description.readyToInputAfterShownPlaceholder();
                }
                else readyToInput();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (description.getText().isEmpty()) {
                    description.demoPlaceholder();
                }
            }
        });
    }

    @Override
    public void setText(String t) {
        super.setText(t);
    }

    public void readyToInputAfterShownPlaceholder(){
        setText("");
        placeholderIsOn = false;
        setForeground(Color.BLACK);
    }

    public void readyToInput(){
        placeholderIsOn = false;
    }

    public  void demoPlaceholder (){
        placeholderIsOn = true;
        setText(this.placeholder);
        setForeground(Color.GRAY);
    }

    public void resetAndPlaceholder (){
        setText("");
        placeholderIsOn = true;
        setText(this.placeholder);
        setForeground(Color.GRAY);
    }
}

