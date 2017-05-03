package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WindowSemiFin extends JFrame implements ActionListener {

	JTextField txtSzukaj;
	JButton btnSzukaj, btnWyloguj, btnMojeKonto, btnWypozycz;

	JTable table;
	JScrollPane tablica;

	JRadioButton rdbtnTytul;
	JRadioButton rdbtnAutor;
	JRadioButton rdbtnRokPublikacji;

	// ButtonGroup searchOption;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement PST = null;
	String querry;
	Integer selection;

	public WindowSemiFin() {

		setSize(800, 400);
		setTitle("System Biblioteczny - Program g��wny");
		getContentPane().setLayout(null);

		conn = DatabaseConnection.ConnectDbs();

		txtSzukaj = new JTextField();
		txtSzukaj.setBounds(153, 32, 339, 33);
		txtSzukaj.setColumns(10);
		getContentPane().add(txtSzukaj);

		ButtonGroup searchOption = new ButtonGroup();
		rdbtnTytul = new JRadioButton("Tytu�", true);
		rdbtnTytul.setBounds(98, 66, 69, 29);
		getContentPane().add(rdbtnTytul);
		searchOption.add(rdbtnTytul);

		rdbtnAutor = new JRadioButton("Autor", false);
		rdbtnAutor.setBounds(174, 66, 83, 29);
		getContentPane().add(rdbtnAutor);
		searchOption.add(rdbtnAutor);

		rdbtnRokPublikacji = new JRadioButton("Rok publikacji", false);
		rdbtnRokPublikacji.setBounds(256, 66, 155, 29);
		getContentPane().add(rdbtnRokPublikacji);
		searchOption.add(rdbtnRokPublikacji);

		btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setBounds(495, 34, 115, 29);
		getContentPane().add(btnSzukaj);
		btnSzukaj.addActionListener(this);

		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setBounds(663, 0, 115, 29);
		getContentPane().add(btnWyloguj);
		btnWyloguj.addActionListener(this);

		tablica = new JScrollPane();
		tablica.setSize(580, 200);
		tablica.setLocation(50, 100);
		getContentPane().add(tablica);

		btnWypozycz = new JButton("Wypo�ycz");
		btnWypozycz.setBounds(648, 299, 115, 29);
		getContentPane().add(btnWypozycz);
		btnWypozycz.addActionListener(this);

		btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.setBounds(547, 0, 115, 29);
		getContentPane().add(btnMojeKonto);
		btnMojeKonto.addActionListener(this);

		catalogue();

	}

	public void catalogue() {
		try {

			String sql = "SELECT * from wypozyczalnia2";

			PST = conn.prepareStatement(sql);
			rs = PST.executeQuery();
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

		tablica.getViewport().add(table);
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
			PST = conn.prepareStatement(sql);
			PST.setString(1, querry);
			rs = PST.executeQuery();
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

		tablica.getViewport().add(table);
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnWyloguj) {
			dispose();

			Window Window = new Window();

			Window.setVisible(true);
		} else if (source == btnMojeKonto) {
			WindowAccount WindowAccount = new WindowAccount();
			WindowAccount.setVisible(true);
			dispose();
		} else if (source == btnSzukaj) {
			searching();
		} else if (source == btnWypozycz) {
			wypozycz();
		}
	}

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// // names of columns
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

	private void wypozycz() {
		
		Integer row = new Integer(table.getSelectedRow());
		selection = (Integer) (table.getValueAt(row, 0));
		System.out.println(selection);
		String book_title = new String();
		book_title = (String) table.getValueAt(row, 1);
		if (!table.getValueAt(row, 7).equals("dostepna")) {
			JOptionPane.showMessageDialog(null, "Ta pozycja jest niedostepna.");
		} else {
			int wypozyczenie = JOptionPane.showConfirmDialog(null, "Czy chcesz wypozyczyc ksiazke " + book_title + "?",
					"Potwierdzenie", JOptionPane.YES_NO_OPTION);
			if (wypozyczenie == JOptionPane.YES_OPTION) {

				try {
					String sql = "CALL `sql11171543`.`borrow`(" + selection + ", " + WindowSignIn.MUserID + ", 3)";
					PST = conn.prepareStatement(sql);
					rs = PST.executeQuery();
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, a);
				}

				JOptionPane.showMessageDialog(null, "Ksi��ka wypo�yczona!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Wystapi� b��d. Sprobuj ponownie pozniej lub skontaktuj si� z bibliotekarzem.");
			}
		}
	}

}
