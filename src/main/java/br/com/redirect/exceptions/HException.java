package br.com.redirect.exceptions;

public class HException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public HException(String message) {
		super(message);
	}
	
	public HException(Exception ex) {
		super(ex);
	}
}