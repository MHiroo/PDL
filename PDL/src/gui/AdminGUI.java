package gui;

import java.awt.EventQueue;
import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class AdminGUI {

     JFrame frame;
    JFrame frameListeCours;
    JFrame framePlanning;
    JFrame frameListeAbsences;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminGUI window = new AdminGUI();
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
    public AdminGUI() {
        initialize();
    }
    
    private void initialize() {
    	  /**
         * Creation de la fenetre d'accueil de l'enseignant
         */
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
       
        
         
         
        /**
         * Creation du bouton pour acceder e liste des cours de l'enseignant
         */
         
        JPanel ListeCours = new JPanel();
        frame.getContentPane().add(ListeCours);
        JButton listeCoursBtn = new JButton("Acceder Liste Cours");
        listeCoursBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		AdminListeCoursGUI window = new AdminListeCoursGUI();
        		frameListeCours = new JFrame();
        		window.frameListeCours.setLocationRelativeTo(null);
        		window.frameListeCours.setVisible(true);
            }
        });
        ListeCours.add(listeCoursBtn);
   
    
	    /**
	     * Creation du bouton pour acceder au planning de l'enseignant
	     */
	    JPanel Planning = new JPanel();
	    frame.getContentPane().add(Planning);
	    JButton planningBtn = new JButton("Acceder au Planning");
	    planningBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
	    		AdminPlanningGUI window = new AdminPlanningGUI();
        		framePlanning = new JFrame();
        		window.framePlanning.setLocationRelativeTo(null);
        		window.framePlanning.setVisible(true);
	        }
	    });
	    Planning.add(planningBtn);
	    
	    /**
	     * Creation du bouton pour acceder e la liste d'absences
	     */
	    JPanel absencePanel = new JPanel();
	    frame.getContentPane().add(absencePanel);
	    JButton absenceBtn = new JButton("Acceder aux absences");
	    absenceBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
	    		AdminAbsenceGUI window = new AdminAbsenceGUI();
        		frameListeAbsences = new JFrame();
        		window.frameListeAbsences.setLocationRelativeTo(null);
        		window.frameListeAbsences.setVisible(true);
	        }
	    });
	    absencePanel.add(absenceBtn);
	    
	  //Ajout bouton deconnexion

	  		JPanel panelBoutonDeco = new JPanel();
	  		JButton retourBtnDeco = new JButton("Deconnexion");
	  		retourBtnDeco.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent event) {
	  				frame.dispose(); // ferme la fenêtre actuelle
	  				UserGUI window = new UserGUI();
	  				frame = new JFrame();
	  				window.frame.setLocationRelativeTo(null);
	  				window.frame.setVisible(true);
	  			}
	  		});
	  		panelBoutonDeco.add(retourBtnDeco);
	  		frame.getContentPane().add(panelBoutonDeco);

	    }

}
