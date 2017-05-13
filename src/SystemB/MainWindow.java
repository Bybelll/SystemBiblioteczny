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

	JButton btnKatalog, btnKonto, btnWyloguj, btnUzytkownicy;
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
		menu_panel.setLayout(new GridLayout(0, 4, 0, 0));

		btnKatalog = new JButton("katalog");
		btnKatalog.setForeground(new Color(0, 0, 0));
		btnKatalog.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnKatalog);
		btnKatalog.addActionListener(listener);

		btnKonto = new JButton("konto");
		btnKonto.setForeground(Color.BLACK);
		btnKonto.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnKonto);
		btnKonto.addActionListener(listener);

		if (Window.MUserType == 1) {

			btnUzytkownicy = new JButton("użytkownicy");
			btnUzytkownicy.setForeground(Color.BLACK);
			btnUzytkownicy.setBackground(UIManager.getColor("Button.background"));
			menu_panel.add(btnUzytkownicy);
			btnUzytkownicy.addActionListener(listener);
		}

		btnWyloguj = new JButton("wyloguj");
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
		content_panel.add(catalogue, "Katalog");
		content_panel.add(profile, "Konto");
		content_panel.add(users, "Users");

	}

	public void Wyloguj() {
		Window window = new Window();
		window.setVisible(true);
		dispose();
		Window.Mlogin = null;
		Window.MUserID = null;
		Window.MUserType = 0;
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			CardLayout cl = (CardLayout) (content_panel.getLayout());

			if (source == btnKatalog) {
				// JOptionPane.showMessageDialog(null,"btnNewButton");
				// WindowSemiFin catalogue = new WindowSemiFin();
				// content_panel.removeAll();
				// content_panel.add(catalogue);
				// content_panel.revalidate();
				cl.show(content_panel, "Katalog");

			} else if (source == btnKonto) {

				cl.show(content_panel, "Konto");

			} else if (source == btnUzytkownicy) {

				cl.show(content_panel, "Users");

			} else if (source == btnWyloguj) {
				Wyloguj();
			}
		}

	}

}
