package gui;

import java.awt.EventQueue;
import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class EnseignantGUI {

     JFrame frame;
    JFrame frameListeCours;
    JFrame framePlanning;
    JFrame frameAppel;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EnseignantGUI window = new EnseignantGUI();
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
    public EnseignantGUI() {
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
        		EnseignantListeCoursGUI window = new EnseignantListeCoursGUI();
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
        		EnseignantPlanningGUI window = new EnseignantPlanningGUI();
        		framePlanning = new JFrame();
        		window.framePlanning.setLocationRelativeTo(null);
        		window.framePlanning.setVisible(true);
	        }
	    });
	    Planning.add(planningBtn);
	    
	    /**
	     * Creation du bouton pour acceder e la liste d'absence de l'enseignant
	     */
	    JPanel appelPanel = new JPanel();
	    frame.getContentPane().add(appelPanel);
	    JButton appelBtn = new JButton("Faire l'appel");
	    appelBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.setVisible(false);
        		EnseignantFaireAppelGUI window = new EnseignantFaireAppelGUI();
        		frameAppel = new JFrame();
        		window.frameAppel.setLocationRelativeTo(null);
        		window.frameAppel.setVisible(true);
	        }
	    });
	    appelPanel.add(appelBtn);
	    }

}
