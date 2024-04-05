/* --------------------------------------------------------------------------------------------- */
/* --------------------------------------- REMISE A ZERO --------------------------------------- */
/* --------------------------------------------------------------------------------------------- */

DROP TABLE IF EXISTS Evenement;
DROP TABLE IF EXISTS DJ;
DROP TABLE IF EXISTS Lieu;


/* --------------------------------------------------------------------------------------------- */
/* ----------------------------------- CREATION DES TABLES  ------------------------------------ */
/* --------------------------------------------------------------------------------------------- */

/* CREATION DE LA TABLE DJ */

CREATE TABLE DJ
(
	nom_scene varchar(30) not null primary key, 
	nom varchar(30) not null, 
	prenom varchar(30) not null, 
	date_naissance date not null, 
	lieu_residence varchar(30) not null, 
	style_musical varchar(12) not null
);

/* CREATION DE LA TABLE LIEU */

CREATE TABLE Lieu
(
	nom_site varchar(30) not null, 
	ville_site varchar(20) not null, 
	pays varchar(20) not null, 
	continent varchar(20) not null, 
	primary key (nom_site,ville_site)
);

/* CREATION DE LA TABLE EVENEMENT */

CREATE TABLE Evenement
(
	date_debut datetime not null, 
	date_fin datetime not null, 
	nom_DJ varchar(30) not null, 
	nom_lieu varchar(30) not null, 
	ville_lieu varchar(20) not null,  
	primary key (date_debut,nom_DJ,nom_lieu,ville_lieu), 
	foreign key (nom_DJ) references DJ(nom_scene) on delete cascade, 
	foreign key (nom_lieu,ville_lieu) references Lieu(nom_site,ville_site) on delete cascade
);


/* --------------------------------------------------------------------------------------------- */
/* ----------------------------------------- TRIGGERS  ----------------------------------------- */
/* --------------------------------------------------------------------------------------------- */

/* ON NE PEUT PAS AVOIR 2 EVENEMENTS SIMULTANES AU MEME ENDROIT AVEC 2 DJ DIFFERENTS */

DELIMITER //
CREATE TRIGGER Salle_prise BEFORE INSERT ON Evenement FOR EACH ROW IF EXISTS (select date_debut, date_fin, nom_lieu, nom_DJ, ville_lieu FROM Evenement where date_debut<NEW.date_fin and date_fin>NEW.date_debut and nom_lieu=NEW.nom_lieu and ville_lieu=NEW.ville_lieu) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Salle deja prise';
END IF//
DELIMITER ;

/* DATE DE DEBUT AVANT DATE DE FIN */

DELIMITER //
CREATE TRIGGER Mauvaises_horaires BEFORE INSERT ON Evenement FOR EACH ROW IF NEW.date_debut>=NEW.date_fin THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mauvaises horaires';
END IF//
DELIMITER ;

/* PERIODE DE 24H MINIMUM ENTRE 2 EVENEMENTS QUI ONT LIEU SUR LE MEME CONTINENT */

DELIMITER //
CREATE TRIGGER Intervalle_evenements_24_post BEFORE INSERT ON Evenement FOR EACH ROW IF EXISTS (select date_fin, nom_lieu, nom_DJ, ville_lieu FROM Evenement where nom_DJ=NEW.nom_DJ and NEW.date_debut<adddate(date_fin, interval 24 hour) and NEW.date_debut>date_fin and ville_lieu in (select ville_site from Lieu where continent=(select distinct continent from Lieu where ville_site=NEW.ville_lieu))) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nouvel evenement a moins de 24 heures apres un autre evenement';
END IF//
DELIMITER ;

DELIMITER //
CREATE TRIGGER Intervalle_evenements_24_ante BEFORE INSERT ON Evenement FOR EACH ROW IF EXISTS (select date_debut, nom_lieu, nom_DJ, ville_lieu FROM Evenement where nom_DJ=NEW.nom_DJ and date_debut<adddate(NEW.date_fin, interval 24 hour) and date_debut>NEW.date_fin and ville_lieu in (select ville_site from Lieu where continent=(select distinct continent from Lieu where ville_site=NEW.ville_lieu))) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nouvel evenement a moins de 24 heures avant un autre evenement';
END IF//
DELIMITER ;

/* PERIODE DE 48H MINIMUM ENTRE 2 EVENEMENTS QUI ONT LIEU SUR DES CONTINENTS DIFFERENTS */

