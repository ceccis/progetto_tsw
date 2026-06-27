package model.bean;

import java.time.LocalDateTime;

public class Acquisto {
	
	private int idAcquisto;
	private int idAcquirente;
	private int idLibro;
	private int idVenditore;
	private double prezzo_unitario;
	private double iva_percentuale;
	private int quantita;
	private LocalDateTime data; 
	private String metodoPagamento;
	
	
	public Acquisto () {};
	
	public int getIdAcquisto() {return idAcquisto;}
	public void setIdAcquisto(int idAcquisto) {this.idAcquisto = idAcquisto;}
	
	public int getIdAcquirente() {return idAcquirente;}
	public void setIdAcquirente(int idAcquirente) {this.idAcquirente = idAcquirente;}
	
	public int getIdLibro() {return idLibro;}
	public void setIdLibro(int idLibro) {this.idLibro = idLibro;}
	
	public int getIdVenditore() {return idVenditore;}
	public void setIdVenditore(int idVenditore) {this.idVenditore = idVenditore;}
	
	public double getPrezzo() {return prezzo_unitario;}
	public void setPrezzo(double prezzo) {this.prezzo_unitario = prezzo;}
	
	public double getIva() {return iva_percentuale;}
	public void setIva(double iva) {this.iva_percentuale = iva;}
	
	public int getQuantita() {return quantita;}
	public void setQuantita (int quantita) {this.quantita = quantita;}
	
	public LocalDateTime getData() {return data;}
	public void setData(LocalDateTime data) {this.data=data;}
	
	public String getMetodoPagamento() {return metodoPagamento;}
	public void setMetodoPagamento(String metodoPagamento) {this.metodoPagamento= metodoPagamento;}
	
	
}
