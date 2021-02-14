package fr.afpa.collection.gestionecole.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fr.afpa.collection.gestionecole.metier.Eleve;
import fr.francois.ecole.bdd.ConnectionUtils;

public class EleveService implements IDao<Eleve> {

	Map<Integer, Eleve> listeEleves = new HashMap<Integer, Eleve>();
	List<Eleve> listeEleve = new ArrayList<Eleve>();

	@Override
	public boolean create(Eleve eleve) {
		
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			
			
				String sqlIntoEleve = "Insert into eleve (nom, prenom, dateNaissance) values ('" + eleve.getPrenom() + "','"
					+ eleve.getNom() + "','" + eleve.getDateNaissance() + "')";
				
				int rowCount = statement.executeUpdate(sqlIntoEleve);
				
				String sqlSelectId = "SELECT auto_increment AS NEXT_ID FROM `information_schema`.`tables` WHERE table_name = \"eleve\" AND table_schema = \"ecole\"";
				
				Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				ResultSet rs = statement2.executeQuery(sqlSelectId);
				
				int eleveId = 0;
				while (rs.next()) {
					
		            eleveId = rs.getInt("NEXT_ID")-1;
		            System.out.println(eleveId+ "auto increment");
		        }
				
				
				String sqlIntoEleveAdresse = "Insert into eleveAdresse (eleve_id, adresse_id) values ('"
						+ eleveId + "','" + eleve.getAdresseId() +"')";
				
				String sqlIntoEleveSalle = "Insert into eleveSalle (eleveId, salleId) values ('"
						+ eleveId + "','" + eleve.getSalleId() +"')";
				
				rowCount += statement.executeUpdate(sqlIntoEleveAdresse);
				rowCount += statement.executeUpdate(sqlIntoEleveSalle);
				
				System.out.println("Insert eleve Count affected = " + rowCount);

			
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
	public boolean delete(Eleve eleve) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			
			String sqlDeleteEleveSalle = "Delete from eleveSalle where eleveId = '" + eleve.getId() + "';";
			System.out.println(sqlDeleteEleveSalle+" sql eleve salle");
			String sqlDeleteEleveAdresse = "Delete from eleveAdresse where eleve_id = '" + eleve.getId() + "'";
			
			String sqlDeleteEleve = "Delete from eleve where id = '" + eleve.getId() + "'";
			

			System.out.println(eleve.getId());
			
			int rowCount = statement.executeUpdate(sqlDeleteEleveAdresse);
			rowCount += statement.executeUpdate(sqlDeleteEleveSalle);
			rowCount += statement.executeUpdate(sqlDeleteEleve);
			
			
			System.out.println("Delete eleve Count affected = " + rowCount);

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
	public boolean update(Eleve eleve) {
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();
			System.out.println(eleve+" update eleve");
			String sqlUpdateEleveAdresse = "Update eleveadresse Set adresse_id ='" + eleve.getAdresseId() + "' Where eleve_id = '" +eleve.getId() + "'";
			
			System.out.println(sqlUpdateEleveAdresse);
			String sqlUpdateEleveSalle = "Update elevesalle Set salleId ='" + eleve.getSalleId()
			+ "' Where eleveId = '" +eleve.getId() + "'";
			String sqlUpdateEleve = "Update eleve Set nom ='" + eleve.getNom() + "', prenom ='" + eleve.getPrenom()
					+ "', dateNaissance = '" +eleve.getDateNaissance()+"' Where id = '" + eleve.getId() + "'";

			System.out.println(sqlUpdateEleve);
			int rowCount = statement.executeUpdate(sqlUpdateEleveAdresse);
			rowCount += statement.executeUpdate(sqlUpdateEleveSalle);
			rowCount += statement.executeUpdate(sqlUpdateEleve);

			System.out.println("update eleve Count affected = " + rowCount);

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
	public Eleve findById(int id) {
		Eleve eleveId = new Eleve(null, null, null);
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String sqlFindEleve = "Select id, nom, prenom, dateNaissance From eleve Where id ='" + id + "'";
			String sqlFindSalleId = "Select salleId From elevesalle Where eleveId ='" + id + "'";
			String sqlFindAdresseId = "Select adresse_Id From eleveadresse Where eleveId ='" + id + "'";
			System.out.println(sqlFindEleve);
			
			// Execute SQL statement returns a ResultSet object.
			ResultSet rs = statement.executeQuery(sqlFindEleve);

			while (rs.next()) {
				// Get value of column 2

				int id1 = rs.getInt(1);

				String nom = rs.getString(2);

				// Then get the value of column 1.

				String prenom = rs.getString(3);

				eleveId.setNom(nom);
				
				eleveId.setPrenom(prenom);
				
				eleveId.setId(id1);

				LocalDate dateNaissance = rs.getDate(4).toLocalDate();
				
				eleveId.setDateNaissance(dateNaissance);
				
				return eleveId;
			}
			ResultSet rs2 = statement.executeQuery(sqlFindAdresseId);
			
			while (rs2.next()) {
				// Get value of column 2

				int salleId = rs2.getInt(1);

				eleveId.setSalleId(salleId);

				return eleveId;
			}
			
			ResultSet rs3 = statement.executeQuery(sqlFindSalleId);
			while (rs3.next()) {
				// Get value of column 2

				int adresseId = rs3.getInt(1);

				eleveId.setSalleId(adresseId);

				return eleveId;
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
		return eleveId;
	}

	public Eleve findByFirstName(String fbn) {
		
		Eleve eleve = new Eleve(0, null, null, null);
		System.out.println(fbn);
		for (Eleve e : findAll()) {
			
			if (fbn.equals(e.getPrenom())) {
				
				eleve.setId(e.getId());
				eleve.setPrenom(e.getPrenom());
				eleve.setNom(e.getNom());
				eleve.setDateNaissance(e.getDateNaissance());
			}
		}
		return eleve;
	}

	@Override
	public List<Eleve> findAll() {

		try {

			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			
			String sql = "Select id, nom, prenom, dateNaissance from eleve";

			
			//SELECT adresse.nomRue FROM adresse INNER JOIN eleveadresse ON adresse_id = adresse.id where eleveadresse.eleve_id = 1
			
			
			// Execute SQL statement returns a ResultSet object.
			Eleve eleve = null;
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				eleve = new Eleve(null, null, null);

				// Then get the value of column 1.
				int id = rs.getInt(1);

				eleve.setId(id);

				String prenom = rs.getString(3);

				eleve.setPrenom(prenom);

				String nom = rs.getString(2);

				eleve.setNom(nom);

				Date dateNaissance = rs.getDate(4);
				//convert To LocalDate
				LocalDate localDate = Instant.ofEpochMilli(dateNaissance.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				
				eleve.setDateNaissance(localDate);
				
				listeEleve.add(eleve);

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
		return listeEleve;

	}

	@Override
	public String toString() {
		return "EleveService [listeEleves=" + listeEleves + ", listeEleve=" + listeEleve + ", findAll()=" + findAll()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}