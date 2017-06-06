package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;


public class Window extends JFrame implements ActionListener {

	static String Mlogin;
	static String MUserID;
	static int MUserType;
	
	JButton bSingUp, bSignIn;
	JLabel lWelcome;
	JLabel lLogin, lPassword;
	JTextField tLogin, tPassword;

	ResultSet RS = null;
	PreparedStatement PST = null;

	public Window() {
		getContentPane().setBackground(Color.WHITE);
		setSize(840, 600);
		setTitle("System Biblioteczny");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 300, 100, 100, 100, 219, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 35, 20, 0, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		bSingUp = new JButton("Zarejestruj");
		GridBagConstraints gbc_bSingUp = new GridBagConstraints();
		gbc_bSingUp.anchor = GridBagConstraints.EAST;
		gbc_bSingUp.fill = GridBagConstraints.VERTICAL;
		gbc_bSingUp.insets = new Insets(0, 0, 5, 0);
		gbc_bSingUp.gridx = 4;
		gbc_bSingUp.gridy = 0;
		getContentPane().add(bSingUp, gbc_bSingUp);
		bSingUp.addActionListener(this);

		lWelcome = new JLabel("Witamy w naszym systemie");
		lWelcome.setFont(new Font("Serif", Font.BOLD, 25));
		lWelcome.setForeground(Color.BLUE);
		GridBagConstraints gbc_lWelcome = new GridBagConstraints();
		gbc_lWelcome.gridwidth = 3;
		gbc_lWelcome.fill = GridBagConstraints.BOTH;
		gbc_lWelcome.insets = new Insets(40, 0, 40, 5);
		gbc_lWelcome.gridx = 1;
		gbc_lWelcome.gridy = 2;
		getContentPane().add(lWelcome, gbc_lWelcome);

		lLogin = new JLabel("Login");
		GridBagConstraints gbc_lLogin = new GridBagConstraints();
		gbc_lLogin.anchor = GridBagConstraints.EAST;
		gbc_lLogin.insets = new Insets(0, 0, 5, 15);
		gbc_lLogin.gridx = 1;
		gbc_lLogin.gridy = 3;
		getContentPane().add(lLogin, gbc_lLogin);

		tLogin = new JTextField();
		GridBagConstraints gbc_tLogin = new GridBagConstraints();
		gbc_tLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_tLogin.insets = new Insets(0, 0, 5, 5);
		gbc_tLogin.gridx = 2;
		gbc_tLogin.gridy = 3;
		getContentPane().add(tLogin, gbc_tLogin);
		tLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
		tLogin.setColumns(15);

		lPassword = new JLabel("Haslo");
		GridBagConstraints gbc_lPassword = new GridBagConstraints();
		gbc_lPassword.anchor = GridBagConstraints.EAST;
		gbc_lPassword.insets = new Insets(0, 0, 5, 15);
		gbc_lPassword.gridx = 1;
		gbc_lPassword.gridy = 4;
		getContentPane().add(lPassword, gbc_lPassword);

		tPassword = new JPasswordField();
		GridBagConstraints gbc_tPassword = new GridBagConstraints();
		gbc_tPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tPassword.gridx = 2;
		gbc_tPassword.gridy = 4;
		getContentPane().add(tPassword, gbc_tPassword);
		tPassword.setColumns(15);

		bSignIn = new JButton("Zaloguj");
		GridBagConstraints gbc_bSignIn = new GridBagConstraints();
		gbc_bSignIn.gridwidth = 3;
		gbc_bSignIn.fill = GridBagConstraints.VERTICAL;
		gbc_bSignIn.insets = new Insets(0, 0, 0, 5);
		gbc_bSignIn.gridx = 1;
		gbc_bSignIn.gridy = 6;
		getContentPane().add(bSignIn, gbc_bSignIn);
		bSignIn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == bSignIn) {
			DatabaseConnection.conn = DatabaseConnection.ConnectDbs();
			try {
				String sql = "select user_id, admin from users where login=? and haslo=md5(?)";
				PST = DatabaseConnection.conn.prepareStatement(sql);
				PST.setString(1, tLogin.getText());
				PST.setString(2, tPassword.getText());
				RS = PST.executeQuery();
				if (RS.next()) {

					Mlogin = tLogin.getText();
					MUserID = RS.getString("user_id");
					MUserType = RS.getInt("admin");
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
			windowSignUp.createFrame();
			this.dispose();
		}

	}

}
