package com.pvr.services;
//S6
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pvr.entity.Cast;
import com.pvr.entity.Movie;
import com.pvr.entity.PrimaryKeyGenerator;
import com.pvr.exceptions.MovieException;
import com.pvr.repository.CastDAO;
import com.pvr.repository.MovieDAO;
import com.pvr.repository.PrimaryKeyGeneratorDAO;
import com.pvr.util.GeneratePrimaryKey;

@Service
public class MovieServiceImpl implements MovieServices{
	private MovieDAO movieDao;
	private CastDAO castDao;
	private GeneratePrimaryKey key;
	@Autowired
	public MovieServiceImpl(MovieDAO movieDao,GeneratePrimaryKey key,CastDAO castDao) {
		this.movieDao=movieDao;
		this.key=key;
		this.castDao= castDao;
	}
	@Override
	public Movie createMovie(Movie movie) throws MovieException {
		
		Optional<Movie> movieOpt=movieDao.findByName(movie.getName());
		if(movieOpt.isPresent())throw new MovieException("!ALERT! Movie already present in the database.");
		movie.setLanguage(movie.getLanguage().toUpperCase());
		movie.setGenere(movie.getGenere().toUpperCase());
		Cast cast=movie.getCast();
		Long value=key.generatePrimaryKey();
		cast.setCastId(value);
		castDao.save(cast);
		movie.setCast(cast);
		return movieDao.save(movie);
	}
	@Override
	public Page<Movie> getMovieByPagination(Integer pageNo) throws MovieException {
		Pageable pageable = PageRequest.of(pageNo,10);
		Page<Movie>pageOfMovie=movieDao.findAll(pageable);
		return pageOfMovie;
	}
	@Override
	public List<Movie> UpcommingMovies(Integer limit, Integer offset) throws MovieException {
		List<Movie> upcommingMovieList=movieDao.upcommingMovies(limit, offset);
		if(upcommingMovieList.isEmpty()) 
			throw new MovieException("!ALERT! List is empty");
		else
			return upcommingMovieList;
	}
	@Override
	public List<Movie> TodaysMovies(Integer limit, Integer offset) throws MovieException {
		List<Movie>todaysMovieList=movieDao.todaysMovies(limit, offset);
		if(todaysMovieList.isEmpty()) 
			throw new MovieException("!ALERT! List is empty");
		else
			return todaysMovieList;
	}
	@Override
	public List<Movie> RunningMovies(Integer limit, Integer offset) throws MovieException {
		List<Movie>runningMovieList=movieDao.runningMovies(limit,offset);
		if(runningMovieList.isEmpty()) 
			throw new MovieException("!ALERT! List is empty!");
		else
			return runningMovieList;
	}
	
	
	
}
