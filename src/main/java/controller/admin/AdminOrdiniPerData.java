package controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class AdminOrdiniPerData
 */
@WebServlet("/AdminOrdiniPerData")
public class AdminOrdiniPerData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrdiniPerData() {
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
			//redirect alla pagina di login se l'admin non e' autenticato
			response.sendRedirect("Login");
			return;
		}
		//recupero le date dal form: le recupero prima come stringhe e poi la trasformo in data, utilizzando un formatter con il formato standard per le date di HTML YYYY-MM-DD
		
		String data1String = request.getParameter("data1");
		String data2String = request.getParameter("data2");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date data1 = null;
		java.sql.Date data2 = null;
		
		try {
			
			java.util.Date uDate1 = formatter.parse(data1String);
			java.util.Date uDate2 = formatter.parse(data2String);
			
			data1 = new java.sql.Date(uDate1.getTime());
			data2 =  new java.sql.Date(uDate2.getTime());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AcquistoDAO dao = new AcquistoDAO();
		List<Acquisto> listaAcquisti = null;
		
		try {
			listaAcquisti = dao.doRetrieveByDate(data1, data2);

		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errore", "Errore nel degli acquisti");
		}
		
		
		request.setAttribute("acquisti", listaAcquisti);
		request.getRequestDispatcher("/WEB-INF/views/admin/adminOrdiniPerData.jsp").forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
