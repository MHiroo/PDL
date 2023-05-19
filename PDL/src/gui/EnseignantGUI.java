package gui;

import java.awt.EventQueue;
import javax.swing.*;

import dao.AbsenceDAO;
import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.Type_absenceDAO;
import model.Absence;
import model.Cours;
import model.Enseignant;
import model.Etudiant;
import model.Groupe_Etudiant;
import model.TYPE_ABSENCE;

import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class EnseignantGUI {

     JFrame frame;
    JFrame frameListeCours;
    JFrame framePlanning;
    private JFrame frameAppel;
    private JComboBox idBoxGroupe;
    private JComboBox idBoxAbsent;
    private JTable table;
    private DefaultTableModel tableModel;
    
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
          * Creation de la fenetre Faire l'appel de l'enseignant
          */
         frameAppel = new JFrame();
         frameAppel.setBounds(100, 100, 470, 300);
         frameAppel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frameAppel.getContentPane().setLayout(new BoxLayout(frameAppel.getContentPane(), BoxLayout.Y_AXIS));
         
         
         
         
         
         
         /**
          * Creation du panel comportant la selection de l'id pour s�lectionner un groupe
          */
         JPanel panelGroupe = new JPanel();
         frameAppel.getContentPane().add(panelGroupe);

         JLabel lblGroupe = new JLabel("Groupe");
         panelGroupe.add(lblGroupe);

         //On recupere l'id des groupes crees dans la BDD pour les afficher ds le menu deroulant 
         GroupeDAO groupeDAO = new GroupeDAO();
         idBoxGroupe = new JComboBox();
         for (int i = 0; i < groupeDAO.getList().size(); i++) {
         idBoxGroupe.addItem(groupeDAO.getList().get(i).getId());
         }
         panelGroupe.add(idBoxGroupe);
         
         /**
          * Creation du panel comportant la selection de l'id pour s�lectionner un etudiant
          */
         JPanel panelAbsent = new JPanel();
         frameAppel.getContentPane().add(panelAbsent);

         JLabel lblAbsent = new JLabel("Absent:");
         panelAbsent.add(lblAbsent);
         
         idBoxAbsent = new JComboBox();
         panelAbsent.add(idBoxAbsent);
        
         JLabel lblHeure = new JLabel("Heure de debut");
         panelAbsent.add(lblHeure);
         
         // Création du combobox des heures a selectionner

		String heureDebut2 = "08:00:00";
		String heureFin = "18:00:00";			
		int intervalleMinutes = 30;

		List<Time> listeHeures = new ArrayList<>();
		
		try {
			SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm:ss");				
			Date dateDebut = formatHeure.parse(heureDebut2);
			Date dateFin = formatHeure.parse(heureFin);

			long intervalleMillis = intervalleMinutes * 60 * 1000;
			long heureActuelle = dateDebut.getTime();

			while (heureActuelle <= dateFin.getTime()) {
				Calendar calendar2 = Calendar.getInstance();
			    calendar2.setTimeInMillis(heureActuelle);
			    int heures = calendar2.get(Calendar.HOUR_OF_DAY);				   
			    int minutes = calendar2.get(Calendar.MINUTE);

			    // Réinitialiser les secondes à zéro
			    calendar2.set(Calendar.SECOND, 0);

			    Time heure = new Time(calendar2.getTimeInMillis());
			    listeHeures.add(heure);

			    heureActuelle += intervalleMillis;				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox<Time> comboBoxHeure = new JComboBox<Time>();
		for (int i = 0; i < listeHeures.size(); i++) {
			comboBoxHeure.addItem(listeHeures.get(i));
		}
			
		panelAbsent.add(comboBoxHeure);
		
         //Ajout d'un bouton pour enregistrer le choix du groupe
         
         JButton btnGroupe = new JButton("Selectionner");
         btnGroupe.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 // Recuperer les donnees saisies par l'utilisateur
                 int id = (int) idBoxGroupe.getSelectedItem();

                 // Creer un objet Etudiant avec les donnees recuperees
                 ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
                 // Appeler la methode d'ajout d'un etudiant dans la base de donnees
                 EtudiantDAO etudiantDAO = new EtudiantDAO();

                 tableModel.setRowCount(0); // Effacer les anciennes donn�es de la table

                 for (int i = 0; i < etudiantDAO.getEtudiantGroupe(id).size(); i++) {
                     Etudiant etudiant = etudiantDAO.getEtudiantGroupe(id).get(i);
                     String firstName = etudiant.getFirstName();
                     String lastName = etudiant.getName();
                     int identifiant = etudiant.getId();

                     // Ajouter les donn�es � la table
                     tableModel.addRow(new Object[]{firstName, lastName, identifiant});
                 }
                     
                  EtudiantDAO etudiantDAO1 = new EtudiantDAO();
                  
                  for (int i = 0; i < etudiantDAO1.getEtudiantGroupe(id).size(); i++) {
                  idBoxAbsent.addItem(etudiantDAO1.getEtudiantGroupe(id).get(i).getId());
                  }
                 
             }
         });
         panelGroupe.add(btnGroupe);
         
         JButton btnAbsent = new JButton("Valider");
         btnAbsent.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 //Ajouter l'abence a l'etudiant correspondant � l'id selectionne
            	 int id = (int) idBoxAbsent.getSelectedItem();
            	 Time temps = (Time) comboBoxHeure.getSelectedItem();
            	 
            	 Absence absence = new Absence(id, temps);
            	 AbsenceDAO absenceDAO = new AbsenceDAO();
            	 absenceDAO.add(absence);         	  
             }
         });
         panelAbsent.add(btnAbsent);

         // Creation de la JTable avec un DefaultTableModel vide
         tableModel = new DefaultTableModel();
         tableModel.setColumnIdentifiers(new Object[]{"Prenom", "Nom", "identifiant"});
         table = new JTable(tableModel);

         // Ajouter la JTable � un JScrollPane
         JScrollPane scrollPane = new JScrollPane(table);
         panelGroupe.add(scrollPane);
       
         
         
        /**
         * Creation du bouton pour acceder e liste des cours de l'enseignant
         */
         
        JPanel ListeCours = new JPanel();
        frame.getContentPane().add(ListeCours);
        JButton ListeCour = new JButton("Acceder Liste Cours");
        ListeCour.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		EnseignantListeCoursGUI window = new EnseignantListeCoursGUI();
        		frameListeCours = new JFrame();
        		window.frameListeCours.setLocationRelativeTo(null);
        		window.frameListeCours.setVisible(true);
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
	    		frame.setVisible(false);
        		EnseignantPlanningGUI window = new EnseignantPlanningGUI();
        		framePlanning = new JFrame();
        		window.framePlanning.setLocationRelativeTo(null);
        		window.framePlanning.setVisible(true);
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
