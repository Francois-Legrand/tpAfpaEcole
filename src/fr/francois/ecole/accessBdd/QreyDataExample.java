package fr.francois.ecole.accessBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.francois.ecole.bdd.ConnectionUtils;

public class QreyDataExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// Get Connection
				Connection connection = ConnectionUtils.getMyConnection();

		// Create statement
				// Statement statement = connection.createStatement();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				String sql = "Select nom, prenom from eleves";

		// Execute SQL statement returns a ResultSet object.
				ResultSet rs = statement.executeQuery(sql);
				 // Jump the cursor to last record.
		        boolean last = rs.last();
		         
		        System.out.println("last : "+ last);
		         
		        if(last) {
		            // Print out data of last record
		            System.out.println("nom:" + rs.getString(1));
		            System.out.println("prenom:" + rs.getString(2));
		        }
		         
		        System.out.println("--------------------");
		         
		        // Move cursor to previous record
		        boolean previous =rs.previous();
		        System.out.println("Previous 1: "+ previous);
		         
		        // Move cursor to previous record
		        previous = rs.previous();
		        System.out.println("Previous 2: "+ previous);       
		        //Move cursor to previous record
		        
		        previous = rs.previous();
		        System.out.println("Previous 2: "+ previous);       
				
		        
		        // Fetch in the ResultSet
		        while (rs.next()) {
		            // Get value of column 2
		            String nom = rs.getString(2);
		 
		            // Then get the value of column 1.
		            String prenom = rs.getString(1);
		             
		            //String empName = rs.getString("Emp_Name");
		             
		            System.out.println("--------------------");
		            System.out.println("prenom:" + prenom);
		            System.out.println("nom:" + nom);
		        }
		 
		        // Close connection.
		        connection.close();
		    }
}
