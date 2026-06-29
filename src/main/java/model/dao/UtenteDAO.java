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
				u.setMetodoPagamento(rs.getString("metodo_pagamento"));//aggiunta metodo pagamento
				u.setNazione(rs.getString("nazione"));
				u.setRegione(rs.getString("regione"));
				u.setProvincia(rs.getString("provincia"));
				u.setComune(rs.getString("comune"));
				u.setVia(rs.getString("via"));
				u.setNumCiv(rs.getString("numCiv"));
				return u;
			}
			
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	
//metodo per recuperare un utente tramite username e password (per login)
	public Utente doRetrieveByUsernameAndPsw(String usernameInserito, String pswHash) { 
		
		String sql = "SELECT id_utente, nome, cognome, ruolo, username, email, bio, metodo_pagamento, nazione, regione, provincia, comune, via, numCiv FROM utenteRegistrato WHERE username = ? AND password_hash = ?";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, usernameInserito);
			ps.setString(2, pswHash);
			
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
			u.setMetodoPagamento(rs.getString("metodo_pagamento"));
			u.setNazione(rs.getString("nazione"));
			u.setRegione(rs.getString("regione"));
			u.setProvincia(rs.getString("provincia"));
			u.setComune(rs.getString("comune"));
			u.setVia(rs.getString("via"));
			u.setNumCiv(rs.getString("numCiv"));
			
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
				u.setMetodoPagamento(rs.getString("metodo_pagamento"));
				u.setNazione(rs.getString("nazione"));
				u.setRegione(rs.getString("regione"));
				u.setProvincia(rs.getString("provincia"));
				u.setComune(rs.getString("comune"));
				u.setVia(rs.getString("via"));
				u.setNumCiv(rs.getString("numCiv"));
	            
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


//metodo per aggiungere un utente (registrazione)
	@Override
	public void doSave(Utente u) throws SQLException {
		
		String sql = "INSERT INTO utenteRegistrato(nome, ruolo, cognome, username, email, bio, password_hash, metodo_pagamento, nazione, regione, provincia, comune, via, numCiv)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, u.getNome());
			ps.setString(2, u.getRuolo());
			ps.setString(3, u.getCognome());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getBio());
			ps.setString(7, u.getHash());
			ps.setString(8, u.getMetodoPagamento());
			ps.setString(9, u.getNazione());
			ps.setString(10, u.getRegione());
			ps.setString(11, u.getProvincia());
			ps.setString(12, u.getComune());
			ps.setString(13, u.getVia());
			ps.setString(14, u.getNumCiv());
			
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
//metodo per modificare un utente
	@Override
	public void doUpdate(Utente u) throws SQLException {
		
		String sql = "UPDATE utenteRegistrato SET nome = ?, ruolo = ?, cognome = ?, username = ?, email = ?, bio = ?, password_hash = ?, metodo_pagamento = ?, nazione = ?, regione = ?, provincia = ?, comune = ?, via = ?, numCiv = ? WHERE id_utente = ? ";
		
		try(Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, u.getNome());
			ps.setString(2, u.getRuolo());
			ps.setString(3, u.getCognome());
			ps.setString(4, u.getUsername());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getBio());
			ps.setString(7, u.getHash());
			ps.setString(8, u.getMetodoPagamento());
			ps.setString(9, u.getNazione());
			ps.setString(10, u.getRegione());
			ps.setString(11, u.getProvincia());
			ps.setString(12, u.getComune());
			ps.setString(13, u.getVia());
			ps.setString(14, u.getNumCiv());
			ps.setInt(15, u.getId());
			
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
