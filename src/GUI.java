import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI implements ActionListener {
	
	
	
	private IDATENHALTUNG daten;
	boolean menu = true;
	int kundennummer, wahl;
	person selected;
	Scanner scan = new Scanner(System.in);
	
	
	
		
	private JFrame frame;
	private JPanel panel;
	
	
	
	//Array fuer Combobox
	private String comboboxliste[] = {"Maennlich", "Weiblich", "Divers"}; 
	private String suchenliste[] = {"ID", "Name", "Fahrzeugnr.", "Marke", "Modell", "Farbe", "Wert"}; 
	private String spaltennamen[] = {"ID", "Name", "Geschlecht", "Fahrzeugnr.", "Marke", "Modell", "Farbe", "Wert"};
	//Combobox
	private JComboBox geschlechter;
	private JComboBox suchende;
	
	//Buttons
	private JButton check;
	private JButton add;
	private JButton delete;
	private JButton search;
	
	//Textvariablen sind Singular
	private JLabel user;
	private JLabel name;
	private JLabel geschlecht;
	private JLabel id;
	private JLabel auto;
	private JLabel nummer;
	private JLabel marke;
	private JLabel modell;
	private JLabel farbe;
	private JLabel wert;
	private JLabel suche;
	
	//Textboxvariablen sind Plural
	private JTextField namen;
	private JTextField ids;
	private JTextField nummern;
	private JTextField marken;
	private JTextField modelle;
	private JTextField farben;
	private JTextField werte;
	private JTextField suchen;
	private JTable tabelle;
	
	private DefaultTableModel model;
	
	
	
	//Variable zum übergeben und speichern in der Funktion
	private int USER;
	private String NAME;
	private char GESCHLECHT;
	private int ID;
	private String AUTO;
	private int NUMMER;
	private String MARKE;
	private String MODELL;
	private String FARBE;
	private int WERT;
	
	private String placeholder;

	public GUI(dbconnector db) {
		daten = db;
		
		//Erstellen eine Fensters + Standard Einstellungen
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(600, 500);
		panel.setBorder(BorderFactory.createEmptyBorder(500, 500, 500, 500));
		panel.setLayout(null);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Fahrzeuge die Rollen");
		
		//Text Hinzufuegen
		addText();
		
		//Textboxen zum Schreiben Hinzufuegen
		addTextbox();
		
		//Buttons Hinzufuegen
		addButton();
		
		//Dorpdown Hinzufuegen
		addDropdown();
		
		tabelle = new JTable(new DefaultTableModel(new Object[] {"","","","","","","",""}, 0));
		tabelle.setBounds(10, 240, 550, 200);
		model = (DefaultTableModel) tabelle.getModel();
		model.addRow(spaltennamen);
		panel.add(tabelle);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Aktion vom Button klicken
		if(e.getSource() == check) {
			ids.setText("");

		}
		if(e.getSource() == add) {
			ID = Integer.parseInt(ids.getText());
			NAME = namen.getText();
			placeholder = (String)geschlechter.getSelectedItem();
			GESCHLECHT = placeholder.charAt(0);

			NUMMER = Integer.parseInt(nummern.getText());
			MARKE = marken.getText();
			MODELL = modelle.getText();
			FARBE = farben.getText();
			WERT = Integer.parseInt(werte.getText());
			
			daten.addPerson(NAME, GESCHLECHT);
			daten.addFahrzeug(MARKE, MODELL, FARBE, WERT, ID);
			
			
		}
		if(e.getSource() == delete) {
			
			ID = Integer.parseInt(ids.getText());
			NUMMER = Integer.parseInt(nummern.getText());
			daten.delPerson(ID);
			daten.delFahrzeug(NUMMER);
			
		}		
		if(e.getSource() == search) {
			model.setRowCount(0);
			model.addRow(spaltennamen);
			
		}
	}
	
	public void addText(){
		//Benutzer Text
		user = new JLabel("Benutzer");
		user.setBounds(10, 5, 80, 25);
		panel.add(user);
		
		//id Text
		id = new JLabel("ID");
		id.setBounds(10, 20, 80, 25);
		panel.add(id);
			
		//name Text
		name = new JLabel("Name");
		name.setBounds(100, 20, 160, 25);
		panel.add(name);
		
		//Geschlecht Text
		geschlecht = new JLabel("Geschlecht");
		geschlecht.setBounds(280, 20, 80, 25);
		panel.add(geschlecht);
				
		//Fahrzeug Text
		auto = new JLabel("Fahrzeug das rollt");
		auto.setBounds(10, 95, 130, 25);
		panel.add(auto);
		
		//Fahrzeugnr. Text
		nummer = new JLabel("Fahrzeugnr.");
		nummer.setBounds(10, 115, 100, 25);
		panel.add(nummer);
		
		//Marke Text
		marke = new JLabel("Marke");
		marke.setBounds(100, 115, 100, 25);
		panel.add(marke);
			
		//Modell Text
		modell = new JLabel("Modell");
		modell.setBounds(190, 115, 100, 25);
		panel.add(modell);
		
		//Farbe Text
		farbe = new JLabel("Farbe");
		farbe.setBounds(280, 115, 100, 25);
		panel.add(farbe);
		
		//Wert Text
		wert = new JLabel("Wert");
		wert.setBounds(370, 115, 100, 25);
		panel.add(wert);
		
		//Suche nach: Text
		suche = new JLabel("Suche nach:");
		suche.setBounds(10, 200, 100, 25);
		panel.add(suche);
		
		
	}
	
	public void addTextbox() {
		//ID Textbox
		ids = new JTextField();
		ids.setBounds(10, 40, 80, 25);
		panel.add(ids);
		
		//name Textbox
		namen = new JTextField();
		namen.setBounds(100, 40, 160, 25);
		panel.add(namen);
		
		//Nummer Textbox
		nummern = new JTextField();
		nummern.setBounds(10, 140, 80, 25);
		panel.add(nummern);
		
		//Marke Textbox
		marken = new JTextField();
		marken.setBounds(100, 140, 80, 25);
		panel.add(marken);
		
		//Modell Textbox
		modelle = new JTextField();
		modelle.setBounds(190, 140, 80, 25);
		panel.add(modelle);

		//Farbe Textbox
		farben = new JTextField();
		farben.setBounds(280, 140, 80, 25);
		panel.add(farben);
		
		//Wert Textbox
		werte = new JTextField();
		werte.setBounds(370, 140, 80, 25);
		panel.add(werte);
		
		//Suchen Textbox
		suchen = new JTextField();
		suchen.setBounds(230, 200, 220, 25);
		panel.add(suchen);
			
	}
	
	public void addButton() {
		//Check Button
		check = new JButton("Check");
		check.setBounds(460, 40, 100, 25);
		check.addActionListener(this);
		panel.add(check);
		
		//Hinzufuegen Button
		add = new JButton("Hinzufuegen");
		add.setBounds(460, 110, 100, 25);
		add.addActionListener(this);
		panel.add(add);
		
		//Loeschen Button
		delete = new JButton("Entfernen");
		delete.setBounds(460, 140, 100, 25);
		delete.addActionListener(this);
		panel.add(delete);
		
		//Suchen Button
		search = new JButton("Suchen");
		search.setBounds(460, 200, 100, 25);
		search.addActionListener(this);
		panel.add(search);
	}
	
	public void addDropdown() {
		//Dropdown fuer Geschlecht
		geschlechter = new JComboBox<>(comboboxliste);
		geschlechter.setBounds(280, 40, 100, 25);
		panel.add(geschlechter);
		
		//Dropdown fuer Geschlecht
		suchende = new JComboBox(suchenliste);
		suchende.setBounds(90, 200, 125, 25);
		panel.add(suchende);
	}
	/*
	public static void main(String[] args) {
		new GUI();
		
		
		
		
	}
*/
}
