package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import SystemB.Window;
import java.sql.*;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Font;

public class WindowAccount extends JPanel implements ActionListener {

	// JFrame frame;
	JTable table, table2;
	private JScrollPane tablica, aktualne;

	private JButton btnAcc, btnZmieHaso, btnResetHaslo;
	private JTextField imie, nazwisko, ulica, numm, kod, miasto, pesel;
	private Box horizontalBox;
	private JLabel lblId, lblLogin;
	private Component horizontalStrut, horizontalStrut_1, horizontalStrut_2, horizontalStrut_3;

	private ResultSet rs;
	private Statement PST;
	private PreparedStatement PS = null;

	private String userID;

	public WindowAccount(String uID) {

		// stworzenie formularza, wyświetlającego dane użytkowników
		setBackground(Color.WHITE);
		userID = uID; // przypisuje zmiennej wartosc podaną jako argument - do
						// wykrozystania w dalszych funkcjach

		// konfiguracja layoutu
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 12, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		gridBagLayout.columnWidths = new int[] { 0, 150, 0, 150, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.8, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, };
		setLayout(gridBagLayout);

		// nieprofesjonalne marginesy
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 0;
		add(horizontalStrut_1, gbc_horizontalStrut_1);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 6;
		gbc_horizontalStrut_2.gridy = 0;
		add(horizontalStrut_2, gbc_horizontalStrut_2);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 4;
		gbc_horizontalStrut_3.gridy = 0;
		add(horizontalStrut_3, gbc_horizontalStrut_3);

		// wyswietla ID i login - tylko informacja, bez opcji edycji, wiec w
		// formie label a nie textfield
		lblId = new JLabel("ID: ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 1;
		gbc_lblId.gridy = 0;
		add(lblId, gbc_lblId);

		lblLogin = new JLabel("login: ");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 1;
		add(lblLogin, gbc_lblLogin);

		// tworzenie dwoch JScrollPane na wypozyczenia - aktualne i historia +
		// labele
		JLabel lblAktualneWyp = new JLabel("Aktualne wypożyczenia");
		lblAktualneWyp.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAktualneWyp = new GridBagConstraints();
		gbc_lblAktualneWyp.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktualneWyp.gridx = 5;
		gbc_lblAktualneWyp.gridy = 0;
		this.add(lblAktualneWyp, gbc_lblAktualneWyp);

		aktualne = new JScrollPane();
		aktualne.getViewport().setBackground(Color.WHITE);
		GridBagConstraints gbc_aktualne = new GridBagConstraints();
		gbc_aktualne.anchor = GridBagConstraints.WEST;
		gbc_aktualne.fill = GridBagConstraints.BOTH;
		gbc_aktualne.insets = new Insets(0, 0, 5, 5);
		gbc_aktualne.gridheight = 4;
		gbc_aktualne.gridx = 5;
		gbc_aktualne.gridy = 1;
		this.add(aktualne, gbc_aktualne);

		JLabel lblHistoriaWypoycze = new JLabel("Historia wypożyczeń");
		lblHistoriaWypoycze.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblHistoriaWypoycze = new GridBagConstraints();
		gbc_lblHistoriaWypoycze.fill = GridBagConstraints.VERTICAL;
		gbc_lblHistoriaWypoycze.insets = new Insets(0, 0, 5, 5);
		gbc_lblHistoriaWypoycze.gridx = 5;
		gbc_lblHistoriaWypoycze.gridy = 5;
		this.add(lblHistoriaWypoycze, gbc_lblHistoriaWypoycze);

		tablica = new JScrollPane();
		tablica.getViewport().setBackground(Color.WHITE);
		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.insets = new Insets(0, 0, 5, 5);
		gbc_tablica.anchor = GridBagConstraints.WEST;
		gbc_tablica.fill = GridBagConstraints.BOTH;
		gbc_tablica.gridheight = 8;
		gbc_tablica.gridx = 5;
		gbc_tablica.gridy = 6;
		this.add(tablica, gbc_tablica);

		// pola i labele na dane pobrane z bazy
		JLabel lblImi = new JLabel("Imie");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.anchor = GridBagConstraints.WEST;
		gbc_lblImi.fill = GridBagConstraints.VERTICAL;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 1;
		gbc_lblImi.gridy = 3;
		this.add(lblImi, gbc_lblImi);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.WEST;
		gbc_lblNazwisko.fill = GridBagConstraints.VERTICAL;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 3;
		gbc_lblNazwisko.gridy = 3;
		this.add(lblNazwisko, gbc_lblNazwisko);

		imie = new JTextField();
		lblImi.setLabelFor(imie);
		GridBagConstraints gbc_imie = new GridBagConstraints();
		gbc_imie.fill = GridBagConstraints.BOTH;
		gbc_imie.insets = new Insets(0, 0, 5, 5);
		gbc_imie.gridx = 1;
		gbc_imie.gridy = 4;
		this.add(imie, gbc_imie);

		nazwisko = new JTextField();
		lblNazwisko.setLabelFor(nazwisko);
		GridBagConstraints gbc_nazwisko = new GridBagConstraints();
		gbc_nazwisko.fill = GridBagConstraints.BOTH;
		gbc_nazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_nazwisko.gridx = 3;
		gbc_nazwisko.gridy = 4;
		this.add(nazwisko, gbc_nazwisko);

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
		gbc_lblNumerMieszkania.gridx = 3;
		gbc_lblNumerMieszkania.gridy = 5;
		this.add(lblNumerMieszkania, gbc_lblNumerMieszkania);

		ulica = new JTextField();
		GridBagConstraints gbc_ulica = new GridBagConstraints();
		gbc_ulica.anchor = GridBagConstraints.WEST;
		gbc_ulica.fill = GridBagConstraints.BOTH;
		gbc_ulica.insets = new Insets(0, 0, 5, 5);
		gbc_ulica.gridx = 1;
		gbc_ulica.gridy = 6;
		this.add(ulica, gbc_ulica);

		numm = new JTextField();
		GridBagConstraints gbc_numm = new GridBagConstraints();
		gbc_numm.anchor = GridBagConstraints.WEST;
		gbc_numm.fill = GridBagConstraints.BOTH;
		gbc_numm.insets = new Insets(0, 0, 5, 5);
		gbc_numm.gridx = 3;
		gbc_numm.gridy = 6;
		this.add(numm, gbc_numm);

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
		gbc_lblMiasto.gridx = 3;
		gbc_lblMiasto.gridy = 7;
		this.add(lblMiasto, gbc_lblMiasto);

		kod = new JTextField();
		GridBagConstraints gbc_kod = new GridBagConstraints();
		gbc_kod.fill = GridBagConstraints.BOTH;
		gbc_kod.insets = new Insets(0, 0, 5, 5);
		gbc_kod.gridx = 1;
		gbc_kod.gridy = 8;
		this.add(kod, gbc_kod);

		miasto = new JTextField();
		GridBagConstraints gbc_miasto = new GridBagConstraints();
		gbc_miasto.fill = GridBagConstraints.BOTH;
		gbc_miasto.insets = new Insets(0, 0, 5, 5);
		gbc_miasto.gridx = 3;
		gbc_miasto.gridy = 8;
		this.add(miasto, gbc_miasto);

		JLabel lblNrPesel = new JLabel("Nr pesel");
		GridBagConstraints gbc_lblNrPesel = new GridBagConstraints();
		gbc_lblNrPesel.anchor = GridBagConstraints.WEST;
		gbc_lblNrPesel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNrPesel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNrPesel.gridx = 1;
		gbc_lblNrPesel.gridy = 9;
		this.add(lblNrPesel, gbc_lblNrPesel);

		pesel = new JTextField();
		GridBagConstraints gbc_pesel = new GridBagConstraints();
		gbc_pesel.fill = GridBagConstraints.BOTH;
		gbc_pesel.insets = new Insets(0, 0, 5, 5);
		gbc_pesel.gridx = 1;
		gbc_pesel.gridy = 10;
		this.add(pesel, gbc_pesel);

		// pudełko i buttony - zatwierdz zmiany i zmien haslo
		horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.gridwidth = 3;
		gbc_horizontalBox.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalBox.gridx = 1;
		gbc_horizontalBox.gridy = 13;
		add(horizontalBox, gbc_horizontalBox);

		btnAcc = new JButton("Zatwierdz zmiany");
		horizontalBox.add(btnAcc);
		btnAcc.addActionListener(this);

		// w przypadku jesli uzytkownik oglada swoje wlasne konto - pozwala na
		// zmiane hasla
		// jesli otwarte jest konto innego uzytkownika niz zalogowany,
		// a zalogowany jest admin - daje opcje zresetowania hasla
		if (userID.equals(Window.MUserID)) {
			horizontalStrut = Box.createHorizontalStrut(20);
			horizontalBox.add(horizontalStrut);

			btnZmieHaso = new JButton("Zmień hasło");
			horizontalBox.add(btnZmieHaso);
			btnZmieHaso.addActionListener(this);
		} else if (Window.MUserType == 1) {
			horizontalStrut = Box.createHorizontalStrut(20);
			horizontalBox.add(horizontalStrut);

			btnResetHaslo = new JButton("Resetuj hasło");
			horizontalBox.add(btnResetHaslo);
			btnResetHaslo.addActionListener(this);
		}

		// wywoluje funkcje uzupelniajace dane - w formularzu i w obu tabelach
		wysw(userID);
		loan(userID);
		loan_current(userID);
	}

	public void wysw(String userID) {

		try {
			// funkcja pobiera z bazy dane wybranego uzytkownika i wypelnia nimi
			// pola w formularzu

			String sql = "SELECT * FROM users WHERE user_id='" + userID + "'";
			PST = DatabaseConnection.conn.createStatement();
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
			String login = rs.getString("login");
			lblLogin.setText("login: " + login);
			String usID = rs.getString("user_id");
			lblId.setText("ID: " + usID);

		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);

		}
	}

