-- DROP DATABASE sitotsw;
-- CREATE SCHEMA sitotsw;
USE sitotsw;

-- creazione tabelle entitá

CREATE TABLE utenteRegistrato( -- ho aggiunto attributi password_hash, username e bio che non avevo messo nello schema er
	id_utente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    username VARCHAR(50) UNIQUE NOT NULL, -- sia username che email hanno il vincolo "unique" cosí che non ci possano essere duplicati fra vari utenti.
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(100) NOT NULL, -- qui ci andrá l'hash della psw (la faremo usando una libreria java nella parte di registrazione del progetto)
    bio VARCHAR(500)
);

CREATE TABLE libro ( -- ho aggiunto attributo nome che non avevo messo nello schema er e come primary key ho usato un id generico al posto dell'ISBN (se due utenti diversi mettono lo stesso libro avranno ISBN uguale e non va bene)
	id_libro INT AUTO_INCREMENT PRIMARY KEY,
    id_venditore INT NOT NULL,
    ISBN VARCHAR(50) NOT NULL, 
    nome VARCHAR(100) NOT NULL,
    autore VARCHAR(50) NOT NULL,
    genere VARCHAR(50) NOT NULL,
    prezzo DECIMAL(10,2) NOT NULL DEFAULT 0.00 CHECK (prezzo >= 0), -- con default imposto un prezzo predefinito nel caso in cui non venga inserito e con check controllo che non vegnano messi numeri negativi
	descrizione VARCHAR(500),
    data_pubblicazione DATE NOT NULL,
    
    FOREIGN KEY (id_venditore) REFERENCES utenteRegistrato(id_utente) ON DELETE CASCADE
    -- questa foreign key serve per sapere quale utente ha messo il libro in vendita, con la servlet recuperemo l'id dell'utente tramite la sessione
);
    
CREATE TABLE acquisto( -- ho aggiunto attributo metodo_pagamento_usato che non ho messo nello schema er
	id_acquisto INT AUTO_INCREMENT PRIMARY KEY,
    id_utente INT NOT NULL,
    id_libro INT NOT NULL,
    data_acquisto TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- quando viene fatto un acquisto verrá inserita automaticamente la data e ora del momento in cui é stato fatto
    metodo_pagamento_usato VARCHAR(50) NOT  NULL,
    
    FOREIGN KEY (id_utente) REFERENCES utenteRegistrato(id_utente) ON DELETE CASCADE, -- se un utente viene eiminato vengono eliminati anche i suoi acquisti (non ha senso secondo me che ci siano acquisti senza un utente)
    FOREIGN KEY (id_libro) REFERENCES libro(id_libro) ON DELETE RESTRICT -- un libro non puó essere rimosso se ci sono degli acquisti collegati ad esso
);

CREATE TABLE recensione( -- ho aggiunto vari attributi che non ho messo nello schema er, fra cui una chiave esterna (id_acquisto) che funge anche da primary key
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
