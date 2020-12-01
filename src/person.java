
public class person {
	private String name;
	private int kundennummer;
	private char geschlecht;
	
	public person(String name1, int kundennummer1, char geschlecht1) {
		name = name1;
		kundennummer = kundennummer1;
		geschlecht = geschlecht1;
	}
	
	public void setName(String nametoset) {
		name = nametoset;
		
	}
	public void setKundennummer(int knrtoset) {
		kundennummer = knrtoset;
		
	}
	public void setGeschlecht(char geschtoset) {
		geschlecht = geschtoset;
		
	}
	public String getName() {
		return name;
	}
	public int getKundennummer() {
		return kundennummer;
	}
	public char getGeschlecht() {
		return geschlecht;
}
	
}
