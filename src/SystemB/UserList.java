package SystemB;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;
import java.awt.CardLayout;

public class UserList extends JPanel implements ActionListener {

	JTextField txtSzukaj;
	JButton btnSzukaj, btnMojeKonto, btnWypozycz, btnUserDetails;

	JTable table;
	JScrollPane tablica;
	JPanel detale;

	JRadioButton rdbtnTytul;
	JRadioButton rdbtnAutor;
	JRadioButton rdbtnRokPublikacji;

	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement PST = null;
	String querry;
	Integer selection;

	public UserList() {
		setBackground(Color.WHITE);
		conn = DatabaseConnection.ConnectDbs();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.5, 0.0, 0.5, 1.0 };
		setLayout(gridBagLayout);

		btnUserDetails = new JButton("Poka\u017C detale");
		GridBagConstraints gbc_btnUserDetails = new GridBagConstraints();
		gbc_btnUserDetails.anchor = GridBagConstraints.SOUTH;
		gbc_btnUserDetails.insets = new Insets(0, 0, 5, 5);
		gbc_btnUserDetails.gridx = 2;
		gbc_btnUserDetails.gridy = 1;
		add(btnUserDetails, gbc_btnUserDetails);
		btnUserDetails.addActionListener(this);

		tablica = new JScrollPane();
		GridBagConstraints gbc_tablica = new GridBagConstraints();
		gbc_tablica.fill = GridBagConstraints.BOTH;
		gbc_tablica.gridx = 1;
		gbc_tablica.gridy = 1;
		this.add(tablica, gbc_tablica);

		searchUsers();

		detale = new JPanel();
		GridBagConstraints gbc_detale = new GridBagConstraints();
		gbc_detale.fill = GridBagConstraints.BOTH;
		gbc_detale.gridy = 3;
		gbc_detale.gridx = 1;
		detale.setLayout(new CardLayout(0, 0));
		WindowAccount userDetails = new WindowAccount("1");
		detale.add(userDetails, "name_193073484718633");
		this.add(detale, gbc_detale);

	}

	public void searchUsers() {
		try {

			String sql = "SELECT user_id, imie, nazwisko from users";

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

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

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

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnUserDetails) {
			showDetails();
		}

	}

	public void showDetails() {
		Integer row = new Integer(table.getSelectedRow());
		Integer userIDint = (Integer) (table.getValueAt(row, 0));
		String userID = Integer.toString(userIDint);
		WindowAccount userDetails = new WindowAccount(userID);
		detale.add(userDetails);
		detale.validate();
		detale.revalidate();
		detale.repaint();
	}

}
