package dao;

/**
 * Classe d'acces a la base de donnees
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class ConnectionDAO {
	/**
	 * Parametres de connexion a la base de donnees oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	// � utiliser si vous �tes sur une machine personnelle :"jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl"  


	//final static String URL   = "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl" ;
	//sinon :"jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int";m dhote: srvoracledb.intranet.int; nom de service : orcl.intranet.int

	final static String URL   = "jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int" ;
	//sinon :"jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int"; nom dhote: srvoracledb.intranet.int; nom de service : orcl.intranet.int


	//final static String URL   = "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl" ;
	//sinon :"jdbc:oracle:thin:@//srvoracledb.intranet.int:1521/orcl.intranet.int";m dhote: srvoracledb.intranet.int; nom de service : orcl.intranet.int

	
	// � utiliser si vous �tes sur une machine de l'�cole :
	//final static String URL   =  "jdbc:oracle:thin:@oracle.esigelec.fr:1521:orcl";

	final static String LOGIN = "C##BDD9_18";   // remplacer les ********. Exemple C##BDD1_1
	final static String PASS  = "BDD918";   // remplacer les ********. Exemple BDD11
	
	/**
	 * Constructor
	 * 
	 */
	public ConnectionDAO() {
		// chargement du pilote de bases de donnees
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
}
