package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table groupe
 * 
 * @author G9 Jaune canaris
 * @version 1.0
 * */
public class GroupeDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public GroupeDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un groupe dans la table groupe.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param groupe l'etudiant a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Groupe_Etudiant groupe) {
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
			ps = con.prepareStatement("INSERT INTO groupeetudiant(idgroupe,num, capacitemax) VALUES( ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setInt(2, groupe.getNum());
			ps.setInt(3, groupe.getcapaciteMax());
			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de groupe existe déjà. Ajout impossible !");
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
	 * Permet de modifier un groupe dans la table groupe.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param groupe le groupe a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Groupe_Etudiant groupe) {
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
			ps = con.prepareStatement("UPDATE groupeetudiant SET capacitemax = ?, num = ? WHERE idgroupe = ?");
			ps.setInt(1, groupe.getcapaciteMax());
			ps.setInt(2, groupe.getNum());
			ps.setInt(3, groupe.getId());

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
	 * Permet de supprimer un groupe par id dans la table etudiant.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du groupe à supprimer
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
			ps = con.prepareStatement("DELETE FROM groupeetudiant WHERE idgroupe = ?");
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
	 * Permet de recuperer un groupe a partir de sa reference
	 * 
	 * @param reference la reference du groupe a recuperer
	 * @return le groupe trouve;
	 * 			null si aucun groupe ne correspond a cette reference
	 */
	public Groupe_Etudiant get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Groupe_Etudiant returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM groupeetudiant WHERE idgroupe = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Groupe_Etudiant(rs.getInt("idgroupe"),
										   rs.getInt("num"),
									       rs.getInt("capacitemax"));
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
	public ArrayList<Groupe_Etudiant> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Groupe_Etudiant> returnValue = new ArrayList<Groupe_Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM groupeetudiant ORDER BY idgroupe");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Groupe_Etudiant(rs.getInt("idgroupe"),
						                     rs.getInt("capacitemax"),
											       rs.getInt("num")));
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
	 * Permet de recuperer tous les etudiants stockes dans la table etudiant
	 * 
	 * @return une ArrayList de etudiant
	 */
	public ArrayList<Integer> getListIdGroupe() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> returnValue = new ArrayList<Integer>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT idGroupe FROM groupeetudiant");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getInt("idgroupe"));
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
	 * ATTENTION : Cette méthode n'a pas vocation à être executée lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les méthodes écrites au-dessus !
	 * 
	 * @param args non utilisés
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 *
	public static void main(String[] args) throws SQLException {
		int returnValue;
		GroupeDAO groupeDAO = new GroupeDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'éviter les collisions avec vos données en prenant de grands ID
		int[] ids = {3, 2, 4};
		// test du constructeur
		Groupe_Etudiant s1 = new Groupe_Etudiant(ids[0],  40, 16);
		Groupe_Etudiant s2 = new Groupe_Etudiant(ids[1],  40, 17);
		Groupe_Etudiant s3 = new Groupe_Etudiant(ids[2],  40, 18);
		// test de la methode add
		returnValue = groupeDAO.add(s1);
		System.out.println(returnValue + " groupe ajoute");
		returnValue = groupeDAO.add(s2);
		System.out.println(returnValue + " groupe ajoute");
		returnValue = groupeDAO.add(s3);
		System.out.println(returnValue + " groupe ajoute");
		System.out.println();
		
		// test de la methode get
		Groupe_Etudiant sg = groupeDAO.get(1);
		// appel implicite de la methode toString de la classe Object (a eviter)
		System.out.println(sg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Groupe_Etudiant> list = groupeDAO.getList();
		for (Groupe_Etudiant s : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			System.out.println(s.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 articles qu'on a créé
		returnValue = 0;
		for (int id : ids) {
//			returnValue = EtudiantDAO.delete(id);
			System.out.println(returnValue + " groupe supprime");
		}
		
		System.out.println();
	}
	*/
}