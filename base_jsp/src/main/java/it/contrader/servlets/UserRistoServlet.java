package it.contrader.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.ProdottoDTO;
import it.contrader.dto.RistoranteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ProdottoService;
import it.contrader.service.RistoranteService;
import it.contrader.service.UserService;

public class UserRistoServlet extends HttpServlet{
	public UserRistoServlet() {
	}
	
	public void updateRistoList(HttpServletRequest request) {
		RistoranteService service = new RistoranteService();
		List<RistoranteDTO>listDTO = service.getAll();
		request.setAttribute("ristorantiList", listDTO);
	}

	public void updateProdottiList(HttpServletRequest request) {
		ProdottoService service = new ProdottoService();
		List<ProdottoDTO>listDTO = service.getAllByRist(Integer.parseInt(request.getSession().getAttribute("idristorante").toString()));
		request.setAttribute("prodottiList", listDTO);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		request.setAttribute("messaggio", "");
		
		switch (mode.toUpperCase()) {

		case "RISTLIST":
			updateRistoList(request);
			request.setAttribute("messaggio", "");
			getServletContext().getRequestDispatcher("/User/ristomanager.jsp").forward(request, response);
			break;
			
		case "PRODLIST":
			//metto l'idristorante nella sessione così non lo perdo tra un salto e l'altro
			int idrist = Integer.parseInt(request.getParameter("idristorante").toString());
			request.getSession().setAttribute("idristorante", idrist);
			updateProdottiList(request);
			request.setAttribute("messaggio", "");
			getServletContext().getRequestDispatcher("/User/prodmanager.jsp").forward(request, response);
			break;
			
		//ti rimanda alla view dei prodotti finchè non ha finito di prendere roba
		case "ADDCARRELLO":
			HashMap<Integer,Integer> carrelloQuantita = new HashMap<Integer,Integer>();
			HashMap<Integer,Float> carrelloPrezzo = new HashMap<Integer,Float>();
			int idprodotto = Integer.parseInt(request.getParameter("idprodotto").toString());
			float prezzo = Float.parseFloat(request.getParameter("prezzo").toString());
			
			String inputQuantita = request.getParameter("inputQuantita").toString();
			if ((!inputQuantita.matches("[0-9]+"))) {
				updateProdottiList(request);
				request.setAttribute("messaggio", "Inserisci un numero intero");
				getServletContext().getRequestDispatcher("/User/prodmanager.jsp").forward(request, response);
			}
			
			carrelloQuantita = (HashMap<Integer,Integer>) request.getSession().getAttribute("carrelloQuantita");
			carrelloPrezzo = (HashMap<Integer,Float>) request.getSession().getAttribute("carrelloPrezzo");
			
			carrelloQuantita.put(idprodotto, Integer.parseInt(inputQuantita));
			carrelloPrezzo.put(idprodotto, prezzo);
			
			request.getSession().setAttribute("carrelloQuantita", carrelloQuantita);
			request.getSession().setAttribute("carrelloPrezzo", carrelloPrezzo);
			
			request.setAttribute("messaggio", "Prodotto inserito nel carrello");
			updateProdottiList(request);
			getServletContext().getRequestDispatcher("/User/prodmanager.jsp").forward(request, response);
			break;
		}
	}
}
