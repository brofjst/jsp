package it.contrader.service;

import java.util.List;

import it.contrader.converter.ProdottoConverter;
import it.contrader.dao.ProdottoDAO;
import it.contrader.dto.ProdottoDTO;

public class ProdottoService {
	private ProdottoDAO prodottoDao;
	private ProdottoConverter prodottoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public ProdottoService(){
		this.prodottoDao = new ProdottoDAO();
		this.prodottoConverter = new ProdottoConverter();
	}
	
	public List<ProdottoDTO> getProdottiUser(int userId){
		return prodottoConverter.toDTOList(prodottoDao.getProdottiUser(userId));
	}

	
	public List<ProdottoDTO> getAllById(int idprodotto){
		return prodottoConverter.toDTOList(prodottoDao.getAllById(idprodotto));
	}

	public List<ProdottoDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return prodottoConverter.toDTOList(prodottoDao.getAll());
	}


	public ProdottoDTO read(int idprodotto) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return prodottoConverter.toDTO(prodottoDao.read(idprodotto));
	}


	public boolean insert(ProdottoDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return prodottoDao.insert(prodottoConverter.toEntity(dto));
	}


	public boolean update(ProdottoDTO dto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return prodottoDao.update(prodottoConverter.toEntity(dto));
	}
	
	public int getIdrist(int idprodotto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return prodottoDao.getIdrist(idprodotto);
	}

	public boolean delete(int idprodotto) {
		// Questo mtodo chiama direttamente il DAO
		return prodottoDao.delete(idprodotto);
	}
	
	public List<Integer> getUserRist(int userID) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return prodottoDao.getUserRist(userID);
	}
	
	public List<ProdottoDTO> getAllByRist(int idristorante){
		return prodottoConverter.toDTOList(prodottoDao.getAllByRist(idristorante));
	}
	
	public boolean updateQuantita(ProdottoDTO dto) {
		return prodottoDao.updateQuantita(prodottoConverter.toEntity(dto));
	}
	
	public List<ProdottoDTO> getAllByRistoratore(int idristoratore){
		return prodottoConverter.toDTOList(prodottoDao.getAllByRistoratore(idristoratore));
	}
}
