package it.contrader.servlets;
import java.io.IOException;
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

public class UserOrdiniServlet extends HttpServlet {
	public UserOrdiniServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		OrdineService service = new OrdineService();
		List<OrdineDTO>listDTO = service.getAllByUser(Integer.parseInt(request.getSession().getAttribute("idSession").toString()));
		request.setAttribute("listOrdini", listDTO);
		UserService uS = new UserService();
		List<UserDTO>listUserDTO = uS.getAll();
		request.setAttribute("listUtenti", listUserDTO);
		RistoranteService rS = new RistoranteService();
		List<RistoranteDTO>listRistoDTO = rS.getAll();
		request.setAttribute("listRisto", listRistoDTO);
		ProdottoService pS = new ProdottoService();
		List<ProdottoDTO>listProdottoDTO = pS.getAll();
		request.setAttribute("listProdotto", listProdottoDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		request.setAttribute("messaggio","");
		
		switch (mode.toUpperCase()) {
		case "ORDLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/User/ordinimanager.jsp").forward(request, response);
			break;

		case "DELETE":
			int idordine = Integer.parseInt(request.getParameter("idordine"));
			OrdineService ordineService = new OrdineService();
			OrdineDTO ordine = ordineService.read(idordine);
			
			if(ordine.getFlag().equals("READY")) {
				request.setAttribute("messaggio","Non puoi cancellare un ordine già pronto");
				updateList(request);
				getServletContext().getRequestDispatcher("/User/ordinimanager.jsp").forward(request, response);
				break;
			}
			else {
				ordineService.delete(idordine);
				updateList(request);
				request.setAttribute("mode","ORDLIST");
				getServletContext().getRequestDispatcher("/Ristoratore/ordinimanager.jsp").forward(request, response);
			}
			break;
		}
	}
}
