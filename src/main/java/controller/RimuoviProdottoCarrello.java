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
import model.dao.CarrelloDAO;

/**
 * Servlet implementation class RimuoviProdottoCarrello
 */
@WebServlet("/RimuoviProdottoCarrello")
public class RimuoviProdottoCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviProdottoCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);

		//tolto il controllo per vedere se l'utente e' loggato perche' poi faro' un filtro che gestisce questa cosa
			
	    Utente u = (Utente) session.getAttribute("utente");
	    
	    
		//recupero l'id del libro da un parametro hidden nel form
		int idLibro = Integer.parseInt(request.getParameter("idLibro"));
		//recupero l'id dell'utente dalla sessione
		int idUtente = u.getId();
				
		CarrelloDAO dao = new CarrelloDAO();
		try {
			dao.togliProdottoCarrello(idUtente, idLibro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("successo", "Prodotto rimosso dal carrello");
	    request.getRequestDispatcher("/VisualizzaCarrello").forward(request, response);
	    //TODO servlet Carrello

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
