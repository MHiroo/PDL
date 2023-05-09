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

<<<<<<< HEAD
/*
2 :  * Pour modifier ce modèle, choisissez Outils | Modèles
3:  * et ouvrez le modèle dans l'éditeur.
4 :  */
5 :  package calendar.app ;
6 :  
7 :  importez java.util.Calendar ;
8 :  
9 :  /**
10:  *
11:  * @author greenxgene
12:  */
13 :  la classe publique  DatePickerExample étend javax.swing.JFrame {
14:  
15:      /**
16:       * Crée un nouveau formulaire DatePickerExample
17:       */
18:      public ExemplePickerDate() {
19 :          initComponents();
20 :      }
21:  
22:      /**
23 :       * Cette méthode est appelée depuis le constructeur pour initialiser le formulaire.
24 :      * AVERTISSEMENT : ne modifiez PAS ce code. Le contenu de cette méthode est toujours
25 :       * régénéré par l'éditeur de formulaires.
26:       */
27:      @SuppressWarnings( "décoché" )
28 :      // <editor-fold defaultstate="collapsed" desc="Code généré">
29 :      vide privé  initComponents() {
30:  
31 :          jPanel1 = nouveau javax.swing.JPanel();
32 :          outputtextbox = new javax.swing.JTextField();
33 :          mydatechooser = new com.toedter.calendar.JDateChooser();
34 :          jLabel1 = new javax.swing.JLabel();
35 :          jLabel2 = new javax.swing.JLabel();
36 :          jButton1 = nouveau javax.swing.JButton();
37:  
38 :         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE) ;
39 :          setTitle( "Exemple de sélecteur de date" );
40 :  
41 :          jLabel1.setText( "Choisir la date" );
42:  
43 :          jLabel2.setText( "Sortie" );
44:  
45 :          jButton1.setText( "Obtenir la date" );
46 :          jButton1.addActionListener( new java.awt.event.ActionListener() {
47:              public  void actionPerformed(java.awt.event.ActionEvent evt) {
48 :                 jButton1ActionPerformed(evt) ;
49 :              }
50 :          });
51 :  
52 :          javax.swing.GroupLayout jPanel1Layout = nouveau javax.swing.GroupLayout(jPanel1);
53 :         jPanel1.setLayout(jPanel1Layout) ;
54 :          jPanel1Layout.setHorizontalGroup(
55 :              jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
56 :              .addGroup(jPanel1Layout.createSequentialGroup()
57 :                  .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
58 :                      .addGroup(jPanel1Layout.createSequentialGroup()
59 :                          .addGap(147, 147, 147)
60 :                          .addComponent(jLabel1))
61 :                      .addGroup(jPanel1Layout.createSequentialGroup()
62 :                          .addGap(143, 143, 143)
63 :                          .addComponent(jButton1))
64 :                      .addGroup(jPanel1Layout.createSequentialGroup()
65 :                          .addGap(165, 165, 165)
66 :                          .addComponent(jLabel2))
67 :                      .addGroup(jPanel1Layout.createSequentialGroup()
68 :                          .addGap(123, 123, 123)
69 :                          .addComponent(mydatechooser, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
70 :                      .addGroup(jPanel1Layout.createSequentialGroup()
71 :                          .addGap(65, 65, 65)
72 :                          .addComponent(outputtextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
73 :                  .addContainerGap(83, Short.MAX_VALUE))
74 :          );
75 :         jPanel1Layout.setVerticalGroup(
76 :             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
77 :              .addGroup(jPanel1Layout.createSequentialGroup()
78 :                  .addContainerGap()
79 :                  .addComponent(jLabel1)
80 :                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
81 :                  .addComponent(mydatechooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
82 :                  .addGap(13, 13, 13)
83 :                  .addComponent(jButton1)
84 :                  .addGap(25, 25, 25)
85 :                  .addComponent(jLabel2)
86 :                  .addGap(18, 18, 18)
87 :                  .addComponent(outputtextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
88 :                  .addContainerGap(25, Short.MAX_VALUE))
89 :          );
90 :  
91 :          disposition javax.swing.GroupLayout = nouveau javax.swing.GroupLayout(getContentPane());
92 :          getContentPane().setLayout(mise en page) ;
93 :          layout.setHorizontalGroup(
94 :              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
95 :              .addGroup(layout.createSequentialGroup()
96 :                  .addContainerGap()
97 :                  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
98 :                  .addContainerGap())
99 :          );
100 :          layout.setVerticalGroup(
101 :              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
102 :              .addGroup(layout.createSequentialGroup()
103 :                  .addContainerGap()
104 :                  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
105 :                  .addContainerGap())
106 :          );
107 :  
108 :          paquet();
109 :      } // </editor-fold>
110 :  
111 :      vide privé  jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
112 :          Calendar cal = mydatechooser.getCalendar();
113 :          int datevar = cal.get(Calendar.DATE);
114 :          int monthvar = cal.get(Calendar.MONTH) ;
115 :          int yearvar = cal.get(Calendar.YEAR);
116:          outputtextbox.setText( "La date est " + datevar + ", le mois est " + monthvar + " et l'année est " + yearvar + "." );
117 :         
118 :      }
119 :  
120 :      /**
121:       * @param args les arguments de la ligne de commande
122 :       */
123:      public  static  void main(String args[]) {
124:          /*
125 :           * Définissez l'apparence de Nimbus
126 :           */
127 :          //<editor-fold defaultstate="collapsed" desc="Code de réglage de l'aspect et de la convivialité (facultatif)">
128:          /*
129:           * Si Nimbus (introduit dans Java SE 6) n'est pas disponible, restez avec le
130 :          * apparence par défaut. Pour plus de détails, voir
131 :           * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
132 :           */
133 :          essayez {
134 :              for (infos javax.swing.UIManager.LookAndFeelInfo : javax.swing.UIManager.getInstalledLookAndFeels()) {
135:                  si ( "Nimbus" .equals(info.getName())) {
136 :                      javax.swing.UIManager.setLookAndFeel(info.getClassName());
137 :                      pause ;
138 :                  }
139 :              }
140 :          } catch (ClassNotFoundException ex) {
141 :              java.util.logging.Logger.getLogger(DatePickerExample. class .getName()).log(java.util.logging.Level.SEVERE, null, ex);
142 :          } catch (InstantiationException ex) {
143 :              java.util.logging.Logger.getLogger(DatePickerExample. class .getName()).log(java.util.logging.Level.SEVERE, null, ex);
144 :          } catch (IllegalAccessException ex) {
145 :              java.util.logging.Logger.getLogger(DatePickerExample. class .getName()).log(java.util.logging.Level.SEVERE, null, ex);
146 :          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
147 :              java.util.logging.Logger.getLogger(DatePickerExample. class .getName()).log(java.util.logging.Level.SEVERE, null, ex);
148 :          }
149 :          //</editor-fold>
150 :  
151:          /*
152:           * Créer et afficher le formulaire
153 :           */
154 :          java.awt.EventQueue.invokeLater( new Runnable() {
155 :  
156:              public  void run() {
157 :                  nouveau DatePickerExample().setVisible(true) ;
158 :              }
159 :          });
160 :      }
161 :      // Déclaration des variables - ne pas modifier
162 :      javax.swing.JButton privé jButton1 ;
163 : javax.swing.JLabel      privé jLabel1 ;
164 : javax.swing.JLabel      privé jLabel2 ;
165 :      javax.swing.JPanel privé jPanel1 ;
166 :      privé com.toedter.calendar.JDateChooser mydatechooser ;
167 : zone de texte de sortie      privée javax.swing.JTextField ;
168 :      // Fin de la déclaration des variables
169 : }
=======
public class PremiereGUI extends JFrame {

    private JCalendar calendar;
    private JLabel dateLabel;

    public PremiereGUI() {
        super("Calendrier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // CrÃ©ation du calendrier
        calendar = new JCalendar();

        // CrÃ©ation d'un bouton pour rÃ©cupÃ©rer la date sÃ©lectionnÃ©e
        JButton button = new JButton("SÃ©lectionner");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = calendar.getDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH) + 1; // les mois sont indexÃ©s Ã  partir de 0
                int year = cal.get(Calendar.YEAR);
                dateLabel.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

        // CrÃ©ation d'un label pour afficher la date sÃ©lectionnÃ©e
        dateLabel = new JLabel("", SwingConstants.CENTER);

        // Ajout des composants Ã  la fenÃªtre
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
    		
    
>>>>>>> 7d340878833d0533e241472ed5b80dfd36377cb6
