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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import trabalho.univas.dao.FarmaceuticoDAO;
import trabalho.univas.dao.FarmaciaDAO;
import trabalho.univas.vo.Farmaceutico;
import trabalho.univas.vo.Farmacia;




public class FarmaceuticoList extends JPanel {

	private JTable farmaceuticoTable;
	private JLabel nameFarmaceuticoLabel;
	private JLabel crfLabel; 
	private JLabel ufLabel;
	private JLabel idLabel;
	private FarmaceuticoDAO farmaceuticoDAO;
	
	public FarmaceuticoList() throws SQLException {
		farmaceuticoDAO = new FarmaceuticoDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel emailLabel = new JLabel();
		emailLabel.setText("Farmaceuticos Cadastrados:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(emailLabel, gbc);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nome");
		columnNames.add("CRF");
		columnNames.add("UF");
		columnNames.add("Id");
		
		
		Vector<? extends Vector> vector = new Vector();
		farmaceuticoTable = new JTable(vector, columnNames);
		JScrollPane farmaceuticoTableScroll = new JScrollPane(farmaceuticoTable);
		farmaceuticoTableScroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		farmaceuticoTableScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		farmaceuticoTableScroll.setMinimumSize(new Dimension(750, 270));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(farmaceuticoTableScroll, gbc);
		
		JButton editButton = new JButton();
		editButton.setText("Editar");
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	
				
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(editButton, gbc);
		
		JButton deleteButton = new JButton();
		deleteButton.setText("Excluir");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					acaoExcluir2(e);
					
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
		this.add(deleteButton, gbc);		
	
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) farmaceuticoTable.getModel();
		tableModel.setRowCount(0);
		
		for (Farmaceutico farmaceutico : farmaceuticoDAO.getAll()) {
			Object[] data = {
					farmaceutico.getNameFarmaceutico(),
					farmaceutico.getCrf(),
					farmaceutico.getUf(),
					farmaceutico.getId()
			};
			
			tableModel.addRow(data);
		}
	}
	
	private void acaoExcluir2(java.awt.event.ActionEvent e) throws SQLException {
		
        if (farmaceuticoTable.getSelectedRow() != -1) {

            Farmaceutico farmaceutico = new Farmaceutico();
            FarmaceuticoDAO farmaceuticodao = new FarmaceuticoDAO();
            
            farmaceutico.setId(Integer.parseInt(farmaceuticoTable.getValueAt(farmaceuticoTable.getSelectedRow(), 3).toString())) ;
            
            farmaceuticodao.delete2(farmaceutico);
            ((DefaultTableModel) farmaceuticoTable.getModel()).removeRow(farmaceuticoTable.getSelectedRow());

            nameFarmaceuticoLabel.setText("");
            crfLabel.setText("");
            ufLabel.setText("");
            idLabel.setText("");

            readJTable2();
            

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma Farmaceutico para excluir.");
        }


    }
	
	 public void readJTable2() throws SQLException {
	        
	        DefaultTableModel modelo = (DefaultTableModel) farmaceuticoTable.getModel();
	        modelo.setNumRows(0);
	        FarmaceuticoDAO farmaceuticodao = new FarmaceuticoDAO();

	        for (Farmaceutico farmaceutico : farmaceuticodao.read()) {

	            modelo.addRow(new Object[]{
	                farmaceutico.getId(),
	                farmaceutico.getNameFarmaceutico(),
	                farmaceutico.getCrf(),
	                farmaceutico.getUf(),
	                
	                
	            });
	
	
	
}
	 }
}