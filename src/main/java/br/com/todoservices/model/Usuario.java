package br.com.todoservices.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity("usuarios")
public class Usuario {
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String urlFoto;
	private String nome;
	private String sobrenome;
	private String nomeEmpresa;
	private String cpfCnpj;
	private Date dataNascimento;
	private Date dataRegistro;
	
	@Transient
	private Login login;
	
	@Transient
	private String token;
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + getId() + ", urlFoto=" + urlFoto + ", nome=" + nome + ", sobrenome=" + sobrenome
				+ ", nomeEmpresa=" + nomeEmpresa + ", cpfCnpj=" + cpfCnpj + ", dataNascimento=" + dataNascimento
				+ ", dataRegistro=" + dataRegistro + "]";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
