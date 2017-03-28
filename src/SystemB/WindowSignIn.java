package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WindowSignIn extends JFrame implements ActionListener{

	JButton bSignIn;
	JLabel lLogin,lPassword;
	JTextField tLogin,tPassword;
	
	public WindowSignIn() {
		setSize(400, 300);
		setTitle("System Biblioteczny - Logowanie");
		setLayout(null);
		
		bSignIn = new JButton("Zaloguj");
		bSignIn.setBounds(150, 200, 100, 20);
		add(bSignIn);
		bSignIn.addActionListener(this);
		
		lLogin = new JLabel("Login");
		lLogin.setBounds(10, 20, 100, 20);
		add(lLogin);
		
		lPassword = new JLabel("Haslo");
		lPassword.setBounds(10, 41, 100, 20);
		add(lPassword);
		
		tLogin = new JTextField();
		tLogin.setBounds(111, 20, 100, 20);
		add(tLogin);
		
		tPassword = new JTextField();
		tPassword.setBounds(111, 41, 100, 20);
		add(tPassword);
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source==bSignIn){
			dispose();
		}
	}

}
