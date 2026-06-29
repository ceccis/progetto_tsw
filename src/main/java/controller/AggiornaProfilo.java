package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Utente;
import model.dao.UtenteDAO;

/**
 * Servlet implementation class AggiornaProfilo
 */
@WebServlet("/AggiornaProfilo")
public class AggiornaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaProfilo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupero la sessione esistente, se non esiste NON ne crea una nuova e restituisce null
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("utente")==null) {
			//redirect alla pagina di login se l'utente non e' autenticato
			response.sendRedirect("Login");
			return;
		}
		
		Utente u = (Utente) session.getAttribute("utente");
		
		String nome = (String) request.getParameter("nome");
		String cognome = (String)request.getParameter("cognome");
		String username = (String)request.getParameter("username");
		String email = (String)request.getParameter("email");
		String bio = (String)request.getParameter("bio");
		//aggiungere metodo pagamento
		String nazione = request.getParameter("nazione");
	    String regione = request.getParameter("regione");
	    String provincia = request.getParameter("provincia");
	    String comune = request.getParameter("comune");
	    String via = request.getParameter("via");
	    String numCiv = request.getParameter("numCiv");
		
	    
	    //aggiorna i dati di utente solo se li ha inseriti nel form, se il campo viene lasciato vuoto vengono lasciati quelli originari
		if(!(nome.trim().isEmpty())) {u.setNome(nome); }
		if(!(cognome.trim().isEmpty())) {u.setCognome(cognome); }
		if(!(username.trim().isEmpty())) {u.setUsername(username); }
		if(!(email.trim().isEmpty())) {u.setEmail(email); }
		if(!(bio.trim().isEmpty())) {u.setBio(bio); }
		//aggiungere metodo pagamento
		if(!(nazione.trim().isEmpty())) {u.setNazione(nazione); }
		if(!(regione.trim().isEmpty())) {u.setRegione(regione); }
		if(!(provincia.trim().isEmpty())) {u.setProvincia(provincia); }
		if(!(comune.trim().isEmpty())) {u.setComune(comune); }
		if(!(via.trim().isEmpty())) {u.setVia(via); }
		if(!(numCiv.trim().isEmpty())) {u.setNumCiv(numCiv); }
		
		
		UtenteDAO dao = new UtenteDAO();
		try {
			dao.doUpdate(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// aggiorno la sessione
        session.setAttribute("utente", u);
        response.sendRedirect("PaginaUtente");

	}
	
}
