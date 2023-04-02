package model;

/**
 * Classe Justificatif
 * @author Alexandre
 * @version 1.0
 * */
public class Groupe_Etudiant {
	/** 
	 * reference du groupe
	 */
	public int idGrpEtudiant;
	/**
	 * numéro du groupe
	 */
	private int num;
	/**
	 * Capacité maximum du groupe
	 */
	private int capaciteMax;
	
	/**
	 * Constructor
	 * @param idGrpEtudiant identifiant du groupe
	 * @param num numero du groupe
	 * @param capaciteMax capacite maximum du groupe
	 */
	public Groupe_Etudiant(int idGrpEtudiant, int num, int capaciteMax) {
		this.idGrpEtudiant=idGrpEtudiant;
		this.num=num;
		this.capaciteMax=capaciteMax;
	}
	
	/**
	 * getter pour l'attribut num
	 * @return valeur du numero du groupe
	 */
	public int getNum() {
		return num;
	}
	/**
	 * getter pour l'attribut capaciteMax
	 * @return valeur de la capacite maximum du groupe
	 */
	public int getcapaciteMax() {
		return capaciteMax;
	}
	/**
	 * setter pour l'attribut num
	 * @param num : nouvelle valeur du num
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * setter pour l'attribut capaciteMax
	 * @param capaciteMax : nouvelle valeur de capacite maximum
	 */
	public void setcapaciteMax(int capaciteMax) {
		this.capaciteMax=capaciteMax;
	}
	public void display() {
		System.out.println(idGrpEtudiant);
		System.out.println(num);
		System.out.println(capaciteMax);
	}
}