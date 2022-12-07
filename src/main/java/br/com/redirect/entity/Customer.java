package br.com.redirect.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbcustomer")
public class Customer extends EntityMain {

	public static final String _FILAH = "0";
	public static final String _DMBOX = "-1";
	public static final String _BOTH = "'" + _FILAH + "','" + _DMBOX + "'";

	public static String[] GetCustomerId(String CustomerId) {
		String[] CustomerIds = new String[3];
		CustomerIds[0] = _FILAH;
		CustomerIds[1] = _DMBOX;
		CustomerIds[2] = CustomerId;
		return CustomerIds;
	}
	
	@Override
	public String getSequenceName() {
		return "sqcustomer";
	}

	@Override
	public boolean isAuditable() {
		return true;
	}
	
}