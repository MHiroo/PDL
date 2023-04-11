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

public class EtudiantGUI {

    private JFrame frame;
    private JFrame frameListeCours;
    private JFrame framePlanning;
    private JFrame frameListeAbsences;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EtudiantGUI window = new EtudiantGUI();
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
    public EtudiantGUI() {
        initialize();
    }
    
    private void initialize() {
    	  /**
         * Cr�ation de la fen�tre d'accueil de l'�tudiant
         */
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
       /**
        * Cr�ation de la fen�tre de la liste des cours de l'�tudiant
        */
        frameListeCours = new JFrame();
        frameListeCours.setBounds(100, 100, 450, 300);
        frameListeCours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameListeCours.getContentPane().setLayout(new BoxLayout(frameListeCours.getContentPane(), BoxLayout.Y_AXIS));
        /**
         * Cr�ation de la fen�tre Planning de l'�tudiant
         */
         framePlanning = new JFrame();
         framePlanning.setBounds(100, 100, 450, 300);
         framePlanning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));
         /**
          * Cr�ation de la fen�tre Liste d'absence de l'�tudiant
          */
         frameListeAbsences = new JFrame();
         frameListeAbsences.setBounds(100, 100, 450, 300);
         frameListeAbsences.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frameListeAbsences.getContentPane().setLayout(new BoxLayout(frameListeAbsences.getContentPane(), BoxLayout.Y_AXIS));
        /**
         * Cr�ation du bouton pour acc�der � liste des cours de l'�tudiant
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
	     * Cr�ation du bouton pour acc�der au planning de l'�tudiant
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
	     * Cr�ation du bouton pour acc�der � la liste d'absence de l'�tudiant
	     */
	    JPanel ListeAbsences = new JPanel();
	    frame.getContentPane().add(ListeAbsences);
	    JButton LISTEABSENCES = new JButton("Acc�der � la liste d'absences");
	    LISTEABSENCES.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frameListeAbsences.setVisible(true);
	        }
	    });
	    ListeAbsences.add(LISTEABSENCES);
	    }

}

