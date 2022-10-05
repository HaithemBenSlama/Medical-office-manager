
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class Login extends JFrame implements MouseListener,ActionListener {
	private JTextField Lname;
	private JPasswordField Pass;
	private ButtonGroup group;
	private JLabel Lclose,ForgetPass;
	private JButton btnLogin;
	private JButton btnRegister;
	private JRadioButton Medecin;
	JRadioButton Secretaire;

	public Login() {
		setLayout(new GridLayout(0, 1, 0, 0));
		Color BackgroundColor=new Color(224, 236, 222);
		JPanel Login = new JPanel();
		Login.setBorder(null);
		Login.setPreferredSize(new Dimension(580,600));
		Login.setBackground(new Color(205, 224, 201));
		getContentPane().add(Login);
		Login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\n Bienvenu \n");
		lblNewLabel.setBounds(0, 21, 585, 70);
		lblNewLabel.setFont(new Font("Anonymous Pro", Font.BOLD, 41));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Login.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(110, 101, 374, 431);
		panel.setBackground(BackgroundColor);
		Login.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom d'utilisateur:");
		lblNewLabel_1.setFont(new Font("Oswald", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(62, 48, 133, 40);
		panel.add(lblNewLabel_1);
		
		Lname = new JTextField();
		Lname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Lname.setHorizontalAlignment(SwingConstants.CENTER);
		Lname.setBounds(50, 90, 260, 40);
		panel.add(Lname);
		Lname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de Passe:");
		lblNewLabel_2.setFont(new Font("Oswald", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(62, 151, 122, 43);
		panel.add(lblNewLabel_2);
		
		btnLogin = new JButton("Se Connecter");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(29, 330, 130, 40);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnLogin);
		btnLogin.addActionListener(this);
		
		btnRegister = new JButton("Cr\u00E9er Compte");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.setBounds(201, 330, 130, 40);
		btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnRegister);
		btnRegister.addActionListener(this);
		
		JLabel lblNewLabel_3 = new JLabel("OU");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(157, 330, 44, 40);
		panel.add(lblNewLabel_3);
		
		ForgetPass = new JLabel("Mot de passe oubli\u00E9 ?");
		ForgetPass.setForeground(Color.BLUE);
		ForgetPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ForgetPass.setHorizontalAlignment(SwingConstants.CENTER);
		ForgetPass.setBounds(113, 395, 140, 30);
		ForgetPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(ForgetPass);
		
		Medecin = new JRadioButton("M\u00E9decin");
		Medecin.setSelected(true);
		panel.add(Medecin);
		Medecin.setFont(new Font("Oswald", Font.PLAIN, 17));
		Medecin.setBackground(BackgroundColor);
		Medecin.setBounds(62, 269, 93, 21);
		
		Secretaire = new JRadioButton("S\u00E9cretaire");
		panel.add(Secretaire);
		Secretaire.setFont(new Font("Oswald", Font.PLAIN, 17));
		Secretaire.setBounds(200, 269, 140, 21);
		Secretaire.setBackground(BackgroundColor);
		group=new ButtonGroup();
		group.add(Secretaire);
		group.add(Medecin);
		
		Pass = new JPasswordField();
		Pass.setHorizontalAlignment(SwingConstants.CENTER);
		Pass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Pass.setBounds(50, 190, 260, 40);
		panel.add(Pass);
		
		JLabel img = new JLabel("");
		img.setIcon(new ImageIcon(Login.class.getResource("/Icon/login.png")));
		img.setBounds(525, 116, 400, 400);
		Login.add(img);
		
		Lclose = new JLabel("New label");
		Lclose.setIcon(new ImageIcon(Login.class.getResource("/Icon/cancel.png")));
		Lclose.setBounds(950, 10, 40, 40);
		Lclose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Lclose.addMouseListener(this);
		Login.add(Lclose);
		
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000,600));
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==Lclose) {
			System.exit(0);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
			
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn=(JButton)e.getSource();
		if(btn==btnRegister) {
			this.dispose();// tsaker il window il 9dima
			new Register();
		}
		if(btn==btnLogin) {
			//controle de saisie et redirection a la page d'accueil si vraie
			String login = Lname.getText();
			String password = Pass.getText();
			if((login.length() == 0)||(password.length() == 0)) {
				JOptionPane.showMessageDialog(null, "Remplir Tous les Chapms SVP");
			}else {
				userLogin(login,password);
			}
		}
		
	}
	
	private void userLogin(String login, String password) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from authentification where login = '" +login+ "' and password = '" + password + "';";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int nb = 0;
			while(result.next()) {
				nb++;
			}
			
			if(nb == 0) {
				JOptionPane.showMessageDialog(null, "Utulisateur n'existe pas !");
			}else {
				if(Secretaire.isSelected()) {					
					Secretaire secretaire = getSecretaireData(login);
					this.dispose();
					new EspaceSecretaire(secretaire);
					connection.close();
				}else {
					Docteur docteur = getDoctorData(login);
					new MedecinInterface(docteur);
					connection.close();
					this.dispose();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Docteur getDoctorData(String login) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from docteur where cin = '"+login+"';";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Docteur docteur = new Docteur();
			while(result.next()) {
				docteur.setCin(result.getString("cin"));
				docteur.setNom(result.getString("nom"));
				docteur.setPrenom(result.getString("prenom"));
				docteur.setEmail(result.getString("email"));
				docteur.setTel(result.getString("tel"));
				docteur.setAdresse(result.getString("adresse"));
				docteur.setSpecialite(result.getString("specialite"));
				docteur.setNumeroRPPS(result.getString("numerorpps"));
			}
			connection.close();
			return docteur;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Secretaire getSecretaireData(String login) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from secretaire where cin = '"+login+"';";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Secretaire secretaire = new Secretaire();
			while(result.next()) {
				secretaire.setCin(result.getString("cin"));
				secretaire.setNom(result.getString("nom"));
				secretaire.setPrenom(result.getString("prenom"));
				secretaire.setEmail(result.getString("email"));
				secretaire.setTel(result.getString("tel"));
				secretaire.setAdresse(result.getString("adresse"));
				secretaire.setDateNaissance(result.getString("date_naissance"));
				secretaire.setRppsDocteur(result.getString("cindoc"));
				secretaire.setSexe(result.getString("sexe"));
			}
			return secretaire;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
