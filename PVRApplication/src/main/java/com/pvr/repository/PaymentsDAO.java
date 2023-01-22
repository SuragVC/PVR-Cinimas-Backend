package com.pvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.Payments;

public interface PaymentsDAO extends JpaRepository<Payments, Long> {

}
