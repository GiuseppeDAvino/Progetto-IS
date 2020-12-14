/*
drop table segnalazione
drop table recensione
drop table notifica_utente
drop table prenotazione_periferica
drop table notifica
drop table periferica
drop table prenotazione
drop table postazione
drop table utente
drop table categoria
*/

CREATE TABLE notifica(
    id INT PRIMARY KEY NOT NULL IDENTITY (1, 1),
    descrizione VARCHAR(50) NOT NULL,
    tipo VARCHAR(20)
)
INSERT INTO notifica(descrizione,tipo) VALUES('PROVA','1');
INSERT INTO notifica(descrizione,tipo) VALUES('PROVA','2');
INSERT INTO notifica(descrizione,tipo) VALUES('PROVA','3');

SELECT * from notifica

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

INSERT INTO utente VALUES('email@email.com','Prova','Provino','Provola',0111000001110010011000100001010,'prova','true','1548763')


CREATE TABLE segnalazione(
    id INT PRIMARY KEY NOT NULL IDENTITY (1, 1),
    tipo VARCHAR(10) NOT NULL,
    descrizione VARCHAR(100) NOT NULL,
    utenteEmail VARCHAR(30) NOT NULL,
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email)
)

CREATE TABLE recensione(
    descrizione VARCHAR(200) NOT NULL,
    valutazione INT NOT NULL,
    verificata bit NOT NULL DEFAULT 'FALSE',
    utenteEmail VARCHAR(30) NOT NULL,
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email)
)

CREATE TABLE periferica(
    tipo VARCHAR(20) NOT NULL,
    nome VARCHAR(20) NOT NULL PRIMARY KEY,
    quantita INTEGER NOT NULL,
    prezzo FLOAT NOT NULL
)
CREATE TABLE categoria(
    nome VARCHAR(15) PRIMARY KEY NOT NULL,
    tipoGenerico VARCHAR(15) NOT NULL,
    prezzo FLOAT NOT NULL,
    descrizione VARCHAR(100) NOT NULL,
    immagine VARCHAR(MAX) NOT NULL
)
CREATE TABLE postazione(
    id INT PRIMARY KEY NOT NULL IDENTITY (1, 1),
    nomeCategoria VARCHAR(15) NOT NULL,
    disponibile BIT NOT NULL,
    FOREIGN KEY (nomeCategoria) REFERENCES categoria(nome)
)


CREATE TABLE prenotazione(
    id INT PRIMARY KEY NOT NULL IDENTITY (1, 1),
    dataPrenotazione DATE NOT NULL,
    fasciaOraria CHAR(5) NOT NULL,
    qR VARCHAR(MAX),/*è un'immagine*/
    utenteEmail VARCHAR(30) NOT NULL,
    postazioneId INT NOT NULL,
    FOREIGN KEY (postazioneid) REFERENCES postazione(id),
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email)
)

CREATE TABLE notifica_utente(
    notificaId INT NOT NULL,
    utenteEmail VARCHAR(30) NOT NULL,
    isRead BIT NOT NULL DEFAULT 'FALSE',
    PRIMARY KEY(notificaId,UtenteEmail),
    FOREIGN KEY (UtenteEmail) REFERENCES utente(email),
    FOREIGN KEY (notificaId) REFERENCES notifica(id)
)

SELECT * from notifica_utente

CREATE TABLE prenotazione_periferica(
    prenotazioneId INT NOT NULL,
    perifericaNome VARCHAR(20) NOT NULL,
    PRIMARY KEY(prenotazioneId,perifericaNome),
    FOREIGN KEY(prenotazioneId) REFERENCES prenotazione(id),
    FOREIGN KEY(perifericaNome) REFERENCES periferica(nome) 
)
