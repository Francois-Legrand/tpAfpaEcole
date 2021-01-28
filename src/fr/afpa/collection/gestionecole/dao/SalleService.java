package fr.afpa.collection.gestionecole.dao;

import java.util.ArrayList;
import java.util.List;

import fr.afpa.collection.gestionecole.metier.Salle;


/*
 * Créer la classe SalleService qui implémente l'interface IDao. 
 * Dans cette classe 
 * les données seront stockés dans une collection de type List.
 * */
public class SalleService implements IDao<Salle> {

	public List<Salle> salles = new ArrayList<Salle>() ;
	
	// Les methodes de CRUD : Create , Read Update  et delete
	@Override
	public boolean create(Salle salle) {
		return salles.add(salle);
	}

	@Override
	public boolean delete(Salle salle) {
		Salle.compteur-- ;
		return salles.remove(salle);
	}
	

	

	@Override
	public boolean update(Salle salle) {
		
		for(Salle s: salles) {
			if(s.getId()== salle.getId()) {
				s.setCode(salle.getCode());
				s.setLibelle(salle.getLibelle());
				return true ;
			}
		}
		return false;
	}

	
	
/*
	public boolean update(int id, Salle salle) {
	salles.set(id, salle) ;
		return false;
	}
	
	@Override
	public boolean update(Salle salle) {
	System.out.println(" je suis dans update !!");
		return false;
	}
	*/


	@Override
	public Salle findById(int id) {
		for(Salle s: salles) {
			if(s.getId()== id) {
				return s ;
			}
		}
		return null;
	}

	
	public int findIdByName(String name) {
		for(Salle s: salles) {
			if(s.getLibelle()== name) {
				return s.getId() ;
			}
		}
		return 0;
	}
	
	public Salle findByName(String name) {
		for(Salle s: salles) {
			if(s.getLibelle()== name) {
				return s;
			}
		}
		return null;
	}
	
	@Override
	public List<Salle> findAll() {
		
		return salles;
	}
	
	public void afficheListeSalles() {
	for(Salle s : salles) {
		System.out.println(s);
	}
	}

}