package fr.afpa.collection.gestionecole.metier;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Eleve {
	private static final AtomicInteger count = new AtomicInteger(0); 
	public int id; 
	String nom ; 
	String prenom ; 
	LocalDate dateNaissance ; 
	int adresseId;
	
	public Eleve(String nom, String prenom, LocalDate dateNaissance, int adresseId) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseId = adresseId;
		
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
	
	public int getAdresse() {
		return adresseId;
	}
	public void setAdresse(int adresseId) {
		this.adresseId = adresseId;
	}
	@Override
	public String toString() {
		return "getNom " + getNom() + ", getPrenom " + getPrenom()
				+ ", getDateNaissance " + getDateNaissance() + ", getAdresse "
				+ getAdresse() + "]\n";
	}
	
	
}
