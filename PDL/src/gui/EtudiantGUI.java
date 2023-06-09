package gui;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JCalendar;

import dao.AbsenceDAO;
import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.PlanningDAO;
import model.Absence;
import model.Planning;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.GridBagLayout;

public class EtudiantGUI extends JFrame{

	JFrame frame;
	private JFrame frameListeCours;
	private JFrame framePlanning;
	private JFrame frameListeAbsences;
	private Integer selectedNumber;
	private JFrame frameListeAbsenceAnticiper;
	private JFrame frameLien;
	private JLabel label;


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
		 * Creation de la fenetre d'accueil de l'etudiant
		 */

		frame=this;
		this.setTitle("Menu Etudiant");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));




		/**
		 * Creation de la fenetre de la liste des cours de l'etudiant
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

		EtudiantDAO etudiantDAO = new EtudiantDAO();
		String[][] data = new String[etudiantDAO.getNomCours(SignInEtudiantGUI.id).size()][7];

		try {
			for (int i = 0; i < etudiantDAO.getNomCours(SignInEtudiantGUI.id).size(); i++) {
				for (int j = 0; j < 7; j++) {
					if (j == 0) {
						data[i][j] = etudiantDAO.getNomCours(SignInEtudiantGUI.id).get(i);
					} else if (j == 1) {
						data[i][j] = etudiantDAO.getMasseHoraire(SignInEtudiantGUI.id).get(i);
					} else if (j == 2) {
						data[i][j] = etudiantDAO.getRepartitionAmphi(SignInEtudiantGUI.id).get(i);
						data[i][j+1] = etudiantDAO.getRepartitionTD(SignInEtudiantGUI.id).get(i);
						data[i][j+2] = etudiantDAO.getRepartitionTP(SignInEtudiantGUI.id).get(i);  
						data[i][j+3] = etudiantDAO.getRepartitionExam(SignInEtudiantGUI.id).get(i);
					} else if (j == 6) {
						data[i][j] = etudiantDAO.getNomEnseignant(SignInEtudiantGUI.id).get(i);
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] columnNames = {"Nom du cours:", "Masse Horaire:", "Amphi:", "TD:", "TP:","Examen:", "Enseignant:"};
		JTable tableau = new JTable(data, columnNames);





		//Creation du bouton retour
		JButton retourBtn = new JButton("Retour");
		retourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListeCours.dispose(); // ferme la fenetre actuelle
				frame.setVisible(true);
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
		panelBouton.setBounds(1174, 106, 239, 218);

		//Ajout bouton retour


		JButton retourBtn2 = new JButton("Retour");
		retourBtn2.setBounds(64, 109, 111, 40);
		retourBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				framePlanning.dispose(); // ferme la fenetre actuelle
				frame.setVisible(true);
			}
		});


		
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



		int nbLigne = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).size();
		String[][] data2 = new String[nbLigne][5];
		Integer[] idPlanning = new Integer[nbLigne];

		try {
			for (int i = 0; i < nbLigne; i++) {
				Planning pln =planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i);
				idPlanning[i] = pln.getId();
				data2[i][0] = pln.getHeure()+ "";
				data2[i][1] = coursDAO.getNomCours(pln.getIdCours());
				data2[i][2] = pln.getSalle();
				data2[i][3] = enseignantDAO.getNomEnseignant(pln.getIdEnseignant());
				data2[i][4] = pln.getDuree()+ "h";									
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] columnNames2 = {"H:", "Cours:", "Salle:", "Enseignant:", "Duree:"};

		DefaultTableModel model = new DefaultTableModel(data2, columnNames2);
		JTable tableau2 = new JTable(model);


		// Création du combobox des seance du jour a selectionner

		JComboBox<Integer> comboBoxSeance = new JComboBox<Integer>();
		comboBoxSeance.setSize(50, 40);
		comboBoxSeance.setLocation(100, 170);
		for (int i = 0; i < data2.length; i++) {
			comboBoxSeance.addItem(i+1);
		}
		comboBoxSeance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer NumeroSeance = (Integer) comboBoxSeance.getSelectedItem();
				NumeroSeance = idPlanning[NumeroSeance-1];
				
				
				frameLien = new JFrame();
		        frameLien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frameLien.setTitle("Lien ");
		        frameLien.setPreferredSize(new Dimension(600, 100));
		        frameLien.getContentPane().setBackground(new Color(240, 240, 240)); // Couleur de fond de la frameLien

		        // Création du panneau principal
		        JPanel mainPanel = new JPanel();
		        mainPanel.setLayout(new GridBagLayout());
		        mainPanel.setBackground(new Color(240, 240, 240)); // Couleur de fond du panneau
		        String lien = planningDAO.getLien(NumeroSeance);
		        // Création du label avec le lien
		        JLabel label = new JLabel(lien);
		        label.setFont(new Font("Arial", Font.BOLD, 13)); // Ajuster la taille de la police
		        label.setForeground(new Color(0, 102, 204)); // Couleur du texte

		        // Ajout d'un effet de survol pour simuler un lien
		        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        label.addMouseListener(new MouseAdapter() {
		        	@Override
		            public void mouseClicked(MouseEvent e) {
		                // Action à effectuer lors du clic sur le lien
		                try {
		                    Desktop.getDesktop().browse(new URI(lien));
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }

		            @Override
		            public void mouseEntered(MouseEvent e) {
		                label.setForeground(new Color(51, 153, 255)); // Couleur du texte au survol
		            }

		            @Override
		            public void mouseExited(MouseEvent e) {
		                label.setForeground(new Color(0, 102, 204)); // Couleur du texte par défaut
		            }
		        });

		        // Ajout du label au panneau principal
		        mainPanel.add(label);

		        // Ajout du panneau principal à la fenêtre
		        frameLien.getContentPane().add(mainPanel);

		        frameLien.pack();
		        frameLien.setLocationRelativeTo(null);
		        frameLien.setVisible(true);
				
			}
		});

		
		
		// Création d'un bouton pour récupérer la date sélectionnée

		JButton button = new JButton("Selectionner");
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
				JPanel panelBouton = new JPanel();
				panelBouton.setBounds(1174, 106, 239, 218);

				// Récupération de la date sélectionnée dans le JCalendar
				java.util.Date selectedDate = calendar.getDate();

				// Conversion de la date en format "jjmmaaaa"
				SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
				String formattedDate = dateFormat.format(selectedDate);

				// Conversion de la date en LocalDate
				LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyyyy"));




				//Creation du tableau de cours du jour selectionne



				int nbLigne = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).size();
				String[][] data2 = new String[nbLigne][5];
				Integer[] idPlanning = new Integer[nbLigne];

				try {
					for (int i = 0; i < nbLigne; i++) {
						Planning pln =planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i);
						idPlanning[i] = pln.getId();
						data2[i][0] = pln.getHeure()+ "";
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


				JTable tableau2 = new JTable(data2, columnNames2);
				
				
				// Création du combobox des seance du jour a selectionner

				JComboBox<Integer> comboBoxSeance = new JComboBox<Integer>();
				comboBoxSeance.setSize(50, 40);
				comboBoxSeance.setLocation(100, 170);
				for (int i = 0; i < data2.length; i++) {
					comboBoxSeance.addItem(i+1);
				}
				comboBoxSeance.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Integer NumeroSeance = (Integer) comboBoxSeance.getSelectedItem();
						NumeroSeance = idPlanning[NumeroSeance-1];



						frameLien = new JFrame();
				        frameLien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				        frameLien.setTitle("Lien ");
				        frameLien.setPreferredSize(new Dimension(600, 100));
				        frameLien.getContentPane().setBackground(new Color(240, 240, 240)); // Couleur de fond de la frameLien

				        // Création du panneau principal
				        JPanel mainPanel = new JPanel();
				        mainPanel.setLayout(new GridBagLayout());
				        mainPanel.setBackground(new Color(240, 240, 240)); // Couleur de fond du panneau
				        String lien = planningDAO.getLien(NumeroSeance);
				        // Création du label avec le lien
				        JLabel label = new JLabel(lien);
				        label.setFont(new Font("Arial", Font.BOLD, 13)); // Ajuster la taille de la police
				        label.setForeground(new Color(0, 102, 204)); // Couleur du texte

				        // Ajout d'un effet de survol pour simuler un lien
				        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				        label.addMouseListener(new MouseAdapter() {
				        	@Override
				            public void mouseClicked(MouseEvent e) {
				                // Action à effectuer lors du clic sur le lien
				                try {
				                    Desktop.getDesktop().browse(new URI(lien));
				                } catch (Exception ex) {
				                    ex.printStackTrace();
				                }
				            }

				            @Override
				            public void mouseEntered(MouseEvent e) {
				                label.setForeground(new Color(51, 153, 255)); // Couleur du texte au survol
				            }

				            @Override
				            public void mouseExited(MouseEvent e) {
				                label.setForeground(new Color(0, 102, 204)); // Couleur du texte par défaut
				            }
				        });

				        // Ajout du label au panneau principal
				        mainPanel.add(label);

				        // Ajout du panneau principal à la fenêtre
				        frameLien.getContentPane().add(mainPanel);

				        frameLien.pack();
				        frameLien.setLocationRelativeTo(null);
				        frameLien.setVisible(true);
				        
					}
				});
				
				panelBouton.setLayout(null);
				panelBouton.add(button);
				panelBouton.add(comboBoxSeance);
				panelBouton.add(retourBtn2);
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
				lblNewLabel.setBounds(120, 11, 144, 78);
				downContainer2.add(lblNewLabel);



				//to finish, we add the main container to the window
				framePlanning.setContentPane(base2);
				framePlanning.setLocationRelativeTo(null);
				framePlanning.setVisible(true);
			}
		});
		panelBouton.setLayout(null);
		panelBouton.add(button);
		panelBouton.add(comboBoxSeance);
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
		int nbLigne3 = etudiantDAO.getStatutAbs(SignInEtudiantGUI.id).size();
		// Creation du tableau
		String[][] data3 = new String[nbLigne3][4];
		String[] columnNames3 = {"Date:", "Cours:", "Heures d'absence:", "Statut:"};

		AbsenceDAO absenceDAO = new AbsenceDAO();
		try {
			for (int i = 0; i < nbLigne3; i++) {
				Absence absence = absenceDAO.getListFromIdEtudiant(SignInEtudiantGUI.id).get(i);

				data3[i][0] = absence.getDate().toString();
				data3[i][1] = coursDAO.getNomCours(absence.getIdCours());
				data3[i][2] = absence.getNbHeure()+"";
				data3[i][3] = absence.getStatut();

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JTable tableau3 = new JTable(data3, columnNames3);

		//Menu deroulant 

		JPanel panelBoutonJst = new JPanel();
		Integer[] listeAbs = new Integer[nbLigne3];
		for (int i = 0; i < nbLigne3; i++) {
			listeAbs[i] = i ;
		}

		JComboBox<Integer> comboBox = new JComboBox<>(listeAbs);

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedNumber = (Integer) comboBox.getSelectedItem();
			}
		});
		panelBoutonJst.add(comboBox);

		//Ajout bouton Deposer Justificatif


		JButton boutonJst = new JButton("Deposer Justificatif");
		boutonJst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {


				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(frameListeAbsences);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();

					String nomCours=(etudiantDAO.getNomCoursAbs(SignInEtudiantGUI.id).get(selectedNumber));
					int idCours= coursDAO.getIdCours(nomCours);
					Time heureDebut=etudiantDAO.getHeureDebut(SignInEtudiantGUI.id).get(selectedNumber);
					AbsenceDAO absenceDAO =new AbsenceDAO();
					absenceDAO.setStatut(idCours,heureDebut);
				}
			}
		});
		panelBoutonJst.add(boutonJst);


		JButton boutonAnticiper = new JButton("Anticiper absence");
		boutonAnticiper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				
				frameListeAbsences.setVisible(false);
				/**
				 * Creation de la fenetre d'anticipation de l'etudiant
				 */
				frameListeAbsenceAnticiper = new JFrame();
				frameListeAbsenceAnticiper.setTitle("Anticiper une absence");
				frameListeAbsenceAnticiper.setBounds(1500, 1500, 1500, 1000);
				frameListeAbsenceAnticiper.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frameListeAbsenceAnticiper.getContentPane().setLayout(new BoxLayout(frameListeAbsenceAnticiper.getContentPane(), BoxLayout.Y_AXIS));

				JPanel panelAbsent = new JPanel();
				panelAbsent.setLayout(null);
				//Creation du calendrier et reccuperation de la date selectionnee

				JCalendar calendar = new JCalendar();
				calendar.setBounds(0, 0, 1484, 742);
				
				panelAbsent.add(calendar);
				JLabel lblHeure = new JLabel("Heure de debut :");
				lblHeure.setBounds(417, 791, 127, 33);
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
				comboBoxHeure.setBounds(394, 835, 163, 22);
				for (int i = 0; i < listeHeures.size(); i++) {
					comboBoxHeure.addItem(listeHeures.get(i));
				}

				panelAbsent.add(comboBoxHeure);


				// Création du combobox des cours a selectionner

				JComboBox<String> comboBoxCours = new JComboBox<String>();
				comboBoxCours.setBounds(663, 835, 189, 22);

				for (int i = 0; i < etudiantDAO .getNomCours(SignInEtudiantGUI.id).size(); i++) {
					comboBoxCours.addItem(etudiantDAO.getNomCours(SignInEtudiantGUI.id).get(i));
				}

				panelAbsent.add(comboBoxCours);

				// Création du combobox des duree a selectionner


				List<Integer> duree = new ArrayList<>();

				for (Integer i = 1; i <= 4; i++) {
					duree.add(i );
				}
				JComboBox<Integer> comboBoxDuree = new JComboBox<Integer>();
				comboBoxDuree.setBounds(958, 835, 79, 22);
				for (int i = 0; i < duree.size(); i++) {
					comboBoxDuree.addItem(duree.get(i));
				}
				panelAbsent.add(comboBoxDuree);

				
				

				JButton btnValider = new JButton("Valider");
				btnValider.setBounds(753, 907, 95, 43);
				btnValider.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Ajouter l'abence a l'etudiant correspondant � l'id selectionne
						// Récupération de la date sélectionnée dans le JCalendar
				        java.util.Date selectedDate = calendar.getDate();

				        // Conversion de la date en format "jjmmaaaa"
				        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
				        String formattedDate = dateFormat.format(selectedDate);

				        // Conversion de la date en LocalDate
				        LocalDate date = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyyyy"));
				        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
				        
						Double heureDebut = (Double) comboBoxHeure.getSelectedItem();
						int idCours = (Integer) coursDAO.getIdCours((String) comboBoxCours.getSelectedItem());
						int nbHeure = (Integer) comboBoxDuree.getSelectedItem();  
						
						Absence absence = new Absence(SignInEtudiantGUI.id, idCours ,heureDebut, nbHeure,sqlDate,"En verification" );
						AbsenceDAO absenceDAO = new AbsenceDAO();
						absenceDAO.add(absence);         	  
					}
				});
				panelAbsent.add(btnValider);





				//Ajout bouton retour

				JButton retourBtn = new JButton("Retour");
				retourBtn.setBounds(1356, 895, 104, 35);
				retourBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						frameListeAbsenceAnticiper.dispose(); // ferme la fenêtre actuelle
						frameListeAbsences.setVisible(true);
					}
				});
				panelAbsent.add(retourBtn);
				
				
				frameListeAbsenceAnticiper.setContentPane(panelAbsent);
				
				JLabel lblNewLabel = new JLabel("Nom du cours :");
				lblNewLabel.setBounds(721, 793, 146, 29);
				panelAbsent.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Duree :");
				lblNewLabel_1.setBounds(969, 796, 67, 22);
				panelAbsent.add(lblNewLabel_1);
				frameListeAbsenceAnticiper.setLocationRelativeTo(null);
				frameListeAbsenceAnticiper.setVisible(true);


			}
		});
		panelBoutonJst.add(boutonAnticiper);





		//Ajout bouton retour

		JPanel panelBoutonRetour3 = new JPanel();
		JButton retourBtn3 = new JButton("Retour");
		retourBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListeAbsences.dispose(); // ferme la fenetre actuelle
				frame.setVisible(true);
			}
		});
		retourBtn3.setBounds(50, 40, 300, 200);
		panelBoutonRetour3.add(retourBtn3);


		JLabel lblNewLabel2 = new JLabel(absenceDAO.declencherPenalite(SignInEtudiantGUI.id));
		lblNewLabel2.setBounds(50, 80, 300, 200);
		lblNewLabel2.setForeground(Color.RED);
		downContainer3.add(lblNewLabel2);

		//distribution of the components in the 2 containers
		upContainer3.add(new JScrollPane(tableau3));
		upContainer3.add(panelBoutonJst);
		downContainer3.add(panelBoutonRetour3);

		//Ajout des containers au container principal
		base3.add(upContainer3);
		base3.add(downContainer3);

		//to finish, we add the main container to the window
		frameListeAbsences.setContentPane(base3);



		/**
		 * Creation du bouton pour acceder a la liste des cours de l'etudiant
		 */
		JPanel ListeCours = new JPanel();
		this.getContentPane().add(ListeCours);
		JButton ListeCour = new JButton("Acceder Liste Cours");
		ListeCour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameListeCours.setLocationRelativeTo(null);
				frameListeCours.setVisible(true);
			}
		});
		ListeCours.add(ListeCour);


		/**
		 * Creation du bouton pour acceder au planning de l'etudiant
		 */
		JPanel Planning = new JPanel();
		this.getContentPane().add(Planning);
		JButton PLANNING = new JButton("Acceder au Planning");
		PLANNING.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				framePlanning.setLocationRelativeTo(null);
				framePlanning.setVisible(true);
			}
		});
		Planning.add(PLANNING);

		/**
		 * Creation du bouton pour acceder à la liste d'absence de l'etudiant
		 */
		JPanel ListeAbsences = new JPanel();
		this.getContentPane().add(ListeAbsences);
		JButton LISTEABSENCES = new JButton("Acceder liste d'absences");
		LISTEABSENCES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameListeAbsences.setLocationRelativeTo(null);
				frameListeAbsences.setVisible(true);
			}
		});
		ListeAbsences.add(LISTEABSENCES);


		//Ajout bouton deconnexion

		JPanel panelBoutonDeco = new JPanel();
		JButton retourBtnDeco = new JButton("Deconnexion");
		retourBtnDeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose(); // ferme la fenêtre actuelle
				UserGUI window = new UserGUI();
				frame = new JFrame();
				window.frame.setLocationRelativeTo(null);
				window.frame.setVisible(true);
			}
		});
		panelBoutonDeco.add(retourBtnDeco);
		frame.getContentPane().add(panelBoutonDeco);


	}
}

