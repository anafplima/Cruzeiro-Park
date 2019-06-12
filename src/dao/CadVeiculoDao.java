package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Connection.Conexao;
import model.Veiculo;

public class CadVeiculoDao {
	
	public Veiculo getVeiculo(String placa) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Veiculo veiculo=null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa=?");
			stmt.setString(1, placa);
			result = stmt.executeQuery();
			if(result.first()) {
				veiculo = new Veiculo();
				veiculo.setPlaca(result.getString("placa"));
				VagasDao vagasDao = new VagasDao();
				veiculo.setVaga(vagasDao.getVaga(result.getString("tipoVaga")));
				MotoristaDao motoristaDao = new MotoristaDao();
				veiculo.setMotorista(motoristaDao.getMotorista(result.getInt("cnh")));
				veiculo.setModelo(result.getString("modelo"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Veículo não cadastrado");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return veiculo;
	}
	
	
	public String cadastrarVeiculo(Veiculo veiculo) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String mensagemErro = "";
		
		try {
			stmt = con.prepareStatement("SELECT * FROM motorista WHERE cnh=?");
			stmt.setInt(1, veiculo.getMotorista().getCnh());
			result = stmt.executeQuery();
			if(!result.first()) {
				stmt=null;
				try {
					stmt = con.prepareStatement("INSERT INTO motorista VALUES (?, ?)");
					stmt.setInt(1, veiculo.getMotorista().getCnh());
					stmt.setString(2, veiculo.getMotorista().getNome());
					stmt.executeUpdate();
				} catch(SQLException e) {
					Conexao.closeConnection(con, stmt, result);
					//e.printStackTrace();
					return mensagemErro = "Erro ao cadastrar motorista.";
				}
			}
		}catch (SQLException e) {
			Conexao.closeConnection(con, stmt, result);
			return mensagemErro = "Erro ao verificar o motorista.";
		}
		
		try {
			stmt = con.prepareStatement("INSERT INTO veiculo VALUES (?, ?, ?, ?)");
			stmt.setString(1, veiculo.getPlaca());
			stmt.setString(2, veiculo.getVaga().getTipoVaga());
			stmt.setInt(3, veiculo.getMotorista().getCnh());
			stmt.setString(4, veiculo.getModelo());
			stmt.executeUpdate();
		} catch(SQLException e) {
			mensagemErro = "Erro ao cadastrar motorista.";
		} finally {
			Conexao.closeConnection(con, stmt, result);
		}
		return mensagemErro;
	}
	
	public Boolean veiculoJaCadastrado(Veiculo veiculo) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Boolean retorno= false;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa=?");
			stmt.setString(1, veiculo.getPlaca());
			result = stmt.executeQuery();
			if(result.first()) {
				retorno= true;
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao verificar veiculo.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		return retorno;
	}
	
	
	public DefaultComboBoxModel getTiposDeVagas() {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<String> strList = new ArrayList<String>();
		DefaultComboBoxModel defaultComboBox = null;
		
		try {
			stmt = con.prepareStatement("SELECT tipoVaga FROM vagas");
			result = stmt.executeQuery();
			while(result.next()){
				strList.add(result.getString("tipoVaga"));
			}
			
			defaultComboBox = new DefaultComboBoxModel(strList.toArray());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao fazer login.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return defaultComboBox;
	}
}
