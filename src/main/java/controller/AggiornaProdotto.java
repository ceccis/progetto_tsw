package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet("/AggiornaProdotto")
@MultipartConfig
public class AggiornaProdotto extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

        String titolo = request.getParameter("titolo");
        String autore = request.getParameter("autore");
        String descrizione = request.getParameter("descrizione");
        String prezzoString = request.getParameter("prezzo");
        Part file = request.getPart("foto");
        double prezzo = Double.parseDouble(prezzoString);
        
        
        //coverto il file della foto in bytes perche' la foto nel db e' salvata come blob
	    InputStream inputStream = file.getInputStream();
	    byte[] fotoBytes = inputStream.readAllBytes();
	    
        
        //regex per i campi "letterali"
        //controlla il campo dall'inizio alla fine e permette solo lettere (accentate e non) e spazi bianchi
        String regexLett = "^[a-zA-ZÀ-ÿ\\s]{2,50}$";
        
        //regex per la descrizione del libro, controlla che sia almeno di 5 caratteri e massimo di 500 (con spazi)
        String regexDescrizione = "^.{5,500}$";

        if (!titolo.matches(regexLett) || !autore.matches(regexLett)) {
            request.setAttribute("errore", "Titolo o autore non validi");
            request.getRequestDispatcher("/WEB-INF/views/modificaProdotto.jsp").forward(request, response);
            return;
        }

        if (!descrizione.matches(regexDescrizione)) {
            request.setAttribute("errore", "Descrizione non valida");
            request.getRequestDispatcher("/WEB-INF/views/modificaProdotto.jsp").forward(request, response);
            return;
        }

        ProdottoDAO dao = new ProdottoDAO();
        Prodotto p = null;

        try {
            p = dao.doRetrieveByKey(idProdotto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //aggiorno i campi
        p.setTitolo(titolo);
        p.setAutore(autore);
        p.setDescrizione(descrizione);
        p.setPrezzo(prezzo);
        p.setFoto(fotoBytes);

        try {
            dao.doUpdate(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("successo", "Prodotto aggiornato correttamente!");

        response.sendRedirect("Vendite");
    }
}
