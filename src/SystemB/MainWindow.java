package SystemB;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.UIManager;

public class MainWindow extends JFrame {

	JButton btnKatalog, btnNewButton_1, btnWyloguj, btnNewButton_3;
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

		btnNewButton_1 = new JButton("konto");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(listener);

		btnWyloguj = new JButton("wyloguj");
		btnWyloguj.setForeground(Color.BLACK);
		btnWyloguj.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnWyloguj);
		btnWyloguj.addActionListener(listener);

		btnNewButton_3 = new JButton("co�tam co�...");
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setBackground(UIManager.getColor("Button.background"));
		menu_panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(listener);

		content_panel = new JPanel();
		content_panel.setBackground(Color.WHITE);
		getContentPane().add(content_panel);
		content_panel.setLayout(new CardLayout(0, 0));

		WindowSemiFin catalogue = new WindowSemiFin();
		WindowAccount profile = new WindowAccount();
		content_panel.add(catalogue, "Katalog");
		content_panel.add(profile, "Konto");

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

			} else if (source == btnNewButton_1) {
				// JOptionPane.showMessageDialog(null,"btnNewButton_1");
				// WindowAccount profile = new WindowAccount();
				// content_panel.removeAll();
				// content_panel.add(profile);
				// content_panel.revalidate();
				cl.show(content_panel, "Konto");
				
			} else if (source == btnWyloguj) {
				Window window = new Window();
				window.setVisible(true);
				dispose();
			}
		}

	}

}
