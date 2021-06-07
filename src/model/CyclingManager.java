package model;

import utilities.Utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CyclingManager {
	
	private ArrayList<Cyclist> listCyclist;
	private ArrayList<Cyclist>listInvalidCyclist;
	private String nameRace;
	private String country;

	public CyclingManager() {
		//this.listCyclist = generateNewCyclistList();
		listCyclist = new ArrayList<>();
		listInvalidCyclist = new ArrayList<>();
	}

	public ArrayList<Cyclist> getListInvalidCyclist() {
		return listInvalidCyclist;
	}

	public void setListInvalidCyclist(ArrayList<Cyclist> listInvalidCyclist) {
		this.listInvalidCyclist = listInvalidCyclist;
	}

	public ArrayList<Cyclist> getListCyclist() {
		return listCyclist;
	}

	public void setListCyclist(ArrayList<Cyclist> listCyclist) {
		this.listCyclist = listCyclist;
	}

	public String getNameRace() {
		return nameRace;
	}

	public void setNameRace(String nameRace) {
		this.nameRace = nameRace;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void addCyclist(Cyclist cyclist) {

			listCyclist.add(cyclist);

	}

	public void addCyclistInvalid(Cyclist cyclist) {

		listInvalidCyclist.add(cyclist);

	}
	
	public int determineAgeRange(LocalDate birthDate) {
		int age = 0;
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(birthDate, ahora);
		age = periodo.getYears();
		return age;
	}

	public void calculateGeneralAverage(Gender gender) {
		long seconds = 0;
		int count = 0;
		for (int i = 0; i < listCyclist.size(); i++) {
			if (listCyclist.get(i).getGender().equals(gender)) {
				seconds += listCyclist.get(i).getTotalRaceTime().getLong(ChronoField.SECOND_OF_DAY);
				count++;
			}
		}
		LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds/count);
		String time = timeOfDay.toString();
		System.out.println("Promedio "+ gender.getGenderName() + " "+time);
	}

	public String calculateAverageByGender(Gender gender) {
		long seconds = 0;
		int count = 0;
		for (int i = 0; i < listCyclist.size(); i++) {
			if (listCyclist.get(i).getGender().equals(gender)) {
				seconds += listCyclist.get(i).getTotalRaceTime().getLong(ChronoField.SECOND_OF_DAY);
				count++;
			}
		}
		LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds/count);
		String time = timeOfDay.toString();
		return time;
	}
	
	public void calculateFemaleAvarage() {
		calculateGeneralAverage(Gender.FEMALE);
	}
	public void calculateMaleAvarage() {
		calculateGeneralAverage(Gender.MALE);
	}
	
	public Gender selectGender(int  option) {
		Gender gender = null;
		switch (option) {
		case 1:
			gender = Gender.FEMALE;
			break;
		case 2:
			gender = Gender.MALE;
		default:
			System.out.println("Opcion invalida");
		break;
		}
		return gender;
	}
	public Teams selectTeam(int option) {
		Teams team = null;
		switch (option) {
		case 1:
			team = Teams.MOVISTAR;
			break;
		case 2:
			team = Teams.KATUSHA;
			break;
		case 3:
			team = Teams.TINKOFF;
			break;
		case 4:
			team = Teams.BMC;
			break;
		case 5:
			team = Teams.SKY;
			break;
		case 6:
			team = Teams.TEAM_DIMENSION;
			break;
		case 7:
			team = Teams.TEAM_ARKEA;
			break;
		case 8:
			team = Teams.TEAM_NOVO;
			break;
		case 9:
			team = Teams.CAJA_RURAL;
			break;
		case 10:
			team = Teams.BURGOS;
			break;

		default:
			System.out.println("Opcion invalida");
			break;
		}
		return team;
	}
	
	public String bestGirlCyclist() {
		String salida = "";
		sortCyclistForTime();
	   for (int i = 0; i < listCyclist.size(); i++) {
		   if (listCyclist.get(i).getGender().equals(Gender.FEMALE)) {
			salida = listCyclist.get(i).toString();
			break;
		   }
	   }
	   return salida;
	}
	public Cyclist getBestCyclist() {
		Cyclist bestCyclist = null;
		sortCyclistForTime();
		for (int i = 0; i < listCyclist.size(); i++) {
			if (listCyclist.get(i).getGender().equals(Gender.FEMALE)) {
				bestCyclist = listCyclist.get(i);
				break;
			}
		}
		return bestCyclist;
	}

	public void order() {
		listCyclist.sort(Comparator.comparing(Cyclist::getTotalRaceTime));
	}
	
	public  Object[][] toMatrixVec(){
        Object[][] dataMatrix = null;
        int sizeColumns = listCyclist.get(0).toObjectVector().length;
        if (listCyclist.size() >0){
            dataMatrix = new Object[listCyclist.size()][sizeColumns];
            for (int i=0; i <dataMatrix.length;i++){
                dataMatrix[i] = listCyclist.get(i).toObjectVector();
            }
        }
        return dataMatrix;
    }

	public Cyclist getCyclistIndex(int position){
		return listCyclist.get(position);
	}

	public void createRace(Cyclist[] cyclists){
		for (int i=0; i<cyclists.length; i++){
			listCyclist.add(cyclists[i]);
		}
	}

	//Devuelve lista de todos los ciclistas
	public ArrayList<Cyclist>getArrayListAllCyclist(){
		ArrayList<Cyclist>listAllCyclist = new ArrayList<>();
		for (int i = 0; i < listCyclist.size(); i++) {
			listAllCyclist.add(listCyclist.get(i));
		}
		return listAllCyclist;
	}

	//Devuelve lista de ciclistas mujeres
	public ArrayList<Cyclist> listOfCyclistFemale(){
		ArrayList<Cyclist>listFemaleCyclist = new ArrayList<>();
		for (int i = 0; i< listCyclist.size();i++){
			if (listCyclist.get(i).getGender().equals(Gender.FEMALE)){
				listFemaleCyclist.add(listCyclist.get(i));
			}
		}
		return listFemaleCyclist;
	}

	//Devuelve Promedios
	public Object[] getAverageFemaleAndMale(){
		return new Object[]{calculateAverageByGender(Gender.FEMALE), calculateAverageByGender(Gender.MALE)};
	}
	
	public void sortCyclistForTime(){
		Collections.sort(listCyclist, new Comparator<Cyclist>(){
			public int compare(Cyclist c1, Cyclist c2) {
				if (c1.getTotalRaceTime().isAfter(c2.getTotalRaceTime())) {
					return 1;
				}else if (c1.getTotalRaceTime().equals(c2.getTotalRaceTime())) {
					return 0;
				}else {
					return -1;
				}
			}
			
		});
	}

	public Cyclist createCyclist(String[]dataCyclist){
		Cyclist cyclistData = null;
		String[] datas = dataCyclist;
			cyclistData.setBibNumber(Integer.parseInt(datas[0]));
			cyclistData.setName(datas[1]);
			cyclistData.setLastName(datas[2]);
			cyclistData.setBirthDate(Utilities.toDate(datas[4]));
			cyclistData.setGender(Utilities.getGender(datas[5]));
			cyclistData.setTeam(Teams.valueOf(datas[6]));
			cyclistData.setTotalRaceTime(LocalTime.parse(datas[7]));
			cyclistData.setAge(determineAgeRange(Utilities.toDate(datas[4])));

			return cyclistData;

	}

	public ArrayList<Cyclist> generateNewCyclistList() {
		ArrayList<Cyclist> tmpCyclist = new ArrayList<>();
		tmpCyclist.add(new Cyclist(1, "Jose", "Luis","Gomez", LocalDate.parse("1998-10-30"), Gender.MALE, Teams.MOVISTAR, LocalTime.parse("01:01:01"), determineAgeRange(LocalDate.parse("1998-10-30"))));
		tmpCyclist.add(new Cyclist(2, "Juan", "Andres","Perez", LocalDate.parse("2004-11-02"), Gender.MALE, Teams.KATUSHA, LocalTime.parse("03:15:36"),determineAgeRange(LocalDate.parse("2004-11-02"))));
		tmpCyclist.add(new Cyclist(8, "Angela", "Maria","Garcia", LocalDate.parse("2000-03-26"), Gender.FEMALE, Teams.TEAM_NOVO, LocalTime.parse("03:01:18"),determineAgeRange(LocalDate.parse("2000-03-26"))));
		tmpCyclist.add(new Cyclist(3, "Jose","Esteban", "Rodriguez", LocalDate.parse("2002-08-25"), Gender.MALE, Teams.TINKOFF, LocalTime.parse("04:05:20"),determineAgeRange(LocalDate.parse("2002-08-25"))));
		tmpCyclist.add(new Cyclist(4, "Luis","Miguel", "Forero", LocalDate.parse("1994-07-15"), Gender.MALE, Teams.BMC, LocalTime.parse("05:02:12"), determineAgeRange(LocalDate.parse("1994-07-15"))));
		tmpCyclist.add(new Cyclist(5, "Jose","Luis", "Henao", LocalDate.parse("1990-06-22"), Gender.MALE, Teams.SKY, LocalTime.parse("04:40:11"), determineAgeRange(LocalDate.parse("1994-07-15"))));
		tmpCyclist.add(new Cyclist(6, "Maryi","Gabriela", "Torres", LocalDate.parse("1988-05-18"), Gender.FEMALE, Teams.TEAM_DIMENSION, LocalTime.parse("01:22:23"),determineAgeRange(LocalDate.parse("1999-04-17"))));
		tmpCyclist.add(new Cyclist(7, "Andres", "Felipe", "Carvajal", LocalDate.parse("1999-04-17"), Gender.MALE, Teams.TEAM_ARKEA, LocalTime.parse("04:11:03"),determineAgeRange(LocalDate.parse("1999-04-17"))));
		tmpCyclist.add(new Cyclist(9, "Laura","Tatiana", "Torres", LocalDate.parse("2004-02-11"), Gender.FEMALE, Teams.CAJA_RURAL, LocalTime.parse("04:25:27"),determineAgeRange(LocalDate.parse("2004-02-11"))));
		tmpCyclist.add(new Cyclist(10, "Diana","Carolina", "Hernandez", LocalDate.parse("2003-01-05"), Gender.FEMALE, Teams.BURGOS, LocalTime.parse("04:10:35"),determineAgeRange(LocalDate.parse("2003-01-05"))));
		tmpCyclist.add(new Cyclist(11, "Laura","Juliana", "Gonzalez", LocalDate.parse("2001-10-27"), Gender.FEMALE, Teams.MOVISTAR, LocalTime.parse("04:19:01"),determineAgeRange(LocalDate.parse("2001-10-27"))));
		tmpCyclist.add(new Cyclist(12, "Alex","Daniel", "Romero", LocalDate.parse("1997-10-31"), Gender.MALE, Teams.KATUSHA, LocalTime.parse("05:22:09"),determineAgeRange(LocalDate.parse("1997-10-31"))));
		tmpCyclist.add(new Cyclist(13, "Laura", "Daniela", "Perez", LocalDate.parse("1992-11-05"), Gender.FEMALE, Teams.TINKOFF, LocalTime.parse("03:33:10"),determineAgeRange(LocalDate.parse("1992-11-05"))));
		tmpCyclist.add(new Cyclist(14, "Juan","Sebastian", "Celis", LocalDate.parse("1996-09-28"), Gender.MALE, Teams.SKY, LocalTime.parse("05:11:38"),determineAgeRange(LocalDate.parse("1996-09-28"))));
		tmpCyclist.add(new Cyclist(15, "Nicolas","David", "Sierra", LocalDate.parse("1985-08-23"), Gender.MALE, Teams.TEAM_DIMENSION, LocalTime.parse("03:44:22"),determineAgeRange(LocalDate.parse("1985-08-23"))));

		return tmpCyclist;
	}
	
}
