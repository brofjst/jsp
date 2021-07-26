package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class UserDAO {

	private final String QUERY_ALL = "SELECT * FROM user";
	private final String QUERY_CREATE = "INSERT INTO user (username, password, usertype, mail , cognome) VALUES (?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM user WHERE iduser=?";
	private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, usertype=? , mail=? , cognome=? WHERE iduser=?";
	private final String QUERY_DELETE = "DELETE FROM user WHERE iduser=?";
	private final String QUERY_GET_RISTORATORI = "SELECT * FROM user WHERE usertype = \"RISTORATORE\"";
	public UserDAO() {

	}

	public List<User> getAllRistoratori() {
		List<User> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_GET_RISTORATORI);
			User user;
			while (resultSet.next()) {
				int id = resultSet.getInt("iduser");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String usertype = resultSet.getString("usertype");
				String mail = resultSet.getString("mail");
				String cognome = resultSet.getString("cognome");
				user = new User(username, password, usertype,mail , cognome);
				user.setId(id);
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public List<User> getAll() {
		List<User> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			User user;
			while (resultSet.next()) {
				int id = resultSet.getInt("iduser");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String usertype = resultSet.getString("usertype");
				String mail = resultSet.getString("mail");
				String cognome = resultSet.getString("cognome");
				user = new User(username, password, usertype,mail , cognome);
				user.setId(id);
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public boolean insert(User userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, userToInsert.getUsername());
			preparedStatement.setString(2, userToInsert.getPassword());
			preparedStatement.setString(3, userToInsert.getUsertype());
			preparedStatement.setString(4, userToInsert.getMail());
			preparedStatement.setString(5, userToInsert.getCognome());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public User read(int userId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String username, password, usertype;

			username = resultSet.getString("username");
			password = resultSet.getString("password");
			usertype = resultSet.getString("usertype");
			String mail = resultSet.getString("mail");
			String cognome = resultSet.getString("cognome");
			User user = new User(username, password, usertype,mail , cognome);
			user.setId(resultSet.getInt("iduser"));

			return user;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(User userToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		// Check if id is present
		if (userToUpdate.getId() == 0)
			return false;
		//User userRead = read(userToUpdate.getId());
		//if (!userRead.equals(userToUpdate)) {
			try {
				// Fill the userToUpdate object
				/*if (userToUpdate.getUsername() == null || userToUpdate.getUsername().equals("")) {
					userToUpdate.setUsername(userRead.getUsername());
				}

				if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
					userToUpdate.setPassword(userRead.getPassword());
				}

				if (userToUpdate.getUsertype() == null || userToUpdate.getUsertype().equals("")) {
					userToUpdate.setUsertype(userRead.getUsertype());
				}*/
				
				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, userToUpdate.getUsername());
				preparedStatement.setString(2, userToUpdate.getPassword());
				preparedStatement.setString(3, userToUpdate.getUsertype());
				preparedStatement.setString(4, userToUpdate.getMail());
				preparedStatement.setString(5, userToUpdate.getCognome());
				preparedStatement.setInt(6, userToUpdate.getId());
				//System.out.println(preparedStatement.toString());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		//}

		//return false;

	}

	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}


}
