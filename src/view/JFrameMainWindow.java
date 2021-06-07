package view;

import model.Cyclist;
import persistence.HandlerLanguage;
import presenter.Presenter;

import javax.swing.*;
import java.util.ArrayList;

public class JFrameMainWindow extends JFrame {

    private JPMainPanel mainPanel;
    private Presenter myPresenter;
    private ArrayList<Cyclist> cyclistArrayList;

    public JFrameMainWindow(Presenter presenter) {
        myPresenter = presenter;
        setExtendedState(MAXIMIZED_BOTH);
        setSize(600, 400);
        //setIconImage(new ImageIcon(getClass().getResource(ConstantsGUI.LOGO_PAGE)).getImage());
        setTitle("CICLISMO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPMainPanel(myPresenter);
        JScrollPane jsPMainPanel = new JScrollPane();
        jsPMainPanel.setViewportView(mainPanel);
        jsPMainPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jsPMainPanel);
    }

    public void showGeneralCyclistTable(ArrayList<Cyclist> cyclistList){
        mainPanel.showGeneralCyclistTable(cyclistList);
    }

    public void showNameAndTimeBestCyclist(Cyclist bestCyclist){
        mainPanel.showNameAndTimeBestCyclist(bestCyclist);
    }

    public void showTableAverageByGender(Object[]average){
        mainPanel.showTableAverageByGender(average);
    }

    public void changeLanguage() {
        this.setTitle( HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_MAIN_WINDOW ));
        mainPanel.changeLanguage();
    }


    /*
    public String getUserName(){
        return mainPanel.getUserName();
    }

    public void changeJLabelColor(){
        mainPanel.changeJLabelColor();
    }

     */
}
