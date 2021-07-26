package it.contrader.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.OrdineDTO;
import it.contrader.dto.ProdottoDTO;
import it.contrader.dto.RistoranteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.OrdineService;
import it.contrader.service.ProdottoService;
import it.contrader.service.RistoranteService;
import it.contrader.service.UserService;

public class UserCarrelloServlet extends HttpServlet{
	public UserCarrelloServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		RistoranteService service = new RistoranteService();
		List<RistoranteDTO>listDTO = service.getAll();
		request.setAttribute("ristorantiList", listDTO);
		
		ProdottoService serviceProdotto = new ProdottoService();
		List<ProdottoDTO>listProdottoDTO = serviceProdotto.getAll();
		request.setAttribute("prodottiList", listProdottoDTO);
	}
	
	private void svuotaCarrello(HttpServletRequest request) {
		HashMap<Integer,Integer> carrQuantita = (HashMap<Integer,Integer>) request.getSession().getAttribute("carrelloQuantita");
		HashMap<Integer,Float> carrPrezzo = (HashMap<Integer,Float>) request.getSession().getAttribute("carrelloPrezzo");
		
		carrQuantita.clear();
		carrPrezzo.clear();
		
		request.getSession().setAttribute("carrelloQuantita", carrQuantita);
		request.getSession().setAttribute("carrelloPrezzo", carrPrezzo);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		request.setAttribute("messaggio", "");
		
		switch (mode.toUpperCase()) {

		case "CARRLIST":
			updateList(request);
			request.setAttribute("messaggio", "");
			getServletContext().getRequestDispatcher("/User/carrellomanager.jsp").forward(request, response);
			break;
			
		case "DELETE":
			int idprodotto = Integer.parseInt(request.getParameter("idprodotto"));
			HashMap<Integer,Integer> carrelloQuantita = (HashMap<Integer,Integer>) request.getSession().getAttribute("carrelloQuantita");
			HashMap<Integer,Float> carrelloPrezzo = (HashMap<Integer,Float>) request.getSession().getAttribute("carrelloPrezzo");
			
			carrelloQuantita.remove(idprodotto);
			carrelloPrezzo.remove(idprodotto);
			
			request.getSession().setAttribute("carrelloQuantita", carrelloQuantita);
			request.getSession().setAttribute("carrelloPrezzo", carrelloPrezzo);
			
			updateList(request);
			
			request.setAttribute("messaggio","");
			getServletContext().getRequestDispatcher("/User/carrellomanager.jsp").forward(request, response);
			break;
			
		case "SVUOTA CARRELLO":
			svuotaCarrello(request);
			updateList(request);
			request.setAttribute("messaggio","");
			getServletContext().getRequestDispatcher("/User/carrellomanager.jsp").forward(request, response);
			break;
			
		case "ORDINA":
			ProdottoService prodottoService = new ProdottoService();
			HashMap<Integer,Integer> carrelloQnt = (HashMap<Integer,Integer>) request.getSession().getAttribute("carrelloQuantita");
			HashMap<Integer,Float> carrelloPrice = (HashMap<Integer,Float>) request.getSession().getAttribute("carrelloPrezzo");
			
			for (int key : carrelloQnt.keySet()) {
				List<ProdottoDTO> prodList = prodottoService.getAllById(key);
				String data = java.time.LocalDate.now().toString();
				ProdottoDTO prod = prodList.get(0);
				
				if(prod.getQuantita() >= carrelloQnt.get(key) && carrelloPrice.get(key) == prod.getPrezzo()) {
					
					OrdineDTO ordine = new OrdineDTO(Integer.parseInt(request.getSession().getAttribute("idSession").toString()), prod.getIdrist(), key, carrelloQnt.get(key), data, carrelloPrice.get(key));
					
					OrdineService ordineService = new OrdineService();
					ordineService.insert(ordine);
					prod.setQuantita(prod.getQuantita()-carrelloQnt.get(key));
					prod.setIdprodotto(key);
					prodottoService.updateQuantita(prod);

				} else {
					request.setAttribute("messaggio","Prodotto non più disponibile");
					svuotaCarrello(request);
					updateList(request);
					getServletContext().getRequestDispatcher("/User/carrellomanager.jsp").forward(request, response);
				}
			}
			
			svuotaCarrello(request);
			updateList(request);
			request.setAttribute("messaggio","Ordine effettuato con successo");
			getServletContext().getRequestDispatcher("/User/carrellomanager.jsp").forward(request, response);
			break;
		}
	}
}
