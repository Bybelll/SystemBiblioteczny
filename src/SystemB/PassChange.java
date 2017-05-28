package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class PassChange extends JFrame implements ActionListener {

	private JPasswordField oldPass, newPass, newPass1;
	JButton btnChangePass, btnBack;
	String OldPas;
	String NewPas2;
	String haslobaza, haslonowe;
	String NewPas1;
	ResultSet rs;
	Statement PST;
	PreparedStatement PS = null;
	JLabel lblNPass, lblnPass1, lblStareHaso;

	public PassChange() {
		getContentPane().setBackground(Color.WHITE);

		setSize(250, 300);
		setResizable(false);
		setTitle("Zmiana hasła");
		setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 100, 100, 10, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 29, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		
				JLabel lblStareHaso_1 = new JLabel("stare haslo");
				GridBagConstraints gbc_lblStareHaso_1 = new GridBagConstraints();
				gbc_lblStareHaso_1.gridwidth = 2;
				gbc_lblStareHaso_1.anchor = GridBagConstraints.NORTH;
				gbc_lblStareHaso_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblStareHaso_1.gridx = 1;
				gbc_lblStareHaso_1.gridy = 1;
				getContentPane().add(lblStareHaso_1, gbc_lblStareHaso_1);

		oldPass = new JPasswordField();
		GridBagConstraints gbc_oldPass = new GridBagConstraints();
		gbc_oldPass.gridwidth = 2;
		gbc_oldPass.anchor = GridBagConstraints.SOUTH;
		gbc_oldPass.insets = new Insets(0, 0, 5, 5);
		gbc_oldPass.gridx = 1;
		gbc_oldPass.gridy = 2;
		getContentPane().add(oldPass, gbc_oldPass);
		oldPass.setColumns(15);

		JLabel lblNPass_1 = new JLabel("nowe haslo");
		GridBagConstraints gbc_lblNPass_1 = new GridBagConstraints();
		gbc_lblNPass_1.gridwidth = 2;
		gbc_lblNPass_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNPass_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNPass_1.gridx = 1;
		gbc_lblNPass_1.gridy = 3;
		getContentPane().add(lblNPass_1, gbc_lblNPass_1);

		newPass = new JPasswordField();
		GridBagConstraints gbc_newPass = new GridBagConstraints();
		gbc_newPass.gridwidth = 2;
		gbc_newPass.fill = GridBagConstraints.VERTICAL;
		gbc_newPass.insets = new Insets(0, 0, 5, 5);
		gbc_newPass.gridx = 1;
		gbc_newPass.gridy = 4;
		getContentPane().add(newPass, gbc_newPass);
		newPass.setColumns(15);

		JLabel lblnPass1_1 = new JLabel("powtorz nowe haslo");
		GridBagConstraints gbc_lblnPass1_1 = new GridBagConstraints();
		gbc_lblnPass1_1.gridwidth = 2;
		gbc_lblnPass1_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblnPass1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblnPass1_1.gridx = 1;
		gbc_lblnPass1_1.gridy = 5;
		getContentPane().add(lblnPass1_1, gbc_lblnPass1_1);

		newPass1 = new JPasswordField();
		GridBagConstraints gbc_newPass1 = new GridBagConstraints();
		gbc_newPass1.gridwidth = 2;
		gbc_newPass1.fill = GridBagConstraints.VERTICAL;
		gbc_newPass1.insets = new Insets(0, 0, 5, 5);
		gbc_newPass1.gridx = 1;
		gbc_newPass1.gridy = 6;
		getContentPane().add(newPass1, gbc_newPass1);
		newPass1.setColumns(15);

		btnChangePass = new JButton("Zmień hasło");
		GridBagConstraints gbc_btnChangePass = new GridBagConstraints();
		gbc_btnChangePass.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChangePass.insets = new Insets(0, 0, 0, 5);
		gbc_btnChangePass.gridx = 1;
		gbc_btnChangePass.gridy = 8;
		getContentPane().add(btnChangePass, gbc_btnChangePass);

		btnBack = new JButton("Wróć");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 8;
		getContentPane().add(btnBack, gbc_btnBack);
		btnBack.addActionListener(this);
		btnChangePass.addActionListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == btnChangePass) {

			OldPas = new String(oldPass.getPassword());
			NewPas1 = new String(newPass.getPassword());
			NewPas2 = new String(newPass1.getPassword());

			if (!OldPas.equals(NewPas1) || !OldPas.equals(NewPas2)) {
				try {
					String sql = "SELECT count(*) as cnt from users where login='" + Window.Mlogin + "' AND haslo=md5('"
							+ OldPas + "')";
					PST = DatabaseConnection.conn.createStatement();
					rs = PST.executeQuery(sql);
					Integer check = null;
					if (rs.next()) {
						check = rs.getInt("cnt");
					}

					if (check == 1 && NewPas1.equals(NewPas2)) {
						String sql1 = "UPDATE users SET haslo=md5('" + NewPas1 + "') WHERE login='" + Window.Mlogin
								+ "'";
						PS = DatabaseConnection.conn.prepareStatement(sql1);
						PS.executeUpdate();

						JOptionPane.showMessageDialog(this, "Hasło zmieniono pomyślnie");
						dispose();

					} else {
						JOptionPane.showMessageDialog(this,
								"Podałeś niepoprawne stare hasło lub Twoje nowe hasła nie są takie same.");
					}

				} catch (Exception a) {

					JOptionPane.showMessageDialog(null, a);

				}

			} else {
				JOptionPane.showMessageDialog(this, "Nowe hasło musi się różnić od starego!");
			}
		}

		if (source == btnBack) {
			this.dispose();
		}
	}
}
