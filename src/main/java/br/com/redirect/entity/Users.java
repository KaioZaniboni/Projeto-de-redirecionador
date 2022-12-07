package br.com.redirect.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbuser", uniqueConstraints = {@UniqueConstraint(columnNames = "dslogin", name = "uk_dslogin")})
public class Users extends EntityMain implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	public static final String SYSTEM_USER_ID = "2";
	public static final String PUBLIC_USER_ID = "-999999";
	
	public static final String USER_SYSTEM_ID = "2";
	public static final String USER_SYSTEM_LOGIN = "system";
	public static final String PASSWORD = "******";
	
	public static final int MIN_LENGTH_PASSWORD = 4;
	public static final int MAX_LENGTH_PASSWORD = 20;
	
	
	 
	@Column(columnDefinition = "timestamp with time zone", name = "dtlastaccess")
	private LocalDateTime lastAccess;

	@Column(name = "dslogin", nullable = false, length = 128)
	private String login;

	@Column(name = "dspassword", nullable = false, length = 255)
	private String password;

	@Column(name = "dsphone", nullable = true, length = 20)
	private String phone;

	@Column(name = "dscellphone", nullable = true, length = 20)
	private String cellphone;
		
	@Column(name = "dsemail", nullable = true, length = 128)
	private String email;
	
	@Column(name="sessionexpire", length = 10)
	private int sessionExpire;

	@Column(name = "dtlastpasswordreset")
	private LocalDateTime lastPasswordReset;
	
	@Override
	public String getSequenceName() {
		return "sqhentity";
	}

	@Column(name = "idaccessgroup", length=25)
	private String idAccessGroup;
	
	@Column(name = "currentuserevolutionid", length=25)
	private String currentUserEvolutionId;
	
	@Transient
	private Set<Roles> roles = null;
	
	public void setRoles(Set<Roles> roles){
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Transient
	private String details;
	
	public boolean isAuditable() {
		return true;
	}	
}