	private void loan(String userID) {

		/*
		 * funkcja pobiera dane z widoku "historia" filtrujac wedug wybranego
		 * uzytkownika dane z sql przetwarzane sa na tabele tabela table jest
		 * umieszczana w JScrollPane tablica, odswiezamy JScrollPane
		 */

		try {

			String sql = "select tytul, status, data from historia where user_id='" + userID + "' order by data desc";
			PST = DatabaseConnection.conn.createStatement();
			rs = PST.executeQuery(sql);
		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table = new JTable(SearchTable.buildTableModel(rs));
			tablica.setViewportView(table);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();

	}

	private void loan_current(String userID) {

		/*
		 * funkcja pobiera dane z widoku "loan_list_open" filtrujac wedug
		 * wybranego uzytkownika dane z sql przetwarzane sa na tabele tabela
		 * table jest umieszczana w JScrollPane tablica, odswiezamy JScrollPane
		 * 
		 * widok loan_list_open filtruje wypozyczenia w bazie danych i pokazuje
		 * tylko te loan_id do ktorych nie jest przypisany zwrot
		 */

		try {

			String sql = "select tytul, data from loan_list_open where status_id=3 and user_id='" + userID
					+ "' order by data desc";
			PST = DatabaseConnection.conn.createStatement();
			rs = PST.executeQuery(sql);
		}

		catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table2 = new JTable(SearchTable.buildTableModel(rs));
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
			Window.setVisible(true);
		} else if (source == btnAcc) {
			ChangeData(userID);
		} else if (source == btnResetHaslo) {
			PassReset(userID);
		}

	}

