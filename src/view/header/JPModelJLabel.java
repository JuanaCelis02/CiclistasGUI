package view.header;

import javax.swing.*;
import java.awt.*;

public class JPModelJLabel extends JLabel {


    public JPModelJLabel(String text, String foreGround){
        setText(text);
        setForeground(Color.decode(foreGround));
        setFont(new Font("Arial", Font.PLAIN,12));
        //setBorder(n);
    }

}
