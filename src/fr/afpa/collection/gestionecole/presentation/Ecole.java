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

		
		EleveService eleveService = new EleveService();
		AdresseService adresseService = new AdresseService();
		SalleService salleService = new SalleService();
		
		LocalDate dateNaissance = LocalDate.of(1998, 01, 22);
		
		Adresse adresseId1 = adresseService.findById(1);
		int adresseId = adresseId1.getId();
		
		
		eleveService.create(new Eleve("jean", "guy", dateNaissance, adresseId));
		//Eleve eleveId1 = eleveService.findById(1);
		//int eleveId = eleveId1.getId();
		
		//System.out.println(eleveId);
		
		//adresseService.create(new Adresse(6, "Oliviers", 75001, "Paris", "France"));
		
		
//		 eleveService.create(new Eleve("jean", "guy", dateNaissance));
//		 eleveService.create(new Eleve("marine", "duporge", dateNaissance));
//		 
//		 eleveService.create(new Eleve("gilbert", "montygny", dateNaissance));
//		 eleveService.create(new Eleve("raphel", "guyzgan", dateNaissance));
		 
		//Eleve eleveId3 = eleveService.findById(3);
		//int eleveId = eleveId3.getId();
		
		//salleService.create(new Salle("math", "Salle de math"));
		//Salle salleId6 = salleService.findById(6);
		//int salleId = salleId6.getId();
		
		//eleveService.create(new Eleve("Frederic", "guon", dateNaissance, salleId));
		
		//eleveService.create(new Eleve("Frederic", "guon", dateNaissance, salleId));
		
		
//		Salle salleId1 = salleService.findById(1);
//		int salleId = salleId1.getId();
//		
//		System.out.println(salleId1);
//		
//		
//		eleveService.create(new Eleve("jean", "guon", dateNaissance, salleId));
		
		//eleveService.create(new Eleve("Frederic", "guon", dateNaissance, salleId));
		
//		System.out.println(eleveId2);
		//int jeanId = jean.getId();
		
		//Eleve eleveId10 = eleveService.findById(12);
		//int eleveId = eleveId10.getId();
		
		//salleService.create(new Salle("hitoire", "Salle dhistoire", eleveId));
		
		//Eleve eleveFindByFirstName = eleveService.findByFirstName("raphel");
		
		//System.out.println(eleveFindByFirstName);
		 //adresseService.create(new Adresse(13, "Oliviers", 75001, "Paris", "France",
		 //eleveId));

		// adresseService.create(new Adresse(2, "Oliviers", 75001, "Paris", "France",
		// francoisId));

		// adresseService.create(new Adresse(3, "Oliviers", 75001, "Paris", "France",
		// jeanId));

		// adresseService.create(new Adresse(4, "Oliviers", 75001, "Paris", "France",
		// jeanId));

		// francois.setNom("duquenoy");
		// francois.setPrenom("Robert");

		// eleveService.update(francois);

		//System.out.println(eleveService.findAll());

		//eleveService.delete(jean);
		
		//System.out.println(eleveService.findAll());

	}

}
