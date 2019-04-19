package Java_CompFinal.copy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles the Derby database connection and retrieves runners'
 * names, speeds, and rest percentages
 */

public class DerbyDB {

	private static Connection conn;
	private RunnersStatsList runList;

	public DerbyDB(RunnersStatsList runList) {
		this.runList = runList;
		createConnection();
	}

	/**
	 * Interface is set for accessing this database
	 */

	public boolean createConnection() {
		
	    try {
	    	String dbDirectory = "Resources"; 
			System.setProperty("derby.system.home", dbDirectory);
			String url = "jdbc:derby:BineetDB;create=true";

	        conn = DriverManager.getConnection(url);
	    } 
	    catch (Exception except) {
	        except.printStackTrace();
		}
		return false;
	}

	/**
	 * Disconnect the connected database
	 * 
	 * @return True' if the connection is disconnected correctly
	 */
	static boolean disconnect() {
		try {
			String shutdownURL = "jdbc:derby:;shutdown=true";

			DriverManager.getConnection(shutdownURL);
		} catch (SQLException e) {
			if (e.getMessage().equals("Derby system shutdown."))
				return true;
		}
		return true;
	}

	/**
	 * Creates runners from the derby database
	 * 
	 * @return True' if the the data retrieves and creates from the derby database
	 */
	public boolean createRunners() {
		String query = "SELECT * FROM RunnersDB";
		try {
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String name = rs.getString("Name");
					double speed = rs.getDouble("RunnersSpeed");
					double rest = rs.getDouble("RestPercentage");
					runList.addRunners(name, speed, rest);
				}
				rs.close();
				return true;
			}
			return false;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		finally {
			disconnect();
		}
	}
}


