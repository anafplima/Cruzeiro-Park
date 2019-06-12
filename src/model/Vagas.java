/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.VagasDao;

/**
 *
 * @author Ana Flavia
 */
public class Vagas {
    private String tipoVaga;
    private int qtdVaga;
    private float precoVaga;
    
    public Vagas() {
    	
    }
    
    public Vagas(String tipoVaga) {
    	VagasDao vagasDao=new VagasDao();
    	Vagas vagas= vagasDao.getVaga(tipoVaga);
    	this.tipoVaga= vagas.getTipoVaga();
    	this.qtdVaga= vagas.getQtdVaga();
    	this.precoVaga= vagas.getPrecoVaga();
    }
    
    public String getTipoVaga() {
		return tipoVaga;
	}
	public void setTipoVaga(String tipoVaga) {
		this.tipoVaga = tipoVaga;
	}
	public int getQtdVaga() {
		return qtdVaga;
	}
	public void setQtdVaga(int qtdVaga) {
		this.qtdVaga = qtdVaga;
	}
	public float getPrecoVaga() {
		return precoVaga;
	}
	public void setPrecoVaga(float precoVaga) {
		this.precoVaga = precoVaga;
	}
}
