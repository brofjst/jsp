package it.contrader.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Ristorante;

public class RistoranteDAO {
	private final String QUERY_ALL_BY_ID = "SELECT * FROM ristorante WHERE id_user = ?" ;
	private final String QUERY_ALL = "SELECT * FROM ristorante";
	private final String QUERY_CREATE = "INSERT INTO ristorante ( paese, piva, indirizzo, id_user, nome) VALUES (?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM ristorante WHERE idristorante=?";
	private final String QUERY_UPDATE = "UPDATE ristorante SET piva=?, indirizzo=?, paese=?, nome=?  WHERE idristorante=?";
	private final String QUERY_DELETE = "DELETE FROM ristorante WHERE idristorante=?";
	private final String QUERY_DELETE_BY_USER = "DELETE FROM ristorante WHERE id_user=?";
	private final String QUERY_UPDATE_ALL = "UPDATE ristorante SET piva=?, indirizzo=?, paese=?, id_user=?, nome=? WHERE idristorante=?";
	private final String QUERY_GET_ALL_RISTORANTI_OF_USER = "SELECT * FROM ristorante where id_user=?";
	public RistoranteDAO() {

	}
	
	public List<Ristorante> getAllByRistOfUser(int idutente){
		Connection connection = ConnectionSingleton.getInstance();
		List<Ristorante> ristoranteList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_RISTORANTI_OF_USER);
			statement.setInt(1, idutente);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Ristorante ristorante = new Ristorante() ;
			ristorante.setId(resultSet.getInt("idristorante"));
			ristorante.setIduser(resultSet.getInt("id_user"));
			ristorante.setIndirizzo(resultSet.getString("indirizzo"));
			ristorante.setPaese(resultSet.getString("paese"));
			ristorante.setPiva(resultSet.getString("piva"));
			ristorante.setNome(resultSet.getString("nome"));
			ristoranteList.add(ristorante);
			}
				
			return ristoranteList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ristoranteList ;
	}
	
	public boolean deleteByUser(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_BY_USER);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}
	public boolean updateAll(Ristorante ristoranteToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		// Check if id is present
		if (ristoranteToUpdate.getId() == 0)
			return false;
		Ristorante ristoranteRead = read(ristoranteToUpdate.getId());
		if (!ristoranteRead.equals(ristoranteToUpdate)) {
			try {

				if (ristoranteToUpdate.getIndirizzo() == null || ristoranteToUpdate.getIndirizzo().equals("")) {
					ristoranteToUpdate.setIndirizzo(ristoranteRead.getIndirizzo());
				}

				if (ristoranteToUpdate.getPaese() == null || ristoranteToUpdate.getPaese().equals("")) {
					ristoranteToUpdate.setPaese(ristoranteRead.getPaese());
				}

				if (ristoranteToUpdate.getPiva() == null || ristoranteToUpdate.getPiva().equals("")) {
					ristoranteRead.setPiva(ristoranteRead.getPiva());
				}

				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE_ALL);
				preparedStatement.setString(1, ristoranteToUpdate.getPiva());
				preparedStatement.setString(2, ristoranteToUpdate.getIndirizzo());
				preparedStatement.setString(3, ristoranteToUpdate.getPaese());
				preparedStatement.setInt(4, ristoranteToUpdate.getIduser());
				preparedStatement.setString(5, ristoranteToUpdate.getNome());
				preparedStatement.setInt(6, ristoranteToUpdate.getId());
				//System.out.println(preparedStatement.toString());	
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;

	}
	
	public List<Ristorante> getAllById(int idutente){
		Connection connection = ConnectionSingleton.getInstance();
		List<Ristorante> ristoranteList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_ALL_BY_ID);
			statement.setInt(1, idutente);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Ristorante ristorante = new Ristorante() ;
			ristorante.setId(resultSet.getInt("idristorante"));
			ristorante.setIduser(resultSet.getInt("id_user"));
			ristorante.setIndirizzo(resultSet.getString("indirizzo"));
			ristorante.setPaese(resultSet.getString("paese"));
			ristorante.setPiva(resultSet.getString("piva"));
			ristorante.setNome(resultSet.getString("nome"));
			ristoranteList.add(ristorante);
			}
			return ristoranteList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ristoranteList ;
	}
	
	
	public List<Ristorante> getAll() {
		List<Ristorante> ristoranteList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Ristorante ristorante;
			while (resultSet.next()) {
				int id = resultSet.getInt("idristorante");
				String paese = resultSet.getString("paese");
				String piva = resultSet.getString("piva");
				String indirizzo = resultSet.getString("indirizzo");
				String nome = resultSet.getString("nome");
				int iduser = resultSet.getInt("id_user");
				ristorante  = new Ristorante(id, paese, piva,indirizzo , iduser, nome);
				ristoranteList.add(ristorante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ristoranteList;
	}

	public boolean insert(Ristorante ristoranteToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, ristoranteToInsert.getPaese());
			preparedStatement.setString(2, ristoranteToInsert.getPiva());
			preparedStatement.setString(3, ristoranteToInsert.getIndirizzo());
			preparedStatement.setInt(4, ristoranteToInsert.getIduser());
			preparedStatement.setString(5, ristoranteToInsert.getNome());
			//System.out.println(preparedStatement);
			preparedStatement.execute();
			
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Ristorante read(int ristId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, ristId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String paese, piva, indirizzo, nome;
			int idutente;

			paese = resultSet.getString("paese");
			piva = resultSet.getString("piva");
			indirizzo = resultSet.getString("indirizzo");
			idutente = resultSet.getInt("id_user");
			nome = resultSet.getString("nome");
			Ristorante ristorante = new Ristorante(paese, piva, indirizzo,idutente, nome);
			ristorante.setId(resultSet.getInt("idristorante"));

			return ristorante;
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean update(Ristorante ristoranteToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (ristoranteToUpdate.getId() == 0)
			return false;

		Ristorante ristoranteRead = read(ristoranteToUpdate.getId());
		if (!ristoranteRead.equals(ristoranteToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (ristoranteToUpdate.getIndirizzo() == null || ristoranteToUpdate.getIndirizzo().equals("")) {
					ristoranteToUpdate.setIndirizzo(ristoranteRead.getIndirizzo());
				}

				if (ristoranteToUpdate.getPaese() == null || ristoranteToUpdate.getPaese().equals("")) {
					ristoranteToUpdate.setPaese(ristoranteRead.getPaese());
				}

				if (ristoranteToUpdate.getPiva() == null || ristoranteToUpdate.getPiva().equals("")) {
					ristoranteRead.setPiva(ristoranteRead.getPiva());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, ristoranteToUpdate.getPiva());
				preparedStatement.setString(2, ristoranteToUpdate.getIndirizzo());
				preparedStatement.setString(3, ristoranteToUpdate.getPaese());
				preparedStatement.setInt(4, ristoranteToUpdate.getId());
				preparedStatement.setString(5, ristoranteToUpdate.getNome());
				//System.out.println(preparedStatement.toString());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;

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
