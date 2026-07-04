package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Prodotto;
import model.bean.Utente;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class AdminVisualizzaCatalogo
 */
@WebServlet("/AdminVisualizzaCatalogo")
public class AdminVisualizzaCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminVisualizzaCatalogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Utente u = (Utente) session.getAttribute("utente");
		if(session == null || u==null || !u.getRuolo().equals("admin")) {
			//redirect alla pagina di login se l'admin non e' autenticato
			response.sendRedirect("Login");
			return;
		}
		
		ProdottoDAO dao = new ProdottoDAO();
		List<Prodotto> listaProdotti = null;
		try {
			
			listaProdotti = dao.doRetrieveAllAvailable();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nel caricamento del catalogo");
		}
		request.setAttribute("prodotti", listaProdotti);
		request.getRequestDispatcher("/WEB-INF/views/admin/catalogoAdmin.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
