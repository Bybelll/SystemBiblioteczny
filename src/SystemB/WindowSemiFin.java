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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WindowSemiFin extends JFrame implements ActionListener {

	JTextField txtSzukaj;

	JButton btnSzukaj;
	JButton btnWyloguj;
	JButton btnMojeKonto;

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

	public WindowSemiFin() {

		setSize(800, 400);
		setTitle("System Biblioteczny - Program główny");
		getContentPane().setLayout(null);

		conn = DatabaseConnection.ConnectDbs();

		txtSzukaj = new JTextField();
		txtSzukaj.setBounds(153, 32, 339, 33);
		txtSzukaj.setColumns(10);
		getContentPane().add(txtSzukaj);

		ButtonGroup searchOption = new ButtonGroup();
		rdbtnTytul = new JRadioButton("Tytuł", true);
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

		JButton btnWypoycz = new JButton("Wypo\u017Cycz");
		btnWypoycz.setBounds(648, 299, 115, 29);
		getContentPane().add(btnWypoycz);

		JButton btnSprawd = new JButton("Sprawdz status");
		btnSprawd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnSprawd.setBounds(630, 263, 148, 33);
		getContentPane().add(btnSprawd);

		btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.setBounds(547, 0, 115, 29);
		getContentPane().add(btnMojeKonto);
		btnMojeKonto.addActionListener(this);
	}

	public void searching() {

		try {

			String sql = "SELECT a.fname,a.sname, b.title, b.pub_year, b.isbn, p.name, c.name FROM book as b "
					+ "LEFT JOIN publisher as p ON p.id_pub=b.id_pub " + "LEFT JOIN category as c ON c.id_cat=b.id_cat "
					+ "LEFT JOIN book_authors as ba ON ba.id_book=b.id_book "
					+ "LEFT JOIN author as a ON a.id_aut=ba.id_aut ";

			if (rdbtnTytul.isSelected()) {
				sql += "where b.title=?";
			} else if (rdbtnAutor.isSelected()) {
				sql += "where a.sname=? OR a.fname=?";

			} else if (rdbtnRokPublikacji.isSelected()) {
				sql += "where b.pub_year=?";
			}

			querry = txtSzukaj.getText();
			PST = conn.prepareStatement(sql);
			PST.setString(1, querry);

			if (rdbtnAutor.isSelected()) {
				PST.setString(2, querry);
			}

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
		}

	}

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();

		columnNames.add("Imie Autora");
		columnNames.add("Nazwisko autora");
		columnNames.add("Tytuł");
		columnNames.add("Rok publikacji");
		columnNames.add("isbn");
		columnNames.add("Wydawnictwo");
		columnNames.add("Kategoria");

		int columnCount = metaData.getColumnCount();

		// for (int column = 1; column <= columnCount; column++) {
		// columnNames.add(metaData.getColumnName(column));
		// }

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);
	}
}
