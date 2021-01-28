package fr.francois.ecole.accessBdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.francois.ecole.bdd.ConnectionUtils;
 
public class PrepareDataExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

// Get a connection
		Connection connection = ConnectionUtils.getMyConnection();

// Create a SQL statement with two parameters (?)
		String sql = "Select id, nom, prenom from eleves where prenom like ? and id = ? ";

// Create a PreparedStatement object.
		PreparedStatement pstm = connection.prepareStatement(sql);

// Set value for the first parameter (First '?')
		pstm.setString(1, "%D");

// Set value for the second parameter (Second '?')
		pstm.setInt(2, 1);

		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			System.out.println(" ---- ");
			System.out.println("id : " + rs.getInt("id"));
			System.out.println("prenom : " + rs.getString(2));
		}

		System.out.println();
		System.out.println("Set other parameters ..");

// Reuse PreparedStatement
// Set other values
		pstm.setString(1, "F%");
		pstm.setInt(2, 1);

// Execute statement.
		rs = pstm.executeQuery();

		while (rs.next()) {
			System.out.println(" ---- ");
			System.out.println("id : " + rs.getInt("id"));
			System.out.println("prenom : " + rs.getString(3));
		}
	}
}
