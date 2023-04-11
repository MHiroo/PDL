import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Authentification extends JFrame implements ActionListener {

    private JLabel label1, label2;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton button;
    private Connection conn;
    
    public Authentification() {
        super("Fenêtre d'authentification");

        label1 = new JLabel("Nom d'utilisateur :");
        label2 = new JLabel("Mot de passe :");
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        button = new JButton("Connexion");
        button.addActionListener(this);

        JPanel panel1 = new JPanel(new GridLayout(2, 1));
        panel1.add(label1);
        panel1.add(label2);

        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        panel2.add(loginField);
        panel2.add(passwordField);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel3.add(button);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add(panel1, BorderLayout.WEST);
        panel4.add(panel2, BorderLayout.CENTER);
        panel4.add(panel3, BorderLayout.SOUTH);

        setContentPane(panel4);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String login = loginField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nom_de_la_base_de_donnees", login, password);
            JOptionPane.showMessageDialog(this, "Connexion réussie !");
            // faire ce que vous voulez avec la connexion ici...
            conn.close();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erreur : pilote JDBC introuvable !");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Authentification();
    }
}
