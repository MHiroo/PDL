package gui;

import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.Type_absenceDAO;
import model.Cours;
import model.Enseignant;
import model.Etudiant;
import model.Groupe_Etudiant;
import model.TYPE_ABSENCE;

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
	private JFrame UC9frame;
	JFrame pframe;
	private JFrame frameModifType;
	private JFrame frameSupprType;
     JFrame frame;
    private JFrame frameModif;
    private JFrame frameModifEns;
    private JFrame frameModifGroupe;
    private JFrame frameSuppr;
    private JFrame frameSupprEns;
    private JFrame frameSupprGroupe;
    private JFrame frameModifCours;
    private JFrame frameSupprCours;
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
    private JTextField textFieldNomCours;
    private JTextField textFieldMHA;
    private JTextField textFieldMHE;
    private JTextField textFieldTP;
    private JTextField textFieldTD;
    private JTextField textFieldNomCoursM;
    private JTextField textFieldMHAM;
    private JTextField textFieldMHEM;
    private JTextField textFieldTPM;
    private JTextField textFieldTDM;
    private JTextField textFieldDesignation;
    private JTextField textFieldQuota;
    private JTextField textFieldDesignationM;
    private JTextField textFieldQuotaM;
    private JComboBox groupeBox;
    private JComboBox groupeBoxM;
    private JComboBox idBox;
    private JComboBox idBoxEns;
    private JComboBox idBoxEnsS;
    private JComboBox idBoxS;
    private JComboBox idBoxGroupe;
    private JComboBox idBoxGroupeS;
    private JComboBox idBoxCours;
    private JComboBox idBoxCoursS;
    private JComboBox idBoxType;
    private JComboBox idBoxTypeS;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionnaireGUI window = new GestionnaireGUI();
                    window.pframe.setLocationRelativeTo(null);
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
    	 * Creation de la fenetre principale
    	 */
    	pframe = new JFrame();
    	pframe.setBounds(100, 100, 450, 400);
    	pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pframe.getContentPane().setLayout(new BoxLayout(pframe.getContentPane(), BoxLayout.Y_AXIS));
    	
        /**
         * Creation du panel comportant les UC part 1
         */
    	JPanel panelUC1 = new JPanel();
    	pframe.getContentPane().add(panelUC1);
    	
    	/**
         * Creation du panel comportant les UC part 2
         */
    	JPanel panelUC2 = new JPanel();
    	pframe.getContentPane().add(panelUC2);
    	
    	/**
         * Creation du panel comportant les UC part 3
         */
    	JPanel panelUC3 = new JPanel();
    	pframe.getContentPane().add(panelUC3); 
    	
    	/**
         * Creation du panel comportant les UC part 4
         */
    	JPanel panelUC4 = new JPanel();
    	pframe.getContentPane().add(panelUC4);   
    	
    	/**
    	 * Creation de la fenetre UC2
    	 */
    	UC2frame = new JFrame();
    	UC2frame.setBounds(100, 100, 450, 300);
        UC2frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UC2frame.getContentPane().setLayout(new BoxLayout(UC2frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Creation de la fenetre UC3
    	 */
    	UC3frame = new JFrame();
    	UC3frame.setBounds(100, 100, 450, 300);
        UC3frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UC3frame.getContentPane().setLayout(new BoxLayout(UC3frame.getContentPane(), BoxLayout.Y_AXIS));
        
    	/**
    	 * Creation de la fenetre UC4
    	 */
    	UC4frame = new JFrame();
    	UC4frame.setBounds(100, 100, 450, 300);
        UC4frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UC4frame.getContentPane().setLayout(new BoxLayout(UC4frame.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection du quota pour ajouter un type d'absence
         */
        JPanel panelQuota = new JPanel();
        UC4frame.getContentPane().add(panelQuota);

        JLabel lblQuota = new JLabel("Quota:");
        panelQuota.add(lblQuota);

        textFieldQuota = new JTextField();
        panelQuota.add(textFieldQuota);
        textFieldQuota.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la designation pour ajouter un type d'absence
         */
        JPanel panelDesignation = new JPanel();
        UC4frame.getContentPane().add(panelDesignation);

        JLabel lblDesignation = new JLabel("Designation:");
        panelDesignation.add(lblDesignation);

        textFieldDesignation = new JTextField();
        panelDesignation.add(textFieldDesignation);
        textFieldDesignation.setColumns(10);
        
        /**
         * Cr�ation de la fenetre de modification d'un type d'absence
         */
        frameModifType = new JFrame();
        frameModifType.setBounds(100, 100, 450, 300);
        frameModifType.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModifType.getContentPane().setLayout(new BoxLayout(frameModifType.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour modifier un type d'absence
         */
        JPanel panelIdM = new JPanel();
        frameModifType.getContentPane().add(panelIdM);
        
        JLabel lblIdM = new JLabel("Id:");
        panelIdM.add(lblIdM);
        
      //On recupere l'id des type d'absences crees dans la BDD pour les afficher ds le menu deroulant 
        Type_absenceDAO type_absenceDAO = new Type_absenceDAO();
        idBoxType = new JComboBox();
        for (int i = 0; i < type_absenceDAO.getList().size(); i++) {
        idBoxType.addItem(type_absenceDAO.getList().get(i).getId());
        }
        panelIdM.add(idBoxType);
        
        /**
         * Creation du panel comportant la selection du quota pour modifier un type d'absence
         */
        JPanel panelQuotaM = new JPanel();
        frameModifType.getContentPane().add(panelQuotaM);

        JLabel lblQuotaM = new JLabel("Quota:");
        panelQuotaM.add(lblQuotaM);

        textFieldQuotaM = new JTextField();
        panelQuotaM.add(textFieldQuotaM);
        textFieldQuotaM.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la designation pour modifier un type d'absence
         */
        JPanel panelDesignationM = new JPanel();
        frameModifType.getContentPane().add(panelDesignationM);

        JLabel lblDesignationM = new JLabel("Designation:");
        panelDesignationM.add(lblDesignationM);

        textFieldDesignationM = new JTextField();
        panelDesignationM.add(textFieldDesignationM);
        textFieldDesignationM.setColumns(10);
        
        /**
         * Cr�ation de la fenetre de suppression d'un type d'absence
         */
        frameSupprType = new JFrame();
        frameSupprType.setBounds(100, 100, 450, 300);
        frameSupprType.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSupprType.getContentPane().setLayout(new BoxLayout(frameSupprType.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour supprimer un type d'absence
         */
        JPanel panelIdS = new JPanel();
        frameSupprType.getContentPane().add(panelIdS);
        
        JLabel lblIdS = new JLabel("Id:");
        panelIdS.add(lblIdS);
        
      //On recupere l'id des type d'absences crees dans la BDD pour les afficher ds le menu deroulant 
        Type_absenceDAO type_absenceDAO2 = new Type_absenceDAO();
        idBoxTypeS = new JComboBox();
        for (int i = 0; i < type_absenceDAO2.getList().size(); i++) {
        idBoxTypeS.addItem(type_absenceDAO2.getList().get(i).getId());
        }
        panelIdS.add(idBoxTypeS);
        
        
    	/**
    	 * Creation de la fenetre UC5
    	 */
    	UC5frame = new JFrame();
    	UC5frame.setBounds(100, 100, 450, 300);
        UC5frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UC5frame.getContentPane().setLayout(new BoxLayout(UC5frame.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection du num pour ajouter un groupe
         */
        JPanel panelNum = new JPanel();
        UC5frame.getContentPane().add(panelNum);

        JLabel lblNum = new JLabel("Num:");
        panelNum.add(lblNum);

        textFieldNum = new JTextField();
        panelNum.add(textFieldNum);
        textFieldNum.setColumns(10);

        /**
         * Creation du panel comportant la selection de la capacite max pour ajouter un groupe
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
        frameModifGroupe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModifGroupe.getContentPane().setLayout(new BoxLayout(frameModifGroupe.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour modifier un groupe
         */
        JPanel panelModifGroupe = new JPanel();
        frameModifGroupe.getContentPane().add(panelModifGroupe);
        
        JLabel lblModifGroupe = new JLabel("Id:");
        panelModifGroupe.add(lblModifGroupe);
        
      //On recupere l'id des etudiants crees dans la BDD pour les afficher ds le menu deroulant 
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
        frameSupprGroupe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    	 * Creation de la fenetre UC6
    	 */
    	UC6frame = new JFrame();
    	UC6frame.setBounds(100, 100, 450, 300);
        UC6frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UC6frame.getContentPane().setLayout(new BoxLayout(UC6frame.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection du nom pour ajouter un cours
         */
        JPanel panelNomCours = new JPanel();
        UC6frame.getContentPane().add(panelNomCours);

        JLabel lblNomCours = new JLabel("Nom du cours:");
        panelNomCours.add(lblNomCours);

        textFieldNomCours = new JTextField();
        panelNomCours.add(textFieldNomCours);
        textFieldNomCours.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire amphi pour ajouter un cours
         */
        JPanel panelMHA = new JPanel();
        UC6frame.getContentPane().add(panelMHA);

        JLabel lblMHA = new JLabel("Masse horaire en amphi:");
        panelMHA.add(lblMHA);

        textFieldMHA = new JTextField();
        panelMHA.add(textFieldMHA);
        textFieldMHA.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire TD pour ajouter un cours
         */
        JPanel panelTD = new JPanel();
        UC6frame.getContentPane().add(panelTD);

        JLabel lblTD = new JLabel("Masse horaire en TD:");
        panelTD.add(lblTD);

        textFieldTD = new JTextField();
        panelTD.add(textFieldTD);
        textFieldTD.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire TP pour ajouter un cours
         */
        JPanel panelTP = new JPanel();
        UC6frame.getContentPane().add(panelTP);

        JLabel lblTP = new JLabel("Masse horaire en TP:");
        panelTP.add(lblTP);

        textFieldTP = new JTextField();
        panelTP.add(textFieldTP);
        textFieldTP.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire exam pour ajouter un cours
         */
        JPanel panelMHE = new JPanel();
        UC6frame.getContentPane().add(panelMHE);

        JLabel lblMHE = new JLabel("Masse horaire en exam:");
        panelMHE.add(lblMHE);

        textFieldMHE = new JTextField();
        panelMHE.add(textFieldMHE);
        textFieldMHE.setColumns(10);
        
        /**
         * Creation de la fenetre de modif d'un cours
         */
        frameModifCours = new JFrame();
        frameModifCours.setBounds(100, 100, 450, 300);
        frameModifCours.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModifCours.getContentPane().setLayout(new BoxLayout(frameModifCours.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour modifier un cours
         */
        JPanel panelModifCours = new JPanel();
        frameModifCours.getContentPane().add(panelModifCours);
        
        JLabel lblModifCours = new JLabel("Id:");
        panelModifCours.add(lblModifCours);
        
        //On recupere l'id des cours crees dans la BDD pour les afficher ds le menu deroulant 
        CoursDAO coursDAO = new CoursDAO();
        idBoxCours = new JComboBox();
        for (int i = 0; i < coursDAO.getList().size(); i++) {
        	idBoxCours.addItem(coursDAO.getList().get(i).getId());
        }
        panelModifCours.add(idBoxCours);
        
        
        /**
         * Creation du panel comportant la selection du nom pour modifier un cours
         */
        JPanel panelNomCoursM = new JPanel();
        frameModifCours.getContentPane().add(panelNomCoursM);

        JLabel lblNomCoursM = new JLabel("Nom du cours:");
        panelNomCoursM.add(lblNomCoursM);

        textFieldNomCoursM = new JTextField();
        panelNomCoursM.add(textFieldNomCoursM);
        textFieldNomCoursM.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire amphi pour modifier un cours
         */
        JPanel panelMHAM = new JPanel();
        frameModifCours.getContentPane().add(panelMHAM);

        JLabel lblMHAM = new JLabel("Masse horaire en amphi:");
        panelMHAM.add(lblMHAM);

        textFieldMHAM = new JTextField();
        panelMHAM.add(textFieldMHAM);
        textFieldMHAM.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire TD pour modifier un cours
         */
        JPanel panelTDM = new JPanel();
        frameModifCours.getContentPane().add(panelTDM);

        JLabel lblTDM = new JLabel("Masse horaire en TD:");
        panelTDM.add(lblTDM);

        textFieldTDM = new JTextField();
        panelTDM.add(textFieldTDM);
        textFieldTDM.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire TP pour modifier un cours
         */
        JPanel panelTPM = new JPanel();
        frameModifCours.getContentPane().add(panelTPM);

        JLabel lblTPM = new JLabel("Masse horaire en TP:");
        panelTPM.add(lblTPM);

        textFieldTPM = new JTextField();
        panelTPM.add(textFieldTPM);
        textFieldTPM.setColumns(10);
        
        /**
         * Creation du panel comportant la selection de la masse horaire exam pour modifier un cours
         */
        JPanel panelMHEM = new JPanel();
        frameModifCours.getContentPane().add(panelMHEM);

        JLabel lblMHEM = new JLabel("Masse horaire en exam:");
        panelMHEM.add(lblMHEM);

        textFieldMHEM = new JTextField();
        panelMHEM.add(textFieldMHEM);
        textFieldMHEM.setColumns(10);
        
        /**
         * Creation de la fenetre de SUPPR d'un cours
         */
        frameSupprCours = new JFrame();
        frameSupprCours.setBounds(100, 100, 450, 300);
        frameSupprCours.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSupprCours.getContentPane().setLayout(new BoxLayout(frameSupprCours.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour supprimer un cours
         */
        JPanel panelSupprCours = new JPanel();
        frameSupprCours.getContentPane().add(panelSupprCours);
        
        JLabel lblSupprCours = new JLabel("Id:");
        panelSupprCours.add(lblSupprCours);
        
        //On recupere l'id des cours crees dans la BDD pour les afficher ds le menu deroulant 
        CoursDAO coursDAO2 = new CoursDAO();
        idBoxCoursS = new JComboBox();
        for (int i = 0; i < coursDAO2.getList().size(); i++) {
        	idBoxCoursS.addItem(coursDAO2.getList().get(i).getId());
        }
        panelSupprCours.add(idBoxCoursS);
        
    
    	/**
    	 * Creation de la fenetre UC9
    	 */
    	UC9frame = new JFrame();
    	UC9frame.setBounds(100, 100, 450, 300);
        UC9frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UC9frame.getContentPane().setLayout(new BoxLayout(UC9frame.getContentPane(), BoxLayout.Y_AXIS));
    	
        /**
         * Creation du panel comportant la selection du nom pour ajouter un enseignant
         */
        JPanel panelNomEns = new JPanel();
        UC9frame.getContentPane().add(panelNomEns);

        JLabel lblNomEns = new JLabel("Nom:");
        panelNomEns.add(lblNomEns);

        textFieldNomEns = new JTextField();
        panelNomEns.add(textFieldNomEns);
        textFieldNomEns.setColumns(10);

        /**
         * Creation du panel comportant la selection du prenom pour ajouter un enseignant
         */
        JPanel panelPrenomEns = new JPanel();
        UC9frame.getContentPane().add(panelPrenomEns);

        JLabel lblPrenomEns = new JLabel("Prenom:");
        panelPrenomEns.add(lblPrenomEns);

        textFieldPrenomEns = new JTextField();
        panelPrenomEns.add(textFieldPrenomEns);
        textFieldPrenomEns.setColumns(10);
        
        /**
         * Creation du panel comportant la selection du tel pour ajouter un enseignant
         */
        JPanel panelTel = new JPanel();
        UC9frame.getContentPane().add(panelTel);
        
        JLabel lblTel = new JLabel("Tel:");
        panelTel.add(lblTel);
        
        textFieldTel = new JTextField();
        panelTel.add(textFieldTel);
        textFieldTel.setColumns(10);
        
        /**
         * Creation du panel comportant la selection du mail pour ajouter un enseignant
         */
        JPanel panelEmailEns = new JPanel();
        UC9frame.getContentPane().add(panelEmailEns);
        
        JLabel lblEmailEns = new JLabel("Email:");
        panelEmailEns.add(lblEmailEns);
        
        textFieldEmailEns = new JTextField();
        panelEmailEns.add(textFieldEmailEns);
        textFieldEmailEns.setColumns(10);
        
        /**
         * Creation du panel comportant la selection du mdp pour ajouter un enseignant
         */
        JPanel panelMdpEns = new JPanel();
        UC9frame.getContentPane().add(panelMdpEns);

        JLabel lblMdpEns = new JLabel("Mot de passe:");
        panelMdpEns.add(lblMdpEns);

        textFieldMdpEns = new JTextField();
        panelMdpEns.add(textFieldMdpEns);
        textFieldMdpEns.setColumns(10);
        
    	/**
    	 * Creation de la fenetre pour le UC Creer/modifier un etudiant
    	 */
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection du groupe pour ajouter un etudiant
         */
        JPanel panelGroupe = new JPanel();
        frame.getContentPane().add(panelGroupe);
        
        JLabel lblGroupe = new JLabel("Groupe:");
        panelGroupe.add(lblGroupe);
        
        //On recupere l'id des groupes creer das la BDD pour les afficher ds le menu deroulant
        GroupeDAO groupeDAO2 = new GroupeDAO();  
        groupeBox = new JComboBox();
        for (int i = 0; i < groupeDAO2.getList().size(); i++) {
        groupeBox.addItem(groupeDAO2.getList().get(i).getId());
        }
        panelGroupe.add(groupeBox);

        /**
         * Creation du panel comportant la selection du nom pour ajouter un etudiant
         */
        JPanel panelNom = new JPanel();
        frame.getContentPane().add(panelNom);

        JLabel lblNom = new JLabel("Nom:");
        panelNom.add(lblNom);

        textFieldNom = new JTextField();
        panelNom.add(textFieldNom);
        textFieldNom.setColumns(10);

        /**
         * Creation du panel comportant la selection du prenom pour ajouter un etudiant
         */
        JPanel panelPrenom = new JPanel();
        frame.getContentPane().add(panelPrenom);

        JLabel lblPrenom = new JLabel("Prenom:");
        panelPrenom.add(lblPrenom);

        textFieldPrenom = new JTextField();
        panelPrenom.add(textFieldPrenom);
        textFieldPrenom.setColumns(10);

        /**
         * Creation du panel comportant la selection de la filiere pour ajouter un etudiant
         */
        JPanel panelFiliere = new JPanel();
        frame.getContentPane().add(panelFiliere);

        JLabel lblFiliere = new JLabel("Filiere:");
        panelFiliere.add(lblFiliere);

        textFieldFiliere = new JTextField();
        panelFiliere.add(textFieldFiliere);
        textFieldFiliere.setColumns(10);

		/**
		 * Creation du panel comportant la selection du mail pour ajouter un etudiant
		 */
        JPanel panelEmail = new JPanel();
        frame.getContentPane().add(panelEmail);

        JLabel lblEmail = new JLabel("Email:");
        panelEmail.add(lblEmail);

        textFieldEmail = new JTextField();
        panelEmail.add(textFieldEmail);
        textFieldEmail.setColumns(10);
        
        /**
         * Creation du panel comportant la selection du mdp pour ajouter un etudiant
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
        frameModifEns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModifEns.getContentPane().setLayout(new BoxLayout(frameModifEns.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour modifier un etudiant
         */
        JPanel panelModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelModifEns);
        
        JLabel lblModifEns = new JLabel("Id:");
        panelModifEns.add(lblModifEns);
        
      //On recupere l'id des etudiants crees dans la BDD pour les afficher ds le menu deroulant 
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
        frameSupprEns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
         * Creation de la fenetre de modif d'un etudiant
         */
        frameModif = new JFrame();
        frameModif.setBounds(100, 100, 450, 300);
        frameModif.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameModif.getContentPane().setLayout(new BoxLayout(frameModif.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour modifier un etudiant
         */
        JPanel panelModif = new JPanel();
        frameModif.getContentPane().add(panelModif);
        
        JLabel lblModif = new JLabel("Id:");
        panelModif.add(lblModif);
        
      //On recupere l'id des etudiants crees dans la BDD pour les afficher ds le menu deroulant 
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        idBox = new JComboBox();
        for (int i = 0; i < etudiantDAO.getList().size(); i++) {
        idBox.addItem(etudiantDAO.getList().get(i).getId());
        }
        panelModif.add(idBox);

        /**
         * Creation du panel comportant la selection du groupe pour modifier un etudiant
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
		 * Creation du panel comportant la selection du nom pour modifier un etudiant
		 */
        JPanel panelNomModif = new JPanel();
        frameModif.getContentPane().add(panelNomModif);

        JLabel lblNomModif = new JLabel("Nom:");
        panelNomModif.add(lblNomModif);

        textFieldNomModif = new JTextField();
        panelNomModif.add(textFieldNomModif);
        textFieldNomModif.setColumns(10);

        /**
         * Creation du panel comportant la selection du prenom pour modifier un etudiant
         */
        JPanel panelPrenomModif = new JPanel();
        frameModif.getContentPane().add(panelPrenomModif);

        JLabel lblPrenomModif = new JLabel("Prenom:");
        panelPrenomModif.add(lblPrenomModif);

        textFieldPrenomModif = new JTextField();
        panelPrenomModif.add(textFieldPrenomModif);
        textFieldPrenomModif.setColumns(10);

        /**
         * Creation du panel comportant la selection de la filiere pour modifier un etudiant
         */
        JPanel panelFiliereModif = new JPanel();
        frameModif.getContentPane().add(panelFiliereModif);

        JLabel lblFiliereModif = new JLabel("Filiere:");
        panelFiliereModif.add(lblFiliereModif);

        textFieldFiliereModif = new JTextField();
        panelFiliereModif.add(textFieldFiliereModif);
        textFieldFiliereModif.setColumns(10);

        /**
         * Creation du panel comportant la selection du mail pour modifier un etudiant
         */
        JPanel panelEmailModif = new JPanel();
        frameModif.getContentPane().add(panelEmailModif);

        JLabel lblEmailModif = new JLabel("Email:");
        panelEmailModif.add(lblEmailModif);

        textFieldEmailModif = new JTextField();
        panelEmailModif.add(textFieldEmailModif);
        textFieldEmailModif.setColumns(10);

        /**
         * Creation du panel comportant la selection du mdp pour modifier un etudiant
         */
        JPanel panelMdpModif = new JPanel();
        frameModif.getContentPane().add(panelMdpModif);

        JLabel lblMdpModif = new JLabel("Mot de passe:");
        panelMdpModif.add(lblMdpModif);

        textFieldMdpModif = new JTextField();
        panelMdpModif.add(textFieldMdpModif);
        textFieldMdpModif.setColumns(10);

        /**
         * Creation de la fenetre de suppression d'un etudiant
         */
        frameSuppr = new JFrame();
        frameSuppr.setBounds(100, 100, 450, 300);
        frameSuppr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameSuppr.getContentPane().setLayout(new BoxLayout(frameSuppr.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation du panel comportant la selection de l'id pour supprimer un etudiant
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
         * Creation du panel qui comportera tous les boutons de la fenetre UC creer/modifier un etudiant
         */
        JPanel panelBoutons = new JPanel();
        frame.getContentPane().add(panelBoutons);
        /**
         * Creation du bouton qui permet d'ajouter un etudiant dans la BDD
         */
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
                int groupe = (int)groupeBox.getSelectedItem();
                String nom = textFieldNom.getText();
                String prenom = textFieldPrenom.getText();
                String filiere = textFieldFiliere.getText();
                String email = textFieldEmail.getText();
                String mdp = textFieldMdp.getText();
                
                // Creer un objet Etudiant avec les donnees recuperees
                Etudiant etudiant = new Etudiant(groupe, nom, prenom, filiere, email, mdp);
                
                // Appeler la methode d'ajout d'un etudiant dans la base de donnees
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.add(etudiant);
            }
        });
        panelBoutons.add(btnAjouter);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de suppression d'un etudiant
         */
        JPanel panelBoutonSuppr = new JPanel();
        frameSuppr.getContentPane().add(panelBoutonSuppr);
        JButton btnSupprimer1 = new JButton("Supprimer");
        btnSupprimer1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameSuppr.setLocationRelativeTo(null);
            	frameSuppr.setVisible(true);
            }
        });
        panelBoutons.add(btnSupprimer1);
       
        /**
         * creation du bouton qui permet de supprimer un etudiant dans la BDD
         */
        JButton btnSupprimer2 = new JButton("Supprimer");
        btnSupprimer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer l'identifiant de l'etudiant à supprimer
            	int id = (int)idBoxS.getSelectedItem();
                
                // Appeler la methode de suppression d'un etudiant de la base de donnees
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.delete(id);
            }
        });
        panelBoutonSuppr.add(btnSupprimer2);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de modification d'un etudiant
         */
        JButton btnModifier1 = new JButton("Modifier");
        btnModifier1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModif.setLocationRelativeTo(null);
            	frameModif.setVisible(true);
            }
        });
        panelBoutons.add(btnModifier1);
        
        /**
         * Creation du bouton qui permet de modifier un etudiant dans la BDD
         */
        JPanel panelBoutonModif = new JPanel();
        frameModif.getContentPane().add(panelBoutonModif);
        JButton btnModifier2 = new JButton("Modifier");
        btnModifier2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
            	int id = (int)idBox.getSelectedItem();
            	int groupe = (int)groupeBoxM.getSelectedItem();
                String nom = textFieldNomModif.getText();
                String prenom = textFieldPrenomModif.getText();
                String filiere = textFieldFiliereModif.getText();
                String email = textFieldEmailModif.getText();
                String mdp = textFieldMdpModif.getText();
                
                // Creer un objet Etudiant avec les donnees recuperees
                Etudiant etudiant = new Etudiant(id, groupe, nom, prenom, filiere, email, mdp);
                
                // Appeler la methode d'ajout d'un etudiant dans la base de donnees
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.update(etudiant);
            }
        });
        panelBoutonModif.add(btnModifier2);
        
        /**
         * Creation du bouton du UC creer modifier etudiant
         */
        JButton btnUC1 = new JButton("Creer/Modifier un etudiant");
        btnUC1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setLocationRelativeTo(null);
            	frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC1);
        
        /**
         * Creation du bouton du UC 2
         */
        JButton btnUC2 = new JButton("Traiter un justificatif");
        btnUC2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC2frame.setLocationRelativeTo(null);
            	UC2frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC2);
        
        /**
         * Creation du bouton du UC 3
         */
        JButton btnUC3 = new JButton("Declencher une penalite");
        btnUC3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC3frame.setLocationRelativeTo(null);
            	UC3frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC3);
        
        /**
         * Creation du bouton du UC 4
         */
        JButton btnUC4 = new JButton("Creer/Modifier un type d'absence");
        btnUC4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC4frame.setLocationRelativeTo(null);
            	UC4frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC4);
        
        /**
         * Creation du panel qui comportera tous les boutons de la fenetre UC creer/modifier un type d'absence
         */
        JPanel panelBoutonsType = new JPanel();
        UC4frame.getContentPane().add(panelBoutonsType);
        /**
         * Creation du bouton qui permet d'ajouter un type d'absence dans la BDD
         */
        JButton btnAjouterType = new JButton("Ajouter");
        btnAjouterType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
                String quota = textFieldQuota.getText();
                String designation = textFieldDesignation.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                TYPE_ABSENCE TypeAbsence = new TYPE_ABSENCE(quota, designation);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                Type_absenceDAO TypeAbsenceDAO = new Type_absenceDAO();
                TypeAbsenceDAO.add(TypeAbsence);
            }
        });
        panelBoutonsType.add(btnAjouterType);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de modification d'un type d'absence
         */
        JButton btnModifierType = new JButton("Modifier");
        btnModifierType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModifType.setLocationRelativeTo(null);
            	frameModifType.setVisible(true);
            }
        });
        panelBoutonsType.add(btnModifierType);
        
        /**
         * Creation du bouton qui permet de modifier un type d'absence dans la BDD
         */
        JPanel panelBoutonModifType = new JPanel();
        frameModifType.getContentPane().add(panelBoutonModifType);
        JButton btnModifierType2 = new JButton("Modifier");
        btnModifierType2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
            	int id = (int)idBoxType.getSelectedItem();
                String quota = textFieldQuotaM.getText();
                String designation = textFieldDesignationM.getText();
                
                // Créer un objet TypeAbsence avec les donnees recuperees
                TYPE_ABSENCE TypeAbsence = new TYPE_ABSENCE(id, quota, designation);
                
                // Appeler la methode d'ajout d'un type d'absence dans la base de donnees
                Type_absenceDAO TypeAbsenceDAO = new Type_absenceDAO();
                TypeAbsenceDAO.update(TypeAbsence);
            }
        });
        panelBoutonModifType.add(btnModifierType2);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de suppression d'un type d'absence
         */
        JButton btnSupprimerType = new JButton("Supprimer");
        btnSupprimerType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameSupprType.setLocationRelativeTo(null);
            	frameSupprType.setVisible(true);
            }
        });
        panelBoutonsType.add(btnSupprimerType);
       
        /**
         * creation du bouton qui permet de supprimer un type d'absence dans la BDD
         */
        JPanel panelBoutonSupprType = new JPanel();
        frameSupprType.getContentPane().add(panelBoutonSupprType);
        JButton btnSupprimerType2 = new JButton("Supprimer");
        btnSupprimerType2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'identifiant de l'étudiant à supprimer
            	int id = (int)idBoxTypeS.getSelectedItem();
                
                // Appeler la méthode de suppression d'un étudiant de la base de données
            	Type_absenceDAO TypeAbsenceDAO = new Type_absenceDAO();
                TypeAbsenceDAO.delete(id);
            }
        });
        panelBoutonSupprType.add(btnSupprimerType2);
        
        
        
        /**
         * Creation du bouton du UC 5
         */
        JButton btnUC5 = new JButton("Creer/Modifier un groupe d'etudiant");
        btnUC5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC5frame.setLocationRelativeTo(null);
            	UC5frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC5);
        
        /**
         * Creation du panel qui comportera tous les boutons de la fenêtre UC creer/modifier un groupe
         */
        JPanel panelBoutonsGroupe = new JPanel();
        UC5frame.getContentPane().add(panelBoutonsGroupe);
        /**
         * Creation du bouton qui permet d'ajouter un groupe dans la BDD
         */
        JButton btnAjouterGroupe = new JButton("Ajouter");
        btnAjouterGroupe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
            	int num = Integer.parseInt(textFieldNum.getText());
                int CM = Integer.parseInt(textFieldCM.getText());
                
                // Creer un objet Etudiant avec les donnees recuperees
                Groupe_Etudiant groupe_etudiant = new Groupe_Etudiant(num, CM);
                
                // Appeler la methode d'ajout d'un etudiant dans la base de donnees
                GroupeDAO groupeDAO = new GroupeDAO();
                groupeDAO.add(groupe_etudiant);
            }
        });
        panelBoutonsGroupe.add(btnAjouterGroupe);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de modification d'un groupe
         */
        JButton btnModifierGroupe = new JButton("Modifier");
        btnModifierGroupe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModifGroupe.setLocationRelativeTo(null);
            	frameModifGroupe.setVisible(true);
            }
        });
        panelBoutonsGroupe.add(btnModifierGroupe);
        
        /**
         * Creation du bouton qui permet de modifier un groupe dans la BDD
         */
        JPanel panelBoutonModifGroupe = new JPanel();
        frameModifGroupe.getContentPane().add(panelBoutonModifGroupe);
        JButton btnModifierGroupe2 = new JButton("Modifier");
        btnModifierGroupe2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
            	int id = (int)idBoxGroupe.getSelectedItem();
            	int num = Integer.parseInt(textFieldNumM.getText());
                int CM = Integer.parseInt(textFieldCMM.getText());
                
                // Creer un objet Etudiant avec les donnees recuperees
                Groupe_Etudiant groupe_etudiant = new Groupe_Etudiant(id, num, CM);
                
                // Appeler la methode d'ajout d'un etudiant dans la base de donnees
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
            	frameSupprGroupe.setLocationRelativeTo(null);
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
                // Recuperer l'identifiant du groupe à supprimer
            	int id = (int)idBoxGroupeS.getSelectedItem();
                
                // Appeler la methode de suppression d'un etudiant de la base de donnees
                GroupeDAO groupeDAO = new GroupeDAO();
                groupeDAO.delete(id);
            }
        });
        panelBoutonSupprGroupe.add(btnSupprimerGroupe2);
        
        
        /**
         * Creation du bouton du UC 6
         */
        JButton btnUC6 = new JButton("Creer/Modifier un cours");
        btnUC6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC6frame.setLocationRelativeTo(null);
            	UC6frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC6);
        
        /**
<<<<<<< HEAD
         * Creation du bouton du UC 7
=======
         * Creation du panel qui comportera tous les boutons de la fenêtre UC creer/modifier un cours
         */
        JPanel panelBoutonsCours = new JPanel();
        UC6frame.getContentPane().add(panelBoutonsCours);
        /**
         * Creation du bouton qui permet d'ajouter un cours dans la BDD
         */
        JButton btnAjouterCours = new JButton("Ajouter");
        btnAjouterCours.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
                String nom = textFieldNomCours.getText();
                int MH;
                int MHA = Integer.parseInt(textFieldMHA.getText());
                int MHTD = Integer.parseInt(textFieldTD.getText());
                int MHTP = Integer.parseInt(textFieldTP.getText());
                int MHE = Integer.parseInt(textFieldMHE.getText());
                MH = MHA + MHTD + MHTP + MHE;
                
                // Creer un objet Cours avec les donnees recuperees
                Cours cours = new Cours(nom, MH, MHA, MHTD, MHTP, MHE);
                
                // Appeler la methode d'ajout d'un Cours dans la base de donnees
                CoursDAO coursDAO = new CoursDAO();
                coursDAO.add(cours);
            }
        });
        panelBoutonsCours.add(btnAjouterCours);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de modification d'un cours
         */
        JButton btnModifierCours = new JButton("Modifier");
        btnModifierCours.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModifCours.setLocationRelativeTo(null);
            	frameModifCours.setVisible(true);
            }
        });
        panelBoutonsCours.add(btnModifierCours);
        
        /**
         * Creation du bouton qui permet de modifier un cours dans la BDD
         */
        JPanel panelBoutonModifCours = new JPanel();
        frameModifCours.getContentPane().add(panelBoutonModifCours);
        JButton btnModifierCours2 = new JButton("Modifier");
        btnModifierCours2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
            	int id = (int)idBoxCours.getSelectedItem();
            	String nom = textFieldNomCoursM.getText();
                int MH;
                int MHA = Integer.parseInt(textFieldMHAM.getText());
                int MHTD = Integer.parseInt(textFieldTDM.getText());
                int MHTP = Integer.parseInt(textFieldTPM.getText());
                int MHE = Integer.parseInt(textFieldMHEM.getText());
                MH = MHA + MHTD + MHTP + MHE;
                
                // Creer un objet Cours avec les donnees recuperees
                Cours cours = new Cours(id, nom, MH, MHA, MHTD, MHTP, MHE);
                
                // Appeler la methode de modification d'un Cours dans la base de donnees
                CoursDAO coursDAO = new CoursDAO();
                coursDAO.update(cours);
            }
        });
        panelBoutonModifCours.add(btnModifierCours2);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de suppression d'un cours
         */
        JPanel panelBoutonSupprCours = new JPanel();
        frameSupprCours.getContentPane().add(panelBoutonSupprCours);
        JButton btnSupprimerCours = new JButton("Supprimer");
        btnSupprimerCours.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameSupprCours.setLocationRelativeTo(null);
            	frameSupprCours.setVisible(true);
            }
        });
        panelBoutonsCours.add(btnSupprimerCours);
       
        /**
         * creation du bouton qui permet de supprimer un enseignant dans la BDD
         */
        JButton btnSupprimerCours2 = new JButton("Supprimer");
        btnSupprimerCours2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer l'identifiant de l'enseignant a supprimer
            	int id = (int)idBoxCoursS.getSelectedItem();
                
            	// Appeler la methode de suppression d'un Cours dans la base de donnees
                CoursDAO coursDAO = new CoursDAO();
                coursDAO.delete(id);
            }
        });
        panelBoutonSupprCours.add(btnSupprimerCours2);
        
        
        
        /**
         * Cr�ation du bouton du UC 7  ajouter/modifier  un planning
         */
        JButton btnUC7 = new JButton("Creer/Modifier un planning");
        btnUC7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	pframe.setVisible(false);
                PlanningGstGUI window = new PlanningGstGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC7);
        
        /**
         * Creation du bouton du UC 9
         */
        JButton btnUC9 = new JButton("Creer/Modifier un enseignant");
        btnUC9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC9frame.setLocationRelativeTo(null);
            	UC9frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC9);
        
        /**
         * Creation du panel qui comportera tous les boutons de la fenêtre UC creer/modifier un enseignant
         */
        JPanel panelBoutonsEns = new JPanel();
        UC9frame.getContentPane().add(panelBoutonsEns);
        /**
         * Creation du bouton qui permet d'ajouter un enseignant dans la BDD
         */
        JButton btnAjouterEns = new JButton("Ajouter");
        btnAjouterEns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
                String nom = textFieldNomEns.getText();
                String prenom = textFieldPrenomEns.getText();
                String tel = textFieldTel.getText();
                String email = textFieldEmailEns.getText();
                String mdp = textFieldMdpEns.getText();
                
                // Creer un objet Etudiant avec les donnees recuperees
                Enseignant enseignant = new Enseignant(nom, prenom, tel, email, mdp);
                
                // Appeler la methode d'ajout d'un etudiant dans la base de donnees
                EnseignantDAO enseignantDAO = new EnseignantDAO();
                enseignantDAO.add(enseignant);
            }
        });
        panelBoutonsEns.add(btnAjouterEns);
        
        /**
         * Creation du bouton qui permet d'ouvrir la fenetre de modification d'un etudiant
         */
        JButton btnModifierEns = new JButton("Modifier");
        btnModifierEns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frameModifEns.setLocationRelativeTo(null);
            	frameModifEns.setVisible(true);
            }
        });
        panelBoutonsEns.add(btnModifierEns);
        
        /**
         * Creation du bouton qui permet de modifier un etudiant dans la BDD
         */
        JPanel panelBoutonModifEns = new JPanel();
        frameModifEns.getContentPane().add(panelBoutonModifEns);
        JButton btnModifierEns2 = new JButton("Modifier");
        btnModifierEns2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
            	int id = (int)idBoxEns.getSelectedItem();
                String nom = textFieldNomEnsM.getText();
                String prenom = textFieldPrenomEnsM.getText();
                String tel = textFieldTelM.getText();
                String email = textFieldEmailEnsM.getText();
                String mdp = textFieldMdpEnsM.getText();
                
                // Creer un objet Etudiant avec les donnees recuperees
                Enseignant enseignant = new Enseignant(id, nom, prenom, tel, email, mdp);
                
                // Appeler la methode d'ajout d'un etudiant dans la base de donnees
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
            	frameSupprEns.setLocationRelativeTo(null);
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
                // Recuperer l'identifiant de l'enseignant à supprimer
            	int id = (int)idBoxEnsS.getSelectedItem();
                
                // Appeler la methode de suppression d'un etudiant de la base de donnees
                EnseignantDAO enseignantDAO = new EnseignantDAO();
                enseignantDAO.delete(id);
            }
        });
        panelBoutonSupprEns.add(btnSupprimerEns2);
    }
}
       
