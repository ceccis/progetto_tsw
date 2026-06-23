package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connessioneDB.ConnectionPool;
import model.bean.Utente;


public class UtenteDAO implements InterfacciaDAO<Utente, Integer>{

	//metodo per recuperare un utente dato un determinato id
	@Override
	public Utente doRetrieveByKey(Integer id) throws SQLException {
		
		
		String sql = "SELECT * FROM utenteRegistrato WHERE id_utente = ?";
		
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Utente u = new Utente();
				u.setId(rs.getInt("id_utente"));
				u.setNome(rs.getString("nome"));
				u.setRuolo(rs.getString("ruolo"));
				u.setCognome(rs.getString("cognome"));
				u.setUsername(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				u.setBio(rs.getString("bio"));
				return u;
			}
			
			
			
			
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
	//metodo per recuperare tutti gli utenti presenti
	@Override
	public List<Utente> doRetrieveAll() throws SQLException {
		String sql= "SELECT * FROM utenteRegistrato";
		
		//creo una lista di utenti	
		List<Utente> utenti = new ArrayList<>();
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
	            Utente u = new Utente();
	            
	            u.setId(rs.getInt("id_utente"));
				u.setNome(rs.getString("nome"));
				u.setRuolo(rs.getString("ruolo"));
				u.setCognome(rs.getString("cognome"));
				u.setUsername(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				u.setBio(rs.getString("bio"));
	            
	            //metto ogni record che trovo nella lista di utenti
	            utenti.add(u);
	        }
			
			//restituisco la lista che sara' vuota nel caso in cui non esitano utenti
			return utenti;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}


	//metodo per aggiungere un utente
	@Override
	public void doSave(Utente u) throws SQLException {
		
		String sql = "INSERT INTO utenteRegistrato(nome, ruolo, cognome, username, email, bio, password_hash, nazione, regione, provincia, comune, via, numCiv)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, u.getNome());
			ps.setString(2, u.getRuolo());
			ps.setString(3, u.getCognome());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getBio());
			ps.setString(7, u.getHash());
			ps.setString(8, u.getNazione());
			ps.setString(9, u.getRegione());
			ps.setString(10, u.getProvincia());
			ps.setString(11, u.getComune());
			ps.setString(12, u.getVia());
			ps.setString(13, u.getNumCiv());
			
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	//metodo per modificare un utente
	@Override
	public void doUpdate(Utente u) throws SQLException {
		
		String sql = "UPDATE utenteRegistrato SET nome = ?, ruolo = ?, cognome = ?, username = ?, email = ?, bio = ?, password_hash = ?, naizone = ?, regione = ?, provincia = ?, comune = ?, via = ?, numCiv = ? WHERE id_utente = ? ";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, u.getNome());
			ps.setString(2, u.getRuolo());
			ps.setString(3, u.getCognome());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getBio());
			ps.setString(7, u.getHash());
			ps.setString(8, u.getNazione());
			ps.setString(9, u.getRegione());
			ps.setString(10, u.getProvincia());
			ps.setString(11, u.getComune());
			ps.setString(12, u.getVia());
			ps.setString(13, u.getNumCiv());
			ps.setInt(14, u.getId());
			
			ps.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	//metodo per eliminare un utente
	@Override
	public void doDelete(Integer id) throws SQLException {
		String sql = "DELETE FROM utenteRegistrato WHERE id_utente = ?";
		
		try(Connection con  = ConnectionPool.getConnection();
			PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		
	} catch (Exception e) {
		e.printStackTrace();
	}

}

}
