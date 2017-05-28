package SystemB;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.UIManager;

public class MainWindow extends JFrame {

	JButton btnKatalog, btnKonto, btnWyloguj, btnUzytkownicy, btnNowyUzytkownik, btnDodaj;
	JPanel content_panel;

	public MainWindow() {
		setBackground(Color.WHITE);

		setTitle("System Biblioteczny");
		setSize(800, 600);
		setMinimumSize(new Dimension(640, 480));
		ButtonHandler listener = new ButtonHandler();
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel menu_panel = new JPanel();
		menu_panel.setBackground(Color.WHITE);
		getContentPane().add(menu_panel);
		menu_panel.setLayout(new GridLayout(0, 3, 0, 0));

		btnKatalog = new JButton("Katalog książek");
		btnKatalog.setForeground(new Color(0, 0, 0));
		btnKatalog.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnKatalog);
		btnKatalog.addActionListener(listener);

		if (Window.MUserType == 1) {
		btnDodaj = new JButton("Dodaj książkę");
		btnDodaj.setForeground(new Color(0, 0, 0));
		btnDodaj.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnDodaj);
		btnDodaj.addActionListener(listener);
		}
		
		btnKonto = new JButton("Moje konto");
		btnKonto.setForeground(Color.BLACK);
		btnKonto.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnKonto);
		btnKonto.addActionListener(listener);

		if (Window.MUserType == 1) {

			btnUzytkownicy = new JButton("Użytkownicy");
			btnUzytkownicy.setForeground(Color.BLACK);
			btnUzytkownicy.setBackground(UIManager.getColor("Button.background"));
			menu_panel.add(btnUzytkownicy);
			btnUzytkownicy.addActionListener(listener);
			
			btnNowyUzytkownik = new JButton("Dodaj użytkownika");
			btnNowyUzytkownik.setForeground(Color.BLACK);
			btnNowyUzytkownik.setBackground(UIManager.getColor("Button.background"));
			menu_panel.add(btnNowyUzytkownik);
			btnNowyUzytkownik.addActionListener(listener);
		}

		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.setForeground(Color.BLACK);
		btnWyloguj.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnWyloguj);
		btnWyloguj.addActionListener(listener);

		content_panel = new JPanel();
		content_panel.setBackground(Color.WHITE);
		getContentPane().add(content_panel);
		content_panel.setLayout(new CardLayout(0, 0));

		WindowSemiFin catalogue = new WindowSemiFin();
		WindowAccount profile = new WindowAccount(Window.MUserID);
		UserList users = new UserList();
		WindowSignUp adduser = new WindowSignUp();
		AddBook addbook = new AddBook();
		content_panel.add(catalogue, "Katalog");
		content_panel.add(profile, "Konto");
		content_panel.add(users, "Users");
		content_panel.add(adduser, "NewUser");
		content_panel.add(addbook, "NewBook");

	}


	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			CardLayout cl = (CardLayout) (content_panel.getLayout());

			if (source == btnKatalog) {

				WindowSemiFin catalogue = new WindowSemiFin();
				content_panel.add(catalogue,"Katalog");
				cl.show(content_panel, "Katalog");

			} else if (source == btnKonto) {
				
				WindowAccount profile = new WindowAccount(Window.MUserID);
				content_panel.add(profile, "Konto");
				cl.show(content_panel, "Konto");

			}else if (source ==btnNowyUzytkownik)
			{
				cl.show(content_panel, "NewUser");
			} else if (source == btnUzytkownicy) {

				cl.show(content_panel, "Users");

			} else if (source == btnWyloguj) {
				Wyloguj();
			}
			else if (source ==btnDodaj)
			{
				cl.show(content_panel, "NewBook");
			}
		}

	}
	
	
	/*
	 * Signing out. setting default variables of login (Mlogin, MUserID, MUserType) and closing the connection.
	 */
	public void Wyloguj() {
		Window window = new Window();
		window.setVisible(true);
		dispose();
		Window.Mlogin = null;
		Window.MUserID = null;
		Window.MUserType = 0;
		
		try {
			DatabaseConnection.conn.close();
		
		} catch (Exception e) {
			/* ignored */
		}
	}

}
