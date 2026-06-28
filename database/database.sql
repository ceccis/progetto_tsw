-- DROP DATABASE sitotsw;
-- CREATE SCHEMA sitotsw;
USE sitotsw;

-- creazione tabelle entitá




CREATE TABLE utenteRegistrato( 
	id_utente INT AUTO_INCREMENT PRIMARY KEY,
    ruolo VARCHAR(20) NOT NULL DEFAULT 'utente', -- AGGIUNTA ATTRIBUTO RUOLO CON VALORE DEFAULT UTENTE
    nome VARCHAR(50) NOT NULL, 
    cognome VARCHAR(50)NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL, 
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(350) NOT NULL, -- AUMENTATO LUNGHEZZA PER L'HASH
    bio VARCHAR(500),
    nazione VARCHAR(100) NOT NULL, 
    regione VARCHAR(100) NOT NULL,
    provincia VARCHAR(100) NOT NULL,
    comune VARCHAR(100) NOT NULL,
    via VARCHAR(100) NOT NULL,
    numCiv VARCHAR(100) NOT NULL
   
);

CREATE TABLE libro (
	id_libro INT AUTO_INCREMENT PRIMARY KEY,
    id_venditore INT NOT NULL,
    disponibilita BOOLEAN DEFAULT TRUE, -- AGGIUNTA ATTRIBUTO DISPONIBILITA se un libro viene comprato disponibilita = false
    ISBN VARCHAR(50) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    autore VARCHAR(50) NOT NULL,
    genere VARCHAR(50) NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL DEFAULT 0.00 CHECK (prezzo >= 0), -- con default imposto un prezzo predefinito nel caso in cui non venga inserito e con check controllo che non vegnano messi numeri negativi
	foto BLOB, -- AGGIUNTA ATTRIBUTO FOTO
    descrizione VARCHAR(500),
    data_pubblicazione DATE NOT NULL,
   
    FOREIGN KEY (id_venditore) REFERENCES utenteRegistrato(id_utente) ON DELETE CASCADE
    -- questa foreign key serve per sapere quale utente ha messo il libro in vendita, con la servlet recuperemo l'id dell'utente tramite la sessione
);
   
CREATE TABLE acquisto( 
	id_acquisto INT AUTO_INCREMENT PRIMARY KEY,
    id_acquirente INT NOT NULL,
    id_libro INT NOT NULL,
    -- AGGIUNTA DATI PER LA FATTURA
    prezzo_unitario DECIMAL(10,2) NOT NULL,
    iva_percentuale DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    quantita INT NOT NULL DEFAULT 1, -- default 1 perchè essendo un sito di compravendita di libri usati, non è possibile acquistare più unità di un prodotto
    data_acquisto TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- quando viene fatto un acquisto verrá inserita automaticamente la data e ora del momento in cui é stato fatto
    metodo_pagamento_usato VARCHAR(50) NOT  NULL,
	id_venditore INT NOT NULL, 
    FOREIGN KEY (id_acquirente) REFERENCES utenteRegistrato(id_utente) ON DELETE RESTRICT, -- per garantire la generazione corretta della fattura (utente non può essere eliminato se ha fatto ordini)
    FOREIGN KEY (id_libro) REFERENCES libro(id_libro) ON DELETE RESTRICT, -- un libro non puó essere rimosso se ci sono degli acquisti collegati ad esso
    FOREIGN KEY (id_venditore) REFERENCES utenteRegistrato(id_utente) ON DELETE RESTRICT -- un venditore non può essere eliminato se ha effettuato una vendita
);

CREATE TABLE recensione(
	id_acquisto INT PRIMARY KEY,
    testo VARCHAR(500),
    data_recensione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rating INT CHECK (rating BETWEEN 1 AND 5),
   
FOREIGN KEY (id_acquisto) REFERENCES acquisto(id_acquisto) ON DELETE CASCADE -- se un ordine viene cancellato viene cancellata anche la recensione
);


-- creazione tabella relazione molti a molti

CREATE TABLE carrello (
    id_utente INT NOT NULL,
    id_libro INT NOT NULL,
    PRIMARY KEY (id_utente, id_libro),
   
    FOREIGN KEY (id_utente) REFERENCES utenteRegistrato(id_utente) ON DELETE CASCADE,
    FOREIGN KEY (id_libro) REFERENCES libro(id_libro) ON DELETE CASCADE
   
);



