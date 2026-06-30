package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import model.bean.Carrello;
import model.bean.Prodotto;
import model.bean.Utente;
import model.dao.CarrelloDAO;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				HttpSession session = request.getSession(false);

		        if (session == null || session.getAttribute("utente") == null) {
		            response.sendRedirect("Login");
		            return;
		        }

		        Utente u = (Utente) session.getAttribute("utente");
		        int idUtente = u.getId();

		        //recupero carrello
		        CarrelloDAO daoC = new CarrelloDAO();
		        List<Carrello> listaCarrello = null;

		        try {
		            listaCarrello = daoC.doRetrieveByKey(idUtente);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        
				if(listaCarrello.isEmpty()) {

					request.setAttribute("errore", "Carrello vuoto!");
					request.getRequestDispatcher("/WEB-INF/views/carrello.jsp").forward(request, response);
				    return;
				}

		        //recupero prodotti del carrello
		        List<Prodotto> prodotti = new ArrayList<>();
		        ProdottoDAO daoP = new ProdottoDAO();

		        double totale = 0;

		        for (Carrello c : listaCarrello) {
		            try {
		                Prodotto p = daoP.doRetrieveByKey(c.getIdLibro());
		                prodotti.add(p);
		                totale += p.getPrezzo();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }

		        //passo tutto alla JSP
		        request.setAttribute("utente", u);
		        request.setAttribute("prodotti", prodotti);
		        request.setAttribute("totale", totale);

		        request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
		    }
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
