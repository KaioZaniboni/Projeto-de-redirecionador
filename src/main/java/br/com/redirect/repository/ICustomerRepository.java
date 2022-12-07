package br.com.redirect.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.redirect.entity.Customer;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, String>{
	
	@Query("select c from Customer c where c.name like %?1%")
	List<Customer> findCustomerByName(String name);
	
}
