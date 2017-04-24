package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.sql.*;

public class WindowSignUp extends JFrame implements ActionListener {

	JButton bSignUp;
	JLabel lLogin, lPassword;
	JTextField tLogin, tPassword, tImie, tnazwisko, tulica, tmiasto, tpesel, tkod, num_mieszk ;
	JTextField textField;
	Connection conn = null;
	ResultSet RS = null;
	PreparedStatement PST = null;

	public WindowSignUp() {
		setSize(800, 436);
		setTitle("System Biblioteczny - Rejestracja");
		setLayout(null);
		
		conn=DatabaseConnection.ConnectDbs();
		
		tImie = new JTextField();
		tImie.setBounds(15, 56, 115, 26);
		add(tImie);
		
		JLabel lblImi = new JLabel("Imi\u0119");
		lblImi.setBounds(15, 31, 69, 20);
		getContentPane().add(lblImi);
		
		tnazwisko = new JTextField();
		tnazwisko.setBounds(15, 110, 146, 26);
		add(tnazwisko);
		
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(15, 85, 69, 20);
		getContentPane().add(lblNazwisko);
		
		tulica = new JTextField();
		tulica.setBounds(15, 163, 146, 26);
		add(tulica);
		
		JLabel lblUlicaINr = new JLabel("Ulica i nr domu");
		lblUlicaINr.setBounds(15, 138, 146, 20);
		getContentPane().add(lblUlicaINr);
		
		num_mieszk = new JTextField();
		num_mieszk.setBounds(15, 216, 77, 26);
		add(num_mieszk);
		
		JLabel lblNumerMieszkania = new JLabel("Numer mieszkania");
		lblNumerMieszkania.setBounds(15, 194, 146, 20);
		getContentPane().add(lblNumerMieszkania);
		
		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		lblKodPocztowy.setBounds(15, 249, 103, 20);
		getContentPane().add(lblKodPocztowy);
		
		tkod = new JTextField();
		tkod.setBounds(15, 274, 103, 26);
		add(tkod);
		
		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(148, 249, 69, 20);
		getContentPane().add(lblMiasto);
		
		tmiasto = new JTextField();
		tmiasto.setBounds(136, 274, 131, 26);
		add(tmiasto);
		
		JLabel lblNrPesel = new JLabel("Nr pesel");
		lblNrPesel.setBounds(15, 316, 69, 20);
		getContentPane().add(lblNrPesel);
		
		tpesel = new JTextField();
		tpesel.setBounds(15, 340, 222, 26);
		add(tpesel);

		bSignUp = new JButton("Zarejestruj");
		bSignUp.setBounds(600, 300, 100, 20);
		add(bSignUp);
		bSignUp.addActionListener(this);

		lLogin = new JLabel("Login");
		lLogin.setBounds(400, 50, 100, 20);
		add(lLogin);

		lPassword = new JLabel("Haslo");
		lPassword.setBounds(400, 90, 100, 20);
		add(lPassword);

		tLogin = new JTextField();
		tLogin.setBounds(400, 70, 100, 20);
		add(tLogin);

		tPassword = new JTextField();
		tPassword.setBounds(400, 110, 100, 20);
		add(tPassword);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		String imie=tImie.getText();
		String nazwisko=tnazwisko.getText();
		String login=tLogin.getText();
		String pass=tPassword.getText();
		String ulica=tulica.getText();
		String mieszk=num_mieszk.getText();
		String kod=tkod.getText();
		String pesel=tpesel.getText();
		String miasto=tmiasto.getText();
		
		try{
		
			String sql = "INSERT INTO users (imie, nazwisko, login, haslo, ulica, num_miesz, kod_pocz, pesel, miasto) VALUES (?,?,?,?,?,?,?,?,?)";
			PST=conn.prepareStatement(sql);
			PST.setString(1,imie);
			PST.setString(2,nazwisko);
			PST.setString(3,login);
			PST.setString(4,pass);
			PST.setString(5,ulica);
			PST.setString(6,mieszk);
			PST.setString(7,kod);
			PST.setString(8,pesel);
			PST.setString(9,miasto);
			PST.executeUpdate(); 
			JOptionPane.showMessageDialog(this,"Zarejestrowano pomyślnie.");
			
			
			
		}catch(Exception a){
			
			JOptionPane.showMessageDialog(null,a);
			
		}
		
		if(source==bSignUp){
			
			dispose();
		}

	}

}
