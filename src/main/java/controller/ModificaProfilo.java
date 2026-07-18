package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Utente;



@WebServlet("/ModificaProfilo")
public class ModificaProfilo extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

    	//tolto il controllo per vedere se l'utente e' loggato perche' poi faro' un filtro che gestisce questa cosa
		

        Utente u = (Utente) session.getAttribute("utente");

        // passo l’utente alla JSP
        request.setAttribute("utente", u);

        request.getRequestDispatcher("/WEB-INF/views/modificaProfilo.jsp").forward(request, response);
    }
}
