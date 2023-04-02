package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.SupplierDAO;
import model.Supplier;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupplierGUI {

	private JFrame modifierFrame;
	private JFrame supprimerFrame;
	private JFrame insererFrame;
	private JFrame fournisseurFrame;
	private JFrame accueilFrame;
	private JFrame mainFrame;
	private JTextField supplierChoiceIpt;
	private JLabel supplierNameLbl;
	private JLabel supplierAddressLbl;
	private JLabel supplierMailLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierGUI window = new SupplierGUI();
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
	public SupplierGUI() {
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
		 * Bouton sur accueil qui affiche la fenêtre fournisseurs
		 */
		JButton button0 = new JButton("fenêtre fournisseur");
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fournisseurFrame.setVisible(true);
			}
		});
		choicePn0.add(button0);
		/**
		 * création de la fenêtre fournisseur
		 */
		fournisseurFrame = new JFrame();
		fournisseurFrame.setTitle("Fournisseurs");
		fournisseurFrame.setBounds(100, 100, 800, 800);
		fournisseurFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fournisseurFrame.getContentPane().setLayout(new BoxLayout(fournisseurFrame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel choicePn01 = new JPanel();
		fournisseurFrame.getContentPane().add(choicePn01);

		/**
		 * Bouton sur fournisseurs qui affiche la fenêtre d'affichage des fournisseurs
		 */
		JButton button01 = new JButton("fenêtre Afficher");
		button01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(true);
			}
		});
		choicePn01.add(button01);

		/**
		 * Bouton sur fournisseurs qui affiche la fenêtre de modification des fournisseurs
		 */
		JButton button02 = new JButton("fenêtre modifier");
		button02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifierFrame.setVisible(true);
			}
		});
		choicePn01.add(button02);
		/**
		 * création de la fenêtre de modification des fournisseurs
		 */
		modifierFrame = new JFrame();
		modifierFrame.setTitle("Fournisseurs");
		modifierFrame.setBounds(100, 100, 800, 800);
		modifierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modifierFrame.getContentPane().setLayout(new BoxLayout(modifierFrame.getContentPane(), BoxLayout.Y_AXIS));

		/**
		 * Bouton sur fournisseurs qui affiche la fenêtre de suppression des fournisseurs
		 */
		JButton button03 = new JButton("fenêtre supprimer");
		button03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerFrame.setVisible(true);
			}
		});
		choicePn01.add(button03);
		/**
		 * Création de la fenêtre de suppression des fournisseurs
		 */
		supprimerFrame = new JFrame();
		supprimerFrame.setTitle("Fournisseurs");
		supprimerFrame.setBounds(100, 100, 800, 800);
		supprimerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		supprimerFrame.getContentPane().setLayout(new BoxLayout(supprimerFrame.getContentPane(), BoxLayout.Y_AXIS));

		/**
		 * Bouton sur fournisseurs qui affiche la fenêtre d'insertion des fournisseurs
		 */
		JButton button04 = new JButton("fenêtre insérer");
		button04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insererFrame.setVisible(true);
			}
		});
		choicePn01.add(button04);
		/**
		 * Création de la feêtre d'insertion des fournisseurs
		 */
		insererFrame = new JFrame();
		insererFrame.setTitle("Fournisseurs");
		insererFrame.setBounds(100, 100, 800, 800);
		insererFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insererFrame.getContentPane().setLayout(new BoxLayout(insererFrame.getContentPane(), BoxLayout.Y_AXIS));

		mainFrame = new JFrame();
		mainFrame.setTitle("Affichage des fournisseurs");
		mainFrame.setBounds(100, 300, 800, 150);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel choicePnl = new JPanel();
		mainFrame.getContentPane().add(choicePnl);

		JLabel supplierchoiceLbl = new JLabel("Référence du fournisseur à afficher : ");
		choicePnl.add(supplierchoiceLbl);

		supplierChoiceIpt = new JTextField();
		choicePnl.add(supplierChoiceIpt);
		supplierChoiceIpt.setColumns(10);

		JButton supplierChoiceSearchBtn = new JButton("Rechercher");
		supplierChoiceSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Attention, il faudra faire davantage de vérifications sur la valeur entrée par l'utilisateur
				if (supplierChoiceIpt.getText().length() > 0) {
					int id = Integer.parseInt(supplierChoiceIpt.getText());
					displaySupplier(id);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous devez entrer une référence de fournisseur", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		choicePnl.add(supplierChoiceSearchBtn);

		JPanel supplierDisplayPnl = new JPanel();
		mainFrame.getContentPane().add(supplierDisplayPnl);

		JLabel supplierSelectedLbl = new JLabel("Fournisseur sélectionné : ");
		supplierDisplayPnl.add(supplierSelectedLbl);

		supplierNameLbl = new JLabel("");
		supplierDisplayPnl.add(supplierNameLbl);

		supplierAddressLbl = new JLabel("");
		supplierDisplayPnl.add(supplierAddressLbl);

		supplierMailLbl = new JLabel("");
		supplierDisplayPnl.add(supplierMailLbl);
	}

	public void displaySupplier(int id) {
		// On récupère le fournisseur en BDD
		SupplierDAO suppDao = new SupplierDAO();
		Supplier supp = suppDao.get(id);

		if (supp != null) {
			// On met à jour les libellés représentant le fournisseur
			supplierNameLbl.setText(supp.getName() + " -");
			supplierAddressLbl.setText(supp.getAddress() + " -");
			supplierMailLbl.setText(supp.getEmail());
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la récupération du fournisseur", "Dialog",
					JOptionPane.ERROR_MESSAGE);
			supplierNameLbl.setText("");
			supplierAddressLbl.setText("");
			supplierMailLbl.setText("");
		}
	}

}