package br.com.todoservices.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

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
	
	public void insert(T obj){
		if (obj != null)
			datastoreManager.save(obj);
	}
	
	public void insertAll(List<T> objs){
		datastoreManager.save(objs);
	}
	
	public UpdateResults update(T obj) {
	    return datastoreManager.update(obj, createOperations());
	}
	
	protected Datastore getDatastore(){
		return this.datastoreManager;
	}
	
	protected UpdateOperations<T> createOperations() {
		return datastoreManager.createUpdateOperations(clazz);
	}
}
