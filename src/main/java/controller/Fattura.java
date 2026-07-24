package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fattura
 */
@WebServlet("/Fattura")
public class Fattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fattura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idAcquisto = request.getParameter("idAcquisto");
		String idLibro = request.getParameter("idLibro");
		String data = request.getParameter("data");
		String prezzoStr = request.getParameter("prezzo");		/*dichiarazione doppia*/
		String ivaStr = request.getParameter("iva");
		
		double prezzo = Double.parseDouble(prezzoStr);
		double iva = Double.parseDouble(ivaStr);
		double totale = prezzo + iva;
		
		request.setAttribute("idAcquisto", idAcquisto);
		request.setAttribute("idLibro", idLibro);
		request.setAttribute("data", data);
		request.setAttribute("prezzo", prezzo);
		request.setAttribute("iva", iva);
		request.setAttribute("totale", totale);
		
		request.getRequestDispatcher("/WEB-INF/views/fattura.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
