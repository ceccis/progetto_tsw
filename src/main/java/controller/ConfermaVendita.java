package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Prodotto;
import model.bean.Utente;

import model.dao.ProdottoDAO;

/**
 * Servlet implementation class AggiungiVendita
 */
@WebServlet("/ConfermaVendita")
@MultipartConfig
public class ConfermaVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaVendita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	//metodo per controllare se un parametro e' null oppure vuoto
		private boolean isEmpty(String s) {
		    return s == null || s.trim().isEmpty();
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("Login");
            return;
        }
        
         Utente u = (Utente) session.getAttribute("utente");
		
		String titolo = request.getParameter("titolo");
		String prezzoS = request.getParameter("prezzo");
		//leggo il file della foto
		Part file = request.getPart("foto");
		String ISBN = request.getParameter("ISBN");
	    String autore = request.getParameter("autore");
	    String genere = request.getParameter("genere");
	    String descrizione = request.getParameter("descrizione");
	    int idVenditore = u.getId();	 
	    
	    
	    ProdottoDAO dao = null;
		Prodotto p = null;
	    
	    //coverto il file della foto in bytes perche' la foto nel db e' salvata come blob
	    InputStream inputStream = file.getInputStream();
	    byte[] fotoBytes = inputStream.readAllBytes();
	    

        //regex per i campi "letterali"
        //controlla il campo dall'inizio alla fine e permette solo lettere (accentate e non), numeri, virgole, trattini, apostrofi, punti e spazi bianchi
        String regexLett = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9 ,.'-]{2,50}$";
        
        //regex per la descrizione del libro, controlla che sia almeno di 5 caratteri e massimo di 500 (con spazi)
        String regexDescrizione = "^.{5,500}$";
	
      //regex per i campi decimali = prezzo
      //controlla il campo dall'inizio alla fine permettendo massimo 8 cifre, un punto e 2 cifre dopo il punto
        String regexNumD = "^[0-9]{1,8}(\\.[0-9]{2})$";
        
        //regex per ISBN = accetta solo una stringa lunga 10 o 13 cifre
        String regexISBN = "^(\\d{10}|\\d{13})$";
        
      //controllo che nessun campo sia vuoto
        if ( isEmpty(titolo) || isEmpty(prezzoS)  || isEmpty(ISBN) || isEmpty(autore) 
           || isEmpty(genere) || isEmpty(descrizione)) {
        	
        	request.setAttribute("errore", "Compila tutti i campi");
        	request.getRequestDispatcher("/WEB-INF/views/aggiungiVendita.jsp").forward(request, response);
        	return;
            
        	} else {
   
        		 dao = new ProdottoDAO();
        		 p = new Prodotto();
        		
        		if(!titolo.matches(regexLett)||!autore.matches(regexLett)||!genere.matches(regexLett)) {
        			
        			request.setAttribute("errore", "Inserire solo da 2 a 50 lettere, numeri, trattini, virgole, apostrofi e spazi bianchi titolo, autore e genere");
            	    request.getRequestDispatcher("/WEB-INF/views/aggiungiVendita.jsp").forward(request, response);
            	    return;
        			
        		}
        		
        		if(!prezzoS.matches(regexNumD)) {
        			request.setAttribute("errore", "Inserire almeno 1 e massimo 8 cifre prima del punto ed esattamente due cifre dopo il punto per il prezzo ");
            	    request.getRequestDispatcher("/WEB-INF/views/aggiungiVendita.jsp").forward(request, response);
            	    return;
        		}
        		
        		if(!descrizione.matches(regexDescrizione)) {
        			request.setAttribute("errore", "Inserire almeno 5 e massimo 500 caratteri per la descrizione ");
            	    request.getRequestDispatcher("/WEB-INF/views/aggiungiVendita.jsp").forward(request, response);
        		}
        		
        		if(!ISBN.matches(regexISBN)){
        			request.setAttribute("errore", "Inserire o 10 o 13 numeri interi per l'ISBN ");
            	    request.getRequestDispatcher("/WEB-INF/views/aggiungiVendita.jsp").forward(request, response);
        		}
        		
        		double prezzo = Double.parseDouble(prezzoS);
        		p.setTitolo(titolo);
        	    p.setPrezzo(prezzo);
        	    p.setFoto(fotoBytes);
        	    p.setISBN(ISBN);
        	    p.setAutore(autore);
        	    p.setGenere(genere);
        	    p.setDescrizione(descrizione);
        	    p.setIdVenditore(idVenditore);		
        		
        	}

	    try {
	        dao.doSave(p);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    request.setAttribute("successo", "Vendita aggiunta correttamente! Puoi trovare la tua vendita nella sezione \"Le mie vendite\" del tuo profilo");
		request.getRequestDispatcher("/WEB-INF/views/profilo.jsp").forward(request, response);
	    
	    
	    
	}

}
