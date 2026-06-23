package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connessioneDB.ConnectionPool;
import model.bean.Prodotto;


//creo una classe ProdottoDAO che implementa tutti i metodi dell'interfaccia DAO
public class ProdottoDAO implements InterfacciaDAO<Prodotto, Integer>{

//@Override serve a riscrivere dei metodi ereditati da un'altra classe
//ad esempio in questo caso: ProdottoDAO implementa InterfacciaDAO e i suoi rispettivi metodi, solo che, ad esempio, per il metodo doRetrieveByKey:
//in ProdottoDAO al posto di avere come parametri dei tipi generici ci sono degli Integers o dei Prodotti e quindi si differenzia dal metodo doRetrieveByKey di InterfacciaDAO
//quindi bisogna usare Override
	
	
	
	
//metodo per recuperare un prodotto dato un determinato id
	@Override
	public Prodotto doRetrieveByKey(Integer id) throws SQLException {
		
		//query per prendere un prodotto dato un determinato id
		String sql = "SELECT * FROM libro WHERE id_libro = ?";
		
		
		//prendo una connessione dalla connection pool e preparp la query usando PreparedStatement per prevenire SQL Injection. uso il try-with-resources per chiudere automaticamente la connessione e l'oggetto PreparedStatement
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
		
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				//creo un JavaBean prodotto e ci metto dentro gli attributi del prodotto con id x
				Prodotto p = new Prodotto();
				p.setId(rs.getInt("id_libro"));
	            p.setTitolo(rs.getString("nome"));
	            p.setPrezzo(rs.getDouble("prezzo"));
	            p.setFoto(rs.getBytes("foto"));
	            p.setISBN(rs.getString("ISBN"));
	            p.setAutore(rs.getString("autore"));
	            p.setGenere(rs.getString("genere"));
	            p.setDescrizione(rs.getString("descrizione"));
	            p.setData(rs.getDate("data_pubblicazione").toLocalDate());
	            p.setIdVenditore(rs.getInt("id_venditore"));
	            return p;
	            
			}
			
			//se questo prodotto non esiste, restituisco null
			return null;
			
			// per gestire una Exception
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
            
        }
		
	}
	
	
//metodo per recuperare tutti i prodotti presenti
	@Override
	public List<Prodotto> doRetrieveAll() throws SQLException {
		
		String sql= "SELECT * FROM libro";
		//creo una lista di prodotti
		List<Prodotto> prodotti = new ArrayList<>();
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
	            Prodotto p = new Prodotto();
	            
	            p.setId(rs.getInt("id_libro"));
	            p.setTitolo(rs.getString("nome"));
	            p.setPrezzo(rs.getDouble("prezzo"));
	            p.setFoto(rs.getBytes("foto"));
	            p.setISBN(rs.getString("ISBN"));
	            p.setAutore(rs.getString("autore"));
	            p.setGenere(rs.getString("genere"));
	            p.setDescrizione(rs.getString("descrizione"));
	            p.setData(rs.getDate("data_pubblicazione").toLocalDate());
	            p.setIdVenditore(rs.getInt("id_venditore"));
	            
	            //metto ogni record che trovo nella lista di prodotti
	            prodotti.add(p);
	        }
			
			//restituisco la lista che sara' vuota nel caso in cui non esitano prodotti
			return prodotti;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	
	//metodo per aggiungere un prodotto
	@Override
	public void doSave(Prodotto p) throws SQLException {
		
		String sql = "INSERT INTO libro(nome, prezzo, foto, ISBN, autore, genere, descrizione, data_pubblicazione, id_venditore)"
				     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			
			ps.setString(1, p.getTitolo());
			ps.setDouble(2, p.getPrezzo());
			ps.setBytes(3, p.getFoto());
			ps.setString(4, p.getISBN());
			ps.setString(5, p.getAutore());
			ps.setString(6, p.getGenere());
			ps.setString(7, p.getDescrizione());
			ps.setDate(8, java.sql.Date.valueOf(p.getData()));
			ps.setInt(9, p.getIdVenditore());
			
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	//metodo per modificare un prodotto
	@Override
	public void doUpdate(Prodotto p) throws SQLException {
		String sql = "UPDATE libro SET nome = ?, prezzo = ?, foto = ?, ISBN = ?, autore = ?, genere = ?, descrizione = ?, data_pubblicazione = ?, id_venditore = ? WHERE id_libro = ? ";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, p.getTitolo());
			ps.setDouble(2, p.getPrezzo());
			ps.setBytes(3, p.getFoto());
			ps.setString(4, p.getISBN());
			ps.setString(5, p.getAutore());
			ps.setString(6, p.getGenere());
			ps.setString(7, p.getDescrizione());
			ps.setDate(8, java.sql.Date.valueOf(p.getData()));
			ps.setInt(9, p.getIdVenditore());
			ps.setInt(10, p.getId());
			
			ps.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	//metodo per eliminare un prodotto
	@Override
	public void doDelete(Integer id) throws SQLException {
		
		String sql = "DELETE FROM libro WHERE id_libro = ?";
		
		try(Connection con  = ConnectionPool.getConnection();
			PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		
	} catch (Exception e) {
		e.printStackTrace();
	}

}
	
}
