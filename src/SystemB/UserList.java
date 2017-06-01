package SystemB;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class UserList extends JPanel implements ActionListener {

	JTextField txtSzukaj;
	JButton btnUserDetails, btnUserDelete, btnAdmin, btnReturnBook;

	static JTable table;
	JScrollPane tablica;
	JPanel detale;

	ResultSet rs = null;
	PreparedStatement PST = null;
	String querry;
	Integer selection;
	private Box verticalBox;
	private Component verticalStrut, verticalStrut_1;
	private JSeparator separator;

	WindowAccount userDetails;

	public UserList() {

		// deklaracja elementów do formularza
		setBackground(Color.WHITE);

		Border border = BorderFactory.createEmptyBorder(1, 0, 0, 0);

		// konfiguracja layoutu
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		tablica = new JScrollPane();
		tablica.setBorder(border); // ustawia pustą ramkę wokół tablicy z
									// użytkownikami, żeby nie mnożyć okienek

		verticalStrut_1 = Box.createVerticalStrut(20); // nieprofesjonalne
														// ustawienie marginesów
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 0;
		gbc_verticalStrut_1.gridy = 0;
		add(verticalStrut_1, gbc_verticalStrut_1);

		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.insets = new Insets(0, 0, 5, 5);
		gbc_tablica.gridheight = 2;
		gbc_tablica.fill = GridBagConstraints.BOTH;
		gbc_tablica.gridx = 0;
		gbc_tablica.gridy = 1;
		this.add(tablica, gbc_tablica);

		searchUsers(); // wywołanie funkcji, która ma na celu stworzenie tabeli
						// z użytkownikami

		separator = new JSeparator(); // separator oddzielający tabele
										// użytkowników od dolnej cześci panelu
										// w której są szczegóły użytkownika
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		add(separator, gbc_separator);

		detale = new JPanel(); // tworzy panel, w którym będą wyświetlane dane
								// poszczególnych użytkowników - korzystając z
								// klasy WindowAccount
		GridBagConstraints gbc_detale = new GridBagConstraints();
		gbc_detale.gridwidth = 3;
		gbc_detale.insets = new Insets(0, 0, 5, 0);
		gbc_detale.fill = GridBagConstraints.BOTH;
		gbc_detale.gridy = 4;
		gbc_detale.gridx = 0;
		detale.setLayout(new CardLayout(0, 0));
		WindowAccount userDetails = new WindowAccount("1");
		detale.add(userDetails);
		this.add(detale, gbc_detale);

		verticalBox = Box.createVerticalBox(); // dodaje pudełko na przyciski
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.insets = new Insets(0, 0, 5, 0);
		gbc_verticalBox.anchor = GridBagConstraints.NORTH;
		gbc_verticalBox.gridheight = 2;
		gbc_verticalBox.gridx = 2;
		gbc_verticalBox.gridy = 1;
		add(verticalBox, gbc_verticalBox);

		btnUserDetails = new JButton("Pokaż detale");
		verticalBox.add(btnUserDetails);
		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		btnUserDelete = new JButton("Usuń");
		verticalBox.add(btnUserDelete);
		btnUserDelete.addActionListener(this);
		btnUserDetails.addActionListener(this);

		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		btnAdmin = new JButton("Nadaj/odbierz admina");
		verticalBox.add(btnAdmin);
		btnAdmin.addActionListener(this);

		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		btnReturnBook = new JButton("Zwróć książkę");
		verticalBox.add(btnReturnBook);
		btnReturnBook.addActionListener(this);

	}

	public void searchUsers() {

		// funkcja wysyła zapytanie o podstawowe dane identyfikacyjne
		// uzytkowników: id, imie, nazwisko
		// tworzy tabele z wynikami zapytania
		// dodaje tabele do JScrollPane tablica

		try {

			String sql = "SELECT user_id, imie, nazwisko, (case when admin=true then 'admin' else 'uzytkownik' end) as admin from users";
			PST = DatabaseConnection.conn.prepareStatement(sql);
			rs = PST.executeQuery();
		}

		catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table = new JTable(SearchTable.buildTableModel(rs));
			table.setFillsViewportHeight(true);
			table.setBorder(null);
			tablica.setViewportView(table);
			PST.close();
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// odświeża JScrollPane po zmianie danych
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnUserDetails) {
			showDetails();
		} else if (source == btnUserDelete) {
			deleteUser();
		} else if (source == btnAdmin) {
			changeAdmin();
		} else if (source == btnReturnBook) {
			returnBook();
			showDetails();
		}

	}

	private void showDetails() {

		// pokazuje detale wybranego uzytkownika
		// odczytuje wiersz który jest obecnie zaznaczony i pobiera z niego
		// wartosc ID
		// tworzy nowy obiekt klasy WindowAccount podajac ID wybranego
		// uzytkownika
		// usuwa wszystko z panelu detale, dodaje do niego nowo stworzony window
		// account i odswieza panel

		Integer row = new Integer(table.getSelectedRow());
		Integer userIDint = (Integer) (table.getValueAt(row, 0));
		String userID = Integer.toString(userIDint); // ID z zaznaczonego
														// wiersza -
														// wykorzystane jako
														// arugment dla
														// WindowAccount
		userDetails = new WindowAccount(userID);
		detale.removeAll();
		detale.add(userDetails);
		detale.validate();
		detale.revalidate();
		detale.repaint();
	}

	private void deleteUser() {

		// usuwa z bazy wybranego użytkownika
		// odczytuje wiersz który jest obecnie zaznaczony i pobiera z niego
		// wartosc ID

		Integer row = new Integer(table.getSelectedRow());
		Integer userIDint = (Integer) (table.getValueAt(row, 0));
		String userID = Integer.toString(userIDint); // ID z zaznaczonego
														// wiersza,
														// wykorzystywane w
														// poleceniu SQL
		String userSname = (String) (table.getValueAt(row, 1));
		String userFname = (String) (table.getValueAt(row, 2));

		// wyświetla okno z potwierdzeniem czy na pewno chcemy wykonać akcję
		int confirm = JOptionPane.showConfirmDialog(null,
				"Czy chcesz usunąć użytkownika" + userFname + " " + userSname + "?", "Potwierdzenie",
				JOptionPane.YES_NO_OPTION);

		// jeśli bibliotekarz potwierdzi wybór, wykonujemy polecenie w bazie
		// delete user
		if (confirm == JOptionPane.YES_OPTION) {
			try {
				String sql = "delete from users where user_id=?";
				PST = DatabaseConnection.conn.prepareStatement(sql);
				PST.setString(1, userID);
				int result = PST.executeUpdate();
				// sprawdzenie czy update się powiódł
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "Użytkownik został usunięty.");
				} else {
					JOptionPane.showMessageDialog(null, "Wystąpił błąd.");
				}

			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, a);
			}

		}
		// po usunięciu użytkownika ponownie odczytujemy dane z bazy, tworzymy
		// nową tabelę i odświeżamy widok
		searchUsers();
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();
	}

	private void changeAdmin() {
		ResultSet rs;
		Statement PST;
		PreparedStatement PS = null;
		boolean isAdmin = false;

		Integer userIDint = (Integer) (table.getValueAt(table.getSelectedRow(), 0));
		String userID = Integer.toString(userIDint);

		try {

			String sql = "SELECT admin as adm FROM users where user_id=" + userID + ";";
			PST = DatabaseConnection.conn.createStatement();
			rs = PST.executeQuery(sql);

			if (rs.next() && rs.getInt("adm") == 1) {
				isAdmin = true;
			}

			sql = "UPDATE users SET  admin=? WHERE user_id='" + userID + "'";

			PS = DatabaseConnection.conn.prepareStatement(sql);

			if (isAdmin) {
				PS.setString(1, "0");
				JOptionPane.showMessageDialog(this, "Odebrano prawa administratora");
			} else {
				PS.setString(1, "1");
				JOptionPane.showMessageDialog(this, "Nadano prawa administratora");
			}
			PS.executeUpdate();

			PST.close();
			PS.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchUsers();
	}

	private void returnBook() {
		Integer row = new Integer(table.getSelectedRow());

		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Nie wybrano użytkwnika.");
		} else {
			Integer userIDint = (Integer) (table.getValueAt(row, 0));
			String userID = Integer.toString(userIDint);

			Integer row2 = new Integer(userDetails.table2.getSelectedRow());

			if (row2 == -1) {
				JOptionPane.showMessageDialog(null, "Nie wybrano ksiązki z listy aktualne wypożyczenia.");
			} else {
				String bookTitle = (String) userDetails.table2.getValueAt(row2, 0);

				int wypozyczenie = JOptionPane.showConfirmDialog(
						null, "Czy chcesz zwrócić książkę \"" + bookTitle + "\" wypożyczoną przez "
								+ table.getValueAt(row, 1) + " " + table.getValueAt(row, 2) + " ?",
						"Potwierdzenie", JOptionPane.YES_NO_OPTION);

				if (wypozyczenie == JOptionPane.YES_OPTION) {
					try {

						String sql = "select book_id as bookid from sql11171543.loan_list_open where status_id=3 and user_id='"
								+ userID + "' and tytul='" + bookTitle + "' order by data desc";
						Statement PS = DatabaseConnection.conn.createStatement();
						ResultSet rs1 = PS.executeQuery(sql);

						if (rs1.next()) {
							Integer m = rs1.getInt("bookid");
							sql = "CALL `sql11171543`.`rtrn2`(" + m + ", " + userID + ")";
							PST = DatabaseConnection.conn.prepareStatement(sql);
							rs = PST.executeQuery();

							JOptionPane.showMessageDialog(null, "Książka zwrócona!");
						}

						PST.close();
						PS.close();
						rs.close();

					}

					catch (Exception a) {
						JOptionPane.showMessageDialog(null, "Coś poszło nie tak. Spróbuj ponownie.");
					}
				}
			}
		}
	}
}
