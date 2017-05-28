package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;

public class AddBook extends JPanel implements ActionListener {

	private JTextField Title;
	private JTextField Rok;
	private JTextField Isbn;
	private JButton btnAdd, btnNextAut, btnMinAut;
	ResultSet RS = null;
	PreparedStatement PS = null;
	Statement PST = null;
	String pub_id, aut_id, book_id, cat_id, Pubname, AuFname, AuSname;
	private JComboBox<String> comboBoxKat, ComWyd, comboImAu, comboNazAu;
	String Catname;
	HashMap<Integer, JComboBox<String>> AuFnameMap, AuSnameMap;
	private Box FnameBox, SnameBox;
	private Integer i = 1;
	private Component verticalStrut;

	public AddBook() {

		this.setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 75, 20, 100, 100, 18, 18, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		setLayout(gridBagLayout);

		AuFnameMap = new HashMap<Integer, JComboBox<String>>();
		AuSnameMap = new HashMap<Integer, JComboBox<String>>();

		JLabel lblAutorImie = new JLabel("Autor");
		GridBagConstraints gbc_lblAutorImie = new GridBagConstraints();
		gbc_lblAutorImie.gridwidth = 2;
		gbc_lblAutorImie.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutorImie.gridx = 3;
		gbc_lblAutorImie.gridy = 0;
		this.add(lblAutorImie, gbc_lblAutorImie);

		JLabel lblTytu = new JLabel("Tytuł");
		GridBagConstraints gbc_lblTytu = new GridBagConstraints();
		gbc_lblTytu.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTytu.insets = new Insets(0, 0, 5, 5);
		gbc_lblTytu.gridx = 1;
		gbc_lblTytu.gridy = 1;
		this.add(lblTytu, gbc_lblTytu);

		JLabel lblImie = new JLabel("imie");
		GridBagConstraints gbc_lblImie = new GridBagConstraints();
		gbc_lblImie.insets = new Insets(0, 0, 5, 5);
		gbc_lblImie.gridx = 3;
		gbc_lblImie.gridy = 1;
		add(lblImie, gbc_lblImie);

		JLabel lblNazwisko = new JLabel("nazwisko");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 4;
		gbc_lblNazwisko.gridy = 1;
		add(lblNazwisko, gbc_lblNazwisko);

		SnameBox = Box.createVerticalBox();
		GridBagConstraints gbc_SnameBox = new GridBagConstraints();
		gbc_SnameBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_SnameBox.anchor = GridBagConstraints.NORTH;
		gbc_SnameBox.gridheight = 10;
		gbc_SnameBox.insets = new Insets(0, 0, 5, 5);
		gbc_SnameBox.gridx = 4;
		gbc_SnameBox.gridy = 2;
		add(SnameBox, gbc_SnameBox);

		comboNazAu = new JComboBox<String>();
		SnameBox.add(comboNazAu);
		comboNazAu.setEditable(true);
		comboNazAu.setSelectedItem(null);
		AuSnameMap.put(1, comboNazAu);

		FnameBox = Box.createVerticalBox();
		GridBagConstraints gbc_FnameBox = new GridBagConstraints();
		gbc_FnameBox.gridheight = 10;
		gbc_FnameBox.anchor = GridBagConstraints.NORTH;
		gbc_FnameBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_FnameBox.insets = new Insets(0, 0, 5, 5);
		gbc_FnameBox.gridx = 3;
		gbc_FnameBox.gridy = 2;
		add(FnameBox, gbc_FnameBox);

		comboImAu = new JComboBox<String>();
		FnameBox.add(comboImAu);
		comboImAu.setEditable(true);
		comboImAu.setSelectedItem(null);
		AuFnameMap.put(1, comboImAu);

		Title = new JTextField();
		Title.setSize(new Dimension(100, 20));
		Title.setMinimumSize(new Dimension(300, 20));
		Title.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_Title = new GridBagConstraints();
		gbc_Title.anchor = GridBagConstraints.NORTHWEST;
		gbc_Title.insets = new Insets(0, 0, 5, 5);
		gbc_Title.gridx = 1;
		gbc_Title.gridy = 2;
		this.add(Title, gbc_Title);
		Title.setColumns(100);

		JLabel lblRokWydania = new JLabel("Rok wydania");
		GridBagConstraints gbc_lblRokWydania = new GridBagConstraints();
		gbc_lblRokWydania.anchor = GridBagConstraints.WEST;
		gbc_lblRokWydania.fill = GridBagConstraints.VERTICAL;
		gbc_lblRokWydania.insets = new Insets(0, 0, 5, 5);
		gbc_lblRokWydania.gridx = 1;
		gbc_lblRokWydania.gridy = 3;
		this.add(lblRokWydania, gbc_lblRokWydania);

		Rok = new JTextField();
		Rok.setMinimumSize(new Dimension(150, 20));
		Rok.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_Rok = new GridBagConstraints();
		gbc_Rok.anchor = GridBagConstraints.WEST;
		gbc_Rok.fill = GridBagConstraints.VERTICAL;
		gbc_Rok.insets = new Insets(0, 0, 5, 5);
		gbc_Rok.gridx = 1;
		gbc_Rok.gridy = 4;
		this.add(Rok, gbc_Rok);
		Rok.setColumns(50);

		btnNextAut = new JButton("+");
		GridBagConstraints gbc_btnNextAut = new GridBagConstraints();
		gbc_btnNextAut.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextAut.gridx = 5;
		gbc_btnNextAut.gridy = 2;
		this.add(btnNextAut, gbc_btnNextAut);
		btnNextAut.addActionListener(this);

		btnMinAut = new JButton("-");
		GridBagConstraints gbc_btnMinAut = new GridBagConstraints();
		gbc_btnMinAut.insets = new Insets(0, 0, 5, 5);
		gbc_btnMinAut.gridx = 6;
		gbc_btnMinAut.gridy = 2;
		this.add(btnMinAut, gbc_btnMinAut);
		btnMinAut.addActionListener(this);

		JLabel lblWydawnictwo = new JLabel("Wydawnictwo");
		GridBagConstraints gbc_lblWydawnictwo = new GridBagConstraints();
		gbc_lblWydawnictwo.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWydawnictwo.insets = new Insets(0, 0, 5, 5);
		gbc_lblWydawnictwo.gridx = 1;
		gbc_lblWydawnictwo.gridy = 5;
		this.add(lblWydawnictwo, gbc_lblWydawnictwo);

		ComWyd = new JComboBox<String>();
		ComWyd.setPreferredSize(new Dimension(150, 20));
		ComWyd.setEditable(true);
		GridBagConstraints gbc_ComWyd = new GridBagConstraints();
		gbc_ComWyd.anchor = GridBagConstraints.SOUTHWEST;
		gbc_ComWyd.insets = new Insets(0, 0, 5, 5);
		gbc_ComWyd.gridx = 1;
		gbc_ComWyd.gridy = 6;
		this.add(ComWyd, gbc_ComWyd);
		ComWyd.setSelectedItem(null);

		JLabel lblIsbn = new JLabel("ISBN");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 1;
		gbc_lblIsbn.gridy = 7;
		this.add(lblIsbn, gbc_lblIsbn);

		Isbn = new JTextField();
		Isbn.setMinimumSize(new Dimension(150, 20));
		Isbn.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_Isbn = new GridBagConstraints();
		gbc_Isbn.anchor = GridBagConstraints.SOUTHWEST;
		gbc_Isbn.insets = new Insets(0, 0, 5, 5);
		gbc_Isbn.gridx = 1;
		gbc_Isbn.gridy = 8;
		this.add(Isbn, gbc_Isbn);
		Isbn.setColumns(50);

		JLabel lblKategoria = new JLabel("Kategoria");
		GridBagConstraints gbc_lblKategoria = new GridBagConstraints();
		gbc_lblKategoria.anchor = GridBagConstraints.WEST;
		gbc_lblKategoria.fill = GridBagConstraints.VERTICAL;
		gbc_lblKategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblKategoria.gridx = 1;
		gbc_lblKategoria.gridy = 9;
		this.add(lblKategoria, gbc_lblKategoria);

		comboBoxKat = new JComboBox<String>();
		comboBoxKat.setPreferredSize(new Dimension(150, 20));
		GridBagConstraints gbc_comboBoxKat = new GridBagConstraints();
		gbc_comboBoxKat.anchor = GridBagConstraints.WEST;
		gbc_comboBoxKat.fill = GridBagConstraints.VERTICAL;
		gbc_comboBoxKat.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxKat.gridx = 1;
		gbc_comboBoxKat.gridy = 10;
		this.add(comboBoxKat, gbc_comboBoxKat);
		comboBoxKat.setSelectedItem(null);

		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 11;
		add(verticalStrut, gbc_verticalStrut);

		btnAdd = new JButton("Dodaj książkę");
		btnAdd.setBounds(216, 185, 146, 26);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 12;
		this.add(btnAdd, gbc_btnAdd);
		btnAdd.addActionListener(this);

		FillComboImAu();
		FillComboKat();
		FillComboWyd();
		FillComboNazAu();
	}

