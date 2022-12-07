package br.com.redirect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.redirect.entity.Roles;
import br.com.redirect.enums.STATUS;
import br.com.redirect.repository.IRolesRepository;

@Service
public class RolesService {
	
	private IRolesRepository roleRepository;
	
	@Autowired
	public RolesService(IRolesRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
public List<Roles> findByAccessGroupId(String accessGroupId){
	return this.roleRepository.findByAccessGroupIdAndFlActive(accessGroupId, STATUS.ACTIVE.INT);
}
	
}