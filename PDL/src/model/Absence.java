package model;
import java.sql.Time;
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
	 * id de l'etudiant
	 */
	private int idEtud;
	/**
	 * id du cours
	 */
	private int idCours;
	/**
	 * heure de debut
	 */
	private java.sql.Date heureDebut;
	/**
	 * nombre d'heure 
	 */
	private int nbHeure;
	/**
	 * date
	 */
	private java.sql.Date date;
	/**
	 * statut
	 */
	private String statut;

	
	/**
	 * Constructor
	 * @param id identifiant de l'absence
	 * @param idEtud identifiant de l'etudiant
	 * @param idCours id du cours de l'absence
	 * @param nbHeure nombre d'heure d'absence
	 * @param nbreHeure de l'absence
	 * @param date date de l'absence
	 * @param statut statut de l'absence justifie ou non
	 */
	public Absence(int id, int idEtud, int idCours,java.sql.Date heureDebut, int nbHeure, java.sql.Date date, String statut) {
		this.id=id;
		this.idEtud=idEtud;
		this.idCours=idCours;
		this.heureDebut=heureDebut;
		this.nbHeure=nbHeure;
		this.date=date;
		this.statut=statut;
	}
	
	public Absence(int id) {
		this.id=id;
	}

	public Absence(int id, int idEtud) {
		this.id=id;
		this.idEtud=idEtud;
	}

	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference Absence
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut idEtud
	 * @return identifiant de l'etudiant
	 */
	public int getIdEtud() {
		return idEtud;
	}
	/**
	 * setter pour l'attribut idEtud
	 * @param idEtud nouvelle valeur de l'id de l'etudiant
	 */
	public void setIdEtud(int idEtud) {
		this.idEtud = idEtud;
	}
	/**
	 * getter pour l'attribut idCours
	 * @return identifiant du cours
	 */
	public int getIdCours() {
		return idCours;
	}
	/**
	 * setter pour l'attribut idCours
	 * @param idCours nouvelle valeur de l'identifiant du cours de l'absence
	 */
	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}
	/**
	 * getter pour l'attribut idCours
	 * @param idCours l'identifiant du cours de l'absence
	 */
	public java.sql.Date getHeureDebut() {
		return heureDebut;
	}
	/**
	 * setter pour l'attribut nbHeure
	 * @param nbHeure : nouvelle valeur du nombre d'heure d'absence
	 */
	public void setHeureDebut(java.sql.Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	/**
	 * getter pour l'attribut idCours
	 * @param idCours l'identifiant du cours de l'absence
	 */
	public int getNbHeure() {
		return nbHeure;
	}
	/**
	 * setter pour l'attribut nbHeure
	 * @param nbHeure : nouvelle valeur du nombre d'heure d'absence
	 */
	public void setNbHeure(int nbHeure) {
		this.nbHeure = nbHeure;
	}
	/**
	 * getter pour l'attribut date
	 * @return date date de l'absence
	 */
	public java.sql.Date getDate() {
		return date;
	}
	/**
	 * setter pour l'attribut date
	 * @param date : nouvelle valeur de la date
	 */
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	/**
	 * getter pour l'attribut statut
	 * @return valeur du statut
	 */
	public String getStatut() {
		return statut;
	}
	/**
	 * setter pour l'attribut statut
	 * @param statut : nouvelle valeur du statut
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	/**
	 * display de toutes les donnees de l'absence
	 */
	public void display() {
		System.out.println(id);
		System.out.println(idEtud);
		System.out.println(idCours);
		System.out.println(heureDebut);
		System.out.println(nbHeure);
		System.out.println(date);
		System.out.println(statut);
	}

}