/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.CadVeiculoDao;

/**
 *
 * @author Ana Flavia
 */
public class Veiculo {
	private String placa;
	private Vagas vaga;
    private Motorista motorista;
    private String modelo;
    
    public Veiculo() {
		
    }
    
    public Veiculo(String placa) {
		CadVeiculoDao cadVeiculoDao=new CadVeiculoDao();
		Veiculo veiculo=cadVeiculoDao.getVeiculo(placa);
		this.placa= veiculo.getPlaca();
		this.vaga=veiculo.getVaga();
		this.motorista=veiculo.getMotorista();
		this.modelo=veiculo.getModelo();
    }
    
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista= motorista;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Vagas getVaga() {
		return vaga;
	}
	public void setVaga(Vagas vaga) {
		this.vaga = vaga;
	}
    
    
   
}
