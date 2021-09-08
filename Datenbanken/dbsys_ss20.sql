DROP TABLE STORNIERTEBUCHUNG;

DROP TABLE LIEGTINNAEHE;

DROP TABLE ISTAUSGESTATTET;

DROP TABLE BILD;

DROP TABLE TOURISTENATTRAKTION;

DROP TABLE AUSSTATTUNG;

DROP TABLE ANZAHLUNG;

DROP TABLE BUCHUNG;

DROP TABLE FERIENWOHNUNG;

DROP TABLE KUNDE;

DROP TABLE ADRESSE;



DROP TABLE LAND;


CREATE TABLE Ausstattung
(   ausstattungsName varchar2(50),
    CONSTRAINT Austattung_pk PRIMARY KEY (ausstattungsName)
);

CREATE TABLE Land
(   landName varchar2(50),
    CONSTRAINT Land_pk PRIMARY KEY (landName)
);

CREATE TABLE Touristenattraktion
(   attraktionsID varchar2(10),
    beschreibung varchar2(50),
    attraktionsName varchar2(50),
    CONSTRAINT Touristenattraktion_pk PRIMARY KEY (attraktionsID)
);

CREATE TABLE Adresse
(   adressID varchar2(10) not null,
    strasse varchar2(50),
    plz varchar2(10),
    hausNr varchar2(10),
    landName varchar2(30),
    stadt varchar2(20),
    CONSTRAINT Adresse_pk PRIMARY KEY (adressID),
    CONSTRAINT Adresse_fk FOREIGN KEY (landName)
            REFERENCES Land(landName)
            ON DELETE CASCADE
);

CREATE TABLE Ferienwohnung
(   fwname varchar2(40),
    groesse float not null,
    anzahlZimmer int not null check (anzahlZimmer < 11),
    preisProTag numeric(9,2) check (preisProTag > 0),
    adressID varchar2(10),
    CONSTRAINT Ferienwohnung_pk PRIMARY KEY (fwname),
    CONSTRAINT Ferienwohnung_fk FOREIGN KEY (adressID)
               REFERENCES Adresse(adressID)
);
                                


CREATE TABLE Bild
(   bildID varchar2(10),
    fwname varchar2(40),
    CONSTRAINT Bild_pk PRIMARY KEY (bildID),
    CONSTRAINT Bild_fk FOREIGN KEY (fwname)
            REFERENCES Ferienwohnung(fwname)
);


CREATE TABLE Kunde
(   email varchar2(50),
    passwort varchar2(50) check (LENGTH(passwort) > 5),
    newsletter char check (newsletter in ('Y', 'N')),
    nachname varchar2(30),
    vorname varchar2(20),
    adressID varchar2(10),
    iban varchar2(31),
    CONSTRAINT Kunde_pk PRIMARY KEY (email),
    CONSTRAINT Kunde_fk FOREIGN KEY (adressID)
            REFERENCES Adresse(adressID)
);

CREATE TABLE Buchung
(   buchungsNummer varchar2(30),
    buchungsDatum date,
    anreise date,
    abreise date, 
    fwname varchar2(40),
    email varchar2(50),
    rechnungsNummer varchar2(15),
    rechnungsBetrag numeric(9,2) check(rechnungsBetrag > 0.1),
    bewertungsDatum date,
    anzahlSterne integer check(anzahlSterne > 0 and anzahlSterne < 6),
    CONSTRAINT anreise1 check(abreise > anreise),
    CONSTRAINT anreise2 check(abreise-anreise > 2),
    CONSTRAINT Buchung_pk PRIMARY KEY (buchungsNummer),
    CONSTRAINT Buchung_fk1 FOREIGN KEY (fwname) 
            REFERENCES Ferienwohnung(fwname),
    CONSTRAINT Buchung_fk2 FOREIGN KEY (email)
            REFERENCES Kunde(email)
);    
    
CREATE TABLE Anzahlung
(   anzahlungsID varchar(10),
    anzahlungsBetrag numeric(9,2) check(anzahlungsBetrag > -0.1),
    anzahlungsDatum date,
    buchungsNummer varchar2(30),
    CONSTRAINT Anzahlung_pk PRIMARY KEY (anzahlungsID),
    CONSTRAINT Anzahlung_fk FOREIGN KEY (buchungsNummer)
            REFERENCES Buchung(buchungsNummer)
);

