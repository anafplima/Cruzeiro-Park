package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.VagasDao;
import model.Vagas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegistrarVagas extends JFrame {

	private JPanel contentPane;
	private JTextField txtTipoVaga;
	private JTextField txtQtd;
	private JTextField txtPreco;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarVagas frame = new RegistrarVagas();
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
	public RegistrarVagas() {
		setTitle("Registro de Vagas e Ve\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 294, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(54, 28, 46, 17);
		contentPane.add(lblTipo);
		
		JLabel lblValor = new JLabel("Quantidade:");
		lblValor.setBounds(54, 56, 65, 14);
		contentPane.add(lblValor);
		
		JLabel lblValor_1 = new JLabel(" R$:");
		lblValor_1.setBounds(54, 85, 46, 14);
		contentPane.add(lblValor_1);
		
		txtTipoVaga = new JTextField();
		txtTipoVaga.setBounds(136, 26, 120, 20);
		contentPane.add(txtTipoVaga);
		txtTipoVaga.setColumns(10);
		
		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		txtQtd.setBounds(136, 51, 120, 20);
		contentPane.add(txtQtd);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(136, 82, 120, 20);
		contentPane.add(txtPreco);
		
		JButton tbnSalvar = new JButton("Salvar");
		tbnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vagas vagas=new Vagas();
				vagas.setTipoVaga(txtTipoVaga.getText());
				vagas.setQtdVaga(Integer.parseInt(txtQtd.getText()));
				vagas.setPrecoVaga(Float.parseFloat(txtPreco.getText()));
				VagasDao vagasDao=new VagasDao();
				String msg = vagasDao.cadastrarVaga(vagas);
				if(msg.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, msg);
				}
			}
		});
		tbnSalvar.setBounds(84, 129, 120, 58);
		contentPane.add(tbnSalvar);
	}
}
