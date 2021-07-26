package it.contrader.model;

import java.util.Objects;

/**
 * Per dettagli vedi guida sez 4 Model
 */
public class Ristorante {

	/**
	 * Qui sotto definisco gli attributi di User. 
	 */
	private int id;

	private String paese;
	
	private String piva;
	
	private String indirizzo, nome;
	
	private int iduser;

	public Ristorante() {
		
	}
	
	public Ristorante(String paese, String piva, String indirizzo, int iduser, String nome) {
		super();
		this.paese = paese;
		this.piva = piva;
		this.indirizzo = indirizzo;
		this.iduser = iduser;
		this.nome = nome;
	}


	public Ristorante(int id, String paese, String piva, String indirizzo, int iduser, String nome) {
		super();
		this.id = id;
		this.paese = paese;
		this.piva = piva;
		this.indirizzo = indirizzo;
		this.iduser = iduser;
		this.nome = nome;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPaese() {
		return paese;
	}


	public void setPaese(String paese) {
		this.paese = paese;
	}


	public String getPiva() {
		return piva;
	}


	public void setPiva(String piva) {
		this.piva = piva;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public int getIduser() {
		return iduser;
	}


	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, iduser, indirizzo, nome, paese, piva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ristorante other = (Ristorante) obj;
		return id == other.id && iduser == other.iduser && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(nome, other.nome) && Objects.equals(paese, other.paese)
				&& Objects.equals(piva, other.piva);
	}

	@Override
	public String toString() {
		return "Ristorante [id=" + id + ", paese=" + paese + ", piva=" + piva + ", indirizzo=" + indirizzo + ", nome="
				+ nome + ", iduser=" + iduser + "]";
	}

	
	
}