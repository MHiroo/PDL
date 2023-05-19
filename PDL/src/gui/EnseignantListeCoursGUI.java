package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.PlanningDAO;
import dao.Type_absenceDAO;
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

public class EnseignantListeCoursGUI {

	JFrame frame;
	JFrame frameListeCours;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnseignantListeCoursGUI window = new EnseignantListeCoursGUI();
					window.frameListeCours.setLocationRelativeTo(null);
					window.frameListeCours.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnseignantListeCoursGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Creation de la fenetre de la liste des cours de l'enseignant
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
		downContainer.setLayout(new GridLayout(1, 1));
		base.setLayout(new GridLayout(2,1));



		// Creation du tableau

		EnseignantDAO enseignantDAO = new EnseignantDAO();
		CoursDAO coursDAO = new CoursDAO();
		String[][] data = new String[enseignantDAO.getNomCours(SignInEnseignantGUI.id).size()][6];

		try {
			for (int i = 0; i < enseignantDAO.getNomCours(SignInEnseignantGUI.id).size(); i++) {
				data[i][0] = enseignantDAO.getNomCours(SignInEnseignantGUI.id).get(i);
				data[i][1] = enseignantDAO.getMasseHoraire(SignInEnseignantGUI.id).get(i);
				data[i][2] = enseignantDAO.getRepartitionAmphi(SignInEnseignantGUI.id).get(i);
				data[i][3] = enseignantDAO.getRepartitionTD(SignInEnseignantGUI.id).get(i);
				data[i][4] = enseignantDAO.getRepartitionTP(SignInEnseignantGUI.id).get(i);  
				data[i][5] = enseignantDAO.getRepartitionExam(SignInEnseignantGUI.id).get(i);


			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] columnNames = {"Nom du cours:", "Masse Horaire:", "Amphi:", "TD:", "TP:","Examen:"};
		JTable tableau = new JTable(data, columnNames);





		JButton retourBtn = new JButton("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListeCours.dispose(); // ferme la fenetre actuelle
				EnseignantGUI window = new EnseignantGUI();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});

		JPanel panelBoutonRetour = new JPanel();
		panelBoutonRetour.add(retourBtn);


		//distribution of the components in the 2 containers
		upContainer.add(new JScrollPane(tableau));
		downContainer.add(panelBoutonRetour);

		//Ajout des containers au container principal
		base.add(upContainer);
		base.add(downContainer);

		//to finish, we add the main container to the window
		frameListeCours.setContentPane(base);















	}
}