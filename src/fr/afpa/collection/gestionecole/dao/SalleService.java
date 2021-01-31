package fr.afpa.collection.gestionecole.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.afpa.collection.gestionecole.metier.Salle;
import fr.francois.ecole.bdd.ConnectionUtils;

/*
 * Créer la classe SalleService qui implémente l'interface IDao. 
 * Dans cette classe 
 * les données seront stockés dans une collection de type List.
 * */
public class SalleService implements IDao<Salle> {

	public List<Salle> listeSalle = new ArrayList<Salle>();

	// Les methodes de CRUD : Create , Read Update et delete
	@Override
	public boolean create(Salle salle) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlIntoSalle = "Insert into salle (code, libelle) values ('" + salle.getCode() + "','"
					+ salle.getLibelle() + "')";

			int rowCount = statement.executeUpdate(sqlIntoSalle);

			
			if (salle.getEleveId() != 0) {

				String sqlIntoEleveSalle = "Insert into eleveSalle (eleveId, salleId) values ('" + salle.getEleveId()
						+ "','" + salle.getId() + "')";
				rowCount += statement.executeUpdate(sqlIntoEleveSalle);
			}
			System.out.println("Insert salle Count affected = " + rowCount);

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
	public boolean delete(Salle salle) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlDeleteSalle = "Delete from salle where code = '" + salle.getCode() + "'";

			int rowCount = statement.executeUpdate(sqlDeleteSalle);

			System.out.println("Delete salle Count affected = " + rowCount);

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
	public boolean update(Salle salle) {

		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlUpdateSalle = "Update salle Set code ='" + salle.getCode() + "', libelle ='" + salle.getLibelle()
					+ "'";

			System.out.println(sqlUpdateSalle);

			int rowCount = statement.executeUpdate(sqlUpdateSalle);

			System.out.println("update salle Count affected = " + rowCount);

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
	public Salle findById(int id) {
		Salle salleId = new Salle(null, null);
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String sqlFindId = "Select id, code, libelle From salle Where id ='" + id + "'";

			System.out.println(sqlFindId);

			// Execute SQL statement returns a ResultSet object.
			ResultSet rs = statement.executeQuery(sqlFindId);

			while (rs.next()) {
				// Get value of column 2

				int id1 = rs.getInt(1);

				String code = rs.getString(2);

				// Then get the value of column 1.

				String libelle = rs.getString(3);

				salleId.setCode(code);
				salleId.setLibelle(libelle);
				salleId.setId(id1);

				return salleId;
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
		return salleId;
	}

	public Salle findIdByName(String fbn) {
		Salle salle = new Salle(null, null);
		System.out.println(fbn);
		for (Salle s : findAll()) {

			if (fbn.equals(s.getCode())) {
				salle.setId(s.getId());
				salle.setCode(s.getCode());
				salle.setLibelle(s.getLibelle());
			}
		}
		return salle;
	}

	@Override
	public List<Salle> findAll() {

		try {

			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql = "Select id, code, libelle, from salle";

			// Execute SQL statement returns a ResultSet object.
			Salle salle = null;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				salle = new Salle(null, null);

				// Then get the value of column 1.
				int id = rs.getInt(1);

				salle.setId(id);

				String code = rs.getString(2);

				salle.setCode(code);

				String libelle = rs.getString(3);

				salle.setLibelle(libelle);

				listeSalle.add(salle);

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
		return listeSalle;
	}

}