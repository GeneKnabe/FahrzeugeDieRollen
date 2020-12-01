import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class dbconnector implements IDATENHALTUNG {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs = null;
	
	public dbconnector() {
		initDB();
	}

	private void initDB() {
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            
            stmt = conn.createStatement();
        } catch (Exception ex) {
            // handle the error
        }
		return;
	}
	
	@Override
	public void addPerson(String name, char geschlecht) {
		// TODO Auto-generated method stub
		try {
			rs = stmt.executeQuery("INSERT INTO kunden (name, geschlecht) VALUES (" + name + ", " + geschlecht + ");");
			} catch (Exception ex) {
				System.out.println(ex);
			}
	}

	@Override
	public void delPerson(int kundennummer) {
		// TODO Auto-generated method stub
		try {
			rs = stmt.executeQuery("DELETE FROM kunden WHERE kundennummer = " + kundennummer + ";");
			rs = stmt.executeQuery("DELETE FROM fahrzeuge WHERE besitzer = " + kundennummer + ";");
			} catch (Exception ex) {
				System.out.println(ex);
			}
	}
	@Override
	public fahrzeug getFahrzeug(int fahrzeugnummer) {
		try {
			rs = stmt.executeQuery("Select * from Fahrzeuge where fahrzeugnummer is" + fahrzeugnummer + ";");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			return null;
	}

	@Override
	public person getPerson(int kundennummer) {
		String name = "dummy";
		char geschlecht = 'm';
		try {
		rs = stmt.executeQuery("Select * from Kunden where kundennummer is" + kundennummer + ";");
		name = rs.getString("name");
		geschlecht = rs.getString("geschlecht").charAt(0);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		person selectedPerson = new person(name, kundennummer, geschlecht);
		return selectedPerson;
	}
	@Override
	public void addFahrzeug(String markeM, String modellM, String farbeM, int wertM, int besitzerM) {
		try {
			rs = stmt.executeQuery("INSERT INTO fahrzeuge (besitzer, marke, modell, farbe, wert) VALUES ("+ besitzerM + ", " + markeM + ", "+ modellM +", "+ farbeM +", "+ wertM +");");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return ;
	}
	@Override
	public void delFahrzeug(int fahrzeugnummer) {
			try {
			rs = stmt.executeQuery("DELETE FROM fahrzeuge WHERE fahrzeugnummer = " + fahrzeugnummer + ";");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		return ;
	}

	@Override
	public ArrayList<person> getPersonen() {
		ArrayList<person> personen = new ArrayList<>();
		return personen;
	}
	
	@Override
	public ArrayList<fahrzeug> getFahrzeuge(int Kundennummer) {
		ArrayList<fahrzeug> fahrzeug = new ArrayList<>();
		return fahrzeug;
	}

}
