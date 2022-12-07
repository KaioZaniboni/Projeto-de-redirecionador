package br.com.redirect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.redirect.entity.Users;

@Repository
public interface IUserRepository extends CrudRepository<Users, String>{

	Users findByLogin(String login);
}
