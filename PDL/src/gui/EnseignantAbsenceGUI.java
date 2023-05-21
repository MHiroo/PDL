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

public class EnseignantAbsenceGUI {

	JFrame frame;
	JFrame frameAbsence;
	JFrame fAbsence;

    private JComboBox idBoxGroupe;
    private JComboBox idBoxAbsent;
    private JTable table;
    private JTable table2;
    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnseignantAbsenceGUI window = new EnseignantAbsenceGUI();
					window.frameAbsence.setLocationRelativeTo(null);
					window.frameAbsence.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnseignantAbsenceGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/**
         * Creation de la fenetre Faire l'appel de l'enseignant
         */
		frameAbsence = new JFrame();
		frameAbsence.setBounds(100, 100, 470, 300);
		frameAbsence.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAbsence.getContentPane().setLayout(new BoxLayout(frameAbsence.getContentPane(), BoxLayout.Y_AXIS));
        
        /**
         * Creation de la fenetre qui affiche les absences de l'eleve
         */
		fAbsence = new JFrame();
		fAbsence.setBounds(100, 100, 470, 300);
		fAbsence.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fAbsence.getContentPane().setLayout(new BoxLayout(fAbsence.getContentPane(), BoxLayout.Y_AXIS));
        
        
        /**
         * Creation du panel comportant la selection de l'id pour s�lectionner un groupe
         */
        JPanel panelGroupe = new JPanel();
        frameAbsence.getContentPane().add(panelGroupe);

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
        frameAbsence.getContentPane().add(panelAbsent);

        JLabel lblAbsent = new JLabel("Id de l'etudiant :");
        panelAbsent.add(lblAbsent);
        
        idBoxAbsent = new JComboBox();
        panelAbsent.add(idBoxAbsent);
       
        
 
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
           	 int idEtudiant =(int)idBoxAbsent.getSelectedItem();
           	 AbsenceDAO absenceDAO = new AbsenceDAO(); 
             EnseignantAbsenceGUI window = new EnseignantAbsenceGUI();
             window.fAbsence.setLocationRelativeTo(null);
             window.fAbsence.setVisible(true);
           	
           	 tableModel2.setRowCount(0); // Effacer les anciennes donn�es de la table

             for (int i = 0; i < absenceDAO.getList().size(); i++) {
                Absence absence = new Absence(absenceDAO.get2(idEtudiant).getId());
                int IdCours = absence.getId();
                int NbHeure = absence.getNbHeure();
                Date date = absence.getDate();

                // Ajouter les donn�es � la table
                tableModel2.addRow(new Object[]{IdCours, NbHeure, date});
             }
            
            }
        });
        panelAbsent.add(btnAbsent);
        
        JPanel panel2 = new JPanel();
        fAbsence.getContentPane().add(panel2);
        
        // Ajouter la JTable � un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(table2);
        panel2.add(scrollPane2);
        
        // Creation de la JTable avec un DefaultTableModel vide
        tableModel2 = new DefaultTableModel();
        tableModel2.setColumnIdentifiers(new Object[]{"Id du cours", "Nombre d'heure", "Date de l'absence"});
        table2 = new JTable(tableModel2);

        // Creation de la JTable avec un DefaultTableModel vide
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Prenom", "Nom", "identifiant"});
        table = new JTable(tableModel);

        // Ajouter la JTable � un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panelGroupe.add(scrollPane);
      	


		JButton retourBtn = new JButton("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameAbsence.dispose(); // ferme la fenetre actuelle
				EnseignantGUI window = new EnseignantGUI();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});

		JPanel panelBoutonRetour = new JPanel();
		panelBoutonRetour.add(retourBtn);
		frameAbsence.getContentPane().add(panelBoutonRetour);


	}
}