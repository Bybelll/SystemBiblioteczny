package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class WindowSemiFin extends JPanel implements ActionListener {

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
		setBackground(Color.WHITE);
		conn = DatabaseConnection.ConnectDbs();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.1, 0.3, 0.3, 0.3, 0.1};
		gridBagLayout.rowWeights = new double[]{0.2, 0.2, 0.5, 0.1};
		//gridBagLayout.columnWidths = new int[] {30, 150, 150, 150, 50};
		//gridBagLayout.rowHeights = new int[] {30, 30, 300, 30};
		setLayout(gridBagLayout);

		ButtonGroup searchOption = new ButtonGroup();
		rdbtnTytul = new JRadioButton("Tytu³", true);
		rdbtnTytul.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnTytul = new GridBagConstraints();
		//gbc_rdbtnTytul.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTytul.gridx = 1;
		gbc_rdbtnTytul.gridy = 1;
		this.add(rdbtnTytul, gbc_rdbtnTytul);
		searchOption.add(rdbtnTytul);
				
//		btnMojeKonto = new JButton("Moje Konto");
//		GridBagConstraints gbc_btnMojeKonto = new GridBagConstraints();
//		//gbc_btnMojeKonto.anchor = GridBagConstraints.WEST;
//		gbc_btnMojeKonto.fill = GridBagConstraints.VERTICAL;
//		//gbc_btnMojeKonto.insets = new Insets(0, 0, 0, 5);
//		gbc_btnMojeKonto.gridx = 2;
//		gbc_btnMojeKonto.gridy = 5;
//		this.add(btnMojeKonto, gbc_btnMojeKonto);
//		btnMojeKonto.addActionListener(this);
//
//		btnWyloguj = new JButton("Wyloguj");
//		GridBagConstraints gbc_btnWyloguj = new GridBagConstraints();
//		//gbc_btnWyloguj.insets = new Insets(0, 0, 0, 5);
//		gbc_btnWyloguj.gridx = 2;
//		gbc_btnWyloguj.gridy = 5;
//		this.add(btnWyloguj, gbc_btnWyloguj);
//		btnWyloguj.addActionListener(this);

		txtSzukaj = new JTextField();
		txtSzukaj.setColumns(10);
		GridBagConstraints gbc_txtSzukaj = new GridBagConstraints();
		gbc_txtSzukaj.weighty = 1.0;
		gbc_txtSzukaj.weightx = 1.0;
		gbc_txtSzukaj.fill = GridBagConstraints.HORIZONTAL;
//		gbc_txtSzukaj.insets = new Insets(0, 0, 5, 5);
		gbc_txtSzukaj.gridwidth = 3;
		gbc_txtSzukaj.gridx = 1;
		gbc_txtSzukaj.gridy = 0;
		this.add(txtSzukaj, gbc_txtSzukaj);
		
		btnSzukaj = new JButton("Szukaj");
		GridBagConstraints gbc_btnSzukaj = new GridBagConstraints();
	//	gbc_btnSzukaj.insets = new Insets(0, 0, 5, 5);
		gbc_btnSzukaj.gridx = 4;
		gbc_btnSzukaj.gridy = 0;
		this.add(btnSzukaj, gbc_btnSzukaj);
		btnSzukaj.addActionListener(this);
				
		rdbtnAutor = new JRadioButton("Autor", false);
		rdbtnAutor.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnAutor = new GridBagConstraints();
		//gbc_rdbtnAutor.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAutor.gridx = 2;
		gbc_rdbtnAutor.gridy = 1;
		this.add(rdbtnAutor, gbc_rdbtnAutor);
		searchOption.add(rdbtnAutor);
		
		rdbtnRokPublikacji = new JRadioButton("Rok publikacji", false);
		rdbtnRokPublikacji.setBackground(Color.WHITE);
		GridBagConstraints gbc_rdbtnRokPublikacji = new GridBagConstraints();
		//gbc_rdbtnRokPublikacji.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRokPublikacji.gridx = 3;
		gbc_rdbtnRokPublikacji.gridy = 1;
		this.add(rdbtnRokPublikacji, gbc_rdbtnRokPublikacji);
		searchOption.add(rdbtnRokPublikacji);

		tablica = new JScrollPane();
		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.weighty = 1.0;
		gbc_tablica.weightx = 1.0;
		gbc_tablica.fill = GridBagConstraints.BOTH;
		//gbc_tablica.insets = new Insets(0, 0, 5, 5);
		gbc_tablica.gridwidth = 3;
		gbc_tablica.gridx = 1;
		gbc_tablica.gridy = 2;
		this.add(tablica, gbc_tablica);
		
		

		btnWypozycz = new JButton("Wypo¿ycz");
		GridBagConstraints gbc_btnWypozycz = new GridBagConstraints();
		//gbc_btnWypozycz.insets = new Insets(0, 0, 5, 5);
		gbc_btnWypozycz.anchor = GridBagConstraints.SOUTH;
		gbc_btnWypozycz.gridx = 4;
		gbc_btnWypozycz.gridy = 2;
		this.add(btnWypozycz, gbc_btnWypozycz);
		btnWypozycz.addActionListener(this);

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
			tablica.setViewportView(table);
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
		tablica.setViewportView(table);
		tablica.validate();
		tablica.revalidate();
		tablica.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnWyloguj) {
			//dispose();

			Window Window = new Window();

			Window.setVisible(true);
		} else if (source == btnMojeKonto) {
			WindowAccount WindowAccount = new WindowAccount();
			WindowAccount.setVisible(true);
			//dispose();
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
		// columnNames.add("Tytuï¿½");
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

				JOptionPane.showMessageDialog(null, "Ksiï¿½ï¿½ka wypoï¿½yczona!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Wystapiï¿½ bï¿½ï¿½d. Sprobuj ponownie pozniej lub skontaktuj siï¿½ z bibliotekarzem.");
			}
		}
	}

}
