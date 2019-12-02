package trabalho.univas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FarmaciaFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private FarmaciaList listPanel1;
	private FarmaceuticoList listPanel2;
	private MedicamentoList listPanel3;
	private Tela1 addPanel1;
	private Tela2 addPanel2;
	private Tela3 addPanel3;
	
	public FarmaciaFrame() {
		setSize(1000, 640);
		setTitle("CONTROLE DE MEDICAMENTOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initialize();
	}
	
	private void initialize() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
	
			
			try {
				addPanel1 = new Tela1();
				listPanel1 = new FarmaciaList();
				listPanel1.updateTable();
				
				addPanel2 = new Tela2();
				listPanel2 = new FarmaceuticoList();
				listPanel2.updateTable();
				
				addPanel3 = new Tela3();
				listPanel3 = new MedicamentoList();
				listPanel3.updateTable();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
		
		createNorthPanel();
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		createSouthPanel();
		
	}
	
	private void createNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(800, 140));
		northPanel.setBackground(Color.GREEN);
		
		JLabel textLabel = new JLabel();
		textLabel.setText("<html>BEM VINDO<BR>"
				+ "Selecione a opção :<br>"
				+ "1-Adicionar Farmaceutico<br>"
				+ "2-Adicionar Farmácia<br>"
				+ "3-Adicionar Medicamento");
		northPanel.add(textLabel);
		
		JButton addFarmaceuticoButton = new JButton();
		addFarmaceuticoButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image1 = new ImageIcon(getClass().getResource("/addfarmaceutico.png"));
		addFarmaceuticoButton.setIcon(image1);
		addFarmaceuticoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel2.updateTable();
				centerPanel.removeAll();
				centerPanel.add(addPanel2);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		northPanel.add(addFarmaceuticoButton);
		
		JButton addFarmaciaButton = new JButton();
		addFarmaciaButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image2 = new ImageIcon(getClass().getResource("/addFarmacia.png"));
		addFarmaciaButton.setIcon(image2);
		addFarmaciaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel1.updateTable();
				centerPanel.removeAll();
				centerPanel.add(addPanel1);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		northPanel.add(addFarmaciaButton);
		
		JButton addMedicamentoButton = new JButton();
		addMedicamentoButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image3 = new ImageIcon(getClass().getResource("/addmedicamento.png"));
		addMedicamentoButton.setIcon(image3);
		addMedicamentoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel3.updateTable();
				centerPanel.removeAll();
				centerPanel.add(addPanel3);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		northPanel.add(addMedicamentoButton);
		
		
		contentPane.add(northPanel, BorderLayout.NORTH);
	}
	
	private void createSouthPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(800, 140));
		southPanel.setBackground(Color.CYAN);
		
		JLabel text2Label = new JLabel();
		text2Label.setText("<html>"
				+ "Selecione a opção :<br>"
				+ "1-Listar Farmaceuticos<br>"
				+ "2-Listar Farmácias<br>"
				+ "3-Listar Medicamentos<br>"
				+ "4-DashBoard");
		southPanel.add(text2Label);
		
		JButton listarFarmaceuticoButton = new JButton();
		listarFarmaceuticoButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image4 = new ImageIcon(getClass().getResource("/relafarmaceutico.png"));
		listarFarmaceuticoButton.setIcon(image4);
		listarFarmaceuticoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel2.updateTable();
				centerPanel.removeAll();
				centerPanel.add(listPanel2);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		southPanel.add(listarFarmaceuticoButton);
		
		JButton listarFarmaciaButton = new JButton();
		listarFarmaciaButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image5 = new ImageIcon(getClass().getResource("/relaFarmacia.png"));
		listarFarmaciaButton.setIcon(image5);
		listarFarmaciaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel1.updateTable();
				centerPanel.removeAll();
				centerPanel.add(listPanel1);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		southPanel.add(listarFarmaciaButton);
		
		JButton listarMedicamentoButton = new JButton();
		listarMedicamentoButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image6 = new ImageIcon(getClass().getResource("/relaMedicamento.png"));
		listarMedicamentoButton.setIcon(image6);
		listarMedicamentoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel3.updateTable();
				centerPanel.removeAll();
				centerPanel.add(listPanel3);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		southPanel.add(listarMedicamentoButton);
		
		
		JButton dashboardButton = new JButton();
		dashboardButton.setPreferredSize(new Dimension(100, 100));
		ImageIcon image7 = new ImageIcon(getClass().getResource("/dashboard.png"));
		dashboardButton.setIcon(image7);
		dashboardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listPanel3.updateTable();
				centerPanel.removeAll();
				centerPanel.add(listPanel3);
				centerPanel.revalidate();
				FarmaciaFrame.this.repaint();
			}
		});
		southPanel.add(dashboardButton);
		
		
		contentPane.add(southPanel, BorderLayout.SOUTH);
	}
	
}