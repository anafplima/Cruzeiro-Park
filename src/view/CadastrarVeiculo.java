package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import dao.CadVeiculoDao;
import model.Motorista;
import model.Vagas;
import model.Veiculo;

import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarVeiculo extends JFrame {

	private JPanel contentPane;
	private JTextField txtModelo;
	private JTextField txtPlaca;
	private JTextField txtMotorista;
	private JTextField txtCnh;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarVeiculo frame = new CadastrarVeiculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public CadastrarVeiculo() {
		setResizable(false);
		setTitle("Cadastro de Ve\u00EDculo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 450);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 34, 305, 138);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(107, 88, 149, 20);
		panel.add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JLabel lblPlaca = new JLabel("     Placa:");
		lblPlaca.setBounds(42, 91, 70, 14);
		panel.add(lblPlaca);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(107, 57, 149, 20);
		panel.add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblModelo = new JLabel("  Modelo:");
		lblModelo.setBounds(42, 60, 70, 14);
		panel.add(lblModelo);
		
		JComboBox cmbTipoVaga = new JComboBox();
		cmbTipoVaga.setBounds(106, 26, 92, 20);
		panel.add(cmbTipoVaga);
		CadVeiculoDao cadVeiculoDao = new CadVeiculoDao();
		cmbTipoVaga.setModel(cadVeiculoDao.getTiposDeVagas());
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(66, 26, 46, 14);
		panel.add(lblTipo);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblTipo, cmbTipoVaga, lblModelo, txtModelo, lblPlaca, txtPlaca}));
		
		JLabel lblVeculo = new JLabel("Ve\u00EDculo");
		lblVeculo.setBounds(44, 9, 74, 14);
		contentPane.add(lblVeculo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 221, 305, 91);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtCnh = new JTextField();
		txtCnh.setBounds(104, 52, 149, 20);
		panel_1.add(txtCnh);
		txtCnh.setColumns(10);
		
		JLabel lblCnh = new JLabel(" CNH:");
		lblCnh.setBounds(61, 55, 70, 14);
		panel_1.add(lblCnh);
		
		txtMotorista = new JTextField();
		txtMotorista.setBounds(104, 21, 149, 20);
		panel_1.add(txtMotorista);
		txtMotorista.setColumns(10);
		
		JLabel lblNome = new JLabel(" Nome:");
		lblNome.setBounds(50, 21, 63, 14);
		panel_1.add(lblNome);
		
		JLabel lblMotorista = new JLabel("Motorista");
		lblMotorista.setBounds(44, 196, 89, 14);
		contentPane.add(lblMotorista);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Motorista motorista=new Motorista();
				motorista.setCnh(Integer.parseInt(txtCnh.getText()));
				motorista.setNome(txtMotorista.getText());
				
				Vagas vagas=new Vagas(cmbTipoVaga.getSelectedItem().toString());
				
				Veiculo veiculo=new Veiculo();
				veiculo.setModelo(txtModelo.getText());
				veiculo.setPlaca(txtPlaca.getText());
				veiculo.setMotorista(motorista);
				veiculo.setVaga(vagas);
				
				CadVeiculoDao cadVeiculoDao=new CadVeiculoDao();
				if (cadVeiculoDao.veiculoJaCadastrado(veiculo)){
					JOptionPane.showMessageDialog(null, "Veiculo já cadastrado!");
				}else {
					String msg = cadVeiculoDao.cadastrarVeiculo(veiculo);
					if(msg.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Veículo cadastrado com Sucesso!");
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, msg);
					}
				}
			}
		});
		btnSalvar.setBounds(104, 348, 114, 37);
		contentPane.add(btnSalvar);
	}
}
