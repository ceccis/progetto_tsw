package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

	
	//metodo per hashare la password
		private String toHash (String password){
			String hashString = null;
			try {
				java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
				byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				hashString = "";
				
				for(int i = 0; i<hash.length; i++) {
					hashString += Integer.toHexString(hash[i] & 0xFF | 0x100).substring(1,3);		
					}
				
				} catch (java.security.NoSuchAlgorithmException e) {
				System.out.println(e);
				
				}
			
			return hashString;
			
		}

		
		
		//metodo per controllare se un parametro e' null oppure vuoto
		private boolean isEmpty(String s) {
		    return s == null || s.trim().isEmpty();
		}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

      //tolto il controllo per vedere se l'utente e' loggato perche' poi faro' un filtro che gestisce questa cosa

        Utente u = (Utente) session.getAttribute("utente");
        
        //recupero la password attuale dell'utente dal database, se l'utente non aggiorna la sua password lascio questa, altrimenti metto la password aggiornata
        UtenteDAO dao = new UtenteDAO();
        Utente dbUser = null;
		try {
			dbUser = dao.doRetrieveByKey(u.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String passwordAttuale = dbUser.getHash();


        // recupero parametri dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String nuovaPassword = request.getParameter("nuovaPassword");
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
        
        
        //regex per il campo "password" = controlla che la password sia almeno di 8 caratteri, con almeno una lettera maiuscola e una minuscola, un numero e un carattere speciale fra @$!%*?&
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        
        
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
        
        if (isEmpty(nuovaPassword)) {
        	//se l'utente non vuole cambiare la password non la inserisce
            // tieni quella già presente nel DB
        	dbUser.setHash(passwordAttuale);
            
            //se la inserisce controllo se rispetta la regex
        } else if (!nuovaPassword.matches(regexPassword)) {
        	 request.setAttribute("errore", "Password non valida!");
             request.getRequestDispatcher("/WEB-INF/views/modificaProfilo.jsp").forward(request, response);
             return;
             
             // hasho la password e la setto
        }else {
        	
        	String hash = toHash(nuovaPassword);
        	dbUser.setHash(hash);
        	
        }



        //aggiornamento bean
        dbUser.setNome(nome);
        dbUser.setCognome(cognome);
        dbUser.setUsername(username);
        dbUser.setEmail(email);
        dbUser.setBio(bio);
        dbUser.setMetodoPagamento(metodoPagamento);
        dbUser.setNazione(nazione);
        dbUser.setRegione(regione);
        dbUser.setProvincia(provincia);
        dbUser.setComune(comune);
        dbUser.setVia(via);
        dbUser.setNumCiv(numCiv);

       
        try {
            dao.doUpdate(dbUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // aggiorno la sessione
        session.setAttribute("utente", dbUser);

        session.setAttribute("successo", "Profilo aggiornato correttamente!");

        response.sendRedirect("PaginaUtente");
    }
}
