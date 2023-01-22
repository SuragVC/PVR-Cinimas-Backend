package com.pvr.util;

import java.util.List;

import com.pvr.entity.MovieTicket;

@FunctionalInterface
public interface DeSerilizeTickets {
	List<MovieTicket> deSerilizeTickets()throws Exception;
}
