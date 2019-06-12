package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.CadVeiculoDao;
import dao.FichaDao;
import dao.VagasDao;
import model.Ficha;
import model.Vagas;
import model.Veiculo;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.ScrollPaneConstants;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtPlaca;
	private JTable tableFichas;
	private JLabel lblQtdCarro;
	private JLabel lblQtdMoto;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				alteraQuantidadeVagas();
				readTabela();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Cruzeiro Park");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 549);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuEditar = new JMenu("Editar");
		menuEditar.setForeground(Color.BLACK);
		menuBar.add(menuEditar);
		
		JMenuItem menuEditarVagas = new JMenuItem("Editar Vagas/Valores");
		menuEditarVagas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarVagas frame=new RegistrarVagas();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		menuEditar.add(menuEditarVagas);
		
		JMenuItem menuSair = new JMenuItem("Sair");
		menuSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		menuEditar.add(menuSair);
		
		JMenu menuCadastro = new JMenu("Cadastro");
		menuCadastro.setForeground(Color.BLACK);
		menuBar.add(menuCadastro);
		
		JMenuItem menuCadFun = new JMenuItem("Cadastrar Funcionário");
		menuCadFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Abre a tela Principal
				CadastrarFuncionario frame = new CadastrarFuncionario();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		menuCadastro.add(menuCadFun);
		
		JMenuItem menuCadMot = new JMenuItem("Cadastrar Ve\u00EDculo/Motorista");
		menuCadMot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarVeiculo frame = new CadastrarVeiculo();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		menuCadastro.add(menuCadMot);
		
		JMenu menuRelatorio = new JMenu("Relat\u00F3rio");
		menuRelatorio.setForeground(Color.BLACK);
		menuBar.add(menuRelatorio);
		
		JMenuItem menuGerarRelatorio = new JMenuItem("Gerar Relat\u00F3rio");
		menuGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaRelatorio frame = new TelaRelatorio();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		menuRelatorio.add(menuGerarRelatorio);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(33, 23, 53, 14);
		contentPane.add(lblPlaca);
		
		txtPlaca = new JTextField();
		txtPlaca.setBounds(72, 20, 89, 20);
		contentPane.add(txtPlaca);
		txtPlaca.setColumns(10);
		
		JButton btnRegEntrada = new JButton("Registrar Entrada");
		btnRegEntrada.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Veiculo veiculo = new Veiculo();
				veiculo.setPlaca(txtPlaca.getText());
				
				CadVeiculoDao cadVeiculoDao = new CadVeiculoDao();
				if(cadVeiculoDao.veiculoJaCadastrado(veiculo)) {
					FichaDao fichaDao=new FichaDao();
					String msg = fichaDao.cadastrarFicha(veiculo.getPlaca());
					if(msg.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
						//readTabela();
						txtPlaca.setText("");
					}else {
						JOptionPane.showMessageDialog(null, msg);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Veículo não cadastrado. Cadastre o veículo!");
					CadastrarVeiculo frame = new CadastrarVeiculo();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
			}
		});
		btnRegEntrada.setBounds(33, 48, 145, 46);
		contentPane.add(btnRegEntrada);
		
		JLabel lblTotalVagas = new JLabel("Total de vagas livres");
		lblTotalVagas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVagas.setBounds(275, 23, 142, 14);
		contentPane.add(lblTotalVagas);
		
		JLabel lblNewLabel = new JLabel("Carro:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(308, 48, 53, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Moto:");
		lblNewLabel_1.setBounds(308, 73, 53, 14);
		contentPane.add(lblNewLabel_1);
		
		lblQtdCarro = new JLabel("0");
		lblQtdCarro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtdCarro.setForeground(Color.RED);
		lblQtdCarro.setBounds(371, 48, 56, 14);
		contentPane.add(lblQtdCarro);
		
		lblQtdMoto = new JLabel("0");
		lblQtdMoto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtdMoto.setForeground(Color.RED);
		lblQtdMoto.setBounds(371, 73, 75, 14);
		contentPane.add(lblQtdMoto);
		
		
		
		tableFichas = new JTable();
		tableFichas.setBackground(Color.WHITE);
		tableFichas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		tableFichas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFichas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Tipo", "Placa", "Modelo", "Dia", "Hora"
			}
		));
		tableFichas.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tableFichas.getSelectedRow();
		        TableModel model = tableFichas.getModel();
		        int idFicha = Integer.parseInt(model.getValueAt(row, 0).toString());
		        
		        Ficha ficha = new Ficha(idFicha); 
		        
		        TelaRegSaida frame = new TelaRegSaida(ficha);
		        frame.setLocationRelativeTo(null);
				frame.setVisible(true);
		    }
		});
		tableFichas.setBounds(20, 117, 479, 357);
		//contentPane.add(tableFichas);
		
		JScrollPane scrollPane = new JScrollPane(tableFichas);
		scrollPane.setBounds(10, 122, 436, 366);
		contentPane.add(scrollPane);
	}
	
	public void readTabela() {
		DefaultTableModel modelo = (DefaultTableModel) tableFichas.getModel();
		modelo.setNumRows(0);
		FichaDao fichaDao = new FichaDao();
		
		for(Ficha f: fichaDao.preencherTabela()) {
			modelo.addRow(new Object[] {
				f.getIdFicha(),
				f.getVeiculo().getVaga().getTipoVaga(),
				f.getVeiculo().getPlaca(),
				f.getVeiculo().getModelo(),
				f.getDtHrEntrada().getDate()+"/"+(f.getDtHrEntrada().getMonth()+1),
				f.getDtHrEntrada().getHours()+":"+f.getDtHrEntrada().getMinutes()
			});
		}
	}
	
	public void alteraQuantidadeVagas() {
		VagasDao vagasDao = new VagasDao();
		
		for(Vagas v: vagasDao.getQtdVagaLivre()) {
			if(v.getTipoVaga().equals("Carro")) {
				lblQtdCarro.setText(String.valueOf(v.getQtdVaga()));
			} else if(v.getTipoVaga().equals("Moto")) {
				lblQtdMoto.setText(String.valueOf(v.getQtdVaga()));
			}
		}
	}
}
