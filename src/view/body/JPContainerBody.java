package view.body;

import model.Cyclist;
import presenter.Presenter;
import view.ConstantGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JPContainerBody extends JPanel {

    private Presenter myPresenter;
    private JPTableElements jTableAllCyclist;


    public JPContainerBody(Presenter presenter) {
        this.setLayout(new BorderLayout());
        myPresenter = presenter;
        initViews();

    }

    private void initViews() {
        jTableAllCyclist = new JPTableElements();
        this.add(jTableAllCyclist,BorderLayout.CENTER);
    }

    public void showGeneralCyclistTable(ArrayList<Cyclist> cyclistList){
        jTableAllCyclist.showGeneralCyclistTable(cyclistList);
    }

    public void showTableAverageByGender(Object[]average){
        jTableAllCyclist.showTableAverageByGender(average);
    }


    public void manageChangeLenguage() {

    }

    public void changeLanguage() {
        jTableAllCyclist.changeLanguage();
    }
}
