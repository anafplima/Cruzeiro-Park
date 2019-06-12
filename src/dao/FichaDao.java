package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connection.Conexao;
import model.Ficha;

public class FichaDao {
	
	public Ficha getFicha(int idFicha) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Ficha ficha=null;
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			stmt = con.prepareStatement("SELECT idFicha, placa, DATE_FORMAT(dtHrEntrada,'%Y-%m-%d %H:%i:%s') as dtHrEntrada, DATE_FORMAT(dtHrSaida,'%Y-%m-%d %H:%i:%s') as dtHrSaida, valorHora FROM ficha WHERE idFicha=?");
			stmt.setInt(1, idFicha);
			result = stmt.executeQuery();
			
			if(result.first()) {
				ficha = new Ficha();
				ficha.setIdFicha(result.getInt("idFicha"));
				//JOptionPane.showMessageDialog(null, result.getString("dtHrEntrada"));
				ficha.setDtHrEntrada(sdFormat.parse(result.getString("dtHrEntrada")));
				/*if(result.getString("dtHrSaida").isEmpty()) {
					ficha.setDtHrSaida(null);
				} else {
					ficha.setDtHrSaida(sdFormat.parse(result.getString("dtHrSaida")));
				}*/
				ficha.setValorHora(result.getFloat("valorHora"));
				CadVeiculoDao veiculoDao = new CadVeiculoDao();
				ficha.setVeiculo(veiculoDao.getVeiculo(result.getString("placa")));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar ficha");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return ficha;
	}
	
	public String cadastrarFicha(String placa) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		String mensagemErro = "";
		
	 	try {
			stmt = con.prepareStatement("INSERT INTO ficha (placa, dtHrEntrada) VALUES (?, NOW())");
			stmt.setString(1, placa);
			stmt.executeUpdate();
		}catch (SQLException e) {
			mensagemErro = "Erro ao registrar entrada no veículo.";
		}finally {
			Conexao.closeConnection(con, stmt);
		}
		return mensagemErro;
	}
	
	public String saidaFicha(Ficha ficha) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		String mensagemErro = "";
		
	 	try {
			stmt = con.prepareStatement("UPDATE ficha SET dtHrSaida=NOW(), valorHora=? WHERE idFicha=?");
			//stmt.setDate(1, Date.(ficha.getDtHrSaida()));
			stmt.setFloat(1, ficha.getValorHora());
			stmt.setInt(2, ficha.getIdFicha());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			mensagemErro = "Erro ao registrar saida do veículo.";
		}finally {
			Conexao.closeConnection(con, stmt);
		}
		return mensagemErro;
	}
	
	public List<Ficha> preencherTabela() {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Ficha> tabela = new ArrayList<>();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			stmt = con.prepareStatement("SELECT * FROM ficha WHERE dtHrSaida IS NULL");
			result = stmt.executeQuery();
			while(result.next()) {
				Ficha ficha=new Ficha();
				ficha.setIdFicha(result.getInt("idFicha"));
				ficha.setDtHrEntrada(sdFormat.parse(result.getString("dtHrEntrada")));
				CadVeiculoDao cadVeiculoDao = new CadVeiculoDao();
				ficha.setVeiculo(cadVeiculoDao.getVeiculo(result.getString("placa")));
				tabela.add(ficha);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao encontrar preencher tabela.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return tabela;
	}
	
	public String getModeloFrequente() {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String modelo = "";
		
		try {
			stmt = con.prepareStatement("SELECT v.modelo, count(*) AS qtd\r\n" + 
					"FROM ficha f INNER JOIN veiculo v ON f.placa=v.placa\r\n" + 
					"GROUP BY v.modelo\r\n" + 
					"ORDER BY qtd DESC\r\n" + 
					"LIMIT 1");
			result = stmt.executeQuery();
			
			if(result.first()) {
				modelo = result.getString("modelo");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar modelo frequente");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return modelo;
	}
}
