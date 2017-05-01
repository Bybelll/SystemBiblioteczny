package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame implements ActionListener {

	JButton bSingUp, bSingIn;
	JLabel lWelcome;	
	
	public Window() {
		setSize(840, 600);
		setTitle("System Biblioteczny");
		setLayout(null);

		lWelcome = new JLabel("Witamy w naszym systemie");
		lWelcome.setBounds(360, 50, 500, 200);
		add(lWelcome);

		bSingUp = new JButton("Zarejestruj");
		bSingUp.setBounds(720, 0, 100, 20);
		add(bSingUp);
		bSingUp.addActionListener(this);

		bSingIn = new JButton("Zaloguj");
		bSingIn.setBounds(600, 0, 100, 20);
		add(bSingIn);
		bSingIn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == bSingIn) {
			WindowSignIn windowSignIn= new WindowSignIn();
			windowSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowSignIn.setVisible(true);
			this.dispose();
			
			
		} else if (source == bSingUp) {
			WindowSignUp windowSignUp= new WindowSignUp();
			windowSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			windowSignUp.setVisible(true);
			this.dispose();
		}

	}

}
