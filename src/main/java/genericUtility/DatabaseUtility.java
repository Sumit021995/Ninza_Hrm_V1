package genericUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Reporter;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	
	Connection conn;
	/**
	 * This is a generic method to connect with mySQL database through JDBC api
	 * @throws SQLException
	 * @throws IOException
	 */
	public void getDatabaseConnection() throws SQLException, IOException
	{
		// Load / Register the database driver
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		conn = DriverManager.getConnection(new PropertiesUtility().getDataFromPropertiesFile("mysqlUrl"),"root","root");
		Reporter.log("✅ Database Connection is Done",true);
		
	}
	/**
	 * This is a generic method to get data form mySQL database from SELECT query.
	 * @param query
	 * @param columnIndex
	 * @throws SQLException
	 * @throws IOException
	 */
	public void getDataFromDatabseFromSelectQuery(String query , int columnIndex) throws SQLException, IOException
	{
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next())
		{
			String stringData = resultSet.getString(columnIndex);
			System.out.println(stringData);
		}
		
	}
	/**
	 * This is a generic method to close the database connection
	 */
	public void closeDatabaseConnection() 
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
