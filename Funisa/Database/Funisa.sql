/*
drop table notifica
drop table utente
drop table segnalazione
drop table recensione
drop table periferica
drop table postazione
drop table prenotazione
drop table notifica_utente
drop table prenotazione_periferica
*/

CREATE TABLE notifica(
    id INT PRIMARY KEY NOT NULL,
    descrizione VARCHAR(50) NOT NULL,
    isRead BIT NOT NULL,
    tipo VARCHAR(20)
)

CREATE TABLE utente(
    email VARCHAR(30) PRIMARY KEY NOT NULL,
    nome VARCHAR(30) NOT NULL,
    cognome VARCHAR(30) NOT NULL,
    username VARCHAR(30) NOT NULL,
    passw BINARY(32) NOT NULL,
    ruolo VARCHAR(10) NOT NULL,
    stato BIT NOT NULL DEFAULT 'FALSE',
    codiceVerifica char(7),
)

CREATE TABLE segnalazione(
    id INT PRIMARY KEY NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    descrizione VARCHAR(100) NOT NULL,
    UtenteEmail VARCHAR(30) NOT NULL,
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email)
)

CREATE TABLE recensione(
    descrizione VARCHAR(200) NOT NULL,
    valutazione INT NOT NULL,
    verificata bit NOT NULL DEFAULT 'FALSE',
    UtenteEmail VARCHAR(30) NOT NULL,
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email)
)

CREATE TABLE periferica(
    tipo VARCHAR(20) NOT NULL,
    nome VARCHAR(20) NOT NULL PRIMARY KEY,
    quantita INTEGER NOT NULL,
    prezzo FLOAT NOT NULL
)

CREATE TABLE postazione(
    id INT PRIMARY KEY NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    categoria VARCHAR(10) NOT NULL,
    prezzo FLOAT NOT NULL,
    disponibile BIT NOT NULL
)

CREATE TABLE prenotazione(
    id INT PRIMARY KEY NOT NULL,
    dataPrenotazione DATE NOT NULL,
    fasciaOraria CHAR(5) NOT NULL,
    QR VARCHAR(MAX),/*è un'immagine*/
    UtenteEmail VARCHAR(30) NOT NULL,
    PostazioneId INT NOT NULL,
    FOREIGN KEY (postazioneid) REFERENCES postazione(id),
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email)
)

CREATE TABLE notifica_utente(
    notificaId INT NOT NULL,
    UtenteEmail VARCHAR(30) NOT NULL,
    PRIMARY KEY(notificaId,UtenteEmail),
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email),
    FOREIGN KEY (notificaId) REFERENCES notifica(id)
)

CREATE TABLE prenotazione_periferica(
    prenotazioneId INT NOT NULL,
    perifericaNome VARCHAR(20) NOT NULL,
    PRIMARY KEY(prenotazioneId,perifericaNome),
    FOREIGN KEY(prenotazioneId) REFERENCES prenotazione(id),
    FOREIGN KEY(perifericaNome) REFERENCES periferica(nome) 
)