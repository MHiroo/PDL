package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.GestionnaireDAO;
import model.Personne;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionnaireGUI {

	private JFrame modifierFrame;
	private JFrame supprimerFrame;
	private JFrame insererFrame;
	private JFrame gestionnaireFrame;
	private JFrame accueilFrame;
	private JFrame mainFrame;
	private JTextField gestionnaireChoiceIpt;
	private JLabel gestionnaireNameLbl;
	private JLabel gestionnaireTelLbl;
	private JLabel gestionnaireMailLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionnaireGUI window = new GestionnaireGUI();
					window.accueilFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionnaireGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * création de la fenêtre d'accueil
		 */
		accueilFrame = new JFrame();
		accueilFrame.setTitle("Accueil");
		accueilFrame.setBounds(100, 100, 800, 800);
		accueilFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accueilFrame.getContentPane().setLayout(new BoxLayout(accueilFrame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel choicePn0 = new JPanel();
		accueilFrame.getContentPane().add(choicePn0);

		/**
		 * Bouton sur accueil qui affiche la fenêtre gestionnaires
		 */
		JButton button0 = new JButton("fenêtre gestionnaire");
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionnaireFrame.setVisible(true);
			}
		});
		choicePn0.add(button0);
		/**
		 * création de la fenêtre gestionnaire
		 */
		gestionnaireFrame = new JFrame();
		gestionnaireFrame.setTitle("Fournisseurs");
		gestionnaireFrame.setBounds(100, 100, 800, 800);
		gestionnaireFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gestionnaireFrame.getContentPane().setLayout(new BoxLayout(gestionnaireFrame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel choicePn01 = new JPanel();
		gestionnaireFrame.getContentPane().add(choicePn01);

		/**
		 * Bouton sur gestionnaires qui affiche la fenêtre d'affichage des gestionnaires
		 */
		JButton button01 = new JButton("fenêtre Afficher");
		button01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(true);
			}
		});
		choicePn01.add(button01);

		/**
		 * Bouton sur gestionnaires qui affiche la fenêtre de modification des gestionnaires
		 */
		JButton button02 = new JButton("fenêtre modifier");
		button02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifierFrame.setVisible(true);
			}
		});
		choicePn01.add(button02);
		/**
		 * création de la fenêtre de modification des gestionnaires
		 */
		modifierFrame = new JFrame();
		modifierFrame.setTitle("Fournisseurs");
		modifierFrame.setBounds(100, 100, 800, 800);
		modifierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modifierFrame.getContentPane().setLayout(new BoxLayout(modifierFrame.getContentPane(), BoxLayout.Y_AXIS));

		/**
		 * Bouton sur gestionnaires qui affiche la fenêtre de suppression des gestionnaires
		 */
		JButton button03 = new JButton("fenêtre supprimer");
		button03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerFrame.setVisible(true);
			}
		});
		choicePn01.add(button03);
		/**
		 * Création de la fenêtre de suppression des gestionnaires
		 */
		supprimerFrame = new JFrame();
		supprimerFrame.setTitle("Fournisseurs");
		supprimerFrame.setBounds(100, 100, 800, 800);
		supprimerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		supprimerFrame.getContentPane().setLayout(new BoxLayout(supprimerFrame.getContentPane(), BoxLayout.Y_AXIS));

		/**
		 * Bouton sur gestionnaires qui affiche la fenêtre d'insertion des gestionnaires
		 */
		JButton button04 = new JButton("fenêtre insérer");
		button04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insererFrame.setVisible(true);
			}
		});
		choicePn01.add(button04);
		/**
		 * Création de la feêtre d'insertion des gestionnaires
		 */
		insererFrame = new JFrame();
		insererFrame.setTitle("Fournisseurs");
		insererFrame.setBounds(100, 100, 800, 800);
		insererFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insererFrame.getContentPane().setLayout(new BoxLayout(insererFrame.getContentPane(), BoxLayout.Y_AXIS));

		mainFrame = new JFrame();
		mainFrame.setTitle("Affichage des gestionnaires");
		mainFrame.setBounds(100, 300, 800, 150);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel choicePnl = new JPanel();
		mainFrame.getContentPane().add(choicePnl);

		JLabel gestionnairechoiceLbl = new JLabel("Référence du gestionnaire à afficher : ");
		choicePnl.add(gestionnairechoiceLbl);

		gestionnaireChoiceIpt = new JTextField();
		choicePnl.add(gestionnaireChoiceIpt);
		gestionnaireChoiceIpt.setColumns(10);

		JButton gestionnaireChoiceSearchBtn = new JButton("Rechercher");
		gestionnaireChoiceSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Attention, il faudra faire davantage de vérifications sur la valeur entrée par l'utilisateur
				if (gestionnaireChoiceIpt.getText().length() > 0) {
					int id = Integer.parseInt(gestionnaireChoiceIpt.getText());
					displayGestionnaire(id);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous devez entrer une référence de gestionnaire", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		choicePnl.add(gestionnaireChoiceSearchBtn);

		JPanel gestionnaireDisplayPnl = new JPanel();
		mainFrame.getContentPane().add(gestionnaireDisplayPnl);

		JLabel gestionnaireSelectedLbl = new JLabel("Fournisseur sélectionné : ");
		gestionnaireDisplayPnl.add(gestionnaireSelectedLbl);

		gestionnaireNameLbl = new JLabel("");
		gestionnaireDisplayPnl.add(gestionnaireNameLbl);

		gestionnaireTelLbl = new JLabel("");
		gestionnaireDisplayPnl.add(gestionnaireTelLbl);

		gestionnaireMailLbl = new JLabel("");
		gestionnaireDisplayPnl.add(gestionnaireMailLbl);
	}

	public void displayGestionnaire(int id) {
		// On récupère le gestionnaire en BDD
		GestionnaireDAO suppDao = new GestionnaireDAO();
		Personne supp = suppDao.get(id);

		if (supp != null) {
			// On met à jour les libellés représentant le gestionnaire
			gestionnaireNameLbl.setText(supp.getName() + " -");
			gestionnaireTelLbl.setText(supp.getTel() + " -");
			gestionnaireMailLbl.setText(supp.getEmail());
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la récupération du gestionnaire", "Dialog",
					JOptionPane.ERROR_MESSAGE);
			gestionnaireNameLbl.setText("");
			gestionnaireTelLbl.setText("");
			gestionnaireMailLbl.setText("");
		}
	}

}