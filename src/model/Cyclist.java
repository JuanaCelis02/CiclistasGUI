package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Cyclist {
	
	private int bibNumber;
	private String name;
	private String secondName;
	private String lastName;
	private LocalDate birthDate;
	private Gender gender;
	private Teams team;
	private LocalTime totalRaceTime;
	private int age;

	public Cyclist() {


	}

	public Cyclist(int bibNumber, String name, String secondName, String lastName,Gender gender, LocalTime time, Teams team, int age) {

		this.bibNumber = bibNumber;
		this.name = name;
		this.secondName = secondName;
		this.lastName = lastName;
		this.gender = gender;
		this.team = team;
		this.totalRaceTime = time;
		this.age = age;
	}

	public Cyclist(int bibNumber, String name, String lastName, LocalDate birthDate, Gender gender, Teams team,
				   LocalTime totalRaceTime, int age){
		this.bibNumber = bibNumber;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.team = team;
		this.totalRaceTime = totalRaceTime;
		this.age = age;
	}

	public Cyclist(int bibNumber, String name, String secondName, String lastName, LocalDate birthDate, Gender gender, Teams team,
				   LocalTime totalRaceTime, int age){
		this.bibNumber = bibNumber;
		this.name = name;
		this.secondName = secondName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.team = team;
		this.totalRaceTime = totalRaceTime;
		this.age = age;
	}
	public Cyclist( String name, String lastName, LocalDate birthDate, Gender gender, Teams team,
				   LocalTime totalRaceTime){
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.team = team;
		this.totalRaceTime = totalRaceTime;

	}
	public int getBibNumber() {
		return bibNumber;
	}
	public void setBibNumber(int bibNumber) {
		this.bibNumber = bibNumber;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Teams getTeam() {
		return team;
	}
	public void setTeam(Teams team) {
		this.team = team;
	}
	public LocalTime getTotalRaceTime() {
		return totalRaceTime;
	}
	public void setTotalRaceTime(LocalTime totalRaceTime) {
		this.totalRaceTime = totalRaceTime;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}

	public Object[] toObjectVector() {
        return new Object[] {getBibNumber(), getName(), getAge()};
    }

	public Object[] getCyclistData(){
		return new Object[]{this.bibNumber,this.name, this.secondName, this.lastName,this.gender,this.team,this.totalRaceTime,this.age};
	}

	public Object[] getAverageData(){
		return new Object[]{};
	}

	@Override
	public String toString() {
		return "Cyclist{" +
				"bibNumber=" + bibNumber +
				", name='" + name + '\'' +
				", secondName='" + secondName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate=" + birthDate +
				", gender=" + gender +
				", team=" + team +
				", totalRaceTime=" + totalRaceTime +
				", age=" + age +
				'}';
	}
}
