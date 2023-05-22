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
import javax.swing.JTextField;
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
	 * Initialize the contents of the frameLien.
	 */
	private void initialize() {

		JFrame frameLien = new JFrame();
        frameLien.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLien.setTitle("Lien Frame");
        frameLien.setPreferredSize(new Dimension(400, 300));
        frameLien.getContentPane().setBackground(new Color(240, 240, 240)); // Couleur de fond de la frameLien

        // Création du panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Couleur de fond du panneau

        // Création du label avec le lien
        JLabel label = new JLabel(lien);
        label.setFont(new Font("Arial", Font.BOLD, 18)); // Ajuster la taille de la police
        label.setForeground(new Color(0, 102, 204)); // Couleur du texte

        // Ajout d'un effet de survol pour simuler un lien
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Action à effectuer lors du clic sur le lien
                JOptionPane.showMessageDialog(frameLien, "Lien cliqué !");
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
	
}
