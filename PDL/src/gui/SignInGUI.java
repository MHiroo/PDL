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

public class SignInGUI {

    private JFrame frame;
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
                	SignInGUI window = new SignInGUI();
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
    public SignInGUI() {
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
                // Récupérer les données saisies par l'utilisateur
                
                String email = textFieldEmail.getText();
                String mdp = textFieldMdp.getText();
                
                // Créer un objet Etudiant avec les données récupérées
                Etudiant etudiant = new Etudiant( 0,  0,  "",  "",  "", "", "");
                
                // Appeler la méthode d'authentification d'un étudiant dans la base de données
                EtudiantDAO etudiantDAO = new EtudiantDAO();
                etudiant = etudiantDAO.signIn(email,mdp);
                
                EtudiantGUI etudiantGUI = new EtudiantGUI();
                etudiantGUI.setVisible(true);
     
            }
            
        });
        panelBoutons.add(btnConnecter);
        

    }
   
}