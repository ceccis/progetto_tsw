package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connessioneDB.ConnectionPool;
import model.bean.Carrello;


public class CarrelloDAO {

//metodo per recuperare il carrello di un utente
	public List<Carrello> doRetrieveByKey(int idUtente) throws SQLException {

		String sql = "SELECT * FROM carrello WHERE id_utente = ?";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
		
		List<Carrello> lista = new ArrayList<>();
			
		ps.setInt(1, idUtente);
		ResultSet rs = ps.executeQuery();
			
		while(rs.next()) {
			Carrello c = new Carrello();
			c.setIdUtente(rs.getInt("id_utente"));
			c.setIdLibro(rs.getInt("id_libro"));
			lista.add(c);
		}
			
			
		return lista;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
//metodo per inserire un prodotto nel carrello	
	public void aggiungiAlCarrello(Carrello c, int idUtente) throws SQLException {

		String sql = "INSERT INTO carrello(id_utente, id_libro) VALUES(?,?) ";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			//l'id dell'utente lo prendiamo dalla sessione nella servlet
			ps.setInt(1, idUtente);
			ps.setInt(2, c.getIdLibro());
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


//metodo per eliminare un prodotto dal carrello	
	public void togliProdottoCarrello(int idUtente, int idLibro) throws SQLException {


		String sql = "DELETE FROM carrello WHERE id_utente = ? AND id_libro = ?";
		
		try(Connection con = ConnectionPool.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setInt(1, idUtente);
				ps.setInt(2, idLibro);
				ps.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	
//metodo per svuotare il carrello	
	public void svuotaCarrello(int idUtente) {
		
		String sql = "DELETE FROM carrello WHERE id_utente = ?";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
		
			ps.setInt(1, idUtente);
			ps.executeUpdate();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
