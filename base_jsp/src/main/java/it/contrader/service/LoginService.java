package it.contrader.service;

import it.contrader.dao.LoginDAO;
import it.contrader.converter.UserConverter;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;

public class LoginService {
	private UserConverter converter ;
	private LoginDAO loginDAO;
	/**
	 * Costruttore della classe, crea un oggetto di tipo LoginDAO per poter chiamare il metodo del DAO
	 */
	public LoginService() {
		this.loginDAO = new LoginDAO();
		this.converter = new UserConverter();
		
	}

	/**
	 * Chiama il metodo del DAO e ottiene una stringa (lo usertype)
	 */
	public UserDTO login (String username, String password) {
		User x = loginDAO.login(username, password);
		return converter.toDTO(x);
	}
}
