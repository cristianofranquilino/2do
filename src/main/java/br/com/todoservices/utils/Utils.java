package br.com.todoservices.utils;

public class Utils {
	public static final boolean isValido(Object objeto) {

		if (objeto == null)
			return false;
		if (objeto instanceof String)
			return valida((String) objeto);
		return true;
	}
	private static boolean valida(String objeto) {
		return (objeto != null) && (!objeto.trim().isEmpty());
	}

}
