package view.dialogs;

import com.toedter.calendar.JDateChooser;
import model.Cyclist;
import model.Gender;
import model.Teams;
import persistence.HandlerLanguage;
import presenter.EVENTS;
import presenter.Presenter;
import utilities.Utilities;
import view.ConstantGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.time.LocalTime;

public class JDAddCyclist extends JDialog {

    private JPanel jPanelDialogContainer;
    private JTextField jtfName, jtLastName,jtfTime;
    private JComboBox<Teams>jcTeams;
    private JComboBox<Gender>jcGender;
    private JDateChooser jDBirthDate;
    private JButton buttonAddCyclist;
    private Presenter myPresenter;

    public JDAddCyclist(Presenter presenter){
        myPresenter = presenter;
        setSize(350, 600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setTitle("AGREGAR CICLISTA");
        setVisible(true);
        initComponents();
    }

    private void initComponents() {
        jPanelDialogContainer = new JPanel();
        GridLayout gridLayout = new GridLayout(7,1);
        gridLayout.setVgap(15);
        jPanelDialogContainer.setBorder(new EmptyBorder(20,20,20,20));
        jPanelDialogContainer.setLayout(gridLayout);


        jtfName = new JTextField();
        jtfName.setBorder(BorderFactory.createTitledBorder(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_GET_NAME )));
        jPanelDialogContainer.add(jtfName);

        jtLastName = new JTextField();
        jtLastName.setBorder(BorderFactory.createTitledBorder(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_GET_LASTNAME )));
        jPanelDialogContainer.add(jtLastName);

        jDBirthDate = new JDateChooser();
        jDBirthDate.setBorder(BorderFactory.createTitledBorder(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_GET_BIRTHDAY )));
        jPanelDialogContainer.add(jDBirthDate);

        jcGender = new JComboBox<>(Gender.values());
        jcGender.setBorder(BorderFactory.createTitledBorder(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_GET_GENDER )));
        jcGender.setBackground(Color.white);
        jPanelDialogContainer.add(jcGender);

        jcTeams = new JComboBox<>(Teams.values());
        jcTeams.setBorder(BorderFactory.createTitledBorder(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_GET_TEAM )));
        jcTeams.setBackground(Color.WHITE);
        jPanelDialogContainer.add(jcTeams);

        jtfTime = new JTextField();
        jtfTime.setBorder(BorderFactory.createTitledBorder(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_GET_TIME )));
        jPanelDialogContainer.add(jtfTime);

        buttonAddCyclist = new JButton();
        buttonAddCyclist = new view.RoundedJButton(15, 15, HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_JD_ADD_CYCLIST ), Color.decode("#337AB7"), Color.WHITE, EVENTS.C_ADD_NEW_CYCLIST.toString(), myPresenter);
        buttonAddCyclist.addActionListener(myPresenter);
        jPanelDialogContainer.add(buttonAddCyclist);

        add(jPanelDialogContainer, BorderLayout.CENTER);

    }

    public Cyclist createCyclist(){
        setVisible(false);
        if (jDBirthDate.getDate()!= null){
            System.out.println(jDBirthDate.getDate());
        }
        return new Cyclist(jtfName.getText(),
                jtLastName.getText(),
                Utilities.parseDateToLocalDate(jDBirthDate.getDate()),
                (Gender)jcGender.getSelectedItem(),
                (Teams)jcTeams.getSelectedItem(),
                LocalTime.parse(jtfTime.getText()));
    }


}
