package view.header;

import javax.swing.*;
import java.awt.*;

public class JPModelTextField extends JTextField {


    public JPModelTextField(String text, String backGround, int width, int high) {
        setText(text);
        setBackground(Color.decode(backGround));
        setForeground(Color.BLACK);
        setMaximumSize(new Dimension(width, high));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        //setBorder(n);
    }
}
