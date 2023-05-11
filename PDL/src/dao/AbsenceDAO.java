package dao;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import model.*;
import java.util.*;
/**
 * Classe d'acces aux donnees contenues dans la table etudiant
 * 
 * @author G9 Jaune canaris
 * @version 1.0
 * */
public class AbsenceDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 * 
	 */
	public AbsenceDAO() {
		super();
	}

	/**
	 * Permet d'ajouter une absence dans la table absence.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param absence l'absence a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Absence absence) {
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
			ps = con.prepareStatement("INSERT INTO absence(idabs, idetud, idcours, nbrdheure, date_abs, statut) VALUES( ?, ?, ?, ?, ?,?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setInt(2, absence.getIdEtud());
			ps.setInt(3, absence.getIdCours());
			ps.setInt(4, absence.getNbHeure());
			ps.setDate(5, absence.getDate());
			ps.setString(6, absence.getStatut());
			

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
	 * Permet de modifier uneabsence dans la table absence.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param absence labsence a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Absence absence) {
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
			ps = con.prepareStatement("UPDATE absence SET idetud = ?, idcours = ?, heureDebut = ?, nbrdheure = ?, date_abs = ?, statut = ? WHERE idabs = ?");
			ps.setInt(1, absence.getIdEtud());
			ps.setInt(2, absence.getIdCours());
			ps.setTime(3, absence.getHeureDebut());	
			ps.setInt(4, absence.getNbHeure());	
			ps.setDate(5, absence.getDate());
			ps.setString(6, absence.getStatut());
			ps.setInt(7, absence.getId());
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
	 * Permet de supprimer une absence par id dans la table absence.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id de l'absence a supprimer
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
			// le getter permet de recuperer la valeur de l'ID de l'absence
			ps = con.prepareStatement("DELETE FROM absence WHERE idabs = ?");
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
	 * Permet de recuperer une absence a partir de sa reference
	 * 
	 * @param reference la reference de l'absence a recuperer
	 * @return l'absence trouve;
	 * 			null si aucune absence ne correspond a cette reference
	 */
	public Absence get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Absence returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM absence WHERE idabs = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Absence(rs.getInt("idabs"),
										   rs.getInt("idetud"),
									       rs.getInt("idcours"),
									       rs.getTime("heureDebut"),
									       rs.getInt("nbHeure"),
									       rs.getDate("date"),
									       rs.getString("statut"));
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
	 * Permet de recuperer toutes les absences stockes dans la table absence
	 * 
	 * @return une ArrayList d'absence
	 */
	public ArrayList<Absence> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Absence> returnValue = new ArrayList<Absence>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM absence ORDER BY idabs");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Absence(rs.getInt("idabs"),
						   rs.getInt("idetud"),
					       rs.getInt("idcours"),
					       rs.getTime("heureDebut"),
					       rs.getInt("nbHeure"),
					       rs.getDate("date"),
					       rs.getString("statut")));
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
	 * Permet de modifier uneabsence dans la table absence.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param absence labsence a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int setStatut(int idCours,Time heureDebut) {
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
			ps = con.prepareStatement("UPDATE absence SET statut = 'En verification' WHERE (idCours=? AND heureDebut=TO_DATE(?, 'HH24:MI:SS'))");
			
			ps.setInt(1,idCours);
			ps.setTime(2, heureDebut);
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
	 * ATTENTION : Cette methode n'a pas vocation a� a�tre executee lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les methodes ecrites au-dessus !
	 * 
	 * @param args non utilises
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	/**
	public static void main(String[] args) throws SQLException {
		int returnValue;
		AbsenceDAO absenceDAO = new AbsenceDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		Date date = new Date();
		Absence s1 = new Absence(ids[0], 1, 1, date, "12/10/2002" );
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
	}*/
}