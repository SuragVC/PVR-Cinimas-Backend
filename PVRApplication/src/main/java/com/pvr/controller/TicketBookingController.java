package com.pvr.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.entity.MovieTicket;
import com.pvr.exceptions.TicketException;
import com.pvr.exceptions.TicketSerilizationException;
import com.pvr.services.MovieTicketServices;

@RestController
@RequestMapping("/pvr")
public class TicketBookingController {
	private MovieTicketServices ticketServices;
	@Autowired
	public TicketBookingController(MovieTicketServices ticketServices) {
		this.ticketServices=ticketServices;
	}
	@PostMapping("/user/book/ticket")
	public ResponseEntity<List<Object>>Book_A_Ticket(@RequestBody @Valid MovieTicket ticket) throws TicketException{
		List<Object> bookedTicket=ticketServices.bookATicket(ticket);
		return new ResponseEntity<List<Object>>(bookedTicket,HttpStatus.OK);
	}
	@GetMapping("/user/ticket/cancel")
	public ResponseEntity<MovieTicket>Cancel_A_Ticket(@RequestParam Long ticketid) throws TicketException{
		MovieTicket canceledTicket=ticketServices.cancelATicket(ticketid);
		return new ResponseEntity<MovieTicket>(canceledTicket,HttpStatus.OK);
	}
	@GetMapping("/admin/ticket/serilize")
	public ResponseEntity<String>Serilize_All_The_Tickets() throws TicketSerilizationException, IOException {
		String answer = ticketServices.serilizeAllTheTickets();
		return new ResponseEntity<String>(answer,HttpStatus.OK);
	}
	@GetMapping("/admin/ticket/deserilize")
	public ResponseEntity<List<MovieTicket>>DeSerilize_All_The_Tickets() throws TicketSerilizationException, IOException {
		List<MovieTicket>list = ticketServices.deSerilizeAllTheTickets();
		return new ResponseEntity<List<MovieTicket>>(list,HttpStatus.OK);
	}
}
