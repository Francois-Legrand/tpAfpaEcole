package fr.afpa.collection.gestionecole.presentation;

import java.time.LocalDate;

import fr.afpa.collection.gestionecole.dao.AdresseService;
import fr.afpa.collection.gestionecole.dao.EleveService;
import fr.afpa.collection.gestionecole.dao.SalleService;
import fr.afpa.collection.gestionecole.metier.Adresse;
import fr.afpa.collection.gestionecole.metier.Eleve;

public class Ecole {

	public static void main(String[] args) {

		EleveService eleveService = new EleveService();
		AdresseService adresseService = new AdresseService();
		SalleService salleService = new SalleService();

		LocalDate dateNaissance = LocalDate.of(1998, 01, 22);

		eleveService.create(new Eleve("David", "chojnacky", dateNaissance));
//		
//		eleveService.create(new Eleve("David", "chojnacky", dateNaissance));
//		
		Eleve eleveId1 = eleveService.findById(1);
//
		int eleveId = eleveId1.getId();
//
//		Eleve eleveId2 = eleveService.findById(2);
//
//		int eleveId21 = eleveId1.getId();
//		
		adresseService.create(new Adresse(14, "rue des olivier", 62000, "lievin", "france", eleveId));
//		adresseService.create(new Adresse(14, "rue des olivier", 62000, "lievin", "france", eleveId));
		
		//Adresse adresseid1 = adresseService.findById(2);

		//int adresseId = adresseid1.getId();
		
		//adresseid1.setCodePostale(78000);
		
		//adresseService.delete(adresseid1);
		System.out.println(eleveId1);
	}

}
