package Java_CompFinal.copy;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class operates all the classes for this program
 */
public class Race {

	private static ArrayList<RunnerThread> runners;
	private DerbyDB db; // For handling database access
	private XmlFile xml; // For handling xml file
	private TextFile txt; // For handling text file
	private RunnersStatsList runList; // For storing/exposing runners objects
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Class constructor is one runList
	 */
	public Race() {

		runList = new RunnersStatsList();
	}

	/**
	 * Sets runners from the RunnersStatsList instance and starts threads
	 *
	 * @param s User chooses a type of data such as derby, xml or text
	 */
	public void startRace(String s) {
		if (s.equals("default"))
			runners = runList.getDefaultRunners();
		else
			runners = runList.getRunners();

		for (RunnerThread th : runners) {
			th.setDaemon(true);
			th.start();
		}
		for (RunnerThread th : runners) {
			try {
				th.join();
			} catch (InterruptedException e) { // Exception handling
				System.out.println("The race was interrupeted.");
				break;
			}
		}
		runList.clearRunners();
	}

	public void waitPress() {
		// Waits until any key from the user is input
		System.out.println();
		System.out.print("Press any key to continue...");
		while (true) {
			if (sc.hasNextLine())
				sc.nextLine();
			break;
		}
	}

	/**
	 * Sets runners' data based on the data user selected
	 *
	 * @param filename A file name user input
	 * @param s        A data source
	 * @return True if the the data retrieves and creates from a file that user
	 *         input
	 */
	public boolean createRunners(String filename, String s) {
		if(s.equals("db")) {
			db = new DerbyDB(runList);
			return db.createRunners(); 
		} else if (s.equals("txt")) {
			txt = new TextFile(runList);
			return txt.createRunnersTXT(filename);
		} else if (s.equals("xml")) {
			xml = new XmlFile(runList);
			return xml.createRunnersXML(filename);
		}
		return false;
	}

	/**
	 * Calls when winner finishes the race. Interrupts all threads to concede the
	 * race ends.
	 *
	 * @param winner The name of the winner. winner name is gotten from
	 *               RunnersStatsList class
	 */
	public static void finishRace(String winner) {
		System.out.println();
		System.out.println("The race is over! " + winner + " is the winner.\n");
		System.out.println(winner + ": You beat me fair and square.");
		for (RunnerThread th : runners) {
			th.interrupt();
		}
	}

	public int getNumber(int min, int max) {
		String prompt = "Enter your choice: ";
		int choice = Validator.getRange(prompt, min, max);
		return choice;
	}
}
