package SystemB;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class WindowSemiFin extends JFrame implements ActionListener {

	JFrame frame;
	JTextField txtSzukaj;
	JButton btnSzukaj;
	JButton btnWyloguj;
	JTable table;
	private JTable table_1;
	JButton btnMojeKonto;



	public WindowSemiFin() {
		setSize(800, 400);
		setTitle("System Biblioteczny - Program g³ówny");
		getContentPane().setLayout(null);
		
		txtSzukaj = new JTextField();
		txtSzukaj.setText("Szukaj..");
		txtSzukaj.setBounds(153, 32, 339, 33);
		getContentPane().add(txtSzukaj);
		txtSzukaj.setColumns(10);
		
		JRadioButton rdbtnTytu = new JRadioButton("Tytu\u0142");
		rdbtnTytu.setBounds(98, 66, 69, 29);
		getContentPane().add(rdbtnTytu);
		
		JRadioButton rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setBounds(174, 66, 83, 29);
		getContentPane().add(rdbtnAutor);
		
		JRadioButton rdbtnRokPublikacji = new JRadioButton("Rok publikacji");
		rdbtnRokPublikacji.setBounds(256, 66, 155, 29);
		getContentPane().add(rdbtnRokPublikacji);
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setBounds(495, 34, 115, 29);
		getContentPane().add(btnSzukaj);
		btnSzukaj.addActionListener(this);
		
		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setBounds(663, 0, 115, 29);
		getContentPane().add(btnWyloguj);
		btnWyloguj.addActionListener(this);
		
		table_1 = new JTable();
		table_1.setBounds(108, 107, 523, 221);
		getContentPane().add(table_1);
		
		JButton btnWypoycz = new JButton("Wypo\u017Cycz");
		btnWypoycz.setBounds(648, 299, 115, 29);
		getContentPane().add(btnWypoycz);
		
		JButton btnSprawd = new JButton("Sprawdz status");
		btnSprawd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSprawd.setBounds(630, 263, 148, 33);
		getContentPane().add(btnSprawd);
		
		btnMojeKonto = new JButton("Moje Konto");
		btnMojeKonto.setBounds(547, 0, 115, 29);
		getContentPane().add(btnMojeKonto);
		btnMojeKonto.addActionListener(this);
	
		
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if (source==btnWyloguj){
			dispose();
			Window Window= new Window();
			Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Window.setVisible(true);	
		}
		else if(source==btnMojeKonto){
			WindowAccount WindowAccount= new WindowAccount();
			WindowAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			WindowAccount.setVisible(true);	
		}
		else if(source==btnWyloguj){
			Window Window= new Window();
			Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Window.setVisible(true);	
		}
		
	}
}
