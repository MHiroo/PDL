package gui;

import java.awt.Color;
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

public class SignInEtudiantGUI {

     JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldEmail;
    private JTextField textFieldMdp;
    public static int id;
    
    /**
     * Launch the application
     */
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	SignInEtudiantGUI window = new SignInEtudiantGUI();
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
    public SignInEtudiantGUI() {
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
                
                
                // Appeler la methode d'authentification d'un etudiant dans la base de donnees
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                Etudiant etudiant = etudiantDAO.signIn(email,mdp);
                

                if (etudiant==null) {
                	
                	JPanel panel = new JPanel();
                    frame.getContentPane().add(panel);
                    JLabel lbl = new JLabel("Erreur: Email ou Mot de passe errones");
                    lbl.setForeground(Color.RED);
                    panel.add(lbl);
	                frame.setVisible(true);
                }
                else {
                	id = etudiant.getId();
                	frame.setVisible(false);
	                EtudiantGUI window = new EtudiantGUI();
	                frame = new JFrame();
	                window.frame.setLocationRelativeTo(null);
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