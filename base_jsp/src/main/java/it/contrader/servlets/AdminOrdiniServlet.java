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

public class AdminOrdiniServlet extends HttpServlet {
	
	public AdminOrdiniServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		OrdineService service = new OrdineService();
		List<OrdineDTO>listDTO = service.getAll();
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
		OrdineService service = new OrdineService();
		String mode = request.getParameter("mode");
		OrdineDTO dto;
		int id;
		boolean ans;
		UserService userService = new UserService();
		List<UserDTO> ristoratori = userService.getAllRistoratori();
		
		
		switch (mode.toUpperCase()) {

		case "ORDLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/admin/ordinimanager.jsp").forward(request, response);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			request.setAttribute("mode","ORDLIST");
			getServletContext().getRequestDispatcher("/admin/ordinimanager.jsp").forward(request, response);
			break;
		}
	}
}
