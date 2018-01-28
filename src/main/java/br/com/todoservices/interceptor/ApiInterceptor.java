package br.com.todoservices.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.todoservices.annotations.NotSecure;
import br.com.todoservices.api.LoginApi;
import br.com.todoservices.utils.Utils;

@Provider
public class ApiInterceptor implements ContainerRequestFilter, ContainerResponseFilter{

	@Context
    private ResourceInfo resourceInfo;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final Response EXPIRED = Response.status(Response.Status.UNAUTHORIZED).entity("Acesso expirado, favor realizar o login novamente.").build();
    private static final Response UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).entity("Acesso não permitido.").build();
    private static final Response ERROR = Response.status(Response.Status.BAD_REQUEST).entity("Ocorreu um erro em nosso sistema, mas já estamos consertando.").build();
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		
		responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
	    responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
	    responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, Authorization");     
		
	    if (!requestContext.getUriInfo().getPath().equals("/login") ){			
	    	LoginApi.atualizaAtividade(requestContext.getHeaderString(AUTHORIZATION_PROPERTY));			
	     }
	    LoginApi.removeUsuariosOciosos();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
	
		Method method = resourceInfo.getResourceMethod();
		
		String token = requestContext.getHeaderString(AUTHORIZATION_PROPERTY);

		if (!method.isAnnotationPresent(NotSecure.class)){
			
			if (Utils.isValido(token)){
				try {
					if (!LoginApi.isLogado(token)) {
						requestContext.abortWith(EXPIRED);        			
						return;
					}					
					if (LoginApi.estaComAcessoOcioso(LoginApi.getSessaoByToken(token))) {						
						requestContext.abortWith(ERROR);        			
						return;
					}
				} catch (Exception e) {
					requestContext.abortWith(ERROR);        			
					return;
				}   			
        	}else {
        		requestContext.abortWith(UNAUTHORIZED);
        		return;
        	}
		}else{
			if (token != null){
				try {
					LoginApi.logoff(token);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
