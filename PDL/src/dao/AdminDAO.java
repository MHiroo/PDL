package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table administratif
 * 
 * @author ESIGELEC - TIC Department
 * @version 2.0
 * */
public class AdminDAO extends ConnectionDAO {
	/**
	 * Constructor
	 * 
	 */
	public AdminDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un administratif dans la table administratif.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param administratif le administratif a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Personne administratif) {
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
			ps = con.prepareStatement("INSERT INTO administratif(idAdmin, nomAdmin, prenomAdmin, tel, email, motdepasse) VALUES( ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getList().get(getList().size()-1).getId()+1);
			ps.setString(2, administratif.getName());
			ps.setString(3, administratif.getFirstName());
			ps.setString(4, administratif.getTel());
			ps.setString(5, administratif.getEmail());
			ps.setString(6, administratif.getMdp());

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de administratif existe déjà. Ajout impossible !");
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
	 * Permet de modifier un administratif dans la table administratif.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param administratif le administratif a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Personne administratif) {
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
			ps = con.prepareStatement("UPDATE administratif set  nomAdmin = ?, prenomAdmin = ?, tel = ?, email = ?, motdepasse = ? WHERE idAdmin = ?");
			ps.setString(1, administratif.getName());
			ps.setString(2, administratif.getFirstName());
			ps.setString(3, administratif.getTel());
			ps.setString(4, administratif.getEmail());
			ps.setString(5, administratif.getMdp());
			ps.setInt(6, administratif.getId());
			
			

		

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
	 * Permet de supprimer un administratif par id dans la table administratif.
	 * Si ce dernier possede des articles, la suppression n'a pas lieu.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id l'id du administratif à supprimer
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
			// le getter permet de recuperer la valeur de l'ID du administratif
			ps = con.prepareStatement("DELETE FROM administratif WHERE idAdmin = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-02292"))
				System.out.println("Ce administratif est utilisé ailleurs, suppression impossible !");
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
	 * Permet de recuperer un administratif a partir de sa reference
	 * 
	 * @param reference la reference du administratif a recuperer
	 * @return le administratif trouve;
	 * 			null si aucun administratif ne correspond a cette reference
	 */
	public Personne get(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Personne returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM administratif WHERE idAdmin = ?");
			ps.setInt(1, id);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Personne(rs.getInt("idAdmin"),
									       rs.getString("nomAdmin"),
									       rs.getString("prenomAdmin"),
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
	 * Permet de recuperer tous les administratifs stockes dans la table administratif
	 * 
	 * @return une ArrayList de administratif
	 */
	public ArrayList<Personne> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Personne> returnValue = new ArrayList<Personne>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM administratif ORDER BY idAdmin");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Personne(rs.getInt("idAdmin"),
											       rs.getString("nomAdmin"),
											       rs.getString("prenomAdmin"),
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
	 * Permet d'authentifier un administratif par son email et son mot de passe dans la table administratif.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param email l'email de l'administratif 
	 * @param mdp mot de passe de l'administratif 
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
			ps = con.prepareStatement("SELECT * FROM administratif WHERE email like ? and motdepasse like ?");
			ps.setString(1, email);
			ps.setString(2, mdp);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Personne(rs.getInt("idadmin"),
									       rs.getString("nomadmin"),
									       rs.getString("prenomadmin"),
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

	
	
}