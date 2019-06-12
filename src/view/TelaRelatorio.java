package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.FichaDao;

import javax.swing.JLabel;
import java.awt.Font;

public class TelaRelatorio extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaRelatorio() {
		setTitle("Relat\u00F3rio");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 289, 167);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblModeloMaisFrequente = new JLabel("Modelo mais frequente:");
		lblModeloMaisFrequente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModeloMaisFrequente.setBounds(10, 30, 162, 41);
		contentPane.add(lblModeloMaisFrequente);
		
		FichaDao fichaDao = new FichaDao();
		String modelo = fichaDao.getModeloFrequente();
		
		JLabel lblModelo = new JLabel(modelo);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setBounds(182, 37, 78, 26);
		contentPane.add(lblModelo);
	}

}
