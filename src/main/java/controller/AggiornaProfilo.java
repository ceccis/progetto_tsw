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

@WebServlet("/AggiornaProfilo")
public class AggiornaProfilo extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("Login");
            return;
        }

        Utente u = (Utente) session.getAttribute("utente");

        // recupero parametri dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String bio = request.getParameter("bio");
        String metodoPagamento = request.getParameter("metodoPagamento");
        String nazione = request.getParameter("nazione");
        String regione = request.getParameter("regione");
        String provincia = request.getParameter("provincia");
        String comune = request.getParameter("comune");
        String via = request.getParameter("via");
        String numCiv = request.getParameter("numCiv");

        //regex per i campi "letterali" = nome, cognome e indirizzo
        //controlla il campo dall'inizio alla fine e permette solo lettere (accentate e non) e spazi bianchi
        String regexLett = "^[a-zA-ZÀ-ÿ\\s]{2,50}$";
 
        //regex per "email" = controlla che la stringa contenga esattamente una chiocciola e un punto escludendo spazi vuoti
        String regexEmail = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        
        //regex per i campi "numerici" = numero civico
        //controlla il campo dall'inizio alla fine permettendo da 1 a 4 cifre
        String regexNum = "^\\d{1,4}$";

        //validazione dei campi
        if (!nome.matches(regexLett) || !cognome.matches(regexLett) ||
            !nazione.matches(regexLett) || !regione.matches(regexLett) ||
            !provincia.matches(regexLett) || !comune.matches(regexLett) ||
            !via.matches(regexLett)) {

            request.setAttribute("errore", "Campi letterali non validi!");
            request.getRequestDispatcher("/WEB-INF/views/modificaProfilo.jsp").forward(request, response);
            return;
        }

        if (!email.matches(regexEmail)) {
            request.setAttribute("errore", "Email non valida!");
            request.getRequestDispatcher("/WEB-INF/views/modificaProfilo.jsp").forward(request, response);
            return;
        }

        if (!numCiv.matches(regexNum)) {
            request.setAttribute("errore", "Numero civico non valido!");
            request.getRequestDispatcher("/WEB-INF/views/modificaProfilo.jsp").forward(request, response);
            return;
        }

        //aggiornamento bean
        u.setNome(nome);
        u.setCognome(cognome);
        u.setUsername(username);
        u.setEmail(email);
        u.setBio(bio);
        u.setMetodoPagamento(metodoPagamento);
        u.setNazione(nazione);
        u.setRegione(regione);
        u.setProvincia(provincia);
        u.setComune(comune);
        u.setVia(via);
        u.setNumCiv(numCiv);

        //aggiungo al db
        UtenteDAO dao = new UtenteDAO();
        try {
            dao.doUpdate(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // aggiorno la sessione
        session.setAttribute("utente", u);

        session.setAttribute("successo", "Profilo aggiornato correttamente!");

        response.sendRedirect("PaginaUtente");
    }
}
