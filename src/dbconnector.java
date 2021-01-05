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
		System.out.println("What is the DB Server Address with port (eg. localhost:3306)");
		address = scan.next();
		url = "jdbc:mysql://" + address + "/";
		System.out.println("What is the DB User");
		user = scan.next();
		System.out.println("What is the DB Password");
		password = scan.next();
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
		stmt = conn.createStatement();
		} catch (Exception e){
			System.out.println("Error in the DB connection information or with the JDBC Driver\n" + e.getMessage());
			System.exit(1);
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
			rs = stmt.executeQuery("SELECT kundennummer FROM person WHERE '"+ name +"' = name AND '"+ geschlecht +"' = geschlecht;");
			rs.next();
			System.out.println("Kundennummer: "+ rs.getInt(1));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void delPerson(int kundennummer) {
		// TODO Auto-generated method stub
		try {
			stmt.executeUpdate("USE `FahrzeugeDieRollen`");
			stmt.executeUpdate("DELETE FROM person WHERE kundennummer = '" + kundennummer + "';");
			stmt.executeUpdate("DELETE FROM fahrzeug WHERE kundennummer = '" + kundennummer + "';");
			} catch (Exception ex) {
				System.out.println(ex);
			}
	}
	@Override
	public person getPerson(int kundennummer) {
		String name = "dummy";
		char geschlecht = 'm';
		try {
			stmt.executeUpdate("USE `FahrzeugeDieRollen`");
			rs = stmt.executeQuery("Select * from Kunden where kundennummer is '" + kundennummer + "';");
			name = rs.getString("name");
			geschlecht = rs.getString("geschlecht").charAt(0);
			System.out.println("Kundennummer: "+ kundennummer + " Name: "+ name + " Gender: " + geschlecht);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		person selectedPerson = new person(name, kundennummer, geschlecht);
		return selectedPerson;
	}
	@Override
	public void addFahrzeug(String markeM, String modellM, String farbeM, int wertM, int besitzerM) {
		try {
			stmt.executeUpdate("USE `FahrzeugeDieRollen`");
			stmt.executeUpdate("INSERT INTO fahrzeug (kundennummer, marke, modell, farbe, wert) VALUES ('"+ besitzerM + "', '" + markeM + "', '"+ modellM +"', '"+ farbeM +"', '"+ wertM +"');");
			rs = stmt.executeQuery("SELECT fahrzeugnummer FROM fahrzeug WHERE '"+ markeM +"' = marke AND '"+ modellM +"' = modell AND '"+ farbeM +"' = farbe AND '"+ wertM +"' = wert AND '"+ besitzerM +"' = kundennummer;");
			rs.next();
			System.out.println("Fahrzeugnummer: "+ rs.getInt(1));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return ;
	}
	@Override
	public fahrzeug getFahrzeug(int fahrzeugnummer) {
		try {
			stmt.executeUpdate("USE `FahrzeugeDieRollen`");
			rs = stmt.executeQuery("Select * from fahrzeug where fahrzeugnummer is '" + fahrzeugnummer + "';");
			while(rs.next()) {
				System.out.println("Fahrzeugnummer: "+ rs.getInt(1) + "Marke: " + rs.getString(2) +" Modell: "+ rs.getString(3) +" Farbe:"+ rs.getString(4) +" Wert: "+ rs.getInt(5) + " Kundennummer: "+ rs.getInt(6));
			}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			return null;
	}

	@Override
	public void delFahrzeug(int fahrzeugnummer) {
			try {
				stmt.executeUpdate("USE `FahrzeugeDieRollen`");
				stmt.executeUpdate("DELETE FROM fahrzeug WHERE fahrzeugnummer = '" + fahrzeugnummer + "';");
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
