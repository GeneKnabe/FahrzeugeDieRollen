import java.util.ArrayList;
public interface IDATENHALTUNG {
	public int addPerson(String name, char geschlecht);
	public void delPerson(int kundennummer);
	public person getPerson(int kundennumer);
	public void addFahrzeug(String markeM, String modellM, String farbeM, int wertM, int besitzerM);
	public void delFahrzeug(int fahrzeugnummer);
	public fahrzeug getFahrzeug(int fahrzeugnumer);
	public ArrayList<person> getPersonen();
	public ArrayList<fahrzeug> getFahrzeuge(int Kundennummer);
}
