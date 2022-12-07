package br.com.redirect.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.redirect.components.UserRequest;
import br.com.redirect.entity.Roles;
import br.com.redirect.entity.Users;
import br.com.redirect.enums.STATUS;
import br.com.redirect.repository.IRolesRepository;
import br.com.redirect.repository.IUserRepository;
import lombok.Getter;

@Repository
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUserRepository userRepository;
		
	@Autowired
	private IRolesRepository roleRepository;
	
	@Getter
	@Autowired
	private UserRequest userRequest;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Users usuario = userRepository.findByLogin(login);
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		
		List<Roles> list = roleRepository.findByAccessGroupIdAndFlActive(usuario.getIdAccessGroup(), STATUS.ACTIVE.INT);
		Set<Roles> roles = new HashSet<Roles>(list);
		usuario.setRoles(roles);

		return usuario;
	}
}