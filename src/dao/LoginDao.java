package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import Connection.Conexao;
import model.Login;

public class LoginDao {
	public Boolean fazerLogin(Login login) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Boolean retorno = false;
		try {
			stmt = con.prepareStatement("SELECT * FROM funcionario WHERE login=? AND senha=?");
			stmt.setString(1, login.getUsuario());
			stmt.setString(2, login.getSenha());
		
			result =  stmt.executeQuery();
			
			if(result.first()) {
				retorno = true;
			}
		
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao fazer login.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return retorno;
	}
}
