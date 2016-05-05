package chlorophyll;

import java.sql.Connection;
import java.awt.Color;
import java.awt.image.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/** this class connects to the database and returns the arrays of longitude and latitude points. 
 * The points for each month are stored in a different table.
 * 
 *
 */
public class Connect {

	/** The name of the MySQL account to use*/
	private final String userName = "b5fc8fcedc1161";

	/** The password for the MySQL account */
	private final String password = "1d92f039";

	/** The name of the server running MySQL */
	private final String serverName = "us-cdbr-azure-west-c.cloudapp.net";

	/** The port of the MySQL server ) */
	private final int portNumber = 3306;

	/** The name of the database containing the chlorophyll data  */
	private final String dbName = "chlorophyll";
	
	/** The name of the default table to test with (this table represents the data for April */
	private String tableName = "testcsv4replaced";
	
	//creates an image that will be used to build the picture as well as a numerical value representing
	//the table number being used
	BufferedImage bi = null;
	public int numtable = 4;
	
	/**Chooses the table to use based on the value selected in the Chlorophyll servlet
	 * (this value is passed as an integer when a new connecteion is created
	 * @param i
	 */
	public void setTable(int i){
		
		numtable = i;
		if(numtable == 4){
			tableName = "testcsv4replaced";
		}else if(numtable == 5){
			tableName = "testcsv5replaced";
		}else if(numtable == 6){
			tableName = "testcsv6selected";
		}else if(numtable == 1){
			tableName = "testcsv1replaced";
		}else if(numtable == 3){
			tableName = "testcsv3replaced";
		}else if(numtable == 2){
			tableName = "testcsv2replaced";
		}
	}
	
	/** this establishes the connection to the database
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		
		//creates values for the connection and properties
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		//establishes the connection and stores it in conn
		try{
			Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * Run a SQL command to update the database if needed
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and return the double array of pigment values at latitude/longitude points 
	 */
	public double[][] run() throws SQLException{

		//creates an empty array to return if the connection fails
		double [][] fake = new double[0][0];
		
		// Connect to MySQL
		Connection conn = null;
		try {
			
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			
		}

		//builds the statement and query to run, selecting all of the data in the table that was chosen
		Statement stmt = null;
		
		String query = "SELECT * FROM " + dbName + "." + tableName;
	
		/** creates and runs statement and fills the double array with the returned data
		 * 
		 */
		try {
		    stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    System.out.println("Successfully at try statement and now returning image");
		    
		    double [][] nums = new double[337][482];
		    int r = 1;
		    while(rs.next()){
				
				for(int i = 0; i < 482; i++){
					Double db = rs.getDouble(i + 1);
					
					nums[r][i] = db;
				}
				r++;
				
		    }
		    
		    conn.close();
		    return nums;
		   
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			
		}finally{
			if(stmt != null){ stmt.close();}
		} 
	return fake;
	}
	
	
	
	}
	


