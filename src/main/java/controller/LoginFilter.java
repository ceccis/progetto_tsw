package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        // pagine pubbliche (devono essere visualizzate anche se l'utente non e' loggato)
        if (path.equals(req.getContextPath()+ "/")|| 
        	path.contains("/Login") ||
            path.contains("/Registrazione") ||
            path.contains("/Catalogo") ||
            path.endsWith("index.jsp") ||
            path.contains("/css/") ||
            path.contains("/js/") ||
            path.contains("/img/")) {

            chain.doFilter(request, response);
            return;
        }

        // se l'utente non e' loggato in altre pagine viene fatto il redirect al login
        if (session == null || session.getAttribute("utente") == null) {
            res.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        // Utente loggato → continua
        chain.doFilter(request, response);
    }
}

