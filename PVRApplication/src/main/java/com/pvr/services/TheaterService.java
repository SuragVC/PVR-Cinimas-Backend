package com.pvr.services;

import java.util.List;

import com.pvr.entity.Theater;
import com.pvr.exceptions.TheaterException;

public interface TheaterService {
	public Theater addNewTheater(Theater theater)throws TheaterException;
	public Theater deleteTheater(String theaterName)throws TheaterException;
	public Theater updateRunningMovie(String movieName,String theaterName) throws TheaterException;
	public List<Theater> findTheaterByMovie(String movieName)throws TheaterException;
	public List<Theater> findAllTheater()throws TheaterException;
	public List<Theater> findTheaterByPlace(String placeName) throws TheaterException;
}
