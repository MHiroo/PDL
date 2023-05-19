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
				String salle="";
				Double dureeDouble=0.0;
				Double temps=0.0;



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
				
				
				// Création du combobox des seance du jour a selectionner
				
				JComboBox<Integer> comboBoxSeance = new JComboBox<Integer>();
				comboBoxSeance.setSize(111, 40);
				comboBoxSeance.setLocation(64, 108);
				for (int i = 0; i < data.length; i++) {
					comboBoxSeance.addItem(i);
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
						Planning planning = new Planning( groupe, enseignantDAO.getId(enseignant),coursDAO.getIdFromNom(cours),date , salle, dureeDouble, temps);
						planningDAO.add(planning);
					}
				});


				// Creation du bouton modifier un planning a partir des informations selectionees

				btnNewButton_1 = new JButton("Modifier");

				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Integer NumeroSeance = (Integer) comboBoxSeance.getSelectedItem();
						NumeroSeance = idPlanning[NumeroSeance];
						String enseignant = comboBoxEnseignant.getSelectedItem().toString();
						Double dureeDouble = (Double) comboBoxDuree.getSelectedItem();
						String cours = comboBoxCours.getSelectedItem().toString();
						String salle = textFieldSalle.getText();
						Double temps = (Double) comboBoxHeure.getSelectedItem();
						Planning planning = new Planning( NumeroSeance,groupe, enseignantDAO.getId(enseignant),coursDAO.getIdFromNom(cours),date, salle, dureeDouble, temps);
						planningDAO.update(planning );
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




						//Creation du tableau de cours du jour selectionne


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
						}
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}



						JTable tableau = new JTable(data, columnNames);
						
						
						// Création du combobox des seance du jour a selectionner
						
						JComboBox<Integer> comboBoxSeance = new JComboBox<Integer>();
						for (int i = 0; i < data.length; i++) {
							comboBoxSeance.addItem(i);
						}
						
						panelBouton.setLayout(null);
						panelBouton.add(button);
						panelBouton.add(retourBtn);
						panelBouton.add(comboBoxSeance);
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

		JLabel lblNewLabel = new JLabel("Veuillez choisir le groupe:");
		panelBoutonJst.add(lblNewLabel);
		panelBoutonJst.add(comboBox);
		panelBoutonJst.add(retourBtn1);
		//to finish, we add the main container to the window

		frame.setContentPane(panelBoutonJst);









	}
}