DELIMITER //
CREATE TRIGGER Intervalle_evenements_48_post BEFORE INSERT ON Evenement FOR EACH ROW IF EXISTS (select date_fin, nom_DJ, ville_lieu FROM Evenement where nom_DJ=NEW.nom_DJ and NEW.date_debut<adddate(date_fin, interval 48 hour) and NEW.date_debut>date_fin and ville_lieu not in (select ville_site from Lieu where continent=(select distinct continent from Lieu where ville_site=NEW.ville_lieu))) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nouvel evenement a moins de 48 heures apres un autre evenement';
END IF//
DELIMITER ;

DELIMITER //
CREATE TRIGGER Intervalle_evenements_48_ante BEFORE INSERT ON Evenement FOR EACH ROW IF EXISTS (select date_debut, nom_lieu, nom_DJ, ville_lieu FROM Evenement where nom_DJ=NEW.nom_DJ and date_debut<adddate(NEW.date_fin, interval 48 hour) and date_debut>NEW.date_fin and ville_lieu in (select ville_site from Lieu where continent=(select distinct continent from Lieu where ville_site=NEW.ville_lieu))) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nouvel evenement a moins de 48 heures avant un autre evenement';
END IF//
DELIMITER ;

/* UN DJ DOIT AVOIR DES EVENEMENTS AVEC DES PLAGES HORAIRES DISTINCTES */

DELIMITER //
CREATE TRIGGER Evenements_concurrents BEFORE INSERT ON Evenement FOR EACH ROW IF EXISTS (select date_debut, nom_lieu, nom_DJ, ville_lieu FROM Evenement where nom_DJ=NEW.nom_DJ and date_debut<NEW.date_fin and date_fin>NEW.date_debut) THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ce DJ a deja un evenement sur cette plage horaire';
END IF//
DELIMITER ;


/* --------------------------------------------------------------------------------------------- */
/* ---------------------------------------- PROCEDURES  ---------------------------------------- */
/* --------------------------------------------------------------------------------------------- */

/* AFFICHAGE DE LA LISTE DES DJ */

DROP PROCEDURE IF EXISTS Liste_DJ;
DELIMITER //
CREATE PROCEDURE Liste_DJ ()
BEGIN select nom_scene from DJ;
END//
DELIMITER ;

/* AJOUT D'UN DJ */

DROP PROCEDURE IF EXISTS Ajout_DJ;
DELIMITER //
CREATE PROCEDURE Ajout_DJ (IN nom_scene_DJ varchar(30),nom_DJ varchar(30),prenom_DJ varchar(30),date_naissance_DJ date,lieu_residence_DJ varchar(30),style_musical_DJ varchar(12))
BEGIN insert into DJ values (nom_scene_DJ,nom_DJ,prenom_DJ,date_naissance_DJ,lieu_residence_DJ,style_musical_DJ);
END//
DELIMITER ;

/* AFFICHAGE DES INFORMATIONS POUR UN DJ */

DROP PROCEDURE IF EXISTS Infos_DJ;
DELIMITER //
CREATE PROCEDURE Infos_DJ (IN nom_scene_DJ varchar(30))
BEGIN select * from DJ where nom_scene=nom_scene_DJ;
END//
DELIMITER ;

/* SUPPRESSION D'UN DJ */

DROP PROCEDURE IF EXISTS Suppr_DJ;
DELIMITER //
CREATE PROCEDURE Suppr_DJ (IN nom_scene_DJ varchar(30))
BEGIN delete from DJ where nom_scene=nom_scene_DJ;
END//
DELIMITER ;

/* MODIFICATION DU NOM D'UN DJ */

DROP PROCEDURE IF EXISTS Modif_Nom_DJ;
DELIMITER //
CREATE PROCEDURE Modif_Nom_DJ (IN nom_scene_DJ varchar(30),nom_DJ varchar(30))
BEGIN UPDATE DJ SET nom=nom_DJ WHERE nom_scene=nom_scene_DJ;
END//
DELIMITER ;

/* MODIFICATION DU PRENOM D'UN DJ */

DROP PROCEDURE IF EXISTS Modif_Prenom_DJ;
DELIMITER //
CREATE PROCEDURE Modif_Prenom_DJ (IN nom_scene_DJ varchar(30),prenom_DJ varchar(30))
BEGIN UPDATE DJ SET prenom=prenom_DJ WHERE nom_scene=nom_scene_DJ;
END//
DELIMITER ;

/* MODIFICATION DE LA DATE DE NAISSANCE D'UN DJ */

