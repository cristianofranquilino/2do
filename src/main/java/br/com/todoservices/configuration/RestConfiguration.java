package br.com.todoservices.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.todoservices.resource.LoginResource;

@ApplicationPath("/rest")
public class RestConfiguration extends Application {
	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>> classes = new HashSet<Class<?>>();
	    classes.add(LoginResource.class);
	    return classes;
	}
}