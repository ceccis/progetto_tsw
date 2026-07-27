package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AggiungiVendita
 */
@WebServlet("/AggiungiVendita")
public class AggiungiVendita extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		//tolto il controllo per vedere se l'utente e' loggato perche' poi faro' un filtro che gestisce questa cosa

        request.getRequestDispatcher("/WEB-INF/views/aggiungiVendita.jsp")
               .forward(request, response);
    }
}

