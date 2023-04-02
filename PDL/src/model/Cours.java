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
	public Cours(int id, String nom,int masseHoraire,int repNbrHAmphi, int repNbrHTD, int repNbrHExam) {
		this.id=id;
		this.nom=nom;
		this.masseHoraire=masseHoraire;
		this.repNbrHAmphi=repNbrHAmphi;
		this.repNbrHTD=repNbrHTD;
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
	public string getNom() {
		return nom;
	}
	/**
	 * setter pour l'attribut nom
	 * @param nom : nouvelle valeur du nom
	 */
	public void setNom(string nom) {
		this.nom = nom;
	}
	/**
	 * getter pour l'attribut masseHoraire
	 * @return valeur du masseHoraire
	 */
	public int getMasseHoraire(int masseHoraire) {
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
	public int getRepNbrHAmphi(int repNbrHAmphi) {
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
	public int getRepNbrHTD(int repNbrHTD) {
		return repNbrHTD;
	}
	/**
	 * setter pour l'attribut repNbrHTD
	 * @param repNbrHTD : nouvelle valeur de la repNbrHTD
	 */
	public void setRepNbrHTD(int repNbrHTD) {
		this.repNbrHTD = repNbrHTD;
	}
	/**
	 * getter pour l'attribut repNbrHExam
	 * @return valeur du repNbrHExam
	 */
	public int getRepNbrHExam(int repNbrHExam) {
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
		System.out.println(repNbrHExam);
	}
	

}