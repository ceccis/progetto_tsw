package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.bean.Prodotto;
import model.dao.ProdottoDAO;

/**
 * Servlet implementation class DettaglioProdotto
 */
@WebServlet("/Admin/AdminDettaglioProdotto")
public class AdminDettaglioProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDettaglioProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//tolto il controllo per vedere se l'utente e' loggato perche' poi faro' un filtro che gestisce questa cosa
		
		//recupero l'id del libro da un parametro hidden nel form
		int idLibro = Integer.parseInt(request.getParameter("idLibro"));
		
		ProdottoDAO dao = new ProdottoDAO();
		Prodotto libro = new Prodotto();
		
		try {
		  libro = dao.doRetrieveByKey(idLibro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("libro", libro);
		 request.getRequestDispatcher("/WEB-INF/views/admin/adminDettaglio.jsp").forward(request,  response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
