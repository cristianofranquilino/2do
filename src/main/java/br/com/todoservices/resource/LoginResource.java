package br.com.todoservices.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.com.todoservices.exception.CredentialsNotFoundException;
import br.com.todoservices.exception.WrongCredentialsException;
import br.com.todoservices.model.Login;
import br.com.todoservices.model.Usuario;
import br.com.todoservices.service.LoginService;

@Path("/login")
@Produces("application/json")
@Consumes("application/json")
public class LoginResource {

	private LoginService loginService = new LoginService();
	
	@POST
	public Response login(Login login) {
		
		try {
			Usuario user = loginService.realizarLogin(login);
			System.out.println(user);
			return Response.status(200).entity(new Gson().toJson(user)).build();
		} catch (WrongCredentialsException e) {
			return Response.status(Status.UNAUTHORIZED).entity(e.getMessage()).build();
		} catch (CredentialsNotFoundException c) {
			return Response.status(Status.UNAUTHORIZED).entity(c.getMessage()).build();
		}
	  }
}
