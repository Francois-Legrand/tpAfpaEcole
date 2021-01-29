package fr.afpa.collection.gestionecole.metier;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Eleve {
	private static final AtomicInteger count = new AtomicInteger(0); 
	public int id; 
	String nom ; 
	String prenom ; 
	LocalDate dateNaissance ; 
	int age; 
	Adresse adresse;
	
	public Eleve(String nom, String prenom, LocalDate dateNaissance, int age, Adresse adresse, int id) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.age = age;
		this.adresse = adresse;
		this.id = id;
	}
	public Eleve(String nom, String prenom, LocalDate dateNaissance, int age, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.age = age;
		this.adresse = adresse;
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
		return "getNom" + getNom() + ", getPrenom" + getPrenom()
				+ ", getDateNaissance" + getDateNaissance() + ", getAge" + getAge() + ", getAdresse"
				+ getAdresse() + "]\n";
	}
	
	

	
	
}
