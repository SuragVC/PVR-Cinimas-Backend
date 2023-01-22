package com.pvr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvr.entity.Movie;
import com.pvr.entity.Theater;
import com.pvr.exceptions.TheaterException;
import com.pvr.repository.AddressDAO;
import com.pvr.repository.MovieDAO;
import com.pvr.repository.TheaterDAO;
import com.pvr.util.GeneratePrimaryKey;

@Service
public class TheaterServiceImpl implements TheaterService{
	private TheaterDAO theaterDao;
	private GeneratePrimaryKey key;
	private AddressDAO addressDao;
	private MovieDAO movieDao;
	@Autowired
	public TheaterServiceImpl(TheaterDAO theaterDao,GeneratePrimaryKey key,AddressDAO addressDao,MovieDAO movieDao) {
		this.theaterDao=theaterDao;
		this.key=key;
		this.addressDao=addressDao;
		this.movieDao=movieDao;
	}
	@Override
	public Theater addNewTheater(Theater theater) throws TheaterException {
		Optional<Theater> theaterOpt=theaterDao.findByName(theater.getName());
		if(theaterOpt.isPresent())throw new TheaterException("!ALERT! Theater Already exists with this name!");
		theater.setAvailableSeats(theater.getSeats());
		theater.setTheaterID(key.generatePrimaryKey());
		theater.getAddress().setTheaterID(theater.getTheaterID());
		theater.getAddress().setAddressId(key.generatePrimaryKey());
		addressDao.save(theater.getAddress());
		return theaterDao.save(theater);
	}
	@Override
	public Theater updateRunningMovie(String movieName, String theaterName) throws TheaterException {
		Optional<Theater> theater=theaterDao.findByName(theaterName);
		if(theater.isEmpty())throw new TheaterException("!ALERT! Theater not exists with this name!");
		Optional<Movie> movie=movieDao.findByName(movieName);
		if(movie.isEmpty())throw new TheaterException("!ALERT! Movie not exists with this name!");
		theater.get().setRunningMovie(movieName);
		return theaterDao.save(theater.get());
	}
	@Override
	public Theater deleteTheater(String theaterName) throws TheaterException {
		Optional<Theater> theater = theaterDao.findByName(theaterName);
		if(theater.isEmpty())throw new TheaterException("!ALERT! Theater not exists with this name!");
		theaterDao.delete(theater.get());
		addressDao.delete(theater.get().getAddress());
		return theater.get();
	}
	@Override
	public List<Theater> findTheaterByMovie(String movieName) throws TheaterException {
		List<Theater>theaterList=theaterDao.findByRunningMovie(movieName);
		if(theaterList.isEmpty())throw new TheaterException("!ALERT! Movie is not running in any theater.");
		return theaterList;
	}
	@Override
	public List<Theater> findAllTheater() throws TheaterException {
		List<Theater>theaterList=theaterDao.findAll();
		if(theaterList.isEmpty())throw new TheaterException("!ALERT! No theater listed on database");
		return theaterList;
	}
	@Override
	public List<Theater> findTheaterByPlace(String placeName) throws TheaterException {
		List<Theater> theaterList=theaterDao.findTheaterByPlace(placeName);
		if(theaterList.isEmpty())throw new TheaterException("!ALERT! No theater is situated at "+placeName);
		return theaterList;
	}
	
}
