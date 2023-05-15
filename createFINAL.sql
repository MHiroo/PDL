
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
CREATE TABLE Absenceclassique
(
 idAbscla INTEGER,
 idEtud INTEGER,
 idcours INTEGER,
 nbrdheure INTEGER,
 date_abs_cls DATE CONSTRAINT NN_date_abs_cls NOT NULL,
 statut VARCHAR2(50)CONSTRAINT CK_statut CHECK (statut IN ('Justifie', 'Non justifie','En verification')),
 CONSTRAINT PK_Absenceclassique PRIMARY KEY(idAbscla)
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
 CONSTRAINT PK_Planning PRIMARY KEY(idplanning),
 CONSTRAINT FK_idGroupe_Pln FOREIGN KEY(idGroupe) REFERENCES GroupeEtudiant(idGroupe) ON DELETE CASCADE,
 CONSTRAINT FK_idCours FOREIGN KEY(idCours) REFERENCES Cours(idCours) ON DELETE CASCADE,
 CONSTRAINT FK_idEnseignant FOREIGN KEY(idEnseignant) REFERENCES Enseignant(idEnseignant) 
ON DELETE CASCADE
 
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
 
  CREATE TABLE EstAbs
(
 idEtud INTEGER,
 idAbsCla INTEGER,
 statut VARCHAR2(50)CONSTRAINT CK_statut CHECK (statut IN ('Justifie', 'Non justifie','En verification')),
 CONSTRAINT PK_Etudiant_eabs PRIMARY KEY(idEtud, idAbsCla),
 CONSTRAINT FK_idEtud_eabs FOREIGN KEY(idEtud) REFERENCES Etudiant(idEtud) ON DELETE CASCADE,
 CONSTRAINT FK_idAbsCla_eabs FOREIGN KEY(idAbsCla) REFERENCES AbsenceClassique(idAbsCla) ON DELETE CASCADE
);


