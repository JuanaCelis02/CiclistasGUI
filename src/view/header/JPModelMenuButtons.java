package view.header;

import javax.swing.*;
import java.awt.*;

public class JPModelMenuButtons extends JButton {

    public JPModelMenuButtons(String text, String Icon){
        setText(text);
        setForeground(Color.BLACK);
        setMaximumSize(new Dimension(390, 40));
        setBackground(null);
        setIcon(new ImageIcon(new ImageIcon(getClass().getResource(Icon)).getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setBorder(null);
    }

    public JPModelMenuButtons(){

    }
}
