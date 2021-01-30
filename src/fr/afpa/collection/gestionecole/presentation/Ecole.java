package fr.afpa.collection.gestionecole.presentation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;

import fr.afpa.collection.gestionecole.dao.AdresseService;
import fr.afpa.collection.gestionecole.dao.EleveService;
import fr.afpa.collection.gestionecole.dao.SalleService;
import fr.afpa.collection.gestionecole.metier.Adresse;
import fr.afpa.collection.gestionecole.metier.Eleve;
import fr.afpa.collection.gestionecole.metier.Salle;
import fr.francois.ecole.bdd.ConnectionUtils;

public class Ecole {

	public static void main(String[] args) {

		// le code au dessus est equivalent
		// SalleService salleService = new SalleService() ;

//	salleService.create(new Salle("mat","Mathématiques"));
//	salleService.create(new Salle("Phy","Physique"));
//	salleService.create(new Salle("Inf","Informatique"));
//	salleService.create(new Salle("Art","Art Plastique"));
//	salleService.create(new Salle("Fr","Français"));
//	
		// salleService.afficheListeSalles();

//	salleService.delete(salleService.findById(2));
//	System.out.println(salleService.findById(2));
//	System.out.println("Liste des salles aprés suppression de la salle  id= 2 :");
		// salleService.afficheListeSalles();

//	System.out.println("Modifier la salle informatique :");
//	
//	Salle laSalleInformatique = salleService.findByName("Informatique") ;
//	if(laSalleInformatique != null) {
//		laSalleInformatique.setLibelle("Computer Science");
//		salleService.update(laSalleInformatique);
//	} else {
//		System.out.println("La salle n'existe pas");
//	}
		// salleService.afficheListeSalles();
		// le code au dessus est equivalent

		EleveService eleveService = new EleveService();
		AdresseService adresseService = new AdresseService();

		LocalDate dateNaissance = LocalDate.of(1998, 01, 22);

		eleveService.create(new Eleve("françois", "Legrand", dateNaissance));
		eleveService.create(new Eleve("françois", "Legrand", dateNaissance));
		
		Eleve francois = eleveService.findById(1);
		int francoisId = francois.getId();
		
		Eleve jean = eleveService.findById(2);
		int jeanId = jean.getId();
		
		adresseService.create(new Adresse(1, "Oliviers", 75001, "Paris", "France", francoisId));

		adresseService.create(new Adresse(2, "Oliviers", 75001, "Paris", "France", francoisId));
		
		adresseService.create(new Adresse(3, "Oliviers", 75001, "Paris", "France", jeanId));

		adresseService.create(new Adresse(4, "Oliviers", 75001, "Paris", "France", jeanId));

	

//		Eleve eleveId = eleveService.findById(1);
//		
//		eleveId.setNom("bouhie");
//		eleveId.setPrenom("Jean");
//		
//		eleveService.update(eleveId);

//	LocalDate dateNaissance = LocalDate.of(1998, 01, 22) ;
//	
//	int age = Period.between(dateNaissance, LocalDate.now()).getYears() ;
//	
//	eleveService.create(new Eleve("bouhie", "jean", dateNaissance, age, ));

//	Eleve eleveId = eleveService.findById(7);
//	
//	System.out.println(eleveId);
//	
//	//eleveService.delete(eleveId);
//	
//	eleveId.setPrenom("gilbert");
//	
//	eleveService.update(eleveId);
//
//	System.out.println(eleveService.findAll());

	}

}
