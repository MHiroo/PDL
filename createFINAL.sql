
CREATE TABLE gestionnaire
(
 idGst INTEGER,
 nomGst VARCHAR2(50),
 prenomGst VARCHAR2(50),
 tel VARCHAR2(50),
 email VARCHAR2(50),
 motdepasse VARCHAR2(50),
 CONSTRAINT PK_Gestionnaire PRIMARY KEY(idGst)
);
CREATE TABLE administratif
(
 idAdmin INTEGER,
 nomAdmin VARCHAR2(50),
 prenomAdmin VARCHAR2(50),
 tel VARCHAR2(50),
 email VARCHAR2(50),
 motdepasse VARCHAR2(50),
 CONSTRAINT PK_Administratif PRIMARY KEY(idAdmin)
);
CREATE TABLE Enseignant
(
 idEnseignant INTEGER,
 nomEnseignant VARCHAR2(50) CONSTRAINT NN_nomEnseignant NOT NULL,
 prenomEnseignant VARCHAR2(50) CONSTRAINT NN_prenomEnseignant NOT NULL,
 tel VARCHAR2(50),
 email VARCHAR2(50),
 motdepasse VARCHAR2(50),
 CONSTRAINT PK_Enseignant PRIMARY KEY(idEnseignant)
);
CREATE SEQUENCE enseignant_seq
 START WITH 1
 INCREMENT BY 1
 NOCACHE
 NOCYCLE;
 
 CREATE TABLE Intervenant
(
 idEnseignant INTEGER,
 idCours INTEGER,
 CONSTRAINT PK_Intervenant PRIMARY KEY(idEnseignant, idCours),
 CONSTRAINT FK_idEnseignant_itv FOREIGN KEY(idEnseignant) REFERENCES Enseignant(idEnseignant) ON DELETE CASCADE,
 CONSTRAINT FK_idCours_itv FOREIGN KEY(idCours) REFERENCES Cours(idCours) ON DELETE CASCADE
);
CREATE TABLE Type_absence
(
 idtypeAbsence INTEGER,
 quota VARCHAR2(50) CONSTRAINT NN_quota NOT NULL,
 designation VARCHAR2(50),
 CONSTRAINT PK_type_absence PRIMARY KEY(idtypeabsence)
);
CREATE TABLE Absence
(
 idAbs INTEGER,
 idEtud INTEGER,
 idcours INTEGER,
 heureDebut FLOAT,
 nbrdheure INTEGER,
 date_abs DATE CONSTRAINT NN_date_abs NOT NULL,
 statut VARCHAR2(50)CONSTRAINT CK_statut CHECK (statut IN ('Justifie', 'Non justifie','En verification')),
 CONSTRAINT PK_Absence PRIMARY KEY(idAbs)
);




CREATE TABLE GroupeEtudiant
(
 idgroupe INTEGER, 
 capacitemax INTEGER,
 num INTEGER CONSTRAINT NN_num NOT NULL,
 CONSTRAINT PK_GroupeEtudiant PRIMARY KEY(idgroupe)
);
CREATE SEQUENCE GroupeEtudiant_seq
 START WITH 1
 INCREMENT BY 1
 NOCACHE
 NOCYCLE;
 
CREATE TABLE etudiant
(
 idetud INTEGER,
 idgroupe INTEGER,
 nomEtudiant VARCHAR2(50),
 prenomEtudiant VARCHAR2(50),
 filiere VARCHAR2(50),
 email VARCHAR2(50),
 motdepasse VARCHAR2(50),
 CONSTRAINT PK_Etudiant PRIMARY KEY(idEtud),
 CONSTRAINT FK_idgroupe FOREIGN KEY(idgroupe) REFERENCES 
GROUPEETUDIANT(idgroupe) 
);
CREATE SEQUENCE etudiant_seq
 START WITH 1
 INCREMENT BY 1
 NOCACHE
 NOCYCLE;
CREATE TABLE lien
(
 idlien INTEGER,
 lien VARCHAR2(100),
 CONSTRAINT PK_lien PRIMARY KEY(idlien)
 
);
CREATE TABLE Cours
(
 idCours INTEGER,
 nomCours VARCHAR2(40), 
 masseHoraire INTEGER, 
 masseHoraireAmphi INTEGER,
 masseHoraireTd INTEGER,
 masseHoraireTp INTEGER,
 masseHoraireExam INTEGER,
 CONSTRAINT PK_Cours PRIMARY KEY(idCours)
);
CREATE SEQUENCE Cours_seq
 START WITH 1
 INCREMENT BY 1
 NOCACHE
 NOCYCLE;
CREATE TABLE Planning
(
 idplanning INTEGER,
 idGroupe INTEGER, 
 idEnseignant INTEGER,
 idcours INTEGER,
 date_pln DATE,
 salle VARCHAR2(50),
 duree FLOAT,
 heure FLOAT,
 CONSTRAINT PK_Planning PRIMARY KEY(idplanning),
 CONSTRAINT FK_idGroupe_Pln FOREIGN KEY(idGroupe) REFERENCES GroupeEtudiant(idGroupe) ON DELETE CASCADE,
 CONSTRAINT FK_idCours FOREIGN KEY(idCours) REFERENCES Cours(idCours) ON DELETE CASCADE,
 CONSTRAINT FK_idEnseignant FOREIGN KEY(idEnseignant) REFERENCES Enseignant(idEnseignant) 
ON DELETE CASCADE
 
);
CREATE TABLE justificatif
(
 idjustificatif INTEGER,
 idAbs INTEGER,
 date_abs DATE,
 etat VARCHAR2(50),
 motif VARCHAR2(50) CONSTRAINT CK_motif CHECK (motif IN ('certificatdeces', 'Covid','arrét 
maladie')),
 CONSTRAINT PK_justificatif PRIMARY KEY(idjustificatif),
 CONSTRAINT FK_idAbs FOREIGN KEY(idAbs) REFERENCES 
 Absence(idAbs) ON DELETE 
CASCADE
);




