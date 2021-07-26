package it.contrader.dto;

import java.util.Objects;

import it.contrader.model.Ristorante;

/**
 * 
 * @author Vittorio
 *
 *Il DTO è simile al Model ma può contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "User".
 */
public class RistoranteDTO {
	
	/**
	 * Qui sotto definisco gli attributi di User. 
	 */
	private int id;

	private String paese;
	
	private String piva;
	
	private String indirizzo, nome;
	
	private int iduser;

	public RistoranteDTO() {
		
	}
	
	public RistoranteDTO(String paese, String piva, String indirizzo, int iduser, String nome) {
		super();
		this.paese = paese;
		this.piva = piva;
		this.indirizzo = indirizzo;
		this.iduser = iduser;
		this.nome = nome;
	}


	public RistoranteDTO(int id, String paese, String piva, String indirizzo, int iduser, String nome) {
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
	public String toString() {
		return "Ristorante [id=" + id + ", paese=" + paese + ", piva=" + piva + ", indirizzo=" + indirizzo + ", nome="
				+ nome + ", iduser=" + iduser + "]";
	}

	
}
