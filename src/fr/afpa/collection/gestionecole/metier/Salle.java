package fr.afpa.collection.gestionecole.metier;

import java.util.concurrent.atomic.AtomicInteger;

public class Salle {

    private static final AtomicInteger count = new AtomicInteger(0); 
	int id ; 
	String code ;
	String libelle;
	
	public Salle(String code, String libelle) {
		this.code = code;
		this.libelle = libelle;
		this.id = count.incrementAndGet();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public String toString() {
		return "Salle [id=" + id + ", code=" + code + ", libelle=" + libelle + "]";
	}
	
	
	
	
	
}
