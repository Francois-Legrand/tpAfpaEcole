package fr.afpa.collection.gestionecole.metier;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * D�finir une classe Adresse  avec les attributs suivants: 
 * numRue , nomRue , codePostale, Ville , Pays.

 * */
public class Adresse {
	private static final AtomicInteger count = new AtomicInteger(0); 
	int id ; 
	int numRue ;
	String nomRue  ; 
	int codePostale ; 
	String ville  ; 
	String pays ;
	int eleveId ; 
	
	public Adresse(int numRue, String nomRue, int codePostale, String ville, String pays, int eleveId) {
		super();
		this.numRue = numRue;
		this.nomRue = nomRue;
		this.codePostale = codePostale;
		this.ville = ville;
		this.pays = pays;
		this.eleveId = eleveId;
		this.id = count.incrementAndGet();
	}


	public int getEleveId() {
		return eleveId;
	}


	public void setEleveId(int eleveId) {
		this.eleveId = eleveId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getNumRue() {
		return numRue;
	}

	public void setNumRue(int numRue) {
		this.numRue = numRue;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public int getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getPays() {
		return pays;
	}



	public void setPays(String pays) {
		this.pays = pays;
	}




	@Override
	public String toString() {
		return "Adresse [numRue=" + numRue + ", nomRue=" + nomRue + ", codePostale=" + codePostale + ", ville=" + ville
				+ ", pays=" + pays + "]";
	}
	
	
	
	
	
	
}
