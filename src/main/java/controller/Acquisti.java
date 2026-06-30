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
import model.bean.Acquisto;
import model.bean.Utente;
import model.dao.AcquistoDAO;


/**
 * Servlet implementation class Acquisti
 */
@WebServlet("/Acquisti")
public class Acquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Acquisti() {
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
		
        Utente u = new Utente();
        u = (Utente) session.getAttribute("utente");
        int idUtente = u.getId();
		AcquistoDAO dao = new AcquistoDAO();
		List<Acquisto> listaAcquisti = null;
		try {
			
			listaAcquisti = dao.doRetrieveByKey(idUtente);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nel caricamento degli acquisti");
		}
		request.setAttribute("acquisti", listaAcquisti);
		request.getRequestDispatcher("/WEB-INF/views/acquisti.jsp").forward(request,  response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
