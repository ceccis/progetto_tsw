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
@WebServlet("/AggiungiVendita")
@MultipartConfig
public class AggiungiVendita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiVendita() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		//leggo il file della foto
		Part file = request.getPart("foto");
		String ISBN = request.getParameter("ISBN");
	    String autore = request.getParameter("autore");
	    String genere = request.getParameter("genere");
	    String descrizione = request.getParameter("descrizione");
	    int idVenditore = u.getId();	 
	    
	    
	    //coverto il file della foto in bytes perche' la foto nel db e' salvata come blob
	    InputStream inputStream = file.getInputStream();
	    byte[] fotoBytes = inputStream.readAllBytes();
	    
	    
	
	    Prodotto p = new Prodotto();
	    p.setTitolo(titolo);
	    p.setPrezzo(prezzo);
	    p.setFoto(fotoBytes);
	    p.setISBN(ISBN);
	    p.setAutore(autore);
	    p.setGenere(genere);
	    p.setDescrizione(descrizione);
	    p.setIdVenditore(idVenditore);
	       
	   
	    
	    ProdottoDAO dao = new ProdottoDAO();
	    try {
	        dao.doSave(p);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    request.setAttribute("successo", "Vendita aggiunta correttamente! Puoi trovare la tua vendita nella sezione \"Le mie vendite\" del tuo profilo");
		request.getRequestDispatcher("/WEB-INF/views/profilo.jsp").forward(request, response);
	    
	    
	    
	}

}
