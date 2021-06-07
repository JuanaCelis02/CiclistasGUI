package view.footer;

import model.Cyclist;
import presenter.Presenter;

import javax.swing.*;
import java.awt.*;

public class JPContainerFooter extends JPanel {

    private Presenter myPresenter;
    private JPShowBestGirl panelGirl;

    public JPContainerFooter(Presenter presenter){
        this.setLayout(new BorderLayout());
        myPresenter = presenter;
        initViews();
    }

    private void initViews() {
        panelGirl = new JPShowBestGirl();
        this.add(panelGirl,BorderLayout.CENTER);

    }
    public void showNameAndTimeBestCyclist(Cyclist bestCyclist){
        panelGirl.showNameAndTimeBestCyclist( bestCyclist);
    }
}
