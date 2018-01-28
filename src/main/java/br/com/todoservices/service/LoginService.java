package br.com.todoservices.service;

import java.time.LocalDateTime;
import java.util.List;

import br.com.todoservices.api.LoginApi;
import br.com.todoservices.api.TokenAPI;
import br.com.todoservices.auth.Sessao;
import br.com.todoservices.auth.Token;
import br.com.todoservices.dao.LoginDao;
import br.com.todoservices.exception.CredentialsNotFoundException;
import br.com.todoservices.exception.WrongCredentialsException;
import br.com.todoservices.model.Login;
import br.com.todoservices.model.Usuario;

public class LoginService {

	private LoginDao dao;
	
	public LoginService(){
		dao = new LoginDao();
	}
	
	public Usuario realizarLogin(Login login) throws Exception {
		if (dao.isRegistred(login)){
			List<Login> logins = dao.getLogin(login);
			if (!logins.isEmpty()){
				Usuario usuario = logins.get(0).getUsuario();
				
				Token token = new TokenAPI<Usuario>().toToken(usuario);
				Sessao sessaoUser = new Sessao();
				
				sessaoUser.setToken(token);
				sessaoUser.setUltimaAtividade(LocalDateTime.now());

				LoginApi.addUsuarioSession(sessaoUser);
			
				usuario.setToken(token.getToken());
				
				return usuario;
			}else{
				throw new WrongCredentialsException();
			}
		}else{
			throw new CredentialsNotFoundException();
		}
	}

}
