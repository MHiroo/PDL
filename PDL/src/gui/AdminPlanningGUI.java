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

public class AdminPlanningGUI {


	JFrame framePlanning;
	JFrame framePlanningEnseignant;
	JFrame framePlanningEtudiant;
	public JFrame frameCalendar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPlanningGUI window = new AdminPlanningGUI();
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
	public AdminPlanningGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {



		/**
		 * Creation de la fenetre d'accueil
		 */
		framePlanning = new JFrame();
		framePlanning.setBounds(100, 100, 450, 300);
		framePlanning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePlanning.getContentPane().setLayout(new BoxLayout(framePlanning.getContentPane(), BoxLayout.Y_AXIS));


		//Initialisation des composants


		JPanel base = new JPanel();
		JPanel upContainer = new JPanel();
		upContainer.setBounds(0, 0, 434, 220);
		JPanel downContainer = new JPanel();
		downContainer.setBounds(0, 221, 434, 39);

		EnseignantDAO enseignantDAO = new EnseignantDAO();
		GroupeDAO groupeDAO = new GroupeDAO();


		// Création du combobox des enseignants a selectionner

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(69, 111, 86, 22);
		for (int i = 0; i < enseignantDAO.getList().size(); i++) {
			comboBox.addItem(enseignantDAO.getList().get(i).getName());
		}
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				framePlanning.setVisible(false);
				int idEnseignant = (Integer) enseignantDAO.getId(comboBox.getSelectedItem().toString());
				System.out.println(idEnseignant);
				/**
				 * Creation de la fenetre Planning de l'etudiant
				 */


				framePlanningEnseignant = new JFrame();
				framePlanningEnseignant.setBounds(1500, 1500, 1500, 1000);
				framePlanningEnseignant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				framePlanningEnseignant.getContentPane().setLayout(new BoxLayout(framePlanningEnseignant.getContentPane(), BoxLayout.Y_AXIS));

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


				
				int nbLigne = planningDAO.getPlanningJourEnseignant(idEnseignant, date).size();
				String[][] data2 = new String[nbLigne][4];
				

				try {
					for (int i = 0; i < nbLigne; i++) {
						Planning pln = planningDAO.getPlanningJourEnseignant(idEnseignant, date).get(i);
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


						framePlanningEnseignant.dispose();
						framePlanningEnseignant = new JFrame();
						framePlanningEnseignant.setBounds(1500, 1500, 1500, 1000);
						framePlanningEnseignant.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						framePlanningEnseignant.getContentPane().setLayout(new BoxLayout(framePlanningEnseignant.getContentPane(), BoxLayout.Y_AXIS));
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


						
						int nbLigne = planningDAO.getPlanningJourEnseignant(idEnseignant, date).size();
						String[][] data2 = new String[nbLigne][4];
						

						try {
							for (int i = 0; i < nbLigne; i++) {
								Planning pln = planningDAO.getPlanningJourEnseignant(idEnseignant, date).get(i);
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
						framePlanningEnseignant.setContentPane(base2);
						framePlanningEnseignant.setLocationRelativeTo(null);
						framePlanningEnseignant.setVisible(true);
					}
				});
				panelBouton.setLayout(null);

				//Ajout bouton retour


				JButton retourBtn2 = new JButton("Retour");
				retourBtn2.setSize(111, 40);
				retourBtn2.setLocation(64, 103);
				retourBtn2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						framePlanningEnseignant.dispose(); // ferme la fenetre actuelle
						AdminPlanningGUI window = new AdminPlanningGUI();
						window.framePlanning.setLocationRelativeTo(null);
						window.framePlanning.setVisible(true);
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

				framePlanningEnseignant.setContentPane(base2);
				framePlanningEnseignant.setLocationRelativeTo(null);
				framePlanningEnseignant.setVisible(true);
			}
		});	



		//Création du combobox du groupe a selectionner

		int groupe = 1;
		JPanel panelBoutonJst = new JPanel();
		int nbGroupe = groupeDAO.getListIdGroupe().size();

		JComboBox<Integer> comboBox2 = new JComboBox<>();
		comboBox2.setBounds(283, 111, 50, 22);
		for (int i = 0; i < nbGroupe; i++) {
			comboBox2.addItem(groupeDAO.getListIdGroupe().get(i)) ;
		}

		comboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				framePlanning.setVisible(false);
				int groupe = (Integer) comboBox2.getSelectedItem();

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
				panelBouton.setBounds(1174, 33, 239, 353);
				JPanel panelModifPlanning = new JPanel();
				panelModifPlanning.setBounds(427, 491, 614, 69);
				JLabel lblNewLabel2 = new JLabel("Planning du jour:");
				JButton btnNewButton= null;
				JButton btnNewButton_1= null;
				//Ajout bouton retour


				JButton retourBtn = new JButton("Retour");
				retourBtn.setBounds(64, 252, 111, 40);
				retourBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						frameCalendar.dispose(); // ferme la fenetre actuelle
						framePlanning.setVisible(true);
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



				//Creation du calendrier et reccuperation de la date selectionnee

				JCalendar calendar = new JCalendar();
				calendar.setBounds(0, 0, 1484, 480);

				// Récupération de la date sélectionnée dans le JCalendar
				Date selectedDate = calendar.getDate();

				// Conversion de la date en format "jjmmaaaa"
				SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
				String formattedDate = dateFormat.format(selectedDate);

				// Conversion de la date en LocalDate
				LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyyyy"));

				Planning plnDate = new Planning(date);
				plnDate.setDate(date);;

				//Creation du tableau de cours du jour selectionne


				PlanningDAO planningDAO = new PlanningDAO();
				EnseignantDAO enseignantDAO = new EnseignantDAO();
				CoursDAO coursDAO = new CoursDAO();
				int nbLigne = planningDAO.getPlanningJour(groupe, date).size();
				String[][] data = new String[nbLigne][5];
				Integer[] idPlanning = new Integer[nbLigne];


				try {
					for (int i = 0; i < nbLigne; i++) {
						Planning pln =planningDAO.getPlanningJour(groupe, date).get(i);
						idPlanning[i] = pln.getId();
						data[i][0] = pln.getHeure()+ "h";
						data[i][1] = coursDAO.getNomCours(pln.getIdCours());
						data[i][2] = pln.getSalle();
						data[i][3] = enseignantDAO.getNomEnseignant(pln.getIdEnseignant());
						data[i][4] = pln.getDuree()+ "h";									
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String[] columnNames = {"H:", "Cours:", "Salle:", "Enseignant:", "Duree:"};

				DefaultTableModel model = new DefaultTableModel(data, columnNames);
				JTable tableau = new JTable(model);


				// Création du combobox des seance du jour a selectionner

				JComboBox<Integer> comboBoxSeance = new JComboBox<Integer>();
				comboBoxSeance.setSize(111, 40);
				comboBoxSeance.setLocation(64, 108);
				for (int i = 0; i < data.length; i++) {
					comboBoxSeance.addItem(i+1);
				}


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





				// Creation du bouton ajouter un nouveau planning a partir des informations selectionees

				btnNewButton = new JButton("Ajouter");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String enseignant = comboBoxEnseignant.getSelectedItem().toString();
						Double dureeDouble = (Double) comboBoxDuree.getSelectedItem();
						String cours = comboBoxCours.getSelectedItem().toString();
						Double temps = (Double) comboBoxHeure.getSelectedItem();
						String salle = textFieldSalle.getText();
						JCalendar calendar = new JCalendar();
						calendar.setBounds(0, 0, 1484, 480);
						Planning planning = new Planning( groupe, enseignantDAO.getId(enseignant),coursDAO.getIdFromNom(cours),plnDate.getDate() , salle, dureeDouble, temps);
						planningDAO.add(planning);
					}
				});


				// Creation du bouton modifier un planning a partir des informations selectionees

				btnNewButton_1 = new JButton("Modifier");

				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Integer NumeroSeance = (Integer) comboBoxSeance.getSelectedItem();
						NumeroSeance = idPlanning[NumeroSeance-1];
						String enseignant = comboBoxEnseignant.getSelectedItem().toString();
						Double dureeDouble = (Double) comboBoxDuree.getSelectedItem();
						String cours = comboBoxCours.getSelectedItem().toString();
						String salle = textFieldSalle.getText();
						Double temps = (Double) comboBoxHeure.getSelectedItem();
						JCalendar calendar = new JCalendar();
						calendar.setBounds(0, 0, 1484, 480);
						Planning planning = new Planning( NumeroSeance,groupe, enseignantDAO.getId(enseignant),coursDAO.getIdFromNom(cours),plnDate.getDate(), salle, dureeDouble, temps);
						planningDAO.update(planning );
					}
				});

				JButton btnNewButton_2 = new JButton("Supprimer");
				btnNewButton_2.setBounds(1150, 537, 100, 23);
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Integer NumeroSeance = (Integer) comboBoxSeance.getSelectedItem();
						int i= NumeroSeance-1;
						Integer j =planningDAO.getPlanningJour(groupe, plnDate.getDate()).get(i).getId();
						planningDAO.delete(j);
						frameCalendar.dispose();
						framePlanning.setVisible(true);
					}
				});




				lblNewLabel2.setBounds(161, 11, 144, 78);
				btnNewButton.setBounds(1051, 491, 89, 23);
				btnNewButton_1.setBounds(1051, 537, 89, 23);



				lblNewLabel2 = new JLabel("Planning du jour:");
				lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 15));


				// Création d'un bouton pour récupérer la date sélectionnée

				JButton button = new JButton("Raffraichir");
				button.setBounds(64, 11, 111, 40);
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
						JPanel panelBouton2 = new JPanel();
						panelBouton2.setBounds(1174, 33, 239, 353);
						downContainer.setBounds(0, 569, 1484, 391);

						PlanningDAO planningDAO = new PlanningDAO();
						EnseignantDAO enseignantDAO = new EnseignantDAO();
						CoursDAO coursDAO = new CoursDAO();

						// Récupération de la date sélectionnée dans le JCalendar
						Date selectedDate = calendar.getDate();

						// Conversion de la date en format "jjmmaaaa"
						SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
						String formattedDate = dateFormat.format(selectedDate);

						// Conversion de la date en LocalDate
						LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyyyy"));

						plnDate.setDate(date);


						//Creation du tableau de cours du jour selectionne


						int nbLigne = planningDAO.getPlanningJour(groupe, date).size();
						String[][] data2 = new String[nbLigne][5];
						Integer[] idPlanning = new Integer[nbLigne];

						try {
							for (int i = 0; i < nbLigne; i++) {
								Planning pln =planningDAO.getPlanningJour(groupe, date).get(i);
								idPlanning[i] = pln.getId();
								data2[i][0] = pln.getHeure()+ "h";
								data2[i][1] = coursDAO.getNomCours(pln.getIdCours());
								data2[i][2] = pln.getSalle();
								data2[i][3] = enseignantDAO.getNomEnseignant(pln.getIdEnseignant());
								data2[i][4] = pln.getDuree()+ "h";								
							}
						}
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JTable tableau = new JTable(data2, columnNames);


						// Création du combobox des seance du jour a selectionner

						JComboBox<Integer> comboBoxSeance2 = new JComboBox<Integer>();
						comboBoxSeance2.setSize(111, 40);
						comboBoxSeance2.setLocation(64, 108);
						for (int i = 0; i < data2.length; i++) {
							comboBoxSeance2.addItem(i+1);
						}

						panelBouton2.setLayout(null);
						panelBouton2.add(button);
						panelBouton2.add(retourBtn);
						panelBouton2.add(comboBoxSeance2);
						downContainer.setLayout(null);
						JScrollPane scrollPane = new JScrollPane(tableau);
						scrollPane.setBounds(355, 0, 743, 386);
						downContainer.add(scrollPane);
						downContainer.add(panelBouton2);


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
				upContainer.add(btnNewButton_2);

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

				panelBouton.setLayout(null);
				panelBouton.add(button);
				panelBouton.add(retourBtn);
				panelBouton.add(comboBoxSeance);
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
		downContainer.setLayout(null);


		JButton retourBtn = new JButton("Retour");
		retourBtn.setBounds(343, 11, 81, 23);
		downContainer.add(retourBtn);
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				framePlanning.dispose(); // ferme la fenetre actuelle
				AdminGUI window = new AdminGUI();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});
		upContainer.setLayout(null);

		//distribution of the components in the 2 containers
		upContainer.add(comboBox);
		upContainer.add(comboBox2);
		downContainer.add(retourBtn);
		base.setLayout(null);

		//Ajout des containers au container principal
		base.add(upContainer);

		JLabel lblNewLabel = new JLabel("Planning : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(199, 11, 148, 14);
		upContainer.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Choix de l'enseignant :");
		lblNewLabel_1.setBounds(69, 67, 190, 14);
		upContainer.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Choix du groupe :");
		lblNewLabel_2.setBounds(269, 67, 184, 14);
		upContainer.add(lblNewLabel_2);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(187, 36, 86, 184);
		upContainer.add(verticalStrut);
		base.add(downContainer);


		//to finish, we add the main container to the window
		framePlanning.setContentPane(base);







	}
}