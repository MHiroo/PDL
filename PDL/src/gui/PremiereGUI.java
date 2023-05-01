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
    	// création du bouton
        button = new JButton("Récupérer la liste des cours");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Connexion à la base de données
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ma_base_de_donnees", "mon_utilisateur", "mon_mot_de_passe");

                    // Requête SQL avec INNER JOIN et sélection du nom du cours
                    String sql = "SELECT Cours.nom_cours FROM Cours INNER JOIN Enseignant ON Cours.id_enseignant = Enseignant.id_enseignant";

                    // Exécution de la requête
                    Statement statement = conn.createStatement();
                    ResultSet result = statement.executeQuery(sql);

                    // Récupération des noms de cours
                    ArrayList<String> nomsCours = new ArrayList<>();
                    while (result.next()) {
                        String nomCours = result.getString("nom_cours");
                        nomsCours.add(nomCours);
                    }

                    // Affichage des noms de cours
                    System.out.println("Liste des noms de cours :");
                    for (String nomCours : nomsCours) {
                        System.out.println(nomCours);
                    }

                    // Fermeture de la connexion
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // ajout du bouton à la frame
        add(button);

        // configuration de la frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PremiereGUI();
    }



}
//etudiantDAO.getNomCours().get(0)

    		
    