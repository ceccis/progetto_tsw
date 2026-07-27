package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class AdminVisualizzaCatalogo
 */

//cambiato annotazione mettendo Admin prima cosi' il filtro sa se questa servlet e' protetta
@WebServlet("/Admin/AdminVisualizzaCatalogo")
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
		
		//tolto il controllo per vedere se l'utente e' loggato e admin perche' poi faro' un filtro che gestisce questa cosa
		ProdottoDAO dao = new ProdottoDAO();
		List<Prodotto> listaProdotti = null;
		try {
			//MODIFICA CON METODO doRetrieveAllAvailable per mostrare solo i libri disponibili
			listaProdotti = dao.doRetrieveAllAvailable();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nel caricamento del catalogo");
		}
		request.setAttribute("prodotti", listaProdotti);
		request.getRequestDispatcher("/WEB-INF/views/admin_jsp/adminIndex.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
