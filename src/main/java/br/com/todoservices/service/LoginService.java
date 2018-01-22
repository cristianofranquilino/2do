package br.com.todoservices.service;

import java.util.List;

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
	
	public Usuario realizarLogin(Login login) throws WrongCredentialsException, CredentialsNotFoundException {
		if (dao.isRegistred(login)){
			List<Login> logins = dao.getLogin(login);
			if (!logins.isEmpty()){
				return logins.get(0).getUsuario();
			}else{
				throw new WrongCredentialsException();
			}
		}else{
			throw new CredentialsNotFoundException();
		}
	}

}
