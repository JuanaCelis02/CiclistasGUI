package view.header;

import persistence.HandlerLanguage;
import presenter.EVENTS;
import presenter.Presenter;
import utilities.Utilities;
import view.ConstantGUI;

import javax.swing.*;
import java.awt.*;

public class JPMainMenu extends JPanel {

    private JPModelMenuButtons jmbAddCyclist;
    private JPModelMenuButtons jmbOrderReport;
    private JPModelMenuButtons jmbGenderReport;
    private JPModelMenuButtons jmbBestGirl;
    private JPModelMenuButtons jmbShowAverage;
    private JButton jmbLanguageEnglish;
    private JButton jmbLanguageSpanish;

    private Presenter myPresenter;

    public JPMainMenu(Presenter presenter){
        myPresenter = presenter;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBackground(Color.decode("#ffffff"));
        initComponents();
    }

    private void initComponents() {
        addButtonLanguages();
        //addIconCycling();
        addButtonsMenu();
    }

    private void addButtonLanguages() {
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));

        jmbLanguageEnglish = new JButton();
        ImageIcon tempEnglish = new ImageIcon(new ImageIcon(getClass().getResource(ConstantGUI.LOGO_ENGLISH)).getImage());
        Utilities.setMainElementsButton(jmbLanguageEnglish,tempEnglish,0,20,5,0);
        jmbLanguageEnglish.addActionListener(myPresenter);
        jmbLanguageEnglish.setActionCommand(EVENTS.C_ENGLISH_LANGUAGE.toString());
        tempPanel.add(jmbLanguageEnglish);

        jmbLanguageSpanish = new JButton();
        ImageIcon tempSpanish = new ImageIcon(new ImageIcon(getClass().getResource(ConstantGUI.LOGO_SPANISH)).getImage());
        Utilities.setMainElementsButton(jmbLanguageSpanish,tempSpanish,5,20,0,0);
        jmbLanguageSpanish.addActionListener(myPresenter);
        jmbLanguageSpanish.setActionCommand(EVENTS.C_SPANISH_LANGUAGE.toString());
        tempPanel.add(jmbLanguageSpanish);

        this.add(tempPanel);

    }

    private void addIconCycling() {
        JLabel jLIcon = new JLabel();
        jLIcon.setIcon(new ImageIcon(getClass().getResource(ConstantGUI.ICON_CYCLING)));
        jLIcon.setBorder(BorderFactory.createEmptyBorder(15,80,0,250));
        //jLIcon.setMaximumSize(new Dimension(200,200));
        jLIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(jLIcon);
    }

    private void addButtonsMenu(){

        jmbAddCyclist = new JPModelMenuButtons(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_BUTTON_ADD_CYCLIST ), ConstantGUI.BUTTON_ADD_CICLIST);
        jmbAddCyclist.addActionListener(myPresenter);
        jmbAddCyclist.setActionCommand(EVENTS.C_SHOW_DIALOG_ADD_CYCLIST.toString());
        add(jmbAddCyclist);

        jmbOrderReport = new JPModelMenuButtons(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_BUTTON_ORDER_REPORT), ConstantGUI.BUTTON_ORDER_REPORT);
        jmbOrderReport.addActionListener(myPresenter);
        jmbOrderReport.setActionCommand(EVENTS.C_SHOW_TABLE_CYCLIST.toString());
        add(jmbOrderReport);

        jmbGenderReport = new JPModelMenuButtons(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_BUTTON_GENDER_REPORT), ConstantGUI.BUTTON_CYCLIST_GIRL);
        jmbGenderReport.addActionListener(myPresenter);
        jmbGenderReport.setActionCommand(EVENTS.C_SHOW_REPORT_FEMALE.toString());
        add(jmbGenderReport);

        jmbBestGirl = new JPModelMenuButtons(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_BUTTON_BEST_GIRL), ConstantGUI.BUTTON_BEST_GIRL);
        jmbBestGirl.addActionListener(myPresenter);
        jmbBestGirl.setActionCommand(EVENTS.C_SHOW_BEST_CYCLIST_GIRL.toString());
        add(jmbBestGirl);

        jmbShowAverage = new JPModelMenuButtons(HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_BUTTON_SHOW_AVERAGE), ConstantGUI.BUTTON_GENDER_REPOR);
        jmbShowAverage.addActionListener(myPresenter);
        jmbShowAverage.setActionCommand(EVENTS.C_SHOW_TABLE_AVERAGE.toString());
        add(jmbShowAverage);
    }

    public void changeLanguage() {
        jmbAddCyclist.setText( HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_BUTTON_ADD_CYCLIST) );
        jmbOrderReport.setText(HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_BUTTON_ORDER_REPORT));
        jmbGenderReport.setText(HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_BUTTON_GENDER_REPORT));
        jmbBestGirl.setText(HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_BUTTON_BEST_GIRL));
        jmbShowAverage.setText(HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_BUTTON_SHOW_AVERAGE));
    }
}
