
public class program {

	public static void main(String[] args) {
		System.out.println("Kunden-/Fahrzeugmanagement");
		//new TUI(new dbconnector());
		//new TUI(new XML());
		//new GUI(new DB());
		//new GUI(new XML());
		new GUI(new dbconnector());

	}

}
