package it.contrader.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.User;
/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class LoginDAO {

	private final String QUERY_LOGIN = "SELECT * FROM user WHERE username = ? AND password = ?";

	
	public User login (String username, String password) {
		Connection connection = ConnectionSingleton.getInstance();
		User user = null;
		try {
			PreparedStatement preparedstatement = connection.prepareStatement(QUERY_LOGIN);
			//ResultSet resultSet = preparedstatement.executeQuery();
			
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			
			
			ResultSet resultSet;
			String  usertype;
			
			/* si crea un oggetto user con tutti i suoi attributi, poi fa estendere home controller da 
			 * questa classe così da prendersi l'oggetto user, ci servirà per avere la getallid() nell'home controller */
			/*if(resultSet.next()) {
				username = resultSet.getString("username");
				password = resultSet.getString("password");
				usertype = resultSet.getString("usertype");
				String mail = resultSet.getString("mail");
				String cognome = resultSet.getString("cognome");
				user = new User(username, password, usertype,mail , cognome);
				user.setId(resultSet.getInt("iduser"));
			}*/
			
			if(preparedstatement.executeQuery().next()) {
				resultSet = preparedstatement.executeQuery();
				resultSet.next();
				usertype = resultSet.getString("usertype");
				username = resultSet.getString("username");
				password = resultSet.getString("password");
				usertype = resultSet.getString("usertype");
				String mail = resultSet.getString("mail");
				String cognome = resultSet.getString("cognome");
				user = new User(username, password, usertype, mail, cognome);
				user.setId(resultSet.getInt("iduser"));
			}

			return user;
		}
		
		catch (SQLException e) {
			return null;
		}
	}
}
