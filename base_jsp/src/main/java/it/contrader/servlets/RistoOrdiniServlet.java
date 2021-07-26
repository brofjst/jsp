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

public class RistoOrdiniServlet extends HttpServlet {
	public RistoOrdiniServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		OrdineService service = new OrdineService();
		List<OrdineDTO>listDTO = service.getAllByRistoratore(Integer.parseInt(request.getSession().getAttribute("idSession").toString()));
		request.setAttribute("listOrdini", listDTO);
		UserService uS = new UserService();
		List<UserDTO>listUserDTO = uS.getAll();
		request.setAttribute("listUtenti", listUserDTO);
		RistoranteService rS = new RistoranteService();
		List<RistoranteDTO>listRistoDTO = rS.getAllById(Integer.parseInt(request.getSession().getAttribute("idSession").toString()));
		request.setAttribute("listRisto", listRistoDTO);
		ProdottoService pS = new ProdottoService();
		List<ProdottoDTO>listProdottoDTO = pS.getAll();
		request.setAttribute("listProdotto", listProdottoDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineService service = new OrdineService();
		String mode = request.getParameter("mode");
		OrdineDTO dto;
		int id;
		boolean ans;
		UserService userService = new UserService();
		
		switch (mode.toUpperCase()) {

		case "ORDLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/Ristoratore/ordinimanager.jsp").forward(request, response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			RistoranteService rS = new RistoranteService();
			
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/Ristoratore/readordini.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/Ristoratore/updateordini.jsp").forward(request, response);
			
			break;
			
		case "SETFLAG":
			int idordine = Integer.parseInt(request.getParameter("id"));
			OrdineService ordService = new OrdineService();
			OrdineDTO ord = ordService.read(idordine);
			if(ord.getFlag().equals("READY")) 
				ord.setFlag("NOT_READY");
			else ord.setFlag("READY");
			
			ordService.updateFlag(ord.getIdordine(), ord.getFlag());
			updateList(request);
			request.setAttribute("mode","ORDLIST");
			getServletContext().getRequestDispatcher("/Ristoratore/ordinimanager.jsp").forward(request, response);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			request.setAttribute("mode","ORDLIST");
			getServletContext().getRequestDispatcher("/Ristoratore/ordinimanager.jsp").forward(request, response);
			break;
		}
	}
}
