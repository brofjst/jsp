package it.contrader.service;

import java.util.List;

import it.contrader.converter.OrdineConverter;
import it.contrader.dao.OrdineDAO;
import it.contrader.dto.OrdineDTO;

public class OrdineService {
	private OrdineDAO ordineDao;
	private OrdineConverter ordineConverter;
	
	//Istanzio DAO  e Converter specifici.
	public OrdineService(){
		this.ordineDao = new OrdineDAO();
		this.ordineConverter = new OrdineConverter();
	}
	
	public boolean updateFlag(int id, String flag) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return ordineDao.updateFlag(id, flag);
	}
	
	public List<OrdineDTO> getAllById(int idordine){
		return ordineConverter.toDTOList(ordineDao.getAllById(idordine));
	}

	public List<OrdineDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return ordineConverter.toDTOList(ordineDao.getAll());
	}


	public OrdineDTO read(int idordine) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return ordineConverter.toDTO(ordineDao.read(idordine));
	}


	public boolean insert(OrdineDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return ordineDao.insert(ordineConverter.toEntity(dto));
	}


	public boolean update(OrdineDTO dto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return ordineDao.update(ordineConverter.toEntity(dto));
	}
	

	public boolean delete(int idordine) {
		// Questo mtodo chiama direttamente il DAO
		return ordineDao.delete(idordine);
	}
	
	public List<OrdineDTO> getAllByRist(int idrist){
		return ordineConverter.toDTOList(ordineDao.getAllByRist(idrist));
	}
	
	public List<OrdineDTO> getAllByUser(int idprodotto){
		return ordineConverter.toDTOList(ordineDao.getAllByUser(idprodotto));
	}
	
	public List<OrdineDTO> getAllByRistoratore(int idristoratore){
		return ordineConverter.toDTOList(ordineDao.getAllByRistoratore(idristoratore));
	}
}
