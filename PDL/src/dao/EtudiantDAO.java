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
public class EtudiantDAO extends ConnectionDAO {
	
	/**
	 * Constructor
	 * 
	 */
	public EtudiantDAO() {
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
			ps = con.prepareStatement("INSERT INTO etudiant(idetud,idgroupe, nomEtudiant, prenomEtudiant, filiere, email, motdepasse) VALUES( etudiant_seq.nextval, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, etudiant.getGroupe());
			ps.setString(2, etudiant.getName());
			ps.setString(3, etudiant.getFirstName());
			ps.setString(4, etudiant.getFiliere());
			ps.setString(5, etudiant.getEmail());
			ps.setString(6, etudiant.getMdp());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de etudiant existe deja�. Ajout impossible !");
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
	 * @param id l'id du etudiant a� supprimer
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
	 * @param id la reference du etudiant a recuperer
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
	 * Permet de recuperer un in du groupe a partir de la reference de l'etudiant
	 * 
	 * @param id la reference du etudiant a recuperer
	 * @return le etudiant trouve;
	 * 			null si aucun etudiant ne correspond a cette reference
	 */
	public int getIdGroupe(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT idGroupe FROM etudiant WHERE idetud = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = rs.getInt("idgroupe");
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
	 * @return retourne l'etudiant qui se connecte
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
	
	public ArrayList<Etudiant> getEtudiantGroupe(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Etudiant> returnValue = new ArrayList<Etudiant>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE idgroupe = ?");
			ps.setInt(1, id);

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Etudiant(rs.getInt("idetud"),
											       rs.getString("nomEtudiant"),
											       rs.getString("prenomEtudiant")));
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
	public ArrayList<String> getNomCours(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nomCours FROM COURS WHERE idCours IN (SELECT idCours FROM Planning WHERE (Planning.idGroupe=(SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))))");
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
			ps = con.prepareStatement("SELECT masseHoraire FROM Cours WHERE idCours IN (SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))=Planning.idGroupe)");
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
			ps = con.prepareStatement("SELECT masseHoraireAmphi FROM Cours WHERE idCours IN(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))=Planning.idGroupe)");
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
			ps = con.prepareStatement("SELECT masseHoraireTD FROM Cours WHERE idCours IN(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))=Planning.idGroupe)");
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
			ps = con.prepareStatement("SELECT masseHoraireTP FROM Cours WHERE idCours IN(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))=Planning.idGroupe)");
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
			ps = con.prepareStatement("SELECT masseHoraireExam FROM Cours WHERE idCours IN(SELECT idCours FROM Planning WHERE (SELECT idGroupe FROM Etudiant WHERE (idEtud= ? ))=Planning.idGroupe)");
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
	/**
	 * Permet de recuperer tous les noms d'enseignants du planning de l'eleve stockes dans la table enseignant a partir de l'id de l'eleve
	 * 
	 * @return une ArrayList de noms d'enseignants
	 */
	public ArrayList<String> getNomEnseignant(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT Enseignant.nomEnseignant FROM Enseignant INNER JOIN Planning ON Enseignant.idEnseignant = Planning.idEnseignant INNER JOIN Etudiant ON Planning.idGroupe = Etudiant.idGroupe WHERE Etudiant.idEtud = ?");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("nomEnseignant"));
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
	 * Permet de recuperer tous les date stockes dans la table Absence ou AbsenceDistanciel
	 * 
	 * @return une ArrayList de date
	 */
	public ArrayList<Date> getDateAbs(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Date> returnValue = new ArrayList<Date>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT date_abs FROM Absence WHERE (idEtud= ?)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getDate("date_abs"));
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
	 * Permet de recuperer tous les noms de cours o� l'�l�ve est absent stockes dans la table  Cours
	 * 
	 * @return une ArrayList de noms cours
	 */
	public ArrayList<String> getNomCoursAbs(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nomCours FROM Cours WHERE (idCours  IN(SELECT idCours FROM Absence WHERE (idEtud= ?)))");
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
	 * Permet de recuperer tous les heure de debut de cours o� l'�l�ve est absent stockes dans la table  absence
	 * 
	 * @return une ArrayList de heure cours
	 */
	public ArrayList<Time> getHeureDebut(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Time> returnValue = new ArrayList<Time>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT heureDebut FROM Absence WHERE (idEtud= ?)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getTime("heureDebut"));
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
	 * Permet de recuperer tous les nombre d'heures par s�ance o� l'�l�ve est absent stockes dans la table  absence
	 * 
	 * @return une ArrayList de noms cours
	 */
	public ArrayList<Integer> getHeureAbs(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> returnValue = new ArrayList<Integer>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT nbrdheure FROM Absence WHERE (idEtud= ?)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getInt("nbrdheure"));
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
	 * Permet de recuperer tous les Statuts des absences stockes dans la table  estAbs
	 * 
	 * @return une ArrayList de Statuts
	 */
	public ArrayList<String> getStatutAbs(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();
		
		
		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT statut FROM Absence WHERE (idEtud= ?)");
			ps.setInt(1, id);
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(rs.getString("statut"));
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
	/**
	public static void main(String[] args) throws SQLException {
		int returnValue;
		EtudiantDAO EtudiantDAO = new EtudiantDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
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
		// On supprime les 3 articles qu'on a cree
		returnValue = 0;
		for (int id : ids) {
//			returnValue = EtudiantDAO.delete(id);
			System.out.println(returnValue + " etudiant supprime");
		}
		
		System.out.println();
	}
	*/
}