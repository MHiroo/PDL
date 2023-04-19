package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table gestionnaire
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class GestionnaireDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public GestionnaireDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un gestionnaire dans la table gestionnaire.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param gestionnaire le gestionnaire a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Personne gestionnaire) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("INSERT INTO gestionnaire(idGst, nomGst, prenomGst, tel, email, motdepasse) VALUES( ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setString(2, gestionnaire.getName());
			ps.setString(3, gestionnaire.getFirstName());
			ps.setString(4, gestionnaire.getTel());
			ps.setString(5, gestionnaire.getEmail());
			ps.setString(6, gestionnaire.getMdp());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de gestionnaire existe déjà. Ajout impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * Permet de modifier un gestionnaire dans la table gestionnaire.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param gestionnaire le gestionnaire a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Personne gestionnaire) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans la modification.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("UPDATE gestionnaire set  nomGst = ?, prenomGst = ?, tel = ?, email = ?, motdepasse = ? WHERE idGst = ?");
			ps.setString(1, gestionnaire.getName());
			ps.setString(2, gestionnaire.getFirstName());
			ps.setString(3, gestionnaire.getTel());
			ps.setString(4, gestionnaire.getEmail());
			ps.setString(5, gestionnaire.getMdp());
			ps.setInt(6, gestionnaire.getId());
			
			

		

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * Permet de supprimer un gestionnaire par id dans la table gestionnaire.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du gestionnaire à supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ID
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID du gestionnaire
			ps = con.prepareStatement("DELETE FROM gestionnaire WHERE idGst = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Ce gestionnaire est utilisé ailleurs, suppression impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}


	/**
	 * Permet de recuperer un gestionnaire a partir de sa reference
	 * 
	 * @param reference la reference du gestionnaire a recuperer
	 * @return le gestionnaire trouve;
	 * 			null si aucun gestionnaire ne correspond a cette reference
	 */
	public Personne get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Personne returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM gestionnaire WHERE idGst = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Personne(rs.getInt("idGst"),
									       rs.getString("nomGst"),
									       rs.getString("prenomGst"),
									       rs.getString("tel"),
									       rs.getString("email"),
										   rs.getString("motdepasse"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * Permet de recuperer tous les gestionnaires stockes dans la table gestionnaire
	 * 
	 * @return une ArrayList de gestionnaire
	 */
	public ArrayList<Personne> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Personne> returnValue = new ArrayList<Personne>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM gestionnaire ORDER BY idGst");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Personne(rs.getInt("idGst"),
											       rs.getString("nomGst"),
											       rs.getString("prenomGst"),
											       rs.getString("tel"),
											       rs.getString("email"),
												   rs.getString("motdepasse")));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	/**
	 * Permet d'authentifier un gestionnaire par son email et son mot de passe dans la table gestionnaire.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param email l'email de l'gestionnaire 
	 * @param mdp mot de passe de l'gestionnaire 
	 * @return retourne l'étudiant qui se connecte
	 */
	public Personne signIn(String email, String mdp) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Personne returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM gestionnaire WHERE email like ? and motdepasse like ?");
			ps.setString(1, email);
			ps.setString(2, mdp);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Personne(rs.getInt("idgst"),
									       rs.getString("nomGst"),
									       rs.getString("prenomGst"),
									       rs.getString("email"),
										   rs.getString("motdepasse"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	
}