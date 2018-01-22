package br.com.todoservices.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;

public abstract class GenericDao<T> {

	private Class<T> clazz;
	private Datastore datastoreManager;
	
	public GenericDao(Class<T> clazz){
		this.clazz = clazz;
		datastoreManager = Connection.getInstance().getDatastoreManager();
	}
	
	public List<T> getAll(){
		return datastoreManager.createQuery(clazz).asList();
	}
	
	protected Datastore getDatastore(){
		return this.datastoreManager;
	}
}
