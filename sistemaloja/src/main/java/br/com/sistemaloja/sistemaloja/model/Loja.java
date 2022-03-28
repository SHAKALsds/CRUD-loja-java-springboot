package br.com.sistemaloja.sistemaloja.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Loja implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	private String nomeImagem;

	@NotEmpty
	private String data;
	
	@NotEmpty
	private String nomeLoja;
	
	@OneToMany(mappedBy = "loja", cascade = CascadeType.REMOVE)
	private List<Roupa> roupas;

	//GETTERS AND SETTERS
	public long getId() {
		return id;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public String getData() {
		return data;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public List<Roupa> getRoupas() {
		return roupas;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public void setRoupas(List<Roupa> roupas) {
		this.roupas = roupas;
	}

}


