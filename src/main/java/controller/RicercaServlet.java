package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class RicercaServlet
 */
@WebServlet("/RicercaServlet")
public class RicercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicercaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (query == null || query.trim().isEmpty()) {
			out.print("<p>Nessuna ricerca effettuata</p>");
			return;
		}
		
		ProdottoDAO dao = new ProdottoDAO();
		List <Prodotto> listaRisultati = null;
		
		try {
			listaRisultati = dao.RicercaLibro(query);
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("<p>Errore durante la ricerca</p>");
			return;
		}
		
		if (listaRisultati.isEmpty()) {
			out.print("<p>La ricerca non ha prodotto risultati.</p>");
	    } else {
	    	String contextPath = request.getContextPath();
	    	
	        out.print("<ul class=\"lista-suggerimenti\">");       //controllare css
	        for (Prodotto p : listaRisultati) {
	        	out.print("<li onclick=\"window.location.href='" + contextPath + "/DettaglioProdotto?idLibro=" + p.getId() + "'\">");
	            out.print(p.getTitolo() + " - " + p.getPrezzo() + " &euro;");
	            out.print("</li>");
	        }
	        out.print("</ul>");
	    }
	   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
