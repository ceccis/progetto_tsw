package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Carrello;
import model.bean.Utente;
import model.dao.CarrelloDAO;

/**
 * Servlet implementation class AggiungiCarrello
 */
@WebServlet("/AggiungiCarrello")
public class AggiungiCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("Login");
            return;
        }
        //recupero id dell'utente e del libro
        Utente u = (Utente) session.getAttribute("utente");
        int idUtente = u.getId();
        int idLibro = Integer.parseInt(request.getParameter("idLibro"));

        CarrelloDAO dao = new CarrelloDAO();

        try {
            dao.aggiungiAlCarrello(idLibro, idUtente);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //recupero il carrello aggiornato
        List<Carrello> carrello = null;

        try {
            carrello = dao.doRetrieveByKey(idUtente);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("carrello", carrello);
        request.getRequestDispatcher("/WEB-INF/views/carrello.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
