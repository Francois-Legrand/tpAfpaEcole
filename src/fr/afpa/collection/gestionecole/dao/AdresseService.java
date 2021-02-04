package fr.afpa.collection.gestionecole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.collection.gestionecole.metier.Adresse;

import fr.francois.ecole.bdd.ConnectionUtils;

public class AdresseService implements IDao<Adresse> {

	public List<Adresse> listeAdresse = new ArrayList<Adresse>();

	@Override
	public boolean create(Adresse adresse) {
		try {

			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlIntoAdresse = "Insert into adresse (numRue, nomRue, codePostale, ville, pays) values ('"
					+ adresse.getNumRue() + "','" + adresse.getNomRue() + "','" + adresse.getCodePostale() + "','"
					+ adresse.getVille() + "','" + adresse.getPays() + "')";

			int rowCount = statement.executeUpdate(sqlIntoAdresse);

			String sqlSelectId = "SELECT MAX(id) AS max_id FROM adresse";
			
			Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = statement2.executeQuery(sqlSelectId);
			
			int adresseId = 0;
			while (rs.next()) {

				adresseId = rs.getInt("max_id");

			}

			if (adresse.getEleveId() != 0) {
				if (adresseId == 0) {
					
					adresseId += 1;

					String sqlIntoEleveAdresse = "Insert into eleveAdresse (eleve_id, adresse_id) values ('"
							+ adresse.getEleveId() + "','" + adresseId + "')";

					rowCount += statement.executeUpdate(sqlIntoEleveAdresse);
				} else {

					String sqlIntoEleveAdresse = "Insert into eleveAdresse (eleve_id, adresse_id) values ('"
							+ adresse.getEleveId() + "','" + adresseId + "')";

					rowCount += statement.executeUpdate(sqlIntoEleveAdresse);
				}

			}

			System.out.println("Insert adresse Count affected = " + rowCount);

			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Adresse adresse) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlDeleteAdresse = "Delete from adresse where id = '" + adresse.getId() + "'";

			int rowCount = statement.executeUpdate(sqlDeleteAdresse);

			System.out.println("Delete adresse Count affected = " + rowCount);

			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Adresse adresse) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlUpdateAdresse = "Update adresse Set nomRue ='" + adresse.getNomRue() + "', numRue ='"
					+ adresse.getNumRue() + "', codePostale = '" + adresse.getCodePostale() + "', ville = '"
					+ adresse.getVille() + "', pays = '" + adresse.getPays() + "' Where nomRue = '"
					+ adresse.getNomRue() + "'";
			System.out.println(sqlUpdateAdresse);

			int rowCount = statement.executeUpdate(sqlUpdateAdresse);

			System.out.println("update adresse Count affected = " + rowCount);

			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Adresse findById(int id) {
		
		Adresse adresseId = new Adresse(id, null, id, null, null, id);
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String sqlFindId = "Select id, nomRue, numRue, codePostale, ville, pays From adresse Where id ='" + id
					+ "'";

			System.out.println(sqlFindId);

			// Execute SQL statement returns a ResultSet object.
			ResultSet rs = statement.executeQuery(sqlFindId);

			while (rs.next()) {
				

				int id1 = rs.getInt(1);
				String nomRue = rs.getString(2);
				int numRue = rs.getInt(3);
				int codePostal = rs.getInt(4);
				String ville = rs.getString(5);
				String pays = rs.getString(6);

				adresseId.setNomRue(nomRue);
				adresseId.setNumRue(numRue);
				adresseId.setCodePostale(codePostal);
				adresseId.setVille(ville);
				adresseId.setPays(pays);
				adresseId.setId(id1);

				return adresseId;
			}

			// Close connection.
			connection.close();

			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresseId;
	}

	@Override
	public List<Adresse> findAll() {
		try {

			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String sql = "Select id, nomRue, numRue, codePostale, ville, pays From adresse";

			// Execute SQL statement returns a ResultSet object.
			Adresse adresse = null;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				adresse = new Adresse(0, null, 0, null, null, 0);

				// Then get the value of column 1.
				int id = rs.getInt(1);
				String nomRue = rs.getString(2);
				int numRue = rs.getInt(3);
				int codePostal = rs.getInt(4);
				String ville = rs.getString(5);
				String pays = rs.getString(6);

				adresse.setId(id);
				adresse.setNomRue(nomRue);
				adresse.setNumRue(numRue);
				adresse.setCodePostale(codePostal);
				adresse.setVille(ville);
				adresse.setPays(pays);

				listeAdresse.add(adresse);

			}
			// Close connection.
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeAdresse;
	}

}
