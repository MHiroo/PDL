package model;
import java.sql.Time;
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
	 * groupe d'étudiant
	 */
	public int idGroupe;
	/**
	 * enseignant
	 */
	private int idEnseignant;
	/**
	 * Cours
	 */
	private int idCours;
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
	 * Répartition
	 */
	private Time heure;
	
	/**
	 * Constructor
	 * @param id identifiant du planning
	 * @param enseignant du planning
	 * @param cours du planning
	 * @param date du planning
	 * @param nbreHeure du planning
	 * @param salle du planning
	 * @param duree de la séance
	 * @param heure de la séance
	 */
	public Planning(int id,int idGroupe, int idEnseignant,int idCours, Date date, String salle, double duree, Time heure) {
		this.id=id;
		this.idGroupe= idGroupe;
		this.idEnseignant=idEnseignant;
		this.idCours=idCours;
		this.date=date;
		this.salle=salle;
		this.duree= duree;
		this.heure= heure;
	}
	
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference Planning
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut groupe
	 * @return valeur du groupe
	 */
	public int getIdGroupe() {
		return idGroupe;
	}
	/**
	 * getter pour l'attribut enseignant
	 * @return valeur du enseignant
	 */
	public int getIdEnseignant() {
		return idEnseignant;
	}
	/**
	 * getter pour l'attribut cours
	 * @return valeur du cours
	 */
	public int getIdCours() {
		return idCours;
	}
	/**
	 * getter pour l'attribut date
	 * @return valeur de la date
	 */
	public Date getDate() {
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
	public String getSalle() {
		return salle;
	}
	/**
	 * setter pour l'attribut salle
	 * @param salle : nouvelle valeur du salle
	 */
	public void setSalle(String salle) {
		this.salle = salle;
	}
	/**
	 * getter pour l'attribut duree
	 * @return valeur de la duree
	 */
	public double getDuree() {
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
	 * getter pour l'attribut heure
	 * @return valeur de la heure
	 */
	public Time getHeure() {
		return heure;
	}
	/**
	 * setter pour l'attribut duree
	 * @param duree : nouvelle valeur de la duree
	 */
	public void setDuree(Time heure) {
		this.heure = heure;
	}
	
	/**
	 * display de toutes les donnees du planning
	 */
	public void display() {
		System.out.println(id);
		System.out.println( idGroupe);
		System.out.println( idEnseignant);
		System.out.println(idCours);
		System.out.println(date);
		System.out.println(salle);
		System.out.println(duree);
		System.out.println(heure);
	}

}