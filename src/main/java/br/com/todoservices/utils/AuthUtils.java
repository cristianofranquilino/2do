package br.com.todoservices.utils;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AuthUtils {

	public static String converteChavePublicaParaString(PublicKey chavePublica) throws NoSuchAlgorithmException, InvalidKeySpecException  {		
		KeyFactory fact = KeyFactory.getInstance("RSA");
	    X509EncodedKeySpec spec = fact.getKeySpec(chavePublica,X509EncodedKeySpec.class);
	    return Base64.encodeBase64String(spec.getEncoded());
	}
	
	public static PublicKey converteStringParaChavePublica(String chavePublica) throws GeneralSecurityException {		
	    byte[] data = Base64.decodeBase64(chavePublica);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
	    KeyFactory fact = KeyFactory.getInstance("RSA");
	    return fact.generatePublic(spec);
	}
	
}
