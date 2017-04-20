package SystemB;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;

import SystemB.Window;
import java.sql.*;

public class WindowAccount extends JFrame implements ActionListener {

	JFrame frame;
	private JTable table;
	JButton btnWyloguj;
	JButton btnWr, btnAcc;
	JTextField imie, nazwisko, ulica, numm, kod, miasto, pesel;
	Connection conn;
	ResultSet rs;
	Statement PST;
	PreparedStatement PS = null;

	public WindowAccount() {
		getContentPane().setLayout(null);

		conn = DatabaseConnection.ConnectDbs();

		imie = new JTextField();
		imie.setBounds(15, 56, 115, 26);
		add(imie);

		JLabel lblImi = new JLabel("Imi\u0119");
		lblImi.setBounds(15, 31, 69, 20);
		getContentPane().add(lblImi);

		nazwisko = new JTextField();
		nazwisko.setBounds(15, 110, 146, 26);
		add(nazwisko);
		

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(15, 85, 69, 20);
		getContentPane().add(lblNazwisko);

		ulica = new JTextField();
		ulica.setBounds(15, 163, 146, 26);
		add(ulica);

		JLabel lblUlicaINr = new JLabel("Ulica i nr domu");
		lblUlicaINr.setBounds(15, 138, 146, 20);
		getContentPane().add(lblUlicaINr);

		numm = new JTextField();
		numm.setBounds(15, 216, 77, 26);
		add(numm);

		JLabel lblNumerMieszkania = new JLabel("Numer mieszkania");
		lblNumerMieszkania.setBounds(15, 194, 146, 20);
		getContentPane().add(lblNumerMieszkania);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		lblKodPocztowy.setBounds(15, 249, 103, 20);
		getContentPane().add(lblKodPocztowy);

		kod = new JTextField();
		kod.setBounds(15, 274, 103, 26);
		add(kod);

		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(148, 249, 69, 20);
		getContentPane().add(lblMiasto);

		miasto = new JTextField();
		miasto.setBounds(136, 274, 131, 26);
		add(miasto);

		JLabel lblNrPesel = new JLabel("Nr pesel");
		lblNrPesel.setBounds(15, 316, 69, 20);
		getContentPane().add(lblNrPesel);

		pesel = new JTextField();
		pesel.setBounds(15, 340, 222, 26);
		add(pesel);

		JLabel lblHistoriaWypoycze = new JLabel("Historia Wypo\u017Cycze\u0144");
		lblHistoriaWypoycze.setBounds(510, 28, 183, 26);
		getContentPane().add(lblHistoriaWypoycze);

		table = new JTable();
		table.setBounds(407, 56, 358, 226);
		getContentPane().add(table);
		setSize(800, 436);
		setTitle("Twoje Konto");
		getContentPane().setLayout(null);

		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setBounds(648, 0, 115, 29);
		getContentPane().add(btnWyloguj);
		btnWyloguj.addActionListener(this);

		btnWr = new JButton("Wr\u00F3\u0107");
		btnWr.setBounds(3, 0, 115, 29);
		getContentPane().add(btnWr);
		btnWr.addActionListener(this);
		
		btnAcc = new JButton("Zatwierdz zmiany");
		btnAcc.setBounds(350, 316, 180, 35);
		getContentPane().add(btnAcc);
		btnAcc.addActionListener(this);

		wysw();

	}

	public void wysw() {

		try {

			String sql = "SELECT * FROM users WHERE login='" + WindowSignIn.Mlogin + "'";
			PST = conn.createStatement();
			rs = PST.executeQuery(sql);
			rs.next();
			String imiee = rs.getString("imie");
			imie.setText(imiee);
			String nazw = rs.getString("nazwisko");
			nazwisko.setText(nazw);
			String ul = rs.getString("ulica");
			ulica.setText(ul);
			String num = rs.getString("num_miesz");
			numm.setText(num);
			String kodd = rs.getString("kod_pocz");
			kod.setText(kodd);
			String mias = rs.getString("miasto");
			miasto.setText(mias);
			String pes = rs.getString("pesel");
			pesel.setText(pes);
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnWyloguj) {
			dispose();
			Window Window = new Window();
			Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Window.setVisible(true);
		}

		else if (source == btnWr) {
			dispose();
			WindowMain WindowMain = new WindowMain();
			WindowMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			WindowMain.setVisible(true);
		}
		else if (source == btnAcc) {
			
			
			
			String simie=imie.getText();
			String snazwisko=nazwisko.getText();
			String sulica=ulica.getText();
			String smieszk=numm.getText();
			String skod=kod.getText();
			String spesel=pesel.getText();
			String smiasto=miasto.getText();
			
			try{
			
				String sql = "UPDATE users SET imie= ?, nazwisko=?, ulica=?, num_miesz=?, kod_pocz=?, pesel=?, miasto=? WHERE login='" + WindowSignIn.Mlogin + "'";;
				PS=conn.prepareStatement(sql);
				PS.setString(1,simie);
				PS.setString(2,snazwisko);
				PS.setString(3,sulica);
				PS.setString(4,smieszk);
				PS.setString(5,skod);
				PS.setString(6,spesel);
				PS.setString(7,smiasto);
				PS.executeUpdate(); 
				JOptionPane.showMessageDialog(this,"Zaktualizowano pomyœlnie.");
				
				
				
			}catch(Exception a){
				
				JOptionPane.showMessageDialog(null,a);
				
			}
		}

	}

}
