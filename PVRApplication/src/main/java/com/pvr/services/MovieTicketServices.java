package com.pvr.services;

import java.io.IOException;
import java.util.List;

import com.pvr.entity.MovieTicket;
import com.pvr.exceptions.TicketException;
import com.pvr.exceptions.TicketSerilizationException;

public interface MovieTicketServices {
	public List<Object>bookATicket(MovieTicket ticket)throws TicketException;
	public MovieTicket cancelATicket(Long ticketID)throws TicketException;
	public String serilizeAllTheTickets()throws TicketSerilizationException, IOException;
	public List<MovieTicket> deSerilizeAllTheTickets()throws TicketSerilizationException, IOException;
}
