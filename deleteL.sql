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

SELECT Cours.nomCours FROM Cours INNER JOIN Planning ON Cours.idCours = Planning.idCours ;
SELECT nomCours FROM Cours;


SELECT nomCours FROM Cours 
INNER JOIN PLANNING ON (Cours.idCours=planning.idCours) 
INNER JOIN GroupeETUDIANT ON (Cours.idCours=planning.idCours)
WHERE((SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=GroupeETUDIANT.idGroupe);

SELECT Cours.nomCours FROM Cours INNER JOIN Planning ON Cours.idCours = Planning.idCours;

SELECT idEnseignant FROM Planning WHERE Planning.idCours =(SELECT idCours FROM Cours WHERE((SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe)) ;
SELECT nomEnseignant FROM Enseignant WHERE idEnseignant=(SELECT idEnseignant FROM Planning WHERE Planning.idCours =(SELECT idCours FROM Cours WHERE((SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))=Planning.idGroupe)));


SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe;
SELECT nomCours FROM COURS WHERE (idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe));

SELECT masseHoraire FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= 1 ))=Planning.idGroupe);