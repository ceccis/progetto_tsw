package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import model.bean.Utente;
import model.dao.UtenteDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,  response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 //metodo per hashare la password
	private String toHash (String password){
		String hashString = null;
		
		try {
		java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		hashString = "";
				
		for(int i = 0; i<hash.length; i++) {
			hashString += Integer.toHexString(hash[i] & 0xFF | 0x100).substring(1,3);		
			}
				
		} catch (java.security.NoSuchAlgorithmException e) {
				System.out.println(e);
				
				}
			
		return hashString;
			
		}
	
	
	//metodo per controllare se un parametro e' null oppure vuoto
	private boolean isEmpty(String s) {
	    return s == null || s.trim().isEmpty();
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<String> errors = new ArrayList<>();
		
		if(isEmpty(username)|| isEmpty(password)) {
			errors.add("Compilare tutti i campi!");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,  response);
			return;
		}
		
		
		UtenteDAO dao = new UtenteDAO();
		String hash = toHash(password);
		Utente u = dao.doRetrieveByUsernameAndPsw(username, hash);
		
		if  (u != null) {
			HttpSession session = request.getSession();
			session.setAttribute("utente", u);
			
			if(u.getRuolo().equals("admin")) {
				request.getSession().setAttribute("isAdmin", Boolean.TRUE);
				response.sendRedirect("Admin");
				return;
				}
			
			session.setAttribute("successo", "Login effettuato con successo!");
			response.sendRedirect("index.jsp");
			return;	
			
		} else {
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request,  response);
			
		}
	}

}