DROP PROCEDURE IF EXISTS Modif_Date_Naissance_DJ;
DELIMITER //
CREATE PROCEDURE Modif_Date_Naissance_DJ (IN nom_scene_DJ varchar(30),date_naissance_DJ date)
BEGIN UPDATE DJ SET date_naissance=date_naissance_DJ WHERE nom_scene=nom_scene_DJ;
END//
DELIMITER ;

/* MODIFICATION DU LIEU DE RESIDENCE D'UN DJ */

DROP PROCEDURE IF EXISTS Modif_Lieu_Residence_DJ;
DELIMITER //
CREATE PROCEDURE Modif_Lieu_Residence_DJ (IN nom_scene_DJ varchar(30),lieu_residence_DJ varchar(30))
BEGIN UPDATE DJ SET lieu_residence=lieu_residence_DJ WHERE nom_scene=nom_scene_DJ;
END//
DELIMITER ;

/* MODIFICATION DU STYLE MUSICAL D'UN DJ */

DROP PROCEDURE IF EXISTS Modif_Style_Musical_DJ;
DELIMITER //
CREATE PROCEDURE Modif_Style_Musical_DJ (IN nom_scene_DJ varchar(30),style_musical_DJ varchar(12))
BEGIN UPDATE DJ SET style_musical=style_musical_DJ WHERE nom_scene=nom_scene_DJ;
END//
DELIMITER ;


/* AFFICHAGE DE LA LISTE DES LIEUX POUVANT ACCUEILLIR LES EVENEMENTS DES DJ */

DROP PROCEDURE IF EXISTS Liste_Lieux;
DELIMITER //
CREATE PROCEDURE Liste_Lieux ()
BEGIN select * from Lieu;
END//
DELIMITER ;

/* AJOUTER UN EVENEMENT */

DROP PROCEDURE IF EXISTS Ajout_Event;
DELIMITER //
CREATE PROCEDURE Ajout_Event (IN date_debut datetime, date_fin datetime, nom_scene_DJ varchar(30), nom_lieu varchar(30), ville_lieu varchar(30))
BEGIN insert into Evenement values (date_debut,date_fin,nom_scene_DJ,nom_lieu,ville_lieu); 
END//
DELIMITER ;

/* AFFICHAGE DE LA LISTE DES EVENEMENTS POUR UN MOIS DONNE */

DROP PROCEDURE IF EXISTS Liste_Events_mois;
DELIMITER //
CREATE PROCEDURE Liste_Events_mois (IN mois int)
BEGIN select * from Evenement where MONTH(date_debut)=mois or MONTH(date_fin)=mois; 
END//
DELIMITER ;

/* AFFICHAGE DE LA LISTE DES EVENEMENTS POUR UN DJ DONNE */

DROP PROCEDURE IF EXISTS Liste_Events_DJ;
DELIMITER //
CREATE PROCEDURE Liste_Events_DJ (IN nom_scene_DJ varchar(30))
BEGIN select * from Evenement where nom_DJ=nom_scene_DJ; 
END//
DELIMITER ;

/* AFFICHAGE DU TOP 5 DES DJ QUI ONT LE PLUS D'EVENEMENTS PASSES */

DROP PROCEDURE IF EXISTS Top_5_DJ;
DELIMITER //
CREATE PROCEDURE Top_5_DJ ()
BEGIN select nom_DJ as Nom, COUNT(*) as Nombre from Evenement Group by nom_DJ ORDER BY COUNT(*) DESC LIMIT 5; 
END//
DELIMITER ;




/* --------------------------------------------------------------------------------------------- */
/* ---------------------------------- REMPLISSAGE DES TABLES  ---------------------------------- */
/* --------------------------------------------------------------------------------------------- */

/* REMPLISSAGE DE LA TABLE DES DJ */

delete from DJ;
insert into DJ values ('DJ Snake','Grigahcine','William','1986-06-13','Paris','electro');
insert into DJ values ('David Guetta','Guetta','Pierre David','1967-11-07','Paris','house');
insert into DJ values ('Diplo','Pentz','Thomas Wesley','1978-11-10','Tupelo','electro');
insert into DJ values ('Tiesto','Verwest','Tijs Michiel','1969-01-17','Breda','EDM');
insert into DJ values ('Calvin Harris','Wiles','Adam Richard','1984-01-17','Dumfries','electro');
insert into DJ values ('DJ Baro','Baro','Vince','2000-03-20','Lannion','hard style');
insert into DJ values ('DJ Chenbo','Chenbo','Alex','2001-07-28','Lannion','house');

/* REMPLISSAGE DE LA TABLE DES LIEUX */

