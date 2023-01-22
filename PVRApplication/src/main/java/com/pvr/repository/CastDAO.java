package com.pvr.repository;
//R4
import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.Cast;

public interface CastDAO extends JpaRepository<Cast, Long>{

}
