package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Connection.Conexao;
import model.Motorista;

public class MotoristaDao {
	public Motorista getMotorista(int cnh) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Motorista motorista=null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM motorista WHERE cnh=?");
			stmt.setInt(1, cnh);
			result = stmt.executeQuery();
			if(result.first()) {
				motorista = new Motorista();
				motorista.setCnh(result.getInt("cnh"));
				motorista.setNome(result.getString("nome"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao encontrar motorista.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return motorista;
	}
}