	private void PassReset(String userID) {

		/*
		 * funkcja resetowania hasła dostępna pod przyciskim tylko z poziomu
		 * bibliotekarza sprawdza również na poziomie funkcji, czy zalogowany
		 * jest bibliotekarz wyswietla komunikat z potwierdzeniem akcji, w
		 * przypadku potwierdzenia, wysyla polecenie do SQL ustawienie hasla
		 * domyslnego Gutenberg123 dla uzytkownika o ID z aktualnego rekordu
		 */
		if (Window.MUserType == 1) {
			int confirm = JOptionPane.showConfirmDialog(null,
					"Czy chcesz zresetować hasło dla użytkownika: " + imie.getText() + " " + nazwisko.getText() + "?",
					"Potwierdzenie", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				try {
					String pass = "Gutenberg123";
					String sql = "update users set haslo=md5(?) where user_id=?";
					PS = DatabaseConnection.conn.prepareStatement(sql);
					PS.setString(1, pass);
					PS.setString(2, userID);
					int result = PS.executeUpdate();
					if (result > 0) {
						JOptionPane.showMessageDialog(null, "Ustawiono domyślne hasło: " + pass + ".");
					} else {
						JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	private void ChangeData(String userID) {
		/*
		 * Funkcja zmiany danych odczytuje aktualne wartosci z pol w formularzu
		 * i przypisuje je do zmiennych wysyla polecenie do SQL - aktualizacja
		 * rekordu o podanym userID do zapytania dodawane sa dane ze zmiennych,
		 * z poszczegolnych pol formularza
		 */

		String simie = imie.getText();
		String snazwisko = nazwisko.getText();
		String sulica = ulica.getText();
		String smieszk = numm.getText();
		String skod = kod.getText();
		String spesel = pesel.getText();
		String smiasto = miasto.getText();

		try {

			String sql = "UPDATE users SET imie= ?, nazwisko=?, ulica=?, num_miesz=?, kod_pocz=?, pesel=?, miasto=? WHERE user_id='"
					+ userID + "'";

			PS = DatabaseConnection.conn.prepareStatement(sql);
			PS.setString(1, simie);
			PS.setString(2, snazwisko);
			PS.setString(3, sulica);
			PS.setString(4, smieszk);
			PS.setString(5, skod);
			PS.setString(6, spesel);
			PS.setString(7, smiasto);
			int result = PS.executeUpdate();
			if (result > 0) {
				JOptionPane.showMessageDialog(this, "Zaktualizowano pomyślnie.");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Wystąpił błąd.");
			}
			

		} catch (Exception a) {

			JOptionPane.showMessageDialog(null, a);

		}

	}

	//poniższy fragment można już chyba wywalić, ale zostawiam dopóki nie przetestuje go więcej osób
	
	/*private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

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
	}*/
}
