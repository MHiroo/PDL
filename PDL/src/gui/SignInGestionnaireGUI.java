package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.GestionnaireDAO;
import model.Personne;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignInGestionnaireGUI {

     JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldEmail;
    private JTextField textFieldMdp;
    
    /**
     * Launch the application
     */
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SignInGestionnaireGUI window = new SignInGestionnaireGUI();
                    window.frame.setLocationRelativeTo(null);
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
    public SignInGestionnaireGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        JPanel panelEmail = new JPanel();
        frame.getContentPane().add(panelEmail);

        JLabel lblEmail = new JLabel("Email:");
        panelEmail.add(lblEmail);

        textFieldEmail = new JTextField();
        panelEmail.add(textFieldEmail);
        textFieldEmail.setColumns(20);

        JPanel panelMdp = new JPanel();
        frame.getContentPane().add(panelMdp);

        JLabel lblMdp = new JLabel("Mot de passe:");
        panelMdp.add(lblMdp);

        textFieldMdp = new JTextField();
        panelMdp.add(textFieldMdp);
        textFieldMdp.setColumns(10);

        JPanel panelBoutons = new JPanel();
        frame.getContentPane().add(panelBoutons);
        JButton btnConnecter = new JButton("Se connecter");
        btnConnecter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recuperer les donnees saisies par l'utilisateur
                
                String email = textFieldEmail.getText();
                String mdp = textFieldMdp.getText();
                
                // Creer un objet gestionnaire avec les donnees recuperees
                Personne gestionnaire = new Personne( 0,  "",  "",  "", "", "");
                
                // Appeler la methode d'authentification d'un gestionnaire dans la base de donnees
                GestionnaireDAO gestionnaireDAO = new GestionnaireDAO();
                gestionnaire = gestionnaireDAO.signIn(email,mdp);
                if (gestionnaire==null) {
                	
                	JPanel panel = new JPanel();
                    frame.getContentPane().add(panel);
                    JLabel lbl = new JLabel("Erreur: Email ou Mot de passe errones");
                    lbl.setForeground(Color.RED);
                    panel.add(lbl);
	                frame.setVisible(true);
                }
                else {
                frame.setVisible(false);
                GestionnaireGUI window = new GestionnaireGUI();
                frame = new JFrame();
                window.frame.setVisible(true);
                }
            }
            
        });
        panelBoutons.add(btnConnecter);
        
        
        
        //Ajout bouton retour
        
        JPanel panelBoutonRetour = new JPanel();
        JButton retourBtn = new JButton("Retour");
        retourBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.dispose(); // ferme la fenêtre actuelle
                UserGUI window = new UserGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
            }
        });
        panelBoutonRetour.add(retourBtn);
        frame.getContentPane().add(panelBoutonRetour);

    }
   
}