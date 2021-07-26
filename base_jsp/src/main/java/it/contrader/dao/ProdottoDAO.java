package it.contrader.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Prodotto;

public class ProdottoDAO {
	private final String QUERY_ALL_BY_ID = "SELECT * FROM prodotto WHERE idprodotto=?" ;
	private final String QUERY_ALL = "SELECT * FROM prodotto";
	private final String QUERY_CREATE = "INSERT INTO prodotto ( quantita, descrizione, prezzo , idrist) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM prodotto WHERE idprodotto=?";
	private final String QUERY_UPDATE = "UPDATE prodotto SET quantita=?, descrizione=?, prezzo=?  WHERE idprodotto=?";
	private final String QUERY_DELETE = "DELETE FROM prodotto WHERE idprodotto=?";
	private final String QUERY_PRODOTTI_USER = "SELECT p.idprodotto, p.quantita, p.descrizione, p.prezzo, idrist FROM prodotto AS p, user AS u, ristorante as r "
			+ "									WHERE p.idrist = r.idristorante and r.id_user = u.iduser and r.id_user =?";

	private final String QUERY_IDRIST = "SELECT idrist FROM prodotto WHERE idprodotto = ?";
	private final String QUERY_GET_RIST = "SELECT idristorante FROM ristorante WHERE id_user = ?";
	private final String QUERY_UPDATE_QUANTITA = "UPDATE prodotto SET quantita=? WHERE idprodotto=?";
	private final String QUERY_GET_ALL_BY_RIST = "SELECT p.idprodotto, p.quantita, p.descrizione, p.prezzo, p.idrist FROM prodotto as p, ristorante as r where r.idristorante = ? and p.idrist = r.idristorante;";
	private final String QUERY_GET_ALL_BY_RISTORATORE="SELECT p.idprodotto, p.quantita, p.descrizione, p.prezzo, p.idrist FROM prodotto as p, ristorante as r where r.idristorante = p.idrist and r.id_user = ?;";
	public ProdottoDAO() {

	}
	
public List<Prodotto> getAllByRistoratore(int idristoratore) {
		
		Connection connection = ConnectionSingleton.getInstance();
		List<Prodotto> prodottoList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_BY_RISTORATORE);
			statement.setInt(1, idristoratore);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
			Prodotto prodotto = new Prodotto();
			prodotto.setIdprodotto(resultSet.getInt("idprodotto"));
			prodotto.setIdrist(resultSet.getInt("idrist"));
			prodotto.setQuantita(resultSet.getInt("quantita"));
			prodotto.setDescrizione(resultSet.getString("descrizione"));
			prodotto.setPrezzo(resultSet.getFloat("prezzo"));
			prodottoList.add(prodotto);
			}
			return prodottoList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return prodottoList;
	}

	public boolean updateQuantita(Prodotto prodottoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		// Check if id is present
		if (prodottoToUpdate.getIdprodotto() == 0)
			return false;

		Prodotto prodottoRead = read(prodottoToUpdate.getIdprodotto());
		if (!prodottoRead.equals(prodottoToUpdate)) {
			try {
				// Fill the userToUpdate object

				if (prodottoToUpdate.getDescrizione() == null || prodottoToUpdate.getDescrizione().equals("")) {
					prodottoToUpdate.setDescrizione(prodottoRead.getDescrizione());
				}
				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE_QUANTITA);
				preparedStatement.setInt(1, prodottoToUpdate.getQuantita());
				preparedStatement.setInt(2, prodottoToUpdate.getIdprodotto());
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
	
	public List<Prodotto> getAllByRist(int idristorante) {
		
		Connection connection = ConnectionSingleton.getInstance();
		List<Prodotto> prodottoList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_BY_RIST);
			statement.setInt(1, idristorante);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Prodotto prodotto = new Prodotto();
			prodotto.setIdprodotto(resultSet.getInt("idprodotto"));
			prodotto.setIdrist(resultSet.getInt("idrist"));
			prodotto.setQuantita(resultSet.getInt("quantita"));
			prodotto.setDescrizione(resultSet.getString("descrizione"));
			prodotto.setPrezzo(resultSet.getFloat("prezzo"));
			prodottoList.add(prodotto);
			}
			return prodottoList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return prodottoList;
	}
	
	public List<Integer> getUserRist(int userID) {
		Connection connection = ConnectionSingleton.getInstance();
		List<Integer> ristList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_GET_RIST);
			statement.setInt(1, userID);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
				int ristID = resultSet.getInt("idristorante");
				ristList.add(ristID);
			}
			return ristList ;
		}catch(SQLException e) {
			System.out.println("#### " + e.getMessage());
		}
		return ristList;
	}
	
	public int getIdrist(int idprodotto){
		Connection connection = ConnectionSingleton.getInstance();
		int idRist = 0000;
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_IDRIST);
			statement.setInt(1, idprodotto);
			ResultSet resultSet = statement.executeQuery() ;
			resultSet.next();
			idRist = resultSet.getInt("idrist");
			return idRist ;
		}catch(SQLException e) {
			System.out.println("#### " + e.getMessage());
		}
		return idRist;
	}
	
	public List<Prodotto> getProdottiUser(int userId){
		Connection connection = ConnectionSingleton.getInstance();
		List<Prodotto> prodottoList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_PRODOTTI_USER);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Prodotto prodotto = new Prodotto();
			prodotto.setIdprodotto(resultSet.getInt("idprodotto"));
			prodotto.setIdrist(resultSet.getInt("idrist"));
			prodotto.setQuantita(resultSet.getInt("quantita"));
			prodotto.setDescrizione(resultSet.getString("descrizione"));
			prodotto.setPrezzo(resultSet.getFloat("prezzo"));
			prodottoList.add(prodotto);
			}
			return prodottoList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return prodottoList;
	}
	
	public List<Prodotto> getAllById(int idprodotto){
		Connection connection = ConnectionSingleton.getInstance();
		List<Prodotto> prodottoList = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_ALL_BY_ID);
			statement.setInt(1, idprodotto);
			ResultSet resultSet = statement.executeQuery() ;
			while (resultSet.next()) {
			Prodotto prodotto = new Prodotto();
			prodotto.setIdrist(resultSet.getInt("idprodotto"));
			prodotto.setIdrist(resultSet.getInt("idrist"));
			prodotto.setQuantita(resultSet.getInt("quantita"));
			prodotto.setDescrizione(resultSet.getString("descrizione"));
			prodotto.setPrezzo(resultSet.getFloat("prezzo"));
			prodottoList.add(prodotto);
			}
			return prodottoList ;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return prodottoList;
	}
	
	
	public List<Prodotto> getAll() {
		List<Prodotto> prodottoList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Prodotto prodotto;
			while (resultSet.next()) {
				int idprodotto = resultSet.getInt("idprodotto");
				int quantita = resultSet.getInt("quantita");
				String descrizione = resultSet.getString("descrizione");
				float prezzo = resultSet.getFloat("prezzo");
				int idrist = resultSet.getInt("idrist");
				prodotto  = new Prodotto(idprodotto, quantita, descrizione,prezzo, idrist);
				prodottoList.add(prodotto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodottoList;
	}

	public boolean insert(Prodotto prodottoToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, prodottoToInsert.getQuantita());
			preparedStatement.setString(2, prodottoToInsert.getDescrizione());
			preparedStatement.setFloat(3, prodottoToInsert.getPrezzo());
			preparedStatement.setInt(4, prodottoToInsert.getIdrist());
			//System.out.println(preparedStatement);
			preparedStatement.execute();
			
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public Prodotto read(int prodIdprodotto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, prodIdprodotto);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String descrizione;
			int quantita, idrist;
			float prezzo;

			quantita = resultSet.getInt("quantita");
			descrizione = resultSet.getString("descrizione");
			prezzo = resultSet.getFloat("prezzo");
			idrist = resultSet.getInt("idrist");
			
			Prodotto prodotto = new Prodotto(quantita, descrizione, prezzo, idrist);
			prodotto.setIdprodotto(resultSet.getInt("idprodotto"));

			return prodotto;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean update(Prodotto prodottoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (prodottoToUpdate.getIdprodotto() == 0)
			return false;

		Prodotto prodottoRead = read(prodottoToUpdate.getIdprodotto());
		if (!prodottoRead.equals(prodottoToUpdate)) {
			try {
				// Fill the userToUpdate object

				if (prodottoToUpdate.getDescrizione() == null || prodottoToUpdate.getDescrizione().equals("")) {
					prodottoToUpdate.setDescrizione(prodottoRead.getDescrizione());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, prodottoToUpdate.getQuantita());
				preparedStatement.setString(2, prodottoToUpdate.getDescrizione());
				preparedStatement.setFloat(3, prodottoToUpdate.getPrezzo());
				preparedStatement.setInt(4, prodottoToUpdate.getIdprodotto());
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

	public boolean delete(int idprodotto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, idprodotto);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}
}
