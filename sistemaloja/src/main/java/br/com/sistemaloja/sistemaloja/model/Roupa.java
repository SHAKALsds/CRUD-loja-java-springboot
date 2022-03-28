package br.com.sistemaloja.sistemaloja.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Roupa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotEmpty
	private String imagemRoupa;
	
	@NotEmpty
	private String nomeRoupa;
	
	@NotEmpty
	private String modelo;
	
	@ManyToOne
	private Loja loja;

	//GETTERS AND SETTERS
	public long getCodigo() {
		return codigo;
	}

	public String getImagemRoupa() {
		return imagemRoupa;
	}

	public String getNomeRoupa() {
		return nomeRoupa;
	}

	public String getModelo() {
		return modelo;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public void setImagemRoupa(String imagemRoupa) {
		this.imagemRoupa = imagemRoupa;
	}

	public void setNomeRoupa(String nomeRoupa) {
		this.nomeRoupa = nomeRoupa;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	
}
