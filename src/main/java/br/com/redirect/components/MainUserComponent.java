package br.com.redirect.components;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MainUserComponent {

	private String id;
	
	private String customerId;
	
	private String dsMapping;
	
	private Integer timezone;
	
	private String parentId;

	private String language;
	
	private String accessGroupId;
}