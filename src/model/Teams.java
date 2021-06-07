package model;

public enum Teams {
	
	MOVISTAR("MOVISTAR"), KATUSHA("Katusha"), TINKOFF("TINKOFF"), BMC("BMC"), SKY("SKY"),
	TEAM_DIMENSION("DIMENSION"), TEAM_ARKEA("ARKEA"), TEAM_NOVO("NORDISK"), CAJA_RURAL("RURAL"), BURGOS("BURGOS"), IAMCYCLING("IAM CYCLING");
	
	private String teamName;


	private Teams(String name) {
		this.teamName = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setName(String name) {
		this.teamName = name;
	}
}
