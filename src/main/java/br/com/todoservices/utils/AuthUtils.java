package br.com.todoservices.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

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

	public static String generateNewPass() {
		Random rnd = new Random();
		Integer n = 100000 + rnd.nextInt(900000);
		return n.toString();
	}
	
	public static void main(String[] args) throws IOException {
		
		 //String string = new String(Files.readAllBytes(Paths.get("src/main/resources/Municipios.html")));
		
		StringBuilder sb = new StringBuilder();
		
		 Files.lines(Paths.get("src/main/resources/Municipios.html")).forEachOrdered(l -> sb.append(monta(l)));
		 
		 System.out.println(sb.toString());
		 
		 
		
	}

	private static String monta(String l) {

		/* Abadia de Goiás (GO) */
		
		
			
			String[] split = l.split("\\|");
			
			String s1 = split[0].trim();
			
			String s = split[1].trim();;
			
			System.out.println(s1);
			System.out.println(s);
			return s;
		
	}
	
	
}
