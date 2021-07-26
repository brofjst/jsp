package it.contrader.dto;

import java.util.Objects;

import it.contrader.model.Ordine;

public class OrdineDTO {
	private int idordine, iduser, idrist, idprodotto, quantita;
	private float prezzo;
	private String data, flag;
	
	
	public OrdineDTO(int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo) {
		super();
		this.iduser = iduser;
		this.idrist = idrist;
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.data = data;
		this.prezzo = prezzo;
	}


	public OrdineDTO(int idordine, int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo) {
		super();
		this.idordine = idordine;
		this.iduser = iduser;
		this.idrist = idrist;
		this.idprodotto = idprodotto;
		this.quantita = quantita;
		this.data = data;
		this.prezzo = prezzo;
	}

	public OrdineDTO(int idordine, int iduser, int idrist, int idprodotto, int quantita, String data, float prezzo, String flag) {
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
	public String toString() {
		return "OrdineDTO [idordine=" + idordine + ", iduser=" + iduser + ", idrist=" + idrist + ", idprodotto="
				+ idprodotto + ", quantita=" + quantita + ", prezzo=" + prezzo + ", data=" + data + ", flag=" + flag
				+ "]";
	}
}
