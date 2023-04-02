package model;

import java.util.Date;

/**
 * Classe Justificatif
 * @author Alexandre
 * @version 1.0
 * */
public class Justificatif {
	/** 
	 * reference du justificatif
	 */
	public int idJustificatif;
	/**
	 * date à justifier
	 */
	public Date date;
	/**
	 * Motif de l'absence
	 */
	public String motif;
	/**
	 * Justification acceptee ou refusee
	 */
	public Boolean accepte;
	
	/**
	 * Constructor
	 * @param idJustificatif identifiant du justificatif
	 * @param date date à justifier
	 * @param motif Motif de l'absence
	 * @param accepté Justification acceptee ou refusee
	 */
	public Justificatif(int idJustificatif, Date date, String motif, boolean accepte) {
		this.idJustificatif=idJustificatif;
		this.date=date;
		this.motif=motif;
		this.accepte=accepte;
	}
	
	/**
	 * display de toutes les donnees du Justificatif
	 */
	public void display() {
		System.out.println(idJustificatif);
		System.out.println(date);
		System.out.println(motif);
		System.out.println(accepte);
	}
}
	