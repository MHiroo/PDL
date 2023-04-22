package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PremiereGUI extends JFrame implements ActionListener {

    private JButton button;

    public PremiereGUI() {
        super("Première GUI");

        button = new JButton("Afficher la deuxième GUI");
        button.addActionListener(this);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(button);

        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        DeuxiemeGUI fenetre2 = new DeuxiemeGUI();
        fenetre2.setVisible(true);
    }

    public static void main(String[] args) {
        new PremiereGUI();
    }
}

class DeuxiemeGUI extends JFrame {

    public DeuxiemeGUI() {
        super("Deuxième GUI");

        JLabel label = new JLabel("Ceci est la deuxième GUI !");

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);

        setContentPane(panel);
        pack();
    }
}
