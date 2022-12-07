package br.com.redirect.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbroles", uniqueConstraints = {@UniqueConstraint(columnNames = "dsname", name = "uk_dsrole")})
@NoArgsConstructor
@Setter
@Getter
public class Roles extends EntityMain implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	public static final String _PREFIX = "ROLE_";
	
	public static final String _SELECT = "SELECT";
	public static final String _INSERT = "INSERT";
	public static final String _UPDATE = "UPDATE";
	public static final String _DELETE = "DELETE";
	public static final String _ADMIN  = "ADMIN";
	
	@Transient
	private String mySelect;

	@Transient
	private String myInsert;	
	
	@Transient
	private String myUpdate;
	
	@Transient
	private String myDelete;

	@Transient
	private String myAdmin;

	@Transient
	private String grSelect;

	@Transient
	private String grInsert;	
	
	@Transient
	private String grUpdate;
	
	@Transient
	private String grDelete;

	@Transient
	private String grAdmin;
	
	@Override
	public String getAuthority() {
		return _PREFIX + super.getName();
	}

	@Override
	public String getSequenceName() {
		return null;
	}
	
	public boolean isAuditable() {
		return false;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}