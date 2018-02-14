package br.com.todoservices.service;

import java.util.Calendar;

import br.com.todoservices.dao.UsuarioDao;
import br.com.todoservices.model.Usuario;
import br.com.todoservices.utils.Utils;

public class UsuarioService {

	public UsuarioDao dao;
	
	public UsuarioService() {
		dao = new UsuarioDao();
	}
	
	public void inserir(Usuario usuario) {
		if (this.valida(usuario)){
			if (usuario.getId() == null){
				usuario.setDataRegistro(Calendar.getInstance().getTime());				
				dao.insert(usuario);
			}else{
				//Atualizar
			}
		}else{
			throw new RuntimeException("Usuário nulo ou vazio.");
		}
	}

	private boolean valida(Usuario usuario) {
		if (Utils.isValido(usuario)){
			if (!Utils.isValido(usuario.getNome()))
				return false;
			if (!Utils.isValido(usuario.getSobrenome()))
				return false;
			if (!Utils.isValido(usuario.getCpfCnpj()))
				return false;
			if (!Utils.isValido(usuario.getDataNascimento()))
				return false;
			
			return true;
		}
		return false;
	}

}
