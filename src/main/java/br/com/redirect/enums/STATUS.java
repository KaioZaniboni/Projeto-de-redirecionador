package br.com.redirect.enums;

public enum STATUS {
 
	ACTIVE(1),
	NOTACTIVE(0),
	ENABLE(1),
	DISABLE(0),
	EXCLUDED(3),
	TEMPORARY(2),
	WAITING(4),
	DISABLEALL(5);
	
	public int INT;
	
	private STATUS(int INT) {
		this.INT=INT;
	}
}