	private void Addbook() { // Jak sama nazwa wskazuje, dodaje ksiazki ;)
		String tytul = Title.getText();

		int rok = Integer.parseInt(Rok.getText()); // Tutaj przypisujemy
													// wartosci wpisane w pola
													// do Stringow, aby uzyc je
													// pozniej do aktualizacji w
													// bazie danych.
		int isbn = Integer.parseInt(Isbn.getText());
		String wydawca = (String) ComWyd.getSelectedItem();
		String kategoria = (String) comboBoxKat.getSelectedItem();

		try {

			String sql5 = "SELECT * FROM category WHERE name='" + kategoria + "'";
			PST = DatabaseConnection.conn.prepareStatement(sql5);
			RS = PST.executeQuery(sql5); // Szukamy id odpowiadajace kategorii
											// wybranej w comboboxie i
											// przypisujemy do stringa
			RS.next();
			cat_id = RS.getString("id_cat");
			PST.close();
			RS.close();

			String sql9 = "SELECT count(*) as cnt FROM publisher WHERE name='" + wydawca + "'"; // sprawdzamy
																								// czy
																								// wydawca
																								// istnieje
																								// w
																								// bazie
			PST = DatabaseConnection.conn.prepareStatement(sql9);
			RS = PST.executeQuery(sql9);
			Integer check2 = null;
			if (RS.next()) {
				check2 = RS.getInt("cnt");
			}
			PST.close();
			RS.close();
			if (check2 == 1) // jezeli wydawca istnieje to pobieramy jego id
			{
				String sql10 = "SELECT * FROM publisher WHERE name='" + wydawca + "'";
				PST = DatabaseConnection.conn.prepareStatement(sql10);
				RS = PST.executeQuery(sql10);
				RS.next();
				pub_id = RS.getString("id_pub");
				PST.close();
				RS.close();
			} else // jezeli wydawca nie istnieje, dodajemy go do bazy.
			{
				String sql11 = "INSERT INTO publisher (name) VALUES (?)";

				PS = DatabaseConnection.conn.prepareStatement(sql11);
				PS.setString(1, wydawca);
				PS.executeUpdate();
				PS.close();
				String sql10 = "SELECT * FROM publisher WHERE name='" + wydawca + "'";
				PST = DatabaseConnection.conn.prepareStatement(sql10);
				RS = PST.executeQuery(sql10);
				RS.next();
				pub_id = RS.getString("id_pub");
				PST.close();
				RS.close();
			}

			String sql12 = "INSERT INTO book (title, pub_year, isbn, id_pub, id_cat) VALUES (?,?,?,?,?)";

			// Tu dodajemy ksiazke do bazy uzywajac wczesniejszych stringow

			PS = DatabaseConnection.conn.prepareStatement(sql12);
			PS.setString(1, tytul);
			PS.setInt(2, rok);
			PS.setInt(3, isbn);
			PS.setString(4, pub_id);
			PS.setString(5, cat_id);
			PS.executeUpdate();
			PS.close();

			String sql1 = "SELECT * from book where title='" + tytul + "'";
			PST = DatabaseConnection.conn.prepareStatement(sql1);
			RS = PST.executeQuery(sql1);
			RS.next();
			book_id = RS.getString("id_book"); // pobieramy id ksiazki

			// dodawanie autora

			for (HashMap.Entry<Integer, JComboBox<String>> entry : AuFnameMap.entrySet()) {
				Integer id = entry.getKey();
				String autorIm = (String) entry.getValue().getSelectedItem();
				String autorNaz = (String) AuSnameMap.get(id).getSelectedItem();
				
				
				String sql6 = "SELECT count(*) as cnt FROM author WHERE fname='" + autorIm + "' AND sname='" + autorNaz
						+ "'";
				PST = DatabaseConnection.conn.prepareStatement(sql6);
				RS = PST.executeQuery(sql6); // sprawdzamy czy w bazie istnieje
												// wpisany przez nas autor
				Integer check1 = null;

				if (RS.next()) {
					check1 = RS.getInt("cnt");
				}
				PST.close();
				RS.close();

				if (check1 == 1) // jezeli autor istnieje w bazie to pobieramy
									// jego
									// id do stringa, aby go potem uzyc.
				{
					String sql4 = "SELECT * FROM author WHERE fname='" + autorIm + "' AND sname='" + autorNaz + "'";
					PST = DatabaseConnection.conn.prepareStatement(sql4);
					RS = PST.executeQuery(sql4);
					RS.next();
					aut_id = RS.getString("id_aut");
					PST.close();
					RS.close();
				} else {
					String sql = "INSERT INTO author (fname, sname) VALUES (?,?)"; // jezeli
																					// autor
																					// nie
																					// istieje
																					// w
																					// bazie,
																					// dodajemy
																					// go.

					PS = DatabaseConnection.conn.prepareStatement(sql);
					PS.setString(1, autorIm);
					PS.setString(2, autorNaz);
					PS.executeUpdate();
					PS.close();
				}
					String sql8 = "SELECT * FROM author WHERE fname='" + autorIm + "' AND sname='" + autorNaz + "'";
					PST = DatabaseConnection.conn.prepareStatement(sql8);
					RS = PST.executeQuery(sql8);
					RS.next();
					aut_id = RS.getString("id_aut");
					PST.close();
					RS.close();

					String sql_ba = "INSERT INTO book_authors (id_book, id_aut) VALUES (?,?)";
				
					PS = DatabaseConnection.conn.prepareStatement(sql_ba);
					PS.setString(1, book_id);
					PS.setString(2, aut_id); // zapisujemy do tabeli
												// book_authors id
												// autora i ksiazki
					PS.executeUpdate();
					PS.close();
				}

			

			JOptionPane.showMessageDialog(this, "Dodano pomyślnie.");

		} catch (Exception a) {

			JOptionPane.showMessageDialog(null, a);
		}

	}

