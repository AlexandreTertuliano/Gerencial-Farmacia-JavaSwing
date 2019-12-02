package trabalho.univas.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import trabalho.univas.dao.FarmaciaDAO;
import trabalho.univas.vo.Farmacia;



public class FarmaciaList extends JPanel {

	private JTable farmaciaTable;
	private JLabel nameFarmaciaLabel;
	private JLabel cnpjLabel; 
	private JLabel cidadeLabel;
	private JLabel ruaLabel;
	private JLabel numeroLabel;
	private JLabel idLabel;
	private FarmaciaDAO farmaciaDAO;
	
	public FarmaciaList() throws SQLException {
		farmaciaDAO = new FarmaciaDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel emailLabel = new JLabel();
		emailLabel.setText("Farmacias Cadastradas:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(emailLabel, gbc);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nome");
		columnNames.add("CNPJ");
		columnNames.add("Cidade");
		columnNames.add("Rua");
		columnNames.add("Numero");
		columnNames.add("Id");
		//Vector<Student> vector = new Vector<Student>();
		Vector<? extends Vector> vector = new Vector();
		farmaciaTable = new JTable(vector, columnNames);
		JScrollPane farmaciaTableScroll = new JScrollPane(farmaciaTable);
		farmaciaTableScroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		farmaciaTableScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		farmaciaTableScroll.setMinimumSize(new Dimension(750, 270));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(farmaciaTableScroll, gbc);
		
		JButton edit2Button = new JButton();
		edit2Button.setText("Editar");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(edit2Button, gbc);
		
		JButton delete2Button = new JButton();
		delete2Button.setText("Excluir");
		delete2Button.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		
				try {
					acaoExcluir(e);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(delete2Button, gbc);		
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) farmaciaTable.getModel();
		tableModel.setRowCount(0);
		
		for (Farmacia farmacia : farmaciaDAO.getAll()) {
			Object[] data = {
					farmacia.getNameFarmacia(),
					farmacia.getCnpj(),
					farmacia.getCidade(),
					farmacia.getRua(),
					farmacia.getNumero(),
					farmacia.getId()
			};
			
			tableModel.addRow(data);
		}
	}
	
	private void acaoExcluir(java.awt.event.ActionEvent e) throws SQLException {
		
        if (farmaciaTable.getSelectedRow() != -1) {

            Farmacia farmacia = new Farmacia();
            FarmaciaDAO farmaciadao = new FarmaciaDAO();
            
            farmacia.setId(Integer.parseInt(farmaciaTable.getValueAt(farmaciaTable.getSelectedRow(), 5).toString())) ;
            
            farmaciadao.delete(farmacia);
            ((DefaultTableModel) farmaciaTable.getModel()).removeRow(farmaciaTable.getSelectedRow());

            nameFarmaciaLabel.setText("");
            cnpjLabel.setText("");
            cidadeLabel.setText("");
            ruaLabel.setText("");
            numeroLabel.setText("");
            idLabel.setText("");

            readJTable();
            

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma farmacia para excluir.");
        }


    }
	
	 public void readJTable() throws SQLException {
	        
	        DefaultTableModel modelo = (DefaultTableModel) farmaciaTable.getModel();
	        modelo.setNumRows(0);
	        FarmaciaDAO farmaciadao = new FarmaciaDAO();

	        for (Farmacia farmacia : farmaciadao.read()) {

	            modelo.addRow(new Object[]{
	                farmacia.getId(),
	                farmacia.getNameFarmacia(),
	                farmacia.getCnpj(),
	                farmacia.getCidade(),
	                farmacia.getRua(),
	                farmacia.getNumero(),
	                farmacia.getId()
	                
	            });

	        }

	    }
	
}
