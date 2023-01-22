DROP database IF EXISTS BeHubDB;
CREATE database BeHubDB;

DROP USER IF EXISTS 'admin'@'localhost';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON BeHubDB.* TO 'admin'@'localhost';

USE BeHubDB;
SET GLOBAL time_zone = '+1:00';

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente
(
	email varchar(50) PRIMARY KEY NOT NULL,
    passwordUser varchar(256) NOT NULL,
	nome varchar(50) NOT NULL,
    cognome varchar(50) NOT NULL,
    indirizzo varchar(150) NOT NULL,
    telefono varchar(15) NOT NULL,
    numeroCarta char(16),
    dataNascita date NOT NULL,
    intestatario varchar(50),
    ruolo ENUM('AC','AS','RU') NOT NULL DEFAULT 'RU'
);

DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria
(
    nome ENUM('Abbigliamento','Calzature','Elettronica','Libri','Giocattoli') PRIMARY KEY NOT NULL
);

DROP TABLE IF EXISTS Prodotto;
CREATE TABLE Prodotto
(
	codice int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    descrizione text NOT NULL,
    deleted BOOL NOT NULL DEFAULT false,
    prezzo double(10,2) NOT NULL,
    speseSpedizione double(7,2) NOT NULL,
    emailVenditore varchar(50) NOT NULL,
    urlImmagine varchar(300) NOT NULL,
    nomeCategoria ENUM('Abbigliamento','Calzature','Elettronica','Libri','Giocattoli') NOT NULL,
    condizione ENUM('Nuovo', 'Usato') NOT NULL,
    quantity int NOT NULL,
    dataAnnuncio date NOT NULL,
    FOREIGN KEY(emailVenditore) REFERENCES Utente(email) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(nomeCategoria) REFERENCES Categoria(nome) ON UPDATE cascade ON DELETE cascade
)ENGINE=InnoDB AUTO_INCREMENT=1000;

DROP TABLE IF EXISTS Ordine;
CREATE TABLE Ordine
(
	codiceOrdine int NOT NULL AUTO_INCREMENT,
    codiceProdotto int NOT NULL,
    emailCliente varchar(50) NOT NULL,
    prezzoTotale double(10,2) NOT NULL,
    quantity int NOT NULL,
    dataAcquisto date NOT NULL,
    tracking varchar(50),
    stato ENUM('In lavorazione', 'Spedito', 'Consegnato') NOT NULL default 'In lavorazione',
    PRIMARY KEY(codiceOrdine,codiceProdotto),
    FOREIGN KEY(codiceProdotto) REFERENCES Prodotto(codice) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(emailCliente) REFERENCES Utente(email) ON UPDATE cascade ON DELETE cascade
)ENGINE=InnoDB AUTO_INCREMENT=100;

DROP TABLE IF EXISTS Ticket;
CREATE TABLE Ticket
(
	codice int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    testo text NOT NULL,
    oggetto varchar(70) NOT NULL,
    dataInvio date NOT NULL,
    emailUtente varchar(50) NOT NULL,
    stato ENUM('Aperto', 'Chiuso') NOT NULL,
    FOREIGN KEY (emailUtente) REFERENCES Utente(email) ON UPDATE cascade ON DELETE cascade
)ENGINE=InnoDB AUTO_INCREMENT=10;