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

public class EnseignantGUI {

     JFrame frame;
    private JFrame frameListeCours;
    private JFrame framePlanning;
    private JFrame frameAppel;
    
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
        * Creation de la fenetre de la liste des cours de l'enseignant
        */
        frameListeCours = new JFrame();
        frameListeCours.setBounds(100, 100, 450, 300);
        frameListeCours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameListeCours.getContentPane().setLayout(new BoxLayout(frameListeCours.getContentPane(), BoxLayout.Y_AXIS));
        /**
         * Creation de la fenetre Planning de l'enseignant
         */
         framePlanning = new JFrame();
         framePlanning.setBounds(100, 100, 450, 300);
         framePlanning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));
         /**
          * Creation de la fenetre Faire l'appel de l'enseignant
          */
         frameAppel = new JFrame();
         frameAppel.setBounds(100, 100, 450, 300);
         frameAppel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frameAppel.getContentPane().setLayout(new BoxLayout(frameAppel.getContentPane(), BoxLayout.Y_AXIS));
        /**
         * Creation du bouton pour acceder e liste des cours de l'enseignant
         */
        JPanel ListeCours = new JPanel();
        frame.getContentPane().add(ListeCours);
        JButton ListeCour = new JButton("Acceder Liste Cours");
        ListeCour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frameListeCours.setLocationRelativeTo(null);
            	frameListeCours.setVisible(true);
            }
        });
        ListeCours.add(ListeCour);
   
    
	    /**
	     * Creation du bouton pour acceder au planning de l'enseignant
	     */
	    JPanel Planning = new JPanel();
	    frame.getContentPane().add(Planning);
	    JButton PLANNING = new JButton("Acceder au Planning");
	    PLANNING.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		framePlanning.setLocationRelativeTo(null);
	        	framePlanning.setVisible(true);
	        }
	    });
	    Planning.add(PLANNING);
	    
	    /**
	     * Creation du bouton pour acceder e la liste d'absence de l'enseignant
	     */
	    JPanel Appel = new JPanel();
	    frame.getContentPane().add(Appel);
	    JButton APPEL = new JButton("Faire l'Appel");
	    APPEL.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frameAppel.setLocationRelativeTo(null);
	    		frameAppel.setVisible(true);
	        }
	    });
	    Appel.add(APPEL);
	    }

}
