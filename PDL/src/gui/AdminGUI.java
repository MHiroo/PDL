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

public class AdminGUI {

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
    private JComboBox idBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminGUI window = new AdminGUI();
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
    public AdminGUI() {
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
        
        /**
         * Cr�ation du bouton du UC cr�er modifier etudiant
         */
        JButton btnUC1 = new JButton("Cr�er/Modifier un �tudiant");
        btnUC1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC1);
        
        /**
         * Cr�ation du bouton du UC 2
         */
        JButton btnUC2 = new JButton("UC2");
        btnUC2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC2frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC2);
        
        /**
         * Cr�ation du bouton du UC 3
         */
        JButton btnUC3 = new JButton("UC3");
        btnUC3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC3frame.setVisible(true);
            }
        });
        panelUC1.add(btnUC3);
        
        /**
         * Cr�ation du bouton du UC 4
         */
        JButton btnUC4 = new JButton("UC4");
        btnUC4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC4frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC4);
        
        /**
         * Cr�ation du bouton du UC 5
         */
        JButton btnUC5 = new JButton("UC5");
        btnUC5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC5frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC5);
        
        /**
         * Cr�ation du bouton du UC 6
         */
        JButton btnUC6 = new JButton("UC6");
        btnUC6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC6frame.setVisible(true);
            }
        });
        panelUC2.add(btnUC6);
        
        /**
         * Cr�ation du bouton du UC 7
         */
        JButton btnUC7 = new JButton("UC7");
        btnUC7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC7frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC7);
        
        /**
         * Cr�ation du bouton du UC 8
         */
        JButton btnUC8 = new JButton("UC8");
        btnUC8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC8frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC8);
        
        /**
         * Cr�ation du bouton du UC 9
         */
        JButton btnUC9 = new JButton("UC9");
        btnUC9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UC9frame.setVisible(true);
            }
        });
        panelUC3.add(btnUC9);
    }
}
       
