package fr.afpa.collection.gestionecole.metier;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Eleve {
	private static final AtomicInteger count = new AtomicInteger(0); 
	public int id; 
	String nom ; 
	String prenom ; 
	LocalDate dateNaissance; 
	int salleId;
	int adresseId;
	
	public Eleve(String nom, String prenom, LocalDate dateNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		
	}
	public Eleve(String nom, String prenom, LocalDate dateNaissance, int adresseId) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseId = adresseId;
		this.id = count.incrementAndGet();
	}
	
	public Eleve(String nom, String prenom, LocalDate dateNaissance, int adresseId, int salleId) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.salleId = salleId;
		this.adresseId = adresseId;
		this.id = count.incrementAndGet();
	}
	public Eleve(int id, String nom, String prenom, LocalDate dateNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}
	
	public Eleve(int id, String nom, String prenom, LocalDate dateNaissance, int salleId, int adresseId) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseId = adresseId;
		this.salleId = salleId;
	}
	
	public int getSalleId() {
		return salleId;
	}
	public void setSalleId(int salleId) {
		this.salleId = salleId;
	}
	public int getAdresseId() {
		return adresseId;
	}
	public void setAdresseId(int adresseId) {
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

	@Override
	public String toString() {
		return "Eleve [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + "]\n";
	}
	
	
	
	
}
