package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AcquistoDAO;
import model.dao.ProdottoDAO;
import model.dao.UtenteDAO;
import model.bean.Acquisto;
import model.bean.Prodotto;
import model.bean.Utente;

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
		
		String idAcquistoS = request.getParameter("idAcquisto");
		int idAcquisto = Integer.parseInt(idAcquistoS);
		AcquistoDAO adao = new AcquistoDAO();
		Acquisto acquisto = null;
		try {
			acquisto = adao.doRetrieveByKey(idAcquisto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//prendo i dati del cliente
		UtenteDAO cdao = new UtenteDAO();
		Utente c = null;
		
		try {
			c = cdao.doRetrieveByKey(acquisto.getIdAcquirente());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//prendo il libro
		ProdottoDAO pdao = new ProdottoDAO();
		Prodotto l = null;
		
		try {
			l = pdao.doRetrieveByKey(acquisto.getIdLibro());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("acquisto", acquisto);
		request.setAttribute("cliente", c);
		request.setAttribute("libro", l);
		request.setAttribute("indirizzoAcquirente", c.getNazione() + " " + c.getRegione() + " " + c.getProvincia()+ " " + c.getComune()+ " " + c.getVia()+ " " + c.getNumCiv());
		
		
		
		
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
