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

import org.postgresql.core.ConnectionFactory;

import trabalho.univas.vo.Farmacia;



public class FarmaciaDAO {

	private Connection connection;
		
	public FarmaciaDAO() throws SQLException {
		connection = ConnectionUtil.getConnection();
	}
	
	public void save(Farmacia farmacia) {
		String sql = "insert into farmacia (nome, cnpj, cidade, rua, numero, id) "
				+ "values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int index = 1;
			statement.setString(index++, farmacia.getNameFarmacia());
			statement.setString(index++, farmacia.getCnpj());
			statement.setString(index++, farmacia.getCidade());
			statement.setString(index++, farmacia.getRua());
			statement.setString(index++, farmacia.getNumero());
			statement.setInt(index++, farmacia.getId());
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Farmacia> getAll() {
		List<Farmacia> farmacias = new ArrayList<Farmacia>();
		
		String sql = "select * from Farmacia";
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				Farmacia farmacia = new Farmacia();
				farmacia.setNameFarmacia(result.getString("nome"));
				farmacia.setCnpj(result.getString("cnpj"));
				farmacia.setCidade(result.getString("cidade"));
				farmacia.setRua(result.getString("rua"));
				farmacia.setNumero(result.getString("numero"));
				farmacia.setId(result.getInt("id"));
				farmacias.add(farmacia);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return farmacias;
	}
	
	public void update(Farmacia farmacia) throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET nome = ? ,cnpj = ?, cidade = ?, rua = ?, numero = ? WHERE id = ?");
            stmt.setString(1, farmacia.getNameFarmacia());
            stmt.setString(2, farmacia.getCnpj());
            stmt.setString(3, farmacia.getCidade());
            stmt.setString(4, farmacia.getRua());
            stmt.setString(5, farmacia.getNumero());
            stmt.setInt(6, farmacia.getId());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt);
        }

    }
    public void delete(Farmacia farmacia) throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("delete from farmacia where id = ?");
            stmt.setInt(1, farmacia.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt);
        }

    }
    
    public List<Farmacia> read() throws SQLException {

        Connection con = ConnectionUtil.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet result = null;

        List<Farmacia> farmacias = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM farmacia");
            result = stmt.executeQuery();

            while (result.next()) {

                Farmacia farmacia = new Farmacia();
				farmacia.setNameFarmacia(result.getString("nome"));
				farmacia.setCnpj(result.getString("cnpj"));
				farmacia.setCidade(result.getString("cidade"));
				farmacia.setRua(result.getString("rua"));
				farmacia.setNumero(result.getString("numero"));
				farmacia.setId(result.getInt("id"));
				farmacias.add(farmacia);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionUtil.closeConnection(con, stmt, result);
        }

        return farmacias;

    }
}
