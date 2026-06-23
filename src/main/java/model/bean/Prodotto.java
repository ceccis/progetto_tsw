package model.bean;

import java.time.LocalDate;

public class Prodotto{	
	
	private int id;
	private String titolo;
	private double prezzo;
	private byte[] foto;
	private String ISBN;
	private String autore;
	private String genere;
	private String descrizione;
	private LocalDate data;
	private int id_venditore;
	
	public Prodotto() {}
	
	
	public Prodotto(int id, String titolo, double prezzo, byte[] foto, String ISBN, String autore, String genere, String descrizione, LocalDate data, int id_venditore) {
		this.id = id;
		this.titolo= titolo;
		this.prezzo= prezzo;
		this.foto = foto;
		this.ISBN = ISBN;
		this.autore= autore;
		this.genere = genere;
		this.descrizione= descrizione;
		this.data= data;
		this.id_venditore = id_venditore;
		
	}
	
	
	public int getIdLibro() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getTitolo() {return titolo;}
	public void setTitolo(String titolo) {this.titolo=titolo;}
	
	public double getPrezzo() {return prezzo;}
	public void setPrezzo(double prezzo) {this.prezzo = prezzo;}
	
	public byte[] getFoto() {return foto;}
	public void setFoto(byte[] foto) {this.foto=foto;}
	
	public String getISBN() {return ISBN;}
	public void setISBN(String ISBN) {this.ISBN = ISBN;}
	
	public String getAutore() {return autore;}
	public void setAutore(String autore) {this.autore = autore;}
	
	public String getGenere() {return genere;}
	public void setGenere(String genere) {this.genere = genere;}
	
	public String getDescrizione() {return descrizione;}
	public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
	
	public LocalDate getData() {return data;}
	public void setData(LocalDate data) {this.data=data;}
	
	public int getIdVenditore() {return id_venditore;}
	public void setIdVenditore(int id_venditore) {this.id_venditore = id_venditore;}
	
	
}


