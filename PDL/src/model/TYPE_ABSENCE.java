package model;

/**
 * Classe TYPE_ABSENCE
 * @author Aurélien
 * @version 1.0
 * */
public class TYPE_ABSENCE {
	/** 
	 * reference du fournisseur
	 */
	private int id;
	/**
	 * quota
	 */
	private String quota;
	/**
	 * désignation
	 */
	private String designation;
	
	/**
	 * Constructor
	 * @param id identifiant du type d'absence
	 * @param quota définition du quota
	 * @param designation définition de la désignation
	 */
	public TYPE_ABSENCE(int id, String quota, String designation) {
		this.id=id;
		this.quota=quota;
		this.designation=designation;
	}
	
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference type_absence
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut quota
	 * @return valeur du quota
	 */
	public String getQuota() {
		return quota;
	}
	/**
	 * setter pour l'attribut quota
	 * @param quota : nouvelle valeur du quota
	 */
	public void setQuota(String quota) {
		this.quota = quota;
	}
	/**
	 * getter pour l'attribut désignation
	 * @return valeur de la désignation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * setter pour l'attribut désignation
	 * @param designation : nouvelle valeur de la désignation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
}