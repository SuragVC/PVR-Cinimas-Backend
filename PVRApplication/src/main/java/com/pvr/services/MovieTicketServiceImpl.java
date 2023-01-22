package com.pvr.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvr.entity.MovieTicket;
import com.pvr.entity.PVRCinimasObject;
import com.pvr.entity.Payments;
import com.pvr.entity.Theater;
import com.pvr.exceptions.TicketException;
import com.pvr.exceptions.TicketSerilizationException;
import com.pvr.repository.MovieTicketDAO;
import com.pvr.repository.PaymentsDAO;
import com.pvr.repository.TheaterDAO;
import com.pvr.util.DeSerilizeTickets;
import com.pvr.util.GeneratePrimaryKey;
import com.pvr.util.SerilizeTickets;

@Service
public class MovieTicketServiceImpl implements MovieTicketServices {
	private MovieTicketDAO ticketDao;
	private PaymentsDAO paymentsDao;
	private TheaterDAO theaterDao;
	private GeneratePrimaryKey key;

	@Autowired
	public MovieTicketServiceImpl(MovieTicketDAO ticketDao, PaymentsDAO paymentsDao, TheaterDAO theaterDao,
			GeneratePrimaryKey key) {
		this.ticketDao = ticketDao;
		this.paymentsDao = paymentsDao;
		this.theaterDao = theaterDao;
		this.key = key;
	}

	@Override
	public List<Object> bookATicket(MovieTicket ticket) throws TicketException {
		
		Optional<Theater> theater = theaterDao.findByName(ticket.getTheaterName());
		if (theater.isEmpty())
			throw new TicketException("!ALERT! Theater not found by the name " + ticket.getTheaterName());
		if (theater.get().getAvailableSeats() == 0)
			throw new TicketException("!ALERT! Theater is Housefull");
		if (ticket.getTicketCount() == 0)
			throw new TicketException("!ALERT! Minimum 1 ticket needs to book");
		if (theater.get().getAvailableSeats() < ticket.getTicketCount())
			throw new TicketException("!ALERT! " + ticket.getTicketCount() + " Seats not available in the theater!");
		Payments payment = ticket.getPayment();
		Double price = theater.get().getPriceForSeat();
		Double totalPrice = price * ticket.getTicketCount();
		if (totalPrice > payment.getTotalPayment())
			throw new TicketException("!ALERT! Not enough money, Total payment needed = " + totalPrice);
		if (totalPrice < payment.getTotalPayment())
			payment.setReturnAmount(totalPrice - payment.getTotalPayment());

		theater.get().setAvailableSeats(theater.get().getAvailableSeats() - ticket.getTicketCount());

		theaterDao.save(theater.get());
		payment.setPaymentID(key.generatePrimaryKey());
		ticket.setMovieTicketID(key.generatePrimaryKey());
		ticket.setStatus("ACTIVE");
		paymentsDao.save(payment);
		ticketDao.save(ticket);
		PVRCinimasObject homeObject= new PVRCinimasObject();
		List<Object> response = Arrays.asList(ticket, theater,homeObject);
		return response;
	}

	@Override
	public MovieTicket cancelATicket(Long ticketID) throws TicketException {
		Optional<MovieTicket> ticket = ticketDao.findById(ticketID);
		if (ticket.isEmpty())
			throw new TicketException("!ALERT! Ticket not found with Ticket ID : " + ticketID);
		ticket.get().setStatus("CANCELED");
		Theater theater = theaterDao.findByName(ticket.get().getTheaterName()).get();
		theater.setAvailableSeats(theater.getAvailableSeats() + ticket.get().getTicketCount());
		theaterDao.save(theater);
		return ticketDao.save(ticket.get());
	}

	@Override
	public String serilizeAllTheTickets() throws TicketSerilizationException, IOException {
		List<MovieTicket> listOfTicket = ticketDao.findAll();
		if (listOfTicket.isEmpty())
			throw new TicketSerilizationException("!ALERT! No Ticket is in the database!");

		try (FileOutputStream stream = new FileOutputStream("AllTickets.ser");) {
			try(ObjectOutputStream fileStream = new ObjectOutputStream(stream);){
				SerilizeTickets serilizeTickets = () -> {
					fileStream.writeObject(listOfTicket);
				};
				serilizeTickets.serilizeTheTickets();
			}catch(Exception e) {
				throw new TicketSerilizationException("!RED ALERT! Internal service error");
			}
		} catch (Exception e) {
			throw new TicketSerilizationException("!RED ALERT! Internal service error");
		}
		return "All the tickets are serilized and saved";
	}

	@Override
	public List<MovieTicket> deSerilizeAllTheTickets() throws TicketSerilizationException, IOException {
		List<MovieTicket>listOfDeserilisedObjects=new ArrayList<>();
		try(FileInputStream stream = new FileInputStream("AllTickets.ser");){
			try(ObjectInputStream fileStream = new ObjectInputStream(stream);){
				DeSerilizeTickets deSerilize = ()->{
					@SuppressWarnings("unchecked")
					List<MovieTicket>list =(List<MovieTicket>) fileStream.readObject();
					return list;
				};
				listOfDeserilisedObjects=deSerilize.deSerilizeTickets();
			}catch(Exception e) {
				throw new TicketSerilizationException("!RED ALERT! Internal service error");
			}
		}catch(Exception e) {
			throw new TicketSerilizationException("!RED ALERT! File not found");
		}
		if(listOfDeserilisedObjects.isEmpty()){
			throw new TicketSerilizationException("!RED ALERT! Something Went Wrong");
		}else {
			return listOfDeserilisedObjects;
		}
	}

}
