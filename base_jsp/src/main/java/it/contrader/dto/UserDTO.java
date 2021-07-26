package it.contrader.dto;

/**
 * 
 * @author Vittorio
 *
 *Il DTO è simile al Model ma può contenere meno attributi (ad esempio d dati sensibili
 *che non devono arrivare alla View). GLi oggetti vengono trasformati da oggetti del Model
 *a oggetti del DTO tramite i Converter (chiamati dai Service). 
 *Per la descrizione della classe far riferimento al Model "User".
 */
public class UserDTO {
	
	private int id;

	private String username;
	
	private String password;
	
	private String usertype;
	
	private String mail;
	
	private String cognome;
	

	
	public UserDTO() {
		
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



	public UserDTO(String username, String password, String usertype, String mail, String cognome) {
		super();
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.mail = mail;
		this.cognome = cognome;
	}



	public UserDTO(int id, String username, String password, String usertype, String mail, String cognome) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.mail = mail;
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



	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", usertype=" + usertype
				+ ", mail=" + mail + ", cognome=" + cognome + "]";
	}


}
