package model;

/**
 * Classe Personne
 * @author Aurélien
 * @version 1.0
 * */
public class Personne {
	/** 
	 * reference de la personne
	 */
	protected int id;
	/**
	 * nom
	 */
	protected String nom;
	/**
	 * prénom
	 */
	protected String prenom;
	/**
	 * mail
	 */
	protected String mail;
	/**
	 * tel
	 */
	protected String tel;
	
	/**
	 * Constructor
	 * @param id identifiant de la personne
	 * @param nom nom de la personne
	 * @param prenom prénom de la personne
	 * @param mail adresse mail de la personne
	 * @param tel numéro de téléphone de la personne
	 */
	public Personne(int id, String nom, String prenom, String mail, String tel) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
		this.tel=tel;
	}
	public Personne(int id, String nom, String prenom, String mail) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
	}
	
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference Personne
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut nom
	 * @return valeur du nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * setter pour l'attribut nom
	 * @param nom : nouvelle valeur du nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * getter pour l'attribut prénom
	 * @return valeur du prénom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * setter pour l'attribut prénom
	 * @param prenom : nouvelle valeur du prénom
	 */
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	/**
	* getter pour l'attribut mail
	* @return valeur de l'adresse mail
	*/
	public String getMail() {
		return mail;
	}
	/**
	 * setter pour l'attribut mail
	 * @param mail : nouvelle valeur de l'adresse mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	* getter pour l'attribut tel
	* @return valeur du numéro de téléphone
	*/
	public String getTel() {
		return tel;
	}
	/**
	 * setter pour l'attribut tel
	 * @param tel : nouvelle valeur du numéro de téléphone
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * Affiche les caractéristiques de la personne  
	 */
	public void display()
	{
		System.out.println("id: "+this.id+"  ;nom: "+this.nom+"  ;prenom: "+this.prenom+"   ;mail: "+this.mail+"   ;tel: "+this.tel);
	}
	/**
	 * Affiche les caractéristiques de la personne sauf le telephone
	 */
	public void display2()
	{
		System.out.println("id: "+this.id+"  ;nom: "+this.nom+"  ;prenom: "+this.prenom+"   ;mail: "+this.mail );
	}
}