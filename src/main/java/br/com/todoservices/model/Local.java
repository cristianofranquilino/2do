package br.com.todoservices.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity("locais")
public class Local {

	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;

	private String identificacao;
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String complemento;
	private boolean ativo;
	private boolean principal;
	private Municipio cidade;
	private Location location;
	
	@Reference
	private Usuario usuario;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public Municipio getCidade() {
		return cidade;
	}

	public void setCidade(Municipio cidade) {
		this.cidade = cidade;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", identificacao=" + identificacao + ", logradouro=" + logradouro + ", numero="
				+ numero + ", cep=" + cep + ", bairro=" + bairro + ", complemento=" + complemento + ", ativo=" + ativo
				+ ", principal=" + principal + ", cidade=" + cidade + ", location=" + location + ", usuario=" + usuario
				+ "]";
	}
	
}
