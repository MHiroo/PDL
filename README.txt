Projet D�veloppement Logiciel
Gestion des absences
Cette application doit permettre la cr�ation et la gestion des cours, des �tudiants, des enseignants et des groupes d'�tudiants. Elle doit �galement permettre la gestion des absences, des justificatifs d'absence, des quotas d'absence, des rattrapages.

Table des mati�res�:

1. Installation
2. Configuration
3. Ex�cution


1. Installation
Commencez par t�l�charger le fichier PDL_MARTINEAU_MATIAS_MIZUNO.zip.
Cr�ation de la base de donn�es�:
- Connectez-vous � SQL developer. Si besoin, se r�f�rer au document ��D�marrer avec SQL developer�� sur l�ENT dans l�onglet ��Programmer avec Java��. 
- Ex�cutez le script de cr�ation de la base de donn�es intitul�e � createFINAL.sql��
Votre base de donn�es est maintenant cr��e.
Ouverture du logiciel sur Eclipse�:
- Lancer Eclipse IDE.
- Dans l�onglet File, s�lectionner ��Import Project From File System��.
- Cliquez sur le bouton ��Archive�.
- Recherchez le fichier zip MARTINEAU_MATIAS_MIZUNO.zip.
- Une fois le fichier s�lectionn�, cliquer sur le bouton ��Ouvrir � puis ��Finish �.

2. Configuration
Dans la classe ��ConnectionDAO�� :
- final static String URL prend la valeur :  "jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int" si vous travaillez depuis un PC de l��cole. Sinon ne pas modifier.
- final static String LOGIN = "C##BDD9_ (mettez votre position dans la liste d�appel)��
- final static String PASS = "BDD9(mettez votre position dans la liste d�appel) �.
Vous pouvez ensuite lancer le logiciel.
Pour vous connectez en tant que gestionnaire la premi�re fois�:
- Choisissez Gestionnaire dans la fen�tre d�authentification.
- Identifiant�: mail
- Mot de passe�: 1234
A partir du menu du gestionnaire, vous pourrez alors cr�er des �tudiants et des enseignants en choisissant les mots de passe et identifiants. 

Pour vous connectez en tant qu�admiratif la premi�re fois�:
- Choisissez Administratif dans la fen�tre d�Authentification.
- Identifiant�: mail
- Mot de passe�: 1234


3. Ex�cution 

Lancer le fichier UserGUI.java.

