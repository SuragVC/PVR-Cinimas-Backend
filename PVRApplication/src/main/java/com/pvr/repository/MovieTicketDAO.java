package com.pvr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvr.entity.MovieTicket;

public interface MovieTicketDAO extends JpaRepository<MovieTicket, Long>{

}
