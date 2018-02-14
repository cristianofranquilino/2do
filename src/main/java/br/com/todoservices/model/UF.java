package br.com.todoservices.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity("ufs")
public class UF {

	@Property("uf")
	private String descricao;

	public UF(String uf) {
		descricao = uf;
	}

	public UF(){}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
