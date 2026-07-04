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
import model.bean.Acquisto;
import model.bean.Utente;
import model.dao.AcquistoDAO;


/**
 * Servlet implementation class Acquisti
 */
@WebServlet("/AdminVisualizzaOrdini")
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
		
		HttpSession session = request.getSession(false);
		Utente u = (Utente) session.getAttribute("utente");
		if(session == null || u==null || !u.getRuolo().equals("admin")) {
			//redirect alla pagina di login se admin non e' autenticato
			response.sendRedirect("Login");
			return;
		}
       
		AcquistoDAO dao = new AcquistoDAO();
		List<Acquisto> listaOrdini = null;
		try {
			
			listaOrdini = dao.doRetrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nel caricamento degli acquisti");
		}
		request.setAttribute("ordini", listaOrdini);
		request.getRequestDispatcher("/WEB-INF/views/admin/ordiniAdmin.jsp").forward(request,  response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
