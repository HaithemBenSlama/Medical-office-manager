import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EspaceSecretaire extends JFrame implements ActionListener,KeyListener,Runnable {
	private JTextField nomAdd;
	private JTextField prenomAdd;
	private JTextField CinAdd;
	private JTextField ageAdd;
	private JTextField PoidsAdd;
	private JTextField NumAdd;
	private JTextField mailAdd;
	private JTextField AdresseAdd;
	private JTextField FiltreDelete;
	private JTable View_delete;
	private JButton btnSupprimerRendez;
	private CardLayout cardLayout;
	private JPanel CardPane;
	private JTextField filtreSearchPatient;
	private JButton btnDelete;
	private JTable View_Search;
	private JTable View_List;
	private JTextField filtreUpdatePatient;
	private JButton btnConfirmAjoutRendez;
	private JTable View_Update;
	private JTextField TnomUpdate;
	private JButton btnConfirmModifier;
	private JTextField TprenomUpdate;
	private JTextField TcinUpdate;
	private JTextField TemailUpdate;
	private JTextField TadresseUpdate;
	private JTextField TageUpdate;
	private JTextField TpoidsUpdate;
	private JTextField FiltreRendez;
	private JTable View_Rendez;
	private JPanel CenterPane,cardPaneModify;
	private CardLayout cardLayout2,cardLayout3;
	private JTextField cinRendezAjout;
	private JTextField dateRendezAjout;
	private JTextField dateRendezModifier;
	private JTable View_Rendez_Afficher_Tous;
	private JButton btnSearch;
	private JButton btnList;
	private JButton btnAdd;
	private JButton btnGestionPatient;
	private JButton btnModify;
	private JButton btnGestionRendezVous;
	private JButton btnAjoutRendez,btnModifierRendez,btnAfficherTousRendez;
	private JRadioButton rfemmeAdd,rhommeAdd;
	private JButton btnAddConfirm,btnCancelAdd ;
	private JButton btnDeleteConfirm;
	private JButton btnSelectUpdateConfirm,btnConfirmUpdate,btnCancelUpdate;
	private JButton btnAccueil;
	private JButton btnDeconnexion;
	private JLabel NomSecretaire,Time,Date ;
	private JTable View_dash;
	private JLabel nombrePatient;
	private JLabel nombreRendezVous;
	private JComboBox HeureAjouter,heureRendezModifier ;

	private Secretaire secretaire;
	private Cabinet cabinet;
	private Thread thread;
	
	public EspaceSecretaire(Secretaire secretaire) {
		super("Espace Sécretaire");
		
		this.secretaire = secretaire;
		cabinet = new Cabinet();
		cabinet.setResponsable(secretaire);
		thread = new Thread(this);
		
		initialiserCabinet();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1000,700));
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
		
		JPanel p20 = new JPanel();
		p20.setMaximumSize(getSize());
		pLeft.add(p20);
		p20.setLayout(new BoxLayout(p20, BoxLayout.Y_AXIS));
		
		JPanel p20_1 = new JPanel();
		p20_1.setBackground(new Color(137,204,247));
		p20.add(p20_1);
		
		JLabel lblsecretaire = new JLabel("     Espace Secr\u00E9taire   ");
		p20_1.add(lblsecretaire);
		lblsecretaire.setFont(new Font("Oswald", Font.PLAIN, 15));
		lblsecretaire.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/secretary.png")));
		
		JPanel p20_2 = new JPanel();
		p20_2.setBackground(new Color(137,204,247));
		p20.add(p20_2);
		
		
		
		NomSecretaire = new JLabel();
		NomSecretaire.setHorizontalAlignment(SwingConstants.CENTER);
		if(secretaire.getSexe().equals("homme")) {
			NomSecretaire.setText("Monsieur "+secretaire.getPrenom() + " " +secretaire.getNom());
		}else {
			NomSecretaire.setText("Madame "+secretaire.getPrenom() + " " +secretaire.getNom());
			
		}
		NomSecretaire.setFont(new Font("Anonymous Pro", Font.PLAIN, 16));
		p20_2.add(NomSecretaire);
		
		JPanel p20_3 = new JPanel();
		p20_3.setBackground(new Color(137,204,247));
		p20.add(p20_3);
		
		Time = new JLabel(getCurrentTime());
		Time.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		p20_3.add(Time);
		Time.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p20_4 = new JPanel();
		p20_4.setBackground(new Color(137,204,247));
		p20.add(p20_4);
		
		Date = new JLabel(getCurrentDate());
		Date.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		p20_4.add(Date);
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p21 = new JPanel();
		p21.setBackground(new Color(137,204,247));
		pLeft.add(p21);
		p21.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel p21_1 = new JPanel();
		p21_1.setBackground(new Color(137,204,247));
		p21.add(p21_1);
		
		btnAccueil = new JButton("Accueil");
		p21.add(btnAccueil);
		btnAccueil.addActionListener(this);
		
		JPanel p21_2 = new JPanel();
		p21_2.setBackground(new Color(137,204,247));
		p21.add(p21_2);
		
		btnGestionPatient = new JButton("Gestion Patient");
		p21.add(btnGestionPatient);
		btnGestionPatient.addActionListener(this);
		
		JPanel p21_3 = new JPanel();
		p21_3.setBackground(new Color(137,204,247));
		p21.add(p21_3);
		
		btnGestionRendezVous = new JButton("Gestion Rendez-vous");
		btnGestionRendezVous.addActionListener(this);
		p21.add(btnGestionRendezVous);
		
		JPanel p21_4 = new JPanel();
		p21_4.setBackground(new Color(137,204,247));
		p21.add(p21_4);
		
		btnDeconnexion = new JButton("D\u00E9connexion");
		p21.add(btnDeconnexion);
		
		cardLayout2=new CardLayout(0,0);
		CenterPane = new JPanel();
		getContentPane().add(CenterPane, BorderLayout.CENTER);
		CenterPane.setLayout(cardLayout2);
		
		JPanel Bienvenu = new JPanel();
		Bienvenu.setBackground(new Color(192,233,255));
		CenterPane.add(Bienvenu, "Bienvenu");
		Bienvenu.setLayout(new GridLayout(0, 1, 0, 0));
		cardLayout2.show(CenterPane,"dash");
		
		JLabel l39 = new JLabel("Bienvenue");
		l39.setHorizontalAlignment(SwingConstants.CENTER);
		l39.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 45));
		Bienvenu.add(l39);
		
		JPanel PatientPane = new JPanel();
		PatientPane.setBackground(new Color(83,166,216));
		CenterPane.add(PatientPane, "PatientPane");
		PatientPane.setLayout(new BoxLayout(PatientPane, BoxLayout.Y_AXIS));
		
		JPanel ButtonPane = new JPanel();
		ButtonPane.setBackground(new Color(83,166,216));
		PatientPane.add(ButtonPane);
		ButtonPane.setPreferredSize(new Dimension(750, 80));
		ButtonPane.setLayout(new BoxLayout(ButtonPane, BoxLayout.X_AXIS));
		
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/add.png")));
		btnAdd.setPreferredSize(new Dimension(50, 50));
		btnAdd.addActionListener(this);
		ButtonPane.add(btnAdd);
		
		btnModify = new JButton("\r\n");
		btnModify.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/modify.png")));
		btnModify.addActionListener(this);
		ButtonPane.add(btnModify);
		
		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/remove.png")));
		ButtonPane.add(btnDelete);
		btnDelete.addActionListener(this);
		btnDelete.addActionListener(this);
		
		btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/search.png")));
		btnSearch.addActionListener(this);
		ButtonPane.add(btnSearch);
		
		btnList = new JButton("");
		btnList.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/list.png")));
		btnList.addActionListener(this);
		ButtonPane.add(btnList);
		
		CardPane = new JPanel();
		PatientPane.add(CardPane);
		cardLayout=new CardLayout(0, 0);
		CardPane.setLayout(cardLayout);
		
		
		JPanel AddPatient = new JPanel();
		AddPatient.setBackground(new Color(192,233,255));
		CardPane.add(AddPatient, "ADDPATIENT");
		AddPatient.setLayout(new BorderLayout(0, 0));
		
		JLabel l1 = new JLabel("Ajouter Patient\r\n");
		l1.setBackground(new Color(137,204,247));
		l1.setFont(new Font("Anonymous Pro", Font.BOLD, 26));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setPreferredSize(new Dimension(750,80));
		AddPatient.add(l1, BorderLayout.NORTH);
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(192,233,255));
		AddPatient.add(p1, BorderLayout.CENTER);
		p1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel p1_1 = new JPanel();
		p1_1.setBackground(new Color(192,233,255));
		FlowLayout fl_p1_1 = (FlowLayout) p1_1.getLayout();
		p1.add(p1_1);
		
		JLabel l2 = new JLabel("Nom:                     ");
		p1_1.add(l2);
		
		nomAdd = new JTextField();
		p1_1.add(nomAdd);
		nomAdd.setColumns(20);
		
		JPanel p1_2 = new JPanel();
		p1_2.setBackground(new Color(192,233,255));
		p1.add(p1_2);
		
		JLabel l3 = new JLabel("Pr\u00E9nom:                 ");
		p1_2.add(l3);
		
		prenomAdd = new JTextField();
		p1_2.add(prenomAdd);
		prenomAdd.setColumns(20);
		
		JPanel p1_3 = new JPanel();
		p1_3.setBackground(new Color(192,233,255));
		p1.add(p1_3);
		
		JLabel l4 = new JLabel("CIN:                        ");
		p1_3.add(l4);
		
		CinAdd = new JTextField();
		p1_3.add(CinAdd);
		CinAdd.setColumns(20);
		
		JPanel p1_4 = new JPanel();
		p1_4.setBackground(new Color(192,233,255));
		p1.add(p1_4);
		
		JLabel l5 = new JLabel("Date de Naissance:");
		p1_4.add(l5);
		
		ageAdd = new JTextField();
		p1_4.add(ageAdd);
		ageAdd.setColumns(20);
		
		JPanel p1_5 = new JPanel();
		p1_5.setBackground(new Color(192,233,255));
		p1.add(p1_5);
		
		JLabel l6 = new JLabel("Poids:                      ");
		p1_5.add(l6);
		
		PoidsAdd = new JTextField();
		p1_5.add(PoidsAdd);
		PoidsAdd.setColumns(20);
		
		JPanel p1_6 = new JPanel();
		p1_6.setBackground(new Color(192,233,255));
		p1.add(p1_6);
		
		JLabel LgenreAdd = new JLabel("Genre:");
		p1_6.add(LgenreAdd);
		
		rhommeAdd = new JRadioButton("Homme");
		rhommeAdd.setSelected(true);
		rhommeAdd.setBackground(new Color(192,233,255));
		p1_6.add(rhommeAdd);
		
		rfemmeAdd = new JRadioButton("Femme");
		rfemmeAdd.setBackground(new Color(192,233,255));
		p1_6.add(rfemmeAdd);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rfemmeAdd);
		group.add(rhommeAdd);
		
		JPanel p1_7 = new JPanel();
		p1_7.setBackground(new Color(192,233,255));
		p1.add(p1_7);
		
		JLabel l7 = new JLabel("Num T\u00E9l:                   ");
		p1_7.add(l7);
		
		NumAdd = new JTextField();
		p1_7.add(NumAdd);
		NumAdd.setColumns(20);
		
		JPanel p1_8 = new JPanel();
		p1_8.setBackground(new Color(192,233,255));
		p1.add(p1_8);
		
		JLabel l8 = new JLabel("E-Mail:                       ");
		p1_8.add(l8);
		
		mailAdd = new JTextField();
		p1_8.add(mailAdd);
		mailAdd.setColumns(20);
		
		JPanel p1_9 = new JPanel();
		p1_9.setBackground(new Color(192,233,255));
		p1.add(p1_9);
		
		JLabel l9 = new JLabel("Adresse:                    ");
		p1_9.add(l9);
		
		AdresseAdd = new JTextField();
		p1_9.add(AdresseAdd);
		AdresseAdd.setColumns(20);
		
		JPanel p2 = new JPanel();
		p2.setBackground(new Color(192,233,255));
		FlowLayout flowLayout = (FlowLayout) p2.getLayout();
		flowLayout.setHgap(70);
		flowLayout.setVgap(10);
		AddPatient.add(p2, BorderLayout.SOUTH);
		
		btnCancelAdd = new JButton("Annuler");
		p2.add(btnCancelAdd);
		
		btnAddConfirm = new JButton("Ajouter");
		p2.add(btnAddConfirm);
		
		JPanel DeletePatient = new JPanel();
		DeletePatient.setBackground(new Color(192,233,255));
		CardPane.add(DeletePatient, "DeletePatient");
		DeletePatient.setLayout(new BoxLayout(DeletePatient, BoxLayout.Y_AXIS));
		
		JPanel p3 = new JPanel();
		p3.setBackground(new Color(192,233,255));
		p3.setPreferredSize(new Dimension(9999, 50));
		p3.setMaximumSize(new Dimension(9999, 100));
		DeletePatient.add(p3);
		
		JLabel l10 = new JLabel("Supprimer Patient");
		p3.add(l10);
		l10.setFont(new Font("Anonymous Pro", Font.PLAIN, 26));
		
		JPanel p4 = new JPanel();
		DeletePatient.add(p4);
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		
		JPanel p4_1 = new JPanel();
		p4_1.setBackground(new Color(192,233,255));
		p4_1.setPreferredSize(new Dimension(50, 60));
		p4_1.setMaximumSize(new Dimension(99999, 60));
		p4.add(p4_1);
		
		JLabel l11 = new JLabel("Filtre:");
		p4_1.add(l11);
		
		FiltreDelete = new JTextField();
		p4_1.add(FiltreDelete);
		FiltreDelete.setColumns(20);
		
		JButton filtreDeleteConfirm = new JButton("");
		filtreDeleteConfirm.setEnabled(false);
		filtreDeleteConfirm.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/filtre.png")));
		p4_1.add(filtreDeleteConfirm);
		
		JScrollPane scrollPane2 = new JScrollPane();
		p4.add(scrollPane2);
		View_delete = new JTable();
		scrollPane2.setViewportView(View_delete);
		View_delete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		View_delete.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"CIN", "Nom", "Prénom", "Date de Naissance", "Poids", "Genre", "Num Tél", "E-Mail","Adresse"
			}
		));
		
		JPanel p4_2 = new JPanel();
		p4_2.setBackground(new Color(192,233,255));
		p4.add(p4_2);
		p4_2.setMinimumSize(new Dimension(9999,150));
		p4_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l12 = new JLabel("Choississez un patient depuis la Table puis appuyer sur supprimer.");
		l12.setHorizontalAlignment(SwingConstants.CENTER);
		p4_2.add(l12);
		l12.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		
		JPanel p5 = new JPanel();
		DeletePatient.add(p5);
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
		
		JPanel p5_1 = new JPanel();
		p5_1.setBackground(new Color(192,233,255));
		p5.add(p5_1);
		
		btnDeleteConfirm = new JButton("Supprimer");
		btnDeleteConfirm.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/remove.png")));
		p5_1.add(btnDeleteConfirm);

	
		
		JPanel SearchPatient = new JPanel();
		SearchPatient.setBackground(new Color(192,233,255));
		CardPane.add(SearchPatient, "SearchPatient");
		SearchPatient.setLayout(new BoxLayout(SearchPatient, BoxLayout.Y_AXIS));
		
		JPanel p6 = new JPanel();
		p6.setBackground(new Color(192,233,255));
		p6.setPreferredSize(new Dimension(9999, 50));
		p6.setMaximumSize(new Dimension(9999, 70));
		SearchPatient.add(p6);
		p6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l13 = new JLabel("Recherche Patient(s)");
		l13.setHorizontalAlignment(SwingConstants.CENTER);
		l13.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 26));
		p6.add(l13);
		
		JPanel p7 = new JPanel();
		SearchPatient.add(p7);
		p7.setLayout(new BoxLayout(p7, BoxLayout.Y_AXIS));
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(new Color(192,233,255));
		panel_19.setPreferredSize(new Dimension(50, 60));
		panel_19.setMaximumSize(new Dimension(99999, 60));
		p7.add(panel_19);
		
		JLabel l14 = new JLabel("Filtrer:");
		panel_19.add(l14);
		
		filtreSearchPatient = new JTextField();
		panel_19.add(filtreSearchPatient);
		filtreSearchPatient.setColumns(20);
		
		JButton btnFiltreSearchPatient = new JButton("");
		btnFiltreSearchPatient.setEnabled(false);
		btnFiltreSearchPatient.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/filtre.png")));
		panel_19.add(btnFiltreSearchPatient);
		
		JScrollPane scrollPane3 = new JScrollPane();
		p7.add(scrollPane3);
		
		View_Search = new JTable();
		scrollPane3.setViewportView(View_Search);
		View_Search.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CIN", "Nom", "Prénom", "Date de Naissance", "Poids", "Genre", "Num Tél", "E-Mail","Adresse"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JPanel ListPatient = new JPanel();
		CardPane.add(ListPatient, "ListPatient");
		
		JPanel p8 = new JPanel();
		p8.setBackground(new Color(192,233,255));
		p8.setPreferredSize(new Dimension(9999, 50));
		p8.setMaximumSize(new Dimension(9999, 70));
		ListPatient.setLayout(new BoxLayout(ListPatient, BoxLayout.Y_AXIS));
		ListPatient.add(p8);
		p8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l15 = new JLabel("Liste des patients");
		l15.setHorizontalAlignment(SwingConstants.CENTER);
		l15.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 28));
		p8.add(l15);
		
		JScrollPane scrollPane4 = new JScrollPane();
		ListPatient.add(scrollPane4);
		
		View_List = new JTable();
		View_List.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"CIN", "Nom", "Prénom", "Date de Naissance", "Poids", "Genre", "Num Tél", "E-Mail","Adresse"
			}
		));
		scrollPane4.setViewportView(View_List);
		
		JPanel ModifyPatient = new JPanel();
		CardPane.add(ModifyPatient, "ModifyPatient");
		ModifyPatient.setLayout(new BoxLayout(ModifyPatient, BoxLayout.Y_AXIS));
		
		JPanel p9 = new JPanel();
		p9.setBackground(new Color(192,233,255));
		ModifyPatient.add(p9);
		p9.setMaximumSize(new Dimension(9999, 70));
		p9.setPreferredSize(new Dimension(9999, 70));
		p9.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l16 = new JLabel("Modifier Information Patient");
		l16.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		l16.setHorizontalAlignment(SwingConstants.CENTER);
		p9.add(l16);
		
		JPanel p10 = new JPanel();
		ModifyPatient.add(p10);
		p10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel p10_1 = new JPanel();
		p10.add(p10_1);
		p10_1.setLayout(new BoxLayout(p10_1, BoxLayout.Y_AXIS));
		
		JPanel p10_1_1 = new JPanel();
		p10_1_1.setBackground(new Color(192,233,255));
		p10_1_1.setMaximumSize(new Dimension(9999,50));
		p10_1.add(p10_1_1);
		
		JLabel l17 = new JLabel("Filtre");
		p10_1_1.add(l17);
		
		filtreUpdatePatient = new JTextField();
		p10_1_1.add(filtreUpdatePatient);
		filtreUpdatePatient.setColumns(15);
		
		JButton btnfiltreUpdatePatient = new JButton("");
		btnfiltreUpdatePatient.setEnabled(false);
		btnfiltreUpdatePatient.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/filtre.png")));
		p10_1_1.add(btnfiltreUpdatePatient);
		
		JScrollPane scrollPane5 = new JScrollPane();
		scrollPane5.setMaximumSize(new Dimension(9999,150));
		scrollPane5.setPreferredSize(new Dimension(9999,150));
		p10_1.add(scrollPane5);
		
		View_Update = new JTable();
		View_Update.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CIN", "Nom", "Prénom"
			}
		));
		scrollPane5.setViewportView(View_Update);
		
		JPanel p10_1_2 = new JPanel();
		p10_1_2.setBackground(new Color(192,233,255));
		p10_1.add(p10_1_2);
		p10_1_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l18 = new JLabel("S'il vous plait selectionner le patient ");
		l18.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		l18.setHorizontalAlignment(SwingConstants.CENTER);
		p10_1_2.add(l18);
		
		JLabel l19 = new JLabel("\u00E0 modifier depuis la Table ci-dessus.");
		l19.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		l19.setHorizontalAlignment(SwingConstants.CENTER);
		p10_1_2.add(l19);
		
		JPanel p10_1_3 = new JPanel();
		p10_1_3.setBackground(new Color(192,233,255));
		p10_1.add(p10_1_3);
		
		btnSelectUpdateConfirm = new JButton("S\u00E9lectionner");
		btnSelectUpdateConfirm.setFont(new Font("Oswald", Font.PLAIN, 17));
		p10_1_3.add(btnSelectUpdateConfirm);
		
		JPanel p10_2 = new JPanel();
		p10_2.setBackground(new Color(192,233,255));
		p10.add(p10_2);
		p10_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel p10_2_1 = new JPanel();
		p10_2_1.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_1);
		
		JLabel l20 = new JLabel("Nom:                    ");
		p10_2_1.add(l20);
		
		TnomUpdate = new JTextField();
		p10_2_1.add(TnomUpdate);
		TnomUpdate.setColumns(15);
		
		JPanel p10_2_2 = new JPanel();
		p10_2_2.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_2);
		
		JLabel l21 = new JLabel("Pr\u00E9nom:               ");
		p10_2_2.add(l21);
		
		TprenomUpdate = new JTextField();
		p10_2_2.add(TprenomUpdate);
		TprenomUpdate.setColumns(15);
		
		JPanel p10_2_3 = new JPanel();
		p10_2_3.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_3);
		
		JLabel l22 = new JLabel("Cin:                      ");
		p10_2_3.add(l22);
		
		TcinUpdate = new JTextField();
		p10_2_3.add(TcinUpdate);
		TcinUpdate.setColumns(15);
		
		JPanel p10_2_4 = new JPanel();
		p10_2_4.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_4);
		
		JLabel l23 = new JLabel("E-Mail:                   ");
		p10_2_4.add(l23);
		
		TemailUpdate = new JTextField();
		p10_2_4.add(TemailUpdate);
		TemailUpdate.setColumns(15);
		
		JPanel p10_2_5 = new JPanel();
		p10_2_5.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_5);
		
		JLabel l24 = new JLabel("Adresse:                 ");
		p10_2_5.add(l24);
		
		TadresseUpdate = new JTextField();
		p10_2_5.add(TadresseUpdate);
		TadresseUpdate.setColumns(15);
		
		JPanel p10_2_6 = new JPanel();
		p10_2_6.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_6);
		
		JLabel l25 = new JLabel("Date de Naissance: ");
		p10_2_6.add(l25);
		
		TageUpdate = new JTextField();
		p10_2_6.add(TageUpdate);
		TageUpdate.setColumns(15);
		
		JPanel p10_2_7 = new JPanel();
		p10_2_7.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_7);
		
		JLabel l26 = new JLabel("Poids:                    ");
		p10_2_7.add(l26);
		
		TpoidsUpdate = new JTextField();
		p10_2_7.add(TpoidsUpdate);
		TpoidsUpdate.setColumns(15);
		
		JPanel p10_2_8 = new JPanel();
		p10_2_8.setBackground(new Color(192,233,255));
		p10_2.add(p10_2_8);
		
		btnCancelUpdate = new JButton("Annuler");
		p10_2_8.add(btnCancelUpdate);
		
		btnConfirmUpdate= new JButton("Modifier");
		p10_2_8.add(btnConfirmUpdate);
		
		JPanel AccueilPatient = new JPanel();
		CardPane.add(AccueilPatient, "AccueilPatient");
		AccueilPatient.setLayout(new BoxLayout(AccueilPatient, BoxLayout.Y_AXIS));
		
		JPanel p11 = new JPanel();
		p11.setBackground(new Color(192,233,255));
		AccueilPatient.add(p11);
		p11.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l27 = new JLabel("Gestion Patient");
		l27.setHorizontalAlignment(SwingConstants.CENTER);
		l27.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 26));
		p11.add(l27);
		
		JPanel p12 = new JPanel();
		p12.setBackground(new Color(192,233,255));
		AccueilPatient.add(p12);
		p12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l28 = new JLabel("");
		l28.setHorizontalAlignment(SwingConstants.CENTER);
		p12.add(l28);
		l28.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/dashPatient.png")));
		
		JPanel RendezPane = new JPanel();
		RendezPane.setBackground(new Color(192,233,255));
		CenterPane.add(RendezPane, "RendezPane");
		RendezPane.setLayout(new BoxLayout(RendezPane, BoxLayout.Y_AXIS));
		
		JPanel p13 = new JPanel();
		p13.setBackground(new Color(192,233,255));
		RendezPane.add(p13);
		p13.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l29 = new JLabel("Gestion Rendez-vous");
		l29.setHorizontalAlignment(SwingConstants.CENTER);
		l29.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		p13.add(l29);
		
		JPanel p14 = new JPanel();
		RendezPane.add(p14);
		p14.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel p15 = new JPanel();
		p14.add(p15);
		p15.setLayout(new BoxLayout(p15, BoxLayout.Y_AXIS));
		
		JPanel p15_1 = new JPanel();
		p15_1.setBackground(new Color(192,233,255));
		p15.add(p15_1);
		
		JLabel lblNewLabel_5 = new JLabel("Filtre:");
		p15_1.add(lblNewLabel_5);
		
		FiltreRendez = new JTextField();
		p15_1.add(FiltreRendez);
		FiltreRendez.setColumns(15);
		
		JButton btnFiltreRendez = new JButton("");
		btnFiltreRendez.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/filtre.png")));
		p15_1.add(btnFiltreRendez);
		
		JScrollPane scrollPane6 = new JScrollPane();
		p15.add(scrollPane6);
		
		View_Rendez = new JTable();
		View_Rendez.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CIN", "Date", "Heure"
			}
		));
		
		View_Rendez.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent l) {
					int row = View_Rendez.getSelectedRow();
					if(row != -1) {
						btnModifierRendez.setEnabled(true);
						btnSupprimerRendez.setEnabled(true);
						
					
					dateRendezModifier.setText(cabinet.rechercheRendezvous(View_Rendez.getValueAt(row, 0)+"", View_Rendez.getValueAt(row, 1)+"", View_Rendez.getValueAt(row, 2) +"").getDateRendezVous());
					int index = Integer.parseInt(cabinet.rechercheRendezvous(View_Rendez.getValueAt(row, 0)+"", View_Rendez.getValueAt(row, 1)+"", View_Rendez.getValueAt(row, 2) +"").getHeure());
					heureRendezModifier.setSelectedIndex((index>12)?index-9:index-8);
				}
			}
			
		});
		
		scrollPane6.setViewportView(View_Rendez);
		
		JPanel p15_2 = new JPanel();
		p15_2.setBackground(new Color(192,233,255));
		p15.add(p15_2);
		
		btnAjoutRendez = new JButton("Ajouter");
		btnAjoutRendez.addActionListener(this);
		p15_2.add(btnAjoutRendez);
		
		btnModifierRendez = new JButton("Modifier");
		btnModifierRendez.addActionListener(this);
		p15_2.add(btnModifierRendez);
		btnModifierRendez.setEnabled(false);
		
		btnSupprimerRendez = new JButton("Supprimer");
		p15_2.add(btnSupprimerRendez);
		btnSupprimerRendez.setEnabled(false);
		
		JPanel p15_3 = new JPanel();
		p15_3.setBackground(new Color(192,233,255));
		p15.add(p15_3);
		
		btnAfficherTousRendez = new JButton("Afficher Tous");
		btnAfficherTousRendez.addActionListener(this);
		p15_3.add(btnAfficherTousRendez);
		
		cardLayout3=new CardLayout(0, 0);
		cardPaneModify = new JPanel();
		p14.add(cardPaneModify);
		cardPaneModify.setLayout(cardLayout3);
		
		JPanel p16_1 = new JPanel();
		p16_1.setBackground(new Color(192,233,255));
		cardPaneModify.add(p16_1, "p16_1");
		p16_1.setLayout(new BorderLayout(0, 0));
		
		JLabel pimg = new JLabel("\r\n");
		pimg.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/reservation.png")));
		pimg.setHorizontalAlignment(SwingConstants.CENTER);
		p16_1.add(pimg, BorderLayout.CENTER);
		
		JPanel p16_1_1 = new JPanel();
		p16_1_1.setBackground(new Color(192,233,255));
		p16_1.add(p16_1_1, BorderLayout.WEST);
		
		JPanel AjoutRendezPane = new JPanel();
		cardPaneModify.add(AjoutRendezPane, "AjoutRendezPane");
		AjoutRendezPane.setLayout(new BoxLayout(AjoutRendezPane, BoxLayout.Y_AXIS));
		
		JPanel p17_1 = new JPanel();
		p17_1.setBackground(new Color(192,233,255));
		AjoutRendezPane.add(p17_1);
		p17_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l30 = new JLabel("Ajouter Rendez-Vous");
		l30.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 26));
		l30.setHorizontalAlignment(SwingConstants.CENTER);
		p17_1.add(l30);
		
		JPanel p17_2 = new JPanel();
		p17_2.setBackground(new Color(192,233,255));
		AjoutRendezPane.add(p17_2);
		
		JLabel l31 = new JLabel("CIN:");
		p17_2.add(l31);
		
		cinRendezAjout = new JTextField();
		p17_2.add(cinRendezAjout);
		cinRendezAjout.setColumns(15);
		
		JPanel p17_3 = new JPanel();
		p17_3.setBackground(new Color(192,233,255));
		AjoutRendezPane.add(p17_3);
		
		JLabel l32 = new JLabel("Date:");
		p17_3.add(l32);
		
		dateRendezAjout = new JTextField();
		p17_3.add(dateRendezAjout);
		dateRendezAjout.setColumns(15);
		
		JPanel p17_4 = new JPanel();
		p17_4.setBackground(new Color(192,233,255));
		AjoutRendezPane.add(p17_4);
		
		JLabel l33 = new JLabel("Heure:");
		p17_4.add(l33);
		
		String[] heure={"8:00", "9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};
		HeureAjouter = new JComboBox(heure);
		p17_4.add(HeureAjouter);
		
		JPanel p17_5 = new JPanel();
		p17_5.setBackground(new Color(192,233,255));
		AjoutRendezPane.add(p17_5);
		
		JButton btnCancelAjoutRendez = new JButton("Annuler");
		p17_5.add(btnCancelAjoutRendez);
		
		btnConfirmAjoutRendez = new JButton("Ajouter");
		p17_5.add(btnConfirmAjoutRendez);
		
		JPanel ModifierRendezPane = new JPanel();
		cardPaneModify.add(ModifierRendezPane, "ModifierRendezPane");
		ModifierRendezPane.setLayout(new BoxLayout(ModifierRendezPane, BoxLayout.Y_AXIS));
		
		JPanel p18_1 = new JPanel();
		p18_1.setBackground(new Color(192,233,255));
		ModifierRendezPane.add(p18_1);
		p18_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l34 = new JLabel("Modifier Rendez-vous");
		l34.setHorizontalAlignment(SwingConstants.CENTER);
		l34.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		p18_1.add(l34);
		
		JPanel p18_2 = new JPanel();
		p18_2.setBackground(new Color(192,233,255));
		ModifierRendezPane.add(p18_2);
		
		JLabel l35 = new JLabel("Date:");
		p18_2.add(l35);
		
		dateRendezModifier = new JTextField();
		p18_2.add(dateRendezModifier);
		dateRendezModifier.setColumns(10);
		
		JPanel p18_3 = new JPanel();
		p18_3.setBackground(new Color(192,233,255));
		ModifierRendezPane.add(p18_3);
		
		JLabel l36 = new JLabel("Heure:");
		p18_3.add(l36);
		
		heureRendezModifier = new JComboBox(heure);
		p18_3.add(heureRendezModifier);
		
		JPanel p18_4 = new JPanel();
		p18_4.setBackground(new Color(192,233,255));
		ModifierRendezPane.add(p18_4);
		
		JButton btnCancelModifier = new JButton("Annuler");
		p18_4.add(btnCancelModifier);
		
		btnConfirmModifier = new JButton("Modifier");
		p18_4.add(btnConfirmModifier);
		
		JPanel ListRedez_vous = new JPanel();
		CenterPane.add(ListRedez_vous, "ListRedez_vous");
		ListRedez_vous.setLayout(new BoxLayout(ListRedez_vous, BoxLayout.Y_AXIS));
		
		JPanel p19 = new JPanel();
		p19.setBackground(new Color(192,233,255));
		p19.setMaximumSize(new Dimension (9999,70));
		p19.setPreferredSize(new Dimension (9999,70));
		ListRedez_vous.add(p19);
		p19.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel p37 = new JLabel("Afficher Liste Rendez-vous");
		p37.setHorizontalAlignment(SwingConstants.CENTER);
		p37.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		p19.add(p37);
		
		JScrollPane scrollPane7 = new JScrollPane();
		ListRedez_vous.add(scrollPane7);
		
		View_Rendez_Afficher_Tous = new JTable();
		View_Rendez_Afficher_Tous.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CIN", "Date", "Heure"
			}
		));
		scrollPane7.setViewportView(View_Rendez_Afficher_Tous);
		
		JPanel dash = new JPanel();
		dash.setBackground(new Color(192,233,255));
		CenterPane.add(dash, "dash");
		dash.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel p22 = new JPanel();
		dash.add(p22);
		p22.setLayout(new BoxLayout(p22, BoxLayout.Y_AXIS));
		
		JPanel p22_1 = new JPanel();
		p22_1.setBackground(new Color(192,233,255));
		p22.add(p22_1);
		p22_1.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel p22_1_1 = new JPanel();
		p22_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p22_1.add(p22_1_1);
		p22_1_1.setLayout(new BoxLayout(p22_1_1, BoxLayout.X_AXIS));
		
		JPanel p22_1_1_1 = new JPanel();
		p22_1_1.add(p22_1_1_1);
		p22_1_1_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l38 = new JLabel("Total Patient");
		l38.setHorizontalAlignment(SwingConstants.CENTER);
		l38.setFont(new Font("Anonymous Pro", Font.PLAIN, 20));
		p22_1_1_1.add(l38);
		
		JLabel l40 = new JLabel("");
		l40.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/patient.png")));
		l40.setHorizontalAlignment(SwingConstants.CENTER);
		p22_1_1_1.add(l40);
		
		JPanel p22_1_1_2 = new JPanel();
		p22_1_1.add(p22_1_1_2);
		p22_1_1_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		nombrePatient = new JLabel(cabinet.getPatients().size()+"");
		p22_1_1_2.add(nombrePatient);
		nombrePatient.setFont(new Font("Anonymous Pro", Font.PLAIN, 52));
		nombrePatient.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p22_1_2 = new JPanel();
		p22_1_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p22_1.add(p22_1_2);
		p22_1_2.setLayout(new BoxLayout(p22_1_2, BoxLayout.X_AXIS));
		
		JPanel p22_1_2_1 = new JPanel();
		p22_1_2.add(p22_1_2_1);
		p22_1_2_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel p22_1_2_1_1 = new JPanel();
		p22_1_2_1.add(p22_1_2_1_1);
		p22_1_2_1_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel l42 = new JLabel("Total Rendez-vous");
		l42.setFont(new Font("Anonymous Pro", Font.PLAIN, 20));
		l42.setHorizontalAlignment(SwingConstants.CENTER);
		p22_1_2_1_1.add(l42);
		
		JLabel l41 = new JLabel("");
		l41.setIcon(new ImageIcon(EspaceSecretaire.class.getResource("/Icon/dossier.png")));
		l41.setHorizontalAlignment(SwingConstants.CENTER);
		p22_1_2_1.add(l41);
		
		JPanel p22_1_2_2 = new JPanel();
		p22_1_2.add(p22_1_2_2);
		p22_1_2_2.setLayout(new CardLayout(0, 0));
		
		nombreRendezVous = new JLabel(cabinet.getRendezvous().size()+ "");
		nombreRendezVous.setFont(new Font("Anonymous Pro", Font.PLAIN, 52));
		nombreRendezVous.setHorizontalAlignment(SwingConstants.CENTER);
		p22_1_2_2.add(nombreRendezVous, "nombreRendezVous");
		
		JPanel p22_2 = new JPanel();
		p22.add(p22_2);
		p22_2.setLayout(new BoxLayout(p22_2, BoxLayout.Y_AXIS));
		
		JPanel p22_2_1 = new JPanel();
		p22_2_1.setBackground(new Color(192,233,255));
		p22_2.add(p22_2_1);
		p22_2_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel p22_2_1_1 = new JPanel();
		p22_2_1_1.setBackground(new Color(192,233,255));
		p22_2_1.add(p22_2_1_1);
		
		JPanel p22_2_1_2 = new JPanel();
		p22_2_1_2.setBackground(new Color(192,233,255));
		p22_2_1.add(p22_2_1_2);
		
		/*JPanel p22_2_1_3 = new JPanel();
		p22_2_1_3.setBackground(new Color(192,233,255));
		p22_2_1.add(p22_2_1_3);
		*/
		JLabel l37 = new JLabel("Rendez-Vous d'aujourd'hui:");
		l37.setHorizontalAlignment(SwingConstants.LEFT);
		l37.setFont(new Font("Anonymous Pro", Font.PLAIN, 24));
		p22_2_1.add(l37);
		
		JPanel p22_2_1_4 = new JPanel();
		p22_2_1_4.setBackground(new Color(192,233,255));
		p22_2_1.add(p22_2_1_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(192,233,255));
		//scrollPane.setMaximumSize(new Dimension(550,200));
		scrollPane.setPreferredSize(new Dimension(650,293));
		p22_2.add(scrollPane);
		thread.start();
		
		View_dash = new JTable();
		View_dash.setRowHeight(30);
		View_dash.setModel(new DefaultTableModel(
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
				"Heure", "CIN", "Nom", "Pr\u00E9nom"
			}
		));
		
		scrollPane.setViewportView(View_dash);
		cardLayout2.show(CenterPane,"dash");
		
		btnAddConfirm.addActionListener(this);
		filtreSearchPatient.addKeyListener(this);
		btnDeleteConfirm.addActionListener(this);
		FiltreDelete.addKeyListener(this);
		filtreUpdatePatient.addKeyListener(this);
		FiltreRendez.addKeyListener(this);
		btnConfirmModifier.addActionListener(this);
		btnSupprimerRendez.addActionListener(this);
		btnAjoutRendez.addActionListener(this);
		btnConfirmAjoutRendez.addActionListener(this);
		btnDeconnexion.addActionListener(this);
		remplirJTableDashboard();
		
		TnomUpdate.setEnabled(false);
		TprenomUpdate.setEnabled(false);
		TadresseUpdate.setEnabled(false);
		TageUpdate.setEnabled(false);
		TcinUpdate.setEnabled(false);
		TemailUpdate.setEnabled(false);
		TpoidsUpdate.setEnabled(false);
		btnConfirmUpdate.setEnabled(false);
		btnConfirmUpdate.addActionListener(this);
		View_Update.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		
			@Override
			public void valueChanged(ListSelectionEvent lst) {
				if(!lst.getValueIsAdjusting()) {
					if(View_Update.getSelectedRow() != -1) {
						String cin = View_Update.getValueAt(View_Update.getSelectedRow(), 0) + "";
						Patient p = cabinet.recherchePatient(cin);
							TnomUpdate.setText(p.getNom());
							TprenomUpdate.setText(p.getPrenom());
							TadresseUpdate.setText(p.getAdresse());
							TageUpdate.setText(p.getDateNaissance());
							TcinUpdate.setText(p.getCin());
							TemailUpdate.setText(p.getEmail());
							TpoidsUpdate.setText(p.getPoids()+"");
							
							TadresseUpdate.setEnabled(true);
							TemailUpdate.setEnabled(true);
							TpoidsUpdate.setEnabled(true);
							btnConfirmUpdate.setEnabled(true);
							
					}
						
					
				}
			}
			
		});
	}

	private void remplirJTableListePatient() {
		int row = 0;
		DefaultTableModel model = (DefaultTableModel)View_List.getModel();
		model.setRowCount(0);
		for(Patient i:cabinet.getPatients()) {

			
			model.insertRow(row, new String [] {i.getCin(),i.getNom(),i.getPrenom(),i.getDateNaissance(),i.getPoids()+"",i.getSexe(),i.getTel(),i.getEmail(),i.getAdresse()});
			
			row++;
			
		}
		
	}

	private void initialiserCabinet() {
		chargerPatients();
		chargerRendezvous();
		
	}

	private void chargerPatients() {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from patient where rpps = '"+secretaire.getRppsDocteur()+"';";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Patient p = new Patient(result.getString("cin") ,result.getString("nom") ,result.getString("prenom") ,result.getString("email") ,result.getString("tel") ,result.getString("adresse") ,result.getString("date_naissance") ,Float.parseFloat(result.getString("poids")),result.getString("sexe"));
				cabinet.ajouterPatient(p);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void chargerRendezvous() {
		String cinDocteur = getCinDocteur(secretaire.getRppsDocteur());
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from rendezvous where cindoc = '"+cinDocteur+"';";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Patient p = cabinet.recherchePatient(result.getString("cinpat"));
				RendezVous r = new RendezVous(p,result.getString("daterdv") , result.getString("heure") );
				cabinet.ajouterRendezVous(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getCinDocteur(String rppsDocteur) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from docteur where numerorpps = '"+rppsDocteur+"';";
			String cinn = "";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				cinn =  result.getString("cin");
			}
			return cinn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton click=(JButton)e.getSource();
		if(click==btnDelete) {
			cardLayout.show(CardPane, "DeletePatient");
		}
		if(click==btnAdd) {
			cardLayout.show(CardPane, "ADDPATIENT");
		}
		if(click==btnSearch) {
			cardLayout.show(CardPane,"SearchPatient");			
		}
		if(click==btnList) {
			remplirJTableListePatient();
			cardLayout.show(CardPane,"ListPatient");

		}
		if(click==btnModify) {
			filtreUpdatePatient.setText("");
			TnomUpdate.setText("");
			TprenomUpdate.setText("");
			TadresseUpdate.setText("");
			TageUpdate.setText("");
			TcinUpdate.setText("");
			TemailUpdate.setText("");
			TpoidsUpdate.setText("");
			

			
			TadresseUpdate.setEnabled(false);
			TemailUpdate.setEnabled(false);
			TpoidsUpdate.setEnabled(false);
			
			
			int row = View_Update.getRowCount() -1;
			DefaultTableModel model = (DefaultTableModel)View_Update.getModel();
			while(row>=0) {
				model.removeRow(row);
				row--;
			}
			
			cardLayout.show(CardPane,"ModifyPatient");
		}
		if(click==btnGestionRendezVous) {
			DefaultTableModel model = (DefaultTableModel)View_Rendez.getModel();
			cardLayout3.show(cardPaneModify, "p16_1");
			for(int i = model.getRowCount() -1 ; i>=0;i--) {
				model.removeRow(i);
			}
			
			FiltreRendez.setText("");
			btnModifierRendez.setEnabled(false);
			btnSupprimerRendez.setEnabled(false);
			
			cardLayout2.show(CenterPane,"RendezPane");
		}
		if(click==btnGestionPatient) {
			cardLayout2.show(CenterPane,"PatientPane");
			cardLayout.show(CardPane,"AccueilPatient");
		}
		if(click==btnAjoutRendez) {
			cardLayout3.show(cardPaneModify,"AjoutRendezPane");
		}
		if(click==btnModifierRendez) {
			cardLayout3.show(cardPaneModify,"ModifierRendezPane");
		}
		if(click==btnAfficherTousRendez) {
			cardLayout2.show(CenterPane,"ListRedez_vous");
		}
		if(click==btnAccueil) {
			for(int i = 0;i<View_dash.getRowCount(); i++) {
				View_dash.setValueAt("", i, 1);
				View_dash.setValueAt("", i, 2);
				View_dash.setValueAt("", i, 3);
			}
			remplirJTableDashboard();
			cardLayout2.show(CenterPane,"dash");
		}
		if(click == btnDeconnexion) {
			this.dispose();
			new Login();
		}
		if(click == btnAddConfirm) {
			ajouterPatient();
		}
		
		if(click == btnDeleteConfirm) {
			ListSelectionModel model = (ListSelectionModel)View_delete.getSelectionModel();

			if(model.isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Selectioner un patient pour le supprimer svp !");
			}else {				
				supprimerPatientSelectionner();
			}
		}
		
		if(click == btnConfirmUpdate) {
			String email = TemailUpdate.getText();
			String poids = TpoidsUpdate.getText();
			String adresse = TadresseUpdate.getText();
			
			try {
				if(email.length() == 0) throw new Exception("Email est Obligatoire !");
				if(!ControleDeSaisie.isEmailValid(email)) throw new Exception("Email Invalide !");
				
				if(poids.length() == 0) throw new Exception("Poids est Obligatoire !");
				
				if(adresse.length() == 0) throw new Exception("Adresse est Obligatoire !");
				
				updatePatient(TcinUpdate.getText(),email,poids,adresse);
				
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		
		if(click == btnAfficherTousRendez) {
			affihcerRendezvousJtable();
		}
		
		if(click == btnConfirmModifier) {
			String date = dateRendezModifier.getText();
			int heure = (heureRendezModifier.getSelectedIndex()>4)?heureRendezModifier.getSelectedIndex() +9 : heureRendezModifier.getSelectedIndex() + 8;
			
			try {
				if(date.length() == 0) throw new Exception("Date est obligatoire !");
				if(! ControleDeSaisie.verifDate(date)) throw new Exception("Date est Invalide (jj/mm/aaaa)");
				int row = View_Rendez.getSelectedRow();
				
				if(docteurDisponible(getCinDocteur(secretaire.getRppsDocteur()), date,heure)) {
					cabinet.rechercheRendezvous(View_Rendez.getValueAt(row, 0)+"", View_Rendez.getValueAt(row, 1)+"", View_Rendez.getValueAt(row, 2)+"").setDateRendezVous(date);
					cabinet.rechercheRendezvous(View_Rendez.getValueAt(row, 0)+"", View_Rendez.getValueAt(row, 1)+"", View_Rendez.getValueAt(row, 2)+"").setHeure(heure+"");
					modifierRendezvous(View_Rendez.getValueAt(View_Rendez.getSelectedRow(), 0)+"",date,heure);
				}else {
					JOptionPane.showMessageDialog(null, "Docteur n'est pas disponible dans cette date !");
				}
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		
		if(click == btnSupprimerRendez) {
			
			
			
			
			String cin = View_Rendez.getValueAt(View_Rendez.getSelectedRow(), 0)+"";
			String date = View_Rendez.getValueAt(View_Rendez.getSelectedRow(), 1)+"";
			String heure = View_Rendez.getValueAt(View_Rendez.getSelectedRow(), 2)+"";
			
			cabinet.supprimerRendezvous(cin, date, heure);
			nombreRendezVous.setText(cabinet.getRendezvous().size()+"");
			supprimerRendezvous(cin,date,heure);
			int row = View_Rendez.getRowCount() -1;
			DefaultTableModel model = (DefaultTableModel)View_Rendez.getModel();
			
			while(row>=0) {
				model.removeRow(row);
				row--;
			}
			btnSupprimerRendez.setEnabled(false);
			FiltreRendez.setText("");
			btnModifierRendez.setEnabled(false);
		}
		
		if(click == btnConfirmAjoutRendez) {
			String cin = cinRendezAjout.getText();
			String date = dateRendezAjout.getText();
			String heure = (HeureAjouter.getSelectedIndex()>4)?(HeureAjouter.getSelectedIndex()+9)+"":(HeureAjouter.getSelectedIndex()+8)+"";
			
			try {
				if(cin.length() == 0) throw new Exception("Cin est Obligatoire !");
				if(! ControleDeSaisie.verifCin(cin)) throw new Exception("Cin est invalide !");
				
				if(date.length() == 0) throw new Exception("Date RendezVous est Obligatoire !");
				if(! ControleDeSaisie.verifDate(date)) throw new Exception("Date est Invalide (jj/mm/aaaa)");
				
				ajouterRendezVous(getCinDocteur(secretaire.getRppsDocteur()) , cin,date,heure);
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}

	private void ajouterRendezVous(String cinDocteur, String cin, String date2, String heure) {
		
		if (!patientExiste(cin)) {
			JOptionPane.showMessageDialog(null, "Patient n'existe pas !");				

		}else {
			if(docteurDisponible(getCinDocteur(secretaire.getRppsDocteur()), date2 , Integer.parseInt(heure))) {
			String jdbcUrl = "jdbc:sqlite:projet.db";
			try {
				Connection connection = DriverManager.getConnection(jdbcUrl);
				String sql = "insert into rendezvous values(?,?,?,?,?);";
				
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, (getNbRendezvous() +1)+"");
				pstmt.setString(2, date2);
				pstmt.setString(3, heure);
				pstmt.setString(4, cinDocteur);
				pstmt.setString(5, cin);

				pstmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Rendezvous ajouté avec succées");	
				cabinet.ajouterRendezVous(new RendezVous(cabinet.recherchePatient(cin),date2,heure));
				nombreRendezVous.setText(cabinet.getRendezvous().size()+"");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
			}	else {
			JOptionPane.showMessageDialog(null, "Docteur n'est pas disponible dans cette date est heure");
		}
		}
	}

	private void supprimerRendezvous(String cin, String date2, String heure) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "delete from rendezvous where cinpat = '"+cin+"' and daterdv = '"+date2+"' and heure = '" + heure + "';";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Rendezvous Supprimer avec Succées");
			DefaultTableModel model = (DefaultTableModel)View_Rendez.getModel();
			model.removeRow(View_Rendez.getSelectedRow());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private boolean docteurDisponible(String cinDocteur, String date2, int heure) {
		
		String jdbcUrl = "jdbc:sqlite:projet.db";
		boolean existe = false;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from rendezvous where cindoc = '"+cinDocteur+"' and daterdv = '"+date2+"' and heure = '"+heure+"';";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int nb = 0;
			while(result.next()) {
				nb++;
			}
			existe =  (nb == 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	private void modifierRendezvous(String string, String date2, int heure) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "update rendezvous set daterdv = '"+date2+"', heure = '"+heure+"' where cinpat = '"+string+"' and daterdv = '"+View_Rendez.getValueAt(View_Rendez.getSelectedRow(), 1)+"';";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Rendezvous Mise a jour avec Succées");
			dateRendezModifier.setText("");
			heureRendezModifier.setSelectedIndex(0);
			FiltreRendez.setText("");
			DefaultTableModel model = (DefaultTableModel)View_Rendez.getModel();
			int row = model.getRowCount() - 1;
			while(row>=0) {
				model.removeRow(row);
				row--;
			}
			btnSupprimerRendez.setEnabled(false);
			btnModifierRendez.setEnabled(false);
			
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void affihcerRendezvousJtable() {
		DefaultTableModel model = (DefaultTableModel)View_Rendez_Afficher_Tous.getModel();
		model.setRowCount(0);
		int row = 0;
		for(RendezVous  r : cabinet.getRendezvous()) {
			model.insertRow(row, new String[] {r.getPatient().getCin(), r.getDateRendezVous(), r.getHeure() + "H"});
		}
	}

	private void updatePatient(String cin,String email, String poids, String adresse) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		cabinet.recherchePatient(cin).setEmail(email);
		cabinet.recherchePatient(cin).setAdresse(adresse);
		cabinet.recherchePatient(cin).setPoids(Float.parseFloat(poids));
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "update patient set email = '"+email+"', adresse = '"+adresse+"' , poids = '"+poids+"' where cin = '"+cin+"';";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Patient Mise a jour avec Succées");
			
			DefaultTableModel model = (DefaultTableModel)View_Update.getModel();
			
			int row = model.getRowCount() -1;
			while(row>=0) {
				model.removeRow(row);
				row--;
			}
			
			filtreUpdatePatient.setText("");
			
			
			
			TnomUpdate.setText("");
			TprenomUpdate.setText("");
			TadresseUpdate.setText("");
			TageUpdate.setText("");
			TcinUpdate.setText("");
			TemailUpdate.setText("");
			TpoidsUpdate.setText("");
			
			TadresseUpdate.setEnabled(false);
			TemailUpdate.setEnabled(false);
			TpoidsUpdate.setEnabled(false);
			btnConfirmUpdate.setEnabled(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void supprimerPatientSelectionner() {
		DefaultTableModel model = (DefaultTableModel)View_delete.getModel();
		String cin = View_delete.getValueAt(View_delete.getSelectedRow(), 0) + "";
		
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "delete from patient where cin = '"+cin+"';";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Patient Supprimer avec Succées");
			model.setRowCount(0);
			FiltreDelete.setText("");
			cabinet.supprimerPatient(cin);
			nombrePatient.setText(cabinet.getPatients().size()+"");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ajouterPatient() {
		String _nom,_prenom,_cin,_age,_poids,_sexe,_tel,_email,_adresse;
		_nom = nomAdd.getText();			
		_prenom = prenomAdd.getText();
		_cin = CinAdd.getText();
		_age = ageAdd.getText();
		_poids = PoidsAdd.getText();
		_tel = NumAdd.getText();
		_email = mailAdd.getText();
		_adresse = AdresseAdd.getText();
		if(rfemmeAdd.isSelected()) {
			_sexe = "femme";
		}else {
			_sexe = "homme";
		}
		
		if(patientExiste(_cin)) {
			JOptionPane.showMessageDialog(null, "Patient est deja existe !");
		}else {
			try {				
				if(_nom.length() == 0) throw new Exception("Champs nom est obligatoire");
				if(! ControleDeSaisie.isAlpha(_nom)) throw new Exception("nom est invalide !");
				
				if(_prenom.length() == 0) throw new Exception("Champs prenom est obligatoire");
				if(! ControleDeSaisie.isAlpha(_prenom)) throw new Exception("Prenom est invalide !");
				
				if(_cin.length() == 0) throw new Exception("Champs cin est obligatoire");
				if(! ControleDeSaisie.verifCin(_cin)) throw new Exception("Cin est invalide !");
				
				if(_age.length() == 0) throw new Exception("Champs Date de Naissance est obligatoire");
				if(! ControleDeSaisie.verifDate(_age)) throw new Exception("Date est Invalide (jj/mm/aaaa)");
				
				if(_poids.length() == 0) throw new Exception("Champs Poids est obligatoire");
				
				if(_tel.length() == 0) throw new Exception("Champs Tel est obligatoire");
				if(! ControleDeSaisie.verifTel(_tel)) throw new Exception("Tel est invalide");
				
				if(_email.length() == 0) throw new Exception("Champs email est obligatoire");
				if(! ControleDeSaisie.isEmailValid(_email)) throw new Exception("Email est invalide !");
				
				if(_adresse.length() == 0) throw new Exception("Champs adresse est obligatoire");
				
				savePatient(_cin,_nom,_prenom,_email,_tel,_adresse,_age,_poids,_sexe );
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		
	}

	private void savePatient(String _cin, String _nom, String _prenom, String _email, String _tel, String _adresse,
			String _age, String _poids, String _sexe) {
		
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "insert into patient values(?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, _cin);
			pstmt.setString(2, _nom);
			pstmt.setString(3, _prenom);
			pstmt.setString(4, _email);
			pstmt.setString(5, _tel);
			pstmt.setString(6, _adresse);
			pstmt.setString(7, _age);
			pstmt.setString(8, _poids);
			pstmt.setString(9, _sexe);
			pstmt.setString(10, secretaire.getRppsDocteur());
			pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Patient ajouté avec succées");
			Patient p = new Patient(_cin,_nom,_prenom,_email,_tel,_adresse,_age,Float.parseFloat(_poids),_sexe);
			cabinet.ajouterPatient(p);
			nombrePatient.setText(cabinet.getRendezvous().size()+"");
			nomAdd.setText("");
			prenomAdd.setText("");
			CinAdd.setText("");
			ageAdd.setText("");
			PoidsAdd.setText("");
			NumAdd.setText("");
			mailAdd.setText("");
			AdresseAdd.setText("");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private boolean patientExiste(String _cin) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		boolean existe = false;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from patient where cin = '"+_cin+"' and rpps = '"+secretaire.getRppsDocteur()+"';";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int nb = 0;
			while(result.next()) {
				nb++;
			}
			existe =  (nb != 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField)e.getSource();
		if(txt== filtreSearchPatient) {
			String ch = filtreSearchPatient.getText();
			DefaultTableModel model = (DefaultTableModel)View_Search.getModel();

			if(ch.length() == 0) {
				model.setRowCount(0);
			}else {
				ArrayList<Patient> patients = getFiltredPatient(ch);
				model.setRowCount(0);
				int row = 0;
				for(Patient p:patients) {
					model.insertRow(row, new String[] {p.getCin(), p.getNom(), p.getPrenom(),p.getDateNaissance(), p.getPoids()+"", p.getSexe(), p.getTel(), p.getEmail(), p.getAdresse()});
					row++;
				}
			}
		}
		
		if(txt == FiltreDelete) {
			String ch = FiltreDelete.getText();
			DefaultTableModel model = (DefaultTableModel)View_delete.getModel();

			if(ch.length() == 0) {
				model.setRowCount(0);
			}else {
				ArrayList<Patient> patients = getFiltredPatient(ch);
				model.setRowCount(0);
				int row = 0;
				for(Patient p:patients) {
					model.insertRow(row, new String[] {p.getCin(), p.getNom(), p.getPrenom(),p.getDateNaissance(), p.getPoids()+"", p.getSexe(), p.getTel(), p.getEmail(), p.getAdresse()});
					row++;
				}
			}
		}
		
		if (txt == filtreUpdatePatient) {
			String ch = filtreUpdatePatient.getText();
			DefaultTableModel model = (DefaultTableModel)View_Update.getModel();

			if(ch.length() == 0) {
				model.setRowCount(0);
			}else {
				ArrayList<Patient> patients = getFiltredPatient(ch);
				model.setRowCount(0);
				int row = 0;
				for(Patient p:patients) {
					model.insertRow(row, new String[] {p.getCin(), p.getNom(), p.getPrenom()});
					row++;
				}
			}
		}
		
		if(txt == FiltreRendez) {
			String ch = FiltreRendez.getText();
			DefaultTableModel model = (DefaultTableModel)View_Rendez.getModel();

			if(ch.length() == 0) {
				model.setRowCount(0);
			}else {
				ArrayList<RendezVous> rendezvous = getFiltredRendezvous(ch);
				model.setRowCount(0);
				int row = 0;
				for(RendezVous r:rendezvous) {
					model.insertRow(row, new String[] {
							r.getPatient().getCin(),
							r.getDateRendezVous(),
							r.getHeure()
					});
					row++;
				}
			}
		}
	}
	
	

	

	private ArrayList<RendezVous> getFiltredRendezvous(String ch) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		ArrayList<RendezVous> rendezvous = new ArrayList<RendezVous>();
		try {
			String cin = getCinDocteur(secretaire.getRppsDocteur());
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from rendezvous where (daterdv like '%"+ch+"%' or heure like '%"+ch+"%' or cindoc like '%"+ch+"%' or cinpat like '%"+ch+"%') and cindoc = '"+cin+"';";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				rendezvous.add(new RendezVous(cabinet.recherchePatient(result.getString("cinpat")),result.getString("daterdv"),result.getString("heure")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rendezvous;
	}

	private ArrayList<Patient> getFiltredPatient(String ch) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		ArrayList<Patient> patients = new ArrayList<Patient>();
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from patient where (cin like '%"+ch+"%' or nom like '%"+ch+"%' or prenom like '%"+ch+"%' or email like '%"+ch+"%' or tel like '%"+ch+"%' or adresse like '%"+ch+"%' or date_naissance like '%"+ch+"%' or poids like '%"+ch+"%' or sexe like '%"+ch+"%') and rpps = '"+secretaire.getRppsDocteur()+"';";
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
	
	private void remplirJTableDashboard() {	
		for(RendezVous r : cabinet.getRendezvous()) {
			if(r.getDateRendezVous().equals(getCurrentDate())) {
				int heure = Integer.parseInt(r.getHeure()); 
				if(heure>=14) heure--;
				View_dash.setValueAt(r.getPatient().getCin(), heure-8, 1);
				View_dash.setValueAt(r.getPatient().getNom(), heure-8, 2);
				View_dash.setValueAt(r.getPatient().getPrenom(), heure-8, 3);
			}
		}
	}
	
	private int getNbRendezvous() {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		ArrayList<Patient> patients = new ArrayList<Patient>();
		int max = 0;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select max(id) as nb from rendezvous;";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				max = Integer.parseInt(result.getString("nb"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
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
