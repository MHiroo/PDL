package model;

/**
 * Classe Etudiant
 * @author Aur�lien
 * @version 1.0
 * */
public class Etudiant extends Personne {
	/** 
	 * reference de l'�tudiant
	 */
	private int groupe;
	/**
	 * fili�re
	 */
	private String filiere;
	
	/**
	 * Constructor
	 * @param id identifiant de l'�tudiant
	 * @param nom nom de l'�tudiant
	 * @param prenom pr�nom de l'�tudiant
	 * @param mail adresse mail de l'�tudiant
	 * @param filiere filiere de l'�tudiant
	 * @param groupe groupe de l'étudiant
	 */
	public Etudiant(int id, String nom, String prenom, String mail, String filiere, int groupe) {
		super(id, nom, prenom, mail);
		this.filiere=filiere;
		this.groupe=groupe;
	}

	/**
	* getter pour l'attribut filiere
	* @return valeur de la filiere
	*/
	public String getFiliere() {
		return tel;
	}
	/**
	 * setter pour l'attribut filiere
	 * @param filiere : nouvelle valeur de la filiere
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	/**
	* getter pour l'attribut groupe
	* @return valeur du groupe
	*/
	public int getGroupe() {
		return groupe;
	}
	/**
	 * setter pour l'attribut groupe
	 * @param groupe : nouvelle valeur du groupe
	 */
	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}

	/**
	 * Affiche les caractéristiques de l'étudiant
	 */
	@Override
	public void display2()
	{
		super.display2();
		System.out.println("filière: "+this.filiere+"  ;groupe: "+this.groupe  );
	}
}