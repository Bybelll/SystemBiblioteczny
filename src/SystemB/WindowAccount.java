package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import SystemB.Window;

import java.sql.*;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class WindowAccount extends JPanel implements ActionListener {

	JFrame frame;
	private JTable table, table2;
	JButton btnAcc, btnZmieHaso;
	JTextField imie, nazwisko, ulica, numm, kod, miasto, pesel;
	JScrollPane tablica, aktualne;

	public WindowAccount(String userID) {

		try {
			if (DatabaseConnection.conn == null || DatabaseConnection.conn.isClosed()) {
				DatabaseConnection.conn = DatabaseConnection.ConnectDbs();
			}
			DatabaseConnection.PST = DatabaseConnection.conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 12, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		// gridBagLayout.columnWidths = new int[]{120, 7, 119, 33, 290, 0};
		// gridBagLayout.rowHeights = new int[] { 3, 20, 25, 20, 25, 10, 5, 25,
		// 20, 25, 20, 25, 20, 25, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 0.1, 0.0, 0.1, 0.0, 0.8 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, };
		setLayout(gridBagLayout);

		JLabel lblAktualneWyp = new JLabel("Aktualne wypożyczenia");
		lblAktualneWyp.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAktualneWyp = new GridBagConstraints();
		gbc_lblAktualneWyp.insets = new Insets(0, 0, 5, 0);
		gbc_lblAktualneWyp.gridx = 4;
		gbc_lblAktualneWyp.gridy = 0;
		this.add(lblAktualneWyp, gbc_lblAktualneWyp);

		aktualne = new JScrollPane();
		GridBagConstraints gbc_aktualne = new GridBagConstraints();
		gbc_aktualne.anchor = GridBagConstraints.WEST;
		gbc_aktualne.fill = GridBagConstraints.BOTH;
		gbc_aktualne.insets = new Insets(0, 0, 5, 0);
		gbc_aktualne.gridheight = 4;
		gbc_aktualne.gridx = 4;
		gbc_aktualne.gridy = 1;
		this.add(aktualne, gbc_aktualne);

		JLabel lblImi = new JLabel("Imi\u0119");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.anchor = GridBagConstraints.WEST;
		gbc_lblImi.fill = GridBagConstraints.VERTICAL;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 0;
		gbc_lblImi.gridy = 0;
		this.add(lblImi, gbc_lblImi);

		imie = new JTextField();
		GridBagConstraints gbc_imie = new GridBagConstraints();
		gbc_imie.fill = GridBagConstraints.BOTH;
		gbc_imie.insets = new Insets(0, 0, 5, 5);
		gbc_imie.gridx = 0;
		gbc_imie.gridy = 1;
		this.add(imie, gbc_imie);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.WEST;
		gbc_lblNazwisko.fill = GridBagConstraints.VERTICAL;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 0;
		gbc_lblNazwisko.gridy = 2;
		this.add(lblNazwisko, gbc_lblNazwisko);

		nazwisko = new JTextField();
		GridBagConstraints gbc_nazwisko = new GridBagConstraints();
		gbc_nazwisko.fill = GridBagConstraints.BOTH;
		gbc_nazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_nazwisko.gridx = 0;
		gbc_nazwisko.gridy = 3;
		this.add(nazwisko, gbc_nazwisko);

		JLabel lblUlicaINr = new JLabel("Ulica i nr domu");
		GridBagConstraints gbc_lblUlicaINr = new GridBagConstraints();
		gbc_lblUlicaINr.anchor = GridBagConstraints.WEST;
		gbc_lblUlicaINr.fill = GridBagConstraints.VERTICAL;
		gbc_lblUlicaINr.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlicaINr.gridx = 0;
		gbc_lblUlicaINr.gridy = 4;
		this.add(lblUlicaINr, gbc_lblUlicaINr);

		JLabel lblHistoriaWypoycze = new JLabel("Historia wypo�ycze�");
		lblHistoriaWypoycze.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHistoriaWypoycze = new GridBagConstraints();
		gbc_lblHistoriaWypoycze.fill = GridBagConstraints.VERTICAL;
		gbc_lblHistoriaWypoycze.insets = new Insets(0, 0, 5, 0);
		gbc_lblHistoriaWypoycze.gridx = 4;
		gbc_lblHistoriaWypoycze.gridy = 5;
		this.add(lblHistoriaWypoycze, gbc_lblHistoriaWypoycze);

		ulica = new JTextField();
		GridBagConstraints gbc_ulica = new GridBagConstraints();
		gbc_ulica.anchor = GridBagConstraints.WEST;
		gbc_ulica.fill = GridBagConstraints.BOTH;
		gbc_ulica.insets = new Insets(0, 0, 5, 5);
		gbc_ulica.gridx = 0;
		gbc_ulica.gridy = 5;
		this.add(ulica, gbc_ulica);

		tablica = new JScrollPane();
		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.insets = new Insets(0, 0, 5, 0);
		gbc_tablica.anchor = GridBagConstraints.WEST;
		gbc_tablica.fill = GridBagConstraints.BOTH;
		gbc_tablica.gridheight = 8;
		gbc_tablica.gridx = 4;
		gbc_tablica.gridy = 6;
		this.add(tablica, gbc_tablica);

		JLabel lblNumerMieszkania = new JLabel("Numer mieszkania");
		GridBagConstraints gbc_lblNumerMieszkania = new GridBagConstraints();
		gbc_lblNumerMieszkania.anchor = GridBagConstraints.WEST;
		gbc_lblNumerMieszkania.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumerMieszkania.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerMieszkania.gridx = 0;
		gbc_lblNumerMieszkania.gridy = 6;
		this.add(lblNumerMieszkania, gbc_lblNumerMieszkania);

		numm = new JTextField();
		GridBagConstraints gbc_numm = new GridBagConstraints();
		gbc_numm.anchor = GridBagConstraints.WEST;
		gbc_numm.fill = GridBagConstraints.BOTH;
		gbc_numm.insets = new Insets(0, 0, 5, 5);
		gbc_numm.gridx = 0;
		gbc_numm.gridy = 7;
		this.add(numm, gbc_numm);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		GridBagConstraints gbc_lblKodPocztowy = new GridBagConstraints();
		gbc_lblKodPocztowy.fill = GridBagConstraints.BOTH;
		gbc_lblKodPocztowy.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodPocztowy.gridx = 0;
		gbc_lblKodPocztowy.gridy = 8;
		this.add(lblKodPocztowy, gbc_lblKodPocztowy);

		JLabel lblMiasto = new JLabel("Miasto");
		GridBagConstraints gbc_lblMiasto = new GridBagConstraints();
		gbc_lblMiasto.anchor = GridBagConstraints.WEST;
		gbc_lblMiasto.fill = GridBagConstraints.VERTICAL;
		gbc_lblMiasto.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiasto.gridx = 2;
		gbc_lblMiasto.gridy = 8;
		this.add(lblMiasto, gbc_lblMiasto);

		kod = new JTextField();
		GridBagConstraints gbc_kod = new GridBagConstraints();
		gbc_kod.fill = GridBagConstraints.BOTH;
		gbc_kod.insets = new Insets(0, 0, 5, 5);
		gbc_kod.gridx = 0;
		gbc_kod.gridy = 9;
		this.add(kod, gbc_kod);

		miasto = new JTextField();
		GridBagConstraints gbc_miasto = new GridBagConstraints();
		gbc_miasto.fill = GridBagConstraints.BOTH;
		gbc_miasto.insets = new Insets(0, 0, 5, 5);
		gbc_miasto.gridx = 2;
		gbc_miasto.gridy = 9;
		this.add(miasto, gbc_miasto);

		JLabel lblNrPesel = new JLabel("Nr pesel");
		GridBagConstraints gbc_lblNrPesel = new GridBagConstraints();
		gbc_lblNrPesel.anchor = GridBagConstraints.WEST;
		gbc_lblNrPesel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNrPesel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrPesel.gridx = 0;
		gbc_lblNrPesel.gridy = 10;
		this.add(lblNrPesel, gbc_lblNrPesel);

		pesel = new JTextField();
		GridBagConstraints gbc_pesel = new GridBagConstraints();
		gbc_pesel.fill = GridBagConstraints.BOTH;
		gbc_pesel.insets = new Insets(0, 0, 5, 5);
		gbc_pesel.gridx = 0;
		gbc_pesel.gridy = 11;
		this.add(pesel, gbc_pesel);

		btnAcc = new JButton("Zatwierdz zmiany");
		GridBagConstraints gbc_btnAcc = new GridBagConstraints();
		gbc_btnAcc.fill = GridBagConstraints.VERTICAL;
		gbc_btnAcc.insets = new Insets(0, 0, 5, 5);
		gbc_btnAcc.gridx = 0;
		gbc_btnAcc.gridy = 13;
		this.add(btnAcc, gbc_btnAcc);
		btnAcc.addActionListener(this);

		btnZmieHaso = new JButton("Zmie\u0144 has\u0142o");
		GridBagConstraints gbc_btnZmieHaso = new GridBagConstraints();
		gbc_btnZmieHaso.fill = GridBagConstraints.BOTH;
		gbc_btnZmieHaso.insets = new Insets(0, 0, 5, 5);
		gbc_btnZmieHaso.gridx = 2;
		gbc_btnZmieHaso.gridy = 13;
		this.add(btnZmieHaso, gbc_btnZmieHaso);
		btnZmieHaso.addActionListener(this);

		wysw(userID);
		loan(userID);
		loan_current(userID);

		try {
			DatabaseConnection.rs.close();
			DatabaseConnection.conn.close();
			DatabaseConnection.PST.close();
		} catch (Exception e) {
			/* ignored */
		}

	}

	public void wysw(String userID) {

		try {

			String sql = "SELECT * FROM users WHERE user_id='" + userID + "'";
			DatabaseConnection.rs = DatabaseConnection.PST.executeQuery(sql);
			DatabaseConnection.rs.next();
			String imiee = DatabaseConnection.rs.getString("imie");
			imie.setText(imiee);
			String nazw = DatabaseConnection.rs.getString("nazwisko");
			nazwisko.setText(nazw);
			String ul = DatabaseConnection.rs.getString("ulica");
			ulica.setText(ul);
			String num = DatabaseConnection.rs.getString("num_miesz");
			numm.setText(num);
			String kodd = DatabaseConnection.rs.getString("kod_pocz");
			kod.setText(kodd);
			String mias = DatabaseConnection.rs.getString("miasto");
			miasto.setText(mias);
			String pes = DatabaseConnection.rs.getString("pesel");
			pesel.setText(pes);

		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);

		}
	}

	public void loan(String userID) {
		try {

			String sql = "select tytul, status, data from historia where user_id='" + userID + "' order by data desc";
			DatabaseConnection.rs = DatabaseConnection.PST.executeQuery(sql);
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table = new JTable(buildTableModel(DatabaseConnection.rs));
			tablica.setViewportView(table);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();

	}

	public void loan_current(String userID) {
		try {

			String sql = "select tytul, data from loan_list_open where status_id=3 and user_id='" + userID
					+ "' order by data desc";
			DatabaseConnection.rs = DatabaseConnection.PST.executeQuery(sql);
		}

		catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table2 = new JTable(buildTableModel(DatabaseConnection.rs));
			aktualne.setViewportView(table2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		aktualne.validate();
		aktualne.revalidate();
		aktualne.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnZmieHaso) {

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
						+ Window.Mlogin + "'";
				;
				DatabaseConnection.PS = DatabaseConnection.conn.prepareStatement(sql);
				DatabaseConnection.PS.setString(1, simie);
				DatabaseConnection.PS.setString(2, snazwisko);
				DatabaseConnection.PS.setString(3, sulica);
				DatabaseConnection.PS.setString(4, smieszk);
				DatabaseConnection.PS.setString(5, skod);
				DatabaseConnection.PS.setString(6, spesel);
				DatabaseConnection.PS.setString(7, smiasto);
				DatabaseConnection.PS.executeUpdate();
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
