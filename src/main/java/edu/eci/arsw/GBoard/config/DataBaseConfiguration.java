package edu.eci.arsw.GBoard.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import org.apache.commons.dbcp2.*;
import org.springframework.stereotype.Component;

@Component
public class DataBaseConfiguration {
	
	private static BasicDataSource ds= new BasicDataSource();
	
	static {
		ds.setUrl("jdbc:postgresql://ec2-54-197-232-203.compute-1.amazonaws.com/dakats3qnkgbba?sslmode=require&user=hqzxpggcwmdlwy&password=32e7c7838d0ff62a1ea41ea8e60ab38b428834dda464d06862ceff9ecc70f2a1");
		ds.setUsername("hqzxpggcwmdlwy");
		ds.setPassword("32e7c7838d0ff62a1ea41ea8e60ab38b428834dda464d06862ceff9ecc70f2a1");
		ds.setMinIdle(1);
		ds.setMaxIdle(5);
	}
	
	public BasicDataSource getDataSource() {
		return ds;
	}
	
	
	/*public static void main(String [] args) throws URISyntaxException, SQLException {
	  
		  Connection connection = null;
		  try
		  {
		      connection = ds.getConnection();
		      Statement stmt = connection.createStatement();
		      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
		      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
		      while (rs.next()) {
		          System.out.println("Read from DB: " + rs.getTimestamp("tick") + "\n");
		      }
		      
		  }
		  catch (SQLException e)
		  {
		      System.out.println(e.getMessage());
		  }
		  finally
		  {
		      if (connection != null)
		      {
		          try { connection.close(); } catch (SQLException e) {}
		      }
		  }
		  
		  
	}*/
    
 
}