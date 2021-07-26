package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.ProdottoDTO;
import it.contrader.model.Prodotto;

public class ProdottoConverter {

	public ProdottoDTO toDTO(Prodotto prod) {
		ProdottoDTO prodDTO = new ProdottoDTO(prod.getIdprodotto(), prod.getQuantita(), prod.getDescrizione(), prod.getPrezzo(), prod.getIdrist());
		return prodDTO;
	}

	public Prodotto toEntity(ProdottoDTO prodDTO) {
		Prodotto prod = new Prodotto(prodDTO.getIdprodotto(), prodDTO.getQuantita(), prodDTO.getDescrizione(), prodDTO.getPrezzo(), prodDTO.getIdrist());
		return prod;
	}
	
	public List<ProdottoDTO> toDTOList(List<Prodotto> prodList) {
		//Crea una lista vuota.
		List<ProdottoDTO> prodDTOList = new ArrayList<ProdottoDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Prodotto prod : prodList) {
			prodDTOList.add(toDTO(prod));
		}
		return prodDTOList;
	}
}
