package Java_CompFinal.copy;

import java.util.Scanner;

/**
 * This class handles all the interactions of the application
 */
public class RunnersApp {

    static Scanner sc = new Scanner(System.in);

    /**
     * Instantiates MarathonRace class which is the core class of this application
     */
    public static void main(String[] args) {
        Race race = new Race();

        String fileName;

            int answer = 0;
            while (answer != 5) {
                System.out.println("Welcome to the Marathon Race Runner Program");// Displays welcome comment
                System.out.println();
                System.out.println("Select your data source : \n"); // Displays the program menu
                System.out.println("1.  Derby database \n"
                        + "2.  XML file       \n"
                        + "3.  Text file      \n"
                        + "4.  Default two runners\n"
                        + "5.  Exit\n");
                answer = race.getNumber(1,5);

                if (answer == 1) { // Derby database option
                    race.createRunners(null, "db");
                    race.startRace("db");
                    race.waitPress();
                }
                else if (answer == 2) { // Xml file option
                    fileName = Validator.getString(sc, "Enter XML file name: ");
                    race.createRunners(fileName, "xml");
                    race.startRace("xml");
                    race.waitPress();
                }
                else if (answer == 3) { // Text file option
                    fileName = Validator.getString(sc, "Enter TXT file name: ");
                    race.createRunners(fileName, "txt");
                    race.startRace("txt");
                    race.waitPress();
                }
                else if (answer == 4) { // Default two runners option
                    race.startRace("default");
                    race.waitPress();
                }
                else if (answer == 5) { // Exits this application
                    System.out.println("Thank you for using my Marathon Race Program");
                    break;
                }
            }
            
    }
}






