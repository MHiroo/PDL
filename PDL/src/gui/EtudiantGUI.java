package gui;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JCalendar;

import dao.EtudiantDAO;

public class EtudiantGUI extends JFrame{

	JFrame frame;
	private JFrame frameListeCours;
	private JFrame framePlanning;
	private JFrame frameListeAbsences;



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
		JPanel downContainer2 = new JPanel();

		//each panel gets its own layout
		upContainer2.setLayout(new GridLayout(1, 1));
		downContainer2.setLayout(new GridLayout(1, 1));
		base2.setLayout(new GridLayout(2,1));
		
		//Ajout bouton retour

		JPanel panelBoutonRetour2 = new JPanel();
		JButton retourBtn2 = new JButton("Retour");
		retourBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				framePlanning.dispose(); // ferme la fenetre actuelle
				frame.setVisible(true);
			}
		});
		panelBoutonRetour2.add(retourBtn2);

		//Creation du calendrier
		JCalendar calendar = new JCalendar();
		java.util.Date utilDate = new java.util.Date();
		utilDate=calendar.getDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		
		//Creation de la liste de cours du jour selectionne
		
		//distribution of the components in the 2 containers
		upContainer2.add(calendar);
		downContainer2.add(panelBoutonRetour2);

		//Ajout des containers au container principal
		base2.add(upContainer2);
		base2.add(downContainer2);

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
				upContainer3.setLayout(new GridLayout(1, 1));
				downContainer3.setLayout(new GridLayout(1, 1));
				base3.setLayout(new GridLayout(2,1));



				// Creation du tableau

				
				String[][] data3 = new String[etudiantDAO.getStatutAbs(SignInEtudiantGUI.id).size()][5];
				try {
					for (int i = 0; i < etudiantDAO.getStatutAbs(SignInEtudiantGUI.id).size(); i++) {
						for (int j = 0; j < 5; j++) {
							   if (j == 0) {
								Date d= etudiantDAO.getDateAbs(SignInEtudiantGUI.id).get(i);
								data3[i][j] = d+"";
							}  if (j == 1) {
								data3[i][j] = etudiantDAO.getNomCoursAbs(SignInEtudiantGUI.id).get(i);
							}  if (j == 2) {
								Integer x = etudiantDAO.getHeureAbs(SignInEtudiantGUI.id).get(i);
								data3[i][j] = x+ "";
							}  if (j == 3) {
								data3[i][j] = etudiantDAO.getStatutAbs(SignInEtudiantGUI.id).get(i);
							} 
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String[] columnNames3 = {"Date:", "Cours:", "Heures d'absence:", "Statut:", "Deposer un justificatif:"};
				JTable tableau3 = new JTable(data3, columnNames3);





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
				downContainer3.add(panelBoutonRetour3);

				//Ajout des containers au container principal
				base3.add(upContainer3);
				base3.add(downContainer3);

				//to finish, we add the main container to the window
				frameListeAbsences.setContentPane(base3);



		/**
		 * Creation du bouton pour acceder à liste des cours de l'etudiant
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
		JButton LISTEABSENCES = new JButton("Acceder à la liste d'absences");
		LISTEABSENCES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameListeAbsences.setLocationRelativeTo(null);
				frameListeAbsences.setVisible(true);
			}
		});
		ListeAbsences.add(LISTEABSENCES);

	}




}

