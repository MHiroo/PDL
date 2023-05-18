package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table cours
 * 
 * @author G9 Jaune canaris
 * @version 1.0
 * */
public class CoursDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 * 
	 */
	public CoursDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un cours dans la table cours.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param cours l'cours a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Cours cours) {
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
			ps = con.prepareStatement("INSERT INTO Cours(idcours, nomCours, masseHoraire, masseHoraireAmphi, masseHoraireTD, masseHoraireTP, masseHoraireExam) VALUES( ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setString(2, cours.getNom());
			ps.setInt(3, cours.getMasseHoraire());
			ps.setInt(4, cours.getRepNbrHAmphi());
			ps.setInt(5, cours.getRepNbrHTD());
			ps.setInt(6, cours.getRepNbrHTP());
			ps.setInt(7, cours.getRepNbrHExam());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de cours existe déjà. Ajout impossible !");
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
	 * Permet de modifier un cours dans la table cours.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param cours le cours a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Cours cours) {
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
			ps = con.prepareStatement("UPDATE cours SET nomCours = ?, masseHoraire = ?, masseHoraireAmphi = ?, masseHoraireTD = ?, masseHoraireTP = ?, masseHoraireExam = ? WHERE idcours = ?");
			ps.setString(1, cours.getNom());
			ps.setInt(2, cours.getMasseHoraire());
			ps.setInt(3, cours.getRepNbrHAmphi());
			ps.setInt(4, cours.getRepNbrHTD());
			ps.setInt(5, cours.getRepNbrHTP());
			ps.setInt(6, cours.getRepNbrHExam());
			ps.setInt(7, cours.getId());	

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
	 * Permet de supprimer un cours par id dans la table cours.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du cours à supprimer
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
			// le getter permet de recuperer la valeur de l'ID du cours
			ps = con.prepareStatement("DELETE FROM cours WHERE idCours = ?");
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
	 * Permet de recuperer un cours a partir de sa reference
	 * 
	 * @param reference la reference du cours a recuperer
	 * @return le cours trouve;
	 * 			null si aucun cours ne correspond a cette reference
	 */
	public Cours get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cours returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM cours WHERE idcours = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Cours(rs.getInt("idcours"),
										   rs.getString("nomcours"),
									       rs.getInt("massehoraire"),
									       rs.getInt("massehoraireAmphi"),
									       rs.getInt("massehoraireTD"),
									       rs.getInt("massehoraireTP"),
										   rs.getInt("massehoraireExam"));
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
	 * Permet de recuperer un idCours a partir de son nom
	 * 
	 * @param reference la reference du enseignant a recuperer
	 * @return le enseignant trouve;
	 * 			null si aucun enseignant ne correspond a cette reference
	 */
	public int getId(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT idCours FROM cours WHERE id = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue =rs.getInt("idCours");
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
	 * Permet de recuperer le nom d'un cours a partir de sa reference
	 * 
	 * @param reference la reference du cours a recuperer
	 * @return le cours trouve;
	 * 			null si aucun cours ne correspond a cette reference
	 */
	public String getNomCours(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nomcours FROM cours WHERE idcours IN (?)");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = rs.getString("nomcours");
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
	 * Permet de recuperer l'id d'un cours a partir de son nom
	 * 
	 * @param reference la reference du cours a recuperer
	 * @return le cours trouve;
	 * 			null si aucun cours ne correspond a cette reference
	 */
	public int getIdCours(String nomCours) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT idCours FROM cours WHERE nomcours IN (?)");
			ps.setString(1, nomCours);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = rs.getInt("idCours");
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
	 * Permet de recuperer tous les cours stockes dans la table cours
	 * 
	 * @return une ArrayList de cours
	 */
	public ArrayList<Cours> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Cours> returnValue = new ArrayList<Cours>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM cours ORDER BY idcours");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Cours(rs.getInt("idcours"),
						                     rs.getString("nomcours"),
											       rs.getInt("massehoraire"),
											       rs.getInt("massehoraireAmphi"),
											       rs.getInt("massehoraireTD"),
											       rs.getInt("massehoraireTP"),
												   rs.getInt("massehoraireExam")));
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
	 * Permet de recuperer tous les noms de cours stockes dans la table cours
	 * 
	 * @return une ArrayList de noms de cours
	 */
	public ArrayList<String> getListeNomCours(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nomCours FROM COURS WHERE (idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Cours WHERE (idcours= ? ))=Planning.idGroupe))");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("nomCours"));
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
	 * Permet de recuperer tous les masses horaires stockes dans la table cours
	 * 
	 * @return une ArrayList de masse Horaire
	 */
	public ArrayList<String> getMasseHoraire(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT masseHoraire FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Cours WHERE (idcours= ? ))=Planning.idGroupe)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("masseHoraire"));
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
	 * Permet de recuperer tous les Repartition horaires en amphi stockes dans la table cours
	 * 
	 * @return une ArrayList de Repartition de masse Horaire amphi
	 */
	public ArrayList<String> getRepartitionAmphi(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT masseHoraireAmphi FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM cours WHERE (idcours= ? ))=Planning.idGroupe)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("masseHoraireAmphi"));
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
	 * Permet de recuperer tous les Repartition horaires en TD stockes dans la table cours
	 * 
	 * @return une ArrayList de Repartition de masse Horaire TD
	 */
	public ArrayList<String> getRepartitionTD(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT masseHoraireTD FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM cours WHERE (idcours= ? ))=Planning.idGroupe)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("masseHoraireTD"));
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
	 * Permet de recuperer tous les Repartition horaires en TP stockes dans la table cours
	 * 
	 * @return une ArrayList de Repartition de masse Horaire TP
	 */
	public ArrayList<String> getRepartitionTP(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT masseHoraireTP FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM cours WHERE (idcours= ? ))=Planning.idGroupe)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("masseHoraireTP"));
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
	 * Permet de recuperer tous les Repartition horaires en DS stockes dans la table cours
	 * 
	 * @return une ArrayList de Repartition de masse Horaire DS
	 */
	public ArrayList<String> getRepartitionExam(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT masseHoraireExam FROM Cours WHERE idCours=(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM cours WHERE (idcours= ? ))=Planning.idGroupe)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("masseHoraireExam"));
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
	
	public static void main(String[] args) throws SQLException {
		int returnValue;
		CoursDAO coursDAO = new CoursDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		Cours s1 = new Cours(ids[0],  "nom",  1,  1,  1,  1, 4);
		Cours s2 = new Cours(ids[1],  "nom",  1,  1,  1,  1, 4);
		Cours s3 = new Cours(ids[2],  "nom",  1,  1,  1,  1, 4);
		// test de la methode add
		returnValue = coursDAO.add(s1);
		System.out.println(returnValue + " cours ajoute");
		returnValue = coursDAO.add(s2);
		System.out.println(returnValue + " cours ajoute");
		returnValue = coursDAO.add(s3);
		System.out.println(returnValue + " cours ajoute");
		System.out.println();
		
		// test de la methode get
		Cours sg = coursDAO.get(1);
		// appel implicite de la methode toString de la classe Object (a eviter)
		System.out.println(sg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Cours> list = coursDAO.getList();
		for (Cours s : list) {
			// appel explicite de la methode toString de la classe Object (a privilegier)
			System.out.println(s.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 articles qu'on a cree
		returnValue = 0;
		for (int id : ids) {
		//returnValue = EtudiantDAO.delete(id);
			System.out.println(returnValue + " cours supprime");
		}
		
		System.out.println();
	}	

}