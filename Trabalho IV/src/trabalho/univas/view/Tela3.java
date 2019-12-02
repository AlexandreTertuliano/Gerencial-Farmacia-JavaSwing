package trabalho.univas.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trabalho.univas.dao.MedicamentoDAO;
import trabalho.univas.vo.Medicamento;

public class Tela3  extends JPanel{
	private JTextField nameMedicamentoTextField;
	private JTextField loteTextField; 
	private JTextField dataValidadeTextField;
	private JTextField valorTextField;
	private JTextField quantidadeTextField;
	private JTextField idTextField;
	private MedicamentoDAO medicamentoDAO;
	
	public Tela3() throws SQLException {
		medicamentoDAO = new MedicamentoDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel nameMedicamentoLabel = new JLabel();
		nameMedicamentoLabel.setText("Nome:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(nameMedicamentoLabel, gbc);
		
		nameMedicamentoTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nameMedicamentoTextField, gbc);
		
		JLabel loteLabel = new JLabel();
		loteLabel.setText("Lote:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(loteLabel, gbc);
		
		loteTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(loteTextField, gbc);
		
		JLabel dataValidadeLabel = new JLabel();
		dataValidadeLabel.setText("Data de Validade:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(dataValidadeLabel, gbc);
		
		dataValidadeTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(dataValidadeTextField, gbc);
		
		JLabel valorLabel = new JLabel();
		valorLabel.setText("Valor (R$):");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(valorLabel, gbc);
		
		valorTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(valorTextField, gbc);
		
		JLabel quantidadeLabel = new JLabel();
		quantidadeLabel.setText("Quantidade(UN):");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(quantidadeLabel, gbc);
		
		quantidadeTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(quantidadeTextField, gbc);
		
		JLabel farmaLabel = new JLabel();
		farmaLabel.setText("Farmácia Disp:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(farmaLabel, gbc);
		
		JComboBox respCombobox = new JComboBox();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(respCombobox, gbc);
		
		JLabel idLabel = new JLabel();
		idLabel.setText("Id:");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(idLabel, gbc);
		
		idTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(idTextField, gbc);
		
		
		
		JButton saveButton = new JButton();
		
		saveButton.setText("Adicionar");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveNewMedicamento();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 3;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(saveButton, gbc);		
	}
	
	private void saveNewMedicamento() {
		if (validateFields()) {

			Medicamento medicamento = new Medicamento();
			medicamento.setNameMedicamento(nameMedicamentoTextField.getText());
			medicamento.setLote(loteTextField.getText());
			medicamento.setDataValidade(dataValidadeTextField.getText());
			medicamento.setValor(valorTextField.getText());
			medicamento.setQuantidade(quantidadeTextField.getText());
			Integer id = Integer.parseInt(idTextField.getText());
			medicamento.setId(id);
			medicamentoDAO.save2(medicamento);
			
			clearFields();
			JOptionPane.showMessageDialog(this, "Medicamento cadastrado com sucesso!",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean validateFields() {
		if (nameMedicamentoTextField.getText() == null || nameMedicamentoTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o nome", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			nameMedicamentoTextField.requestFocus();
			return false;
		}
		if (loteTextField.getText() == null || loteTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o lote", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			loteTextField.requestFocus();
			return false;
		}
		if (dataValidadeTextField.getText() == null || dataValidadeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a data de validade", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			dataValidadeTextField.requestFocus();
			return false;
		}
		if (valorTextField.getText() == null || valorTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o valor", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			valorTextField.requestFocus();
			return false;
		}
		if (quantidadeTextField.getText() == null || quantidadeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a quantidade (un)", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			quantidadeTextField.requestFocus();
			return false;
		}
		if (idTextField.getText() == null || idTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o Id", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			idTextField.requestFocus();
			return false;
		}
		
		return true;
	}

	private void clearFields() {
		nameMedicamentoTextField.requestFocus();
		nameMedicamentoTextField.setText(null);
		loteTextField.setText(null);
		dataValidadeTextField.setText(null);
		valorTextField.setText(null);
		quantidadeTextField.setText(null);
		idTextField.setText(null);
	}
}