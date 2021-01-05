import java.util.ArrayList;
import java.util.Scanner;

public class TUI {
	private IDATENHALTUNG daten;
	boolean menu = true;
	int kundennummer, wahl;
	person selected;
	Scanner scan = new Scanner(System.in);
	public TUI(dbconnector db) {
		daten = db;
		initTUI();
	}

	
	private void initTUI() {
		
		while(menu) {
		System.out.println("Kundenverwaltungs Menue");
		System.out.println("Waehle deine Zukunft!");
		System.out.println("1. Add person \n2. Delete person "
				+ "\n3. Select person \n4. Get all persons"
				+ "\n5. Add Vehicle to person \n6. Remove Vehicle from person "
				+ "\nAN INVALID ENTRY WILL KILL THE PROGRAM");
		try {
		wahl = scan.nextInt();
		} catch (Exception e){
			System.out.println("Das war keine Zahl du Mango: " + "\n" + e);
		}
		switch(wahl) {
		case 1: 
			addPerson();
			break;
		case 2:
			System.out.println("Welche Kundennummer hat der Kunde, welcher gelöscht werden soll");
			kundennummer = scan.nextInt();
			daten.delPerson(kundennummer);
			break;
		case 3:
			System.out.println("Welche Kundennummer hat der Kunde?");
			int kundennummer = scan.nextInt();
			selected = daten.getPerson(kundennummer);
			System.out.println(selected.getName() +" : "+ selected.getGeschlecht() + " : " + selected.getKundennummer()+ " aufgewählt");
			break;
		case 4:
			ladeKunden();
			break;
		case 5:
			addCarToKunde();
			break;
		case 6:
			removeCarFromKunde();
			break;
		default:
		menu = false;
			
		}
	}
	}
	private void addPerson() {
			System.out.println("Wier heisst der Kunde?");
			String name = scan.next(); 
			System.out.println("Welches Geschlecht hat der Kunde? (Char)");
			char geschlecht = scan.next().charAt(0);
			kundennummer = daten.addPerson(name, geschlecht);
			System.out.println("Kundennummer ist: "+ kundennummer);
	}
	private void addCarToKunde() {
		 String markeM, modellM,  farbeM;
		 int wertM, besitzerM;	
		 System.out.println("Marke?");
		 markeM = scan.next();
		 System.out.println("Modell?");
		 modellM = scan.next();
		 System.out.println("Farbe?");
		 farbeM = scan.next();
		 System.out.println("Preis des Fahrzeugs?");
		 wertM = scan.nextInt();
		 System.out.println("Kundennumer?");
		 besitzerM = scan.nextInt();
		 int fahrzeugnummer = daten.addFahrzeug( markeM, modellM, farbeM, wertM, besitzerM);
		 System.out.println("Das Fahrzeug hat die nummer "+ fahrzeugnummer +"bekommen");
	}
	private void removeCarFromKunde() {
		System.out.println("Welche Fahrzeugnumer hat das Fahrzeug welches geloescht werden soll?");
		int fahrzeugnummer = scan.nextInt();
		daten.delFahrzeug(fahrzeugnummer);
		return;
	}
	
	private void ladeKunden() {
		System.out.println("Lade Kunden Menue");
		ArrayList<person> alleKunden = daten.getPersonen();
		for(person pers : alleKunden) {
			System.out.println("Kundennummer: " + pers.getKundennummer() + " Name: " + pers.getName()+ " Geschlecht: " + pers.getGeschlecht());
			ArrayList<fahrzeug> AlleFahrzeugeZumKunden = daten.getFahrzeuge(pers.getKundennummer());
			for(fahrzeug zeug : AlleFahrzeugeZumKunden) {
				System.out.println("Fahrzeugnummer: " + zeug.getFahrzeugnummer() +" Marke: " + zeug.getMarke() +" Modell: " + zeug.getModell() +" Farbe: " + zeug.getFarbe() +" Wert:" + zeug.getWert());
			}
		}
	}
	}