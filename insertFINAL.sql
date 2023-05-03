INSERT INTO gestionnaire (idGst, nomGst, prenomGst, tel, email, motdepasse)
VALUES (1,'NomGst','PrénomGst','07345343','prenom.nom@esigelec.org','azerty');
INSERT INTO administratif(idAdmin, nomAdmin, prenomAdmin, tel, email, motdepasse)
VALUES (1,'NomAdmin','PrénomAdmin','07345343','prenom.nom@esigelec.org','azerty');
INSERT INTO enseignant (idEnseignant, nomEnseignant, prenomEnseignant, tel, email, motdepasse)
VALUES (1,'Cabot','Chloé','07345343','chloé.cabot@esigelec.org','azerty');
INSERT INTO GroupeEtudiant (idgroupe, capacitemax , num) 
VALUES (1,34,9);
INSERT INTO absenceclassique (idAbscla, idEtud, idcours, Absenceclassique, nbrdheure, date_abs_cls,statut)
VALUES (1,1,1,'N',180,TO_DATE('21-03-2023','DD-MM-YYYY'),'Non justifie');
INSERT INTO absencedistanciel (idAbsdis, idEtud, idcours, Absencedistanciel, nbrdheure, date_abs_dst,statut_dst)
VALUES (1,1,1,'N',180,TO_DATE('21-03-2023','DD-MM-YYYY'),'Non justifie');
INSERT INTO etudiant (idetud, idgroupe, nomEtudiant, prenomEtudiant, filiere, email, motdepasse)
VALUES (1,1,'Mizuno','Hiroo','classique','hiroo.mizuno@groupe-esigelec.org','azerty');
INSERT INTO etudiant (idetud, idgroupe, nomEtudiant, prenomEtudiant, filiere, email, motdepasse)
VALUES (2,1,'Matias','Alexandre','classique','alexandre.matias@groupe-esigelec.org','azerty');
INSERT INTO etudiant (idetud, idgroupe, nomEtudiant, prenomEtudiant, filiere, email, motdepasse)
VALUES (3,1,'Martineau','Aurelien','classique','aurelien.martineau@groupe-esigelec.org','azerty');
INSERT INTO cours (idCours, nomCours, masseHoraire, masseHoraireAmphi, masseHoraireTd, masseHoraireTp, masseHoraireExam)
VALUES (1,'PDL',120,4,30,80,6);
INSERT INTO type_absence (idtypeabsence, quota, designation)
VALUES (1,50,'Congé de deuil');
INSERT INTO type_absence (idtypeabsence, quota, designation)
VALUES (2,40,'malade');
INSERT INTO type_absence (idtypeabsence, quota, designation)
VALUES (3,10,'grève');

INSERT INTO justificatifclassique (idjustificatifcla , idAbscla, date_abs_cls, etat, motif) 
VALUES (1,1,TO_DATE('21-03-2023','DD-MM-YYYY'),'N','Covid');

INSERT INTO justificatifdistanciel (idjustificatifdis , idAbsdis, date_abs_dst, etat, motif) 
VALUES (1,1,TO_DATE('21-03-2023','DD-MM-YYYY'),'N','Covid');

INSERT INTO Planning (idplanning,idGroupe, idEnseignant, idcours, date_pln, salle,duree)
VALUES (1,1,1,1,TO_DATE('21-03-2023','DD-MM-YYYY'),'N',120);

INSERT INTO lien (idlien, lien)
VALUES (1,'https://ent.esigelec.fr/course/view.php?id=6070');


INSERT INTO Intervenant (idEnseignant, idCours)
VALUES (1,1);
