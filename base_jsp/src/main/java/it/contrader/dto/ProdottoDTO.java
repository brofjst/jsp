package it.contrader.dto;

import java.util.Objects;

public class ProdottoDTO {
	private int idprodotto, quantita, idrist;
	private String descrizione;
	private float prezzo;
	
	
	public ProdottoDTO(int quantita, String descrizione, float prezzo, int idrist) {
		super();
		this.quantita = quantita;
		this.idrist = idrist;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}


	public ProdottoDTO(int idprodotto, int quantita, String descrizione, float prezzo, int idrist) {
		super();
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.idrist = idrist;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}


	public int getIdprodotto() {
		return idprodotto;
	}


	public void setIdprodotto(int idprodotto) {
		this.idprodotto = idprodotto;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public int getIdrist() {
		return idrist;
	}


	public void setIdrist(int idrist) {
		this.idrist = idrist;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


	@Override
	public String toString() {
		return "ProdottoDTO [idprodotto=" + idprodotto + ", quantita=" + quantita + ", idrist=" + idrist
				+ ", descrizione=" + descrizione + ", prezzo=" + prezzo + "]";
	}
}
