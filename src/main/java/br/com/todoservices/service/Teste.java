package br.com.todoservices.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;

import br.com.todoservices.dao.LoginDao;
import br.com.todoservices.model.Login;

@Path("/teste")
@Produces("application/json")
public class Teste {
	
	private LoginDao usuarioDao;
	
	  @GET
	  public Response convertFtoC() throws JSONException {
		
		usuarioDao = new LoginDao();
		  
		List<Login> all = new ArrayList<>();
		all = usuarioDao.getAll();
		return Response.status(200).entity(new Gson().toJson(all)).build();
	  }
}
