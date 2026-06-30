package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import model.bean.Acquisto;
import model.bean.Carrello;
import model.bean.Prodotto;
import model.bean.Utente;
import model.dao.AcquistoDAO;
import model.dao.CarrelloDAO;
import model.dao.ProdottoDAO;
import model.dao.UtenteDAO;

/**
 * Servlet implementation class FinalizzaOrdine
 */
@WebServlet("/FinalizzaOrdine")
public class FinalizzaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizzaOrdine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("utente")==null) {
			//redirect alla pagina di login se l'utente non e' autenticato
			response.sendRedirect("Login");
			return;
		}
		
		//recupero utente dalla sessione
		Utente u = (Utente) session.getAttribute("utente");
		int idUtente = u.getId();
		UtenteDAO daoU = new UtenteDAO();
		
		
		try {
			u = daoU.doRetrieveByKey(idUtente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//recupero metodo pagamento
		String metodoPagamento = u.getMetodoPagamento();
	
		//recupero il carrello dell'utente
		List<Carrello> listaLibri = new ArrayList<>();
		CarrelloDAO daoC = new CarrelloDAO();
		try {
			listaLibri = daoC.doRetrieveByKey(idUtente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//ciclo per creare un acquisto per ogni libro nel carrello
		for(int i=0; i<listaLibri.size(); i++) {
			
			Acquisto acquisto = new Acquisto();
			AcquistoDAO daoA = new AcquistoDAO();
			
			//prendo ogni libro dal carrello
			Carrello c = listaLibri.get(i);
			int idLibro = c.getIdLibro();
		
			Prodotto libro = new Prodotto();
			ProdottoDAO daoL = new ProdottoDAO();
			try {
				libro = daoL.doRetrieveByKey(idLibro);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int idVenditore = libro.getIdVenditore();
			double prezzo = libro.getPrezzo();
			double iva = (22 * prezzo) / 100.0;
			
			try {
				//rendo il libro non piu disponibile perche' e' stato acquistato
				daoL.disattivaLibro(idLibro);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO AGGIUNGERE PREZZO TOTALE A BEAN ACQUISTO E DATABASE
		
			// double prezzoTotale = prezzo + iva
		
			acquisto.setIdAcquirente(idUtente);
			acquisto.setIdLibro(idLibro);
			acquisto.setIdVenditore(idVenditore);
			acquisto.setPrezzo(prezzo);
			acquisto.setIva(iva);
			acquisto.setQuantita(1);
			acquisto.setMetodoPagamento(metodoPagamento);
			//acquisto.setPrezzoTotale(prezzoTotale);
		
			try {
				daoA.doSave(acquisto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		//svuoto il carrello
		daoC.svuotaCarrello(idUtente);
		
		request.setAttribute("successo", "Ordine completato! puoi viusalizzarlo nella sezione \"I miei ordini\" del tuo profilo");
		request.getRequestDispatcher("/WEB-INF/views/porfilo.jsp").forward(request, response);

	}
	
}
