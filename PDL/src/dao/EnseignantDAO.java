package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table enseignant
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class EnseignantDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public EnseignantDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un enseignant dans la table enseignant.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param enseignant le enseignant a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Enseignant enseignant) {
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
			ps = con.prepareStatement("INSERT INTO enseignant(idEnseignant, nomEnseignant, prenomEnseignant, tel, email, motdepasse) VALUES( ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setString(2, enseignant.getName());
			ps.setString(3, enseignant.getFirstName());
			ps.setString(4, enseignant.getTel());
			ps.setString(5, enseignant.getEmail());
			ps.setString(6, enseignant.getMdp());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de enseignant existe déjà. Ajout impossible !");
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
	 * Permet de modifier un enseignant dans la table enseignant.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param enseignant le enseignant a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Personne enseignant) {
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
			ps = con.prepareStatement("UPDATE enseignant set  nomEnseignant = ?, prenomEnseignant = ?, tel = ?, email = ?, motdepasse = ? WHERE idGst = ?");
			ps.setString(1, enseignant.getName());
			ps.setString(2, enseignant.getFirstName());
			ps.setString(3, enseignant.getTel());
			ps.setString(4, enseignant.getEmail());
			ps.setString(5, enseignant.getMdp());
			ps.setInt(6, enseignant.getId());
			
			

		

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
	 * Permet de supprimer un enseignant par id dans la table enseignant.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du enseignant à supprimer
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
			// le getter permet de recuperer la valeur de l'ID du enseignant
			ps = con.prepareStatement("DELETE FROM enseignant WHERE idEnseignant = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Cet enseignant est utilisé ailleurs, suppression impossible !");
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
	 * Permet de recuperer un enseignant a partir de sa reference
	 * 
	 * @param reference la reference du enseignant a recuperer
	 * @return le enseignant trouve;
	 * 			null si aucun enseignant ne correspond a cette reference
	 */
	public Personne get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Personne returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM enseignant WHERE idEnseignant = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Personne(rs.getInt("idEnseignant"),
									       rs.getString("nomEnseignant"),
									       rs.getString("prenomEnseignant"),
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
	 * Permet de recuperer tous les enseignants stockes dans la table enseignant
	 * 
	 * @return une ArrayList de enseignant
	 */
	public ArrayList<Personne> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Personne> returnValue = new ArrayList<Personne>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM enseignant ORDER BY idEnseignant");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Personne(rs.getInt("idEnseignant"),
											       rs.getString("nomEnseignant"),
											       rs.getString("prenomEnseignant"),
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

	
	
}