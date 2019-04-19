package Java_CompFinal.copy;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * This class handles reading and storing runners' data from an xml file
 */
public class XmlFile {

        private RunnersStatsList runList;

    /**
     * Constructor of this class
     *
     * @param runList   runList holds all runner objects
     */
    public XmlFile(RunnersStatsList runList) {

        this.runList = runList;
    }

    /**
     * Creates all runner objects from an xml file
     *
     * @param filename  reads xml file name from user input
     * @return  if the the data retrieves and creates from an xmlfile from user input
     */
    public boolean createRunnersXML(String filename) {
        try{
            // Reads file
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            FileReader file = new FileReader(filename);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(file);

            String name = "";
            double speed = 0;
            double rest = 0;

            // Creates runners from xml file
            while (reader.hasNext()) {
                int eventType = reader.getEventType();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        if (elementName.equals("Runner")) {
                            name = reader.getAttributeValue(0);
                        }
                        if (elementName.equals("RunnersMoveIncrement")) {
                            speed = Double.parseDouble(reader.getElementText());
                        }
                        if (elementName.equals("RestPercentage")) {
                            rest = Double.parseDouble(reader.getElementText());
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        // Adds runners in the order as name, speed and rest
                        if (elementName.equals("Runner")) {
                            runList.addRunners(name, speed, rest);
                        }
                        break;
                    default:
                        break;
                }
                reader.next();
            }
            return true;
        }
        // Exception handling
        catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND\n");
            return false;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }


}