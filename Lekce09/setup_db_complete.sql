-- Prihlasit se jako root, prazdne heslo
CREATE USER  'student'@'localhost'   IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO   'student'@'%'   IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO   'student'@'localhost'   IDENTIFIED BY 'password';




-- Prihlasit se jako student, heslo password
# DROP DATABASE VideoBoss;

CREATE DATABASE VideoBoss
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_czech_ci;

USE VideoBoss;

CREATE TABLE Branches
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    BranchName VARCHAR(200) NOT NULL
);

CREATE TABLE Customers
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Firstname VARCHAR(250) NOT NULL,
    Lastname VARCHAR(250) NOT NULL,
    Address VARCHAR(500),
    Deleted TINYINT DEFAULT 0,
    Version INT DEFAULT 0
);

CREATE TABLE Films
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    Name VARCHAR(250) NOT NULL,
    ReleaseYear INT NOT NULL,
    Rating DOUBLE DEFAULT -1 NOT NULL,
    Picture MEDIUMBLOB,
    Version INT DEFAULT 0,
    ExternalLink VARCHAR(1024)
);

CREATE TABLE MediaItems
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    FilmID INT,
    BranchID INT,
    Available TINYINT DEFAULT 1,
    Version INT DEFAULT 0,
    CONSTRAINT MediaItems_ibfk_2 FOREIGN KEY (BranchID) REFERENCES Branches (ID),
    CONSTRAINT MediaItems_ibfk_1 FOREIGN KEY (FilmID) REFERENCES Films (ID)
);

CREATE TABLE Loans
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    MediaItemID INT NOT NULL,
    CustomerID INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    IsOpen TINYINT DEFAULT 1 NOT NULL,
    Version INT DEFAULT 0,
    CONSTRAINT Loans_ibfk_2 FOREIGN KEY (CustomerID) REFERENCES Customers (ID),
    CONSTRAINT Loans_ibfk_1 FOREIGN KEY (MediaItemID) REFERENCES MediaItems (ID)
);


INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jan', 'Dvořák', 'Veselá 16, 111 50 Praha', 0, 42);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jiří', 'Šikovný', 'Jindřiššká 14, 111 50 Praha', 0, 7);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('František', 'Kožich', 'Nerudova 5, 111 50 Praha', 1, 5);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Daniel', 'Válec', 'Jabloňová 8, 602 00 Brno', 1, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Václav', 'Fiala', 'Grohova 6, 602 00 Brno', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Karel', 'Fendrich', 'Plantážní 5, 606 00 Brno', 0, 6);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Petr', 'Kozák', 'Vysoká 18, 532 00 Olomouc', 1, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('František', 'Poláček', 'Kounicova 10, 612 00 Brno', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Drahoslav', 'Hartman', 'Jungmannova 33, 110 00 Praha 1', 0, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Karel', 'Zelenka', 'Vocelova 1334, Hradec Králové', 0, 16);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jan', 'Čápek', 'Tasova 20, 683 33 Brankovice', 0, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Petr', 'Slezák', 'Hrušky 22, 683 52 Hrušky', 0, 4);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Stanislava', 'Kočová', 'Školní 18, 671 28 Jaroslavice', 1, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Matěj', 'Kohout', 'Stříbrné Hory 15, 341 01 Nalžovské Hory', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Daniel', 'Drábek', 'Spojovací 15, 503 11 Hradec Králové', 0, 7);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Otakar', 'Šťastný', 'Náměstí 2, 373 11 Ledenice', 0, 4);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Zdeněk', 'Suchoň', 'Jeřábkova 6, 399 01 Milevsko', 0, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jaroslav', 'Kozlík', 'Pohůrecká 12, 370 06 České Budějovice', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jan', 'Bayer', 'Kostelní 8, 671 72 Miroslav', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Václav', 'Nenosil', 'Komenského 512, 375 01 Týn nad Vltavou+', 1, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jaroslav', 'Hašek', 'Haškova 10, 631 00 Brno-Lesná', 1, 3);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jan', 'Samec', 'Samcova', 1, 0);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Vašek', 'Přeholík', 'Ostrava', 1, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Karel', 'Snášel', 'Frýdek-Místek', 1, 1);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Karel', 'Snášel', 'Frýdek-Místek', 1, 1);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Dana', 'Silvanova', 'Praha', 1, 1);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Václav', 'Snědý', 'Ostrava', 1, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Radovan', 'Snědý', 'Mariánská 16, Ostrava', 0, 1);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jan', 'Jandera', 'Janov', 1, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Marek', 'Veselka', 'Sosnová 37, 470 01 Sosnová', 0, 5);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Jan', 'Semerád', 'Slevova 8, Semily', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Karel', 'Šikovný', 'Jindřiššká 16, 111 50 Praha', 0, 6);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('František', 'Kožich', 'Nerudova 5, 111 50 Praha', 0, 2);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Václav', 'Fiala', 'Grohova 6, 602 00 Brno', 0, 0);
INSERT INTO VideoBoss.Customers (Firstname, Lastname, Address, Deleted, Version) VALUES ('Karel', 'Světec', 'Světlá nad Sázavou', 0, 1);


INSERT INTO VideoBoss.Branches (BranchName) VALUES ('Lesná');
INSERT INTO VideoBoss.Branches (BranchName) VALUES ('Bystrc');
INSERT INTO VideoBoss.Branches (BranchName) VALUES ('Královo pole');
INSERT INTO VideoBoss.Branches (BranchName) VALUES ('Řečkovice');
INSERT INTO VideoBoss.Branches (BranchName) VALUES ('Komárov');


INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Terminator 1', 1984, 87, 0, 'http://www.imdb.com/title/tt0088247/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Terminator 2: Judgement Day', 1991, 91, 0, 'http://www.imdb.com/title/tt0103064/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Terminator 3: Rise of the Machines', 2003, 72, 0, 'http://www.imdb.com/title/tt0181852/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Terminator 4: Terminator Salvation', 2009, 70, 0, 'http://www.imdb.com/title/tt0438488/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Star Trek', 2008, 87, 1, 'http://www.imdb.com/title/tt0796366/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Valkyrie', 2009, 82, 1, 'http://www.imdb.com/title/tt0985699/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Harry Potter and the Prisoner of Azkaban', 2004, 78, 2, 'http://www.imdb.com/title/tt0304141/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Minority Report', 2002, 81, 1, 'http://www.imdb.com/title/tt0181689/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Shawshank Redemption', 1994, 95, 0, 'http://www.imdb.com/title/tt0111161/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Schindler''s List', 1993, 94, 1, 'http://www.imdb.com/title/tt0108052/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Forrest Gump', 1994, 95, 0, 'http://www.imdb.com/title/tt0109830/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Back to the Future I', 1985, 90, 0, 'http://www.imdb.com/title/tt0088763/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Back to the Future II', 1989, 87, 2, 'http://www.imdb.com/title/tt0096874/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Back to the Future III ', 1990, 86, 0, 'http://www.imdb.com/title/tt0099088/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Saving Private Ryan', 1998, 88, 0, 'http://www.imdb.com/title/tt0120815/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Kolja', 1995, 87, 2, 'http://www.imdb.com/title/tt0116790/');
INSERT INTO VideoBoss.Films (Name, ReleaseYear, Rating, Version, ExternalLink) VALUES ('Resident Evil', 2002, 67, 1, 'http://www.imdb.com/title/tt0120804/');


INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (1, 2, 1, 0);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (1, 3, 1, 0);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (1, 3, 1, 10);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (1, 1, 1, 0);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (2, 1, 1, 4);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (2, 1, 0, 7);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (4, 1, 1, 4);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (4, 2, 1, 22);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (5, 3, 1, 2);
INSERT INTO VideoBoss.MediaItems (FilmID, BranchID, Available, Version) VALUES (5, 2, 1, 5);


INSERT INTO VideoBoss.Loans (MediaItemID, CustomerID, StartDate, EndDate, IsOpen, Version) VALUES (6, 1, '2011-03-03', '2011-03-03', 0, 0);
INSERT INTO VideoBoss.Loans (MediaItemID, CustomerID, StartDate, EndDate, IsOpen, Version) VALUES (5, 1, '2011-03-03', '2011-03-03', 0, 0);
INSERT INTO VideoBoss.Loans (MediaItemID, CustomerID, StartDate, EndDate, IsOpen, Version) VALUES (6, 8, '2011-04-07', '2011-04-08', 1, 0);


















-- Prihlasit se jako student, heslo password
# DROP DATABASE DailyPlanet;

CREATE DATABASE DailyPlanet
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_czech_ci;

USE DailyPlanet;

CREATE TABLE Clanky (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  Nazev VARCHAR(250),
  Autor VARCHAR(250),
  Datum DATE DEFAULT now() NOT NULL
);

INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('Lidé doma hromadí léky', 'Loise Lane', '2019-03-20');
INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('Nový japonský císař', 'Clark Kent', '2019-04-30');
INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('Prodeje androidových telefonů rostou', 'Bob Harley', '2018-08-22');
INSERT INTO Clanky (Nazev, Autor, Datum) VALUES ('U přehrady našli utonulého muže', 'Cat Grant', '2019-06-01');












-- Prihlasit se jako student, heslo password
# DROP DATABASE Pexeso;

CREATE DATABASE Pexeso
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_czech_ci;

USE Pexeso;

CREATE TABLE HerniPlochy (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  Stav VARCHAR(250),
  CasPoslednihoTahu TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE Karty (
  ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  HerniPlochaID INT NOT NULL,
  CisloKarty INT DEFAULT 0 NOT NULL,
  Stav VARCHAR(250) ,
  PoradiKarty INT NOT NULL DEFAULT 0,
  CONSTRAINT HerniPlocha_FK FOREIGN KEY (HerniPlochaID) REFERENCES HerniPlochy (ID)
);







-- Prihlasit se jako student, heslo password
# DROP DATABASE SeznamKontaktu;

CREATE DATABASE SeznamKontaktu
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_czech_ci;

USE SeznamKontaktu;

CREATE TABLE Kontakt (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  Jmeno VARCHAR(250),
  TelefonniCislo VARCHAR(32),
  Email VARCHAR(250)
);

INSERT INTO Kontakt (Jmeno, TelefonniCislo, Email) VALUES ('Leonardo Da Vinci', '+39 234 123 950', 'leo@davinci.code');
INSERT INTO Kontakt (Jmeno, TelefonniCislo, Email) VALUES ('Marie Curie', '+420 604 111 222', 'marie.curie@sorbonne-universite.fr');
INSERT INTO Kontakt (Jmeno, TelefonniCislo, Email) VALUES ('Thomas Alva Edison', '+1-123-555-666', 'thomas@edison.com');
INSERT INTO Kontakt (Jmeno, TelefonniCislo, Email) VALUES ('Albert Einstein', '+41 953 203 569', 'albert.einstein@cern.ch');
INSERT INTO Kontakt (Jmeno, TelefonniCislo, Email) VALUES ('Kamil Ševeček', '+420 604 111 222', 'kamil.sevecek@czechitas.cz');
