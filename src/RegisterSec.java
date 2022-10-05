
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;


public class RegisterSec extends JFrame implements MouseListener,ActionListener {
	private JTextField nom;
	private JTextField prenom;
	private ButtonGroup group;
	private JLabel Lclose;
	private JTextField cin;
	private JTextField tel;
	private JTextField adresse;
	private JTextField age;
	private JRadioButton rfemme,rhomme;
	private JButton btnCreate,btnCancel;
	private String rpps;
	private String cinn;
	
	Secretaire secretaire;

	public RegisterSec(String cinn, String rpps) {
		secretaire = new Secretaire();
		
		secretaire.setCin(cinn);
		secretaire.setRppsDocteur(rpps);
		
		
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		Color BackgroundColor=new Color(224, 236, 222);
		JPanel Info = new JPanel();
		Info.setBorder(null);
		Info.setPreferredSize(new Dimension(580,600));
		Info.setBackground(new Color(205, 224, 201));
		getContentPane().add(Info);
		Info.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Information Personnel S\u00E9cretaire");
		lblNewLabel.setBounds(31, 21, 894, 70);
		lblNewLabel.setFont(new Font("Anonymous Pro", Font.BOLD, 41));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Info.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(105, 101, 800, 460);
		panel.setBackground(BackgroundColor);
		Info.add(panel);
		panel.setLayout(null);
		
		JLabel Lnom = new JLabel("Nom:");
		Lnom.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lnom.setBounds(50, 17, 133, 40);
		panel.add(Lnom);
		
		nom = new JTextField();
		nom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setBounds(50, 67, 260, 40);
		panel.add(nom);
		nom.setColumns(10);
		
		JLabel Lprenom = new JLabel("Pr\u00E9nom:");
		Lprenom.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lprenom.setBounds(50, 117, 122, 43);
		panel.add(Lprenom);
		group=new ButtonGroup();
		
		prenom = new JTextField();
		prenom.setHorizontalAlignment(SwingConstants.CENTER);
		prenom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		prenom.setBounds(50, 170, 260, 40);
		panel.add(prenom);
		
		JLabel Lcin = new JLabel("E-mail:");
		Lcin.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lcin.setBounds(49, 231, 70, 21);
		panel.add(Lcin);
		
		cin = new JTextField();
		cin.setBounds(50, 262, 260, 40);
		cin.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(cin);
		cin.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 224, 201));
		panel_1.setBounds(400, 20, 4, 420);
		panel.add(panel_1);
		
		JLabel Lgenre = new JLabel("Genre:");
		Lgenre.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lgenre.setBounds(50, 327, 59, 21);
		panel.add(Lgenre);
		
		JLabel Ltel = new JLabel("Num\u00E9ro T\u00E9l:");
		Ltel.setFont(new Font("Oswald", Font.PLAIN, 17));
		Ltel.setBounds(460, 23, 122, 28);
		panel.add(Ltel);
		
		tel = new JTextField();
		tel.setBounds(460, 69, 260, 40);
		tel.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(tel);
		tel.setColumns(10);
		
		JLabel Ladresse = new JLabel("Adresse:");
		Ladresse.setFont(new Font("Oswald", Font.PLAIN, 17));
		Ladresse.setBounds(460, 128, 153, 21);
		panel.add(Ladresse);
		
		adresse = new JTextField();
		adresse.setBounds(460, 170, 260, 40);
		adresse.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(adresse);
		adresse.setColumns(10);
		
		JLabel Lage = new JLabel("Date Naissance:");
		Lage.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lage.setBounds(460, 231, 200, 21);
		panel.add(Lage);
		
		age = new JTextField();
		age.setBounds(460, 262, 260, 40);
		age.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(age);
		age.setColumns(10);
		
		btnCancel = new JButton("Annuler");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(460, 339, 100, 40);
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.addActionListener(this);
		panel.add(btnCancel);
		
		btnCreate = new JButton("Cr\u00E9er");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreate.setBounds(620, 339, 100, 40);
		btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCreate.addActionListener(this);
		panel.add(btnCreate);
		
		rhomme = new JRadioButton("Homme");
		rhomme.setBackground(BackgroundColor);
		rhomme.setSelected(true);
		rhomme.setFont(new Font("Oswald", Font.PLAIN, 16));
		rhomme.setBounds(80, 366, 103, 21);
		panel.add(rhomme);
		
		rfemme = new JRadioButton("Femme");
		rfemme.setBackground(BackgroundColor);
		rfemme.setFont(new Font("Oswald", Font.PLAIN, 16));
		rfemme.setBounds(207, 366, 103, 21);
		panel.add(rfemme);
		group.add(rhomme);
		group.add(rfemme);
		
		Lclose = new JLabel("New label");
		Lclose.setIcon(new ImageIcon(Login.class.getResource("/Icon/cancel.png")));
		Lclose.setBounds(950, 10, 40, 40);
		Lclose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Lclose.addMouseListener(this);
		Info.add(Lclose);
		
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
		if(btn==btnCancel) {
			this.dispose();
			new Login();
		}else {
			String _nom,_prenom,_email,_sexe,_tel,_adresse,_date;
			_nom = nom.getText();
			_prenom = prenom.getText();
			_email = cin.getText();
			_tel = tel.getText();
			_adresse = adresse.getText();
			_date = age.getText();
			if(rhomme.isSelected()) {
				_sexe = "homme";
			}else {
				_sexe = "femme";
			}
			try {
				if(_nom.length() == 0) throw new Exception("Champs Nom est obligatoire");
				if(! ControleDeSaisie.isAlpha(_nom) ) throw new Exception("Nom Invalide !");
				
				if(_prenom.length() == 0) throw new Exception("Champs Prenom est obligatoire");
				if(! ControleDeSaisie.isAlpha(_nom) ) throw new Exception("Prenom Invalide !");
				
				if(_email.length() == 0) throw new Exception("Champs Email est obligatoire");
				if(! ControleDeSaisie.isEmailValid(_email) ) throw new Exception("Email Invalide !");
				
				if(_tel.length() == 0) throw new Exception("Champs Tel est obligatoire");
				if(! ControleDeSaisie.verifTel(_tel) ) throw new Exception("Tel Invalide !");
				
				if(_adresse.length() == 0) throw new Exception("Champs Adresse est obligatoire");
				if(! ControleDeSaisie.isAlpha(_nom) ) throw new Exception("Nom Invalide !");
				
				if(_nom.length() == 0) throw new Exception("Champs Nom est obligatoire");
				
				if(_date.length() == 0) throw new Exception("Champs Date est obligatoire");
				
				saveUser(_nom,_prenom,_email,_sexe,_tel,_adresse,_date);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
		
	}

	private void saveUser(String _nom, String _prenom, String _email, String _sexe, String _tel,
			String _adresse, String _date) {
		secretaire.setNom(_nom);
		secretaire.setPrenom(_prenom);
		secretaire.setEmail(_email);
		secretaire.setTel(_tel);
		secretaire.setAdresse(_adresse);
		secretaire.setDateNaissance(_date);
		secretaire.setSexe(_sexe);
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "insert into secretaire(cin,nom,prenom,email,tel,adresse,date_naissance,cindoc,sexe) values(?,?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, secretaire.getCin());
			pstmt.setString(2, _nom);
			pstmt.setString(3, _prenom);
			pstmt.setString(4, _email);
			pstmt.setString(5, _tel);
			pstmt.setString(6, _adresse);
			pstmt.setString(7, _date);
			pstmt.setString(8, secretaire.getRppsDocteur());
			pstmt.setString(9, _sexe);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ajout effectué avec succées !");
			
			this.dispose();
			new EspaceSecretaire(secretaire);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
