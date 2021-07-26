package it.contrader.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Ordine;

public class OrdineDAO {
	private final String QUERY_ALL_BY_ID = "SELECT * FROM ordine" ;
	private final String QUERY_ALL = "SELECT * FROM ordine";
	private final String QUERY_CREATE = "INSERT INTO ordine ( iduser, idrist, idprodotto , quantita, data, prezzo) VALUES (?,?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM ordine WHERE idordine=?";
	private final String QUERY_UPDATE = "UPDATE ordine SET quantita=?, data=? WHERE idordine=?";
	private final String QUERY_DELETE = "DELETE FROM ordine WHERE idordine=?";
	private final String QUERY_ALL_BY_RIST = "SELECT * FROM ordine WHERE idrist = ?";
	private final String QUERY_SET_FLAG = "UPDATE ordine SET Flag=? WHERE idordine=?";
	private final String QUERY_GET_ALL_BY_USER = "SELECT * FROM ordine where iduser=?";
	private final String QUERY_GET_ALL_BY_RISTORATORE="SELECT o.idordine, o.iduser, idrist, o.idprodotto, o.quantita, o.data, o.prezzo, o.Flag from ordine as o, ristorante as r where o.idrist= r.idristorante and r.id_user = ?";
	
	
	public List<Ordine> getAllByRistoratore(int ristoratore){
		Connection connection = ConnectionSingleton.getInstance();
		List<Ordine> ordineList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_BY_RISTORATORE);
			statement.setInt(1, ristoratore);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
			Ordine ordine = new Ordine();
			ordine.setIdordine(resultSet.getInt("idordine"));
			ordine.setIduser(resultSet.getInt("iduser"));
			ordine.setIdrist(resultSet.getInt("idrist"));
			ordine.setIdprodotto(resultSet.getInt("idprodotto"));
			ordine.setQuantita(resultSet.getInt("quantita"));
			ordine.setData(resultSet.getString("data"));
			ordine.setPrezzo(resultSet.getFloat("prezzo"));
			ordine.setFlag(resultSet.getString("Flag"));
			ordineList.add(ordine);
			}
			return ordineList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ordineList;
	}
	
	public List<Ordine> getAllByUser(int iduser){
		Connection connection = ConnectionSingleton.getInstance();
		List<Ordine> ordineList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_BY_USER);
			statement.setInt(1, iduser);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
			Ordine ordine = new Ordine();
			ordine.setIdordine(resultSet.getInt("idordine"));
			ordine.setIduser(resultSet.getInt("iduser"));
			ordine.setIdrist(resultSet.getInt("idrist"));
			ordine.setIdprodotto(resultSet.getInt("idprodotto"));
			ordine.setQuantita(resultSet.getInt("quantita"));
			ordine.setData(resultSet.getString("data"));
			ordine.setPrezzo(resultSet.getFloat("prezzo"));
			ordine.setFlag(resultSet.getString("Flag"));
			ordineList.add(ordine);
			}
			return ordineList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ordineList;
	}
	
	public boolean updateFlag(int id, String flag) {
		Connection connection = ConnectionSingleton.getInstance();

		try {
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_SET_FLAG);
			preparedStatement.setString(1, flag);
			preparedStatement.setInt(2, id);
			//System.out.println("statement " + preparedStatement.toString());
			int a = preparedStatement.executeUpdate();
			if (a > 0)
				return true;
			else
				return false;

			} catch (SQLException e) {
				return false;
			}
	}

	public List<Ordine> getAllByRist(int idrist){
		Connection connection = ConnectionSingleton.getInstance();
		List<Ordine> ordineList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_ALL_BY_RIST);
			statement.setInt(1, idrist);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Ordine ordine = new Ordine();
			ordine.setIdordine(resultSet.getInt("idordine"));
			ordine.setIduser(resultSet.getInt("iduser"));
			ordine.setIdrist(resultSet.getInt("idrist"));
			ordine.setIdprodotto(resultSet.getInt("idprodotto"));
			ordine.setQuantita(resultSet.getInt("quantita"));
			ordine.setData(resultSet.getString("data"));
			ordine.setPrezzo(resultSet.getFloat("prezzo"));
			ordine.setFlag(resultSet.getString("Flag"));
			ordineList.add(ordine);
			}
			return ordineList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ordineList;
	}
	
	public List<Ordine> getAllById(int idordine){
		Connection connection = ConnectionSingleton.getInstance();
		List<Ordine> ordineList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_ALL_BY_ID);
			statement.setInt(1, idordine);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Ordine ordine = new Ordine();
			ordine.setIdordine(resultSet.getInt("idordine"));
			ordine.setIduser(resultSet.getInt("iduser"));
			ordine.setIdrist(resultSet.getInt("idrist"));
			ordine.setIdprodotto(resultSet.getInt("idprodotto"));
			ordine.setQuantita(resultSet.getInt("quantita"));
			ordine.setData(resultSet.getString("data"));
			ordine.setPrezzo(resultSet.getFloat("prezzo"));
			ordineList.add(ordine);
			}
			return ordineList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ordineList;
	}
	
	
	public List<Ordine> getAll() {
		List<Ordine> ordineList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Ordine ordine;
			while (resultSet.next()) {
				int idordine = resultSet.getInt("idordine");
				int iduser = resultSet.getInt("iduser");
				int idrist = resultSet.getInt("idrist");
				int idprodotto = resultSet.getInt("idprodotto");
				int quantita = resultSet.getInt("quantita");
				String data = resultSet.getString("data");
				float prezzo = resultSet.getFloat("prezzo");
				String flag = resultSet.getString("Flag");

				ordine  = new Ordine(idordine, iduser, idrist, idprodotto, quantita, data, prezzo, flag);
				ordineList.add(ordine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordineList;
	}

	public boolean insert(Ordine ordineToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, ordineToInsert.getIduser());
			preparedStatement.setInt(2, ordineToInsert.getIdrist());
			preparedStatement.setInt(3, ordineToInsert.getIdprodotto());
			preparedStatement.setInt(4, ordineToInsert.getQuantita());
			preparedStatement.setString(5, ordineToInsert.getData());
			preparedStatement.setFloat(6, ordineToInsert.getPrezzo());
			preparedStatement.execute();
			
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Ordine read(int idordine) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, idordine);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			int iduser, idrist, idprodotto, quantita;
			String flag;
			float prezzo;
			String data;

			iduser = resultSet.getInt("iduser");
			idrist = resultSet.getInt("idrist");
			idprodotto = resultSet.getInt("idprodotto");
			quantita = resultSet.getInt("quantita");
			data = resultSet.getString("data");
			prezzo = resultSet.getFloat("prezzo");
			flag = resultSet.getString("Flag");

			
			Ordine ordine = new Ordine(iduser, idrist, idprodotto, quantita, data, prezzo, flag);
			ordine.setIdordine(resultSet.getInt("idordine"));

			return ordine;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean update(Ordine ordineToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (ordineToUpdate.getIdordine() == 0)
			return false;

		Ordine ordineRead = read(ordineToUpdate.getIdordine());
		if (!ordineRead.equals(ordineToUpdate)) {
			try {
				// Fill the userToUpdate object

				if (ordineToUpdate.getData() == null || ordineToUpdate.getData().equals("")) {
					ordineToUpdate.setData(ordineRead.getData());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, ordineToUpdate.getQuantita());
				preparedStatement.setString(2, ordineToUpdate.getData());
				//System.out.println("statement " + preparedStatement.toString());
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

	public boolean delete(int idordine) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, idordine);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}
}
