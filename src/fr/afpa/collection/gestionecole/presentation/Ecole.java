package fr.afpa.collection.gestionecole.presentation;

import java.time.LocalDate;
import java.time.Period;
import fr.afpa.collection.gestionecole.dao.EleveService;
import fr.afpa.collection.gestionecole.dao.SalleService;
import fr.afpa.collection.gestionecole.metier.Adresse;
import fr.afpa.collection.gestionecole.metier.Eleve;
import fr.afpa.collection.gestionecole.metier.Salle;

public class Ecole {

	public static void main(String[] args) {

	// le code au dessus est �quivalent � 
	SalleService salleService = new SalleService() ;
	
	salleService.create(new Salle("mat","Mathématiques"));
	salleService.create(new Salle("Phy","Physique"));
	salleService.create(new Salle("Inf","Informatique"));
	salleService.create(new Salle("Art","Art Plastique"));
	salleService.create(new Salle("Fr","Français"));
	
	salleService.afficheListeSalles();
	
	salleService.delete(salleService.findById(2));
	
	System.out.println("Liste des salles aprés suppression de la salle  id= 2 :");
	salleService.afficheListeSalles();
	
	System.out.println("Modifier la salle informatique :");
	
	Salle laSalleInformatique = salleService.findByName("Informatique") ;
	if(laSalleInformatique != null) {
		laSalleInformatique.setLibelle("Computer Science");
		salleService.update(laSalleInformatique);
	} else {
		System.out.println("La salle n'existe pas");
	}
	salleService.afficheListeSalles();
	// le code au dessus est �quivalent � 
	EleveService eleveService = new EleveService() ;
	
	LocalDate dateNaissance = LocalDate.of(1998, 01, 22) ;
	int age = Period.between(dateNaissance, LocalDate.now()).getYears() ;
	
	eleveService.create(new Eleve("Dupont", "François", dateNaissance, age, 
			new Adresse(12,"Oliviers", 75001,"Paris", "France") )) ; 
	
	dateNaissance = LocalDate.of(2008, 01, 22) ;
	age = Period.between(dateNaissance, LocalDate.now()).getYears() ;
	
	eleveService.create(new Eleve("Duhamel", "Francis", dateNaissance, age, 
			new Adresse(12,"Oliviers", 75001,"Paris", "France") )) ; 
	

	dateNaissance = LocalDate.of(1990, 01, 22) ;
	age = Period.between(dateNaissance, LocalDate.now()).getYears() ;
	
	
	System.out.println( eleveService.toString());
	
	
	Salle updateSalle = salleService.findById(1);
	
	updateSalle.setCode("oooo");
	
	salleService.update(updateSalle);
	
	Eleve eleveUpdate = eleveService.findById(1) ;
	
	eleveUpdate.setNom("Antoine");
	
	eleveService.update(eleveUpdate);
	
//	System.out.println( eleveService.toString());
//	
//	for(int i =0 ; i<eleveService.findAll().size() ; i++ ) {
//		
//		Eleve br = eleveService.findByFirstName("Brigitte") ;
//		if (br!= null)
//			eleveService.delete(br);
//		}
		System.out.println( eleveService.toString());
	}

}
