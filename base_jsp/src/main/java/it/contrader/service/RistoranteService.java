package it.contrader.service;

import java.util.List;



import it.contrader.converter.RistoranteConverter;
import it.contrader.dto.RistoranteDTO;
import it.contrader.dao.RistoranteDAO;
public class RistoranteService {

	private RistoranteDAO ristoranteDao;
	private RistoranteConverter ristoranteConverter;
	
	//Istanzio DAO  e Converter specifici.
	public RistoranteService(){
		this.ristoranteDao = new RistoranteDAO();
		this.ristoranteConverter = new RistoranteConverter();
	}
	
	
	public List<RistoranteDTO> getAllByRistOfUser(int idutente){
		return ristoranteConverter.toDTOList(ristoranteDao.getAllByRistOfUser(idutente));
	}
	
	public List<RistoranteDTO> getAllById(int idutente){
		return ristoranteConverter.toDTOList(ristoranteDao.getAllById(idutente));
	}

	public List<RistoranteDTO> getAll() {
		// Ottiene una lista di entità e le restituisce convertendole in DTO
		return ristoranteConverter.toDTOList(ristoranteDao.getAll());
	}


	public RistoranteDTO read(int id) {
		// Ottiene un'entità e la restituisce convertendola in DTO
		return ristoranteConverter.toDTO(ristoranteDao.read(id));
	}


	public boolean insert(RistoranteDTO dto) {
		// Converte un DTO in entità e lo passa al DAO per l'inserimento
		return ristoranteDao.insert(ristoranteConverter.toEntity(dto));
	}


	public boolean update(RistoranteDTO dto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return ristoranteDao.update(ristoranteConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return ristoranteDao.delete(id);
	}
	
	public boolean updateAll(RistoranteDTO dto) {
		// Converte un userDTO in entità e lo passa allo userDAO per la modifica
		return ristoranteDao.updateAll(ristoranteConverter.toEntity(dto));
	}
	public boolean deleteByUser(int id) {
		// Questo mtodo chiama direttamente il DAO
		return ristoranteDao.deleteByUser(id);
	}
	
}
