package gui;

import java.awt.Color;
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
import java.io.File;
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

public class AdminAbsenceGUI {

	JFrame frame;
	JFrame frameListeAbsences;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAbsenceGUI window = new AdminAbsenceGUI();
					window.frameListeAbsences.setLocationRelativeTo(null);
					window.frameListeAbsences.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminAbsenceGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		


		/**
		 * Creation de la fenetre Liste d'absence de l'etudiant
		 */
		frameListeAbsences = new JFrame();
		frameListeAbsences.setBounds(1500, 1500, 1500, 1000);
		frameListeAbsences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameListeAbsences.getContentPane().setLayout(new BoxLayout(frameListeAbsences.getContentPane(), BoxLayout.Y_AXIS));



		//Initialisation des composants


		JPanel base3 = new JPanel();
		JPanel upContainer3 = new JPanel();
		JPanel downContainer3 = new JPanel();

		//each panel gets its own layout
		upContainer3.setLayout(new GridLayout(1, 2));
		//downContainer3.setLayout(new GridLayout(1, 1));
		base3.setLayout(new GridLayout(2,1));

		//System.out.println(etudiantDAO.getStatutAbs(1).get(0));
		AbsenceDAO absenceDAO = new AbsenceDAO();
		CoursDAO coursDAO = new CoursDAO();
		EtudiantDAO etudiantDAO = new EtudiantDAO();
		int nbLigne3 =absenceDAO.getList().size();
		
		// Creation du tableau
		String[][] data3 = new String[nbLigne3][6];
		String[] columnNames3 = {"Date:", "Cours:", "Heure debut", "Heures d'absence:","Statut:", "Nom etudiant:"};
		

		try {
			for (int i = 0; i < nbLigne3; i++) {

				Absence absence = absenceDAO.getList().get(i);
				data3[i][0] = absence.getDate()+"";
				data3[i][1] = coursDAO.getNomCours(absence.getIdCours());
				data3[i][2] = absence.getHeureDebut()+"";
				data3[i][3] = absence.getNbHeure()+"";
				data3[i][4] = absence.getStatut();
				data3[i][5] = etudiantDAO.get(absence.getIdEtud()).getName()+ " " +etudiantDAO.get(absence.getIdEtud()).getFirstName();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JTable tableau3 = new JTable(data3, columnNames3);

		






		//Ajout bouton retour

		JPanel panelBoutonRetour3 = new JPanel();
		JButton retourBtn3 = new JButton("Retour");
		retourBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListeAbsences.dispose(); // ferme la fenetre actuelle
				AdminGUI window = new AdminGUI();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});
		retourBtn3.setBounds(50, 40, 300, 200);
		panelBoutonRetour3.add(retourBtn3);


		//distribution of the components in the 2 containers
		upContainer3.add(new JScrollPane(tableau3));
		downContainer3.add(panelBoutonRetour3);

		//Ajout des containers au container principal
		base3.add(upContainer3);
		base3.add(downContainer3);

		//to finish, we add the main container to the window
		frameListeAbsences.setContentPane(base3);












	}
}