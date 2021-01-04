import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class dbconnector implements IDATENHALTUNG {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs = null;
	Scanner scan = new Scanner(System.in);
	String url, address, user, password;
	
	public dbconnector() {
		initDB();
	}

	private void initDB() {
		try{
		System.out.println("What is the DB Server Address");
		address = scan.next();
		url = "jdbc:mysql://" + address + ":3306/";
		System.out.println("What is the DB User");
		user = scan.next();
		System.out.println("What is the DB Password");
		password = scan.next();
		} catch (Exception e){
			System.out.println("Perfekt direkt nen error :)");
		}
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        } catch (Exception ex) {
            System.out.println("Driver error: \n "+ ex.getMessage());
		}
		
		System.out.println("Do you want to create new DB? (y/n)");
		char abfrage = scan.next().charAt(0);
		if((abfrage == 'y') || (abfrage == 'Y')){
		String dbName = "FahrzeugeDieRollen";

		 try {
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + dbName + "`");
		} catch (SQLException e) {
			System.out.println(" DB Create error 1:" + e.getMessage()); 
		}

		 try {
			stmt.executeUpdate("USE `" + dbName + "`");
		} catch (SQLException e) {
			System.out.println(" DB Create error 2:" + e.getMessage());
		}

		 try {
			 stmt.executeUpdate("SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO'; ");
		} catch (SQLException e) {
			System.out.println(" DB Create error 3:" + e.getMessage());
		}

		 try {
			 stmt.executeUpdate("CREATE TABLE `"+ dbName +"`.`person` ( `kundennummer` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(60) NOT NULL , `geschlecht` CHAR NOT NULL , PRIMARY KEY (`kundennummer`)) ENGINE = InnoDB;");
			 stmt.executeUpdate("CREATE TABLE `"+ dbName +"`.`fahrzeug` ( `fahrzeugnummer` INT NOT NULL AUTO_INCREMENT , `marke` VARCHAR(60) NOT NULL , `modell` VARCHAR(60) NOT NULL , `farbe` VARCHAR(60) NOT NULL , `wert` INT NOT NULL , `kundennummer` INT NOT NULL , PRIMARY KEY (`fahrzeugnummer`)) ENGINE = InnoDB;");
		} catch (SQLException e) {
			System.out.println(" DB Create error 4:" + e.getMessage());
		}
		} else if ((abfrage == 'n') || (abfrage == 'N')) {
			return;
		}else {
			System.exit(1);
		}
		 return;
		}
		
	
	@Override
	public void addPerson(String name, char geschlecht) {
		// TODO Auto-generated method stub
		try {
			stmt.executeUpdate("USE `FahrzeugeDieRollen`");
			stmt.executeUpdate("INSERT INTO person (name, geschlecht) VALUES ('" + name + "', '" + geschlecht + "');");
			rs.next();
			rs = stmt.executeQuery("SELECT kundennummer FROM person WHERE '"+ name +"' = name AND '"+ geschlecht +"' = geschlecht;");
			while(rs.next()) {
			System.out.println("Kundennummer: "+ rs.getInt("kundennummer"));
			}
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
