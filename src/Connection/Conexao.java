package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	//private static final String DriverMeneger = null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:porta/estacionamento";
	private static final String USER = ""; 
	private static final String PASS = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Erro na conexão: ", e);
			//e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection con) {
		try {
		if (con != null) {
				con.close();
			} 
		}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
	closeConnection(con);
	try {
	if (stmt != null) {
			stmt.close();
		} 
	}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
	closeConnection(con);
	try {
	if (rs != null) {
			rs.close();
		} 
	}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

