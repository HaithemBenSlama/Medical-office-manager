
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;


public class RegisterMed extends JFrame implements MouseListener,ActionListener {
	private JLabel Lclose;
	private JTextField nom;
	private JTextField prenom;
	private JTextField cin;
	private JTextField rpps;
	private JTextField tel;
	private JTextField adresse;
	private JTextField specialite;
	private JButton btnCreate,btnCancel;

	private String medCin;
	
	public RegisterMed(String cinn) {
		medCin = cinn;
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		Color BackgroundColor=new Color(224, 236, 222);
		JPanel Login = new JPanel();
		Login.setBorder(null);
		Login.setPreferredSize(new Dimension(580,600));
		Login.setBackground(new Color(205, 224, 201));
		getContentPane().add(Login);
		Login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Information Personnel M\u00E9decin");
		lblNewLabel.setBounds(31, 21, 894, 70);
		lblNewLabel.setFont(new Font("Anonymous Pro", Font.BOLD, 41));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Login.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(105, 101, 800, 460);
		panel.setBackground(BackgroundColor);
		Login.add(panel);
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
		
		JLabel Lrpps = new JLabel("RPPS:");
		Lrpps.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lrpps.setBounds(50, 327, 59, 21);
		panel.add(Lrpps);
		
		rpps = new JTextField();
		rpps.setBounds(50, 358, 260, 40);
		rpps.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(rpps);
		rpps.setColumns(10);
		
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
		
		JLabel Lspecialite = new JLabel("Sp\u00E9cialit\u00E9:");
		Lspecialite.setFont(new Font("Oswald", Font.PLAIN, 17));
		Lspecialite.setBounds(460, 231, 98, 21);
		panel.add(Lspecialite);
		
		specialite = new JTextField();
		specialite.setBounds(460, 262, 260, 40);
		specialite.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(specialite);
		specialite.setColumns(10);
		
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
		}else {
			String _nom,_prenom,_email,_rpps,_tel,_adresse,_spec;
			_nom = nom.getText();
			_prenom = prenom.getText();
			_email = cin.getText();
			_rpps= rpps.getText();
			_tel= tel.getText();
			_spec = specialite.getText();
			_adresse = adresse.getText();
			
			try {
				if(_nom.length() == 0) throw new Exception("Champ Nom est obligatoire");
				if(! ControleDeSaisie.isAlpha(_nom)) throw new Exception("Nom est Invalide !");

				if(_prenom.length() == 0) throw new Exception("Champ Prenom est obligatoire");
				if(! ControleDeSaisie.isAlpha(_prenom)) throw new Exception("Prenom est Invalide !");
				
				if(_email.length() == 0) throw new Exception("Champ Email est obligatoire");
				if(! ControleDeSaisie.isEmailValid(_email)) throw new Exception("Email est Invalide !");
				
				if(_rpps.length() == 0) throw new Exception("Champ RPPS est obligatoire");
				if(! ControleDeSaisie.verifRPPS(_rpps)) throw new Exception("RPPS est Invalide !");
				
				if(_tel.length() == 0) throw new Exception("Champ Tel est obligatoire");
				if(! ControleDeSaisie.verifTel(_tel)) throw new Exception("Tel est Invalide !");
				
				if(_adresse.length() == 0) throw new Exception("Champ Adresse est obligatoire");
				
				if(_spec.length() == 0) throw new Exception("Champ Specialité est obligatoire");
				if(! ControleDeSaisie.isAlpha(_spec)) throw new Exception("Spécialité est Invalide !");
				
				
				registerMed(medCin ,_nom,_prenom,_email,_rpps,_tel,_adresse,_spec);
				Docteur docteur = new Docteur(medCin,_nom,_prenom,_email,_tel,_adresse,_spec,_rpps);
				this.dispose();
				new MedecinInterface(docteur);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	private void registerMed(String medCin2, String _nom, String _prenom, String _email, String _rpps, String _tel,String _adresse, String _spec) {
		String jdbcUrl = "jdbc:sqlite:projet.db";
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);
			String sql = "insert into docteur(cin,nom,prenom,email,tel,adresse,specialite,numerorpps) values(?,?,?,?,?,?,?,?);";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, medCin);
			pstmt.setString(2, _nom);
			pstmt.setString(3, _prenom);
			pstmt.setString(4, _email);
			pstmt.setString(5, _tel);
			pstmt.setString(6, _adresse);
			pstmt.setString(7, _spec);
			pstmt.setString(8, _rpps);
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ajout effectué avec succées !");
			Docteur docteur = new Docteur(medCin2,_nom,_prenom,_email,_tel,_adresse,_spec,_rpps);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
