package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import exception.ExceptionReadName;
import model.*;
import persistence.HandlerLanguage;
import persistence.MyFile;
import utilities.Utilities;
import view.IoManager;
import view.JFrameMainWindow;
import view.body.JPTableElements;
import view.dialogs.JDAddCyclist;

import javax.swing.*;

public class Presenter implements ActionListener {

	private static final String ENGLISH_PATH = "resources/languages/languageUS.properties";
	private static final String SPANISH_PATH = "resources/languages/languageES.properties";
	private static final String NAME_FILE_CONFIG = "resources/config/config.init";
	//static final String PATH_FILE_IN = "resources/Corredores.bike";

	private String pathFile;
	private CyclingManager managerObj;
	private JFrameMainWindow mainWindow;
	private JDAddCyclist jdAddCyclist;
	private JPTableElements jpTableElements;
	private IoManager console;
	private Boolean isFemaleTableShowing = false;
	MyFile myFile;

	private HandlerLanguage config;
	
	public Presenter() {
		managerObj = new CyclingManager();
		showJFileChooser();
		loadConfiguration();
		mainWindow = new JFrameMainWindow(this);
		//console = new IoManager();
		//this.menu();
	}

	private void showJFileChooser(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int jfcResult = fileChooser.showOpenDialog(mainWindow);
		switch (jfcResult){
			case JFileChooser.APPROVE_OPTION:
				pathFile = fileChooser.getSelectedFile().getAbsolutePath();
				if (new File(pathFile).canRead()){
					myFile = new MyFile(pathFile);
					startApp(pathFile);
				}
				break;
			case JFileChooser.CANCEL_OPTION:
				System.out.println("Cancelado por el usuario");
				break;
			case JFileChooser.ERROR_OPTION:
				System.out.println("Error de seleccion");
				break;
		}
	}

