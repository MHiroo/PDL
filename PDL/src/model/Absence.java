package model;
import java.util.Date;
/**
 * Classe Absence
 * @author Hiroo
 * @version 1.0
 * */
public class Absence {
	/** 
	 * reference de l'absence
	 */
	private int id;
	/**
	 * enseignant
	 */
	private Enseignant enseignant;
	/**
	 * Cours
	 */
	private Cours cours;
	/**
	 * Date 
	 */
	private Date date;
	/**
	 * nbreHeure
	 */
	private int nbreHeure;
	/**
	 * justificatif
	 */
	private String justificatif;
	/**
	 * décision
	 */
	private String decision;
	
	/**
	 * Constructor
	 * @param id identifiant de l'absence
	 * @param enseignant de l'absence
	 * @param cours de l'absence
	 * @param date de l'absence
	 * @param nbreHeure de l'absence
	 * @param justificatif de l'absence
	 * @param decision de l'absence
	 */
	public Absence(int id, Enseignant enseignant,Cours cours, Date date, String justificatif, String decision) {
		this.id=id;
		this.enseignant=enseignant;
		this.cours=cours;
		this.date=date;
		this.justificatif=justificatif;
		this.decision=decision;
	}
	
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference Absence
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut enseignant
	 * @return valeur du enseignant
	 */
	public Enseignant getEnseignant() {
		return enseignant;
	}
	/**
	 * setter pour l'attribut enseignant
	 * @param nom : nouvelle valeur du enseignant
	 */
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	/**
	 * getter pour l'attribut cours
	 * @return valeur du cours
	 */
	public Cours getCours(Cours cours) {
		return cours;
	}
	/**
	 * setter pour l'attribut cours
	 * @param cours : nouvelle valeur du cours
	 */
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	/**
	 * getter pour l'attribut date
	 * @return valeur de la date
	 */
	public Date getDate(Date date) {
		return date;
	}
	/**
	 * setter pour l'attribut date
	 * @param date : nouvelle valeur de la date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * getter pour l'attribut justificatif
	 * @return valeur de la justificatif
	 */
	public String getJustificatif(String justificatif) {
		return justificatif;
	}
	/**
	 * setter pour l'attribut justificatif
	 * @param justificatif : nouvelle valeur de la justificatif
	 */
	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}
	/**
	 * getter pour l'attribut decision
	 * @return valeur de la decision
	 */
	public String getDecision(String decision) {
		return decision;
	}
	/**
	 * setter pour l'attribut decision
	 * @param decision : nouvelle valeur de la decision
	 */
	public void setDecision(String decision) {
		this.decision = decision;
	}
	/**
	 * display de toutes les donnees de l'absence
	 */
	public void display() {
		System.out.println(id);
		System.out.println( enseignant);
		System.out.println(cours);
		System.out.println(date);
		System.out.println(justificatif);
		System.out.println(decision);
	}

}