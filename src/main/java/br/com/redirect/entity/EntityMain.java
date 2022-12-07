package br.com.redirect.entity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
@Check(constraints = " (dsname <> '' )")
public abstract class EntityMain {

	@Id
	@Column(name = "id", updatable = false, nullable = false, length=40)
	private String id;

	@Column(name = "flactive", nullable = false, length=1)
	private int flActive = 1;
	
	@Column(name = "idupdate", nullable = false, length=25)
	private String idUpdate;

	@Column(updatable = false, name = "idinsert", nullable=false, length=25)
	private String idInsert;

	@Column(name = "dtupdate", nullable=false)
	private ZonedDateTime dtUpdate;

	@Column(updatable = false, name = "dtinsert", nullable=false)
	private ZonedDateTime dtInsert;

	@Column(name = "dsname", nullable = false, length=255)
	private String name;

	@Column(name = "customerid", nullable = false, length=25)
	private String customerId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "customerid", nullable = false, insertable = false, updatable = false)
	private Customer customer;

	@Transient
	private Boolean autitable = true;
	
	@Transient
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	protected Object dto;
	
	@PreUpdate
	private void preUpdate() throws RuntimeException {
		dtUpdate = LocalDateTime.now().atZone(ZoneOffset.UTC);
	}

	@PrePersist
	private void prePersist() throws RuntimeException{
		dtInsert = LocalDateTime.now().atZone(ZoneOffset.UTC);
		preUpdate();
	}
	
	@JsonIgnore
	@Transient
	public abstract String getSequenceName();
	
	@Transient
	public abstract boolean isAuditable();

}