CREATE TABLE IstAusgestattet
(   fwname varchar2(40),
    ausstattungsName varchar(50),
    CONSTRAINT IstAusgestattet_pk PRIMARY KEY (fwname, ausstattungsName),
    CONSTRAINT IstAusgestattet_fk1 FOREIGN KEY(fwname)
			REFERENCES Ferienwohnung(fwname)
			ON DELETE CASCADE,
	CONSTRAINT IstAusgestattet_fk2 FOREIGN KEY (ausstattungsName) 
            REFERENCES Ausstattung(ausstattungsName)
            ON DELETE CASCADE
);

CREATE TABLE LiegtInNaehe
(   fwname varchar2(40),
    attraktionsID varchar2(10),
    entfernung numeric(9,1) check(entfernung < 51 and entfernung > 0),
    CONSTRAINT LiegtInNaehe_pk PRIMARY KEY (fwname, attraktionsID),
    CONSTRAINT LiegtInNaehe_fk1 FOREIGN KEY(fwname)
			REFERENCES Ferienwohnung(fwname)
			ON DELETE CASCADE,
    CONSTRAINT LiegtInNaehe_fk2 FOREIGN KEY (attraktionsID)
            REFERENCES Touristenattraktion(attraktionsID)
            ON DELETE CASCADE
);

CREATE TABLE StornierteBuchung
(   sbuchungsNummer varchar2(30),
    sbuchungsDatum date,
    sanreise date,
    sabreise date, 
    sfwname varchar2(40),
    semail varchar2(50),
    srechnungsNummer varchar2(15),
    srechnungsBetrag numeric(9,2) check(srechnungsBetrag > 0.1),
    sbewertungsDatum date,
    sanzahlSterne integer check(sanzahlSterne > 0 and sanzahlSterne < 6),
    stornoDatum date,
    CONSTRAINT sanreise1 check(sabreise > sanreise),
    CONSTRAINT sanreise2 check(sabreise-sanreise > 2),
    CONSTRAINT StornierteBuchung_pk PRIMARY KEY (sbuchungsNummer),
    CONSTRAINT StornierteBuchung_fk1 FOREIGN KEY (sfwname) 
            REFERENCES Ferienwohnung(fwname),
    CONSTRAINT StornierteBuchung_fk2 FOREIGN KEY (semail)
            REFERENCES Kunde(email)
);

CREATE OR REPLACE TRIGGER stornoTrigger
BEFORE DELETE ON Buchung FOR EACH ROW
BEGIN
INSERT INTO StornierteBuchung
VALUES
(:OLD.buchungsNummer,:OLD.buchungsDatum,:OLD.anreise,
:OLD.abreise,:OLD.fwname,:OLD.email,
:OLD.rechnungsNummer,:OLD.rechnungsBetrag,:OLD.bewertungsDatum,
:OLD.anzahlSterne,CURRENT_DATE);
END;
/
insert into Ausstattung(ausstattungsname)
values ('Sauna');

insert into Ausstattung(ausstattungsname)
values ('Pool');

insert into ausstattung(ausstattungsname)
values('-');

insert into land(landname)
values ('Deutschland');

insert into land(landname)
values('Spanien');

insert into land(landname)
values('Frankreich');

insert into land(landname)
values('Polen');

insert into adresse (adressid, strasse, plz, hausnr, landname, stadt)
values ('0000000001', 'Carrer de Floridablanca', '08004', '38', 'Spanien', 'Barcelona');

insert into adresse (adressid, strasse, plz, hausnr, landname, stadt)
values ('0000000002', 'Koelnstrasse', '08004', '38', 'Spanien', 'Koeln');

insert into adresse (adressid, strasse, plz, hausnr, landname, stadt)
values ('3', 'Rua de Mallorca', '09999', '38', 'Spanien', 'Palma de Mallorca');

insert into adresse (adressid, strasse, plz, hausnr, landname, stadt)
values ('0000000004', 'Luvre', '08004', '56', 'Spanien', 'Paris');

