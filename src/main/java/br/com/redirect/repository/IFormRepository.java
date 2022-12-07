package br.com.redirect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.redirect.entity.RedirectForm;

@Repository
public interface IFormRepository extends JpaRepository<RedirectForm, String>{
	
	RedirectForm findByName(String dsUrl);
	
    RedirectForm findByIdAndCustomerId(String id, String customerId);
    
	@Query("select r from RedirectForm r where r.name = ?1")
    RedirectForm findDsUrlByDsName(String name);
}