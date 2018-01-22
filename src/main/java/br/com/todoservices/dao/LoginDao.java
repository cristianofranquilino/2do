package br.com.todoservices.dao;

import java.util.List;

import br.com.todoservices.model.Login;

public class LoginDao extends GenericDao<Login>{

	public LoginDao() {
		super(Login.class);
	}

	public List<Login> getLogin(Login login) {
		
		System.out.println(login);
		
		return super.getDatastore().createQuery(Login.class)
					.field("email").equal(login.getEmail())
					.field("senha").equal(login.getSenha())
					.asList();		
	}	
	
	public boolean isRegistred(Login login) {
		return !super.getDatastore().createQuery(Login.class)
					.field("email").equal(login.getEmail())
					.asList().isEmpty();		
	}	
	
}
