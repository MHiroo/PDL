DROP TABLE justificatifclassique;
DROP TABLE justificatifdistanciel;
DROP TABLE gestionnaire;
DROP TABLE administratif;
DROP TABLE absenceclassique;
DROP TABLE absencedistanciel;
DROP TABLE etudiant;
DROP TABLE GroupeEtudiant;
DROP TABLE enseignant;
DROP TABLE Cours;
DROP TABLE Planning;
DROP TABLE lien;
DROP TABLE type_absence;
DROP TABLE intervenant;
DROP TABLE estabs;


SELECT Cours.nomCours FROM Cours INNER JOIN Planning ON Cours.idCours = Planning.idCours ;
SELECT nomCours FROM Cours;


SELECT nomCours FROM Cours 
INNER JOIN PLANNING ON (Cours.idCours=planning.idCours) 
INNER JOIN GroupeETUDIANT ON (Cours.idCours=planning.idCours)
WHERE((SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=GroupeETUDIANT.idGroupe);

SELECT Cours.nomCours FROM Cours INNER JOIN Planning ON Cours.idCours = Planning.idCours;

SELECT idEnseignant FROM Planning WHERE Planning.idCours =(SELECT idCours FROM Cours WHERE((SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe)) ;
SELECT nomEnseignant FROM Enseignant WHERE idEnseignant=(SELECT idEnseignant FROM Planning WHERE Planning.idCours =(SELECT idCours FROM Cours WHERE((SELECT idGroupe FROM Etudiant WHERE (idEtud= 4 ))=Planning.idGroupe)));


SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe;
SELECT nomCours FROM COURS WHERE (idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe));

SELECT masseHoraire FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe);

SELECT masseHoraireAmphi FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe);
SELECT masseHoraireTD FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe);
SELECT masseHoraireTP FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe);


SELECT date_abs_cls FROM AbsenceClassique WHERE (idEtud= 1);
SELECT nbrdheure FROM AbsenceClassique WHERE (idEtud= 1);
SELECT statut FROM AbsenceClassique WHERE (idEtud= 1);
(SELECT idCours FROM AbsenceClassique WHERE (idEtud= 1))
SELECT nomCours FROM Cours WHERE (idCours =(SELECT idCours FROM AbsenceClassique WHERE (idEtud= 1)));

SELECT masseHoraire FROM Cours WHERE idCours IN (SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe);


SELECT * FROM planning WHERE (idGroupe = 1 AND date_pln = (TO_DATE('08-05-2023','DD-MM-YYYY'))) ORDER BY heure;
08-05-2023
SELECT * FROM planning WHERE (idGroupe = 1 AND date_pln = (TO_DATE('08-05-2023','DD-MM-YYYY'))) ORDER BY heure;
SELECT * FROM planning WHERE (idGroupe = 1 AND date_pln = (TO_DATE('08-05-2023','DD-MM-YYYY'))) ORDER BY heure;
