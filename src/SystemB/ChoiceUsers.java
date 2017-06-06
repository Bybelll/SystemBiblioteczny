package SystemB;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChoiceUsers extends JFrame implements ActionListener {

	JButton btnBack, btnChoice;
	private JScrollPane tablica;
	ResultSet rs = null;
	PreparedStatement PST = null;
	String querry, userID;
	Integer selection;
	JTable table;

	/**
	 * Create the application.
	 */
	public ChoiceUsers() {
		getContentPane().setBackground(Color.WHITE);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Border border = BorderFactory.createEmptyBorder(1, 0, 0, 0);

		setBounds(100, 100, 300, 350);
		setResizable(false);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		tablica = new JScrollPane();
		tablica.setBorder(border); // ustawia pustą ramkę wokół tablicy z
									// użytkownikami, żeby nie mnożyć okienek

		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.gridwidth = 3;
		gbc_tablica.insets = new Insets(0, 0, 5, 0);
		gbc_tablica.gridheight = 2;
		gbc_tablica.fill = GridBagConstraints.BOTH;
		gbc_tablica.gridx = 0;
		gbc_tablica.gridy = 1;
		getContentPane().add(tablica, gbc_tablica);

		btnChoice = new JButton("Wybierz");
		btnChoice.addActionListener(this);

		btnBack = new JButton("Wróć");
		btnBack.addActionListener(this);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 3;
		getContentPane().add(btnBack, gbc_btnBack);
		GridBagConstraints gbc_btnChoice = new GridBagConstraints();
		gbc_btnChoice.insets = new Insets(0, 0, 5, 0);
		gbc_btnChoice.gridx = 2;
		gbc_btnChoice.gridy = 3;
		getContentPane().add(btnChoice, gbc_btnChoice);

		searchUsers();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnBack) {
			dispose();
		} else if (source == btnChoice) {
			choiceUser();
			dispose();
		}

	}

	/*
	 * Umożliwia wybór użytkownika z tabeli na którego konto chcemy wypożyczyć
	 * ksiązkę
	 */
	private void choiceUser() {

		Integer row = new Integer(table.getSelectedRow());
		Integer userIDint = (Integer) (table.getValueAt(table.getSelectedRow(), 0));
		userID = Integer.toString(userIDint);

		int wypozyczenie = JOptionPane.showConfirmDialog(null,
				"Czy chcesz wypozyczyc ksiazke \"" + WindowSemiFin.book_title + "\" użytkownikowi "
						+ table.getValueAt(row, 1) + " " + table.getValueAt(row, 2) + "?",
				"Potwierdzenie", JOptionPane.YES_NO_OPTION);
		if (wypozyczenie == JOptionPane.YES_OPTION) {

			try {
				String sql = "CALL `sql11171543`.`borrow`(" + WindowSemiFin.selection + ", " + userID + ", 3)";
				PST = DatabaseConnection.conn.prepareStatement(sql);
				rs = PST.executeQuery();

			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, a);
			}

			JOptionPane.showMessageDialog(null, "Książka wypożyczona!");
		}

	}

	
	/*
	 *  funkcja wysyła zapytanie o podstawowe dane identyfikacyjne
	 *	 uzytkowników: id, imie, nazwisko
	 *	 tworzy tabele z wynikami zapytania
	 *	 dodaje tabele do JScrollPane tablica
	 */
	public void searchUsers() {

		try {

			String sql = "SELECT user_id, imie, nazwisko from users";
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

}
