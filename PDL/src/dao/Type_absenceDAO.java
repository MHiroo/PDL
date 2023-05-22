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
public class Type_absenceDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 * 
	 */
	public Type_absenceDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un type d'absence dans la table type_absence.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param typeAbsence le type d'absence a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(TYPE_ABSENCE typeAbsence) {
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
			ps = con.prepareStatement("INSERT INTO type_absence(idtypeabsence, quota, designation) VALUES( ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setString(2, typeAbsence.getQuota());
			ps.setString(3, typeAbsence.getDesignation());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de type d'absence existe deja. Ajout impossible !");
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
	 * Permet de modifier un type d'absence dans la table type_absence.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param typeAbsence le type d'absence a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(TYPE_ABSENCE typeAbsence) {
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
			ps = con.prepareStatement("UPDATE type_absence SET quota = ?, designation = ? WHERE idtypeabsence = ?");
			ps.setString(1, typeAbsence.getQuota());
			ps.setString(2, typeAbsence.getDesignation());
			ps.setInt(3, typeAbsence.getId());	

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
	 * Permet de supprimer un type d'absence par id dans la table type_absence.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du type d'absence a supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int id) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    int returnValue = 0;

	    try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ID
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID du type d'abence
			ps = con.prepareStatement("DELETE FROM type_absence WHERE idtypeabsence = ?");
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
	 * Permet de recuperer un type d'absence a partir de sa reference
	 * 
	 * @param id la reference du type d'absence a recuperer
	 * @return le type d'absence trouve;
	 * 			null si aucun type d'absence ne correspond a cette reference
	 */
	public TYPE_ABSENCE get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TYPE_ABSENCE returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM type_absence WHERE idtypeabsence = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new TYPE_ABSENCE(rs.getInt("idtypeabsence"),
										   rs.getString("quota"),
									       rs.getString("designation"));
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
	 * Permet de recuperer tous les type d'absences stockes dans la table type_absence
	 * 
	 * @return une ArrayList de type d'absence
	 */
	public ArrayList<TYPE_ABSENCE> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TYPE_ABSENCE> returnValue = new ArrayList<TYPE_ABSENCE>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM type_absence ORDER BY idtypeabsence");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new TYPE_ABSENCE(rs.getInt("idtypeabsence"),
						                     rs.getString("quota"),
											       rs.getString("designation")));
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
	 * ATTENTION : Cette methode n'a pas vocation a� a�tre executee lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les methodes ecrites au-dessus !
	 * 
	 * @param args non utilises
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	
	public static void main(String[] args) throws SQLException {
		int returnValue;
		Type_absenceDAO type_absenceDAO = new Type_absenceDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		TYPE_ABSENCE s1 = new TYPE_ABSENCE(ids[0], "quota",  "designation");
		TYPE_ABSENCE s2 = new TYPE_ABSENCE(ids[0], "quota",  "designation");
		TYPE_ABSENCE s3 = new TYPE_ABSENCE(ids[0], "quota",  "designation");
		// test de la methode add
		returnValue = type_absenceDAO.add(s1);
		System.out.println(returnValue + " type d'absence ajoute");
		returnValue = type_absenceDAO.add(s2);
		System.out.println(returnValue + " type d'absence ajoute");
		returnValue = type_absenceDAO.add(s3);
		System.out.println(returnValue + " type d'absence ajoute");
		System.out.println();
		
		// test de la methode get
		TYPE_ABSENCE sg = type_absenceDAO.get(1);
		// appel implicite de la methode toString de la classe Object (a eviter)
		System.out.println(sg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<TYPE_ABSENCE> list = type_absenceDAO.getList();
		for (TYPE_ABSENCE s : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			System.out.println(s.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 articles qu'on a cree
		returnValue = 0;
		for (int id : ids) {
//			returnValue = EtudiantDAO.delete(id);
			System.out.println(returnValue + " type d'absence supprime");
		}
		
		System.out.println();
	}
}