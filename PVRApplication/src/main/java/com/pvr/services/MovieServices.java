package com.pvr.services;
//S5
import java.util.List;

import org.springframework.data.domain.Page;

import com.pvr.entity.Movie;
import com.pvr.exceptions.MovieException;

public interface MovieServices {
	public Movie createMovie(Movie movie)throws MovieException;
	public Page<Movie>getMovieByPagination(Integer pageNo)throws MovieException;
	public List<Movie>UpcommingMovies(Integer limit,Integer offset) throws MovieException;
	public List<Movie>TodaysMovies(Integer limit,Integer offset) throws MovieException;
	public List<Movie>RunningMovies(Integer limit,Integer offset) throws MovieException;
}
