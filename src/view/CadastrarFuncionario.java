package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CadFunDao;
import dao.CadVeiculoDao;
import model.Endereco;
import model.Funcionario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused" })
public class CadastrarFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JTextField txtCpf;
	private JTextField txtCartTrabalho;
	private JTextField txtRua;
	private JTextField txtCep;
	private JTextField txtTelefone;
	private JTextField txtCargo;
	private JTextField txtBairro;
	private JTextField txtSenha;
	private JTextField txtNumCasa;
	private JTextField txtCidade;
	private JTextField txtEstado;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarFuncionario frame = new CadastrarFuncionario();
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
	public CadastrarFuncionario() {
		setTitle("Cadastro de Funcion\u00E1rio:");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 651, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("      Nome:");
		lblNewLabel.setBounds(76, 44, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRg = new JLabel("Login:");
		lblRg.setBounds(100, 69, 35, 14);
		contentPane.add(lblRg);
		
		JLabel lblCpf = new JLabel("   CPF:");
		lblCpf.setBounds(100, 110, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblCarteiraDeTrabalho = new JLabel("Carteira Trabalho:");
		lblCarteiraDeTrabalho.setBounds(41, 135, 139, 14);
		contentPane.add(lblCarteiraDeTrabalho);
		
		JLabel lblEndereo = new JLabel("  Rua:");
		lblEndereo.setBounds(76, 166, 81, 14);
		contentPane.add(lblEndereo);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(97, 194, 60, 14);
		contentPane.add(lblBairro);
		
		JLabel lblCep = new JLabel("  Cep:");
		lblCep.setBounds(109, 222, 48, 14);
		contentPane.add(lblCep);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(87, 247, 70, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblFuno = new JLabel("Cargo:");
		lblFuno.setBounds(387, 135, 70, 14);
		contentPane.add(lblFuno);
		
		txtNome = new JTextField();
		txtNome.setBounds(153, 41, 149, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(153, 71, 121, 20);
		contentPane.add(txtLogin);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(153, 101, 121, 20);
		contentPane.add(txtCpf);
		
		txtCartTrabalho = new JTextField();
		txtCartTrabalho.setColumns(10);
		txtCartTrabalho.setBounds(153, 132, 149, 20);
		contentPane.add(txtCartTrabalho);
		
		txtRua = new JTextField();
		txtRua.setColumns(10);
		txtRua.setBounds(153, 163, 234, 20);
		contentPane.add(txtRua);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(153, 219, 121, 20);
		contentPane.add(txtCep);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(153, 247, 149, 20);
		contentPane.add(txtTelefone);
		
		txtCargo = new JTextField();
		txtCargo.setColumns(10);
		txtCargo.setBounds(444, 132, 149, 20);
		contentPane.add(txtCargo);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(153, 191, 149, 20);
		contentPane.add(txtBairro);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Endereco endereco=new Endereco();
				endereco.setRua(txtRua.getText());
				endereco.setNumCasa(Integer.parseInt(txtNumCasa.getText()));
				endereco.setBairro(txtBairro.getText());
				endereco.setCidade(txtCidade.getText());
				endereco.setCep(Integer.parseInt(txtCep.getText()));
				endereco.setEstado(txtEstado.getText());
				
				Funcionario funcionario=new Funcionario();
				funcionario.setNome(txtNome.getText());
				funcionario.setLogin(txtLogin.getText());
				funcionario.setSenha(txtSenha.getText());
				funcionario.setCpf(Integer.parseInt(txtCpf.getText()));
				funcionario.setCartTrabalho(Integer.parseInt(txtCartTrabalho.getText()));
				funcionario.setCargo(txtCargo.getText());
				funcionario.setEndereco(endereco);
				CadFunDao cadFunDao=new CadFunDao();
				if (cadFunDao.funcionarioJaCadastrado(funcionario)){
					JOptionPane.showMessageDialog(null, "Funcionário já cadastrado!");
				}else {
					String msg = cadFunDao.cadastrarFuncionario(funcionario);
					if(msg.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, msg);
					}
				}
			}
		});
		btnSalvar.setBounds(163, 293, 121, 53);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(315, 74, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		txtSenha = new JTextField();
		txtSenha.setBounds(351, 71, 86, 20);
		contentPane.add(txtSenha);
		txtSenha.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00B0:");
		lblNewLabel_2.setBounds(397, 166, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNumCasa = new JTextField();
		txtNumCasa.setBounds(424, 163, 86, 20);
		contentPane.add(txtNumCasa);
		txtNumCasa.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Cidade:");
		lblNewLabel_3.setBounds(326, 194, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(371, 191, 86, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Estado:");
		lblNewLabel_4.setBounds(284, 222, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(336, 219, 86, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
	}
}
