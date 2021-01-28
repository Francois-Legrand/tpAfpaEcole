package fr.afpa.collection.gestionecole.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.afpa.collection.gestionecole.metier.Adresse;
import fr.afpa.collection.gestionecole.metier.Eleve;
import fr.afpa.collection.gestionecole.metier.Salle;
import fr.francois.ecole.bdd.ConnectionUtils;

public class EleveService implements IDao<Eleve> {

	Map<Integer, Eleve> listeEleves = new HashMap<Integer, Eleve>();
	List<Eleve> listeEleve = new ArrayList<Eleve>();
	@Override
	public boolean create(Eleve eleve) {

		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlIntoEleve = "Insert into eleves (prenom, nom, dateNaissance, age, adresse) values ('" + eleve.getPrenom() + "','"
					+ eleve.getNom() + "','" + eleve.getDateNaissance() + "','" + eleve.getAge() + "','"
					+ eleve.getAdresse()+"')";
			
			int rowCount = statement.executeUpdate(sqlIntoEleve);
			
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

			String sqlDeleteEleve = "Delete from eleves where id = '"+eleve.getId()+"'";
			
			int rowCount = statement.executeUpdate(sqlDeleteEleve);
			
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

			String sqlUpdateEleve = "Update eleves Set nom ='"+eleve.getNom()+"', prenom ='"+eleve.getPrenom()+"', age = '"+eleve.getAge()+"' Where id = '"+eleve.getId()+"'";
			System.out.println(sqlUpdateEleve);
			int rowCount = statement.executeUpdate(sqlUpdateEleve);
			
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
		
			try {
				Connection connection = ConnectionUtils.getMyConnection();

				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				String sqlFindId = "Select nom, prenom, age From eleves Where id ='"+id+"'";
				System.out.println(sqlFindId);

return null;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
	}

	public Eleve findByFirstName(String fn) {
		for (Eleve e : findAll()) {
			if (e.getPrenom() == fn) {
				return e;
			}
		}
		return null;
	}

	@Override
	public List<Eleve> findAll() {
		
		try {
			
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "Select nom, prenom, age From eleves";
			
			// Execute SQL statement returns a ResultSet object.
			Eleve eleve = null;
					ResultSet rs = statement.executeQuery(sql);
					while (rs.next()) {
						
						eleve = new Eleve(null, null, null, 0, null);
						
						eleve.setId(eleve.getId()-1);
			            String nom = rs.getString(2);
			            
			            eleve.setNom(nom);
			            
			            // Then get the value of column 1.
			            String prenom = rs.getString(1);
			            eleve.setPrenom(prenom);
			            
			            int age = rs.getInt(3);
			            
			            eleve.setAge(age);
			            
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