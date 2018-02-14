package br.com.todoservices.model;

import org.mongodb.morphia.annotations.Entity;

@Entity("municipios")
public class Municipio {

	private String nome;
	private String uf;
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
