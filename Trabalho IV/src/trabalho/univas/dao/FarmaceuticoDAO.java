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

import trabalho.univas.vo.Farmaceutico;
import trabalho.univas.vo.Farmacia;



public class FarmaceuticoDAO {

	private Connection connection;
	
	public FarmaceuticoDAO() throws SQLException {
		connection = ConnectionUtil.getConnection();
	}
	
	public void save1(Farmaceutico farmaceutico) {
		String sql = "insert into farmaceutico (nome, crf, uf, id) "
				+ "values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			statement.setString(index++, farmaceutico.getNameFarmaceutico());
			statement.setString(index++, farmaceutico.getCrf());
			statement.setString(index++, farmaceutico.getUf());
			statement.setInt(index++, farmaceutico.getId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Farmaceutico> getAll() {
		List<Farmaceutico> farmaceuticos = new ArrayList<Farmaceutico>();
		
		String sql = "select * from Farmaceutico";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Farmaceutico farmaceutico = new Farmaceutico();
				farmaceutico.setNameFarmaceutico(result.getString("nome"));
				farmaceutico.setCrf(result.getString("crf"));
				farmaceutico.setUf(result.getString("uf"));
				farmaceutico.setId(result.getInt("id"));
				farmaceuticos.add(farmaceutico);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return farmaceuticos;
	}
	
	public void update(Farmaceutico farmaceutico) throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET nome = ? ,crf = ?, uf = ? WHERE id = ?");
            stmt.setString(1, farmaceutico.getNameFarmaceutico());
            stmt.setString(2, farmaceutico.getCrf());
            stmt.setString(3, farmaceutico.getUf());
            stmt.setInt(4, farmaceutico.getId());
            
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt);
        }

    }
    public void delete2(Farmaceutico farmaceutico) throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from farmaceutico where id = ?");
            stmt.setInt(1, farmaceutico.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt);
        }

    }
    
    public List<Farmaceutico> read() throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet result = null;

        List<Farmaceutico> farmaceuticos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM farmacia");
            result = stmt.executeQuery();

            while (result.next()) {

                Farmaceutico farmaceutico = new Farmaceutico();
                farmaceutico.setNameFarmaceutico(result.getString("nome"));
                farmaceutico.setCrf(result.getString("crf"));
                farmaceutico.setUf(result.getString("uf"));
      			farmaceutico.setId(result.getInt("id"));
				farmaceuticos.add(farmaceutico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt, result);
        }

        return farmaceuticos;

    }
}