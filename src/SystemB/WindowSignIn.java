package SystemB;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;



public class WindowSignIn extends JFrame implements ActionListener{

	JButton bSignIn;
	JLabel lLogin,lPassword;
	JTextField tLogin,tPassword;
	static String Mlogin;
	static String MUserID;
	
	Connection conn = null;
	ResultSet RS = null;
	PreparedStatement PST = null;
	

	
	public WindowSignIn() {
		setSize(400, 300);
		setTitle("System Biblioteczny - Logowanie");
		setLayout(null);
		
		
		conn=DatabaseConnection.ConnectDbs();
		
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
		
		tPassword = new JPasswordField();
		tPassword.setBounds(111, 41, 100, 20);
		add(tPassword);
		
		
		tLogin = new JTextField();
		tLogin.setBounds(111, 20, 100, 20);
		add(tLogin);
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source==bSignIn){
	
			

			try{
				String sql="select user_id from users where login=? and haslo=?";
				PST=conn.prepareStatement(sql);
				PST.setString(1, tLogin.getText());
				PST.setString(2, tPassword.getText());
				RS=PST.executeQuery();
				if(RS.next()){
					
					Mlogin = tLogin.getText();
					MUserID = RS.getString("user_id");
					dispose();
					WindowSemiFin WindowMain= new WindowSemiFin();
					WindowMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					WindowMain.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null,"Coœ posz³o nie tak, spróbuj ponownie.");
				}
				
				
			}catch(Exception a){
				JOptionPane.showMessageDialog(null,a);
			}
//			JOptionPane.showMessageDialog(null,"Zalogowano pomyÅ›lnie.");
			
			
		}
	}

}
