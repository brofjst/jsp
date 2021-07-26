package it.contrader.model;

import java.util.Objects;

public class Ordine {
	private int idordine, iduser, idrist, idprodotto, quantita;
	private float prezzo;
	private String data, flag;
	
	public Ordine() {
		
	}
	
	public Ordine(int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo) {
		super();
		this.iduser = iduser;
		this.idrist = idrist;
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.data = data;
		this.prezzo = prezzo;
	}


	public Ordine(int idordine, int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo) {
		super();
		this.idordine = idordine;
		this.iduser = iduser;
		this.idrist = idrist;
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.data = data;
		this.prezzo = prezzo;
	}
	
	public Ordine(int idordine, int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo, String flag) {
		super();
		this.idordine = idordine;
		this.iduser = iduser;
		this.idrist = idrist;
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.data = data;
		this.prezzo = prezzo;
		this.flag = flag;
	}
	
	public Ordine(int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo, String flag) {
		super();
		this.iduser = iduser;
		this.idrist = idrist;
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.data = data;
		this.prezzo = prezzo;
		this.flag = flag;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdordine() {
		return idordine;
	}


	public void setIdordine(int idordine) {
		this.idordine = idordine;
	}


	public int getIduser() {
		return iduser;
	}


	public void setIduser(int iduser) {
		this.iduser = iduser;
	}


	public int getIdrist() {
		return idrist;
	}


	public void setIdrist(int idrist) {
		this.idrist = idrist;
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

	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, flag, idordine, idprodotto, idrist, iduser, prezzo, quantita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordine other = (Ordine) obj;
		return Objects.equals(data, other.data) && Objects.equals(flag, other.flag) && idordine == other.idordine
				&& idprodotto == other.idprodotto && idrist == other.idrist && iduser == other.iduser
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo) && quantita == other.quantita;
	}

	@Override
	public String toString() {
		return "Ordine [idordine=" + idordine + ", iduser=" + iduser + ", idrist=" + idrist + ", idprodotto="
				+ idprodotto + ", quantita=" + quantita + ", prezzo=" + prezzo + ", data=" + data + ", flag=" + flag
				+ "]";
	}
}
