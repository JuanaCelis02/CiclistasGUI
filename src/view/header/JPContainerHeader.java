package view.header;

import presenter.Presenter;

import javax.swing.*;
import java.awt.*;

public class JPContainerHeader extends JPanel {

    private JPMainMenu jpMainMenu;
    private Presenter myPresenter;

    public JPContainerHeader(Presenter presenter) {
        this.myPresenter = presenter;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        initComponents();
    }

    private void initComponents() {
        jpMainMenu = new JPMainMenu(myPresenter);
        this.add(jpMainMenu);
    }

    public void changeLanguage(){
        jpMainMenu.changeLanguage();
    }
}
