package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import model.bean.Utente;
import model.dao.UtenteDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // recupero i parametri dal form HTML
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
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
 
        //regex per il campo "password" = controlla che la password sia almeno di 8 caratteri, con almeno una lettera maiuscola e una minuscola, un numero e un carattere speciale fra @$!%*?&
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        
        //regex per "email" = controlla che la stringa contenga esattamente una chiocciola e un punto escludendo spazi vuoti
        String regexEmail = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        
        //regex per i campi "numerici" = numero civico
        //controlla il campo dall'inizio alla fine permettendo da 1 a 4 cifre
        String regexNum = "^\\d{1,4}$";
        
       
        
        //controllo che nessun campo sia vuoto
        if ( isEmpty(nome) || isEmpty(email) || isEmpty(cognome) || isEmpty(password) || isEmpty(username) 
           || isEmpty(bio) || isEmpty(nazione) || isEmpty(regione) || isEmpty(provincia) || isEmpty(comune)
           || isEmpty(via) || isEmpty(numCiv)) {
        	
        	request.setAttribute("errore", "Compila tutti i campi");
        	request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
        	return;
            
        	} else {
        		UtenteDAO dao = new UtenteDAO();
            	Utente u = new Utente();
            	
            	if (!nome.matches(regexLett) || !cognome.matches(regexLett)|| !nazione.matches(regexLett) 
            		|| !regione.matches(regexLett)|| !provincia.matches(regexLett) 
            		|| !comune.matches(regexLett) || !via.matches(regexLett)) {
            	    request.setAttribute("errore", "Inserire solo lettere nei campi: nome, cognome, nazione, regione, provincia, comune e via");
            	    request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            	    return;
            	} 
            	
            	if (!password.matches(regexPassword)) {
            	    request.setAttribute("errore", "Password non valida: inserire almeno 8 caratteri, una lettera minuscola, una maiuscola e un carattele speciale fra @ $ ! % * ? & ");
            	    request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            	    return;
            	}
            	
            	if (!numCiv.matches(regexNum)) {
            	    request.setAttribute("errore", "Inserire da 1 a 4 cifre nel campo numero civico ");
            	    request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            	    return;
            	}
            	
            	if (!email.matches(regexEmail)) {
            	    request.setAttribute("errore", "Email non valida: inserire la chiocciola e un punto, senza spazi vuoti");
            	    request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request, response);
            	    return;
            	}
            	
            	
            	u.setNome(nome);
            	u.setEmail(email);
            	String hash = toHash(password);
            	u.setHash(hash);
            	u.setCognome(cognome);
            	u.setUsername(username);
            	u.setBio(bio);
            	u.setMetodoPagamento(metodoPagamento);
            	u.setNazione(nazione);
            	u.setRegione(regione);
            	u.setProvincia(provincia);
            	u.setComune(comune);
            	u.setVia(via);
            	u.setNumCiv(numCiv);
            	u.setRuolo("utente");

            	
            	try {
    				dao.doSave(u);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
            	
            	HttpSession session = request.getSession();
            	session.setAttribute("successo", "Registrazione completata!");
            	response.sendRedirect("Login");

        	
        	}
    	}
	}


