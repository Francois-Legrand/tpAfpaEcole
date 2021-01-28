package fr.afpa.collection.gestionecole.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.afpa.collection.gestionecole.metier.Eleve;
import fr.afpa.collection.gestionecole.metier.Salle;
import fr.francois.ecole.bdd.ConnectionUtils;

public class EleveService implements IDao<Eleve> {

	Map<Integer, Eleve> listeEleves = new HashMap<Integer, Eleve>();

	@Override
	public boolean create(Eleve eleve) {
//		this.nom = nom;
//		this.prenom = prenom;
//		this.dateNaissance = dateNaissance;
//		this.age = age;
//		this.adresse = adresse;
		try {
			Connection connection = ConnectionUtils.getMyConnection();

			Statement statement = connection.createStatement();

			String sqlIntoEleve = "Insert into eleves (prenom, nom, dateNaissance, age, adresse) values ('" + eleve.getPrenom() + "','"
					+ eleve.getNom() + "','" + eleve.getDateNaissance() + "','" + eleve.getAge() + "','"
					+ eleve.getAdresse()+"')";
			
			int rowCount = statement.executeUpdate(sqlIntoEleve);
			System.out.println("Row Count affected = " + rowCount);
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
		return listeEleves.remove(eleve.getId(), eleve);
	}

	@Override
	public boolean update(Eleve eleve) {
		listeEleves.put(eleve.getId(), eleve);
		return false;
	}

	@Override
	public Eleve findById(int id) {
		listeEleves.get(id);
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
		return listeEleves.values().stream().collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "listeEleves=" + listeEleves + "]";
	}

}