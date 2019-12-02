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

import trabalho.univas.dao.FarmaciaDAO;
import trabalho.univas.dao.MedicamentoDAO;
import trabalho.univas.vo.Farmacia;
import trabalho.univas.vo.Medicamento;

public class MedicamentoList extends JPanel {

	private JTable medicamentoTable;
	private JLabel nameMedicamentoLabel;
	private JLabel loteLabel; 
	private JLabel dataValidadeLabel;
	private JLabel valorLabel;
	private JLabel quantidadeLabel;
	private JLabel idLabel;
	private JTextField nameMedicamentoTextField;
	private JTextField loteTextField; 
	private JTextField dataValidadeTextField;
	private JTextField valorTextField;
	private JTextField quantidadeTextField;
	private JTextField idTextField;
	private MedicamentoDAO medicamentoDAO;
	
	public MedicamentoList() throws SQLException {
		medicamentoDAO = new MedicamentoDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel emailLabel = new JLabel();
		emailLabel.setText("Medicamentos Cadastrados:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(emailLabel, gbc);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nome");
		columnNames.add("Lote");
		columnNames.add("Data de Validade");
		columnNames.add("Valor");
		columnNames.add("Quantidade");
		columnNames.add("Id");
		//Vector<Student> vector = new Vector<Student>();
		Vector<? extends Vector> vector = new Vector();
		medicamentoTable = new JTable(vector, columnNames);
		JScrollPane medicamentoTableScroll = new JScrollPane(medicamentoTable);
		medicamentoTableScroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		medicamentoTableScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		medicamentoTableScroll.setMinimumSize(new Dimension(750, 270));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(medicamentoTableScroll, gbc);
		
		JButton edit1Button = new JButton();
		edit1Button.setText("Editar");
		edit1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jButton3ActionPerformed(e);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(edit1Button, gbc);
		
		JButton delete1Button = new JButton();
		delete1Button.setText("Excluir");
		delete1Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					acaoExcluir3(e);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(delete1Button, gbc);		
	
	
	medicamentoTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            medicamentoTableMouseClicked(evt);
        }
    });
	medicamentoTable.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            medicamentoTableKeyReleased(evt);
        }
    });
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) medicamentoTable.getModel();
		tableModel.setRowCount(0);
		
		for (Medicamento medicamento : medicamentoDAO.getAll()) {
			Object[] data = {
            		medicamento.getNameMedicamento(),
            		medicamento.getLote(),
            		medicamento.getDataValidade(),
            		medicamento.getValor(),
            		medicamento.getQuantidade(),
            		medicamento.getId()
			};

			
			tableModel.addRow(data);
		}
	}
	
	private void medicamentoTableMouseClicked(java.awt.event.MouseEvent evt) {

        if (medicamentoTable.getSelectedRow() != -1) {

        	nameMedicamentoTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 0).toString());
        	loteTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 1).toString());
        	dataValidadeTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 2).toString());
        	valorTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 3).toString());
        	quantidadeTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 4).toString());
        	idTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 5).toString());
      

        }

    }

    private void medicamentoTableKeyReleased(java.awt.event.KeyEvent evt) {

        if (medicamentoTable.getSelectedRow() != -1) {

        	nameMedicamentoTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 0).toString());
        	loteTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 1).toString());
        	dataValidadeTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 2).toString());
        	valorTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 3).toString());
        	quantidadeTextField.setText(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 4).toString());

        }

    }
	
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        

        if (medicamentoTable.getSelectedRow() != -1) {

            Medicamento m = new Medicamento();
            MedicamentoDAO meddao = new MedicamentoDAO();

            m.setNameMedicamento(nameMedicamentoTextField.getText());
            m.setLote(loteTextField.getText());
            m.setDataValidade(dataValidadeTextField.getText());
            m.setValor(valorTextField.getText());
            m.setQuantidade(quantidadeTextField.getText());
            m.setId((int) medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 5));
            meddao.update(m);

            nameMedicamentoTextField.setText("");
            loteTextField.setText("");
            dataValidadeTextField.setText("");
            valorTextField.setText("");
            quantidadeTextField.setText("");
            

            readJTable3();

        }


    }
	
	
	
	private void acaoExcluir3(java.awt.event.ActionEvent e) throws SQLException {
		
        if (medicamentoTable.getSelectedRow() != -1) {

            Medicamento medicamento = new Medicamento();
            MedicamentoDAO medicamentodao = new MedicamentoDAO();
            
            medicamento.setId(Integer.parseInt(medicamentoTable.getValueAt(medicamentoTable.getSelectedRow(), 5).toString())) ;
            
            medicamentodao.delete3(medicamento);
            ((DefaultTableModel) medicamentoTable.getModel()).removeRow(medicamentoTable.getSelectedRow());

            nameMedicamentoLabel.setText("");
            loteLabel.setText("");
            dataValidadeLabel.setText("");
            valorLabel.setText("");
            quantidadeLabel.setText("");
            idLabel.setText("");

            readJTable3();
            

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma Medicamento para excluir.");
        }


    }
	
	 public void readJTable3() throws SQLException {
	        
	        DefaultTableModel modelo = (DefaultTableModel) medicamentoTable.getModel();
	        modelo.setNumRows(0);
	        MedicamentoDAO medicamentodao = new MedicamentoDAO();

	        for (Medicamento medicamento : medicamentodao.read()) {

	            modelo.addRow(new Object[]{
	            		medicamento.getId(),
	            		medicamento.getNameMedicamento(),
	            		medicamento.getLote(),
	            		medicamento.getDataValidade(),
	            		medicamento.getValor(),
	            		medicamento.getQuantidade(),
	            		
	                
	            });

	        }

	    }
	
}
