package SystemB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame implements ActionListener{

	JButton bSingUp,bSingIn;
	JLabel lWelcome;
	
	public Window(){
		setSize(840,600);
		setTitle("System Biblioteczny");
		setLayout(null);
		
		lWelcome = new JLabel("Witamy w naszym systemie");
		lWelcome.setBounds(360, 50, 500,200);
		add(lWelcome);
		
		bSingUp = new JButton("Zarejestruj");
		bSingUp.setBounds(720, 0, 100, 20);
		add(bSingUp);
		
		bSingIn = new JButton("Zaloguj");
		bSingIn.setBounds(600, 0, 100, 20);
		add(bSingIn);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
}
