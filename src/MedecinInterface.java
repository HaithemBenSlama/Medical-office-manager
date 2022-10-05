import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Calendar;

public class MedecinInterface extends JFrame implements ActionListener,KeyListener,ItemListener,Runnable{
		private JPanel CenterPane;
		private CardLayout cardLayout;
		private JButton btnConsulterHistorique;
		private JButton btnImprimerOrdonnance;
		private JButton btnRendezVousDeJour;
		private JButton btnDeconnexion;
		private JLabel NomMedecin,Time,Date ;
		private JTable View_RendezVous;
		private JButton [] btndashAffichePlus;
		private JTable View_Historique_Consultation;
		private JTextField filtrePatientListe;
		private JTable View_Liste_Patient;
		private JTextField filtreImprimerOrdonnance;
		private JTable View_Ordonnance;
		private JTextField numPatientAjouterConsultation;
		private JTextField filtreMedicamentAjouterConsultation;
		private JTable View_Medicament;
		private JTable View_Medicament_Added;
		private JPanel p12_1;
		private JButton btnAjouterConsultation,btnImprimerOrdonnanceConfirm,btnRetourOrdonnance; 
		private JButton btnAbscent,btnRetourHistoriqueConfirm,btnAfficherOrdonnaceConfirm,btnAfficherHistoriqueConfirm;
		private JLabel prenomPatientConsulter,nomPatientConsulter,datePatientConsulter,adressePatientConsultation,poidsPatientConsulter,cinPatientConsulter,sexePatientConsulter,telPatientConsultation;
		private JComboBox comboTypeMaladie ;
		private JTextArea remarqueAjouterConsultation;
		private JSpinner nbFoisParJour,nbPosologie,nbPendant;
		private JCheckBox checkTraitement;
		private JComboBox comboJourSemaineMois;
		private JTextArea commentaireAjouterConsultation;
		private JComboBox comboFormeMedicament;
		private JComboBox comboDosageMedicament;
		private JButton btnAddMediament, btnDeleteMedicament;
		private JButton btnConfirmerAjouterConsultation,btnCancelAjouterConsultation;
		private Docteur docteur;
		private JLabel nomAjouterConsultation,prenomAjouterConsultation,dateAjouterConsultation,poidAjouterConsultation;
		
