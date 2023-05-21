package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.sql.Time;

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

import dao.AbsenceDAO;

public class test extends JFrame {
	public test() {
	}
	

	
	private JFrame frameListeAbsences;
	private Integer selectedNumber;

				
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
	/**
	JComboBox<Integer> comboBox = new JComboBox<>(listeAbs);

	comboBox.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedNumber = (Integer) comboBox.getSelectedItem();
		}
	});
	panelBoutonJst.add(comboBox);
*/
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

	AbsenceDAO absenceDAO = new AbsenceDAO();
	
	JLabel lblNewLabel2 = new JLabel(absenceDAO.declencherPenalite(SignInEtudiantGUI.id));
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

	frameListeAbsences.setLocationRelativeTo(null);
	frameListeAbsences.setVisible(true);

}
