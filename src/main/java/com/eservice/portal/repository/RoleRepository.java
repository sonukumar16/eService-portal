package com.eservice.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eservice.portal.entity.RoleEntity;



@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
}
