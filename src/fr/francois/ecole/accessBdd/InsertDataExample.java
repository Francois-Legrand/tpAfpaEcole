package fr.francois.ecole.accessBdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import fr.francois.ecole.bdd.ConnectionUtils;

public class InsertDataExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

// Get Connection
		Connection connection = ConnectionUtils.getMyConnection();

		Statement statement = connection.createStatement();

		String sqlIntoSalle = "Insert into salles (code, libelle) " + " values ('dfdf','Salle de dhistoire')";
		
		//String sqlIntoEleve = "Insert into eleves (prenom, nom) " + " values ('jean','aimarre')";
		
		//UPDATE table
		//SET nom_colonne_1 = 'nouvelle valeur'
		//WHERE condition
		
		
		//String sqlUpdateEleve = "Update eleves Set prenom = 'François' Where id = '1'";
		
		
		//String sqlUpadate = "Update Employee Set emp_Name = 'François' Where emp_Id = '7369'";
		
		String sqlSelect = "Select * from Employee";
		
		// Execute statement
		// executeUpdate(String) using for Insert, Update, Delete statement.

		String sqlDeleteSalle = "Delete from salles Where id = '1'";
		
		int rowCount = statement.executeUpdate(sqlDeleteSalle);
	
		
		//int rowCount2 = statement.executeUpdate(sqlIntoEleve);
	
		//rowCount += statement.executeUpdate(sqlIntoSalle);

		System.out.println("Row Count affected = " + rowCount);
	}
}
