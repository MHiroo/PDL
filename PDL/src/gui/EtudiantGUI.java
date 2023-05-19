package gui;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;

public class EtudiantGUI extends JFrame{

	JFrame frame;
	private JFrame frameListeCours;
	private JFrame framePlanning;
	private JFrame frameListeAbsences;
	private Integer selectedNumber;


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
		

		try {
			for (int i = 0; i < nbLigne; i++) {
				data2[i][0] = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getHeure()+ "";
				data2[i][1] = coursDAO.getNomCours(planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getIdCours());
				data2[i][2] = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getSalle();
				data2[i][3] = enseignantDAO.getNomEnseignant(planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getIdEnseignant());
				data2[i][4] = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getDuree()+ "h";									
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] columnNames2 = {"H:", "Cours:", "Salle:", "Enseignant:", "Duree:"};

		DefaultTableModel model = new DefaultTableModel(data2, columnNames2);
		JTable tableau2 = new JTable(model);



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

				try {
					for (int i = 0; i < nbLigne; i++) {
						data2[i][0] = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getHeure()+ "";
						data2[i][1] = coursDAO.getNomCours(planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getIdCours());
						data2[i][2] = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getSalle();
						data2[i][3] = enseignantDAO.getNomEnseignant(planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getIdEnseignant());
						data2[i][4] = planningDAO.getPlanningJour(etudiantDAO.getIdGroupe(SignInEtudiantGUI.id), date).get(i).getDuree()+ "h";									
					}
				}
				catch (Exception e1) {
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
		downContainer3.setLayout(new GridLayout(1, 1));
		base3.setLayout(new GridLayout(2,1));

		//System.out.println(etudiantDAO.getStatutAbs(1).get(0));
		int nbLigne3 = etudiantDAO.getStatutAbs(SignInEtudiantGUI.id).size();
		// Creation du tableau
		String[][] data3 = new String[nbLigne3][4];
		String[] columnNames3 = {"Date:", "Cours:", "Heures d'absence:", "Statut:"};


		try {
			for (int i = 0; i < nbLigne3; i++) {

				Date d= etudiantDAO.getDateAbs(SignInEtudiantGUI.id).get(i);
				data3[i][0] = d+"";
				data3[i][1] = etudiantDAO.getNomCoursAbs(SignInEtudiantGUI.id).get(i);
				Integer x = etudiantDAO.getHeureAbs(SignInEtudiantGUI.id).get(i);
				data3[i][2] = x+ "";
				data3[i][3] = etudiantDAO.getStatutAbs(SignInEtudiantGUI.id).get(i);

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





		//Ajout bouton retour

		JPanel panelBoutonRetour3 = new JPanel();
		JButton retourBtn3 = new JButton("Retour");
		retourBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frameListeAbsences.dispose(); // ferme la fenetre actuelle
				frame.setVisible(true);
			}
		});
		panelBoutonRetour3.add(retourBtn3);



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

	}
}

