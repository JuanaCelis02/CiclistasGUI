package view.footer;

import model.Cyclist;
import persistence.HandlerLanguage;
import view.ConstantGUI;

import javax.swing.*;
import java.awt.*;

public class JPShowBestGirl extends JPanel {

    private JLabel nameBestCyclist, infoTime;

    public JPShowBestGirl(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1300,170));
        setAlignmentX(LEFT_ALIGNMENT);
        setBackground(Color.gray);
        initComponents();
    }

    private void initComponents() {

        addLabelNameRunner();
        addTimeRunner();
    }

    private void addLabelNameRunner() {
        nameBestCyclist = new JLabel();
        nameBestCyclist.setForeground(Color.BLACK);
        nameBestCyclist.setBorder(BorderFactory.createEmptyBorder(20,20,0,0));
        nameBestCyclist.setFont(new Font("Arial", Font.PLAIN,17));
        add(nameBestCyclist);

    }
    
    private void addTimeRunner() {
        infoTime = new JLabel();
        infoTime.setForeground(Color.BLACK);
        infoTime.setFont(new Font("Arial", Font.PLAIN,17));
        infoTime.setBorder(BorderFactory.createEmptyBorder(20,20,20,0));
        add(infoTime);
    }

    public void showNameAndTimeBestCyclist(Cyclist bestCyclist){
        nameBestCyclist.setText(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_SHOW_RUNNER_GIRL_NAME)+ bestCyclist.getName() + " " +bestCyclist.getLastName());
        infoTime.setText(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_SHOW_RUNNER_GIRL_TIME )+ bestCyclist.getTotalRaceTime());
    }
}
