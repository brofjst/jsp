package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.RistoranteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.RistoranteService;
import it.contrader.service.UserService;

public class AdminRistorantiServlet extends HttpServlet {

	public AdminRistorantiServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		RistoranteService service = new RistoranteService();
		List<RistoranteDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
		
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RistoranteService service = new RistoranteService();
		String mode = request.getParameter("mode");
		RistoranteDTO dto;
		int id;
		boolean ans;
		UserService userService = new UserService();
		List<UserDTO> ristoratori = userService.getAllRistoratori();
		request.setAttribute("ristoratoriList", ristoratori);
		request.setAttribute("messaggio", "");
		
		switch (mode.toUpperCase()) {

		case "RISTLIST":
			RistoranteService uS = new RistoranteService();
			List<RistoranteDTO>listDTO = uS.getAll();
			request.setAttribute("list", listDTO);
			request.setAttribute("messaggio", "");
			getServletContext().getRequestDispatcher("/admin/ristomanager.jsp").forward(request, response);
			break;
			
		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/admin/readristo.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/admin/updateristo.jsp").forward(request, response);
			
			break;
			
		case "UPDATE":
			id = Integer.parseInt(request.getParameter("id"));
			RistoranteService ristService = new RistoranteService();
			RistoranteDTO risto = ristService.read(id);
			
			risto.setNome(request.getParameter("nome"));
			risto.setPaese(request.getParameter("paese"));
			risto.setPiva(request.getParameter("piva"));
			risto.setIndirizzo(request.getParameter("indirizzo"));
			ristService.updateAll(risto);
			
			request.setAttribute("messaggio","");
			request.setAttribute("mode","RISTLIST");
			updateList(request);
			getServletContext().getRequestDispatcher("/admin/ristomanager.jsp").forward(request, response);
			break;
			
		case "INSERTRIST":
			String paeseI = request.getParameter("paese").toString();
			String pivaI = request.getParameter("piva").toString();
			String indirizzoI = request.getParameter("indirizzo").toString();
			String nomeI = request.getParameter("nome").toString();
			int idristI = Integer.parseInt(request.getParameter("idrist").toString());

			RistoranteService r = new RistoranteService();
			RistoranteDTO newRist = new RistoranteDTO(paeseI, pivaI, indirizzoI, idristI, nomeI);
			request.setAttribute("ans", r.insert(newRist));
			updateList(request);
			
			getServletContext().getRequestDispatcher("/admin/ristomanager.jsp").forward(request, response);
			break;
			
		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			
			request.setAttribute("messaggio","");
			request.setAttribute("mode","USERLIST");
			getServletContext().getRequestDispatcher("/admin/ristomanager.jsp").forward(request, response);
			break;
		}
	}
}
