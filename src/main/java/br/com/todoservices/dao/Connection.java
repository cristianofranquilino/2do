package br.com.todoservices.dao;

import java.util.ResourceBundle;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import br.com.todoservices.utils.PropertyUtils;

public class Connection {

	private MongoClient mongoClient;
	private DatabaseUser user;
	private Datastore datastore;
	private static Connection connection;

	public void openConnection(){
		this.loadCredentials();
		mongoClient = new MongoClient(
						new MongoClientURI("mongodb://" + user.getNome() +":"+ user.getSenha() +"@2do-desenv-shard-00-00-5ixld.mongodb.net:27017,"
								+ "2do-desenv-shard-00-01-5ixld.mongodb.net:27017,"
								+ "2do-desenv-shard-00-02-5ixld.mongodb.net:27017/test?ssl=true&replicaSet=2do-desenv-shard-0&authSource=admin"));
		this.setDatabase();
	}
	
	private void setDatabase(){
		if (mongoClient == null){
			this.openConnection();
		}
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.todo.model");
		datastore = morphia.createDatastore(mongoClient, user.getDatabase());
		datastore.ensureIndexes();
	}
	
	public Datastore getDatastoreManager(){
		if (this.datastore == null){
			this.setDatabase();
		}
		return this.datastore;
	}
	
	private void loadCredentials() {
		ResourceBundle resource = PropertyUtils.getResource("credentials");
		user = new DatabaseUser(resource.getString("user"), resource.getString("senha"), resource.getString("database"));
	}	
	
	public static Connection getInstance(){
		if (connection == null){
			connection = new Connection();
		}
		return connection;
	}
}

class DatabaseUser {

	private String nome;
	private String senha;
	private String database;

	DatabaseUser(String nome, String senha, String database){
		this.nome = nome;
		this.senha = senha;
		this.database = database;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

}
