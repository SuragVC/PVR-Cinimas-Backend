package com.pvr.repository;
//R1
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.Admin;

public interface AdminDAO extends JpaRepository<Admin, Long>{
	
	Optional<Admin> findByEmail(String email);

}
