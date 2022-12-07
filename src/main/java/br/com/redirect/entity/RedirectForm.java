package br.com.redirect.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbredirect")
public class RedirectForm extends EntityMain {
	
	public RedirectForm() {
		
	}	
	
	public RedirectForm(String dsUrl) {
		this.dsUrl = dsUrl;
	}
	
	@Column(name = "dsurl", nullable = false, length=1000)
	private String dsUrl;

	@Override
	public String getSequenceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuditable() {
		// TODO Auto-generated method stub
		return false;
	}
}