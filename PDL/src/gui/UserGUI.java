package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import java.awt.Component;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;

public class UserGUI {

    public JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserGUI window = new UserGUI();
                	//window.frame.pack();
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
        frame.setBounds(1500, 1500, 820, 629);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        //Initialisation des composants


        JPanel base = new JPanel();
		JPanel upContainer = new JPanel();
		upContainer.setBackground(new Color(255, 255, 255));
		upContainer.setForeground(new Color(255, 255, 255));
		upContainer.setBounds(0, 0, 811, 484);
		JPanel downContainer = new JPanel();
		downContainer.setBackground(new Color(255, 255, 255));
		downContainer.setBounds(0, 481, 811, 111);
        
        
        
        
        
      //Bouton Gestionnaire:
        JButton btnGestionnaire = new JButton("Gestionnaire");
        btnGestionnaire.setBounds(104, 22, 131, 46);
        btnGestionnaire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                
            	SignInGestionnaireGUI window = new SignInGestionnaireGUI();
                frame = new JFrame();
                window.pframe.setLocationRelativeTo(null);
                window.pframe.setVisible(true);
     
            }
            
        });
       
        
        
      //Bouton Enseignant:
        
        JButton btnEnseignant = new JButton("Enseignant");
        btnEnseignant.setBounds(271, 22, 116, 46);
        btnEnseignant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
            	SignInEnseignantGUI window = new SignInEnseignantGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
     
            }
            
        });
       
        
      //Bouton etudiant:
        
        JButton btnEtudiant = new JButton("Etudiant");
        btnEtudiant.setBounds(416, 22, 123, 46);
        btnEtudiant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                SignInEtudiantGUI window = new SignInEtudiantGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
     
            }
            
        });
        
        
        //Bouton Adminisatratif:
        JButton btnAdmin = new JButton("Administratif");
        btnAdmin.setBounds(570, 22, 123, 46);
        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
                
            	SignInAdminGUI window = new SignInAdminGUI();
                frame = new JFrame();
                window.frame.setLocationRelativeTo(null);
                window.frame.setVisible(true);
     
            }
            
        });
        
        
        upContainer.setLayout(null);
        downContainer.setLayout(null);
        
        downContainer.add(btnGestionnaire);
        downContainer.add(btnEnseignant);
        downContainer.add(btnEtudiant);
        downContainer.add(btnAdmin);
        base.setLayout(null);
        base.add(upContainer);
        
        JLabel lblNewLabel = new JLabel("Utilisateur :");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 23));
        lblNewLabel.setBounds(308, 74, 429, 123);
        upContainer.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("PDL\\img.jpg"));
        lblNewLabel_1.setBounds(49, 153, 705, 331);
        upContainer.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("PDL\\img2.png"));
        lblNewLabel_2.setBounds(-14, 0, 297, 123);
        upContainer.add(lblNewLabel_2);
        base.add(downContainer);
        frame.setContentPane(base);
    }
}