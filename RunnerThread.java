package Java_CompFinal.copy;
import java.util.Random;

/**
 * This class manages threads Each runner is created as each thread Utilizes
 * Random to get a a race result randomly
 */

public class RunnerThread extends Thread {

	private String name;
	private double rest;
	private double speed;
	private Random random;

	/**
	 * Constructor
	 * 
	 * @param name  runner's name
	 * @param speed runner's speed
	 * @param rest  runner's rest
	 */
	public RunnerThread(String name, double speed, double rest) {
		this.name = name;
		this.speed = speed;
		this.rest = rest;
		random = new Random();
	}

	/**
	 * Random manages runners' run or rest In the while loop random number is set
	 * between 1 - 100 and this is used for rest and speed Sets sleep for each move
	 * of the runners Once a runner gets 1000 meters, threads are finished.
	 *
	 */
	@Override
	public void run() {

		int meters = 0;

		Thread ct = Thread.currentThread();
		ct.setName(name);

		while (!ct.isInterrupted()) {
			if (random.nextInt(100) + 1 > rest) { // Sets random number between 1 - 100
				meters += speed; // generates runner's speed to meters
				System.out.println(this.name + ": " + meters); // display runner's distance
			}

			try {
				Thread.sleep(80); // Sets time of runners' move
			} catch (InterruptedException ex) {
				return;
			}

			if (meters >= 1000) { // Once a runner gets 1000 meters, displays a comment and finishes all threads
				System.out.println(this.name + ": I finished!");
				finished(ct);
				return;
			}
		}
	}

	/**
	 * Sets finished conditions dependant on who wins the race. One runner arrives
	 * at 1000 meters then the threads finish This is defined by finishRace method
	 * which is in MarathonRace class
	 *
	 * @param winner
	 */
	synchronized public void finished(Thread winner) {

		if (winner.getName().equals("Hare")) {
			Race.finishRace(this.name);
		} else if (winner.getName().equals("Tortoise")) {
			Race.finishRace(this.name);
		} else if (winner.getName().equals("Dog")) {
			Race.finishRace(this.name);
		} else if (winner.getName().equals("Cat")) {
			Race.finishRace(this.name);
		}

	}
}
