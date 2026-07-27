package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Acquisto;
import model.dao.AcquistoDAO;


/**
 * Servlet implementation class Acquisti
 */

//cambiato annotazione mettendo Admin prima cosi' il filtro sa se questa servlet e' protetta
@WebServlet("/Admin/AdminVisualizzaOrdini")
public class AdminVisualizzaOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminVisualizzaOrdini() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//tolto il controllo per vedere se l'utente e' loggato e admin perche' poi faro' un filtro che gestisce questa cosa
       
		AcquistoDAO dao = new AcquistoDAO();
		List<Acquisto> listaOrdini = null;
		try {
			
			listaOrdini = dao.doRetrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nel caricamento degli acquisti");
		}
		request.setAttribute("ordini", listaOrdini);
		request.getRequestDispatcher("/WEB-INF/views/admin_jsp/ordiniAdmin.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
