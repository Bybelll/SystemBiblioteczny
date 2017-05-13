package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class PassChange extends JFrame implements ActionListener {

	private JPasswordField oldPass, newPass, newPass1;
	JButton btnChangePass, btnBack;
	String OldPas;
	String NewPas2;
	String haslobaza, haslonowe;
	String NewPas1;
	Connection conn;
	ResultSet rs;
	Statement PST;
	PreparedStatement PS = null;
	JLabel lblNPass, lblnPass1, lblStareHaso;

	public PassChange() {

		setSize(800, 436);
		setTitle("Zmiana has�a");
		setLayout(null);

		conn = DatabaseConnection.ConnectDbs();

		JLabel lblStareHaso = new JLabel("stare haslo");
		lblStareHaso.setBounds(130, 72, 122, 20);
		this.add(lblStareHaso);

		oldPass = new JPasswordField();
		oldPass.setBounds(130, 91, 146, 26);
		this.add(oldPass);
		oldPass.setColumns(10);

		newPass = new JPasswordField();
		newPass.setBounds(130, 152, 146, 26);
		this.add(newPass);
		newPass.setColumns(10);

		JLabel lblNPass = new JLabel("nowe haslo");
		lblNPass.setBounds(130, 130, 100, 20);
		this.add(lblNPass);

		JLabel lblnPass1 = new JLabel("powtorz nowe haslo");
		lblnPass1.setBounds(130, 197, 189, 20);
		this.add(lblnPass1);

		newPass1 = new JPasswordField();
		newPass1.setBounds(130, 217, 146, 26);
		this.add(newPass1);
		newPass1.setColumns(10);

		btnChangePass = new JButton("Zmień hasło");
		btnChangePass.setBounds(260, 267, 139, 29);
		this.add(btnChangePass);
		btnChangePass.addActionListener(this);

		btnBack = new JButton("Wróć");
		btnBack.setBounds(100, 267, 139, 29);
		this.add(btnBack);
		btnBack.addActionListener(this);

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
					PST = conn.createStatement();
					rs = PST.executeQuery(sql);
					Integer check = null;
					if (rs.next()) {
						check = rs.getInt("cnt");
					}

					if (check == 1 && NewPas1.equals(NewPas2)) {
						String sql1 = "UPDATE users SET haslo=md5('" + NewPas1 + "') WHERE login='" + Window.Mlogin
								+ "'";
						PS = conn.prepareStatement(sql1);
						PS.executeUpdate();

						JOptionPane.showMessageDialog(this, "Has�o zmieniono pomy�lnie");
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
