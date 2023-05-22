Projet Développement Logiciel
Gestion des absences
Cette application doit permettre la création et la gestion des cours, des étudiants, des enseignants et des groupes d'étudiants. Elle doit également permettre la gestion des absences, des justificatifs d'absence, des quotas d'absence, des rattrapages.

Table des matières :

1. Installation
2. Configuration
3. Exécution


1. Installation
Commencez par télécharger le fichier PDL_MARTINEAU_MATIAS_MIZUNO.zip.
Création de la base de données :
- Connectez-vous à SQL developer. Si besoin, se référer au document « Démarrer avec SQL developer » sur l’ENT dans l’onglet « Programmer avec Java ». 
- Exécutez le script de création de la base de données intitulée « createFINAL.sql »
Votre base de données est maintenant créée.
Ouverture du logiciel sur Eclipse :
- Lancer Eclipse IDE.
- Dans l’onglet File, sélectionner « Import Project From File System ».
- Cliquez sur le bouton « Archive».
- Recherchez le fichier zip MARTINEAU_MATIAS_MIZUNO.zip.
- Une fois le fichier sélectionné, cliquer sur le bouton « Ouvrir » puis « Finish ».

2. Configuration
Dans la classe « ConnectionDAO » :
- final static String URL prend la valeur :  "jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int" si vous travaillez depuis un PC de l’école. Sinon ne pas modifier.
- final static String LOGIN = "C##BDD9_ (mettez votre position dans la liste d’appel) »
- final static String PASS = "BDD9(mettez votre position dans la liste d’appel) ».
Vous pouvez ensuite lancer le logiciel.
Pour vous connectez en tant que gestionnaire la première fois :
- Choisissez Gestionnaire dans la fenêtre d’authentification.
- Identifiant : mail
- Mot de passe : 1234
A partir du menu du gestionnaire, vous pourrez alors créer des étudiants et des enseignants en choisissant les mots de passe et identifiants. 

Pour vous connectez en tant qu’admiratif la première fois :
- Choisissez Administratif dans la fenêtre d’Authentification.
- Identifiant : mail
- Mot de passe : 1234


3. Exécution 

Lancer le fichier UserGUI.java.