	private void startApp(String pathFile) {
		try{
			ArrayList<String> stringList = myFile.readFile(pathFile);
			manageTokenizeLines(stringList);
			//System.out.println(managerObj.getListCyclist().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void manageTokenizeLines(ArrayList<String>stringsList) {
		for (String string : stringsList) {
			String[] aux = Utilities.splitLine(string);
			if(aux.length == 8){
				try {
					if(managerObj.determineAgeRange(Utilities.toDate(aux[4])) >= 15
							&& managerObj.determineAgeRange(Utilities.toDate(aux[4])) <= 40
							&& Utilities.validateReadName(aux[1])
							&& Utilities.validateReadName(aux[2])
							&& Utilities.validateReadName(aux[3])){

						managerObj.addCyclist(new Cyclist(
								Integer.parseInt(aux[0]),
								aux[1],
								aux[2],
								aux[3],
								Utilities.getGender(aux[5]),
								LocalTime.parse(aux[7]),
								Teams.valueOf(aux[6]),
								managerObj.determineAgeRange(Utilities.toDate(aux[4]))));
					}

				}catch (Exception e){
					managerObj.addCyclistInvalid(new Cyclist(
							Integer.parseInt(aux[0]),
							aux[1],
							aux[2],
							aux[3],
							Utilities.getGender(aux[5]),
							LocalTime.parse(aux[7]),
							Teams.valueOf(aux[6]),
							managerObj.determineAgeRange(Utilities.toDate(aux[4]))));
				}
			}
		}
		writeNoValidateCyclist();
		System.out.println(managerObj.getListInvalidCyclist().toString());
	}

	private void writeNoValidateCyclist() {
		try {
			FileWriter fileWriter = new FileWriter("resources/noValidos"+ Calendar.getInstance().getTime() +".bike", true);
			for (int i = 0; i < managerObj.getListInvalidCyclist().size(); i++) {
				fileWriter.write(managerObj.getListInvalidCyclist().get(i).toString());
				fileWriter.write("\r\n");
			}
			fileWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public void showListCyclist(){
		for (int c = 0; c < managerObj.getListCyclist().size(); c++) {
			//System.out.println(managerObj.getListCyclist().get(c).toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (EVENTS.valueOf(e.getActionCommand())){

			case C_SPANISH_LANGUAGE:
				manageChangeLanguageES();
				break;

			case C_ENGLISH_LANGUAGE:
				manageChangeLenguageUS();
				break;

			case C_SHOW_DIALOG_ADD_CYCLIST:
				jdAddCyclist = new JDAddCyclist(this);
				break;

			case C_SHOW_TABLE_CYCLIST:
				showGeneralCyclistTable();
				isFemaleTableShowing = false;
				break;

			case C_SHOW_BEST_CYCLIST_GIRL:
				showNameAndTimeBestCyclist();
				break;

			case C_ADD_NEW_CYCLIST:
				try {
					addNewCyclistOfDialog();
				} catch (ExceptionReadName exceptionReadName) {
					exceptionReadName.printStackTrace();
				}
				showGeneralCyclistTable();
				break;

			case C_SHOW_REPORT_FEMALE:
				showFemaleCyclistTable();
				break;

			case C_SHOW_TABLE_AVERAGE:
				showTableAverageByGender(managerObj.getAverageFemaleAndMale());
				break;
		}
	}

	private void loadConfiguration() {
		if(config == null){
			config = new HandlerLanguage(NAME_FILE_CONFIG);
		}
		try{
			config.loadLanguage();
		}catch(IOException e){
            //JOptionPane.showMessageDialog(jfMainWindow, e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	private void manageChangeLenguageUS(){
		try {
			changeToEnglish();
		}catch (IOException e1){
			JOptionPane.showMessageDialog(mainWindow,e1.getMessage());
		}
		manageChangeLanguage();
	}

	private void manageChangeLanguageES(){
		try {
			changeToSpanish();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(mainWindow, e1.getMessage());
		}
		manageChangeLanguage();
	}

	private void manageChangeLanguage(){
		mainWindow.changeLanguage();
	}

	public void changeToEnglish() throws IOException{
		HandlerLanguage.language = ENGLISH_PATH;
		saveConfig();
		loadLanguage();
	}

	public void changeToSpanish() throws IOException{
		HandlerLanguage.language = SPANISH_PATH;
		saveConfig();
		loadLanguage();
	}

	private void loadLanguage() {
		try {
			config.loadLanguage();
		}catch (IOException e){
			JOptionPane.showMessageDialog(mainWindow,e.getMessage());
		}
	}

	private void saveConfig() {
		try {
			new HandlerLanguage(NAME_FILE_CONFIG).saveLanguage();
		}catch (IOException e){
			JOptionPane.showMessageDialog(mainWindow, e.getMessage());
		}
	}

	public void showTableAverageByGender(Object[]average){
		mainWindow.showTableAverageByGender(average);
	}

	private void showFemaleCyclistTable() {
		managerObj.sortCyclistForTime();
		mainWindow.showGeneralCyclistTable(managerObj.listOfCyclistFemale());
	}

	private void showGeneralCyclistTable() {
		managerObj.sortCyclistForTime();
		mainWindow.showGeneralCyclistTable(managerObj.getListCyclist());
	}


	public void addNewCyclistOfDialog() throws ExceptionReadName {
		Cyclist cyclist = jdAddCyclist.createCyclist();
		cyclist.setBibNumber(managerObj.getListCyclist().size());
		cyclist.setAge(managerObj.determineAgeRange(cyclist.getBirthDate()));
		//VALIDAR
		if(cyclist.getAge()> 15
				&& cyclist.getAge()< 40
				&& Utilities.validateReadName(cyclist.getName())
				&& Utilities.validateReadName(cyclist.getLastName())
				&& Utilities.validateReadName(cyclist.getSecondName())){
			managerObj.addCyclist(cyclist);
		}
		else{
			System.out.println("VERIFIQUE EDAD Y NOMBRES");
		}
	}

	public void showNameAndTimeBestCyclist(){
		Cyclist bestCyclist = managerObj.getBestCyclist();
		mainWindow.showNameAndTimeBestCyclist(bestCyclist);
	}
	
	/*
	public void printCyclistList() {
		ArrayList<Cyclist> cyclistList = managerObj.getListCyclist();
		StringBuilder text = new StringBuilder();
		for (Cyclist cyclist : cyclistList) {
			text.append(cyclist);
			text.append("\n");
		}
		System.out.print(text.toString());
	}

	public void menu() {
        int op=0;
        do {
            op = console.readMenu();
            switch (op) {
              	case 1:
           		   addCyclist();
              	break;
               	case 2:
              	    managerObj.sortCyclistForTime();
               		console.showListDatas(managerObj.toMatrixVec());
           		break;
               	case 3:
            		managerObj.calculateFemaleAvarage();
               		managerObj.calculateMaleAvarage();
               	break;
               	case 4:
              		console.showMessage((managerObj.bestGirlCyclist()));
          		break;
                case 5:
                  	console.showMessage("Salida");
                break;
                default:
                    console.showMessage("Opcion invalida");
                }
        } while (op != 5);
    }

	public Cyclist createCyclist() {
		LocalDate date = null;
		LocalTime time = null;
		int bibNumber = managerObj.getListCyclist().size()+1;
		String name = console.getCyclistName();
		String lastName = console.getCyclistLastName();
		date = console.getCyclistBirthDate();
		Gender gender = managerObj.selectGender(console.getCyclistGender());
		Teams team = managerObj.selectTeam(console.getCyclistTeam());
		time = LocalTime.parse(console.getTotalRaceTime());
		int age = managerObj.determineAgeRange(date);
		return new Cyclist(bibNumber, name, lastName, date, gender, team, time, age);
	}
	public void addCyclist() {
		Cyclist cyc = this.createCyclist();
		if (cyc.getAge()>15 && cyc.getAge()<40) {
			managerObj.addCyclist(cyc);
		}else {
			console.showMessageProblemAge();
		}
	}
	 */
}