		private Cabinet cabinet;
		private Thread thread;
		public MedecinInterface(Docteur docteur) {
			super("Espace Medecin");
			cabinet = new Cabinet();
			this.docteur = docteur;
			cabinet.setResponsable(docteur);
			thread = new Thread(this);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setPreferredSize(new Dimension(1200,700));
			setVisible(true);
			pack();
			setLocationRelativeTo(null);
			
			JPanel pTop = new JPanel();
			pTop.setBackground(new Color(45, 130, 181));
			pTop.setPreferredSize(new Dimension(1000, 70));
			getContentPane().add(pTop, BorderLayout.NORTH);
			pTop.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel title = new JLabel("Gestion du Cabinet");
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setForeground(Color.WHITE);
			title.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			pTop.add(title);
			
			JPanel pLeft = new JPanel();
			pLeft.setBackground(Color.WHITE);
			pLeft.setPreferredSize(new Dimension(250, 700));
			getContentPane().add(pLeft, BorderLayout.WEST);
			pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
			
			JPanel p1 = new JPanel();
			p1.setMaximumSize(getSize());
			pLeft.add(p1);
			p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
			
			JPanel p1_1 = new JPanel();
			p1_1.setBackground(new Color(137,204,247));
			p1.add(p1_1);
			
			JLabel Medecin = new JLabel("     Espace M\u00E9decin   ");
			p1_1.add(Medecin);
			Medecin.setFont(new Font("Oswald", Font.PLAIN, 15));
			Medecin.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/doctor.png")));
			
			JPanel p1_2 = new JPanel();
			p1_2.setBackground(new Color(137,204,247));
			p1.add(p1_2);
			p1_2.setLayout(new GridLayout(0, 1, 0, 0));
			
			NomMedecin = new JLabel();
			p1_2.add(NomMedecin);
			NomMedecin.setFont(new Font("Anonymous Pro", Font.PLAIN, 16));
			NomMedecin.setHorizontalAlignment(SwingConstants.CENTER);
			NomMedecin.setText(docteur.getPrenom()+" " + docteur.getNom());
			
			JPanel p1_3 = new JPanel();
			p1_3.setBackground(new Color(137,204,247));
			p1.add(p1_3);
			
			Time = new JLabel();
			Time.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			p1_3.add(Time);
			Time.setHorizontalAlignment(SwingConstants.CENTER);
			
			JPanel p1_4 = new JPanel();
			p1_4.setBackground(new Color(137,204,247));
			p1.add(p1_4);
			
			Date = new JLabel(getCurrentDate());
			Date.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			p1_4.add(Date);
			Date.setHorizontalAlignment(SwingConstants.CENTER);
			
			JPanel p2 = new JPanel();
			p2.setBackground(new Color(137,204,247));
			pLeft.add(p2);
			p2.setLayout(new GridLayout(0, 1, 0, 0));
			
			JPanel p2_1 = new JPanel();
			p2_1.setBackground(new Color(137,204,247));
			p2.add(p2_1);
			
			btnRendezVousDeJour = new JButton("Rendez-vous de Jour");
			p2.add(btnRendezVousDeJour);
			btnRendezVousDeJour.addActionListener(this);
			
			JPanel p2_2 = new JPanel();
			p2_2.setBackground(new Color(137,204,247));
			p2.add(p2_2);
			
			btnConsulterHistorique = new JButton("Consulter Historique Patient");
			p2.add(btnConsulterHistorique);
			btnConsulterHistorique.addActionListener(this);
			
			JPanel p2_3 = new JPanel();
			p2_3.setBackground(new Color(137,204,247));
			p2.add(p2_3);
			
			btnImprimerOrdonnance = new JButton("Imprimer Ordonnance");
			btnImprimerOrdonnance.addActionListener(this);
			p2.add(btnImprimerOrdonnance);
			
			JPanel p2_4 = new JPanel();
			p2_4.setBackground(new Color(137,204,247));
			p2.add(p2_4);
			
			btnDeconnexion = new JButton("D\u00E9connexion");
			p2.add(btnDeconnexion);
			
			cardLayout=new CardLayout(0,0);
			CenterPane = new JPanel();
			getContentPane().add(CenterPane, BorderLayout.CENTER);
			CenterPane.setLayout(cardLayout);
			
			JPanel Bienvenu = new JPanel();
			Bienvenu.setBackground(new Color(192,233,255));
			CenterPane.add(Bienvenu, "Bienvenu");
			Bienvenu.setLayout(new GridLayout(0, 1, 0, 0));
			cardLayout.show(CenterPane,"Bienvenu");
			
			JLabel l1 = new JLabel("Bienvenue");
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			l1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
			Bienvenu.add(l1);
			
			JPanel RendezVousPane = new JPanel();
			RendezVousPane.setBackground(new Color(83,166,216));
			CenterPane.add(RendezVousPane, "RendezVousPane");
			RendezVousPane.setLayout(new BoxLayout(RendezVousPane, BoxLayout.Y_AXIS));
			
			JPanel p3 = new JPanel();
			RendezVousPane.add(p3);
			p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
			
			JPanel p3_1 = new JPanel();
			p3_1.setBackground(new Color(192,233,255));
			p3.add(p3_1);
			p3_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l2 = new JLabel("Rendez-Vous de Jour");
			l2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 42));
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			p3_1.add(l2);
			
			JScrollPane scrollPane1 = new JScrollPane();
			p3.add(scrollPane1);
			
			btndashAffichePlus=new JButton[9];
			for(int i=0;i<9;i++)
				btndashAffichePlus[i]=new JButton("Afficher plus...");
			View_RendezVous = new JTable();
			View_RendezVous.setRowHeight(45);
			View_RendezVous.setModel(new DefaultTableModel(
				new Object[][] {
					{"8:00", null, null, null},
					{"9:00", null, null, null},
					{"10:00", null, null, null},
					{"11:00", null, null, null},
					{"12:00", null, null, null},
					{"14:00", null, null, null},
					{"15:00", null, null, null},
					{"16:00", null, null, null},
					{"17:00", null, null, null},
				},
				new String[] {
					"Heure", "CIN", "Nom", "Prenom"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					true, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			View_RendezVous.getColumnModel().getColumn(0).setResizable(false);
			View_RendezVous.getColumnModel().getColumn(1).setResizable(false);
			View_RendezVous.getColumnModel().getColumn(2).setResizable(false);
			View_RendezVous.getColumnModel().getColumn(3).setResizable(false);
			scrollPane1.setViewportView(View_RendezVous);
			
			JPanel p3_2 = new JPanel();
			p3_2.setBackground(new Color(192,233,255));
			p3.add(p3_2);
			
			btnAbscent= new JButton("Marquer Absent");
			btnAbscent.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/close.png")));
			p3_2.add(btnAbscent);
			
			btnAjouterConsultation= new JButton("Ajouter Consultation");
			btnAjouterConsultation.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/add.png")));
			p3_2.add(btnAjouterConsultation);
			btnAjouterConsultation.addActionListener(this);
			
			JPanel ConsulterHistoriquePane = new JPanel();
			ConsulterHistoriquePane.setBackground(new Color(192,233,255));
			CenterPane.add(ConsulterHistoriquePane, "ConsulterHistoriquePane");
			ConsulterHistoriquePane.setLayout(new BoxLayout(ConsulterHistoriquePane, BoxLayout.Y_AXIS));
			
			JPanel p4 = new JPanel();
			p4.setBackground(new Color(192,233,255));
			ConsulterHistoriquePane.add(p4);
			p4.setMaximumSize(new Dimension(9999,100));
			p4.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l3 = new JLabel("Consulter Historique Patient");
			l3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			l3.setHorizontalAlignment(SwingConstants.CENTER);
			p4.add(l3);
			
			JPanel p5 = new JPanel();
			ConsulterHistoriquePane.add(p5);
			p5.setLayout(new GridLayout(0, 1, 0, 0));
			
			JSplitPane splitPane = new JSplitPane();
			p5.add(splitPane);
			
			JPanel p5_1 = new JPanel();
			p5_1.setMinimumSize(new Dimension(300, 200));
			splitPane.setLeftComponent(p5_1);
			p5_1.setLayout(new BoxLayout(p5_1, BoxLayout.Y_AXIS));
			
			JPanel p5_1_1 = new JPanel();
			p5_1_1.setBackground(new Color(192,233,255));
			p5_1_1.setMaximumSize(new Dimension(9999,70));
			p5_1.add(p5_1_1);
			p5_1_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l4 = new JLabel("Information Patient");
			l4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
			l4.setHorizontalAlignment(SwingConstants.CENTER);
			p5_1_1.add(l4);
			
			JPanel p5_1_2 = new JPanel();
			p5_1_2.setBackground(new Color(192,233,255));
			p5_1_2.setMaximumSize(new Dimension(9999,70));
			p5_1.add(p5_1_2);
			p5_1_2.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l5 = new JLabel("\r\n");
			l5.setHorizontalAlignment(SwingConstants.CENTER);
			l5.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/patient.png")));
			p5_1_2.add(l5);
			
			JPanel p5_1_3 = new JPanel();
			p5_1.add(p5_1_3);
			p5_1_3.setLayout(new GridLayout(0, 2, 0, 0));
			
			JPanel p5_1_3_1 = new JPanel();
			p5_1_3_1.setBackground(new Color(192,233,255));
			p5_1_3.add(p5_1_3_1);
			p5_1_3_1.setLayout(new GridLayout(8, 0, 0, 0));
			
			JPanel p5_1_3_1_1 = new JPanel();
			p5_1_3_1_1.setBackground(new Color(192,233,255));
			p5_1_3_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_1);
			p5_1_3_1_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l6 = new JLabel("Nom:");
			p5_1_3_1_1.add(l6);
			
			JPanel p5_1_3_1_2 = new JPanel();
			p5_1_3_1_2.setBackground(new Color(192,233,255));
			p5_1_3_1_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_2);
			p5_1_3_1_2.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l7 = new JLabel("Prenom:");
			p5_1_3_1_2.add(l7);
			
			JPanel p5_1_3_1_3 = new JPanel();
			p5_1_3_1_3.setBackground(new Color(192,233,255));
			p5_1_3_1_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_3);
			p5_1_3_1_3.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l8 = new JLabel("Date de Naissance:");
			p5_1_3_1_3.add(l8);
			
			JPanel p5_1_3_1_4 = new JPanel();
			p5_1_3_1_4.setBackground(new Color(192,233,255));
			p5_1_3_1_4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_4);
			p5_1_3_1_4.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l9 = new JLabel("Poids:");
			p5_1_3_1_4.add(l9);
			
			JPanel p5_1_3_1_5 = new JPanel();
			p5_1_3_1_5.setBackground(new Color(192,233,255));
			p5_1_3_1_5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_5);
			p5_1_3_1_5.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l10 = new JLabel("CIN:");
			p5_1_3_1_5.add(l10);
			
			JPanel p5_1_3_1_6 = new JPanel();
			p5_1_3_1_6.setBackground(new Color(192,233,255));
			p5_1_3_1_6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_6);
			p5_1_3_1_6.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l11 = new JLabel("Sexe:");
			p5_1_3_1_6.add(l11);
			
			JPanel p5_1_3_1_7 = new JPanel();
			p5_1_3_1_7.setBackground(new Color(192,233,255));
			p5_1_3_1_7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_7);
			p5_1_3_1_7.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l12 = new JLabel("Num T\u00E9l:");
			p5_1_3_1_7.add(l12);
			
			JPanel p5_1_3_1_8 = new JPanel();
			p5_1_3_1_8.setBackground(new Color(192,233,255));
			p5_1_3_1_8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			p5_1_3_1.add(p5_1_3_1_8);
			p5_1_3_1_8.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l13 = new JLabel("Adresse:");
			p5_1_3_1_8.add(l13);
			
			JPanel p5_1_3_2 = new JPanel();
			p5_1_3_2.setBackground(new Color(192,233,255));
			p5_1_3.add(p5_1_3_2);
			p5_1_3_2.setLayout(new GridLayout(0, 1, 0, 0));
			
			JPanel p5_1_3_2_1 = new JPanel();
			p5_1_3_2_1.setBackground(new Color(192,233,255));
			p5_1_3_2_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_1);
			p5_1_3_2_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			nomPatientConsulter = new JLabel("Ben Slama");
			p5_1_3_2_1.add(nomPatientConsulter);
			
			JPanel p5_1_3_2_2 = new JPanel();
			p5_1_3_2_2.setBackground(new Color(192,233,255));
			p5_1_3_2_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_2);
			p5_1_3_2_2.setLayout(new GridLayout(0, 1, 0, 0));
			
			prenomPatientConsulter= new JLabel("Haithem");
			p5_1_3_2_2.add(prenomPatientConsulter);
			
			JPanel p5_1_3_2_3 = new JPanel();
			p5_1_3_2_3.setBackground(new Color(192,233,255));
			p5_1_3_2_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_3);
			p5_1_3_2_3.setLayout(new GridLayout(0, 1, 0, 0));
			
			datePatientConsulter = new JLabel("14/04/2001");
			p5_1_3_2_3.add(datePatientConsulter);
			
			JPanel p5_1_3_2_4 = new JPanel();
			p5_1_3_2_4.setBackground(new Color(192,233,255));
			p5_1_3_2_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_4);
			p5_1_3_2_4.setLayout(new GridLayout(0, 1, 0, 0));
			
			poidsPatientConsulter = new JLabel("70.0 KG");
			p5_1_3_2_4.add(poidsPatientConsulter);
			
			JPanel p5_1_3_2_5 = new JPanel();
			p5_1_3_2_5.setBackground(new Color(192,233,255));
			p5_1_3_2_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_5);
			p5_1_3_2_5.setLayout(new GridLayout(0, 1, 0, 0));
			
			cinPatientConsulter = new JLabel("00000000");
			p5_1_3_2_5.add(cinPatientConsulter);
			
			JPanel p5_1_3_2_6 = new JPanel();
			p5_1_3_2_6.setBackground(new Color(192,233,255));
			p5_1_3_2_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_6);
			p5_1_3_2_6.setLayout(new GridLayout(0, 1, 0, 0));
			
			sexePatientConsulter = new JLabel("Homme");
			p5_1_3_2_6.add(sexePatientConsulter);
			
			JPanel p5_1_3_2_7 = new JPanel();
			p5_1_3_2_7.setBackground(new Color(192,233,255));
			p5_1_3_2_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_7);
			p5_1_3_2_7.setLayout(new GridLayout(0, 1, 0, 0));
			
			telPatientConsultation = new JLabel("27660834");
			p5_1_3_2_7.add(telPatientConsultation);
			
			JPanel p5_1_3_2_8 = new JPanel();
			p5_1_3_2_8.setBackground(new Color(192,233,255));
			p5_1_3_2_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p5_1_3_2.add(p5_1_3_2_8);
			p5_1_3_2_8.setLayout(new GridLayout(0, 1, 0, 0));
			
			adressePatientConsultation = new JLabel("Monastir");
			p5_1_3_2_8.add(adressePatientConsultation);
			
			JPanel p5_2 = new JPanel();
			splitPane.setRightComponent(p5_2);
			p5_2.setLayout(new BoxLayout(p5_2, BoxLayout.Y_AXIS));
			
			JPanel p5_2_1 = new JPanel();
			p5_2_1.setBackground(new Color(192,233,255));
			p5_2.add(p5_2_1);
			p5_2_1.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l14 = new JLabel("Historique Consultation");
			l14.setHorizontalAlignment(SwingConstants.CENTER);
			
			l14.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 26));
			p5_2_1.add(l14);
			
			JScrollPane scrollPane2 = new JScrollPane();
			p5_2.add(scrollPane2);
			
			View_Historique_Consultation = new JTable();
			View_Historique_Consultation.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"CIN", "Date", "Heure", "Status", "N\u00B0 Ordonnace", "Remarque"
				}
			));
			View_Historique_Consultation.setRowHeight(25);
			scrollPane2.setViewportView(View_Historique_Consultation);
			
			JPanel p5_2_2 = new JPanel();
			p5_2_2.setBackground(new Color(192,233,255));
			p5_2.add(p5_2_2);
			
			btnRetourHistoriqueConfirm = new JButton("Retour");
			btnRetourHistoriqueConfirm.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/back.png")));
			p5_2_2.add(btnRetourHistoriqueConfirm);
			btnRetourHistoriqueConfirm.addActionListener(this);
			
			btnAfficherOrdonnaceConfirm = new JButton("Afficher Ordonnance");
			btnAfficherOrdonnaceConfirm.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/treatment.png")));
			p5_2_2.add(btnAfficherOrdonnaceConfirm);
			
			String[] heure={"8:00", "9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};
			
			JPanel ListePatientPane = new JPanel();
			CenterPane.add(ListePatientPane, "ListePatientPane");
			ListePatientPane.setLayout(new BoxLayout(ListePatientPane, BoxLayout.Y_AXIS));
			
			JPanel p6 = new JPanel();
			p6.setBackground(new Color(192,233,255));
			p6.setMaximumSize(new Dimension (9999,70));
			p6.setPreferredSize(new Dimension (9999,70));
			ListePatientPane.add(p6);
			p6.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l15 = new JLabel("Liste des Patients");
			l15.setHorizontalAlignment(SwingConstants.CENTER);
			l15.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
			p6.add(l15);
			
			JPanel p7 = new JPanel();
			p7.setBackground(new Color(192,233,255));
			ListePatientPane.add(p7);
			p7.setLayout(new BoxLayout(p7, BoxLayout.Y_AXIS));
			
			JPanel p7_1 = new JPanel();
			p7_1.setBackground(new Color(192,233,255));
			p7.add(p7_1);
			
			JLabel l16 = new JLabel("Filtre");
			p7_1.add(l16);
			
			filtrePatientListe = new JTextField();
			p7_1.add(filtrePatientListe);
			filtrePatientListe.setColumns(15);
			
			JButton btnFiltrePatient = new JButton("");
			btnFiltrePatient.setEnabled(false);
			btnFiltrePatient.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/filtre.png")));
			p7_1.add(btnFiltrePatient);
			
			JScrollPane scrollPane3 = new JScrollPane();
			p7.add(scrollPane3);
			
			View_Liste_Patient = new JTable();
			View_Liste_Patient.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"CIN", "Nom", "Prenom", "Date de naissance", "Email", "Tel", "Sexe", "Poids", "Adresse"
				}
			));
			scrollPane3.setViewportView(View_Liste_Patient);
			
			JPanel p7_2 = new JPanel();
			p7_2.setBackground(new Color(192,233,255));
			p7.add(p7_2);
			
			btnAfficherHistoriqueConfirm = new JButton("Afficher l'historique des Consultations");
			btnAfficherHistoriqueConfirm.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/search48.png")));
			p7_2.add(btnAfficherHistoriqueConfirm);
			btnAfficherHistoriqueConfirm.addActionListener(this);
			
			JPanel ImprimerOrdonnancePane = new JPanel();
			ImprimerOrdonnancePane.setBackground(new Color(192,233,255));
			CenterPane.add(ImprimerOrdonnancePane, "ImprimerOrdonnancePane");
			ImprimerOrdonnancePane.setLayout(new BoxLayout(ImprimerOrdonnancePane, BoxLayout.Y_AXIS));
			
			JPanel p8 = new JPanel();
			p8.setBackground(new Color(192,233,255));
			ImprimerOrdonnancePane.add(p8);
			p8.setMinimumSize(new Dimension(12,70));
			p8.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l17 = new JLabel("Imprimer Ordonnance");
			l17.setHorizontalAlignment(SwingConstants.CENTER);
			l17.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			p8.add(l17);
			
			JPanel p9 = new JPanel();
			ImprimerOrdonnancePane.add(p9);
			p9.setLayout(new BoxLayout(p9, BoxLayout.Y_AXIS));
			
			JPanel p9_1 = new JPanel();
			p9_1.setBackground(new Color(192,233,255));
			p9.add(p9_1);
			
			JLabel l18 = new JLabel("Filtre:");
			p9_1.add(l18);
			
			filtreImprimerOrdonnance = new JTextField();
			p9_1.add(filtreImprimerOrdonnance);
			filtreImprimerOrdonnance.setColumns(15);
			
			JButton btnFiltreOrdonnance = new JButton("");
			btnFiltreOrdonnance.setEnabled(false);
			btnFiltreOrdonnance.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/filtre.png")));
			p9_1.add(btnFiltreOrdonnance);
			
			JScrollPane scrollPane4 = new JScrollPane();
			p9.add(scrollPane4);
			
			View_Ordonnance = new JTable();
			View_Ordonnance.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Ordonnance N°","Cin", "Date Consultation", "Heure Consultation", "Status", "Remarque"
				}
			));
			scrollPane4.setViewportView(View_Ordonnance);
			
			JPanel p9_2 = new JPanel();
			p9_2.setBackground(new Color(192,233,255));
			p9.add(p9_2);
			
			btnRetourOrdonnance = new JButton("Retour");
			btnRetourOrdonnance.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/back.png")));
			p9_2.add(btnRetourOrdonnance);
			btnRetourOrdonnance.addActionListener(this);
			
			btnImprimerOrdonnanceConfirm = new JButton("Imprimer Ordonnace");
			btnImprimerOrdonnanceConfirm.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/print.png")));
			p9_2.add(btnImprimerOrdonnanceConfirm);
			
			JPanel AjouterConsultationPane = new JPanel();
			CenterPane.add(AjouterConsultationPane, "AjouterConsultationPane");
			AjouterConsultationPane.setLayout(new BoxLayout(AjouterConsultationPane, BoxLayout.Y_AXIS));
			
			JPanel p10 = new JPanel();
			p10.setBackground(new Color(192,233,255));
			p10.setMaximumSize(new Dimension(9999,70));
			p10.setPreferredSize(new Dimension(9999, 60));
			AjouterConsultationPane.add(p10);
			p10.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel l19 = new JLabel("Ajouter Consultation");
			l19.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			l19.setHorizontalAlignment(SwingConstants.CENTER);
			p10.add(l19);
			
			JPanel p11 = new JPanel();
			p11.setBackground(new Color(192,233,255));
			p11.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			AjouterConsultationPane.add(p11);
			p11.setMaximumSize(new Dimension(9999, 120));
			p11.setPreferredSize(new Dimension(9999, 50));
			p11.setLayout(new GridLayout(0, 4, 0, 0));
			
			JPanel p11_1 = new JPanel();
			p11_1.setBackground(new Color(192,233,255));
			p11.add(p11_1);
			p11_1.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel l20 = new JLabel("NOM:");
			p11_1.add(l20);
			
			nomAjouterConsultation = new JLabel("Ben Slama");
			p11_1.add(nomAjouterConsultation);
			
			JPanel p11_2 = new JPanel();
			p11_2.setBackground(new Color(192,233,255));
			p11.add(p11_2);
			p11_2.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel l21 = new JLabel("Pr\u00E9nom:");
			p11_2.add(l21);
			
			prenomAjouterConsultation = new JLabel("Haithem");
			p11_2.add(prenomAjouterConsultation);
			
			JPanel p11_3 = new JPanel();
			p11_3.setBackground(new Color(192,233,255));
			p11.add(p11_3);
			p11_3.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel l22 = new JLabel("Date De Naissance:");
			p11_3.add(l22);
			
			dateAjouterConsultation = new JLabel("14/04/2001");
			p11_3.add(dateAjouterConsultation);
			
			JPanel p11_4 = new JPanel();
			p11_4.setBackground(new Color(192,233,255));
			p11.add(p11_4);
			p11_4.setLayout(new GridLayout(0, 2, 0, 0));
			
			JLabel l23 = new JLabel("Poids:");
			p11_4.add(l23);
			
			poidAjouterConsultation = new JLabel("70 Kg");
			p11_4.add(poidAjouterConsultation);
			
			JPanel p12 = new JPanel();
			p12.setBackground(new Color(192,233,255));
			AjouterConsultationPane.add(p12);
			p12.setLayout(new BoxLayout(p12, BoxLayout.X_AXIS));
			
			p12_1 = new JPanel();
			p12_1.setBorder(new LineBorder(new Color(0, 102, 102), 3, true));
			p12_1.setBackground(new Color(192,233,255));
			p12.add(p12_1);
			p12_1.setLayout(new BoxLayout(p12_1, BoxLayout.Y_AXIS));
			
			JPanel p12_1_1 = new JPanel();
			p12_1_1.setBackground(new Color(192,233,255));
			p12_1.add(p12_1_1);
			
			JLabel l24 = new JLabel("Fiche Patient N\u00B0:");
			p12_1_1.add(l24);
			
			numPatientAjouterConsultation = new JTextField();
			p12_1_1.add(numPatientAjouterConsultation);
			numPatientAjouterConsultation.setColumns(10);
			
			JPanel p12_1_2 = new JPanel();
			p12_1_2.setBackground(new Color(192,233,255));
			p12_1.add(p12_1_2);
			
			JLabel l25 = new JLabel("Type de Maladie:");
			p12_1_2.add(l25);
			
			String[] TypeMaladie= {"Maladies cardiovasculaires", "Maladies endocriniennes", "Maladies respiratoires et ORL", "Maladies digestives", "Maladies rhumatologiques", "Maladies neurologiques", "Maladies gyn\u00E9cologiques", "Maladies de la peau", "Maladies des yeux", "Maladies h\u00E9matologiques"};
			comboTypeMaladie= new JComboBox(TypeMaladie);
			p12_1_2.add(comboTypeMaladie);
			
			JPanel p12_1_3 = new JPanel();
			p12_1_3.setBackground(new Color(192,233,255));
			p12_1.add(p12_1_3);
			
			JLabel l26 = new JLabel("Remarque:");
			p12_1_3.add(l26);
			
			remarqueAjouterConsultation= new JTextArea();
			remarqueAjouterConsultation.setLineWrap(true);
			remarqueAjouterConsultation.setColumns(30);
			remarqueAjouterConsultation.setRows(10);
			remarqueAjouterConsultation.setTabSize(5);
			p12_1_3.add(remarqueAjouterConsultation);
			
			JPanel p12_2 = new JPanel();
			p12_2.setBorder(new LineBorder(new Color(0, 102, 102), 3, true));
			p12_2.setBackground(new Color(192,233,255));
			p12.add(p12_2);
			p12_2.setLayout(new BoxLayout(p12_2, BoxLayout.Y_AXIS));
			
			JPanel p12_2_1 = new JPanel();
			p12_2_1.setBackground(new Color(192,233,255));
			p12_2.add(p12_2_1);
			p12_2_1.setLayout(new BoxLayout(p12_2_1, BoxLayout.Y_AXIS));
			
			JPanel p12_2_1_1 = new JPanel();
			p12_2_1.add(p12_2_1_1);
			p12_2_1_1.setLayout(new BoxLayout(p12_2_1_1, BoxLayout.X_AXIS));
			
			JPanel p12_2_1_1_1 = new JPanel();
			p12_2_1_1_1.setPreferredSize(new Dimension(70, 90));
			p12_2_1_1.add(p12_2_1_1_1);
			p12_2_1_1_1.setLayout(new BoxLayout(p12_2_1_1_1, BoxLayout.Y_AXIS));
			
			JPanel p12_2_1_1_1_1 = new JPanel();
			p12_2_1_1_1_1.setBackground(new Color(192,233,255));
			p12_2_1_1_1.add(p12_2_1_1_1_1);
			
			JLabel l27 = new JLabel("Filtre:");
			p12_2_1_1_1_1.add(l27);
			
			filtreMedicamentAjouterConsultation = new JTextField();
			p12_2_1_1_1_1.add(filtreMedicamentAjouterConsultation);
			filtreMedicamentAjouterConsultation.setColumns(10);
			
			JScrollPane scrollPane5 = new JScrollPane();
			p12_2_1_1_1.add(scrollPane5);
			
			View_Medicament = new JTable();
			View_Medicament.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Code Méd.", "Nom Méd.", "Famille"
				}
			));
			scrollPane5.setViewportView(View_Medicament);
			
			JPanel p12_2_1_1_2 = new JPanel();
			p12_2_1_1_2.setBackground(new Color(192,233,255));
			p12_2_1_1_2.setPreferredSize(new Dimension(80, 100) );
			p12_2_1_1.add(p12_2_1_1_2);
			
			p12_2_1_1_2.setLayout(new BoxLayout(p12_2_1_1_2, BoxLayout.Y_AXIS));
			
			JPanel p12_2_1_1_2_1 = new JPanel();
			p12_2_1_1_2_1.setBackground(new Color(192,233,255));
			p12_2_1_1_2.add(p12_2_1_1_2_1);
			
			JLabel l28 = new JLabel("Posologie:     ");
			p12_2_1_1_2_1.add(l28);
			
			nbPosologie= new JSpinner();
			nbPosologie.setPreferredSize(new Dimension(50, 20));
			p12_2_1_1_2_1.add(nbPosologie);
			
			JPanel p12_2_1_1_2_2 = new JPanel();
			p12_2_1_1_2_2.setBackground(new Color(192,233,255));
			p12_2_1_1_2.add(p12_2_1_1_2_2);
			
			JLabel l29 = new JLabel("Par Jour: ");
			p12_2_1_1_2_2.add(l29);
			
			nbFoisParJour = new JSpinner();
			nbFoisParJour.setModel(new SpinnerNumberModel(0, 0, 5, 1));
			nbFoisParJour.setPreferredSize(new Dimension(50, 20));
			p12_2_1_1_2_2.add(nbFoisParJour);
			
			JLabel l30 = new JLabel("fois");
			p12_2_1_1_2_2.add(l30);
			
			JPanel p12_2_1_1_2_3 = new JPanel();
			p12_2_1_1_2_3.setBackground(new Color(192,233,255));
			p12_2_1_1_2.add(p12_2_1_1_2_3);
			
			JLabel l31 = new JLabel("Pendant:");
			p12_2_1_1_2_3.add(l31);
			
			nbPendant= new JSpinner();
			nbPendant.setPreferredSize(new Dimension(50, 20));
			p12_2_1_1_2_3.add(nbPendant);
			
			String[] choix1= {"Jour", "Semaine", "Mois"};
			comboJourSemaineMois= new JComboBox(choix1);
			p12_2_1_1_2_3.add(comboJourSemaineMois);
			
			JPanel p12_2_1_1_2_4 = new JPanel();
			p12_2_1_1_2_4.setBackground(new Color(192,233,255));
			p12_2_1_1_2.add(p12_2_1_1_2_4);
			
			checkTraitement= new JCheckBox("Traitement au long cours");
			checkTraitement.setBackground(new Color(192,233,255));
			p12_2_1_1_2_4.add(checkTraitement);
			
			JPanel p12_2_1_1_2_5 = new JPanel();
			p12_2_1_1_2_5.setBackground(new Color(192,233,255));
			p12_2_1_1_2.add(p12_2_1_1_2_5);
			
			JLabel l32 = new JLabel("Commentaire:");
			p12_2_1_1_2_5.add(l32);
			
			commentaireAjouterConsultation= new JTextArea();
			commentaireAjouterConsultation.setLineWrap(true);
			commentaireAjouterConsultation.setColumns(10);
			commentaireAjouterConsultation.setRows(3);
			p12_2_1_1_2_5.add(commentaireAjouterConsultation);
			
			JPanel p12_2_1_2 = new JPanel();
			p12_2_1_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			p12_2_1.add(p12_2_1_2);
			p12_2_1_2.setMaximumSize(new Dimension(9999,70));
			p12_2_1_2.setLayout(new GridLayout(0, 2, 0, 0));
			
			JPanel p12_2_1_2_1 = new JPanel();
			p12_2_1_2_1.setBackground(new Color(192,233,255));
			p12_2_1_2.add(p12_2_1_2_1);
			
			JLabel l33 = new JLabel("FORME:");
			l33.setHorizontalAlignment(SwingConstants.CENTER);
			p12_2_1_2_1.add(l33);
			
			String []forme={"Comprim\u00E9", "Cr\u00E9mes", "G\u00E9lule", "Sirop ", "Injectables "};
			comboFormeMedicament= new JComboBox(forme);
			p12_2_1_2_1.add(comboFormeMedicament);
			
			JPanel p12_2_1_2_2 = new JPanel();
			p12_2_1_2_2.setBackground(new Color(192,233,255));
			p12_2_1_2.add(p12_2_1_2_2);
			
			JLabel l34 = new JLabel("DOSAGE:");
			p12_2_1_2_2.add(l34);
			
			String [] dosage={"0.5ml", "0.2mg/ml", "1mg/ml", "5mg/ml", "7.5mg/ml", "20mg", "30mg", "50mg", "75mg", "100mg", "150mg", "300mg", "400mg", "500mg", "750mg", "800mg", "1g"};
			comboDosageMedicament= new JComboBox(dosage);
			p12_2_1_2_2.add(comboDosageMedicament);
			
			JPanel p12_2_2 = new JPanel();
			p12_2_2.setBackground(new Color(192,233,255));
			p12_2_2.setMaximumSize(new Dimension(9999, 120));
			p12_2_2.setPreferredSize(new Dimension(9999, 50));
			p12_2.add(p12_2_2);
			
			btnAddMediament= new JButton("Ajouter M\u00E9dicament");
			p12_2_2.add(btnAddMediament);
			
			JPanel p13 = new JPanel();
			p13.setBackground(new Color(192,233,255));
			AjouterConsultationPane.add(p13);
			p13.setMaximumSize(new Dimension(9999, 150));
			p13.setPreferredSize(new Dimension(9999, 100));
			p13.setLayout(new GridLayout(0, 1, 0, 0));
			
			JScrollPane scrollPane6 = new JScrollPane();
			p13.add(scrollPane6);
			
			View_Medicament_Added = new JTable();
			View_Medicament_Added.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Ref Médicament","Nom Médicaments", "Famille", "Forme", "Dosage","Fois/Jour","Période", "Posologie Usuelle"
				}
			));
			scrollPane6.setViewportView(View_Medicament_Added);
			
			JPanel p13_1 = new JPanel();
			p13_1.setBackground(new Color(192,233,255));
			scrollPane6.setRowHeaderView(p13_1);
			p13_1.setLayout(new BoxLayout(p13_1, BoxLayout.X_AXIS));
			
			btnDeleteMedicament= new JButton("Supprimer ");
			p13_1.add(btnDeleteMedicament);
			
			JPanel p14 = new JPanel();
			p14.setBackground(new Color(192,233,255));
			p14.setMaximumSize(new Dimension(9999, 70));
			p14.setPreferredSize(new Dimension(9999, 50));
			AjouterConsultationPane.add(p14);
			
			chargerTousPatients();
			
			btnCancelAjouterConsultation= new JButton("Annuler");
			btnCancelAjouterConsultation.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/close64.png")));
			p14.add(btnCancelAjouterConsultation);
			btnCancelAjouterConsultation.addActionListener(this);
			
			btnConfirmerAjouterConsultation= new JButton("Confirmer");
			btnConfirmerAjouterConsultation.setIcon(new ImageIcon(MedecinInterface.class.getResource("/Icon/add - Copie.png")));
			p14.add(btnConfirmerAjouterConsultation);
			btnConfirmerAjouterConsultation.addActionListener(this);
			cardLayout.show(CenterPane,"RendezVousPane");
			afficherRendezVous();
			filtreMedicamentAjouterConsultation.addKeyListener(this);
			checkTraitement.addItemListener(this);
			btnAddMediament.addActionListener(this);
			numPatientAjouterConsultation.setEnabled(false);
			btnAbscent.addActionListener(this);
			filtrePatientListe.addKeyListener(this);
			filtreImprimerOrdonnance.addKeyListener(this);
			btnDeconnexion.addActionListener(this);
			btnDeleteMedicament.addActionListener(this);
			btnImprimerOrdonnanceConfirm.addActionListener(this);
			btnAfficherOrdonnaceConfirm.addActionListener(this);
			thread.start();

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton click=(JButton)e.getSource();
			if(click==btnImprimerOrdonnance) {
				int row = View_Ordonnance.getRowCount()-1;
				DefaultTableModel model = (DefaultTableModel)View_Ordonnance.getModel();
				while(row>=0) {
					model.removeRow(row);
					row--;
				}
				filtreImprimerOrdonnance.setText("");
				
				cardLayout.show(CenterPane,"ImprimerOrdonnancePane");
			}else 
			if(click==btnConsulterHistorique) {
				int row = View_Liste_Patient.getRowCount() -1;
				if(row >-1) {
					DefaultTableModel model = (DefaultTableModel)View_Liste_Patient.getModel();
					while(row>=0) {
						model.removeRow(row);
						row--;
					}
				}
				filtrePatientListe.setText("");
				cardLayout.show(CenterPane,"ListePatientPane");
			}else 
			if(click==btnRendezVousDeJour) {
				afficherRendezVous();
				cardLayout.show(CenterPane,"RendezVousPane");
			}else
			if(click==btnImprimerOrdonnance) {
				DefaultTableModel model = (DefaultTableModel)View_Ordonnance.getModel();
				int row = View_Ordonnance.getRowCount() -1;
				while(row>=0) {
					model.removeRow(row);
					row--;
				}
				cardLayout.show(CenterPane,"ImprimerOrdonnancePane");
			}else
			if(click==btnAjouterConsultation) {
				int row = View_Medicament.getRowCount() -1;
				DefaultTableModel model = (DefaultTableModel)View_Medicament.getModel();
				while(row>=0) {
					model.removeRow(row);
					row--;
				}
				filtreMedicamentAjouterConsultation.setText("");
				DefaultTableModel model2 = (DefaultTableModel)View_Medicament_Added.getModel();
				row = View_Medicament_Added.getRowCount() -1;
				while(row>=0) {
					model2.removeRow(row);
					row--;
				}
				nbFoisParJour.setValue(0);
				nbPosologie.setValue(0);
				nbPendant.setValue(0);
				commentaireAjouterConsultation.setText("");
				remarqueAjouterConsultation.setText("");
				comboFormeMedicament.setSelectedIndex(0);
				comboDosageMedicament.setSelectedIndex(0);
				comboJourSemaineMois.setSelectedIndex(0);
				comboTypeMaladie.setSelectedIndex(0);
				checkTraitement.setSelected(false);
				
				
				
				if(View_RendezVous.getSelectionModel().isSelectionEmpty() || View_RendezVous.getValueAt(View_RendezVous.getSelectedRow(), 1).equals("")){
					JOptionPane.showMessageDialog(null, "Choisir un patient SVP !");
				}else {
					String heure = getCurrentTime();
					heure = heure.substring(0, 2);
					int index = Integer.parseInt(heure);
					index = (index>=17)?17:index;
					String h =View_RendezVous.getValueAt(View_RendezVous.getSelectedRow(), 0)+""; 
					h = h.substring(0, h.indexOf(':'));

					if(Integer.parseInt(h) == index) {
						remplirConsultationForm(cabinet.recherchePatient(View_RendezVous.getValueAt(View_RendezVous.getSelectedRow(), 1)+""));
						cardLayout.show(CenterPane,"AjouterConsultationPane");
					}else {
						JOptionPane.showMessageDialog(null, "L'heure de cette consultation est deja passé !");
					}
				}
			}else
			if(click==btnAfficherHistoriqueConfirm) {
				if(View_Liste_Patient.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "Selectionner un Patient SVP !");
				}else {	
					Patient patient = cabinet.recherchePatient(View_Liste_Patient.getValueAt(View_Liste_Patient.getSelectedRow(), 0)+"");
					nomPatientConsulter.setText(patient.getNom());
					prenomPatientConsulter.setText(patient.getPrenom());
					datePatientConsulter.setText(patient.getDateNaissance());
					poidsPatientConsulter.setText(patient.getPoids()+"");
					cinPatientConsulter.setText(patient.getCin());
					telPatientConsultation.setText(patient.getTel());
					adressePatientConsultation.setText(patient.getAdresse());
					sexePatientConsulter.setText(patient.getSexe());
					int row = View_Historique_Consultation.getRowCount() -1 ;
					DefaultTableModel model = (DefaultTableModel)View_Historique_Consultation.getModel();
					while(row>=0) {
						model.removeRow(row);
						row--;
					}
					chargerHistoriqueConsultation();
					cardLayout.show(CenterPane,"ConsulterHistoriquePane");
				}
			}else
			if(click==btnRetourHistoriqueConfirm) {
				cardLayout.show(CenterPane,"ListePatientPane");
			}else
			if(click==btnRetourOrdonnance) {
				cardLayout.show(CenterPane,"RendezVousPane");
			}else
			if(click==btnCancelAjouterConsultation) {
				cardLayout.show(CenterPane,"RendezVousPane");
			}else
			
			if(click == btnAbscent) {
				if(View_RendezVous.getSelectionModel().isSelectionEmpty() || View_RendezVous.getValueAt(View_RendezVous.getSelectedRow(), 1).toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Choisir un RendezVous SVP !");
				}else {
					String cinpat = View_RendezVous.getValueAt(View_RendezVous.getSelectedRow(), 1).toString();
					
					marquerAbsent(cinpat);
					
				}
			}else
			
			if(click == btnAddMediament) {
				ListSelectionModel model = View_Medicament.getSelectionModel();
				if(model.isSelectionEmpty())
					JOptionPane.showMessageDialog(null, "Choisir un médicament SVP !");
				else {
					String nomMed = View_Medicament.getValueAt(View_Medicament.getSelectedRow(), 1)+"";
					String familleMed = View_Medicament.getValueAt(View_Medicament.getSelectedRow(), 2)+"";
					String refmed = View_Medicament.getValueAt(View_Medicament.getSelectedRow(), 0)+"";
					String forme = comboFormeMedicament.getSelectedItem().toString();
					String dosage = comboDosageMedicament.getSelectedItem().toString();
					String periode = "";
					String commentaire = commentaireAjouterConsultation.getText();
					String nbfois = nbFoisParJour.getValue().toString();
					if(checkTraitement.isSelected()) {
						periode = "à vie";
					}else {
						periode = nbPendant.getValue().toString() +" "+ comboJourSemaineMois.getSelectedItem().toString();
					}
					String posologie = nbPosologie.getValue().toString() + " Paquet";
					
					DefaultTableModel model2 = (DefaultTableModel)View_Medicament_Added.getModel();
					model2.addRow(new String[] {refmed,nomMed,familleMed,forme,dosage,nbfois,periode,posologie});
					insererMedicaments(refmed,nomMed,familleMed,forme,dosage,nbfois,commentaire,periode,posologie);
				}
			}else
			
			if(click == btnConfirmerAjouterConsultation) {
				String num = numPatientAjouterConsultation.getText();
				String typeMaladie = comboTypeMaladie.getSelectedItem().toString();
				String remarque = remarqueAjouterConsultation.getText();
				
				try {
					if(View_Medicament_Added.getRowCount() == 0) throw new Exception("Essayez d'ajouter des medicaments");
					String status = "Présent";
					String jdbcUrl = "jdbc:sqlite:projet.db";
					String heure = getCurrentTime();
					heure = heure.substring(0,2);
					Connection connection = DriverManager.getConnection(jdbcUrl);
					String sql = "insert into consultation values(?,?,?,?,?,?,?,?);";
					String cin = docteur.getCin();
					String cinpat = View_RendezVous.getValueAt(View_RendezVous.getSelectedRow(), 1)+"";
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1, cin);
					pstmt.setString(2,cinpat );
					pstmt.setString(3, num);
					pstmt.setString(4, status);
					pstmt.setString(5, remarque);
					pstmt.setString(6, typeMaladie);
					pstmt.setString(7, getCurrentDate());
					pstmt.setString(6, heure);

					pstmt.executeUpdate();

					JOptionPane.showMessageDialog(null, "Conultation ajouté avec succées");
					connection.close();
				}
					//cardLayout.show(CenterPane,"RendezVousPane");
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				//insererMedicaments();
			cardLayout.show(CenterPane,"RendezVousPane");
			}else if(click == btnDeconnexion) {
				this.dispose();
				new Login();
			}else if(click == btnDeleteMedicament) {
				if(View_Medicament_Added.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,"Le tableau est deja vide");
				}else if(View_Medicament_Added.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null,"Selectionner un medicament pour le supprimer !");
				}else {
					supprimerMedicamentOrdonnance(numPatientAjouterConsultation.getText(),View_Medicament_Added.getValueAt(View_Medicament_Added.getSelectedRow(), 0)+"" );
					DefaultTableModel model = (DefaultTableModel)View_Medicament_Added.getModel();
					model.removeRow(View_Medicament_Added.getSelectedRow());
				}
			}else if ((click == btnImprimerOrdonnanceConfirm)||(click == btnAfficherOrdonnaceConfirm)) {
				try {
					Desktop.getDesktop().open(new File("Ordonnance.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		

		private void supprimerMedicamentOrdonnance(String idord, String ref) {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "delete from ordonnance where idord = '"+idord+"' and ref = '"+ref+"';";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Suppression Terminé");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void insererMedicaments(String refmed,String nomMed,String familleMed,String forme,String dosage,String nbfois,String commentaire,String periode,String posologie) {	
			ajouterOrdonnance(numPatientAjouterConsultation.getText(),refmed,posologie,nbfois,periode,commentaire,forme,dosage);
		}
		
		private void ajouterOrdonnance(String id, String reference, String posologie,String nbfois, String periode,String commentaire, String forme,String dosage) {
			
			try {
				String jdbcUrl = "jdbc:sqlite:projet.db";
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "insert into ordonnance values(?,?,?,?,?,?,?,?);";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2,reference);
				pstmt.setString(3, posologie);
				pstmt.setString(4, nbfois);
				pstmt.setString(5, periode);
				pstmt.setString(6, commentaire);
				pstmt.setString(7, forme);
				pstmt.setString(8, dosage);

				pstmt.executeUpdate();

				JOptionPane.showMessageDialog(null, "Ordonnance ajouté avec succées");
				connection.close();
			}
				//cardLayout.show(CenterPane,"RendezVousPane");
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		
		}

		private String getReference(String nom) {
			String jdbcUrl = "jdbc:sqlite:projet.db";

			String ref = "";
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);

				String sql = "select * from medicament where nom = '"+nom+"';";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					ref = result.getString("ref");
					
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ref;
		}

		private void chargerHistoriqueConsultation() {
			DefaultTableModel model = (DefaultTableModel)View_Historique_Consultation.getModel();
			String jdbcUrl = "jdbc:sqlite:projet.db";
			ArrayList<Consultation> consultations = new ArrayList<Consultation>();
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "select * from consultation where cindoc = '"+docteur.getCin()+"' and cinpat = '"+View_Liste_Patient.getValueAt(View_Liste_Patient.getSelectedRow(), 0).toString()+"';";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
					if(Integer.parseInt(result.getString("idord")) == 0) {
						consultations.add(new Consultation(result.getString("cinpat"),0,result.getString("status"),"*", result.getString("datec"),result.getString("heurec")));

					}else {
						consultations.add(new Consultation(result.getString("cinpat"),Integer.parseInt(result.getString("idord")),result.getString("status"),result.getString("remarques"), result.getString("datec"),result.getString("heurec")));
					}
					}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			for(Consultation c:consultations) {
				if(! c.getPassed().equals("Absent"))
					model.addRow(new String[] {c.getCinPatient(), c.getDate(),c.getHeure(),c.getPassed(),c.getOrdonnance()+"",c.getRemarques()});
				else
					model.addRow(new String[] {c.getCinPatient(), c.getDate(),c.getHeure(),c.getPassed(),"******","******"});

			}
			
		}
			
		

		private void marquerAbsent(String cinpat) {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			Connection connection;
			String heure = getCurrentTime();
			heure = heure.substring(0,heure.indexOf(':'));
			try {
				connection = DriverManager.getConnection(jdbcUrl);

			String sql = "insert into consultation(cindoc,cinpat,idord,status,datec,heurec) values(?,?,?,?,?,?);";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, docteur.getCin());
			pstmt.setString(2,cinpat);
			pstmt.setString(3, "0");
			pstmt.setString(4, "Absent");
			pstmt.setString(5,getCurrentDate());
			pstmt.setString(6, heure);
			


			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Patient à été marqué absent");
			connection.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Patient est deja marqué absent");
			}
		}


		private void remplirConsultationForm(Patient patient) {
			
			nomAjouterConsultation.setText(patient.getNom());
			prenomAjouterConsultation.setText(patient.getPrenom());
			dateAjouterConsultation.setText(patient.getDateNaissance());
			poidAjouterConsultation.setText(patient.getPoids()+"");
			nomPatientConsulter.setText(patient.getNom());
			prenomPatientConsulter.setText(patient.getPrenom());
			datePatientConsulter.setText(patient.getDateNaissance());
			poidsPatientConsulter.setText(patient.getPoids()+"");
			cinPatientConsulter.setText(patient.getCin());
			numPatientAjouterConsultation.setText(getNbConsultation()+"");
		}

		private void afficherRendezVous() {
			
			DefaultTableModel model = (DefaultTableModel)View_RendezVous.getModel();
			
			for(int i = 0; i<View_RendezVous.getRowCount(); i++)
				for(int j = 1;j<View_RendezVous.getColumnCount(); j++)
					View_RendezVous.setValueAt("", i, j);
			
			String cinDocteur = docteur.getCin();
			String jdbcUrl = "jdbc:sqlite:projet.db";
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);

				String sql = "select * from rendezvous where cindoc = '"+cinDocteur+"' and daterdv = '"+getCurrentDate()+"';";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
					int row = (Integer.parseInt(result.getString("heure"))>12)?Integer.parseInt(result.getString("heure"))-9:Integer.parseInt(result.getString("heure"))-8;
					
					View_RendezVous.setValueAt(result.getString("cinpat"), row, 1);
					View_RendezVous.setValueAt(cabinet.recherchePatient(result.getString("cinpat")).getNom(), row, 2);
					View_RendezVous.setValueAt(cabinet.recherchePatient(result.getString("cinpat")).getPrenom(), row, 3);
					
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			JTextField txt = (JTextField) e.getSource();
			if(txt == filtreMedicamentAjouterConsultation) {
				int row = View_Medicament.getRowCount() -1;
				DefaultTableModel model = (DefaultTableModel)View_Medicament.getModel();
				while(row>=0) {
					model.removeRow(row);
					row--;
				}
				String ch  = filtreMedicamentAjouterConsultation.getText();
				if(ch.length() != 0) {
					ArrayList<Medicament> medicaments = getMedicamentFiltred(ch);
					for(Medicament m : medicaments) {
						model.addRow(new String[] {m.getRef(), m.getNom(),m.getFamille()});
					}
				}
			}else if (txt == filtrePatientListe) {
				String ch = filtrePatientListe.getText();
				DefaultTableModel model = (DefaultTableModel)View_Liste_Patient.getModel();
				int row = View_Liste_Patient.getRowCount() -1;
				while(row>=0) {
					model.removeRow(row);
					row--;
				}
				if(ch.length() != 0) {
					ArrayList<Patient> patients = getFiltredPatient(ch);

					for(Patient r:patients) {
						//"CIN", "Nom", "Prenom", "Date de naissance", "Email", "Tel", "Sexe", "Poids", "Adresse"
						model.addRow(new String[] {r.getCin(), r.getNom(),r.getPrenom(), r.getDateNaissance(), r.getEmail(),r.getTel(),r.getSexe(),r.getPoids()+"",r.getAdresse()});
					}
				}else {
					while(row>=0) {
						model.removeRow(row);
						row--;
					}
				}
			}else if(txt == filtreImprimerOrdonnance) {
				DefaultTableModel model = (DefaultTableModel)View_Ordonnance.getModel();
				int row = View_Ordonnance.getRowCount() - 1;
				while(row>=0) {
					model.removeRow(row);
					row--;
				}
				String ch = filtreImprimerOrdonnance.getText();
				if(ch.length() == 0) {
					row = View_Ordonnance.getRowCount() - 1;
					while(row>=0) {
						model.removeRow(row);
						row--;
					}
				}else {
					getFiltredConsultation(ch);
				}
			}
			
		}
		
		private void getFiltredConsultation(String ch) {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			DefaultTableModel model = (DefaultTableModel)View_Ordonnance.getModel();
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "select * from consultation where cindoc = '"+docteur.getCin()+"' and(cinpat like '%"+ch+"%' or idord like '%"+ch+"%' or status like '%"+ch+"%' or remarques like '%"+ch+"%' or typemaladie like '%"+ch+"%' or datec like '%"+ch+"%' or heurec like '%"+ch+"%') order by idord ASC;";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					//"Ordonnance N°","Cin", "Date Consultation", "Heure Consultation", "Status", "Remarque"
					model.addRow(new String[] {result.getString("idord"),result.getString("cinpat"), result.getString("datec"),result.getString("heurec"),result.getString("status"),result.getString("remarques")});
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		private ArrayList<Medicament> getMedicamentFiltred(String ch) {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			ArrayList<Medicament> medicaments = new ArrayList<Medicament>();
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);

				String sql = "select * from medicament where ref like '%"+ch+"%' or nom like '%"+ch+"%' or famille like '%"+ch+"%' ;";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
					medicaments.add(new Medicament(result.getString("ref"),result.getString("nom"), result.getString("famille")));
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return medicaments;
		}

		private String getCurrentDate() {
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	        LocalDate localDate = LocalDate.now();
	        return  dtf.format(localDate);
		}
		
		private String getCurrentTime() {
			Instant now = Instant.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
	        return formatter.format(now);
		}
		
		
		private int getNbConsultation() {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			int max = 0;
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);

				String sql = "select count(*) as nb from consultation;";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					max = Integer.parseInt(result.getString("nb"));
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return max +1;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == checkTraitement) {
				if(checkTraitement.isSelected()) {
					nbPendant.setEnabled(false);
					comboJourSemaineMois.setEnabled(false);
				}else {
					nbPendant.setEnabled(true);
					comboJourSemaineMois.setEnabled(true);
				}
			}
			
		}
		
		private void chargerTousPatients() {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);

				String sql = "select * from patient where rpps = '"+docteur.getNumeroRPPS()+"';";
				
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while(result.next()) {
					Patient p = new Patient(result.getString("cin"),result.getString("nom"),result.getString("prenom"),
							result.getString("email"),result.getString("tel"),result.getString("adresse"),
							result.getString("date_naissance"),Float.parseFloat(result.getString("poids")), result.getString("sexe"));
					cabinet.ajouterPatient(p);
				}
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		private ArrayList<Patient> getFiltredPatient(String ch) {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			ArrayList<Patient> patients = new ArrayList<Patient>();
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "select * from patient where (cin like '%"+ch+"%' or nom like '%"+ch+"%' or prenom like '%"+ch+"%' or email like '%"+ch+"%' or tel like '%"+ch+"%' or adresse like '%"+ch+"%' or date_naissance like '%"+ch+"%' or poids like '%"+ch+"%' or sexe like '%"+ch+"%') and rpps = '"+docteur.getNumeroRPPS()+"';";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				
				while(result.next()) {
					patients.add(new Patient( result.getString("cin"),result.getString("nom"),
							result.getString("prenom"),result.getString("email"),
							result.getString("tel"),result.getString("adresse"),result.getString("date_naissance"),
							Float.parseFloat(result.getString("poids")),result.getString("sexe")));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return patients;
		}

		@Override
		public void run() {
			while(true) {
				Time.setText(getCurrentTime());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
	}
