package it.contrader.model;

import java.util.Objects;

public class Prodotto {
	private int idprodotto, quantita, idrist;
	private String descrizione;
	private float prezzo;
	
	public Prodotto() {
		
	}
	
	public Prodotto(int quantita, String descrizione, float prezzo, int idrist) {
		super();
		this.quantita = quantita;
		this.idrist = idrist;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}
	public Prodotto(int idprodotto, int quantita, String descrizione, float prezzo, int idrist) {
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
	public int hashCode() {
		return Objects.hash(descrizione, idprodotto, idrist, prezzo, quantita);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prodotto other = (Prodotto) obj;
		return Objects.equals(descrizione, other.descrizione) && idprodotto == other.idprodotto
				&& idrist == other.idrist && Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo)
				&& quantita == other.quantita;
	}
	@Override
	public String toString() {
		return "Prodotto [idprodotto=" + idprodotto + ", quantita=" + quantita + ", idrist=" + idrist + ", descrizione="
				+ descrizione + ", prezzo=" + prezzo + "]";
	}
}
