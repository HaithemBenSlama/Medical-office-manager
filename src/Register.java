
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class Register extends JFrame implements MouseListener,ActionListener {
	private JTextField nom;
	private JPasswordField prenom;
	private JRadioButton Medecin,Secretaire;
	private ButtonGroup group;
	private JLabel Lclose;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JPasswordField mail;
	private JTextField code;


	public Register() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		Color BackgroundColor=new Color(224, 236, 222);
		JPanel Login = new JPanel();
		Login.setBorder(null);
		Login.setPreferredSize(new Dimension(580,600));
		Login.setBackground(new Color(205, 224, 201));
		getContentPane().add(Login);
		Login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cr\u00E9er Compte");
		lblNewLabel.setBounds(0, 21, 585, 70);
		lblNewLabel.setFont(new Font("Anonymous Pro", Font.BOLD, 41));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Login.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(110, 101, 374, 431);
		panel.setBackground(BackgroundColor);
		Login.add(panel);
		panel.setLayout(null);
		
		JLabel Lusername = new JLabel("Cin:");
		Lusername.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lusername.setBounds(50, 10, 133, 40);
		panel.add(Lusername);
		
		nom = new JTextField();
		nom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setBounds(50, 49, 260, 40);
		panel.add(nom);
		nom.setColumns(10);
		
		JLabel Lpass = new JLabel("Mot de Passe:");
		Lpass.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lpass.setBounds(50, 87, 122, 43);
		panel.add(Lpass);
		
		btnCancel = new JButton("Annuler");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancel.setBounds(42, 381, 130, 40);
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.addActionListener(this);
		panel.add(btnCancel);
		
		btnConfirm = new JButton("Confirmer");
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirm.setBounds(202, 381, 130, 40);
		btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConfirm.addActionListener(this);
		panel.add(btnConfirm);
		
		Medecin = new JRadioButton("M\u00E9decin");
		Medecin.setSelected(true);
		panel.add(Medecin);
		Medecin.setFont(new Font("Oswald", Font.PLAIN, 17));
		Medecin.setBackground(BackgroundColor);
		Medecin.setBounds(60, 254, 93, 21);
		
		Secretaire = new JRadioButton("S\u00E9cretaire");
		panel.add(Secretaire);
		Secretaire.setFont(new Font("Oswald", Font.PLAIN, 17));
		Secretaire.setBounds(198, 254, 140, 21);
		Secretaire.setBackground(BackgroundColor);
		group=new ButtonGroup();
		group.add(Secretaire);
		group.add(Medecin);
		
		prenom = new JPasswordField();
		prenom.setHorizontalAlignment(SwingConstants.CENTER);
		prenom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		prenom.setBounds(50, 127, 260, 40);
		panel.add(prenom);
		
		JLabel Lmail = new JLabel("Confirmer mot de passe:");
		Lmail.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lmail.setBounds(50, 177, 200, 21);
		panel.add(Lmail);
		
		mail = new JPasswordField();
		mail.setBounds(50, 208, 260, 40);
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(mail);
		mail.setColumns(10);
		
		JLabel lRpps = new JLabel("Matricule M\u00E9decin: [Cas S\u00E9cretaire]");
		lRpps.setFont(new Font("Oswald", Font.PLAIN, 17));
		lRpps.setBounds(50, 290, 260, 23);
		panel.add(lRpps);
		
		code = new JTextField();
		code.setBounds(50, 320, 260, 40);
		code.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(code);
		code.setColumns(10);
		
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
		if(btn==btnCancel) {
			this.dispose();
			new Login();
		}
		if(btn==btnConfirm) {
			String cin = nom.getText();
			String password = prenom.getText();
			String confirmPassword = mail.getText();
			String codem = code.getText();
			
			try {					
				if(cin.length() == 0) throw new Exception("champs CIN est obligatoire !");
				if(!ControleDeSaisie.verifCin(cin)) throw new Exception("CIN est invalide !");
				if((password.length() == 0)|| confirmPassword.length() == 0) throw new Exception("Tous les champs sont obligatoires !");
				if (password.equals(confirmPassword) == false) throw new Exception("Les mots de passe ne sont pas identiques !");
				if(Secretaire.isSelected() && codem.length() == 0) throw new Exception("champs Codem est obligatoire pour la secretaire!");
				
				saveNewUser(cin,password,codem);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());

			}
				
			}	
	}

	private void saveNewUser(String cin, String password, String rpps) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sqlSelect = "select * from authentification where login = '" +cin+ "';";

			String sql = "insert into authentification(login,password) values(?,?);";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sqlSelect);
			int nb = 0;
			while(result.next()) {
				nb++;
			}
			
			if(nb != 0) {
				JOptionPane.showMessageDialog(null, "Utulisateur existe deja");
			}else {	
				
				if(Secretaire.isSelected()) {
					if(!medExiste(rpps)) {
						JOptionPane.showMessageDialog(null, "Docteur avec RPPS = " + rpps + " n'existe pas !");
					}else {
						PreparedStatement pstmt = connection.prepareStatement(sql);
						pstmt.setString(1, cin);
						pstmt.setString(2, password);
						pstmt.executeUpdate();
						this.dispose();
						new RegisterSec(cin,rpps);
					}
				}else {
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setString(1, cin);
					pstmt.setString(2, password);
					pstmt.executeUpdate();
					this.dispose();
					new RegisterMed(cin);
				}
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean medExiste(String rpps) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		boolean existe = false;
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "select * from docteur where numerorpps = '"+rpps+"';";
			
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
}
