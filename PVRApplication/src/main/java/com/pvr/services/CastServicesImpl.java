package com.pvr.services;
//S8
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvr.entity.Cast;
import com.pvr.entity.Movie;
import com.pvr.exceptions.MovieException;
import com.pvr.repository.CastDAO;
import com.pvr.repository.MovieDAO;

@Service
public class CastServicesImpl implements CastServices {
	private CastDAO castDao;
	private MovieDAO movieDao;
	@Autowired
	public CastServicesImpl(CastDAO castDao,MovieDAO movieDao) {
		super();
		this.castDao=castDao;
		this.movieDao=movieDao;
	}
	@Override
	public Movie addCastforMovie(Long movieID,Cast cast) throws MovieException {
		Optional<Movie> movieOpt =movieDao.findById(movieID);
		
		if(movieOpt.isEmpty())throw new MovieException("Movie not found with movie id "+movieID);
		
		Cast castOfMovie = movieOpt.get().getCast();
		
		if(castOfMovie!=null)throw new MovieException("Movie already have a cast assigned");
		movieOpt.get().setCast(cast);
		
		castDao.save(cast);
		return movieDao.save(movieOpt.get());
	}
	
}
