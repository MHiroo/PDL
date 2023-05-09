package gui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;

public class PremiereGUI extends JFrame {

    private JCalendar calendar;
    private JLabel dateLabel;

    public PremiereGUI() {
        super("Calendrier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Creation du calendrier
        calendar = new JCalendar();

        // Creation d'un bouton pour recuperer la date selectionnee
        JButton button = new JButton("Selectionner");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = calendar.getDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH) + 1; // les mois sont indexes à partir de 0
                int year = cal.get(Calendar.YEAR);
                dateLabel.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

        // Creation d'un label pour afficher la date selectionnee
        dateLabel = new JLabel("", SwingConstants.CENTER);

        // Ajout des composants à la fenêtre
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(calendar, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        panel.add(dateLabel, BorderLayout.NORTH);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PremiereGUI();
    }
}
    		
    