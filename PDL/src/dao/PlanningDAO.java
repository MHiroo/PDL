package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table etudiant
 * 
 * @author G9 Jaune canaris
 * @version 1.0
 * */
public class PlanningDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public PlanningDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un etudiant dans la table etudiant.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param etudiant l'etudiant a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Etudiant etudiant) {
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
			ps = con.prepareStatement("INSERT INTO etudiant(idetud,idgroupe, nomEtudiant, prenomEtudiant, filiere, email, motdepasse) VALUES( ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setInt(2, etudiant.getGroupe());
			ps.setString(3, etudiant.getName());
			ps.setString(4, etudiant.getFirstName());
			ps.setString(5, etudiant.getFiliere());
			ps.setString(6, etudiant.getEmail());
			ps.setString(7, etudiant.getMdp());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de etudiant existe déjà. Ajout impossible !");
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
	 * Permet de modifier un etudiant dans la table etudiant.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param etudiant le etudiant a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Etudiant etudiant) {
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
			ps = con.prepareStatement("UPDATE etudiant SET idgroupe = ?, nomEtudiant = ?, prenomEtudiant = ?, filiere = ?, email = ?, motdepasse = ? WHERE idetud = ?");
			ps.setInt(1, etudiant.getGroupe());
			ps.setString(2, etudiant.getName());
			ps.setString(3, etudiant.getFirstName());
			ps.setString(4, etudiant.getFiliere());
			ps.setString(5, etudiant.getEmail());
			ps.setString(6, etudiant.getMdp());
			ps.setInt(7, etudiant.getId());	

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
	 * Permet de supprimer un etudiant par id dans la table etudiant.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du etudiant à supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int id) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int returnValue = 0;

	    try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ID
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID du fournisseur
			ps = con.prepareStatement("DELETE FROM etudiant WHERE idetud = ?");
			ps.setInt(1, id);

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
	 * Permet de recuperer un etudiant a partir de sa reference
	 * 
	 * @param reference la reference du etudiant a recuperer
	 * @return le etudiant trouve;
	 * 			null si aucun etudiant ne correspond a cette reference
	 */
	public Etudiant get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE idetud = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idetud"),
										   rs.getInt("idgroupe"),
									       rs.getString("nomEtudiant"),
									       rs.getString("prenomEtudiant"),
									       rs.getString("filiere"),
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
	 * Permet de recuperer tous les etudiants stockes dans la table etudiant
	 * 
	 * @return une ArrayList de etudiant
	 */
	public ArrayList<Etudiant> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant ORDER BY idetud");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idetud"),
						                     rs.getInt("idgroupe"),
											       rs.getString("nomEtudiant"),
											       rs.getString("prenomEtudiant"),
											       rs.getString("filiere"),
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
	 * Permet d'authentifier un etudiant par son email et son mot de passe dans la table etudiant.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param email l'email de l'etudiant 
	 * @param mdp mot de passe de l'etudiant 
	 * @return retourne l'étudiant qui se connecte
	 */
	public Etudiant signIn(String email, String mdp) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE email like ? and motdepasse like ?");
			ps.setString(1, email);
			ps.setString(2, mdp);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Etudiant(rs.getInt("idetud"),
										   rs.getInt("idgroupe"),
									       rs.getString("nomEtudiant"),
									       rs.getString("prenomEtudiant"),
									       rs.getString("filiere"),
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
	 * ATTENTION : Cette méthode n'a pas vocation à être executée lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les méthodes écrites au-dessus !
	 * 
	 * @param args non utilisés
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {
		int returnValue;
		PlanningDAO EtudiantDAO = new PlanningDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'éviter les collisions avec vos données en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		Etudiant s1 = new Etudiant(ids[0],  1,  "nom",  "prenom",  "filiere",  "mail", "mdp");
		Etudiant s2 = new Etudiant(ids[1],  1,  "nom",  "prenom",  "filiere",  "mail", "mdp");
		Etudiant s3 = new Etudiant(ids[2],  1,  "nom",  "prenom",  "filiere",  "mail", "mdp");
		// test de la methode add
		returnValue = EtudiantDAO.add(s1);
		System.out.println(returnValue + " etudiant ajoute");
		returnValue = EtudiantDAO.add(s2);
		System.out.println(returnValue + " etudiant ajoute");
		returnValue = EtudiantDAO.add(s3);
		System.out.println(returnValue + " etudiant ajoute");
		System.out.println();
		
		// test de la methode get
		Etudiant sg = EtudiantDAO.get(1);
		// appel implicite de la methode toString de la classe Object (a eviter)
		System.out.println(sg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Etudiant> list = EtudiantDAO.getList();
		for (Etudiant s : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			System.out.println(s.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 articles qu'on a créé
		returnValue = 0;
		for (int id : ids) {
//			returnValue = EtudiantDAO.delete(id);
			System.out.println(returnValue + " etudiant supprime");
		}
		
		System.out.println();
	}
	
}