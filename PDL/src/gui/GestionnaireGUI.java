package gui;

import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.EtudiantDAO;
import dao.GroupeDAO;
import model.Etudiant;
import model.Groupe_Etudiant;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GestionnaireGUI {

    private JFrame frame;
    private JFrame frameModif;
    private JFrame frameSuppr;
    private JTextField textFieldId1;
    private JTextField textFieldId2;
    private JTextField textFieldGroupeModif;
    private JTextField textFieldNomModif;
    private JTextField textFieldPrenomModif;
    private JTextField textFieldFiliereModif;
    private JTextField textFieldEmailModif;
    private JTextField textFieldMdpModif;
    private JTextField textFieldGroupe;
    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JTextField textFieldFiliere;
    private JTextField textFieldEmail;
    private JTextField textFieldMdp;
    private JComboBox groupeBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionnaireGUI window = new GestionnaireGUI();
                    window.frame.setVisible(true);
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
        GroupeDAO groupeDAO = new GroupeDAO();  
        groupeBox = new JComboBox();
        for (int i = 0; i < groupeDAO.getList().size(); i++) {
        groupeBox.addItem(groupeDAO.getList().get(i).getId());
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
        
        textFieldId1 = new JTextField();
        panelModif.add(textFieldId1);
        textFieldId1.setColumns(10);
        
        /**
         * Cr�ation du panel comportant la s�lection du groupe pour modifier un �tudiant
         */
        JPanel panelGroupeModif = new JPanel();
        frameModif.getContentPane().add(panelGroupeModif);
        
        JLabel lblGroupeModif = new JLabel("Groupe:");
        panelGroupeModif.add(lblGroupeModif);

        textFieldGroupeModif = new JTextField();
        panelGroupeModif.add(textFieldGroupeModif);
        textFieldGroupeModif.setColumns(10);

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
        
        textFieldId2 = new JTextField();
        panelSuppr.add(textFieldId2);
        textFieldId2.setColumns(10);
        
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
                int id = Integer.parseInt(textFieldId2.getText());
                
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
            	int id = Integer.parseInt(textFieldId1.getText());
                int groupe = Integer.parseInt(textFieldGroupeModif.getText());
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
    }
}
       
