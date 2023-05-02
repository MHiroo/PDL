package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import dao.EtudiantDAO;

public class PremiereGUI extends JFrame  {

    private JButton button;
    EtudiantDAO etudiantDAO = new EtudiantDAO();
    public PremiereGUI() {
    	
    	
    	EtudiantDAO etudiantDAO = new EtudiantDAO();
    	String[][] data = new String[etudiantDAO.getNomCours(SignInEtudiantGUI.id).size()][4];

    	try {
    	    for (int i = 0; i < etudiantDAO.getNomCours(SignInEtudiantGUI.id).size(); i++) {
    	        for (int j = 0; j < 4; j++) {
    	            if (j==0) {
    	                data[i][j] = etudiantDAO.getNomCours(SignInEtudiantGUI.id).get(i);
    	            } else if (j==1) {
    	                data[i][j] = etudiantDAO.getMasseHoraire(SignInEtudiantGUI.id).get(i);
    	            } else if (j==2) {
    	                JPanel panel = new JPanel(new GridLayout(1, 3)); // créer un panel qui contient 3 colonnes
    	                panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // ajouter une bordure vide pour ajouter une marge
    	                JLabel label1 = new JLabel(etudiantDAO.getRepartition1(SignInEtudiantGUI.id).get(i)); // ajouter le 1er label
    	                label1.setForeground(Color.BLUE); // changer la couleur de texte
    	                panel.add(label1);
    	                JLabel label2 = new JLabel(etudiantDAO.getRepartition2(SignInEtudiantGUI.id).get(i)); // ajouter le 2ème label
    	                label2.setForeground(Color.RED); // changer la couleur de texte
    	                panel.add(label2);
    	                JLabel label3 = new JLabel(etudiantDAO.getRepartition3(SignInEtudiantGUI.id).get(i)); // ajouter le 3ème label
    	                label3.setForeground(Color.GREEN); // changer la couleur de texte
    	                panel.add(label3);
    	                data[i][j] = ""; // mettre une cellule vide
    	                tableau.add(panel); // ajouter le panel contenant les labels à la table
    	            } else if (j==3) {
    	                data[i][j] = etudiantDAO.getNomEnseignant(SignInEtudiantGUI.id).get(i);
    	            }
    	        }
    	    }
    	} catch (Exception e1) {
    	    e1.printStackTrace();
    	}

    	String[] columnNames = {"Nom du cours:", "Masse Horaire:", "Repartition:", "Enseignant:"};
    	JTable tableau = new JTable(data, columnNames);

    	// Modifier l'apparence des colonnes pour que les cellules soient alignées au centre
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    	centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    	tableau.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    	tableau.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    	tableau.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

    	// Ajouter la table dans un JScrollPane
    	JScrollPane scrollPane = new JScrollPane(tableau);

    	// Ajouter le JScrollPane et le bouton à la frame
    	JFrame frame = new JFrame("Tableau avec 3 colonnes");
    	frame.getContentPane().setLayout(new BorderLayout());
    	frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    	frame.getContentPane().add(button, BorderLayout.SOUTH);
    	frame.pack();
    	frame.setVisible(true);



}
//etudiantDAO.getNomCours().get(0)

    		
    