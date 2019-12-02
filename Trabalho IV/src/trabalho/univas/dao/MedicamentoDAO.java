package trabalho.univas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import trabalho.univas.vo.Farmacia;
import trabalho.univas.vo.Medicamento;

public class MedicamentoDAO {

	private Connection connection;
	
	public MedicamentoDAO() throws SQLException {
		connection = ConnectionUtil.getConnection();
	}
	
	public void save2(Medicamento medicamento) {
		String sql = "insert into medicamento (nome, lote, data_validade, valor, quantidade, id) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			statement.setString(index++, medicamento.getNameMedicamento());
			statement.setString(index++, medicamento.getLote());
			statement.setString(index++, medicamento.getDataValidade());
			statement.setString(index++, medicamento.getValor());
			statement.setString(index++, medicamento.getQuantidade());
			statement.setInt(index++, medicamento.getId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public List<Medicamento> getAll() {
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		
		String sql = "select * from Medicamento";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Medicamento medicamento = new Medicamento();
				medicamento.setNameMedicamento(result.getString("nome"));
				medicamento.setLote(result.getString("lote"));
				medicamento.setDataValidade(result.getString("data_validade"));
				medicamento.setValor(result.getString("valor"));
				medicamento.setQuantidade(result.getString("quantidade"));
				medicamento.setId(result.getInt("id"));
				medicamentos.add(medicamento);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return medicamentos;
	}
	
	public void update(Medicamento medicamento) throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE medicamento SET nome = ? ,lote = ?, data_validade = ?, valor = ?, quantidade = ? WHERE id = ?");
            stmt.setString(1, medicamento.getNameMedicamento());
            stmt.setString(2, medicamento.getLote());
            stmt.setString(3, medicamento.getDataValidade());
            stmt.setString(4, medicamento.getValor());
            stmt.setString(5, medicamento.getQuantidade());
            stmt.setInt(6, medicamento.getId());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt);
        }

    }
    public void delete3(Medicamento medicamento) throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from medicamento where id = ?");
            stmt.setInt(1, medicamento.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt);
        }

    }
    
    public List<Medicamento> read() throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet result = null;

        List<Medicamento> medicamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM medicamento");
            result = stmt.executeQuery();

            while (result.next()) {

                Medicamento medicamento = new Medicamento();
                medicamento.setNameMedicamento(result.getString("nome"));
                medicamento.setLote(result.getString("lote"));
                medicamento.setDataValidade(result.getString("data_validade"));
                medicamento.setValor(result.getString("valor"));
				medicamento.setQuantidade(result.getString("quantidade"));
				medicamento.setId(result.getInt("id"));
				medicamentos.add(medicamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt, result);
        }

        return medicamentos;

    }
}