	private void FillComboWyd() // Wypełnia combobox z wydawnictwami
	{
		try {
			String sql = "SELECT * FROM publisher";
			PST = DatabaseConnection.conn.prepareStatement(sql);
			RS = PST.executeQuery(sql);

			while (RS.next()) {
				Pubname = RS.getString("name");
				ComWyd.addItem(Pubname);
			}
			PST.close();
			RS.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void FillComboImAu() // Wypełnia combobox z imieniem autora
	{
		try {
			String sql = "SELECT distinct fname FROM author order by fname asc";
			PST = DatabaseConnection.conn.prepareStatement(sql);
			RS = PST.executeQuery(sql);

			while (RS.next()) {
				AuFname = RS.getString("fname");
				comboImAu.addItem(AuFname);
			}
			PST.close();
			RS.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void FillComboNazAu() // Wypełnia combobox z nazwiskiem autora
	{
		try {
			String sql = "SELECT distinct sname FROM author order by sname asc";
			PST = DatabaseConnection.conn.prepareStatement(sql);
			RS = PST.executeQuery(sql);

			while (RS.next()) {
				AuSname = RS.getString("sname");
				comboNazAu.addItem(AuSname);
			}
			PST.close();
			RS.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void FillComboKat() // Wypełnia combobox z kategoriami
	{
		try {
			String sql = "SELECT * FROM category";
			PST = DatabaseConnection.conn.prepareStatement(sql);
			RS = PST.executeQuery(sql);

			while (RS.next()) {
				Catname = RS.getString("name");
				comboBoxKat.addItem(Catname);
			}
			PST.close();
			RS.close();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btnAdd) {
			Addbook();

		} else if (source == btnNextAut) {
			AddAuthors();

		} else if (source == btnMinAut) {
			DeleteAuthors();

		}

	}

	private void AddAuthors() {
		i = i + 1;
		comboNazAu = new JComboBox<String>();
		SnameBox.add(comboNazAu);
		comboNazAu.setEditable(true);
		comboNazAu.setSelectedItem(null);
		AuSnameMap.put(i, comboNazAu);

		comboImAu = new JComboBox<String>();
		FnameBox.add(comboImAu);
		comboImAu.setEditable(true);
		comboImAu.setSelectedItem(null);
		AuFnameMap.put(i, comboImAu);

		FillComboImAu();
		FillComboNazAu();
	}

	private void DeleteAuthors() {

		SnameBox.remove(AuSnameMap.get(i));
		FnameBox.remove(AuFnameMap.get(i));
		AuSnameMap.remove(i);
		AuFnameMap.remove(i);
		i = i - 1;
		SnameBox.revalidate();
		FnameBox.revalidate();
		System.out.println(AuSnameMap.toString());
	}
}
