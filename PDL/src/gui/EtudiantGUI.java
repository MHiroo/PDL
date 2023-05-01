package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import model.Enseignant;
import model.Etudiant;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EtudiantGUI extends JFrame{

	JFrame frame;
    private JFrame frameListeCours;
    private JFrame framePlanning;
    private JFrame frameListeAbsences;
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	
                    EtudiantGUI window = new EtudiantGUI();
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
    public EtudiantGUI() {
        initialize();
    }
    
    private void initialize() {
    	  /**
         * Création de la fenêtre d'accueil de l'étudiant
         */

    	frame=this;
    	this.setTitle("Menu Etudiant");
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
       /**
        * Création de la fenêtre de la liste des cours de l'étudiant
        */
        
        frameListeCours = new JFrame();
        frameListeCours.setBounds(1500, 1500, 1500, 1000);
        frameListeCours.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameListeCours.getContentPane().setLayout(new BoxLayout(frameListeCours.getContentPane(), BoxLayout.Y_AXIS));


        //Initialisation des composants
        
        
        JPanel base = new JPanel();
        JPanel upContainer = new JPanel();
        JPanel downContainer = new JPanel();
        
      //each panel gets its own layout
        upContainer.setLayout(new GridLayout(1, 1));
        downContainer.setLayout(new GridLayout(1, 3));
        base.setLayout(new GridLayout(2, 1));
        
     
        
     // Création du tableau
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        String[][] data = new String[10][4];
        for (int i = 0; i < etudiantDAO.getNomCours(SignInEtudiantGUI.id).size(); i++) {
            for (int j = 0; j < 4; j++) {
            	if (j==0) {
            		data[i][j] =etudiantDAO.getNomCours(SignInEtudiantGUI.id).get(i);
            	}
            	if (j==1) {
            		data[i][j] =etudiantDAO.getMasseHoraire(SignInEtudiantGUI.id).get(i);
            	}
            	/**if (j==2) {
            		data[i][j] =etudiantDAO.getRepartition(SignInEtudiantGUI.id).get(i);
            	}*/
            	if (j==3) {
            		data[i][j] =etudiantDAO.getNomEnseignant(SignInEtudiantGUI.id).get(i);
            	
            	}
            }
        }
        String[] columnNames = {"Nom du cours:", "Masse Horaire:", "Repartition:", "Enseignant:"};
        JTable tableau = new JTable(data, columnNames);


        //Création du bouton retour
	    
	    JPanel panelBoutonRetour = new JPanel();
	    JButton retourBtn = new JButton("Retour");
	    retourBtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	frameListeCours.dispose(); // ferme la fenêtre actuelle
	            frame.setVisible(true);
	        }
	    });
	    panelBoutonRetour.add(retourBtn);
	    
        
	    //distribution of the components in the 2 containers
	    upContainer.add(new JScrollPane(tableau), BorderLayout.CENTER);
	    downContainer.add(panelBoutonRetour,BorderLayout.EAST);

	    //Ajout des containers au container principal
	    base.add(upContainer);
	    base.add(downContainer);
	    
		 //to finish, we add the main container to the window
	    frameListeCours.setContentPane(base);
	    
	    
	    
            

        
        
        

        /**
         * Création de la fenêtre Planning de l'étudiant
         */
         framePlanning = new JFrame();
         framePlanning.setBounds(100, 100, 450, 300);
         framePlanning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));
         
	       //Ajout bouton retour
         
	        JPanel panelBoutonRetour2 = new JPanel();
	 	    JButton retourBtn2 = new JButton("Retour");
	 	    retourBtn2.addActionListener(new ActionListener() {
	 	        public void actionPerformed(ActionEvent event) {
	 	        	framePlanning.dispose(); // ferme la fenêtre actuelle
	 	            frame.setVisible(true);
	 	        }
	 	    });
	 	    panelBoutonRetour2.add(retourBtn2);
	 	   framePlanning.getContentPane().add(panelBoutonRetour2);
 	    
         /**
          * Création de la fenêtre Liste d'absence de l'étudiant
          */
         frameListeAbsences = new JFrame();
         frameListeAbsences.setBounds(100, 100, 450, 300);
         frameListeAbsences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frameListeAbsences.getContentPane().setLayout(new BoxLayout(frameListeAbsences.getContentPane(), BoxLayout.Y_AXIS));
        
	       //Ajout bouton retour
	 	    
	        JPanel panelBoutonRetour3 = new JPanel();
	 	    JButton retourBtn3 = new JButton("Retour");
	 	    retourBtn3.addActionListener(new ActionListener() {
	 	        public void actionPerformed(ActionEvent event) {
	 	        	frameListeAbsences.dispose(); // ferme la fenêtre actuelle
	 	            frame.setVisible(true);
	 	        }
	 	    });
	 	    panelBoutonRetour3.add(retourBtn3);
	 	   frameListeAbsences.getContentPane().add(panelBoutonRetour3);
	 	    
         
         
         
         /**
         * Création du bouton pour accéder à liste des cours de l'étudiant
         */
        JPanel ListeCours = new JPanel();
        this.getContentPane().add(ListeCours);
        JButton ListeCour = new JButton("Accéder Liste Cours");
        ListeCour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frameListeCours.setLocationRelativeTo(null);
            	frameListeCours.setVisible(true);
            }
        });
        ListeCours.add(ListeCour);
   
    
	    /**
	     * Création du bouton pour accéder au planning de l'étudiant
	     */
	    JPanel Planning = new JPanel();
	    this.getContentPane().add(Planning);
	    JButton PLANNING = new JButton("Accéder au Planning");
	    PLANNING.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		framePlanning.setLocationRelativeTo(null);
	        	framePlanning.setVisible(true);
	        }
	    });
	    Planning.add(PLANNING);
	    
	    /**
	     * Création du bouton pour accéder à la liste d'absence de l'étudiant
	     */
	    JPanel ListeAbsences = new JPanel();
	    this.getContentPane().add(ListeAbsences);
	    JButton LISTEABSENCES = new JButton("Accéder à la liste d'absences");
	    LISTEABSENCES.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frameListeAbsences.setLocationRelativeTo(null);
	    		frameListeAbsences.setVisible(true);
	        }
	    });
	    ListeAbsences.add(LISTEABSENCES);
	    
    }
	    
    
	  

}
