package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table planning
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
	 * Permet d'ajouter un planning dans la table planning.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param planning le planning a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Planning planning) {
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
			ps = con.prepareStatement("INSERT INTO planning(idplanning,idenseignant, idcours, date_pln, salle) VALUES( ?, ?, ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setEnseignant(2, planning.getEnseignant());
			ps.setCours(3, planning.getCours());
			ps.setDate(4, planning.getDate());
			ps.setString(5, planning.getSalle());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de planning existe déjà. Ajout impossible !");
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
	 * Permet de modifier un planning dans la table planning.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param planning le planning a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Planning planning) {
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
			ps = con.prepareStatement("UPDATE planning SET idplanning = ?, idenseignant = ?, idcours = ?, date_pln = ?, salle = ? WHERE idplanning = ?");
			ps.setString(1, planning.getSalle());
			ps.setDate(2, planning.getDate());
			ps.setCours(3, cours.getCours());
			ps.setEnseignant(4, enseignant.getEnseignant());
			ps.setInt(5, planning.getId());	

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
	 * Permet de supprimer un planning par id dans la table planning.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du planning à supprimer
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
			// le getter permet de recuperer la valeur de l'ID du planning
			ps = con.prepareStatement("DELETE FROM planning WHERE idplanning = ?");
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
	 * Permet de recuperer un planning a partir de son id
	 * 
	 * @param id l'id du planning a recuperer
	 * @return le planning trouve;
	 * 			null si aucun planning ne correspond a cette reference
	 */
	public Planning get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Planning returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM planning WHERE idplanning = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Planning(rs.getInt("idplanning"),
										   rs.getInt("idenseignant"),
									       rs.getString("idcours"),
									       rs.getString("date_pln"),
									       rs.getString("salle"));
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
	 * Permet de recuperer tous les planning stockes dans la table planning
	 * 
	 * @return une ArrayList de planning
	 */
	public ArrayList<Planning> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Planning> returnValue = new ArrayList<Planning>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM planning ORDER BY idplanning");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Planning(rs.getInt("idplanning"),
						                     rs.getInt("idenseignant"),
											       rs.getString("idcours"),
											       rs.getString("date_pln"),
											       rs.getString("salle")));
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
	 */
	public static void main(String[] args) throws SQLException {
		int returnValue;
		PlanningDAO planningdao = new PlanningDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'éviter les collisions avec vos données en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		Planning s1 = new Planning(ids[0],  1,  1,  01/01/2023,  "D1275");
		Planning s2 = new Planning(ids[1],  1,  1,  01/01/2023,  "D1275");
		Planning s3 = new Planning(ids[2],  1,  1,  01/01/2023,  "D1275");
		// test de la methode add
		returnValue = PlanningDAO.add(s1);
		System.out.println(returnValue + " planning ajoute");
		returnValue = PlanningDAO.add(s2);
		System.out.println(returnValue + " planning ajoute");
		returnValue = PlanningDAO.add(s3);
		System.out.println(returnValue + " planning ajoute");
		System.out.println();
		
		// test de la methode get
		Planning sg = PlanningDAO.get(1);
		// appel implicite de la methode toString de la classe Object (a eviter)
		System.out.println(sg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Planning> list = PlanningDAO.getList();
		for (Planning s : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			System.out.println(s.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 articles qu'on a créé
		returnValue = 0;
		for (int id : ids) {
//			returnValue = PlanningDAO.delete(id);
			System.out.println(returnValue + " planning supprime");
		}
		
		System.out.println();
	}
	
}