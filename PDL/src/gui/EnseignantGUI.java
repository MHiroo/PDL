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
         * Cr�ation de la fen�tre d'accueil de l'enseignant
         */
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
       /**
        * Cr�ation de la fen�tre de la liste des cours de l'enseignant
        */
        frameListeCours = new JFrame();
        frameListeCours.setBounds(100, 100, 450, 300);
        frameListeCours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameListeCours.getContentPane().setLayout(new BoxLayout(frameListeCours.getContentPane(), BoxLayout.Y_AXIS));
        /**
         * Cr�ation de la fen�tre Planning de l'enseignant
         */
         framePlanning = new JFrame();
         framePlanning.setBounds(100, 100, 450, 300);
         framePlanning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));
         /**
          * Cr�ation de la fen�tre Faire l'appel de l'enseignant
          */
         frameAppel = new JFrame();
         frameAppel.setBounds(100, 100, 450, 300);
         frameAppel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frameAppel.getContentPane().setLayout(new BoxLayout(frameAppel.getContentPane(), BoxLayout.Y_AXIS));
        /**
         * Cr�ation du bouton pour acc�der � liste des cours de l'enseignant
         */
        JPanel ListeCours = new JPanel();
        frame.getContentPane().add(ListeCours);
        JButton ListeCour = new JButton("Acc�der Liste Cours");
        ListeCour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	frameListeCours.setVisible(true);
            }
        });
        ListeCours.add(ListeCour);
   
    
	    /**
	     * Cr�ation du bouton pour acc�der au planning de l'enseignant
	     */
	    JPanel Planning = new JPanel();
	    frame.getContentPane().add(Planning);
	    JButton PLANNING = new JButton("Acc�der au Planning");
	    PLANNING.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	        	framePlanning.setVisible(true);
	        }
	    });
	    Planning.add(PLANNING);
	    
	    /**
	     * Cr�ation du bouton pour acc�der � la liste d'absence de l'enseignant
	     */
	    JPanel Appel = new JPanel();
	    frame.getContentPane().add(Appel);
	    JButton APPEL = new JButton("Faire l'Appel");
	    APPEL.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frameAppel.setVisible(true);
	        }
	    });
	    Appel.add(APPEL);
	    }

}
