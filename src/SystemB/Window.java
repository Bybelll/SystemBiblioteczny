package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {

	JButton bSingUp, bSignIn;
	JLabel lWelcome;
	JLabel lLogin, lPassword;
	JTextField tLogin, tPassword;
	static String Mlogin;
	static String MUserID;
	static String MUserType;

	Connection conn = null;
	ResultSet RS = null;
	PreparedStatement PST = null;

	public Window() {
		setSize(840, 600);
		setTitle("System Biblioteczny");
		setLayout(null);

		conn = DatabaseConnection.ConnectDbs();

		lWelcome = new JLabel("Witamy w naszym systemie");
		lWelcome.setFont(new Font("Serif", Font.BOLD, 25));
		lWelcome.setForeground(Color.BLUE);
		lWelcome.setBounds(300, 90, 500, 35);
		add(lWelcome);

		bSingUp = new JButton("Zarejestruj");
		bSingUp.setBounds(720, 0, 100, 20);
		add(bSingUp);
		bSingUp.addActionListener(this);

		bSignIn = new JButton("Zaloguj");
		bSignIn.setBounds(401, 210, 100, 20);
		add(bSignIn);
		bSignIn.addActionListener(this);

		lLogin = new JLabel("Login");
		lLogin.setBounds(310, 150, 50, 20);
		add(lLogin);

		lPassword = new JLabel("Haslo");
		lPassword.setBounds(310, 171, 50, 20);
		add(lPassword);

		tPassword = new JPasswordField();
		tPassword.setBounds(401, 171, 100, 20);
		add(tPassword);

		tLogin = new JTextField();
		tLogin.setBounds(401, 150, 100, 20);
		add(tLogin);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == bSignIn) {

			try {
				String sql = "select user_id, admin from users where login=? and haslo=md5(?)";
				PST = conn.prepareStatement(sql);
				PST.setString(1, tLogin.getText());
				PST.setString(2, tPassword.getText());
				RS = PST.executeQuery();
				if (RS.next()) {

					Mlogin = tLogin.getText();
					MUserID = RS.getString("user_id");
					MUserType = RS.getString("admin");
					dispose();
					MainWindow WindowMain = new MainWindow();
					WindowMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					WindowMain.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Co� posz�o nie tak, spr�buj ponownie.");
				}

			} catch (Exception a) {
				JOptionPane.showMessageDialog(null, a);
			}

		} else if (source == bSingUp) {
			WindowSignUp windowSignUp = new WindowSignUp();
			windowSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowSignUp.setVisible(true);
			this.dispose();
		}

	}

}
