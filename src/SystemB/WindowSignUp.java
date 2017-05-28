package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class WindowSignUp extends JPanel implements ActionListener {

	JButton bSignUp, btnBack;
	JLabel lLogin, lPassword;
	JTextField tLogin, tImie, tnazwisko, tulica, tmiasto, tpesel, tkod, num_mieszk;
	JTextField textField;
	JPasswordField tPassword;
	ResultSet RS = null;
	PreparedStatement PST = null;
	Statement ST = null;
	JFrame register;
	Boolean CloseWindow = false;

	public WindowSignUp() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 131, 133, 20, 100, 100, 0 };
		gridBagLayout.rowHeights = new int[] { 31, 20, 14, 12, 5, 20, 26, 20, 26, 20, 26, 20, 26, 36, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblImi = new JLabel("Imie");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.fill = GridBagConstraints.BOTH;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 1;
		gbc_lblImi.gridy = 1;
		this.add(lblImi, gbc_lblImi);

		lLogin = new JLabel("Login");
		GridBagConstraints gbc_lLogin = new GridBagConstraints();
		gbc_lLogin.anchor = GridBagConstraints.SOUTH;
		gbc_lLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_lLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lLogin.gridx = 4;
		gbc_lLogin.gridy = 1;
		add(lLogin, gbc_lLogin);

		tImie = new JTextField();
		GridBagConstraints gbc_tImie = new GridBagConstraints();
		gbc_tImie.fill = GridBagConstraints.BOTH;
		gbc_tImie.insets = new Insets(0, 0, 5, 5);
		gbc_tImie.gridx = 1;
		gbc_tImie.gridy = 2;
		add(tImie, gbc_tImie);

		tLogin = new JTextField();
		GridBagConstraints gbc_tLogin = new GridBagConstraints();
		gbc_tLogin.anchor = GridBagConstraints.NORTH;
		gbc_tLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_tLogin.insets = new Insets(0, 0, 5, 5);
		gbc_tLogin.gridx = 4;
		gbc_tLogin.gridy = 2;
		add(tLogin, gbc_tLogin);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.WEST;
		gbc_lblNazwisko.fill = GridBagConstraints.VERTICAL;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 1;
		gbc_lblNazwisko.gridy = 3;
		this.add(lblNazwisko, gbc_lblNazwisko);

		lPassword = new JLabel("Haslo");
		GridBagConstraints gbc_lPassword = new GridBagConstraints();
		gbc_lPassword.fill = GridBagConstraints.BOTH;
		gbc_lPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lPassword.gridx = 4;
		gbc_lPassword.gridy = 3;
		add(lPassword, gbc_lPassword);

		tnazwisko = new JTextField();
		GridBagConstraints gbc_tnazwisko = new GridBagConstraints();
		gbc_tnazwisko.fill = GridBagConstraints.BOTH;
		gbc_tnazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_tnazwisko.gridx = 1;
		gbc_tnazwisko.gridy = 4;
		add(tnazwisko, gbc_tnazwisko);

		tPassword = new JPasswordField();
		GridBagConstraints gbc_tPassword = new GridBagConstraints();
		gbc_tPassword.anchor = GridBagConstraints.NORTH;
		gbc_tPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tPassword.gridx = 4;
		gbc_tPassword.gridy = 4;
		add(tPassword, gbc_tPassword);

		JLabel lblUlicaINr = new JLabel("Ulica i nr domu");
		GridBagConstraints gbc_lblUlicaINr = new GridBagConstraints();
		gbc_lblUlicaINr.anchor = GridBagConstraints.WEST;
		gbc_lblUlicaINr.fill = GridBagConstraints.VERTICAL;
		gbc_lblUlicaINr.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlicaINr.gridx = 1;
		gbc_lblUlicaINr.gridy = 5;
		this.add(lblUlicaINr, gbc_lblUlicaINr);

		JLabel lblNumerMieszkania = new JLabel("Numer mieszkania");
		GridBagConstraints gbc_lblNumerMieszkania = new GridBagConstraints();
		gbc_lblNumerMieszkania.anchor = GridBagConstraints.WEST;
		gbc_lblNumerMieszkania.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumerMieszkania.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerMieszkania.gridx = 2;
		gbc_lblNumerMieszkania.gridy = 5;
		this.add(lblNumerMieszkania, gbc_lblNumerMieszkania);

		tulica = new JTextField();
		GridBagConstraints gbc_tulica = new GridBagConstraints();
		gbc_tulica.fill = GridBagConstraints.BOTH;
		gbc_tulica.insets = new Insets(0, 0, 5, 5);
		gbc_tulica.gridx = 1;
		gbc_tulica.gridy = 6;
		add(tulica, gbc_tulica);

		num_mieszk = new JTextField();
		GridBagConstraints gbc_num_mieszk = new GridBagConstraints();
		gbc_num_mieszk.fill = GridBagConstraints.BOTH;
		gbc_num_mieszk.insets = new Insets(0, 0, 5, 5);
		gbc_num_mieszk.gridx = 2;
		gbc_num_mieszk.gridy = 6;
		add(num_mieszk, gbc_num_mieszk);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
		gbc_lblKodPocztowy.fill = GridBagConstraints.BOTH;
		gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodPocztowy.gridx = 1;
		gbc_lblKodPocztowy.gridy = 7;
		this.add(lblKodPocztowy, gbc_lblKodPocztowy);

		JLabel lblMiasto = new JLabel("Miasto");
		GridBagConstraints gbc_lblMiasto = new GridBagConstraints();
		gbc_lblMiasto.anchor = GridBagConstraints.WEST;
		gbc_lblMiasto.fill = GridBagConstraints.VERTICAL;
		gbc_lblMiasto.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiasto.gridx = 2;
		gbc_lblMiasto.gridy = 7;
		this.add(lblMiasto, gbc_lblMiasto);

		tkod = new JTextField();
		GridBagConstraints gbc_tkod = new GridBagConstraints();
		gbc_tkod.fill = GridBagConstraints.BOTH;
		gbc_tkod.insets = new Insets(0, 0, 5, 5);
		gbc_tkod.gridx = 1;
		gbc_tkod.gridy = 8;
		add(tkod, gbc_tkod);

		tmiasto = new JTextField();
		GridBagConstraints gbc_tmiasto = new GridBagConstraints();
		gbc_tmiasto.fill = GridBagConstraints.BOTH;
		gbc_tmiasto.insets = new Insets(0, 0, 5, 5);
		gbc_tmiasto.gridx = 2;
		gbc_tmiasto.gridy = 8;
		add(tmiasto, gbc_tmiasto);

		JLabel lblNrPesel = new JLabel("Nr pesel");
		GridBagConstraints gbc_lblNrPesel = new GridBagConstraints();
		gbc_lblNrPesel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNrPesel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrPesel.gridx = 1;
		gbc_lblNrPesel.gridy = 9;
		this.add(lblNrPesel, gbc_lblNrPesel);

		tpesel = new JTextField();
		GridBagConstraints gbc_tpesel = new GridBagConstraints();
		gbc_tpesel.fill = GridBagConstraints.BOTH;
		gbc_tpesel.insets = new Insets(0, 0, 5, 5);
		gbc_tpesel.gridx = 1;
		gbc_tpesel.gridy = 10;
		add(tpesel, gbc_tpesel);

		bSignUp = new JButton("Zarejestruj");
		GridBagConstraints gbc_bSignUp = new GridBagConstraints();
		gbc_bSignUp.anchor = GridBagConstraints.NORTH;
		gbc_bSignUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_bSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_bSignUp.gridx = 1;
		gbc_bSignUp.gridy = 12;
		add(bSignUp, gbc_bSignUp);
		bSignUp.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btnBack) {
			Window window = new Window();
			window.setVisible(true);
			register.dispose();
		}
		else if (source == bSignUp) {

			String imie = tImie.getText();
			String nazwisko = tnazwisko.getText();
			String login = tLogin.getText();
			String pass = new String(tPassword.getPassword());
			String ulica = tulica.getText();
			String mieszk = num_mieszk.getText();
			String kod = tkod.getText();
			String pesel = tpesel.getText();
			String miasto = tmiasto.getText();

			if (imie.length() > 0 && nazwisko.length() > 0 && login.length() > 0 && pass.length() > 0) {
				try {

					DatabaseConnection.conn = DatabaseConnection.ConnectDbs();
					String sql = "SELECT count(*) as cnt from users where login='" + login + "'";
					PST = DatabaseConnection.conn.prepareStatement(sql);
					RS = PST.executeQuery();
					Integer check = null;
					if (RS.next()) {
						check = RS.getInt("cnt");
					}

					if (check == null || check == 0) {
						sql = "INSERT INTO users (imie, nazwisko, login, haslo, ulica, num_miesz, kod_pocz, pesel, miasto) VALUES (?,?,?,md5(?),?,?,?,?,?)";
						PST = DatabaseConnection.conn.prepareStatement(sql);
						PST.setString(1, imie);
						PST.setString(2, nazwisko);
						PST.setString(3, login);
						PST.setString(4, pass);
						PST.setString(5, ulica);
						PST.setString(6, mieszk);
						PST.setString(7, kod);
						PST.setString(8, pesel);
						PST.setString(9, miasto);
						PST.executeUpdate();
						JOptionPane.showMessageDialog(this, "Zarejestrowano pomyślnie.");
						if (CloseWindow == true) {
							Window window = new Window();
							window.setVisible(true);
							register.dispose();
							DatabaseConnection.conn.close();
						}

					} else {
						JOptionPane.showMessageDialog(this, "Podany login już istnieje");
					}

				} catch (Exception a) {

					JOptionPane.showMessageDialog(null, "Błąd");

				}

			} else {
				JOptionPane.showMessageDialog(this, "Pola imie, nazwisko, login i hasło nie mogą być puste");
			}

		}

	}

	public void createFrame() {

		register = new JFrame();
		register.setSize(840, 600);
		register.setTitle("System Biblioteczny");

		register.getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0 };
		register.getContentPane().setLayout(gridBagLayout);

		btnBack = new JButton("Wróć");
		btnBack.setBounds(10, 10, 100, 20);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.EAST;
		gbc_btnBack.fill = GridBagConstraints.VERTICAL;
		gbc_btnBack.insets = new Insets(0, 0, 5, 0);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 0;
		register.getContentPane().add(btnBack, gbc_btnBack);
		btnBack.addActionListener(this);

		
		WindowSignUp reg = new WindowSignUp();
		GridBagConstraints gbc_reg = new GridBagConstraints();
		gbc_reg.fill = GridBagConstraints.BOTH;
		gbc_reg.gridx = 0;
		gbc_reg.gridy = 1;
		register.getContentPane().add(reg, gbc_reg);
		register.setVisible(true);
		reg.CloseWindow = true;
		reg.register=register;
	}

}
