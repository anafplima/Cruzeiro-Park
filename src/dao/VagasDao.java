package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connection.Conexao;
import model.Vagas;

public class VagasDao {
	public Vagas getVaga(String tipoVaga) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Vagas vagas=null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM vagas WHERE tipoVaga=?");
			stmt.setString(1, tipoVaga);
			result = stmt.executeQuery();
			if(result.first()) {
				vagas = new Vagas();
				vagas.setTipoVaga(result.getString("tipoVaga"));
				vagas.setQtdVaga(result.getInt("qtdVaga"));
				vagas.setPrecoVaga(result.getFloat("precoVaga"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao encontrar vaga.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return vagas;
	}
	
	public String cadastrarVaga(Vagas vagas) {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		String mensagemErro = "";
		
	 	try {
			if(this.getVaga(vagas.getTipoVaga())==null) {
				stmt = con.prepareStatement("INSERT INTO vagas VALUES (?, ?, ?)");
				stmt.setString(1, vagas.getTipoVaga());
				stmt.setInt(2, vagas.getQtdVaga());
				stmt.setFloat(3, vagas.getPrecoVaga());
			} else {
				stmt = con.prepareStatement("UPDATE vagas SET qtdVaga=?, precoVaga=? WHERE tipoVaga=?");
				stmt.setInt(1, vagas.getQtdVaga());
				stmt.setFloat(2, vagas.getPrecoVaga());
				stmt.setString(3, vagas.getTipoVaga());
			}	
			stmt.executeUpdate();
		}catch (SQLException e) {
			mensagemErro = "Erro ao cadastrar vaga.";
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		return mensagemErro;
	}
	
	public List<Vagas> getQtdVagaLivre() {
		Connection con =  Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Vagas> vagas = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT va.tipoVaga, if(A.qtdOcupada is null, va.qtdVaga, (va.qtdVaga-A.qtdOcupada)) as qtdLivre\r\n" + 
					"FROM \r\n" + 
					"(SELECT v.tipoVaga, count(*) as qtdOcupada\r\n" + 
					"FROM ficha f INNER JOIN veiculo v on f.placa=v.placa\r\n" + 
					"WHERE dtHrSaida IS NULL\r\n" + 
					"GROUP BY v.tipoVaga) A\r\n" + 
					"RIGHT JOIN vagas va on A.tipoVaga=va.tipoVaga");
			
			result = stmt.executeQuery();
			while(result.next()) {
				Vagas vaga = new Vagas();
				vaga.setTipoVaga(result.getString("tipoVaga"));
				vaga.setQtdVaga(result.getInt("qtdLivre"));
				vagas.add(vaga);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao encontrar vaga.");
		}finally {
			Conexao.closeConnection(con, stmt, result);
		}
		
		return vagas;
	}
}
