package model.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connessioneDB.ConnectionPool;
import model.bean.Acquisto;

public class AcquistoDAO {

	
	//metodo che restituisce tutti gli acquisti di un utente
	public List<Acquisto> doRetrieveByKey(int idCliente) throws SQLException {
		
		String sql = "SELECT * FROM acquisto WHERE id_acquirente = ?";
		
		List<Acquisto> acquisti = new ArrayList<>();
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, idCliente);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Acquisto a = new Acquisto();
				a.setIdAcquisto(rs.getInt("id_acquisto"));
				a.setIdAcquirente(rs.getInt("id_acquirente"));
				a.setIdVenditore(rs.getInt("id_venditore"));
				a.setIdLibro(rs.getInt("id_libro"));
				a.setPrezzo(rs.getDouble("prezzo_unitario"));
				a.setIva(rs.getDouble("iva_percentuale"));
				a.setQuantita(rs.getInt("quantita"));
				a.setData(rs.getTimestamp("data_acquisto").toLocalDateTime());
				a.setMetodoPagamento(rs.getString("metodo_pagamento_usato"));
				
				acquisti.add(a);
				
			}
			
			return acquisti;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
	//metodo che restituisce tutti gli acquisti registrati
	public List<Acquisto> doRetrieveAll() throws SQLException {
		
		String sql = "SELECT * FROM acquisto";
		
		List<Acquisto> acquisti = new ArrayList<>();
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Acquisto a = new Acquisto();
				a.setIdAcquisto(rs.getInt("id_acquisto"));
				a.setIdAcquirente(rs.getInt("id_acquirente"));
				a.setIdVenditore(rs.getInt("id_venditore"));
				a.setIdLibro(rs.getInt("id_libro"));
				a.setPrezzo(rs.getDouble("prezzo_unitario"));
				a.setIva(rs.getDouble("iva_percentuale"));
				a.setQuantita(rs.getInt("quantita"));
				a.setData(rs.getTimestamp("data_acquisto").toLocalDateTime());
				a.setMetodoPagamento(rs.getString("metodo_pagamento_usato"));
				
				acquisti.add(a);
				
			}
			
			return acquisti;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//metodo per restituire tutti gli acquisti fatti in uno specifico range di date
	public List<Acquisto> doRetrieveByDate(Date data_inizio, Date data_fine) throws SQLException{
		
		String sql = "SELECT * FROM acquisto WHERE data BETWEEN ? AND ?";
		List<Acquisto> acquisti = new ArrayList<>();
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
		ps.setDate(1, data_inizio);
		ps.setDate(2, data_fine);
		
		ResultSet rs = ps.executeQuery();
			
		while(rs.next()) {
			Acquisto a = new Acquisto();
			a.setIdAcquisto(rs.getInt("id_acquisto"));
			a.setIdAcquirente(rs.getInt("id_acquirente"));
			a.setIdVenditore(rs.getInt("id_venditore"));
			a.setIdLibro(rs.getInt("id_libro"));
			a.setPrezzo(rs.getDouble("prezzo_unitario"));
			a.setIva(rs.getDouble("iva_percentuale"));
			a.setQuantita(rs.getInt("quantita"));
			a.setData(rs.getTimestamp("data_acquisto").toLocalDateTime());
			a.setMetodoPagamento(rs.getString("metodo_pagamento_usato"));
			
			acquisti.add(a);
			
		}
		
		return acquisti;	
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	
	
	//metodo per aggiungere un nuovo acquisto
	public void doSave(Acquisto acquisto) throws SQLException {
		
		String sql = "INSERT INTO acquisto(id_acquirente, id_libro, id_venditore, prezzo_unitario, iva_percentuale, quantita, data_acquisto, metodo_pagamento_usato)" +
					 "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			
			ps.setInt(1, acquisto.getIdAcquirente());
			ps.setInt(2, acquisto.getIdLibro());
			ps.setInt(3, acquisto.getIdVenditore());
			ps.setDouble(4, acquisto.getPrezzo());
			ps.setDouble(5, acquisto.getIva());
			ps.setInt(6, acquisto.getQuantita());
			ps.setString(7, acquisto.getMetodoPagamento());
			
			ps.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	//metodo per eliminare un acquisto
	public void doDelete(int idAcquisto) throws SQLException {
		
		String sql = "DELETE FROM acquisto WHERE id_acquisto = ?";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, idAcquisto);
			ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}


