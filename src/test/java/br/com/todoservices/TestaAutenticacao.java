package br.com.todoservices;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;

import br.com.todoservices.auth.Token;
import br.com.todoservices.model.Usuario;

public class TestaAutenticacao {

	public static String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOntcInRpbWVzdGFtcFwiOjE1MTcxMTAyNzMsXCJtYWNoaW5lSWRlbnRpZmllclwiOjgwODIxNzMsXCJwcm9jZXNzSWRlbnRpZmllclwiOjI4MTUsXCJjb3VudGVyXCI6NjIyODE5fSxcImNwZkNucGpcIjpcIjExNjIxMTYxNzA2XCJ9IiwiaXNzIjoiY3Jpc3RpYW5vIn0.MQggXaeawDZ8CBe11DoerMb3UKgrxI7U6lrONWiXXe631eZ77Yd6GBoeuQ6TRj0QRPPpwwxKYZZ5-_x3NqUcHY-X16sCv5Tn2BuGmMQ_R-xhCvvu0ukAScG5Jn97MbAiub7Tv9tiBmqRK4Ab92Nzx_rHfVdwO7v7ApWPl8t6sO8";
	
	public static String AUTH0 = "cristiano"; 
	
	/*
	 * 
	 * */
	
	public static void main(String[] args) throws Exception {
		
		Usuario mockUser = mockUser();
		String json = new Gson().toJson(mockUser);
		
		KeyPair keyPair = getKeyPair();
		
		Algorithm algorithm = Algorithm.RSA512((RSAPublicKey)keyPair.getPublic(), (RSAPrivateKey)keyPair.getPrivate());
	    String token = JWT.create().withIssuer(AUTH0).withSubject(json).sign(algorithm);
		
	    Token tokenObj = new Token(token, (RSAPublicKey)keyPair.getPublic());
	    
	    TOKEN = tokenObj.getToken();
	    
	    System.out.println(tokenObj.getToken());
	    
	    if (validaTokenCom(tokenObj)){
	    	System.out.println("Válido");
	    	
	    }else{
	    	System.out.println("inválido");
	    }
	    
	    getUsuario();
		
	}
	
	
	private static PublicKey converteStringParaChavePublica(String chavePublica) throws GeneralSecurityException {		
	    byte[] data = Base64.decodeBase64(chavePublica);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    return fact.generatePublic(spec);
	}
	
	
	public static Boolean validaTokenCom(Token t) throws GeneralSecurityException {
		
		RSAPublicKey pk = t.getPublicKey();
		
		try {
			Algorithm algorithm = Algorithm.RSA512(pk, (RSAPrivateKey)getKeyPair().getPrivate());
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(AUTH0).build(); 
			DecodedJWT jwt = verifier.verify(TOKEN);
			
			String jsonUsuario = jwt.getSubject();
			
			Usuario usuarioDecoded = new Gson().fromJson(jsonUsuario, Usuario.class);
			
			System.out.println(usuarioDecoded);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Usuario getUsuario(){
		try {
		    DecodedJWT jwt = JWT.decode(TOKEN);
		    String jsonUsuario = jwt.getSubject();
			
			Usuario usuarioDecoded = new Gson().fromJson(jsonUsuario, Usuario.class);
			
			System.out.println(usuarioDecoded);
		    return usuarioDecoded;
		} catch (JWTDecodeException exception){
		    return null;
		}
	}
	
	private static KeyPair getKeyPair() throws NoSuchAlgorithmException{
		KeyPairGenerator keyInstance = KeyPairGenerator.getInstance("RSA");
		keyInstance.initialize(1024);
		return keyInstance.generateKeyPair();
	}
	
	
	private static Usuario mockUser(){
		
		Usuario usuario = new Usuario();
		
		usuario.setId(new ObjectId(new Date()));
		usuario.setCpfCnpj("11621161706");
		return usuario;
	}
	
	
}
