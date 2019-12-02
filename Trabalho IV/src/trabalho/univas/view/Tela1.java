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

import trabalho.univas.dao.FarmaciaDAO;
import trabalho.univas.vo.Farmacia;



public class Tela1 extends JPanel {

	private JTextField nameFarmaciaTextField;
	private JTextField cnpjTextField; 
	private JTextField cidadeTextField;
	private JTextField ruaTextField;
	private JTextField numeroTextField;
	private JTextField idTextField;
	private FarmaciaDAO farmaciaDAO;
	
	public Tela1() throws SQLException {
		farmaciaDAO = new FarmaciaDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel nameFarmaciaLabel = new JLabel();
		nameFarmaciaLabel.setText("Nome:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(nameFarmaciaLabel, gbc);
		
		nameFarmaciaTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nameFarmaciaTextField, gbc);
		
		JLabel cnpjLabel = new JLabel();
		cnpjLabel.setText("CNPJ:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(cnpjLabel, gbc);
		
		cnpjTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(cnpjTextField, gbc);
		
		JLabel cidadeLabel = new JLabel();
		cidadeLabel.setText("Cidade:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(cidadeLabel, gbc);
		
		cidadeTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(cidadeTextField, gbc);
		
		JLabel ruaLabel = new JLabel();
		ruaLabel.setText("Rua:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(ruaLabel, gbc);
		
		ruaTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(ruaTextField, gbc);
		
		JLabel numeroLabel = new JLabel();
		numeroLabel.setText("Numero:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(numeroLabel, gbc);
		
		numeroTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(numeroTextField, gbc);
		
		JLabel idLabel = new JLabel();
		idLabel.setText("Id:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(idLabel, gbc);
		
		idTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(idTextField, gbc);
		
		/**JLabel respLabel = new JLabel();
		respLabel.setText("Responsável:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(respLabel, gbc);
		
		JComboBox respCombobox = new JComboBox();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(respCombobox, gbc);
		**/
		
		JButton saveButton = new JButton();
		saveButton.setText("Adicionar");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveNewFarmacia();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(saveButton, gbc);		
	}
	
	private void saveNewFarmacia() {
		if (validateFields()) {

			Farmacia farmacia = new Farmacia();
			farmacia.setNameFarmacia(nameFarmaciaTextField.getText());
			farmacia.setCnpj(cnpjTextField.getText());
			farmacia.setCidade(cidadeTextField.getText());
			farmacia.setRua(ruaTextField.getText());
			farmacia.setNumero(numeroTextField.getText());
			Integer id = Integer.parseInt(idTextField.getText());
			farmacia.setId(id);
			farmaciaDAO.save(farmacia);
			
			clearFields();
			JOptionPane.showMessageDialog(this, "Farmacia cadastrado com sucesso!",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean validateFields() {
		if (nameFarmaciaTextField.getText() == null || nameFarmaciaTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o nome", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			nameFarmaciaTextField.requestFocus();
			return false;
		}
		if (cnpjTextField.getText() == null || cnpjTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o CNPJ", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			cnpjTextField.requestFocus();
			return false;
		}
		if (cidadeTextField.getText() == null || cidadeTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a cidade", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			cidadeTextField.requestFocus();
			return false;
		}
		if (ruaTextField.getText() == null || ruaTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a rua", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			ruaTextField.requestFocus();
			return false;
		}
		if (numeroTextField.getText() == null || numeroTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o numero", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			numeroTextField.requestFocus();
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
		nameFarmaciaTextField.requestFocus();
		nameFarmaciaTextField.setText(null);
		cnpjTextField.setText(null);
		cidadeTextField.setText(null);
		ruaTextField.setText(null);
		numeroTextField.setText(null);
		idTextField.setText(null);
	}
}