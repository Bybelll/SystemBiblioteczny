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
import javax.swing.JTextField;
import javax.swing.JButton;

public class PassChange extends JFrame implements ActionListener {

	private JTextField oldPass;
	private JTextField newPass;
	private JTextField newPass1;
	JButton btnChangePass;
	String OldPas, NewPas2, NewPas1, haslobaza, haslonowe;
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

		JLabel lblStareHaso = new JLabel("stare has\u0142o");
		lblStareHaso.setBounds(130, 72, 122, 20);
		this.add(lblStareHaso);

		oldPass = new JTextField();
		oldPass.setBounds(130, 91, 146, 26);
		this.add(oldPass);
		oldPass.setColumns(10);

		newPass = new JTextField();
		newPass.setBounds(130, 152, 146, 26);
		this.add(newPass);
		newPass.setColumns(10);

		JLabel lblNPass = new JLabel("nowe has\u0142o");
		lblNPass.setBounds(130, 130, 100, 20);
		this.add(lblNPass);

		JLabel lblnPass1 = new JLabel("powt\u00F3rz nowe has\u0142o");
		lblnPass1.setBounds(130, 197, 189, 20);
		this.add(lblnPass1);

		newPass1 = new JTextField();
		newPass1.setBounds(130, 217, 146, 26);
		this.add(newPass1);
		newPass1.setColumns(10);

		btnChangePass = new JButton("Zmie\u0144 has\u0142o");
		btnChangePass.setBounds(137, 267, 139, 29);
		this.add(btnChangePass);
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

			OldPas = oldPass.getText();
			NewPas1 = newPass.getText();
			NewPas2 = newPass1.getText();
			if (!OldPas.equals(NewPas1) || !OldPas.equals(NewPas2)) {
				try {
					String sql = "SELECT count(*) as cnt from users where login='" + Window.Mlogin + "' AND haslo='"
							+ OldPas + "'";
					PST = conn.createStatement();
					rs = PST.executeQuery(sql);
					Integer check = null;
					if (rs.next()) {
						check = rs.getInt("cnt");
					}

					if (check == 1 && NewPas1.equals(NewPas2)) {
						String sql1 = "UPDATE users SET haslo='" + NewPas1 + "' WHERE login='" + Window.Mlogin + "'";
						PS = conn.prepareStatement(sql1);
						PS.executeUpdate();

						JOptionPane.showMessageDialog(this, "Has�o zmieniono pomy�lnie");
						dispose();

					} else {
						JOptionPane.showMessageDialog(this,
								"Poda�e�/a� niepoprawne stare has�o lub Twoje nowe has�a nie s� takie same.");
					}

				} catch (Exception a) {

					JOptionPane.showMessageDialog(null, a);

				}

			} else {
				JOptionPane.showMessageDialog(this, "Nowe has�o musi si� r�ni� od starego!");
			}
		}
	}
}
