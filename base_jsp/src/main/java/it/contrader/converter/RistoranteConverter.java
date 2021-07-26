package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Ristorante;

import it.contrader.dto.RistoranteDTO;
import it.contrader.model.Ristorante;


public class RistoranteConverter   {
	
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo User.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public RistoranteDTO toDTO(Ristorante rist) {
		RistoranteDTO ristDTO = new RistoranteDTO(rist.getId(), rist.getPaese(), rist.getPiva(), rist.getIndirizzo(), rist.getIduser(), rist.getNome());
		return ristDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Ristorante toEntity(RistoranteDTO ristDTO) {
		Ristorante rist = new Ristorante(ristDTO.getId(), ristDTO.getPaese(), ristDTO.getPiva(), ristDTO.getIndirizzo(), ristDTO.getIduser(), ristDTO.getNome());
		return rist;
	}
	
	/**
	 * Metodo per convertire le liste di Ristorante.
	 */
	public List<RistoranteDTO> toDTOList(List<Ristorante> ristList) {
		//Crea una lista vuota.
		List<RistoranteDTO> ristDTOList = new ArrayList<RistoranteDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Ristorante rist : ristList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			ristDTOList.add(toDTO(rist));
		}
		return ristDTOList;
	}	
}
