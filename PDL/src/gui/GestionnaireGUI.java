package gui;

import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import model.Enseignant;
import model.Etudiant;
import model.Groupe_Etudiant;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GestionnaireGUI {

	private JFrame UC2frame;
	private JFrame UC3frame;
	private JFrame UC4frame;
	private JFrame UC5frame;
	private JFrame UC6frame;
	private JFrame UC7frame;
	private JFrame UC8frame;
	private JFrame UC9frame;
	private JFrame pframe;
     JFrame frame;
    private JFrame frameModif;
    private JFrame frameModifEns;
    private JFrame frameModifGroupe;
    private JFrame frameSuppr;
    private JFrame frameSupprEns;
    private JFrame frameSupprGroupe;
    private JTextField textFieldGroupeModif;
    private JTextField textFieldNomModif;
    private JTextField textFieldPrenomModif;
    private JTextField textFieldFiliereModif;
    private JTextField textFieldEmailModif;
    private JTextField textFieldMdpModif;
    private JTextField textFieldGroupe;
    private JTextField textFieldNom;
    private JTextField textFieldNomEns;
    private JTextField textFieldNomEnsM;
    private JTextField textFieldNomEnsS;
    private JTextField textFieldPrenom;
    private JTextField textFieldPrenomEns;
    private JTextField textFieldPrenomEnsM;
    private JTextField textFieldPrenomEnsS;
    private JTextField textFieldFiliere;
    private JTextField textFieldEmail;
    private JTextField textFieldEmailEns;
    private JTextField textFieldEmailEnsM;
    private JTextField textFieldEmailEnsS;
    private JTextField textFieldTel;
    private JTextField textFieldTelM;
    private JTextField textFieldTelS;
    private JTextField textFieldMdp;
    private JTextField textFieldMdpEns;
    private JTextField textFieldMdpEnsM;
    private JTextField textFieldMdpEnsS;
    private JTextField textFieldNum;
    private JTextField textFieldNumM;
    private JTextField textFieldCM;
    private JTextField textFieldCMM;
    private JComboBox groupeBox;
    private JComboBox groupeBoxM;
    private JComboBox idBox;
    private JComboBox idBoxEns;
    private JComboBox idBoxEnsS;
    private JComboBox idBoxS;
    private JComboBox idBoxGroupe;
    private JComboBox idBoxGroupeS;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionnaireGUI window = new GestionnaireGUI();
                    window.pframe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionnaireGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	/**
    	 * Cr�ation de la fen�tre principale
    	 */
    	pframe = new JFrame();
    	pframe.setBounds(100, 100, 450, 300);
        pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pframe.getContentPane().setLayout(new BoxLayout(pframe.getContentPane(), BoxLayout.Y_AXIS));
    	
        /**
         * Cr�ation du panel comportant les UC part 1
         */
    	JPanel panelUC1 = new JPanel();
    	pframe.getContentPane().add(panelUC1);
    	
    	/**
         * Cr�ation du panel comportant les UC part 2
         */
    	JPanel panelUC2 = new JPanel();
    	pframe.getContentPane().add(panelUC2);
    	
    	/**
         * Cr�ation du panel comportant les UC part 3
         */
    	JPanel panelUC3 = new JPanel();
    	pframe.getContentPane().add(panelUC3); 
    	
    	/**
         * Cr�ation du panel comportant les UC part 4
         */
    	JPanel panelUC4 = new JPanel();
    	pframe.getContentPane().add(panelUC4);   
    	
    	/**
    	 * Cr�ation de la fen�tre UC2
    	 */
    	UC2frame = new JFrame();
    	UC2frame.setBounds(100, 100, 450, 300);
        UC2frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC2frame.getContentPane().setLayout(new BoxLayout(UC2frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Cr�ation de la fen�tre UC3
    	 */
    	UC3frame = new JFrame();
    	UC3frame.setBounds(100, 100, 450, 300);
        UC3frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC3frame.getContentPane().setLayout(new BoxLayout(UC3frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Cr�ation de la fen�tre UC4
    	 */
    	UC4frame = new JFrame();
    	UC4frame.setBounds(100, 100, 450, 300);
        UC4frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC4frame.getContentPane().setLayout(new BoxLayout(UC4frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Cr�ation de la fen�tre UC5
    	 */
    	UC5frame = new JFrame();
    	UC5frame.setBounds(100, 100, 450, 300);
        UC5frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC5frame.getContentPane().setLayout(new BoxLayout(UC5frame.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la sélection du num pour ajouter un groupe
         */
        JPanel panelNum = new JPanel();
        UC5frame.getContentPane().add(panelNum);

        JLabel lblNum = new JLabel("Num:");
        panelNum.add(lblNum);

        textFieldNum = new JTextField();
        panelNum.add(textFieldNum);
        textFieldNum.setColumns(10);

        /**
         * Création du panel comportant la sélection de la capacite max pour ajouter un groupe
         */
        JPanel panelCM = new JPanel();
        UC5frame.getContentPane().add(panelCM);

        JLabel lblCM = new JLabel("Capacite max:");
        panelCM.add(lblCM);

        textFieldCM = new JTextField();
        panelCM.add(textFieldCM);
        textFieldCM.setColumns(10);
        
        /**
         * Creation de la fenetre de modif d'un groupe
         */
        frameModifGroupe = new JFrame();
        frameModifGroupe.setBounds(100, 100, 450, 300);
        frameModifGroupe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameModifGroupe.getContentPane().setLayout(new BoxLayout(frameModifGroupe.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour modifier un groupe
         */
        JPanel panelModifGroupe = new JPanel();
        frameModifGroupe.getContentPane().add(panelModifGroupe);
        
        JLabel lblModifGroupe = new JLabel("Id:");
        panelModifGroupe.add(lblModifGroupe);
        
      //On r�cup�re l'id des �tudiants cr�es dans la BDD pour les afficher ds le menu d�roulant 
        GroupeDAO groupeDAO = new GroupeDAO();
        idBoxGroupe = new JComboBox();
        for (int i = 0; i < groupeDAO.getList().size(); i++) {
        idBoxGroupe.addItem(groupeDAO.getList().get(i).getId());
        }
        panelModifGroupe.add(idBoxGroupe);
        
        /**
		 * Creation du panel comportant la selection du num pour modifier un groupe
		 */
        JPanel panelNomModifGroupe = new JPanel();
        frameModifGroupe.getContentPane().add(panelNomModifGroupe);

        JLabel lblNomModifGroupe = new JLabel("Num:");
        panelNomModifGroupe.add(lblNomModifGroupe);

        textFieldNumM = new JTextField();
        panelNomModifGroupe.add(textFieldNumM);
        textFieldNumM.setColumns(10);

        /**
         * Creation du panel comportant la selection de la cap. max pour modifier un groupe
         */
        JPanel panelCmModifGroupe = new JPanel();
        frameModifGroupe.getContentPane().add(panelCmModifGroupe);

        JLabel lblCmModifGroupe = new JLabel("Capacite max:");
        panelCmModifGroupe.add(lblCmModifGroupe);

        textFieldCMM = new JTextField();
        panelCmModifGroupe.add(textFieldCMM);
        textFieldCMM.setColumns(10);
        
        /**
         * Creation de la fenetre de suppression d'un groupe
         */
        frameSupprGroupe = new JFrame();
        frameSupprGroupe.setBounds(100, 100, 450, 300);
        frameSupprGroupe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSupprGroupe.getContentPane().setLayout(new BoxLayout(frameSupprGroupe.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour supprimer un groupe
         */
        JPanel panelSupprGroupe = new JPanel();
        frameSupprGroupe.getContentPane().add(panelSupprGroupe);
        
        JLabel lblSupprGroupe = new JLabel("Id:");
        panelSupprGroupe.add(lblSupprGroupe);
        
        GroupeDAO groupeDAOsup = new GroupeDAO();
        idBoxGroupeS = new JComboBox();
        for (int i = 0; i < groupeDAOsup.getList().size(); i++) {
        idBoxGroupeS.addItem(groupeDAOsup.getList().get(i).getId());
        }
        panelSupprGroupe.add(idBoxGroupeS);
        
    	/**
    	 * Cr�ation de la fen�tre UC6
    	 */
    	UC6frame = new JFrame();
    	UC6frame.setBounds(100, 100, 450, 300);
        UC6frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC6frame.getContentPane().setLayout(new BoxLayout(UC6frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Cr�ation de la fen�tre UC7
    	 */
    	UC7frame = new JFrame();
    	UC7frame.setBounds(100, 100, 450, 300);
        UC7frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC7frame.getContentPane().setLayout(new BoxLayout(UC7frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Cr�ation de la fen�tre UC8
    	 */
    	UC8frame = new JFrame();
    	UC8frame.setBounds(100, 100, 450, 300);
        UC8frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC8frame.getContentPane().setLayout(new BoxLayout(UC8frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Cr�ation de la fen�tre UC9
    	 */
    	UC9frame = new JFrame();
    	UC9frame.setBounds(100, 100, 450, 300);
        UC9frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UC9frame.getContentPane().setLayout(new BoxLayout(UC9frame.getContentPane(), BoxLayout.Y_AXIS));
    	
        /**
         * Cr�ation du panel comportant la sélection du nom pour ajouter un enseignant
         */
        JPanel panelNomEns = new JPanel();
        UC9frame.getContentPane().add(panelNomEns);

        JLabel lblNomEns = new JLabel("Nom:");
        panelNomEns.add(lblNomEns);

        textFieldNomEns = new JTextField();
        panelNomEns.add(textFieldNomEns);
        textFieldNomEns.setColumns(10);

        /**
         * Création du panel comportant la sélection du prénom pour ajouter un enseignant
         */
        JPanel panelPrenomEns = new JPanel();
        UC9frame.getContentPane().add(panelPrenomEns);

        JLabel lblPrenomEns = new JLabel("Prenom:");
        panelPrenomEns.add(lblPrenomEns);

        textFieldPrenomEns = new JTextField();
        panelPrenomEns.add(textFieldPrenomEns);
        textFieldPrenomEns.setColumns(10);
        
        /**
         * Création du panel comportant la sélection du tél pour ajouter un enseignant
         */
        JPanel panelTel = new JPanel();
        UC9frame.getContentPane().add(panelTel);
        
        JLabel lblTel = new JLabel("Tél:");
        panelTel.add(lblTel);
        
        textFieldTel = new JTextField();
        panelTel.add(textFieldTel);
        textFieldTel.setColumns(10);
        
        /**
         * Création du panel comportant la sélection du mail pour ajouter un enseignant
         */
        JPanel panelEmailEns = new JPanel();
        UC9frame.getContentPane().add(panelEmailEns);
        
        JLabel lblEmailEns = new JLabel("Email:");
        panelEmailEns.add(lblEmailEns);
        
        textFieldEmailEns = new JTextField();
        panelEmailEns.add(textFieldEmailEns);
        textFieldEmailEns.setColumns(10);
        
        /**
         * Création du panel comportant la sélection du mdp pour ajouter un enseignant
         */
        JPanel panelMdpEns = new JPanel();
        UC9frame.getContentPane().add(panelMdpEns);

        JLabel lblMdpEns = new JLabel("Mot de passe:");
        panelMdpEns.add(lblMdpEns);

        textFieldMdpEns = new JTextField();
        panelMdpEns.add(textFieldMdpEns);
        textFieldMdpEns.setColumns(10);
        
    	/**
    	 * Cr�ation de la fenetre pour le UC Cr�er/modifier un �tudiant
    	 */
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Cr�ation du panel comportant la s�lection du groupe pour ajouter un �tudiant
         */
        JPanel panelGroupe = new JPanel();
        frame.getContentPane().add(panelGroupe);
        
        JLabel lblGroupe = new JLabel("Groupe:");
        panelGroupe.add(lblGroupe);
        
        //On r�cup�re l'id des groupes cr�er das la BDD pour les afficher ds le menu d�roulant
        GroupeDAO groupeDAO2 = new GroupeDAO();  
        groupeBox = new JComboBox();
        for (int i = 0; i < groupeDAO2.getList().size(); i++) {
        groupeBox.addItem(groupeDAO2.getList().get(i).getId());
        }
        panelGroupe.add(groupeBox);

        /**
         * Cr�ation du panel comportant la s�lection du nom pour ajouter un �tudiant
         */
        JPanel panelNom = new JPanel();
        frame.getContentPane().add(panelNom);

        JLabel lblNom = new JLabel("Nom:");
        panelNom.add(lblNom);

        textFieldNom = new JTextField();
        panelNom.add(textFieldNom);
        textFieldNom.setColumns(10);

        /**
         * Cr�ation du panel comportant la s�lection du pr�nom pour ajouter un �tudiant
         */
        JPanel panelPrenom = new JPanel();
        frame.getContentPane().add(panelPrenom);

        JLabel lblPrenom = new JLabel("Prenom:");
        panelPrenom.add(lblPrenom);

        textFieldPrenom = new JTextField();
        panelPrenom.add(textFieldPrenom);
        textFieldPrenom.setColumns(10);

        /**
         * Cr�ation du panel comportant la s�lection de la fili�re pour ajouter un �tudiant
         */
        JPanel panelFiliere = new JPanel();
        frame.getContentPane().add(panelFiliere);

        JLabel lblFiliere = new JLabel("Filiere:");
        panelFiliere.add(lblFiliere);

        textFieldFiliere = new JTextField();
        panelFiliere.add(textFieldFiliere);
        textFieldFiliere.setColumns(10);

		/**
		 * Cr�ation du panel comportant la s�lection du mail pour ajouter un �tudiant
		 */
        JPanel panelEmail = new JPanel();
        frame.getContentPane().add(panelEmail);

        JLabel lblEmail = new JLabel("Email:");
        panelEmail.add(lblEmail);

        textFieldEmail = new JTextField();
        panelEmail.add(textFieldEmail);
        textFieldEmail.setColumns(10);
        
        /**
         * Cr�ation du panel comportant la s�lection du mdp pour ajouter un �tudiant
         */
        JPanel panelMdp = new JPanel();
        frame.getContentPane().add(panelMdp);

        JLabel lblMdp = new JLabel("Mot de passe:");
        panelMdp.add(lblMdp);

        textFieldMdp = new JTextField();
        panelMdp.add(textFieldMdp);
        textFieldMdp.setColumns(10);
        
        /**
         * Creation de la fenetre de modif d'un enseignant
         */
        frameModifEns = new JFrame();
        frameModifEns.setBounds(100, 100, 450, 300);
        frameModifEns.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameModifEns.getContentPane().setLayout(new BoxLayout(frameModifEns.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Cr�ation du panel comportant la s�lection de l'id pour modifier un �tudiant
         */
        JPanel panelModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelModifEns);
        
        JLabel lblModifEns = new JLabel("Id:");
        panelModifEns.add(lblModifEns);
        
      //On r�cup�re l'id des �tudiants cr�es dans la BDD pour les afficher ds le menu d�roulant 
        EnseignantDAO enseignantDAO = new EnseignantDAO();
        idBoxEns = new JComboBox();
        for (int i = 0; i < enseignantDAO.getList().size(); i++) {
        idBoxEns.addItem(enseignantDAO.getList().get(i).getId());
        }
        panelModifEns.add(idBoxEns);
        
        /**
		 * Creation du panel comportant la selection du nom pour modifier un enseignant
		 */
        JPanel panelNomModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelNomModifEns);

        JLabel lblNomModifEns = new JLabel("Nom:");
        panelNomModifEns.add(lblNomModifEns);

        textFieldNomEnsM = new JTextField();
        panelNomModifEns.add(textFieldNomEnsM);
        textFieldNomEnsM.setColumns(10);

        /**
         * Creation du panel comportant la selection du prenom pour modifier un enseignant
         */
        JPanel panelPrenomModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelPrenomModifEns);

        JLabel lblPrenomModifEns = new JLabel("Prenom:");
        panelPrenomModifEns.add(lblPrenomModifEns);

        textFieldPrenomEnsM = new JTextField();
        panelPrenomModifEns.add(textFieldPrenomEnsM);
        textFieldPrenomEnsM.setColumns(10);
        
        /**
         * Creation du panel comportant la selection du tel pour modifier un enseignant
         */
        JPanel panelTelModif = new JPanel();
        frameModifEns.getContentPane().add(panelTelModif);

        JLabel lblTelModif = new JLabel("Tel:");
        panelTelModif.add(lblTelModif);

        textFieldTelM = new JTextField();
        panelTelModif.add(textFieldTelM);
        textFieldTelM.setColumns(10);

        /**
         * Creation du panel comportant la selection du mail pour modifier un enseignant
         */
        JPanel panelEmailModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelEmailModifEns);

        JLabel lblEmailModifEns = new JLabel("Email:");
        panelEmailModifEns.add(lblEmailModifEns);

        textFieldEmailEnsM = new JTextField();
        panelEmailModifEns.add(textFieldEmailEnsM);
        textFieldEmailEnsM.setColumns(10);

        /**
         * Creation du panel comportant la selection du mdp pour modifier un enseignant
         */
        JPanel panelMdpModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelMdpModifEns);

        JLabel lblMdpModifEns = new JLabel("Mot de passe:");
        panelMdpModifEns.add(lblMdpModifEns);

        textFieldMdpEnsM = new JTextField();
        panelMdpModifEns.add(textFieldMdpEnsM);
        textFieldMdpEnsM.setColumns(10);
        
        /**
         * Creation de la fenetre de suppression d'un enseignant
         */
        frameSupprEns = new JFrame();
        frameSupprEns.setBounds(100, 100, 450, 300);
        frameSupprEns.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSupprEns.getContentPane().setLayout(new BoxLayout(frameSupprEns.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour supprimer un enseignant
         */
        JPanel panelSupprEns = new JPanel();
        frameSupprEns.getContentPane().add(panelSupprEns);
        
        JLabel lblSupprEns = new JLabel("Id:");
        panelSupprEns.add(lblSupprEns);
        
        EnseignantDAO enseignantDAOsup = new EnseignantDAO();
        idBoxEnsS = new JComboBox();
        for (int i = 0; i < enseignantDAOsup.getList().size(); i++) {
        idBoxEnsS.addItem(enseignantDAOsup.getList().get(i).getId());
        }
        panelSupprEns.add(idBoxEnsS);
        
        /**
         * Cr�ation de la fen�tre de modif d'un �tudiant
         */
        frameModif = new JFrame();
        frameModif.setBounds(100, 100, 450, 300);
        frameModif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameModif.getContentPane().setLayout(new BoxLayout(frameModif.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Cr�ation du panel comportant la s�lection de l'id pour modifier un �tudiant
         */
        JPanel panelModif = new JPanel();
        frameModif.getContentPane().add(panelModif);
        
        JLabel lblModif = new JLabel("Id:");
        panelModif.add(lblModif);
        
      //On r�cup�re l'id des �tudiants cr�es dans la BDD pour les afficher ds le menu d�roulant 
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        idBox = new JComboBox();
        for (int i = 0; i < etudiantDAO.getList().size(); i++) {
        idBox.addItem(etudiantDAO.getList().get(i).getId());
        }
        panelModif.add(idBox);

        /**
         * Cr�ation du panel comportant la s�lection du groupe pour modifier un �tudiant
         */
        JPanel panelGroupeModif = new JPanel();
        frameModif.getContentPane().add(panelGroupeModif);
        
        JLabel lblGroupeModif = new JLabel("Groupe:");
        panelGroupeModif.add(lblGroupeModif);
        
        GroupeDAO groupeDAOmodif = new GroupeDAO();  
        groupeBoxM = new JComboBox();
        for (int i = 0; i < groupeDAOmodif.getList().size(); i++) {
        groupeBoxM.addItem(groupeDAOmodif.getList().get(i).getId());
        }
        panelGroupeModif.add(groupeBoxM);

		/**
		 * Cr�ation du panel comportant la s�lection du nom pour modifier un �tudiant
		 */
        JPanel panelNomModif = new JPanel();
        frameModif.getContentPane().add(panelNomModif);

        JLabel lblNomModif = new JLabel("Nom:");
        panelNomModif.add(lblNomModif);

        textFieldNomModif = new JTextField();
        panelNomModif.add(textFieldNomModif);
        textFieldNomModif.setColumns(10);

        /**
         * Cr�ation du panel comportant la s�lection du pr�nom pour modifier un �tudiant
         */
        JPanel panelPrenomModif = new JPanel();
        frameModif.getContentPane().add(panelPrenomModif);

        JLabel lblPrenomModif = new JLabel("Prenom:");
        panelPrenomModif.add(lblPrenomModif);

        textFieldPrenomModif = new JTextField();
        panelPrenomModif.add(textFieldPrenomModif);
        textFieldPrenomModif.setColumns(10);

        /**
         * Cr�ation du panel comportant la s�lection de la fili�re pour modifier un �tudiant
         */
        JPanel panelFiliereModif = new JPanel();
        frameModif.getContentPane().add(panelFiliereModif);

        JLabel lblFiliereModif = new JLabel("Filiere:");
        panelFiliereModif.add(lblFiliereModif);

        textFieldFiliereModif = new JTextField();
        panelFiliereModif.add(textFieldFiliereModif);
        textFieldFiliereModif.setColumns(10);

        /**
         * Cr�ation du panel comportant la s�lection du mail pour modifier un �tudiant
         */
        JPanel panelEmailModif = new JPanel();
        frameModif.getContentPane().add(panelEmailModif);

        JLabel lblEmailModif = new JLabel("Email:");
        panelEmailModif.add(lblEmailModif);

        textFieldEmailModif = new JTextField();
        panelEmailModif.add(textFieldEmailModif);
        textFieldEmailModif.setColumns(10);

        /**
         * Cr�ation du panel comportant la s�lection du mdp pour modifier un �tudiant
         */
        JPanel panelMdpModif = new JPanel();
        frameModif.getContentPane().add(panelMdpModif);

        JLabel lblMdpModif = new JLabel("Mot de passe:");
        panelMdpModif.add(lblMdpModif);

        textFieldMdpModif = new JTextField();
        panelMdpModif.add(textFieldMdpModif);
        textFieldMdpModif.setColumns(10);

        /**
         * Cr�ation de la fen�tre de suppression d'un �tudiant
         */
        frameSuppr = new JFrame();
        frameSuppr.setBounds(100, 100, 450, 300);
        frameSuppr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSuppr.getContentPane().setLayout(new BoxLayout(frameSuppr.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Cr�ation du panel comportant la s�lection de l'id pour supprimer un �tudiant
         */
        JPanel panelSuppr = new JPanel();
        frameSuppr.getContentPane().add(panelSuppr);
        
        JLabel lblSuppr = new JLabel("Id:");
        panelSuppr.add(lblSuppr);
        
        EtudiantDAO etudiantDAOsup = new EtudiantDAO();
        idBoxS = new JComboBox();
        for (int i = 0; i < etudiantDAOsup.getList().size(); i++) {
        idBoxS.addItem(etudiantDAOsup.getList().get(i).getId());
        }
        panelSuppr.add(idBoxS);
        
        /**
         * Cr�ation du panel qui comportera tous les boutons de la fen�tre UC cr�er/modifier un �tudiant
         */
        JPanel panelBoutons = new JPanel();
        frame.getContentPane().add(panelBoutons);
        /**
         * Cr�ation du bouton qui permet d'ajouter un �tudiant dans la BDD
         */
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
                int groupe = (int)groupeBox.getSelectedItem();
                String nom = textFieldNom.getText();
                String prenom = textFieldPrenom.getText();
                String filiere = textFieldFiliere.getText();
                String email = textFieldEmail.getText();
                String mdp = textFieldMdp.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                Etudiant etudiant = new Etudiant(groupe, nom, prenom, filiere, email, mdp);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.add(etudiant);
            }
        });
        panelBoutons.add(btnAjouter);
        
        /**
         * Cr�ation du bouton qui permet d'ouvrir la fen�tre de suppression d'un �tudiant
         */
        JPanel panelBoutonSuppr = new JPanel();
        frameSuppr.getContentPane().add(panelBoutonSuppr);
        JButton btnSupprimer1 = new JButton("Supprimer");
        btnSupprimer1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameSuppr.setVisible(true);
            }
        });
        panelBoutons.add(btnSupprimer1);
       
        /**
         * cr�ation du bouton qui permet de supprimer un �tudiant dans la BDD
         */
        JButton btnSupprimer2 = new JButton("Supprimer");
        btnSupprimer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'identifiant de l'étudiant à supprimer
            	int id = (int)idBoxS.getSelectedItem();
                
                // Appeler la méthode de suppression d'un étudiant de la base de données
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.delete(id);
            }
        });
        panelBoutonSuppr.add(btnSupprimer2);
        
        /**
         * Cr�ation du bouton qui permet d'ouvrir la fen�tre de modification d'un �tudiant
         */
        JButton btnModifier1 = new JButton("Modifier");
        btnModifier1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModif.setVisible(true);
            }
        });
        panelBoutons.add(btnModifier1);
        
        /**
         * Cr�ation du bouton qui permet de modifier un �tudiant dans la BDD
         */
        JPanel panelBoutonModif = new JPanel();
        frameModif.getContentPane().add(panelBoutonModif);
        JButton btnModifier2 = new JButton("Modifier");
        btnModifier2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
            	int id = (int)idBox.getSelectedItem();
            	int groupe = (int)groupeBoxM.getSelectedItem();
                String nom = textFieldNomModif.getText();
                String prenom = textFieldPrenomModif.getText();
                String filiere = textFieldFiliereModif.getText();
                String email = textFieldEmailModif.getText();
                String mdp = textFieldMdpModif.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                Etudiant etudiant = new Etudiant(id, groupe, nom, prenom, filiere, email, mdp);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.update(etudiant);
            }
        });
        panelBoutonModif.add(btnModifier2);
        
        /**
         * Cr�ation du bouton du UC cr�er modifier etudiant
         */
        JButton btnUC1 = new JButton("Créer/Modifier un étudiant");
        btnUC1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC1);
        
        /**
         * Cr�ation du bouton du UC 2
         */
        JButton btnUC2 = new JButton("Traiter un justificatif");
        btnUC2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC2frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC2);
        
        /**
         * Cr�ation du bouton du UC 3
         */
        JButton btnUC3 = new JButton("Déclencher une pénalité");
        btnUC3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC3frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC3);
        
        /**
         * Cr�ation du bouton du UC 4
         */
        JButton btnUC4 = new JButton("Créer/Modifier un type d'absence");
        btnUC4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC4frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC4);
        
        /**
         * Cr�ation du bouton du UC 5
         */
        JButton btnUC5 = new JButton("Créer/Modifier un groupe d'étudiant");
        btnUC5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC5frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC5);
        
        /**
         * Création du panel qui comportera tous les boutons de la fenêtre UC créer/modifier un groupe
         */
        JPanel panelBoutonsGroupe = new JPanel();
        UC5frame.getContentPane().add(panelBoutonsGroupe);
        /**
         * Cr�ation du bouton qui permet d'ajouter un groupe dans la BDD
         */
        JButton btnAjouterGroupe = new JButton("Ajouter");
        btnAjouterGroupe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
            	int num = Integer.parseInt(textFieldNum.getText());
                int CM = Integer.parseInt(textFieldCM.getText());
                
                // Créer un objet Etudiant avec les données récupérées
                Groupe_Etudiant groupe_etudiant = new Groupe_Etudiant(num, CM);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                GroupeDAO groupeDAO = new GroupeDAO();
                groupeDAO.add(groupe_etudiant);
            }
        });
        panelBoutonsGroupe.add(btnAjouterGroupe);
        
        /**
         * Cr�ation du bouton qui permet d'ouvrir la fenetre de modification d'un groupe
         */
        JButton btnModifierGroupe = new JButton("Modifier");
        btnModifierGroupe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModifGroupe.setVisible(true);
            }
        });
        panelBoutonsGroupe.add(btnModifierGroupe);
        
        /**
         * Cr�ation du bouton qui permet de modifier un groupe dans la BDD
         */
        JPanel panelBoutonModifGroupe = new JPanel();
        frameModifGroupe.getContentPane().add(panelBoutonModifGroupe);
        JButton btnModifierGroupe2 = new JButton("Modifier");
        btnModifierGroupe2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
            	int id = (int)idBoxGroupe.getSelectedItem();
            	int num = Integer.parseInt(textFieldNumM.getText());
                int CM = Integer.parseInt(textFieldCMM.getText());
                
                // Créer un objet Etudiant avec les données récupérées
                Groupe_Etudiant groupe_etudiant = new Groupe_Etudiant(id, num, CM);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                GroupeDAO groupeDAO = new GroupeDAO();
                groupeDAO.update(groupe_etudiant);
            }
        });
        panelBoutonModifGroupe.add(btnModifierGroupe2);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de suppression d'un groupe
         */
        JPanel panelBoutonSupprGroupe = new JPanel();
        frameSupprGroupe.getContentPane().add(panelBoutonSupprGroupe);
        JButton btnSupprimerGroupe = new JButton("Supprimer");
        btnSupprimerGroupe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameSupprGroupe.setVisible(true);
            }
        });
        panelBoutonsGroupe.add(btnSupprimerGroupe);
       
        /**
         * creation du bouton qui permet de supprimer un groupe dans la BDD
         */
        JButton btnSupprimerGroupe2 = new JButton("Supprimer");
        btnSupprimerGroupe2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'identifiant du groupe à supprimer
            	int id = (int)idBoxGroupeS.getSelectedItem();
                
                // Appeler la méthode de suppression d'un étudiant de la base de données
                GroupeDAO groupeDAO = new GroupeDAO();
                groupeDAO.delete(id);
            }
        });
        panelBoutonSupprGroupe.add(btnSupprimerGroupe2);
        
        
        /**
         * Cr�ation du bouton du UC 6
         */
        JButton btnUC6 = new JButton("Créer/Modifier un cours");
        btnUC6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC6frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC6);
        
        /**
         * Cr�ation du bouton du UC 7
         */
        JButton btnUC7 = new JButton("Créer/Modifier un planning");
        btnUC7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC7frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC7);
        
        /**
         * Cr�ation du bouton du UC 8
         */
        JButton btnUC8 = new JButton("Définir les quotas");
        btnUC8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC8frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC8);
        
        /**
         * Cr�ation du bouton du UC 9
         */
        JButton btnUC9 = new JButton("Créer/Modifier un enseignant");
        btnUC9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC9frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC9);
        
        /**
         * Création du panel qui comportera tous les boutons de la fenêtre UC créer/modifier un enseignant
         */
        JPanel panelBoutonsEns = new JPanel();
        UC9frame.getContentPane().add(panelBoutonsEns);
        /**
         * Cr�ation du bouton qui permet d'ajouter un enseignant dans la BDD
         */
        JButton btnAjouterEns = new JButton("Ajouter");
        btnAjouterEns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
                String nom = textFieldNomEns.getText();
                String prenom = textFieldPrenomEns.getText();
                String tel = textFieldTel.getText();
                String email = textFieldEmailEns.getText();
                String mdp = textFieldMdpEns.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                Enseignant enseignant = new Enseignant(nom, prenom, tel, email, mdp);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                EnseignantDAO enseignantDAO = new EnseignantDAO();
                enseignantDAO.add(enseignant);
            }
        });
        panelBoutonsEns.add(btnAjouterEns);
        
        /**
         * Cr�ation du bouton qui permet d'ouvrir la fen�tre de modification d'un �tudiant
         */
        JButton btnModifierEns = new JButton("Modifier");
        btnModifierEns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModifEns.setVisible(true);
            }
        });
        panelBoutonsEns.add(btnModifierEns);
        
        /**
         * Cr�ation du bouton qui permet de modifier un �tudiant dans la BDD
         */
        JPanel panelBoutonModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelBoutonModifEns);
        JButton btnModifierEns2 = new JButton("Modifier");
        btnModifierEns2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
            	int id = (int)idBoxEns.getSelectedItem();
                String nom = textFieldNomEnsM.getText();
                String prenom = textFieldPrenomEnsM.getText();
                String tel = textFieldTelM.getText();
                String email = textFieldEmailEnsM.getText();
                String mdp = textFieldMdpEnsM.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                Enseignant enseignant = new Enseignant(id, nom, prenom, tel, email, mdp);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                EnseignantDAO enseignantDAO = new EnseignantDAO();
                enseignantDAO.update(enseignant);
            }
        });
        panelBoutonModifEns.add(btnModifierEns2);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de suppression d'un enseignant
         */
        JPanel panelBoutonSupprEns = new JPanel();
        frameSupprEns.getContentPane().add(panelBoutonSupprEns);
        JButton btnSupprimerEns = new JButton("Supprimer");
        btnSupprimerEns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameSupprEns.setVisible(true);
            }
        });
        panelBoutonsEns.add(btnSupprimerEns);
       
        /**
         * creation du bouton qui permet de supprimer un enseignant dans la BDD
         */
        JButton btnSupprimerEns2 = new JButton("Supprimer");
        btnSupprimerEns2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'identifiant de l'enseignant à supprimer
            	int id = (int)idBoxEnsS.getSelectedItem();
                
                // Appeler la méthode de suppression d'un étudiant de la base de données
                EnseignantDAO enseignantDAO = new EnseignantDAO();
                enseignantDAO.delete(id);
            }
        });
        panelBoutonSupprEns.add(btnSupprimerEns2);
    }
}
       
