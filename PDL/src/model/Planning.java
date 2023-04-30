package model;
import java.util.Date;
/**
 * Classe Planning
 * @author Hiroo
 * @version 1.0
 * */
public class Planning {
	/** 
	 * reference du planning
	 */
	public int id;
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
	private double duree;
	/**
	 * Répartition
	 */
	private String salle;
	
	/**
	 * Constructor
	 * @param id identifiant du planning
	 * @param enseignant du planning
	 * @param cours du planning
	 * @param date du planning
	 * @param nbreHeure du planning
	 * @param salle du planning
	 */
	public Planning(int id, Enseignant enseignant,Cours cours, Date date, String salle, double duree) {
		this.id=id;
		this.enseignant=enseignant;
		this.cours=cours;
		this.date=date;
		this.salle=salle;
		this.duree= duree;
	}
	
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference Planning
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
	 * getter pour l'attribut salle
	 * @return valeur de la salle
	 */
	public String getsalle(String salle) {
		return salle;
	}
	/**
	 * setter pour l'attribut salle
	 * @param salle : nouvelle valeur du salle
	 */
	public void setsalle(String salle) {
		this.salle = salle;
	}
	/**
	 * getter pour l'attribut duree
	 * @return valeur de la duree
	 */
	public double getDuree(double duree) {
		return duree;
	}
	/**
	 * setter pour l'attribut duree
	 * @param duree : nouvelle valeur de la duree
	 */
	public void setDuree(double duree) {
		this.duree = duree;
	}
	
	/**
	 * display de toutes les donnees du planning
	 */
	public void display() {
		System.out.println(id);
		System.out.println( enseignant);
		System.out.println(cours);
		System.out.println(date);
		System.out.println(salle);
		System.out.println(duree);
	}

}