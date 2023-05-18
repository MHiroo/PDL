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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class PlanningGstGUI {

	JFrame frame;
	public JFrame frameCalendar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanningGstGUI window = new PlanningGstGUI();
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
	public PlanningGstGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * Creation de la fenetre principale
		 */
		frame = new JFrame();
		frame.setTitle("Choix du Groupe");
		frame.setBounds(300, 300, 400, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		//Initialisation des composants


		JPanel groupePanel = new JPanel();
		PlanningDAO planningDAO = new PlanningDAO();
		EnseignantDAO enseignantDAO = new EnseignantDAO();
		CoursDAO coursDAO = new CoursDAO();



		//Ajout bouton retour


		JButton retourBtn1 = new JButton("Retour");
		retourBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose(); // ferme la fenetre actuelle
				GestionnaireGUI window = new GestionnaireGUI();
				window.pframe.setLocationRelativeTo(null);
				window.pframe.setVisible(true);
			}
		});

		//Menu deroulant de selection du groupe
		int groupe = 1;
		GroupeDAO groupeDAO = new GroupeDAO();
		JPanel panelBoutonJst = new JPanel();
		int nbGroupe = groupeDAO.getListIdGroupe().size();


		JComboBox<Integer> comboBox = new JComboBox<>();
		for (int i = 0; i < nbGroupe; i++) {
			comboBox.addItem(groupeDAO.getListIdGroupe().get(i)) ;
		}

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int groupe = (Integer) comboBox.getSelectedItem();
				frame.setVisible(false);
				/**
				 * Creation de la fenetre calendar
				 */
				frameCalendar = new JFrame();
				frameCalendar.setTitle("Planning");
				frameCalendar.setBounds(1500, 1500, 1500, 1000);
				frameCalendar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frameCalendar.getContentPane().setLayout(new BoxLayout(frameCalendar.getContentPane(), BoxLayout.Y_AXIS));

				//Initialisation des composants


				JPanel base = new JPanel();
				JPanel upContainer = new JPanel();
				upContainer.setBounds(0, 0, 1484, 567);
				JPanel downContainer = new JPanel();
				downContainer.setBounds(0, 569, 1484, 391);
				JPanel panelBouton = new JPanel();
				panelBouton.setBounds(1174, 106, 239, 218);
				JPanel panelModifPlanning = new JPanel();
				panelModifPlanning.setBounds(427, 491, 614, 69);
				JLabel lblNewLabel2 = new JLabel("Planning du jour:");
				JButton btnNewButton= null;
				JButton btnNewButton_1= null;
				//Ajout bouton retour


				JButton retourBtn = new JButton("Retour");
				retourBtn.setBounds(64, 109, 111, 40);
				retourBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						frameCalendar.dispose(); // ferme la fenetre actuelle
						frame.setVisible(true);
					}
				});

				// Creation boutons ajouter modifier planning
				/**
				 * Creation du panel de modification du planning
				 */
				JLabel lblEnseignant = new JLabel("Enseignant:");
				JLabel lblCours = new JLabel("Cours:");
				JLabel lblSalle = new JLabel("Salle:");
				JLabel lblDuree = new JLabel("Duree:");
				JLabel lblHeure = new JLabel("Heure:");
				JTextField textFieldSalle = new JTextField();
				textFieldSalle.setColumns(6);
				String enseignant="";
				String cours="";
				String salle="AIAA";
				Double dureeDouble=0.0;
				Time temps=null;



				//Creation du calendrier et reccuperation de la date selectionnee

				JCalendar calendar = new JCalendar();
				calendar.setBounds(0, 0, 1484, 480);

				java.util.Date utilDate = new java.util.Date();
				utilDate=calendar.getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				// Formater la date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String formattedDate = sdf.format(sqlDate);



				//Creation du tableau de cours du jour selectionne


				int nbLigne = planningDAO.getPlanningJour(groupe, formattedDate).size();
				String[][] data = new String[nbLigne][5];


				try {
					for (int i = 0; i < nbLigne; i++) {
						data[i][0] = planningDAO.getPlanningJour(groupe, formattedDate).get(i).getHeure()+ "";
						data[i][1] = coursDAO.getNomCours(planningDAO.getPlanningJour(groupe, formattedDate).get(i).getIdCours());
						data[i][2] = planningDAO.getPlanningJour(groupe, formattedDate).get(i).getSalle();
						data[i][3] = enseignantDAO.getNomEnseignant(planningDAO.getPlanningJour(groupe, formattedDate).get(i).getIdEnseignant());
						data[i][4] = planningDAO.getPlanningJour(groupe, formattedDate).get(i).getDuree()+ "h";									
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String[] columnNames = {"H:", "Cours:", "Salle:", "Enseignant:", "Duree:"};

				DefaultTableModel model = new DefaultTableModel(data, columnNames);
				JTable tableau = new JTable(model);


				// Création du combobox des enseignants a selectionner

				JComboBox<String> comboBoxEnseignant = new JComboBox<String>();
				for (int i = 0; i < enseignantDAO.getList().size(); i++) {
					comboBoxEnseignant.addItem(enseignantDAO.getList().get(i).getName());
				}

						
					


				// Création du combobox des cours a selectionner

				JComboBox<String> comboBoxCours = new JComboBox<String>();
				for (int i = 0; i < coursDAO.getList().size(); i++) {
					comboBoxCours.addItem(coursDAO.getList().get(i).getNom());
				}
				


				// Création du combobox des duree a selectionner

				List<Double> duree = new ArrayList<>();

				for (Double i = 1.0; i <= 4; i++) {
					duree.add(i );
				}
				JComboBox<Double> comboBoxDuree = new JComboBox<Double>();
				for (int i = 0; i < duree.size(); i++) {
					comboBoxDuree.addItem(duree.get(i));
				}


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

					    heureActuelle += intervalleMillis;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JComboBox<Time> comboBoxHeure = new JComboBox<Time>();
				for (int i = 0; i < listeHeures.size(); i++) {
					comboBoxHeure.addItem(listeHeures.get(i));
				}
				
				
				// Création du combobox des seance du jour a selectionner
				
				JComboBox<Integer> comboBoxSeance = new JComboBox<Integer>();
				for (int i = 1; i < data.length+1; i++) {
					comboBoxSeance.addItem(i);
				}
				
				
				// Creation du bouton ajouter un nouveau planning a partir des informations selectionees

				btnNewButton = new JButton("Ajouter");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String enseignant = comboBoxEnseignant.getSelectedItem().toString();
						Double dureeDouble = (Double) comboBoxDuree.getSelectedItem();
						String cours = comboBoxCours.getSelectedItem().toString();
						Time temps = (Time) comboBoxHeure.getSelectedItem();
						java.sql.Date sqlDate2 = new java.sql.Date(calendar.getDate().getTime());
						Planning planning = new Planning( groupe, enseignantDAO.getId(enseignant),coursDAO.getId(cours),sqlDate2 , salle, dureeDouble, temps);
						planningDAO.add(planning);
					}
				});


				// Creation du bouton modifier un planning a partir des informations selectionees

				btnNewButton_1 = new JButton("Modifier");

				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

						
						Integer NumeroSeance = (Integer) comboBoxSeance.getSelectedItem();
						String enseignant = comboBoxEnseignant.getSelectedItem().toString();
						Double dureeDouble = (Double) comboBoxDuree.getSelectedItem();
						String cours = comboBoxCours.getSelectedItem().toString();
						Time temps = (Time) comboBoxHeure.getSelectedItem();
						java.sql.Date sqlDate2 = new java.sql.Date(calendar.getDate().getTime());
						Planning planning = new Planning( NumeroSeance,groupe, enseignantDAO.getId(enseignant),coursDAO.getId(cours),sqlDate2, salle, dureeDouble, temps);
						planningDAO.update(planning );
					}
				});
				lblNewLabel2.setBounds(161, 11, 144, 78);
				btnNewButton.setBounds(1051, 491, 89, 23);
				btnNewButton_1.setBounds(1051, 537, 89, 23);



				lblNewLabel2 = new JLabel("Planning du jour:");
				lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 15));


				// Création d'un bouton pour récupérer la date sélectionnée

				JButton button = new JButton("Selectionner");
				button.setBounds(64, 44, 111, 40);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {


						frameCalendar.dispose();
						frameCalendar = new JFrame();
						frameCalendar.setBounds(1500, 1500, 1500, 1000);
						frameCalendar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frameCalendar.getContentPane().setLayout(new BoxLayout(frameCalendar.getContentPane(), BoxLayout.Y_AXIS));
						JPanel base = new JPanel();
						JPanel downContainer = new JPanel();
						downContainer.setBounds(0, 569, 1484, 391);


						java.util.Date utilDate = calendar.getDate();
						Calendar cal = Calendar.getInstance();
						cal.setTime(utilDate);
						int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH) + 1; // les mois sont indexés à partir de 0
						int year = cal.get(Calendar.YEAR);
						String formattedDate = (dayOfMonth + "-" + month + "-" + year);


						//Creation du tableau de cours du jour selectionne


						int nbLigne = planningDAO.getPlanningJour(groupe, formattedDate).size();
						String[][] data = new String[nbLigne][5];

						try {
							for (int i = 0; i < nbLigne; i++) {
								data[i][0] = planningDAO.getPlanningJour(groupe, formattedDate).get(i).getHeure()+ "";
								data[i][1] = coursDAO.getNomCours(planningDAO.getPlanningJour(groupe, formattedDate).get(i).getIdCours());
								data[i][2] = planningDAO.getPlanningJour(groupe, formattedDate).get(i).getSalle();
								data[i][3] = enseignantDAO.getNomEnseignant(planningDAO.getPlanningJour(groupe, formattedDate).get(i).getIdEnseignant());
								data[i][4] = planningDAO.getPlanningJour(groupe, formattedDate).get(i).getDuree()+ "h";									
							}
						}
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}



						JTable tableau = new JTable(data, columnNames);


						downContainer.setLayout(null);
						JScrollPane scrollPane = new JScrollPane(tableau);
						scrollPane.setBounds(355, 0, 743, 386);
						downContainer.add(scrollPane);
						downContainer.add(panelBouton);

						base.setLayout(null);

						base.add(upContainer);
						base.add(downContainer);


						//to finish, we add the main container to the window
						frameCalendar.setContentPane(base);
						frameCalendar.setLocationRelativeTo(null);
						frameCalendar.setVisible(true);
					}
				});



				upContainer.add(lblNewLabel2);
				upContainer.add(btnNewButton);
				upContainer.add(btnNewButton_1);


				panelModifPlanning.add(lblEnseignant);
				panelModifPlanning.add(comboBoxEnseignant);
				panelModifPlanning.add(lblCours);
				panelModifPlanning.add(comboBoxCours);
				panelModifPlanning.add(lblSalle);
				panelModifPlanning.add(textFieldSalle);
				panelModifPlanning.add(lblDuree);
				panelModifPlanning.add(comboBoxDuree);
				panelModifPlanning.add(lblHeure);
				panelModifPlanning.add(comboBoxHeure);
				panelModifPlanning.add(comboBoxSeance);
				panelBouton.setLayout(null);
				panelBouton.add(button);
				panelBouton.add(retourBtn);
				JScrollPane scrollPane = new JScrollPane(tableau);
				scrollPane.setBounds(355, 0, 743, 386);

				upContainer.setLayout(null);
				upContainer.add(calendar);
				upContainer.add(panelModifPlanning);
				downContainer.setLayout(null);
				downContainer.add(scrollPane);
				downContainer.add(panelBouton);
				base.setLayout(null);

				//Ajout des containers au container principal
				base.add(upContainer);
				base.add(downContainer);


				//to finish, we add the main container to the window

				frameCalendar.setContentPane(base);

				frameCalendar.setLocationRelativeTo(null);
				frameCalendar.setVisible(true);
			}
		});

		JLabel lblNewLabel = new JLabel("Veuillez choisir le groupe:");
		panelBoutonJst.add(lblNewLabel);
		panelBoutonJst.add(comboBox);
		panelBoutonJst.add(retourBtn1);
		//to finish, we add the main container to the window

		frame.setContentPane(panelBoutonJst);









	}
}