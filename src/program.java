import java.util.Scanner;

public class program {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Kunden-/Fahrzeugmanagement");
		System.out.println("TUI(T) oder GUI(G)?");
		char menu = scan.next().charAt(0);
		switch(menu) {
			case 't':
			case 'T':
				new TUI(new dbconnector());
			break;
			case 'g':
			case 'G':
				new GUI(new dbconnector());
			break;
			default:
				System.exit(1);
			//new TUI(new XML());
			//new GUI(new DB());
			//new GUI(new XML());
		}
	}

}
