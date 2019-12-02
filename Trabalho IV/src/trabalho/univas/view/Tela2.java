package trabalho.univas.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import trabalho.univas.dao.FarmaceuticoDAO;
import trabalho.univas.vo.Farmaceutico;


public class Tela2  extends JPanel{
	private JTextField nameFarmaceuticoTextField;
	private JTextField crfTextField; 
	private JTextField ufTextField;
	private JTextField idTextField;
	private FarmaceuticoDAO farmaceuticoDAO;
	
	public Tela2() throws SQLException {
		farmaceuticoDAO = new FarmaceuticoDAO();
		initialize();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JLabel nameFarmaceuticoLabel = new JLabel();
		nameFarmaceuticoLabel.setText("Nome:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(nameFarmaceuticoLabel, gbc);
		
		nameFarmaceuticoTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nameFarmaceuticoTextField, gbc);
		
		JLabel crfLabel = new JLabel();
		crfLabel.setText("CRF:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(crfLabel, gbc);
		
		crfTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(crfTextField, gbc);
		
		JLabel ufLabel = new JLabel();
		ufLabel.setText("UF(Sigla):");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(ufLabel, gbc);
		
		ufTextField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(ufTextField, gbc);
		
		JLabel idLabel = new JLabel();
		idLabel.setText("Id:");
		gbc.gridx = 0;
		gbc.gridy = 3;
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
				saveNewFarmaceutico();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(saveButton, gbc);		
	}
	
	private void saveNewFarmaceutico() {
		if (validateFields()) {

			Farmaceutico farmaceutico = new Farmaceutico();
			farmaceutico.setNameFarmaceutico(nameFarmaceuticoTextField.getText());
			farmaceutico.setCrf(crfTextField.getText());
			farmaceutico.setUf(ufTextField.getText());
			farmaceuticoDAO.save1(farmaceutico);
			Integer id = Integer.parseInt(idTextField.getText());
			farmaceutico.setId(id);
			
			clearFields();
			JOptionPane.showMessageDialog(this, "Farmaceutico cadastrado com sucesso!",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean validateFields() {
		if (nameFarmaceuticoTextField.getText() == null || nameFarmaceuticoTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o nome", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			nameFarmaceuticoTextField.requestFocus();
			return false;
		}
		if (crfTextField.getText() == null || crfTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o CRF", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			crfTextField.requestFocus();
			return false;
		}
		if (ufTextField.getText() == null || ufTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a UF", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			ufTextField.requestFocus();
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
		nameFarmaceuticoTextField.requestFocus();
		nameFarmaceuticoTextField.setText(null);
		crfTextField.setText(null);
		ufTextField.setText(null);
		idTextField.setText(null);
	}
}

