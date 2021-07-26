package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.OrdineDTO;
import it.contrader.model.Ordine;

public class OrdineConverter {

	public OrdineDTO toDTO(Ordine ordine) {
		OrdineDTO ordDTO = new OrdineDTO(ordine.getIdordine(), ordine.getIduser(), ordine.getIdrist(), ordine.getIdprodotto(), ordine.getQuantita(), ordine.getData(), ordine.getPrezzo(), ordine.getFlag());
		return ordDTO;
	}

	public Ordine toEntity(OrdineDTO ordineDTO) {
		Ordine ordine = new Ordine(ordineDTO.getIdordine(), ordineDTO.getIduser(), ordineDTO.getIdrist(), ordineDTO.getIdprodotto(), ordineDTO.getQuantita(), ordineDTO.getData(), ordineDTO.getPrezzo(), ordineDTO.getFlag());
		return ordine;
	}
	
	/**
	 * Metodo per convertire le liste di Ristorante.
	 */
	public List<OrdineDTO> toDTOList(List<Ordine> ordineList) {
		//Crea una lista vuota.
		List<OrdineDTO> ordDTOList = new ArrayList<OrdineDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Ordine ordine : ordineList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			ordDTOList.add(toDTO(ordine));
		}
		return ordDTOList;
	}	
}
