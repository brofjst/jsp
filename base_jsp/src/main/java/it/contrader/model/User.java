package it.contrader.model;
/**
 * Per dettagli vedi guida sez 4 Model
 */
public class User {

	/**
	 * Qui sotto definisco gli attributi di User. 
	 */
	private int id;

	private String username;
	
	private String password;
	
	private String usertype;
	
	private String mail;
	
	private String cognome;
	

	/**
	 * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo User
	 */
	public User() {
		
	}


	public User(String username, String password, String usertype, String mail, String cognome) {
		super();
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.mail = mail;
		this.cognome = cognome;
	}


	public User(int id, String username, String password, String usertype, String mail, String cognome) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.mail = mail;
		this.cognome = cognome;
	}
	
	
	
	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}




	//Metodo per il confronto degli oggetti
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (usertype == null) {
			if (other.usertype != null)
				return false;
		} else if (!usertype.equals(other.usertype))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", usertype=" + usertype
				+ ", mail=" + mail + ", cognome=" + cognome + "]";
	}
}