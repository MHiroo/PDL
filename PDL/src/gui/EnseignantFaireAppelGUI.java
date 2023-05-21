package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dao.AbsenceDAO;
import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.PlanningDAO;
import dao.Type_absenceDAO;
import model.Absence;
import model.Cours;
import model.Enseignant;
import model.Etudiant;
import model.Groupe_Etudiant;
import model.Planning;
import model.TYPE_ABSENCE;

import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class EnseignantFaireAppelGUI {

	JFrame frame;
	JFrame frameAppel;

    private JComboBox idBoxGroupe;
    private JComboBox idBoxAbsent;
    private JTable table;
    private DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnseignantFaireAppelGUI window = new EnseignantFaireAppelGUI();
					window.frameAppel.setLocationRelativeTo(null);
					window.frameAppel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnseignantFaireAppelGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
        EnseignantDAO enseignantDAO = new EnseignantDAO();
		CoursDAO coursDAO= new CoursDAO();
		
		
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

			List<Double> listeHeures = new ArrayList<>();

			double heureDebut = 8.0;
			double heureFin = 18.0;
			double intervalle = 0.5;

			double heureActuelle = heureDebut;
			while (heureActuelle <= heureFin) {
			    listeHeures.add(heureActuelle);
			    heureActuelle += intervalle;
			}
			
			JComboBox<Double> comboBoxHeure = new JComboBox<Double>();
			for (int i = 0; i < listeHeures.size(); i++) {
				comboBoxHeure.addItem(listeHeures.get(i));
			}
			
		panelAbsent.add(comboBoxHeure);
		
		
		// Création du combobox des cours a selectionner

		JComboBox<String> comboBoxCours = new JComboBox<String>();
		
		for (int i = 0; i < enseignantDAO .getNomCours(SignInEnseignantGUI.id).size(); i++) {
			comboBoxCours.addItem(enseignantDAO.getNomCours(SignInEnseignantGUI.id).get(i));
		}
		
		panelAbsent.add(comboBoxCours);

		// Création du combobox des duree a selectionner
		

		List<Integer> duree = new ArrayList<>();

		for (Integer i = 1; i <= 4; i++) {
			duree.add(i );
		}
		JComboBox<Integer> comboBoxDuree = new JComboBox<Integer>();
		for (int i = 0; i < duree.size(); i++) {
			comboBoxDuree.addItem(duree.get(i));
		}
		panelAbsent.add(comboBoxDuree);
		
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
           	 int idEtudiant = (int) idBoxAbsent.getSelectedItem();
           	 Double heureDebut = (Double) comboBoxHeure.getSelectedItem();
           	 int idCours = (Integer) coursDAO.getIdCours((String) comboBoxCours.getSelectedItem());
           	 int nbHeure = (Integer) comboBoxDuree.getSelectedItem();        	 
           	 Absence absence = new Absence(idEtudiant, idCours ,heureDebut, nbHeure );
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
      

        //Creation du bouton retour

		JButton retourBtn = new JButton("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameAppel.dispose(); // ferme la fenetre actuelle
				EnseignantGUI window = new EnseignantGUI();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});

		JPanel panelBoutonRetour = new JPanel();
		panelBoutonRetour.add(retourBtn);
		frameAppel.getContentPane().add(panelBoutonRetour);
















	}
}