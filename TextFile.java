package Java_CompFinal.copy;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class handles reeading and storeing runners data from a text file
 */
class TextFile {
	private RunnersStatsList runList;

	/**
	 * Constractor of this class
	 *
	 * @param runList runList holds all runner objects
	 */
	public TextFile(RunnersStatsList runList) {

		this.runList = runList;
	}

	/**
	 * Creates all runner objects from an xml file
	 *
	 * @param filename reads text file name from user input
	 * @return if the the data retrieves and creates from a text file from user
	 *         input
	 */
	public boolean createRunnersTXT(String filename) {
		String line;
		Path filePath = Paths.get(filename);

		try (BufferedReader in = new BufferedReader(new FileReader(String.valueOf(filePath.getFileName())))) {
			line = in.readLine();
			// Parse and trim text file
			while (line != null) {
				String[] runnerArray = line.split(" ");
				String name = runnerArray[0].trim();
				int speed = Integer.parseInt(runnerArray[1].trim());
				int rest = Integer.parseInt(runnerArray[2].trim());
				runList.addRunners(name, speed, rest); // Creates runners to runList
				line = in.readLine();
			}
			return true;
		}
		// Exception handling
		catch (FileNotFoundException e) {
			System.out.println("File not found\n");
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}