package br.com.redirect.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tbaccessgroupxroles")
@Table(name = "tbaccessgroupxroles")
@NoArgsConstructor
@Getter
@Setter
public class AccessGroupXRoles extends EntityMain {
	
	@Column(name = "accessgroupid", nullable = false, length=25)
	private String accessGroupId;
	
	@Column(name = "roleid", nullable = false, length=25)
	private String roleId; 
	
	@Override
	public String getSequenceName() {
		return "sqaccessgroupxroles";
	}
	
	@Override
	public boolean isAuditable() {
		return false;
	}
}