/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

import dao.FichaDao;
import model.Veiculo;

/**
 *
 * @author Ana Flavia
 */
public class Ficha {
    private int idFicha;
    private Date dtHrEntrada;
    private Date dtHrSaida; 
    private float valorHora;
    private Veiculo veiculo;
    
    public Ficha() {
		
    }
    
    public Ficha(int idFicha) {
		FichaDao fichaDao = new FichaDao();
		Ficha ficha = fichaDao.getFicha(idFicha);
		this.idFicha = ficha.getIdFicha();
		this.dtHrEntrada = ficha.getDtHrEntrada();
		this.dtHrSaida = ficha.getDtHrSaida();
		this.valorHora = ficha.getValorHora();
		this.veiculo = ficha.getVeiculo();
    }
    
    
	public int getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}
	public Date getDtHrEntrada() {
		return dtHrEntrada;
	}
	public void setDtHrEntrada(Date dtHrEntrada) {
		this.dtHrEntrada = dtHrEntrada;
	}
	public Date getDtHrSaida() {
		return dtHrSaida;
	}
	public void setDtHrSaida(Date dtHrSaida) {
		this.dtHrSaida = dtHrSaida;
	}
	public float getValorHora() {
		return valorHora;
	}
	public void setValorHora(float valorHora) {
		this.valorHora = valorHora;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
    
}
