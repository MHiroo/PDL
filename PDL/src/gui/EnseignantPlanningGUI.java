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

public class EnseignantPlanningGUI {

	JFrame frame;
	JFrame framePlanning;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnseignantPlanningGUI window = new EnseignantPlanningGUI();
					window.framePlanning.setLocationRelativeTo(null);
					window.framePlanning.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnseignantPlanningGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		


		/**
		 * Creation de la fenetre Planning de l'etudiant
		 */


		framePlanning = new JFrame();
		framePlanning.setBounds(1500, 1500, 1500, 1000);
		framePlanning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));

		//Initialisation des composants


		JPanel base2 = new JPanel();
		JPanel upContainer2 = new JPanel();
		upContainer2.setBounds(0, 0, 1484, 480);
		JPanel downContainer2 = new JPanel();
		downContainer2.setBounds(0, 480, 1484, 480);
		JPanel panelBouton = new JPanel();
		panelBouton.setBounds(1167, 92, 239, 218);
		
		

		
	




		//Creation du calendrier et reccuperation de la date selectionnee

		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 0, 1484, 480);
		
		// Récupération de la date sélectionnée dans le JCalendar
        java.util.Date selectedDate = calendar.getDate();

        // Conversion de la date en format "jjmmaaaa"
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String formattedDate = dateFormat.format(selectedDate);

        // Conversion de la date en LocalDate
        LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyyyy"));



		//Creation du tableau de cours du jour selectionne

		PlanningDAO planningDAO = new PlanningDAO();
		EnseignantDAO enseignantDAO = new EnseignantDAO();
		CoursDAO coursDAO = new CoursDAO();


		
		int nbLigne = planningDAO.getPlanningJourEnseignant(SignInEnseignantGUI.id, date).size();
		String[][] data2 = new String[nbLigne][4];
		

		try {
			for (int i = 0; i < nbLigne; i++) {
				Planning pln = planningDAO.getPlanningJourEnseignant(SignInEnseignantGUI.id, date).get(i);
				data2[i][0] = pln.getHeure()+ "h";
				data2[i][1] = coursDAO.getNomCours(pln.getIdCours());
				data2[i][2] = pln.getSalle();
				data2[i][3] = pln.getDuree()+ "h";									
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] columnNames2 = {"H:", "Cours:", "Salle:", "Duree:"};

		DefaultTableModel model = new DefaultTableModel(data2, columnNames2);
		JTable tableau2 = new JTable(model);



		// Création d'un bouton pour récupérer la date sélectionnée

		JButton button = new JButton("Rafraichir");
		button.setBounds(64, 44, 111, 40);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				framePlanning.dispose();
				framePlanning = new JFrame();
				framePlanning.setBounds(1500, 1500, 1500, 1000);
				framePlanning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));
				JPanel base2 = new JPanel();
				JPanel downContainer2 = new JPanel();
				downContainer2.setBounds(0, 480, 1484, 480);
				downContainer2.setLayout(new GridLayout(1, 2));
				base2.setLayout(new GridLayout(2,1));


				// Récupération de la date sélectionnée dans le JCalendar
		        java.util.Date selectedDate = calendar.getDate();

		        // Conversion de la date en format "jjmmaaaa"
		        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		        String formattedDate = dateFormat.format(selectedDate);

		        // Conversion de la date en LocalDate
		        LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyyyy"));




				//Creation du tableau de cours du jour selectionne


				
				int nbLigne = planningDAO.getPlanningJourEnseignant(SignInEnseignantGUI.id, date).size();
				String[][] data2 = new String[nbLigne][4];
				

				try {
					for (int i = 0; i < nbLigne; i++) {
						Planning pln = planningDAO.getPlanningJourEnseignant(SignInEnseignantGUI.id, date).get(i);
						data2[i][0] = pln.getHeure()+ "h";
						data2[i][1] = coursDAO.getNomCours(pln.getIdCours());
						data2[i][2] = pln.getSalle();
						data2[i][3] = pln.getDuree()+ "h";									
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				JTable tableau2 = new JTable(data2, columnNames2);

				downContainer2.setLayout(null);
				JScrollPane scrollPane = new JScrollPane(tableau2);
				scrollPane.setBounds(355, 0, 742, 447);
				downContainer2.add(scrollPane);
				downContainer2.add(panelBouton);
				base2.setLayout(null);

				base2.add(upContainer2);
				base2.add(downContainer2);

				JLabel lblNewLabel = new JLabel("Planning du jour:");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblNewLabel.setBounds(161, 11, 144, 78);
				downContainer2.add(lblNewLabel);

				//to finish, we add the main container to the window
				framePlanning.setContentPane(base2);
				framePlanning.setLocationRelativeTo(null);
				framePlanning.setVisible(true);
			}
		});
		panelBouton.setLayout(null);

		//Ajout bouton retour


		JButton retourBtn2 = new JButton("Retour");
		retourBtn2.setSize(111, 40);
		retourBtn2.setLocation(64, 103);
		retourBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				framePlanning.dispose(); // ferme la fenetre actuelle
				EnseignantGUI window = new EnseignantGUI();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});

		panelBouton.add(button);
		panelBouton.add(retourBtn2);
		upContainer2.setLayout(null);



		//distribution of the components in the 2 containers
		upContainer2.add(calendar);
		downContainer2.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(tableau2);
		scrollPane.setBounds(355, 0, 742, 447);
		downContainer2.add(scrollPane);
		downContainer2.add(panelBouton);
		base2.setLayout(null);

		//Ajout des containers au container principal
		base2.add(upContainer2);
		base2.add(downContainer2);

		JLabel lblNewLabel = new JLabel("Planning du jour:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(161, 11, 144, 78);
		downContainer2.add(lblNewLabel);

		//to finish, we add the main container to the window

		framePlanning.setContentPane(base2);















	}
}