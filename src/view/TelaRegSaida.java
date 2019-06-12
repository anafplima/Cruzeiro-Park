package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import dao.FichaDao;
import model.Ficha;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class TelaRegSaida extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRegSaida frame = new TelaRegSaida();
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
	public TelaRegSaida(Ficha ficha) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 288, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Perman\u00EAncia:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 32, 84, 14);
		contentPane.add(lblNewLabel);

        Date dataInicio = ficha.getDtHrEntrada();
        Date dataFim = new java.util.Date();
        long diff = dataFim.getTime() - dataInicio.getTime();

        long resultado = diff / (1000*60);
        int total = 0;
        if (resultado <= 60) {
        	total = 1;
        } else {
        	if((resultado%60) == 0) {
        		total = (int) resultado/60;
        	} else {
        		total = (int) (resultado/60) + 1;
        	}
        }
        
		ficha.setValorHora(total*ficha.getVeiculo().getVaga().getPrecoVaga());
		JLabel lblTempo = new JLabel(String.valueOf(total) + "h");
		lblTempo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTempo.setBounds(115, 32, 46, 14);
		contentPane.add(lblTempo);
		
		JLabel lblNew = new JLabel("Valor R$:");
		lblNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNew.setBounds(39, 71, 66, 14);
		contentPane.add(lblNew);
		
		JLabel lblTotal = new JLabel(String.valueOf(total*ficha.getVeiculo().getVaga().getPrecoVaga()));
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(115, 71, 46, 14);
		contentPane.add(lblTotal);
		
		JButton btnRegSaida = new JButton("Confirmar");
		btnRegSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FichaDao fichaDao = new FichaDao();
				String msg = fichaDao.saidaFicha(ficha);
				if(msg.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Registro de saída com sucesso!");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, msg);
				}
			}
		});
		btnRegSaida.setBounds(21, 142, 89, 23);
		contentPane.add(btnRegSaida);
		
		JButton btnCancelarSaida = new JButton("Cancelar");
		btnCancelarSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelarSaida.setBounds(157, 142, 89, 23);
		contentPane.add(btnCancelarSaida);
	}
}
