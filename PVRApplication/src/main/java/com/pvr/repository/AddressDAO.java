package com.pvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.Address;

public interface AddressDAO extends JpaRepository<Address, Long>{

}
