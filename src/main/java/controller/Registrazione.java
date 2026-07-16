package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UtenteDAO;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/registrazione.jsp").forward(request,  response);;
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String email = request.getParameter("email");    //controllare quale altre cose servono per la registrazione
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		
		UtenteDAO utenteDao = new UtenteDAO();
				
		boolean emailPresente = utenteDao.checkEmail(email);   //controllare questo comando, sta nell'altro branch
		
		if (emailPresente == true) {
					
			HttpSession session = request.getSession();
			session.setAttribute("messaggioConferma", "Email già in uso");
			response.sendRedirect("Login");   //login e non direttamente la pagina acquisti perchè è registrato e non loggato
		
		} else {
			utenteDao.salvaUtente(email, password, nome);   //controllare il metodo
			HttpSession session = request.getSession();
			session.setAttribute("messaggioConferma", "La registrazione è avvenuta con successo, benvenuto sul sito!");
			response.sendRedirect("Login");
		}

}