delete from Lieu;
insert into Lieu values ('Berghain','Berlin','Allemagne','Europe');
insert into Lieu values ('Fabric','Londres','Royaume-Uni','Europe');
insert into Lieu values ('Output','Brooklyn','États-Unis','Amérique du Nord');
insert into Lieu values ('Space','Ibiza','Espagne','Europe');
insert into Lieu values ('Amnesia','Ibiza','Espagne','Europe');
insert into Lieu values ('Pacha','Ibiza','Espagne','Europe');
insert into Lieu values ('Ushuaia','Ibiza','Espagne','Europe');
insert into Lieu values ('Hi Ibiza','Ibiza','Espagne','Europe');
insert into Lieu values ('Cavo Paradiso','Mykonos','Grèce','Europe');
insert into Lieu values ('DC10','Ibiza','Espagne','Europe');
insert into Lieu values ('Watergate','Berlin','Allemagne','Europe');
insert into Lieu values ('Ministry of Sound','Londres','Royaume-Uni','Europe');
insert into Lieu values ('Echostage','Washington D.C.','États-Unis','Amérique du Nord');
insert into Lieu values ('Output','New York','États-Unis','Amérique du Nord');
insert into Lieu values ('Amsterdam Dance Event (ADE)','Amsterdam','Pays-Bas','Europe');
insert into Lieu values ('Womb','Tokyo','Japon','Asie');
insert into Lieu values ('Zouk','Singapour','Singapour','Asie');
insert into Lieu values ('Omnia','Las Vegas','États-Unis','Amérique du Nord');
insert into Lieu values ('Pacha','Barcelone','Espagne','Europe');
insert into Lieu values ('Elrow','Barcelone','Espagne','Europe');
insert into Lieu values ('Rex Club','Paris','France','Europe');
insert into Lieu values ('Concrete','Paris','France','Europe');
insert into Lieu values ('Faust','Paris','France','Europe');
insert into Lieu values ('Warung Beach Club','Itajaí','Brésil','Amérique du Sud');
insert into Lieu values ('D-Edge','Sao Paulo','Brésil','Amérique du Sud');
insert into Lieu values ('Mandarino Club','Rio de Janeiro','Brésil','Amérique du Sud');
insert into Lieu values ('Crobar Buenos Aires','Buenos Aires','Argentine','Amérique du Sud');
insert into Lieu values ('Cocorico','Riccione','Italie','Europe');
insert into Lieu values ('Home Nightclub','Sydney','Australie','Océanie');
insert into Lieu values ('Marquee Sydney','Sydney','Australie','Océanie');
insert into Lieu values ('Revolver Upstairs','Melbourne','Australie','Océanie');
insert into Lieu values ('Elsewhere','Gold Coast','Australie','Océanie');

/* REMPLISSAGE DE LA TABLE DES EVENEMENTS */

insert into Evenement Values ('2024-06-10 22:00', '2024-06-10 23:30', 'DJ Snake', 'Pacha', 'Barcelone');
insert into Evenement Values ('2024-06-24 20:30', '2024-06-24 22:00', 'DJ Snake', 'Pacha', 'Ibiza');
insert into Evenement Values ('2024-07-12 21:30', '2024-07-12 23:00', 'DJ Baro', 'Output', 'New York');
insert into Evenement Values ('2024-05-23 20:30', '2024-05-23 22:00', 'Diplo', 'Faust', 'Paris');
insert into Evenement Values ('2024-08-04 22:30', '2024-08-05 02:00', 'David Guetta', 'Berghain', 'Berlin');
insert into Evenement Values ('2024-07-28 20:30', '2024-07-28 23:00', 'Tiesto', 'Cocorico', 'Riccione');
insert into Evenement Values ('2024-05-19 20:00', '2024-05-19 23:30', 'Calvin Harris', 'Zouk', 'Singapour');
insert into Evenement Values ('2024-06-08 20:45', '2024-06-08 23:15', 'DJ Chenbo', 'Womb', 'Tokyo');
insert into Evenement Values ('2024-08-07 21:20', '2024-08-07 23:50', 'David Guetta', 'Berghain', 'Berlin');
insert into Evenement Values ('2024-09-13 22:30', '2024-09-14 02:00', 'David Guetta', 'Concrete', 'Paris');
insert into Evenement Values ('2024-10-21 22:10', '2024-10-21 23:55', 'Tiesto', 'DC10', 'Ibiza');
insert into Evenement Values ('2024-05-10 20:45', '2024-05-10 23:45', 'Tiesto', 'Elsewhere', 'Gold Coast');
insert into Evenement Values ('2024-09-28 20:20', '2024-09-28 23:20', 'Tiesto', 'Elrow', 'Barcelone');



