package view;

import java.time.LocalDate;
import java.util.Scanner;
import exception.*;
import utilities.*;

public class IoManager {
		
		private static final String FORMAT = "%1$-22s %2$-15s %3$-15s %4$-22s %5$-15s %6$-22s %7$-22s %8$-10s";
		private static final String BIB_NUMBER = "NUMERO DEL DORSAL";
		private static final String NAME = "NOMBRE";
		private static final String LASTNAME = "APELLIDO";
		private static final String HIREDATE = "FECHA NACIMIENTO";
		private static final String GENDER = "GENERO";
		private static final String TEAM = "EQUIPO";
		private static final String TIME = "TIEMPO DE RECORRIDO";
		private static final String AGE = "EDAD";
		private static final Object[] HEADERS = {BIB_NUMBER, NAME, LASTNAME, HIREDATE, GENDER, TEAM, TIME, AGE};
		private static final String SEPARATOR_LINE = "---------------------------------------------------------------------------------------------------------------------------------------------------------------";
		
		private Scanner input;
		
		public IoManager() {
			input = new Scanner(System.in);
		}
		
		public String getCyclistName(){
			showMessage("Ingrese su nombre");
			String name = input.next().trim(); 
			try {
				Utilities.validateReadName(name);
				return name; 
			} catch (ExceptionReadName e) {
				System.err.println(e.getMessage());
				return getCyclistName();
			}
		}
		
		public String getCyclistLastName(){
			showMessage("Ingrese su apellido");
			String name = input.next().trim(); 
			try {
				Utilities.validateReadName(name);
				return name; 
			} catch (ExceptionReadName e) {
				System.err.println(e.getMessage());
				return getCyclistName();
			}
		}
		
		public LocalDate getCyclistBirthDate(){
			showMessage("Ingrese fecha en formato dd/mm/yyyy");
			String dateString = input.next().trim();
			try {
				Utilities.validateDate(dateString);
				return Utilities.toDate(dateString);
			} catch (DateFormatException e) {
				System.err.println(e.getMessage());
				return getCyclistBirthDate();
			}
		}
		
		public int getCyclistGender(){
			showMessage("Escoja una opcion.\n1.FEMENINO\n2.MASCULINO");
			return Integer.parseInt(input.next());

		}
		public int getCyclistTeam(){
			showMessage("Escoja una opcion.\n1. Movistar Team\n2. Mondiale Team\n3. Astana\n4. CCC Team\n5. Sky Team\n6. Dimension Team\n7. Arkea Team\n8. Nordisk Team\n9. Rural Team\n10. |Burgos Team");
			return Integer.parseInt(input.next());

		}
		
		public String getTotalRaceTime(){
			showMessage("Ingrese tiempo en formato hh:mm:ss");
			return input.next().trim();
		}
		
		public void showMessageProblemAge(){
			showMessage("Debe ser mayor de 15 y menor de 40");
		}
		
		public void showMessage(String message) {
			System.out.println(message);
		}
		public short readShort(String message) {
			this.showMessage(message);
			return input.nextShort();
		}	
		public void generateHeader() {
			System.out.println(String.format(FORMAT, HEADERS));
			System.out.println(SEPARATOR_LINE);
		}
		
		public void showListDatas(Object[][] dataList) {
	        if(dataList != null) {
	        	generateHeader();
	            for (int i = 0; i < dataList.length; i++) {
	                Object[] dataObject = dataList[i];
	                System.out.println(String.format(FORMAT, dataObject));
	            }
	        }else
	            System.err.println("Error insuficiente");
	    }
		
		public void showErrorMessage(String message){
	        System.out.println(message);
	    }
		
		public short readMenu(){
	        String menuText = "1. Adicionar un ciclista\n"
	                + "2. Mostrar reporte ordenado\n"
	                + "3. Mostrar reporte por genero\n"
	                + "4. Mostrar corredora con mejor tiempo\n"
	        		+ "5. Salida";
	        return readShort(menuText);
	    }
		
}
