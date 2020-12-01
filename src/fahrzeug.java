
public class fahrzeug {
	private String farbe,modell,marke;
	private int fahrzeugnummer,wert,besitzer;
	
	
	public fahrzeug(String markeM,String modellM, String farbeM, int fahrzeugnummerM, int wertM, int besitzerM) {
		farbe = farbeM;
		modell = modellM;
		marke = markeM;
		fahrzeugnummer = fahrzeugnummerM;
		wert = wertM;
		besitzer = besitzerM;
	}
	
	public void setFarbe(String variableToSet) {
		farbe = variableToSet;
	}
	public void setMarke(String variableToSet) {
		marke = variableToSet;
	}
	public void setModell(String variableToSet) {
		modell = variableToSet;
	}
	public void setFahrzeugnummer(int variableToSet) {
		fahrzeugnummer = variableToSet;
	}
	public void setWert(int variableToSet) {
		wert = variableToSet;
	}
	public void setBesitzer(int variableToSet) {
		besitzer = variableToSet;
	}
	public String getFarbe() {
		return farbe;
	}
	public String getModell() {
		return modell;
	}
	public String getMarke() {
		return marke;
	}
	public int getFahrzeugnummer() {
		return fahrzeugnummer;
	}
	public int getWert() {
		return wert;
	}
	public int getBesitzer() {
		return besitzer;
	}
}
