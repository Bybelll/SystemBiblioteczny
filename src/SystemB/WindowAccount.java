package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import SystemB.Window;
import java.sql.*;
import java.util.Vector;

public class WindowAccount extends JPanel implements ActionListener {

	JFrame frame;
	private JTable table, table2;
	JButton btnWyloguj;
	JButton btnWr, btnAcc, btnZmieHaso;
	JTextField imie, nazwisko, ulica, numm, kod, miasto, pesel;
	Connection conn;
	ResultSet rs;
	Statement PST;
	PreparedStatement PS = null;
	JScrollPane tablica, aktualne;

	public WindowAccount() {
		this.setLayout(null);

		conn = DatabaseConnection.ConnectDbs();

		imie = new JTextField();
		imie.setBounds(15, 60, 115, 25);
		this.add(imie);

		JLabel lblImi = new JLabel("Imi\u0119");
		lblImi.setBounds(15, 40, 69, 20);
		this.add(lblImi);

		nazwisko = new JTextField();
		nazwisko.setBounds(15, 110, 146, 25);
		this.add(nazwisko);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(15, 90, 69, 20);
		this.add(lblNazwisko);

		ulica = new JTextField();
		ulica.setBounds(15, 160, 146, 25);
		this.add(ulica);

		JLabel lblUlicaINr = new JLabel("Ulica i nr domu");
		lblUlicaINr.setBounds(15, 140, 146, 20);
		this.add(lblUlicaINr);

		numm = new JTextField();
		numm.setBounds(15, 210, 77, 25);
		this.add(numm);

		JLabel lblNumerMieszkania = new JLabel("Numer mieszkania");
		lblNumerMieszkania.setBounds(15, 190, 146, 20);
		this.add(lblNumerMieszkania);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		lblKodPocztowy.setBounds(15, 240, 103, 20);
		this.add(lblKodPocztowy);

		kod = new JTextField();
		kod.setBounds(15, 260, 103, 25);
		this.add(kod);

		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(148, 240, 69, 20);
		this.add(lblMiasto);

		miasto = new JTextField();
		miasto.setBounds(136, 260, 131, 25);
		this.add(miasto);

		JLabel lblNrPesel = new JLabel("Nr pesel");
		lblNrPesel.setBounds(15, 290, 69, 20);
		this.add(lblNrPesel);

		pesel = new JTextField();
		pesel.setBounds(15, 310, 222, 25);
		this.add(pesel);

		JLabel lblAktualneWyp = new JLabel("Aktualne wypo�yczenia");
		lblAktualneWyp.setHorizontalAlignment(SwingConstants.CENTER);
		lblAktualneWyp.setBounds(470, 10, 120, 26);
		this.add(lblAktualneWyp);

		aktualne = new JScrollPane();
		aktualne.setSize(450, 115);
		aktualne.setLocation(300, 35);
		this.add(aktualne);

		JLabel lblHistoriaWypoycze = new JLabel("Historia wypo�ycze�");
		lblHistoriaWypoycze.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistoriaWypoycze.setBounds(470, 155, 120, 26);
		this.add(lblHistoriaWypoycze);

		tablica = new JScrollPane();
		tablica.setSize(450, 200);
		tablica.setLocation(300, 180);
		this.add(tablica);

		// table = new JTable();
		// table.setBounds(407, 56, 358, 226);
		// this.add(table);
		//
		
		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setBounds(660, 0, 115, 29);
		this.add(btnWyloguj);
		btnWyloguj.addActionListener(this);

		btnWr = new JButton("Wr��");
		btnWr.setBounds(15, 10, 115, 29);
		this.add(btnWr);
		btnWr.addActionListener(this);

		btnAcc = new JButton("Zatwierdz zmiany");
		btnAcc.setBounds(15, 350, 180, 35);
		this.add(btnAcc);
		btnAcc.addActionListener(this);
		
		btnZmieHaso = new JButton("Zmie\u0144 has\u0142o");
		btnZmieHaso.setBounds(148, 9, 133, 29);
		this.add(btnZmieHaso);
		btnZmieHaso.addActionListener(this);

		wysw();
		loan();
		loan_current();

		try {
			rs.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			PST.close();
		} catch (Exception e) {
			/* ignored */ }
		try {
			conn.close();
		} catch (Exception e) {
			/* ignored */ }
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

	public void loan() {
		try {

			String sql = "select tytul, status, data from historia where user='" + WindowSignIn.Mlogin
					+ "' order by data desc";
			PST = conn.createStatement();
			rs = PST.executeQuery(sql);
		}

		catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table = new JTable(buildTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tablica.setViewportView(table);
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();

	}

	public void loan_current() {
		try {

			String sql = "select tytul, data from loan_list_open where status_id=3 and user='" + WindowSignIn.Mlogin
					+ "' order by data desc";
			PST = conn.createStatement();
			rs = PST.executeQuery(sql);
		}

		catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table2 = new JTable(buildTableModel(rs));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		aktualne.setViewportView(table2);
		aktualne.validate();
		aktualne.revalidate();
		aktualne.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnWyloguj) {
			//dispose();
			//Window Window = new Window();
			//Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Window.setVisible(true);
		}

		else if (source == btnWr) {
			
			//WindowSemiFin WindowMain = new WindowSemiFin();
			//WindowMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//WindowMain.setVisible(true);
		}	else if (source == btnZmieHaso) {
			
			PassChange Window = new PassChange();
			Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Window.setVisible(true);
			
			
			
		} else if (source == btnAcc) {

			String simie = imie.getText();
			String snazwisko = nazwisko.getText();
			String sulica = ulica.getText();
			String smieszk = numm.getText();
			String skod = kod.getText();
			String spesel = pesel.getText();
			String smiasto = miasto.getText();

			try {

				String sql = "UPDATE users SET imie= ?, nazwisko=?, ulica=?, num_miesz=?, kod_pocz=?, pesel=?, miasto=? WHERE login='"
						+ WindowSignIn.Mlogin + "'";
				;
				PS = conn.prepareStatement(sql);
				PS.setString(1, simie);
				PS.setString(2, snazwisko);
				PS.setString(3, sulica);
				PS.setString(4, smieszk);
				PS.setString(5, skod);
				PS.setString(6, spesel);
				PS.setString(7, smiasto);
				PS.executeUpdate();
				JOptionPane.showMessageDialog(this, "Zaktualizowano pomy�lnie.");

			} catch (Exception a) {

				JOptionPane.showMessageDialog(null, a);

			}
		}

	}

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		// columnNames.add("ID");
		// columnNames.add("Imie Autora");
		// columnNames.add("Nazwisko autora");
		// columnNames.add("Tytu�");
		// columnNames.add("Rok publikacji");
		// columnNames.add("ISBN");
		// columnNames.add("Wydawnictwo");
		// columnNames.add("Kategoria");
		// columnNames.add("Status");
		//

		int columnCount = metaData.getColumnCount();

		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new SearchTable(data, columnNames);
	}
}
