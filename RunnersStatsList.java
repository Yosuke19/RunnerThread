package Java_CompFinal.copy;
import java.util.ArrayList;

/**
 * This class handles runners' stats and default options of the thread
 */
public class RunnersStatsList {
	private ArrayList<RunnerThread> runners;

	/**
	 * Constructor has ArrayList which stores each runner
	 */
	public RunnersStatsList() {

		runners = new ArrayList<>();
	}

	/**
	 * Returns runners' list.
	 *
	 * @return An ArrayList with each instance
	 */
	public ArrayList<RunnerThread> getRunners() {
		return runners;
	}

	/**
	 * Creates default instances that have two runners
	 *
	 * @return A list of default instances
	 */
	public ArrayList<RunnerThread> getDefaultRunners() {
		RunnerThread hare = new RunnerThread("Hare", 100, 90);
		RunnerThread tortoise = new RunnerThread("Tortoise", 10, 0);
		runners.add(hare);
		runners.add(tortoise);
		return runners;
	}

	/**
	 * Creates instances and adds into the list. This method is used for adding
	 * runners' data from the databases
	 *
	 * @param name  runner's name
	 * @param speed runner's speed
	 * @param rest  runner's rest
	 */
	public void addRunners(String name, double speed, double rest) {
		RunnerThread tr = new RunnerThread(name, speed, rest);
		runners.add(tr);
	}

	/**
	 * Clears the runners list.
	 */
	public void clearRunners() {
		runners.clear();
	}

}