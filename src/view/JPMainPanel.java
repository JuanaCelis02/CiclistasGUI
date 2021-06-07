package view;

import model.Cyclist;
import presenter.Presenter;
import view.body.JPContainerBody;
import view.body.JPTableElements;
import view.footer.JPContainerFooter;
import view.header.JPContainerHeader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JPMainPanel extends JPanel {

    private JPContainerHeader jpcontainerHeader;
    private JPContainerBody jpcontainerBody;
    private JPContainerFooter jpcontainerFooter;
    private Presenter myPresenter;

    public JPMainPanel(Presenter presenter) {
        myPresenter = presenter;
        setLayout(new BorderLayout());
        setBackground(Color.white);
        initComponents();
    }

    private void initComponents() {
        jpcontainerHeader = new JPContainerHeader(myPresenter);
        add(jpcontainerHeader, BorderLayout.PAGE_START);

        jpcontainerBody = new JPContainerBody(myPresenter);
        add(jpcontainerBody,BorderLayout.CENTER);

        jpcontainerFooter = new JPContainerFooter(myPresenter);
        add(jpcontainerFooter, BorderLayout.PAGE_END);


    }

    public void showGeneralCyclistTable(ArrayList<Cyclist> cyclistList){
        jpcontainerBody.showGeneralCyclistTable(cyclistList);
    }

    public void showNameAndTimeBestCyclist(Cyclist bestCyclist){
        jpcontainerFooter.showNameAndTimeBestCyclist(bestCyclist);
    }

    public void showTableAverageByGender(Object[]average){
        jpcontainerBody.showTableAverageByGender(average);
    }

    public void changeLanguage() {
        jpcontainerHeader.changeLanguage();
        jpcontainerBody.changeLanguage();
    }

    /*
    public String getUserName(){
        return jpcontainerBody.getUserName();
    }

    public void changeJLabelColor(){
        jpcontainerBody.changeJLabelColor();
    }
    P
     */
}
