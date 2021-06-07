package view.body;

import model.Cyclist;
import persistence.HandlerLanguage;
import view.ConstantGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JPTableElements extends JPanel {

    private DefaultTableModel defaultTable;
    private JTable tableElements;
    private JScrollPane scrollTable;

    public JPTableElements(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#30373D"));
        initComponents();
    }

    private void initComponents() {
        defaultTable = new DefaultTableModel();

        tableElements = new JTable();
        tableElements.setModel(defaultTable);
        tableElements.getTableHeader().setReorderingAllowed(false);
        tableElements.getTableHeader().setBackground(new Color(120, 120, 120));

        //setSize

        tableElements.getTableHeader().setPreferredSize(new Dimension(150,50));
        tableElements.getTableHeader().setForeground(Color.WHITE);
        tableElements.setBackground(Color.WHITE);
        tableElements.setFillsViewportHeight(true);
        tableElements.setRowHeight(40);

        scrollTable = new JScrollPane(tableElements);
        scrollTable.setForeground(Color.WHITE);
        scrollTable.setAlignmentX(LEFT_ALIGNMENT);
        this.add(scrollTable);
    }

    public void showGeneralCyclistTable(ArrayList<Cyclist> cyclistList){
        clearTable();

        String [] headers = {HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_DORSAL ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_NAME ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_SECONDNAME ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_LASTNAME ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_GENDER ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_TEAM ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_TIME ),
                HandlerLanguage.languageProperties.getProperty( ConstantGUI.T_HEADER_AGE )};

        defaultTable.setColumnIdentifiers(headers);

        loadCyclistToList(cyclistList);
    }

    public void showTableAverageByGender(Object[]average){
        clearTable();
        String[] headers = {HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_HEADER_AVERAGE_FEMALE),
                            HandlerLanguage.languageProperties.getProperty(ConstantGUI.T_HEADER_AVERAGE_MALE)};
        defaultTable.setColumnIdentifiers(headers);
        defaultTable.addRow(average);

    }

    private void clearTable() {
        defaultTable.setRowCount(0);
    }

    private void loadCyclistToList(ArrayList<Cyclist> cyclistList) {
        for (Cyclist cyclist : cyclistList) {
            defaultTable.addRow(cyclist.getCyclistData());
        }
    }

    public void changeLanguage() {
    }
}
