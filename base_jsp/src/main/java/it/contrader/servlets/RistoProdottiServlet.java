package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.ProdottoDTO;
import it.contrader.dto.RistoranteDTO;
import it.contrader.service.ProdottoService;
import it.contrader.service.RistoranteService;

public class RistoProdottiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RistoProdottiServlet() {
	}

	public void updateList(HttpServletRequest request) {
		RistoranteService rS = new RistoranteService();
		List<RistoranteDTO>listRistoDTO = rS.getAllById(Integer.parseInt(request.getSession().getAttribute("idSession").toString()));
		request.setAttribute("listRisto", listRistoDTO);
		ProdottoService pS = new ProdottoService();
		//List<ProdottoDTO>listProdottoDTO = pS.getAllByRist(Integer.parseInt(request.getSession().getAttribute("idSession").toString()));
		List<ProdottoDTO>listProdottoDTO = pS.getAllByRistoratore(Integer.parseInt(request.getSession().getAttribute("idSession").toString()));
		request.setAttribute("listProdotto", listProdottoDTO);
	}

	private boolean checkInteger(String id) {
		if ((!id.matches("[0-9]+"))) {
			return false;
   	 	}
		return true;
	}

   private boolean checkFloat(String prezzo) {
   		if(!prezzo.matches("[-+]?[0-9]*\\.?[0-9]+")) {
   			return false;
   		}
   		return true;
   }

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdottoService service = new ProdottoService();
		String mode = request.getParameter("mode");
		ProdottoDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "PRODLIST":
			updateList(request);
			request.setAttribute("messaggio", "");
			getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);

			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/Ristoratore/readprodotto.jsp").forward(request, response);

			}

			else getServletContext().getRequestDispatcher("/Ristoratore/updateprodotto.jsp").forward(request, response);

			break;

		case "INSERTPROD":
			String nome = request.getParameter("nome").toString();
			String quantita = request.getParameter("quantita").toString();
			String prezzo = request.getParameter("prezzo").toString();
			int  idristorante = Integer.parseInt(request.getParameter("idrist").toString());
			request.setAttribute("messaggio", "");
			if(!checkInteger(quantita)) {
				request.setAttribute("messaggio", "Quantità non valida");
				updateList(request);
				getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			}

			if(!checkFloat(prezzo)) {
				request.setAttribute("messaggio", "Prezzo non valido");
				updateList(request);
				getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			}

			int quantitaInt = Integer.parseInt(quantita);
			float prezzoFloat = Float.parseFloat(prezzo);

			ProdottoDTO prod = new ProdottoDTO(quantitaInt, nome, prezzoFloat, idristorante);
			ProdottoService pS = new ProdottoService();
			request.setAttribute("ans", pS.insert(prod));
			request.setAttribute("mode","PRODLIST");
			updateList(request);
			getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			break;

		case "UPDATE":
			String nomeU = request.getParameter("nome").toString();
			String quantitaU = request.getParameter("quantita").toString();
			String prezzoU = request.getParameter("prezzo").toString();
			int  idristoranteU = Integer.parseInt(request.getParameter("idrist").toString());
			int idprodottoU = Integer.parseInt(request.getParameter("id").toString());
			request.setAttribute("messaggio", "");

			if(!checkInteger(quantitaU)) {
				request.setAttribute("messaggio", "Quantità non valida");
				updateList(request);
				getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			}

			if(!checkFloat(prezzoU)) {
				request.setAttribute("messaggio", "Prezzo non valido");
				updateList(request);
				getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			}

			request.setAttribute("mode","PRODLIST");
			int quantitaIntU = Integer.parseInt(quantitaU);
			float prezzoFloatU = Float.parseFloat(prezzoU);
			ProdottoDTO prodU = new ProdottoDTO(idprodottoU, quantitaIntU, nomeU, prezzoFloatU, idristoranteU);
			ProdottoService pSU = new ProdottoService();
			pSU.update(prodU);
			updateList(request);
			getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);

			request.setAttribute("messaggio","");
			request.setAttribute("mode","PRODLIST");
			getServletContext().getRequestDispatcher("/Ristoratore/prodmanager.jsp").forward(request, response);
			break;
		}
	}
}
