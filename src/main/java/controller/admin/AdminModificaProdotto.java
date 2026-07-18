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



@WebServlet("/Admin/AdminModificaProdotto")
public class AdminModificaProdotto extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public AdminModificaProdotto() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		//tolto il controllo per vedere se l'utente e' loggato perche' poi faro' un filtro che gestisce questa cosa
		

      
        int idLibro = Integer.parseInt(request.getParameter("idLibro"));
        
        ProdottoDAO  dao = new ProdottoDAO();
        Prodotto libro = null;
		try {
			libro = dao.doRetrieveByKey(idLibro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

        // passo il,libro alla JSP
        request.setAttribute("libro", libro);

        request.getRequestDispatcher("/WEB-INF/views/admin/adminModificaProdotto.jsp").forward(request,  response);
    }
}
