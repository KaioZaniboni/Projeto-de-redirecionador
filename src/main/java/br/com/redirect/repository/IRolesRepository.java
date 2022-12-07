package br.com.redirect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.redirect.entity.Roles;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, String>{
	
    @Query(value = "select a.* from tbroles a  inner join tbaccessgroupXroles x on x.RoleId = a.id where x.AccessGroupId= :accessGroupId and x.flActive = :flActive", nativeQuery = true)
	List<Roles> findByAccessGroupIdAndFlActive(@Param("accessGroupId") String accessGroupId, @Param("flActive") int flActive);

}