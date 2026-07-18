package controller.admin;

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

@WebServlet("/Admin/AdminAggiornaProdotto")
@MultipartConfig
public class AdminAggiornaProdotto extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        //recupero parametri
        
        int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

        String titolo = request.getParameter("titolo");
        String autore = request.getParameter("autore");
        String genere = request.getParameter("genere");
        String descrizione = request.getParameter("descrizione");
        String prezzoString = request.getParameter("prezzo");
        String ISBN = request.getParameter("ISBN");
        Part file = request.getPart("foto");

        double prezzo = Double.parseDouble(prezzoString);

       
        //regex per i campi "letterali"
        //controlla il campo dall'inizio alla fine e permette solo lettere (accentate e non) e spazi bianchi
        //String regexLett = "^[a-zA-ZÀ-ÿ\\s]{2,50}$";
        
        //regex per la descrizione del libro, controlla che sia almeno di 5 caratteri e massimo di 500 (con spazi)
        String regexDescrizione = "^.{5,500}$";
	
        //regex per i campi decimali = prezzo
        //controlla il campo dall'inizio alla fine permettendo massimo 8 cifre, un punto e 2 cifre dopo il punto
        String regexNumD = "^[0-9]{1,8}(\\.[0-9]{1,2})$";
        
        //regex per ISBN = accetta solo una stringa lunga 10 o 13 cifre
        String regexISBN = "^(\\d{10}|\\d{13})$";
        		
        
        //validazione
        
        /*if (!titolo.matches(regexLett) || !autore.matches(regexLett) || !genere.matches(regexLett)) {
            request.setAttribute("errore", "Titolo, autore o genere non validi, sono permesse lettere (accentate o non), virgole, punti, apostrofi, trattini e numeri (da 5 a max 50 caratteri).");
            request.getRequestDispatcher("/WEB-INF/views/admin/adminModificaProdotto.jsp").forward(request,  response);
            return;
        }*/

        if (!descrizione.matches(regexDescrizione)) {
            request.setAttribute("errore", "Descrizione non valida (min 5, max 500 caratteri).");
            request.getRequestDispatcher("/WEB-INF/views/admin/adminModificaProdotto.jsp").forward(request,  response);
            return;
        }

        if (!prezzoString.matches(regexNumD)) {
            request.setAttribute("errore", "Prezzo non valido. Usa il formato: 12.50 ");
            request.getRequestDispatcher("/WEB-INF/views/admin/adminModificaProdotto.jsp").forward(request,  response);
            return;
        }
        
        if(!ISBN.matches(regexISBN)){
			request.setAttribute("errore", "Inserire o 10 o 13 numeri interi per l'ISBN ");
			 request.getRequestDispatcher("/WEB-INF/views/admin/adminModificaProdotto.jsp").forward(request,  response);
    	    return;
		}
        
        //recupero prodotto dal DB
        
        ProdottoDAO dao = new ProdottoDAO();
        Prodotto p = null;

        try {
            p = dao.doRetrieveByKey(idProdotto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (p == null) {
            request.setAttribute("errore", "Prodotto non trovato.");
            request.getRequestDispatcher("/WEB-INF/views/admin/adminModificaProdotto.jsp").forward(request,  response);
            return;
        }

        
        //aggiornamento campi
        
        p.setTitolo(titolo);
        p.setAutore(autore);
        p.setGenere(genere);
        p.setDescrizione(descrizione);
        p.setPrezzo(prezzo);
        p.setISBN(ISBN);

        
        // aggiornamento foto (solo se caricata)
        
        if (file != null && file.getSize() > 0) {
            InputStream inputStream = file.getInputStream();
            byte[] fotoBytes = inputStream.readAllBytes();
            p.setFoto(fotoBytes);
        }

        try {
            dao.doUpdate(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }

     
        request.getSession().setAttribute("successo", "Prodotto aggiornato correttamente!");
        response.sendRedirect(request.getContextPath() + "/Admin/AdminVisualizzaCatalogo");
    }
}
