package fr.afpa.collection.gestionecole.metier;

import java.time.LocalDate;

public class Eleve {
	private static int compteur ;
	int id; 
	String nom ; 
	String prenom ; 
	LocalDate dateNaissance ; 
	int age; 
	Adresse adresse;
	
	public Eleve(String nom, String prenom, LocalDate dateNaissance, int age, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.age = age;
		this.adresse = adresse;
		this.id = compteur++ ;
	}
	public static int getCompteur() {
		return compteur;
	}
	public static void setCompteur(int compteur) {
		Eleve.compteur = compteur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Eleve Id=" + getId() + ", getNom()=" + getNom() + ", getPrenom()=" + getPrenom()
				+ ", getDateNaissance()=" + getDateNaissance() + ", getAge()=" + getAge() + ", getAdresse()="
				+ getAdresse() + "]\n";
	}
	
	

	
	
}
