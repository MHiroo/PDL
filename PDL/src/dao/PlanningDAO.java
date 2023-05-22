package dao;
import java.sql.*;
import java.time.LocalDate;
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
			ps = con.prepareStatement("INSERT INTO planning(idplanning,idgroupe, idenseignant, idcours, date_pln, salle, duree, heure, lien) VALUES( ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), ?, ?, ?,?)");
			ps.setInt(1, getListPlanning().get(getListPlanning().size()-1).getId()+1);
			ps.setInt(2, planning.getIdGroupe());
			ps.setInt(3, planning.getIdEnseignant());
			ps.setInt(4, planning.getIdCours());
			//ps.setString(5,"19-05-2023" );
			ps.setDate(5, Date.valueOf(planning.getDate()));
			ps.setString(6, planning.getSalle());
			ps.setDouble(7, planning.getDuree());
			ps.setDouble(8, planning.getHeure());
			ps.setString(9, planning.getLien());
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
			ps = con.prepareStatement("UPDATE planning SET idgroupe = ?, idenseignant = ?, idcours = ?, date_pln = TO_DATE(?,'DD-MM-YYYY'), salle = ?, duree = ?, heure = ? , lien = ? WHERE idplanning = ?");
			ps.setInt(1, planning.getIdGroupe());
			ps.setInt(2, planning.getIdEnseignant());
			ps.setInt(3, planning.getIdCours());
			ps.setDate(4,Date.valueOf(planning.getDate()));
			ps.setString(5, planning.getSalle());
			ps.setDouble(6, planning.getDuree());
			ps.setDouble(7, planning.getHeure());
			ps.setString(8, planning.getLien());
			ps.setInt(9, planning.getId());	

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
			// le getter permet de recuperer la valeur de l'ID du fournisseur
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
	 * Permet de recuperer un planning a partir de sa reference
	 * 
	 * @param id la reference du planning a recuperer
	 * @return le planning trouve;
	 * 			null si aucun planning ne correspond a cette reference
	 */
	public Planning getPlanning(int id) {
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
				java.sql.Date sqlDate = rs.getDate("date_pln");
				LocalDate localDate = sqlDate.toLocalDate();
				returnValue = new Planning(rs.getInt("idplanning"),
										   rs.getInt("idgroupe"),
									       rs.getInt("idEnseignant"),
									       rs.getInt("idCours"),
									       localDate,
									       rs.getString("salle"),
										   rs.getDouble("duree"),
										   rs.getDouble("heure"),
										   rs.getString("lien"));
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
	 * Permet de recuperer un planning a partir de sa reference
	 * 
	 * @param id la reference du planning a recuperer
	 * @return le planning trouve;
	 * 			null si aucun planning ne correspond a cette reference
	 */
	public String getLien(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String returnValue = "";
		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT lien FROM planning WHERE idplanning = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				
				returnValue = rs.getString("lien");
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
	 * Permet de recuperer tous les plannings stockes dans la table planning
	 * 
	 * @return une ArrayList de planning
	 */
	public ArrayList<Planning> getListPlanning() {
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
				java.sql.Date sqlDate = rs.getDate("date_pln");
				LocalDate localDate = sqlDate.toLocalDate();
				returnValue.add(new Planning(rs.getInt("idplanning"),
						   rs.getInt("idgroupe"),
					       rs.getInt("idEnseignant"),
					       rs.getInt("idCours"),
					       localDate,
					       rs.getString("salle"),
						   rs.getDouble("duree"),
						   rs.getDouble("heure"),
						   rs.getString("lien")));
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
	 * Permet de recuperer une liste de planning du jour a partir du groupe et de la date
	 * 
	 * @param idGroupe 
	 * @param date 
	 * @return le planning trouve;
	 * 			null si aucun planning ne correspond a cette reference
	 */
	public ArrayList<Planning> getPlanningJour(int idGroupe, LocalDate date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Planning> returnValue = new ArrayList<Planning>();

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM planning WHERE (idGroupe = ? AND date_pln = (TO_DATE(?,'DD-MM-YYYY'))) ORDER BY heure");
			ps.setInt(1, idGroupe);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			ps.setDate(2, (Date) sqlDate);
			//ps.setDate(2, date);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			while (rs.next()) {
				LocalDate localDate = rs.getDate("date_pln").toLocalDate();
				returnValue.add(new Planning(rs.getInt("idplanning"),
						   rs.getInt("idgroupe"),
					       rs.getInt("idEnseignant"),
					       rs.getInt("idCours"),
					       localDate,
					       rs.getString("salle"),
						   rs.getDouble("duree"),
						   rs.getDouble("heure"),
						   rs.getString("lien")));
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
	 * Permet de recuperer une liste de planning du jour a partir de l'id enseignant et de la date
	 * 
	 * @param idEnseignant 
	 * @param date 
	 * @return le planning trouve;
	 * 			null si aucun planning ne correspond a cette reference
	 */
	public ArrayList<Planning> getPlanningJourEnseignant(int idEnseignant, LocalDate date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Planning> returnValue = new ArrayList<Planning>();

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM planning WHERE (idEnseignant = ? AND date_pln = (TO_DATE(?,'DD-MM-YYYY'))) ORDER BY heure");
			ps.setInt(1, idEnseignant);
			java.sql.Date sqlDate = java.sql.Date.valueOf(date);
			ps.setDate(2, (Date) sqlDate);
			//ps.setDate(2, date);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			while (rs.next()) {
				LocalDate localDate = rs.getDate("date_pln").toLocalDate();
				returnValue.add(new Planning(rs.getInt("idplanning"),
						   rs.getInt("idgroupe"),
					       rs.getInt("idEnseignant"),
					       rs.getInt("idCours"),
					       localDate,
					       rs.getString("salle"),
						   rs.getDouble("duree"),
						   rs.getDouble("heure"),
						   rs.getString("lien")));
						   
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