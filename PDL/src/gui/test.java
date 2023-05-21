package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dao.AbsenceDAO;
import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.PlanningDAO;
import model.Absence;
import model.Planning;

public class test extends JFrame {
	
	JFrame frameListeAbsenceAnticiper;
	JFrame frameListeAbsences;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frameListeAbsenceAnticiper.setLocationRelativeTo(null);
					window.frameListeAbsenceAnticiper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//frameListeAbsences.setVisible(false);
				/**
				 * Creation de la fenetre d'anticipation de l'etudiant
				 */
				frameListeAbsenceAnticiper = new JFrame();
				//frameListeAbsenceAnticiper.setTitle("Anticiper une absence");
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
	
}
