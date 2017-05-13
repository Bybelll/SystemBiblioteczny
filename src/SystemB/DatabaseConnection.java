package SystemB;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnection {

	Connection conn = null;

	public static Connection ConnectDbs() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://sql11.freesqldatabase.com/sql11171543?characterEncoding=UTF-8", "sql11171543",
					"SMbd9wcgnH");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;

		}

	}

}
