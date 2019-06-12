package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import Connection.Conexao;
import model.Funcionario;
import model.Veiculo;

public class CadFunDao {
	public String cadastrarFuncionario(Funcionario funcionario) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String mensagemErro = "";
		int idEndereco=0;
		
		try {
			stmt = con.prepareStatement("INSERT INTO endereco (rua, numCasa, bairro, cidade, cep, estado) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, funcionario.getEndereco().getRua());
			stmt.setInt(2, funcionario.getEndereco().getNumCasa());
			stmt.setString(3, funcionario.getEndereco().getBairro());
			stmt.setString(4, funcionario.getEndereco().getCidade());
			stmt.setInt(5, funcionario.getEndereco().getCep());
			stmt.setString(6, funcionario.getEndereco().getEstado());
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			if(result.next()) {
				idEndereco= result.getInt(1);
			}
		} catch(SQLException e) {
			Conexao.closeConnection(con, stmt, result);
			return mensagemErro = "Erro ao cadastrar endereço.";
		} 
		
		try {
			stmt = con.prepareStatement("INSERT INTO funcionario VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, funcionario.getCartTrabalho());
			stmt.setInt(2, idEndereco);
			stmt.setString(3, funcionario.getNome());
			stmt.setInt(4, funcionario.getCpf());
			stmt.setInt(5, funcionario.getTelefone());
			stmt.setString(6, funcionario.getCargo());
			stmt.setString(7, funcionario.getLogin());
			stmt.setString(8, funcionario.getSenha());
					
			stmt.executeUpdate();
		}catch (SQLException e) {
			mensagemErro = "Erro ao cadastrar funcionario.";
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		return mensagemErro;
	}
	
	public Boolean funcionarioJaCadastrado(Funcionario funcionario) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Boolean retorno= false;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM funcionario WHERE cartTrabalho=?");
			stmt.setInt(1, funcionario.getCartTrabalho());
			result = stmt.executeQuery();
			if(result.first()) {
				retorno= true;
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar funcionario.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		return retorno;
	}
}