insert into adresse (adressid, strasse, plz, hausnr, landname, stadt)
values ('0000000005', 'Rua de Espanha', '08004', '56', 'Spanien', 'Paris');

insert into ferienwohnung(fwname, groesse, anzahlZimmer, preisprotag, adressid)
values ('Wohnung am Rhein', 78.5, 3, 130.99, '0000000002');

insert into ferienwohnung(fwname, groesse, anzahlZimmer, preisprotag, adressid)
values ('Finca de Plaza', 123 , 5, 173.49, '0000000001');

insert into ferienwohnung(fwname, groesse, anzahlZimmer, preisprotag, adressid)
values ('Chateux de mer', 150, 7, 200, '0000000004');

insert into ferienwohnung(fwname, groesse, anzahlZimmer, preisprotag, adressid)
values ('Mallorca de Plaza', 126 , 6, 174, '3');

insert into ferienwohnung(fwname, groesse, anzahlZimmer, preisprotag, adressid)
values ('Casa Espanha', 126 , 6, 174, '0000000005');

INSERT INTO Kunde (email, passwort, newsletter, nachname, vorname, adressid, iban)
values ('hansmueller@gmail.com', 'Passwort123', 'Y', 'Mueller', 'Hans', '0000000001', 'DE12345678901234567890123456789');

INSERT INTO Kunde (email, passwort, newsletter, nachname, vorname, adressid, iban)
values ('petermaier@gmx.de', 'HalliHallo', 'N', 'Maier', 'Peter', '0000000002', 'DE98765432109876543210987654321');

