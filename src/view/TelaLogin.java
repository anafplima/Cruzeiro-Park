package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.LoginDao;
import model.Login;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		
		setResizable(false);
		setTitle("Cruzeiro Park");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\CruzeiroPark2\\img\\logo2.png"));
		label.setBounds(139, 22, 156, 95);
		
		
		
		contentPane.add(label);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(160, 146, 114, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsurio.setBounds(114, 148, 44, 17);
		contentPane.add(lblUsurio);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login=new Login();
				login.setUsuario(txtUsuario.getText());
				login.setSenha(String.valueOf(txtSenha.getPassword()));
				LoginDao loginDao=new LoginDao();
				//login = loginDao.fazerLogin(login);
				
				if(loginDao.fazerLogin(login)) {
					JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
					//Abre a tela Principal
					Principal frame = new Principal();
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.setVisible(true);
					//fechar a tela Login
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou Senha incorretos!");
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEntrar.setBounds(174, 208, 89, 34);
		contentPane.add(btnEntrar);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSenha.setBounds(114, 180, 36, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(160, 177, 114, 20);
		contentPane.add(txtSenha);
	}
	
	public java.awt.Image Renderizar(String ph)
	{
		ImageIcon img= new ImageIcon(ph);
		
		java.awt.Image image = img.getImage(); // transform it 
		java.awt.Image newimg = image.getScaledInstance(120,130,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		return newimg;
	}
}
