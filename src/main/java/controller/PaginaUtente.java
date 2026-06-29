package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Utente;
/**
 * Servlet implementation class PaginaUtente
 */
@WebServlet("/PaginaUtente")
public class PaginaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupero la sessione esistente, se non esiste NON ne crea una nuova e restituisce null
				HttpSession session = request.getSession(false);
				if(session == null || session.getAttribute("utente")==null) {
					//redirect alla pagina di login se l'utente non e' autenticato
					response.sendRedirect("Login");
					return;
				}
				
			//se l'utente e' autenticato recupero i dati per mostrarli nella sua pagina personale
				
				Utente u = (Utente) session.getAttribute("utente");
				
				String ruolo = u.getRuolo();
				String nome = u.getNome();
				String cognome = u.getCognome();
				String username= u.getUsername();
				String email = u.getEmail();
				String bio = u.getBio();
				//aggiungere il metodo di pagamento
				//String metodoPagamento = u.getMetodoPagamento;
				String indirizzo = 
						u.getNazione() + ", " +
				 		u.getRegione() + ", " +
				 		u.getProvincia() + ", " +
				 		u.getComune() + ", " +
				 		u.getVia() + ", " +
				 		u.getNumCiv();
						
						
				request.setAttribute("ruolo", ruolo);
				request.setAttribute("nome", nome);
				request.setAttribute("cognome", cognome);
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				request.setAttribute("bio", bio);
				//aggiungere il metodo di pagamento
				request.setAttribute("indirizzo", indirizzo);
				request.getRequestDispatcher("/WEB-INF/views/profilo.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