insert into buchung(buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('0', to_date('2019-11-23','yyyy-mm-dd'), to_date('2019-12-13','yyyy-mm-dd'), to_date('2019-12-16','yyyy-mm-dd'), 'Wohnung am Rhein', 'petermaier@gmx.de', '000001234567890', 392.97, to_date('2019-12-17','yyyy-mm-dd'), 5);

insert into buchung(buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('1', to_date('2020-05-17','yyyy-mm-dd'), to_date('2020-08-28','yyyy-mm-dd'), to_date('2020-09-07','yyyy-mm-dd'), 'Chateux de mer', 'hansmueller@gmail.com', '000001234567810', 2200,null, null);

insert into buchung (buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('2', to_date('2017-10-24','yyyy-mm-dd'), to_date('2017-11-11','yyyy-mm-dd'), to_date('2017-11-15','yyyy-mm-dd'), 'Finca de Plaza', 'petermaier@gmx.de', '000001234567911', 693.96, to_date('2017-11-17','yyyy-mm-dd'), 5);

insert into buchung (buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('6', to_date('2020-10-24','yyyy-mm-dd'), to_date('2020-11-11','yyyy-mm-dd'), to_date('2020-11-15','yyyy-mm-dd'), 'Mallorca de Plaza', 'petermaier@gmx.de', '000001234567911', 693.96, to_date('2020-11-17','yyyy-mm-dd'), 5);

insert into buchung(buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('3', to_date('2019-10-28','yyyy-mm-dd'), to_date('2019-10-28','yyyy-mm-dd'), to_date('2019-11-05','yyyy-mm-dd'), 'Chateux de mer', 'hansmueller@gmail.com', '000001234567810', 2200,to_date('2019-11-15','yyyy-mm-dd'), 4);

insert into buchung(buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('4', to_date('2019-10-28','yyyy-mm-dd'), to_date('2019-11-18','yyyy-mm-dd'), to_date('2019-11-25','yyyy-mm-dd'), 'Wohnung am Rhein', 'hansmueller@gmail.com', '000001234567810', 2200,to_date('2019-11-15','yyyy-mm-dd'), 4);

insert into buchung(buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne)
values ('5', to_date('2019-10-28','yyyy-mm-dd'), to_date('2019-10-28','yyyy-mm-dd'), to_date('2019-11-25','yyyy-mm-dd'), 'Mallorca de Plaza', 'hansmueller@gmail.com', '000001234567810', 2200, to_date('2019-11-15','yyyy-mm-dd'), 4);


insert into touristenattraktion(attraktionsid, beschreibung, attraktionsname) values('001', 'Wasserpark', 'Splash Paradies');

insert into liegtinnaehe(fwname, attraktionsid, entfernung)
values('Chateux de mer', '001', 47.3);

insert into istausgestattet(fwname, ausstattungsname) values('Chateux de mer', 'Sauna');

insert into istausgestattet(fwname, ausstattungsname) values('Chateux de mer', 'Pool');

insert into istausgestattet(fwname, ausstattungsname) values('Finca de Plaza', 'Sauna');

insert into istausgestattet(fwname, ausstattungsname) values('Mallorca de Plaza', 'Sauna');

insert into istausgestattet(fwname, ausstattungsname) values('Wohnung am Rhein', 'Sauna');




grant select on Anzahlung to dbsys11;

grant select on Ferienwohnung to dbsys11;

grant select on Ausstattung to dbsys11;

grant select on Touristenattraktion to dbsys11;

grant select on istausgestattet to dbsys11;

grant select on liegtinnaehe to dbsys11;

grant select on Bild to dbsys11;

grant select on Land to dbsys11;

/*
grant select, update, delete on Adresse to dbsys11;

grant select, update, delete on Kunde to dbsys11;

grant select, update on Buchung to dbsys11;
*/

commit;

/* Aufgabe 1 */
select stadt, count (*)
from adresse natural join ferienwohnung
group by stadt;

/* Aufgabe 2 */
SELECT fwname, avg(anzahlsterne)
FROM ferienwohnung natural join adresse natural join land natural join buchung
where landname = 'Spanien'
and (select avg(anzahlSterne) AVG
    from Buchung) > 4 
group by fwname;

/* Aufgabe 3 */    
select count (*)
from ferienwohnung left outer join buchung 
on ferienwohnung.fwname = buchung.fwname
where buchung.buchungsnummer is null;

/* Aufgabe 4 */
select fwname
from istausgestattet group by fwname
having count(ausstattungsname) = (		
select MAX(anzahl)
from(
    select count(ausstattungsname) as anzahl
    from istausgestattet
    group by fwname));

/* Aufgabe 5 */   
create or replace view BuchungenProLand as
    select landname, count(*) as Buchungen
	from buchung natural join ferienwohnung natural join adresse natural join land
	group by landname;
	
select land.landname, count (buchungen) as anzahl
from land left outer join buchungenproland on land.landname = buchungenproland.landname
group by land.landname
order by anzahl desc;


/* Aufgabe 6 */

create or replace view SaunaInSpanien as
select fwname
from ferienwohnung natural join istausgestattet natural join adresse natural join land
where ausstattungsname = 'Sauna'
and landname = 'Spanien';

create or replace view Gebucht as
select fwname, abreise, anreise
from buchung natural join SaunaInSpanien
where (anreise between to_date('2019-11-01','yyyy-mm-dd') and to_date('2019-11-21','yyyy-mm-dd'))
or (anreise < to_date('2019-11-01','yyyy-mm-dd') and abreise > to_date('2019-11-21','yyyy-mm-dd'))
or (abreise between to_date('2019-11-01','yyyy-mm-dd') and to_date('2019-11-21','yyyy-mm-dd'));


SELECT ferienwohnung.fwname, avg(nvl(anzahlsterne,0)) avg
from ferienwohnung left outer join buchung on ferienwohnung.fwname = buchung.fwname
where ferienwohnung.fwname not in (select fwname from gebucht)
and ferienwohnung.fwname in (select fwname from SaunaInSpanien)
group by ferienwohnung.fwname
order by avg(nvl(anzahlsterne, 0)) desc;

SELECT * FROM BUCHUNG;

DELETE FROM BUCHUNG WHERE buchung.buchungsnummer = 5;

SELECT * FROM BUCHUNG;

SELECT * FROM storniertebuchung;


DROP VIEW KundeBuchung;
DROP VIEW KundeStornierung;
DROP VIEW KundennummerBuchung;
DROP VIEW KundennummerStornierung;
DROP VIEW KundeZahlungen;
DROP VIEW KundenstatistikTest;
DROP VIEW Kundenstatistik;


CREATE VIEW KundeBuchung(Kundennummer,AnzahlBuchung) AS
SELECT k.email, COUNT(b.buchungsnummer) FROM BUCHUNG B JOIN KUNDE K
ON k.email = b.email
GROUP BY k.email;

CREATE VIEW KundennummerBuchung(Kundennummer,AnzahlBuchung) AS
SELECT k.email, NVL(kb.AnzahlBuchung,0)
FROM KundeBuchung KB RIGHT OUTER JOIN Kunde K
ON kb.kundennummer = k.email;


CREATE VIEW KundeStornierung(Kundennummer,AnzahlStornierung) AS
SELECT k.email, COUNT(sb.sbuchungsnummer) FROM StornierteBuchung SB JOIN Kunde K
ON k.email = sb.semail
GROUP BY k.email;



CREATE VIEW KundennummerStornierung(Kundennummer,AnzahlStornierung) AS
SELECT k.email, NVL(ks.AnzahlStornierung,0) 
FROM KundeStornierung KS RIGHT OUTER JOIN Kunde K
ON ks.kundennummer = k.email;



CREATE VIEW KundeZahlungen(Kundennummer,Zahlung) AS
SELECT k.email,SUM(a.anzahlungsbetrag) FROM Anzahlung a,Kunde K,Buchung B
WHERE k.email = b.email
AND b.email = a.anzahlungsid
GROUP BY k.email;



CREATE VIEW KundenstatistikTest AS
SELECT kb.kundennummer,kb.anzahlbuchung,ks.anzahlstornierung FROM KundennummerBuchung KB JOIN KundennummerStornierung KS
ON kb.kundennummer = ks.kundennummer;



CREATE VIEW Kundenstatistik AS
SELECT kt.kundennummer,kt.anzahlbuchung,kt.anzahlstornierung,NVL(kz.Zahlung,0) 
as Zahlung FROM KundenstatistikTest KT LEFT OUTER JOIN KundeZahlungen KZ
ON kt.kundennummer = kz.kundennummer;

SELECT * FROM Kundenstatistik;

/*
CREATE VIEW Buchungen(Ferienwohnung)
AS SELECT f.fwname
FROM Ferienwohnung f, Buchung B
where (anreise between to_date('2020-08-28','yyyy-mm-dd') and to_date('2020-09-07','yyyy-mm-dd'))
or (anreise < to_date('2020-08-28','yyyy-mm-dd') and abreise > to_date('2020-09-07','yyyy-mm-dd'))
or (abreise between to_date('2020-08-28','yyyy-mm-dd') and to_date('2020-09-07','yyyy-mm-dd'));
*/

CREATE VIEW Buchungen AS
SELECT fwname, abreise, anreise
FROM buchung
WHERE (anreise BETWEEN TO_DATE('2019-08-28','yyyy-mm-dd')
AND TO_DATE('2019-08-28','yyyy-mm-dd'))
OR (anreise < TO_DATE('2019-08-28','yyyy-mm-dd')
AND abreise > TO_DATE('2019-09-07','yyyy-mm-dd'))
OR (abreise BETWEEN TO_DATE('2019-08-28','yyyy-mm-dd')
AND TO_DATE('2019-09-07','yyyy-mm-dd'));

/*
CREATE VIEW Buchungen AS
SELECT fwname, abreise, anreise
FROM buchung
WHERE (anreise BETWEEN TO_DATE('2019-08-28','yyyy-mm-dd')
AND TO_DATE('2019-08-28','yyyy-mm-dd'))
OR (anreise < TO_DATE('2019-08-28','yyyy-mm-dd')
AND abreise > TO_DATE('2019-09-07','yyyy-mm-dd'))
OR (abreise BETWEEN TO_DATE('2019-08-28','yyyy-mm-dd') 
AND TO_DATE('2019-09-07','yyyy-mm-dd'));
*/
SELECT * FROM Buchungen;

SELECT COUNT(*) AS NumberTest FROM Buchung;

DROP VIEW Buchungen;

DELETE FROM BUCHUNG WHERE buchung.buchungsnummer = 2;

SELECT COUNT(*) AS NumberTest FROM Buchung;

DROP VIEW ferienwohnunginspanien;
DROP VIEW gebuchtinspanien;

SELECT * FROM land;
SELECT * FROM ausstattung;
