/* Popolazione BeHubDB */

/* Popolazione Utente */
INSERT INTO Utente (email, passwordUser, nome, cognome, indirizzo, telefono, numeroCarta, dataNascita, ruolo)
VALUES ('geekfactory@gmail.com', sha2('ABC12345', 256), 'Test', 'Test', 'Unisa, Dipartimento Informatica', '3476549862', '543672459843', 01/01/2000, 'AS');
INSERT INTO Utente (email, passwordUser, nome, cognome, indirizzo, telefono, numeroCarta, dataNascita)
VALUES ('mariorossi@gmail.com', sha2('TEST1234', 256), 'Mario', 'Rossi', 'Napoli, Via Roma 1, 80000', '3476549862', '543672459843', 01/01/2000);
INSERT INTO Utente (email, passwordUser, nome, cognome, indirizzo, telefono, numeroCarta, dataNascita)
VALUES ('luigiverdi@gmail.com', sha2('TEST5678', 256), 'Luigi', 'Verdi', 'Roma, Via Napoli 1, 11283', '3476549862', '543672459843', 01/01/2000);
INSERT INTO Utente (email, passwordUser, nome, cognome, indirizzo, telefono, numeroCarta, dataNascita)
VALUES ('antoniobianchi@gmail.com', sha2('Test1234', 256), 'Antonio', 'Bianchi', 'Milano, Via Napoli 2, 20192', '3476549862', '543672459843', 01/01/2000);

/* Popolazione Categoria */
INSERT INTO Categoria (nome) VALUES ('Abbigliamento');
INSERT INTO Categoria (nome) VALUES ('Giocattoli');
INSERT INTO Categoria (nome) VALUES ('Calzature');
INSERT INTO Categoria (nome) VALUES ('Elettronica');
INSERT INTO Categoria (nome) VALUES ('Libri');

/* Popolazione Prodotto */
INSERT INTO Prodotto (nome, descrizione, prezzo, speseSpedizione, emailVenditore, urlImmagine, nomeCategoria, condizione, quantity, dataAnnuncio)
VALUES ('Divina Commedia nuova edizione', 'Divina Commedia scritta da Dante Alighieri, maestro della poesia italiana medievale', 10, 2, 'mariorossi@gmail.com', 'C:\Users\franc\OneDrive\Documenti\apache-tomcat-10.0.27\BeHub\IMG\divinacommedia.jpg', 'Libri', 'Usato', 1, current_date());
INSERT INTO Prodotto (nome, descrizione, prezzo, speseSpedizione, emailVenditore, urlImmagine, nomeCategoria, condizione, quantity, dataAnnuncio)
VALUES ('Promessi Sposi', 'Divina Commedia scritta da Dante Alighieri, maestro della poesia italiana medievale', 10, 2, 'mariorossi@gmail.com', 'C:\Users\franc\OneDrive\Documenti\apache-tomcat-10.0.27\BeHub\IMG\divinacommedia.jpg', 'Libri', 'Nuovo', 10, current_date());
INSERT INTO Prodotto (nome, descrizione, prezzo, speseSpedizione, emailVenditore, urlImmagine, nomeCategoria, condizione, quantity, dataAnnuncio)
VALUES ('De Monarchia', 'Divina Commedia scritta da Dante Alighieri, maestro della poesia italiana medievale', 10, 2, 'luigiverdi@gmail.com', 'C:\Users\franc\OneDrive\Documenti\apache-tomcat-10.0.27\BeHub\IMG\divinacommedia.jpg', 'Libri', 'Usato', 1, current_date());
INSERT INTO Prodotto (nome, descrizione, prezzo, speseSpedizione, emailVenditore, urlImmagine, nomeCategoria, condizione, quantity, dataAnnuncio)
VALUES ('Harry Potter e la pietra filosofale nuovo', 'Divina Commedia scritta da Dante Alighieri, maestro della poesia italiana medievale', 10, 2, 'luigiverdi@gmail.com', 'C:\Users\franc\OneDrive\Documenti\apache-tomcat-10.0.27\BeHub\IMG\divinacommedia.jpg', 'Libri', 'Nuovo', 1, current_date());

/* Popolazione Ordine */
INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto)
VALUES (1000, 'luigiverdi@gmail.com', 12, 1, current_date());
INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto)
VALUES (1002, 'mariorossi@gmail.com', 24, 2, current_date());
INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto)
VALUES (1003, 'mariorossi@gmail.com', 36, 3, current_date());
INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto)
VALUES (1001, 'luigiverdi@gmail.com', 12, 1, current_date());

/* Popolazione Ticket */
INSERT INTO Ticket (testo, oggetto, dataInvio, emailUtente, stato)
VALUES ('Ho comprato un libro (De Monarchia) ma non è ancora arrivato dopo più di 1 mese. Voglio il rimborso!', 'Richiesta rimborso De Monarchia', current_date(), 'mariorossi@gmail.com', 'Aperto');
INSERT INTO Ticket (testo, oggetto, dataInvio, emailUtente, stato)
VALUES ('Ho comprato un libro (Harry Potter e a pietra filosofale) ma non è ancora arrivato dopo più di 1 mese. Voglio il rimborso!', 'Articolo non arrivato', current_date(), 'mariorossi@gmail.com', 'Chiuso');

