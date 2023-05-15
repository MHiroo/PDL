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
import dao.GestionnaireDAO;
import model.Personne;
import dao.EnseignantDAO;
import model.Enseignant;
import dao.AdminDAO;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI {

    public JFrame frame;
    
    /**
     * Launch the application
     */
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserGUI window = new UserGUI();
                	window.frame.pack();
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
    public UserGUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 250, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        JPanel panelUser = new JPanel();
        
        JLabel lblUser = new JLabel("Utilisateur:");
        panelUser.add(lblUser);
        frame.getContentPane().add(panelUser);
        

        //Creation des boutons :
        JPanel panelBoutons = new JPanel();
        frame.getContentPane().add(panelBoutons);
        
        
      //Bouton Gestionnaire:
        JButton btnGestionnaire = new JButton("Gestionnaire");
        btnGestionnaire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                
            	SignInGestionnaireGUI window = new SignInGestionnaireGUI();
                frame = new JFrame();
                window.pframe.setLocationRelativeTo(null);
                window.pframe.setVisible(true);
     
            }
            
        });
        panelBoutons.add(btnGestionnaire);
        
        
      //Bouton Enseignant:
        
        JButton btnEnseignant = new JButton("Enseignant");
        btnEnseignant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
            	SignInEnseignantGUI window = new SignInEnseignantGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
     
            }
            
        });
        panelBoutons.add(btnEnseignant);
        
      //Bouton etudiant:
        
        JButton btnEtudiant = new JButton("Etudiant");
        btnEtudiant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                SignInEtudiantGUI window = new SignInEtudiantGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
     
            }
            
        });
        panelBoutons.add(btnEtudiant);
        
        //Bouton Adminisatratif:
        JButton btnAdmin = new JButton("Administratif");
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                
            	SignInAdminGUI window = new SignInAdminGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
     
            }
            
        });
        panelBoutons.add(btnAdmin);


    }
   
}