package model;

/**
 * Classe Enseignant
 * @author Hiroo
 * @version 1.0
 * */
public class Enseignant extends Personne {
	
	
	/**
	 * Constructor
	 * @param id identifiant de l'enseignant
	 * @param nom nom de l'enseignant
	 * @param prenom pr�nom de l'enseignant
	 * @param tel téléphone de l'enseignant
	 * @param mail adresse mail de l'enseignant
	 */
	public Enseignant(int id, String nom, String prenom, String mail, String tel) {
		super(id, nom, prenom, mail,tel);
	}
	
	/**
	 * Affiche les caractéristiques de l'enseignant
	 */
	@Override
	public void display()
	{
		super.display();
	}
	

}