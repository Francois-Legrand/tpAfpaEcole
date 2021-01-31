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

		LocalDate dateNaissance = LocalDate.of(1998, 01, 22);

//		 eleveService.create(new Eleve("jean", "guy", dateNaissance));
//		 eleveService.create(new Eleve("marine", "duporge", dateNaissance));
//		 
//		 eleveService.create(new Eleve("gilbert", "montygny", dateNaissance));
//		 eleveService.create(new Eleve("raphel", "guyzgan", dateNaissance));
		 
//		Eleve eleveId2 = eleveService.findById(3);
//		int eleveId = eleveId2.getId();
//
//		
//		System.out.println(eleveId2);
		//int jeanId = jean.getId();

		Eleve eleveFindByFirstName = eleveService.findByFirstName("raphel");
		
		System.out.println(eleveFindByFirstName);
		// adresseService.create(new Adresse(1, "Oliviers", 75001, "Paris", "France",
		// francoisId));

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
