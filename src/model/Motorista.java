/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.MotoristaDao;

/**
 *
 * @author Ana Flavia
 */
public class Motorista {
    private int cnh;
    private String nome;
    
    public Motorista() {
		
    }
    
    public Motorista(int cnh) {
		MotoristaDao motoristaDao =new MotoristaDao();
		Motorista motorista = motoristaDao.getMotorista(cnh);
		this.cnh = motorista.getCnh();
		this.nome = motorista.getNome();
    }
    
    public int getCnh(){
        return this.cnh;
    }
    public void setCnh(int cnh){
        this.cnh=cnh;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
    
}
