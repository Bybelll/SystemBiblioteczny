package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;

public class WindowSemiFin extends JPanel implements ActionListener {

	JTextField txtSzukaj;
	JButton btnSzukaj, btnMojeKonto, btnWypozycz, btnAddBook, btnReturnBook, btnDeleteBook;

	JTable table;
	JScrollPane tablica;

	JRadioButton rdbtnTytul;
	JRadioButton rdbtnAutor;
	JRadioButton rdbtnRokPublikacji;

	ResultSet rs = null;
	PreparedStatement PST = null;
	String querry;
	static Integer selection;
	static String book_title;
	private Component verticalStrut;
	private Box verticalBox;
	private Box verticalBox_1;

	public WindowSemiFin() {

		setBackground(Color.WHITE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 12, 0, 0, 0, 0, 0, 0, 0, 0 };
		// gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.1, 0.3, 0.3, 0.3, 0.1 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.2, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		setLayout(gridBagLayout);

		ButtonGroup searchOption = new ButtonGroup();
		rdbtnTytul = new JRadioButton("Tytuł", true);
		rdbtnTytul.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnTytul = new GridBagConstraints();
		gbc_rdbtnTytul.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTytul.gridx = 1;
		gbc_rdbtnTytul.gridy = 1;
		this.add(rdbtnTytul, gbc_rdbtnTytul);
		searchOption.add(rdbtnTytul);

		txtSzukaj = new JTextField();
		txtSzukaj.setColumns(10);
		GridBagConstraints gbc_txtSzukaj = new GridBagConstraints();
		gbc_txtSzukaj.insets = new Insets(0, 0, 5, 5);
		gbc_txtSzukaj.weighty = 1.0;
		gbc_txtSzukaj.weightx = 1.0;
		gbc_txtSzukaj.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSzukaj.gridwidth = 3;
		gbc_txtSzukaj.gridx = 1;
		gbc_txtSzukaj.gridy = 0;
		this.add(txtSzukaj, gbc_txtSzukaj);

		btnSzukaj = new JButton("Szukaj");
		GridBagConstraints gbc_btnSzukaj = new GridBagConstraints();
		gbc_btnSzukaj.insets = new Insets(0, 0, 5, 0);
		gbc_btnSzukaj.gridx = 4;
		gbc_btnSzukaj.gridy = 0;
		this.add(btnSzukaj, gbc_btnSzukaj);
		btnSzukaj.addActionListener(this);

		rdbtnAutor = new JRadioButton("Autor", false);
		rdbtnAutor.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnAutor = new GridBagConstraints();
		gbc_rdbtnAutor.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAutor.gridx = 2;
		gbc_rdbtnAutor.gridy = 1;
		this.add(rdbtnAutor, gbc_rdbtnAutor);
		searchOption.add(rdbtnAutor);

		rdbtnRokPublikacji = new JRadioButton("Rok publikacji", false);
		rdbtnRokPublikacji.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnRokPublikacji = new GridBagConstraints();
		gbc_rdbtnRokPublikacji.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRokPublikacji.gridx = 3;
		gbc_rdbtnRokPublikacji.gridy = 1;
		this.add(rdbtnRokPublikacji, gbc_rdbtnRokPublikacji);
		searchOption.add(rdbtnRokPublikacji);

		tablica = new JScrollPane();
		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.insets = new Insets(0, 0, 5, 5);
		gbc_tablica.fill = GridBagConstraints.BOTH;
		gbc_tablica.gridwidth = 3;
		gbc_tablica.gridheight = 6;
		gbc_tablica.gridx = 1;
		gbc_tablica.gridy = 2;
		this.add(tablica, gbc_tablica);

		verticalBox = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox = new GridBagConstraints();
		gbc_verticalBox.insets = new Insets(0, 0, 5, 0);
		gbc_verticalBox.gridx = 4;
		gbc_verticalBox.gridy = 2;
		add(verticalBox, gbc_verticalBox);

		btnWypozycz = new JButton("Wypozycz");
		verticalBox.add(btnWypozycz);
		btnWypozycz.addActionListener(this);

		if (Window.MUserType == 1) {
		btnReturnBook = new JButton("Zwróć książkę");
		verticalBox.add(btnReturnBook);
		btnReturnBook.addActionListener(this);
		}

		verticalBox_1 = Box.createVerticalBox();
		GridBagConstraints gbc_verticalBox_1 = new GridBagConstraints();
		gbc_verticalBox_1.anchor = GridBagConstraints.SOUTH;
		gbc_verticalBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_verticalBox_1.gridx = 4;
		gbc_verticalBox_1.gridy = 7;
		add(verticalBox_1, gbc_verticalBox_1);

		if (Window.MUserType == 1) {
		btnDeleteBook = new JButton("Usuń książkę");
		verticalBox_1.add(btnDeleteBook);
		btnDeleteBook.addActionListener(this);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 8;
		add(verticalStrut, gbc_verticalStrut);
		 }

		catalogue();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnMojeKonto) {
			WindowAccount WindowAccount = new WindowAccount(Window.MUserID);
			WindowAccount.setVisible(true);

		} else if (source == btnSzukaj) {
			searching();
		} else if (source == btnWypozycz) {
			wypozycz();

		} else if (source == btnAddBook) {
			AddBook AddBook = new AddBook();
			AddBook.setVisible(true);
		} else if (source == btnReturnBook) {
			returnBook();

		} else if (source == btnDeleteBook) {
			usun();
		}
	}

	private void usun() {

		Integer row = new Integer(table.getSelectedRow());
		selection = (Integer) (table.getValueAt(row, 0));
		book_title = (String) table.getValueAt(row, 1);

		int usuniecie = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz usunąć ksiazke " + book_title + "?",
				"Potwierdzenie", JOptionPane.YES_NO_OPTION);
		if (usuniecie == JOptionPane.YES_OPTION) {

			try {
				String sql = "DELETE FROM book WHERE title='" + book_title + "'";
				PST = DatabaseConnection.conn.prepareStatement(sql);
				PST.executeUpdate();

			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, a);
			}
			try {
				String sql = "DELETE FROM book_authors WHERE id_book='" + selection + "'";
				PST = DatabaseConnection.conn.prepareStatement(sql);
				PST.executeUpdate();

			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, a);
			}

			JOptionPane.showMessageDialog(null, "Książka usunięta!");
		} else {
			JOptionPane.showMessageDialog(null, "Nie usunięto.");
		}
	}

	private void wypozycz() {

		Integer row = new Integer(table.getSelectedRow());

		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Nie wybrano żadnej ksiązki z listy.");
		} else {
			selection = (Integer) (table.getValueAt(row, 0));
			book_title = (String) table.getValueAt(row, 1);

			if (!table.getValueAt(row, 7).equals("dostepna")) {
				JOptionPane.showMessageDialog(null, "Ta pozycja jest niedostepna.");
			} else {
				if (Window.MUserType == 1) {
					ChoiceUsers u = new ChoiceUsers();
					u.setVisible(true);
				} else {

					int wypozyczenie = JOptionPane.showConfirmDialog(null,
							"Czy chcesz wypozyczyc ksiazke " + book_title + "?", "Potwierdzenie",
							JOptionPane.YES_NO_OPTION);
					if (wypozyczenie == JOptionPane.YES_OPTION) {

						try {
							String sql = "CALL `sql11171543`.`borrow`(" + selection + ", " + Window.MUserID + ", 3)";
							PST = DatabaseConnection.conn.prepareStatement(sql);
							rs = PST.executeQuery();
							JOptionPane.showMessageDialog(null, "Książka wypożyczona!");
						} catch (Exception a) {
							JOptionPane.showMessageDialog(null, "Nie można wypożyczyć. Skontaktuj się z bibliotekarzem");
						}				
						
					} 
				}
			}
		}
	}

	public void catalogue() {
		try {

			String sql = "SELECT * from wypozyczalnia2";

			PST = DatabaseConnection.conn.prepareStatement(sql);
			rs = PST.executeQuery();

		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table = new JTable(SearchTable.buildTableModel(rs));
			tablica.setViewportView(table);

			PST.close();
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		tablica.validate();
		tablica.revalidate();
		tablica.repaint();

	}

	public void searching() {

		try {

			String sql = "SELECT * from wypozyczalnia2 ";

			if (rdbtnTytul.isSelected()) {
				sql += "where tytul like ?";
			} else if (rdbtnAutor.isSelected()) {
				sql += "where autorzy like ?";

			} else if (rdbtnRokPublikacji.isSelected()) {
				sql += "where rok like ?";
			}
			querry = "%" + txtSzukaj.getText() + "%";
			PST = DatabaseConnection.conn.prepareStatement(sql);
			PST.setString(1, querry);
			rs = PST.executeQuery();

		} catch (Exception a) {
			JOptionPane.showMessageDialog(null, a);
		}

		try {
			table = new JTable(SearchTable.buildTableModel(rs));

			PST.close();
			rs.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tablica.setViewportView(table);
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();
	}

	private void returnBook() {

		Integer row = new Integer(table.getSelectedRow());
		String status = (String) table.getValueAt(row, 7);

		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Nie wybrałeś książki z listy.");
		}
		else if (!status.equals("wypozyczona"))
		{
			JOptionPane.showMessageDialog(null, "Ta pozycja nie jest wypożyczona.");
		}
		else {
			selection = (Integer) (table.getValueAt(row, 0));
			book_title = (String) table.getValueAt(row, 1);

			String imie = "";
			String nazwisko = "";

			try {

				String sql = "select imie im, nazwisko naz  from sql11171543.loan_list_open where status_id=3 and book_id='"
						+ selection + "' order by data desc";
				Statement PS = DatabaseConnection.conn.createStatement();
				ResultSet rs1 = PS.executeQuery(sql);

				if (rs1.next()) {
					imie = rs1.getString("im");
					nazwisko = rs1.getString("naz");

				}

				int wypozyczenie = JOptionPane.showConfirmDialog(null, "Czy chcesz zwrócić książkę \"" + book_title
						+ "\" wypożyczoną przez " + imie + " " + nazwisko + " ?", "Potwierdzenie",
						JOptionPane.YES_NO_OPTION);

				if (wypozyczenie == JOptionPane.YES_OPTION) {

					sql = "CALL `sql11171543`.`rtrn_book`(" + selection + ")";
					PST = DatabaseConnection.conn.prepareStatement(sql);
					rs = PST.executeQuery();

					JOptionPane.showMessageDialog(null, "Książka zwrócona!");

					PST.close();
					PS.close();
					rs.close();
				}
			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, "Coś poszło nie tak.");
			}
		}
	}

}
