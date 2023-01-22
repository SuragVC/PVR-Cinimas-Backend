package com.pvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.PrimaryKeyGenerator;

public interface PrimaryKeyGeneratorDAO extends JpaRepository<PrimaryKeyGenerator, Long>{

}
