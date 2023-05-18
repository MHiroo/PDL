package model;
import java.util.Date;
/**
 * Classe Cours
 * @author Hiroo
 * @version 1.0
 * */
public class Cours {
	/** 
	 * reference du cours
	 */
	private int id;
	/**
	 * nom
	 */
	private String nom;
	/**
	 * masseHoraire
	 */
	private int masseHoraire;
	/**
	 * repNbrHAmphi 
	 */
	private int repNbrHAmphi;
	/**
	 * repNbrHTD
	 */
	private int repNbrHTD;
	/**
	 * repNbrHTP
	 */
	private int repNbrHTP;
	/**
	 * repNbrHExam
	 */
	private int repNbrHExam;
	
	/**
	 * Constructor
	 * @param id identifiant du cours
	 * @param nom du cours
	 * @param cours du cours
	 * @param date du cours
	 * @param nbreHeure du cours
	 * @param justificatif du cours
	 * @param decision du cours
	 */
	public Cours(int id, String nom,int masseHoraire,int repNbrHAmphi, int repNbrHTD,int repNbrHTP, int repNbrHExam) {
		this.id=id;
		this.nom=nom;
		this.masseHoraire=masseHoraire;
		this.repNbrHAmphi=repNbrHAmphi;
		this.repNbrHTD=repNbrHTD;
		this.repNbrHTP=repNbrHTP;
		this.repNbrHExam=repNbrHExam;
	}
	public Cours(String nom) {
		this.nom=nom;
	}
	
	public Cours(String nom, int masseHoraire, int repNbrHAmphi, int repNbrHTD, int repNbrHTP, int repNbrHExam) {
		this.nom=nom;
		this.masseHoraire=masseHoraire;
		this.repNbrHAmphi=repNbrHAmphi;
		this.repNbrHTD=repNbrHTD;
		this.repNbrHTP=repNbrHTP;
		this.repNbrHExam=repNbrHExam;
	}
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference Cours
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
	 * getter pour l'attribut masseHoraire
	 * @return valeur du masseHoraire
	 */
	public int getMasseHoraire() {
		return masseHoraire;
	}
	/**
	 * setter pour l'attribut masseHoraire
	 * @param masseHoraire : nouvelle valeur de la masseHoraire
	 */
	public void setMasseHoraire(int masseHoraire) {
		this.masseHoraire = masseHoraire;
	}
	
	/**
	 * getter pour l'attribut repNbrHAmphi
	 * @return valeur du repNbrHAmphi
	 */
	public int getRepNbrHAmphi() {
		return repNbrHAmphi;
	}
	/**
	 * setter pour l'attribut repNbrHAmphi
	 * @param repNbrHAmphi : nouvelle valeur de la repNbrHAmphi
	 */
	public void setRepNbrHAmphi(int repNbrHAmphi) {
		this.repNbrHAmphi = repNbrHAmphi;
	}
	/**
	 * getter pour l'attribut repNbrHTD
	 * @return valeur du repNbrHTD
	 */
	public int getRepNbrHTD() {
		return repNbrHTD;
	}
	/**
	 * getter pour l'attribut repNbrHTP
	 * @return valeur du repNbrHTP
	 */
	public int getRepNbrHTP() {
		return repNbrHTP;
	}
	/**
	 * setter pour l'attribut repNbrHTD
	 * @param repNbrHTD : nouvelle valeur de la repNbrHTD
	 */
	public void setRepNbrHTD(int repNbrHTD) {
		this.repNbrHTD = repNbrHTD;
	}
	/**
	 * setter pour l'attribut repNbrHTP
	 * @param repNbrHTP : nouvelle valeur de la repNbrHTP
	 */
	public void setRepNbrHTP(int repNbrHTP) {
		this.repNbrHTP = repNbrHTP;
	}
	/**
	 * getter pour l'attribut repNbrHExam
	 * @return valeur du repNbrHExam
	 */
	public int getRepNbrHExam() {
		return repNbrHExam;
	}
	/**
	 * setter pour l'attribut repNbrHExam
	 * @param repNbrHExam : nouvelle valeur de la repNbrHExam
	 */
	public void setRepNbrHExam(int repNbrHExam) {
		this.repNbrHExam = repNbrHExam;
	}
	/**
	 * display de toutes les donnees du cours
	 */
	public void display() {
		System.out.println(id);
		System.out.println(nom );
		System.out.println(masseHoraire);
		System.out.println(repNbrHAmphi);
		System.out.println(repNbrHTD);
		System.out.println(repNbrHTP);
		System.out.println(repNbrHExam);
	}
	

}