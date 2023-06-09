package model;
import java.time.LocalDate;
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
	 * LocalDate 
	 */
	private LocalDate date;
	/**
	 * nbreHeure
	 */
	private double duree;
	/**
	 * Salle
	 */
	private String salle;
	/**
	 * Répartition
	 */
	private double heure;
	/**
	 * lien
	 */
	private String lien;
	
	/**
	 * Constructor
	 * @param id identifiant du planning
	 * @param idGroupe du planning
	 * @param idEnseignant du planning
	 * @param idCours du planning
	 * @param date du planning
	 * @param salle du planning
	 * @param duree du planning
	 * @param heure de la séance
	 * @param lien de la séance
	 */
	public Planning(int id,int idGroupe, int idEnseignant,int idCours, LocalDate date, String salle, double duree, double heure, String lien) {
		this.id=id;
		this.idGroupe= idGroupe;
		this.idEnseignant=idEnseignant;
		this.idCours=idCours;
		this.date=date;
		this.salle=salle;
		this.duree= duree;
		this.heure= heure;
		this.lien= lien;
	}
	public Planning(int idGroupe, int idEnseignant,int idCours, LocalDate date, String salle, double duree, double heure, String lien) {
	
		this.idGroupe= idGroupe;
		this.idEnseignant=idEnseignant;
		this.idCours=idCours;
		this.date=date;
		this.salle=salle;
		this.duree= duree;
		this.heure= heure;
		this.lien= lien;
	}
	public Planning(LocalDate date) {
	
		this.date=date;
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
	public LocalDate getDate() {
		return date;
	}
	/**
	 * setter pour l'attribut date
	 * @param date : nouvelle valeur de la date
	 */
	public void setDate(LocalDate date) {
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
	public void setDuree1(double duree) {
		this.duree = duree;
	}
	/**
	 * getter pour l'attribut heure
	 * @return valeur de la heure
	 */
	public double getHeure() {
		return heure;
	}
	/**
	 * setter pour l'attribut duree
	 * @param heure : nouvelle valeur de la duree
	 */
	public void setHeure(double heure) {
		this.heure = heure;
	}
	/**
	 * getter pour l'attribut heure
	 * @return valeur de la heure
	 */
	public String getLien() {
		return lien;
	}
	/**
	 * setter pour l'attribut duree
	 * @param lien : nouvelle valeur de la duree
	 */
	public void setLien(String lien) {
		this.lien = lien;
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
		System.out.println(lien);
	}

}