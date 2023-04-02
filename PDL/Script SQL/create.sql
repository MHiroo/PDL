
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
CREATE TABLE Type_absence
(
 idtypeAbsence INTEGER,
 quota VARCHAR2(50) CONSTRAINT NN_quota NOT NULL,
 designation VARCHAR2(50),
 CONSTRAINT PK_type_absence PRIMARY KEY(idtypeabsence)
);
CREATE TABLE Absenceclassique
(
 idAbscla INTEGER,
 idEtud INTEGER,
 idcours INTEGER,
 Absenceclassique VARCHAR2(50) CONSTRAINT NN_Absence NOT NULL,
 nbrdheure INTEGER,
 date_abs_cls DATE CONSTRAINT NN_date_abs_cls NOT NULL,
 CONSTRAINT PK_Absenceclassique PRIMARY KEY(idAbscla)
);
CREATE TABLE Absencedistanciel
(
 idAbsdis INTEGER,
 idEtud INTEGER,
 idCours INTEGER,
 Absencedistanciel VARCHAR2(50),
 nbrdheure INTEGER,
 Date_abs_dst DATE,
 estjustifier VARCHAR2(50),
 CONSTRAINT PK_Absencedistanciel PRIMARY KEY(idAbsdis)
);

CREATE TABLE GroupeEtudiant
(
 idgroupe INTEGER, 
 capacitemax INTEGER,
 num INTEGER CONSTRAINT NN_num NOT NULL,
 CONSTRAINT PK_GroupeEtudiant PRIMARY KEY(idgroupe)
);
CREATE TABLE etudiant
(
 idetud INTEGER,
 idgroupe INTEGER,
 nomEtudiant VARCHAR2(50),
 prenomEtudiant VARCHAR2(50),
 tel VARCHAR2(50),
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
 idEnseignant INTEGER,
 idcours INTEGER,
 date_pln DATE,
 salle VARCHAR2(50),
 CONSTRAINT PK_Planning PRIMARY KEY(idplanning),
 CONSTRAINT FK_idCours FOREIGN KEY(idCours) REFERENCES Cours(idCours) ON DELETE CASCADE,
 CONSTRAINT FK_idEnseignant FOREIGN KEY(idEnseignant) REFERENCES Enseignant(idEnseignant) 
ON DELETE CASCADE,
 CONSTRAINT UN_idEnseignant UNIQUE(idEnseignant)
);
CREATE TABLE justificatifclassique
(
 idjustificatifcla INTEGER,
 idAbscla INTEGER,
 date_abs_cls DATE,
 etat VARCHAR2(50),
 motif VARCHAR2(50) CONSTRAINT CK_motif CHECK (motif IN ('certificatdeces', 'Covid','arrét 
maladie')),
 CONSTRAINT PK_justificatifclassique PRIMARY KEY(idjustificatifcla),
 CONSTRAINT FK_idAbscla FOREIGN KEY(idAbscla) REFERENCES 
 Absenceclassique(idAbscla) ON DELETE 
CASCADE
);
CREATE TABLE justificatifdistanciel
(
 idjustificatifdis INTEGER,
 idAbsdis INTEGER,
 date_abs_dst DATE,
 etat VARCHAR2(50),
 motif VARCHAR2(50) CONSTRAINT CK_motif_dis CHECK (motif IN ('certificatdeces', 'Covid','arrét 
maladie')),
 CONSTRAINT PK_justificatifdistanciel PRIMARY KEY(idjustificatifdis),
 CONSTRAINT FK_idAbsdis FOREIGN KEY(idAbsdis) 
 REFERENCES Absencedistanciel(idAbsdis) ON DELETE 
CASCADE
);
CREATE SEQUENCE justificatifdistanciel_seq
 START WITH 1
 INCREMENT BY 1
 NOCACHE
 NOCYCLE;