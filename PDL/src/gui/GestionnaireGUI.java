package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.EtudiantDAO;
import model.Etudiant;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionnaireGUI {

    private JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldGroupe;
    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JTextField textFieldFiliere;
    private JTextField textFieldEmail;
    private JTextField textFieldMdp;

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
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel panelId = new JPanel();
        frame.getContentPane().add(panelId);

        JLabel lblId = new JLabel("Id:");
        panelId.add(lblId);

        textFieldId = new JTextField();
        panelId.add(textFieldId);
        textFieldId.setColumns(10);

        JPanel panelGroupe = new JPanel();
        frame.getContentPane().add(panelGroupe);

        JLabel lblGroupe = new JLabel("Groupe:");
        panelGroupe.add(lblGroupe);

        textFieldGroupe = new JTextField();
        panelGroupe.add(textFieldGroupe);
        textFieldGroupe.setColumns(10);

        JPanel panelNom = new JPanel();
        frame.getContentPane().add(panelNom);

        JLabel lblNom = new JLabel("Nom:");
        panelNom.add(lblNom);

        textFieldNom = new JTextField();
        panelNom.add(textFieldNom);
        textFieldNom.setColumns(10);

        JPanel panelPrenom = new JPanel();
        frame.getContentPane().add(panelPrenom);

        JLabel lblPrenom = new JLabel("Prenom:");
        panelPrenom.add(lblPrenom);

        textFieldPrenom = new JTextField();
        panelPrenom.add(textFieldPrenom);
        textFieldPrenom.setColumns(10);

        JPanel panelFiliere = new JPanel();
        frame.getContentPane().add(panelFiliere);

        JLabel lblFiliere = new JLabel("Filiere:");
        panelFiliere.add(lblFiliere);

        textFieldFiliere = new JTextField();
        panelFiliere.add(textFieldFiliere);
        textFieldFiliere.setColumns(10);

        JPanel panelEmail = new JPanel();
        frame.getContentPane().add(panelEmail);

        JLabel lblEmail = new JLabel("Email:");
        panelEmail.add(lblEmail);

        textFieldEmail = new JTextField();
        panelEmail.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JPanel panelMdp = new JPanel();
        frame.getContentPane().add(panelMdp);

        JLabel lblMdp = new JLabel("Mot de passe:");
        panelMdp.add(lblMdp);

        textFieldMdp = new JTextField();
        panelMdp.add(textFieldMdp);
        textFieldMdp.setColumns(10);

        JPanel panelBoutons = new JPanel();
        frame.getContentPane().add(panelBoutons);
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies par l'utilisateur
                int id = Integer.parseInt(textFieldId.getText());
                int groupe = Integer.parseInt(textFieldGroupe.getText());
                String nom = textFieldNom.getText();
                String prenom = textFieldPrenom.getText();
                String filiere = textFieldFiliere.getText();
                String email = textFieldEmail.getText();
                String mdp = textFieldMdp.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                Etudiant etudiant = new Etudiant(id, groupe, nom, prenom, filiere, email, mdp);
                
                // Appeler la méthode d'ajout d'un étudiant dans la base de données
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.add(etudiant);
            }
        });
        panelBoutons.add(btnAjouter);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'identifiant de l'étudiant à supprimer
                int id = Integer.parseInt(textFieldId.getText());
                
                // Appeler la méthode de suppression d'un étudiant de la base de données
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiantDAO.delete(id);
            }
        });
        panelBoutons.add(btnSupprimer);
    }
}


       
