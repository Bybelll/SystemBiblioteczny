package SystemB;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JPasswordField;


public class WindowMain extends JFrame implements ActionListener {

	JFrame frame;
	JTextField txtSzukaj;
	JButton btnSzukaj;
	JButton btnWyloguj;
	JTable table;
	JRadioButton rdbtnTytu; 
	JRadioButton rdbtnAutor;
	JRadioButton rdbtnRokPublikacji; 
	JButton btnMojeKonto;
	private JPasswordField passwordField;

	public WindowMain() {
		setSize(800, 400);
		setTitle("System Biblioteczny - Program glowny");
		getContentPane().setLayout(null);
		
		txtSzukaj = new JTextField();
		txtSzukaj.setText("Szukaj..");
		txtSzukaj.setBounds(153, 32, 339, 33);
		getContentPane().add(txtSzukaj);
		txtSzukaj.setColumns(10);
		
		rdbtnTytu = new JRadioButton("Tytu\u0142");
		rdbtnTytu.setBounds(98, 66, 69, 29);
		getContentPane().add(rdbtnTytu);
		
		rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setBounds(174, 66, 83, 29);
		getContentPane().add(rdbtnAutor);
		
		rdbtnRokPublikacji = new JRadioButton("Rok publikacji");
		rdbtnRokPublikacji.setBounds(256, 66, 155, 29);
		getContentPane().add(rdbtnRokPublikacji);
		
		btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setBounds(495, 34, 115, 29);
		getContentPane().add(btnSzukaj);
		btnSzukaj.addActionListener(this);
		
		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setBounds(663, 0, 115, 29);
		getContentPane().add(btnWyloguj);
		
		btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.setBounds(548, 0, 115, 29);
		getContentPane().add(btnMojeKonto);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(202, 174, 128, 29);
		getContentPane().add(passwordField);
		btnMojeKonto.addActionListener(this);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source==btnSzukaj)
		{
			WindowSemiFin WindowSemiFin= new WindowSemiFin();
			WindowSemiFin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			WindowSemiFin.setVisible(true);
			
		}
		
		else if(source==btnWyloguj){
			Window Window= new Window();
			Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Window.setVisible(true);	
		}
		
		else if(source==btnMojeKonto){
			WindowAccount WindowAccount= new WindowAccount();
			WindowAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			WindowAccount.setVisible(true);	
		}
		
	}
}
