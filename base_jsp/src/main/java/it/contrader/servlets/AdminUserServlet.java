package it.contrader.servlets;

import java.util.List;





import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.OrdineDTO;
import it.contrader.dto.ProdottoDTO;
import it.contrader.dto.RistoranteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Ristorante;
import it.contrader.service.OrdineService;
import it.contrader.service.ProdottoService;
import it.contrader.service.RistoranteService;
import it.contrader.service.UserService;

public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminUserServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		UserService service = new UserService();
		List<UserDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		String mode = request.getParameter("mode");
		UserDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "USERLIST":
			//updateList(request);
			UserService uS = new UserService();
			List<UserDTO>listDTO = uS.getAll();
			request.setAttribute("list", listDTO);
			request.setAttribute("messaggio", "");
			getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/admin/readuser.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/admin/updateuser.jsp").forward(request, response);
			
			break;

		case "INSERTUSER":
			String usernameM = request.getParameter("username").toString();
			String pwdM = request.getParameter("password").toString();
			String usertypeM = request.getParameter("usertype").toString();
			String mailM = request.getParameter("mail").toString();
			String cognomeM = request.getParameter("cognome").toString();
			UserDTO userNewM = new UserDTO(usernameM, pwdM, usertypeM, mailM, cognomeM);
			UserService uM = new UserService();
			
			//setto il messagio vuoto cosi ogni volta che ci rientro non mi rimane il messaggio vecchio
			request.setAttribute("messaggio", "");

			//controllo che non abbia cercato di inserire un utente con username e mail non univoci
			if(!checkMailAndUsername(usernameM, mailM)) {
				request.setAttribute("messaggio", "username o mail non univoci\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			if(checkSpace(usernameM)) {
				request.setAttribute("messaggio", "Lo username non può essere vuoto o contenere spazi\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			if(checkSpace(mailM)) {
				request.setAttribute("messaggio", "La mail non può essere vuota o contenere spazi\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			if(checkSpace(pwdM)) {
				request.setAttribute("messaggio", "La password non può essere vuota o contenere spazi\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			//esegui l'udate dello user
			request.setAttribute("ans", uM.insert(userNewM));
			request.setAttribute("mode","USERLIST");
			updateList(request);
			getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
			
			break;
			
		case "UPDATE":
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String mail = request.getParameter("mail").toString();
			String cognome = request.getParameter("cognome").toString();
			String usertype = request.getParameter("usertype");
			id = Integer.parseInt(request.getParameter("id"));
			UserDTO user = new UserDTO(id,username, password, usertype, mail, cognome);
			UserService u = new UserService();
			request.setAttribute("messaggio","");
			request.setAttribute("mode","USERLIST");
			

			//controllo che non abbia cercato di inserire un utente con username e mail non univoci
			if(!checkMailAndUsernameModify(username, mail, id)) {
				request.setAttribute("messaggio", "username o mail già presenti nel database\n");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			if(checkSpace(username)) {
				request.setAttribute("messaggio", "Lo username non può essere vuoto o contenere spazi\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			if(checkSpace(mail)) {
				request.setAttribute("messaggio", "La mail non può essere vuota o contenere spazi\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			if(checkSpace(password)) {
				request.setAttribute("messaggio", "La password non può essere vuota o contenere spazi\n");
				request.setAttribute("mode","USERLIST");
				updateList(request);
				getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
				break;
			}
			
			u.update(user);
			updateList(request);
			getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
			break;
		
		case "DELETE":
			List<UserDTO>listaRistoratori = service.getAllRistoratori();
			int iduser = Integer.parseInt(request.getParameter("id"));
			for(UserDTO  x : listaRistoratori) { //se l'utente è un ristoratore devo cancellare prima tutti i suoi ristoranti ed i prodotti associati
				if(iduser == x.getId()) {
					//devo ciclare tutti i suoi ristoranti
					RistoranteService ristoranteService = new RistoranteService();
					List<RistoranteDTO> ristoranti = ristoranteService.getAllByRistOfUser(iduser);
					System.out.println(ristoranti);
					for(RistoranteDTO risto: ristoranti) {
						ProdottoService prodService = new ProdottoService();
						List<ProdottoDTO> prodList = prodService.getAllByRist(risto.getId());
						for(ProdottoDTO p : prodList) {
							prodService.delete(p.getIdprodotto());
						}
						// cancello il ristorante
						ristoranteService.delete(risto.getId());
					}
				}
			}
			service.delete(iduser);
			updateList(request);
			request.setAttribute("messaggio","");
			request.setAttribute("mode","USERLIST");
			getServletContext().getRequestDispatcher("/admin/usermanager.jsp").forward(request, response);
			break;
		}
	}
	
	//ritorna vero se l'id utente esiste nel databse
		private boolean checkIdUser (int id) {
			UserService u = new UserService();
			List <UserDTO> listusers = u.getAll();
			
			for(UserDTO c : listusers) {
				if(c.getId() == id) return true;
			}
			return false;
		}
		
		//ritorna vero se mail e username non sono già presenti nel database
		private boolean checkMailAndUsername(String username, String mail) {
			UserService u = new UserService();
			List <UserDTO> listusers = u.getAll();
			boolean check = false;
			
			for(UserDTO c : listusers) {
				if(c.getMail().toString().equals(mail) ) {
					check = true;
				}
				
				if(c.getUsername().toString().equals(username)) {
					check = true;
				}
			}
			
			if(check) return false;
				
			return true;
		}
		
		//ritorna vero se mail e username non sono già presenti nel database durante la modifica, quindi conteggià 1 in piu
		private boolean checkMailAndUsernameModify(String username, String mail, int id) {
			UserService u = new UserService();
			List <UserDTO> listusers = u.getAll();
			boolean check = false;
			
			for(UserDTO c : listusers) {
				if(c.getMail().toString().equals(mail) & c.getId() != id ) {
					check = true;
				}
				
				if(c.getUsername().toString().equals(username) & c.getId() != id) {
					check = true;
				}
			}
			
			if(check) return false;
				
			return true;
		}
		
		//ritorna vero se l'id è presente nel database
		private boolean checkIdRistoratore(int id) {
			UserService u = new UserService();
			List <UserDTO> listusers = u.getAllRistoratori();
			
			for(UserDTO c : listusers) 
				if(c.getId() == id) return true;
			
			return false;
		}
		
		private boolean checkIdRistorante(int id) {
			RistoranteService r = new RistoranteService();
			List <RistoranteDTO> listRistoranti = r.getAll();
			
			for(RistoranteDTO c : listRistoranti) 
				if(c.getId() == id) return true;
				
			return false;
		}
		
		private boolean checkIdOrdine(int id) {
			OrdineService r = new OrdineService();
			List <OrdineDTO> listaOrdini = r.getAll();
			
			for(OrdineDTO c : listaOrdini) 
				if(c.getIdordine() == id) return true;
			
			return false;
		}
		
		//ritorna vero se la stringa contiene spazi o è vuota
		private boolean checkSpace(String s) {
			if (s.contains(" ") || s.equals("")) {
				return true;
			}
			return false;
		}
}
