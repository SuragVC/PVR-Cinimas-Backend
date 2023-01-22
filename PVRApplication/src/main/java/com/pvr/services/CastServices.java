package com.pvr.services;
//S7
import com.pvr.entity.Cast;
import com.pvr.entity.Movie;
import com.pvr.exceptions.MovieException;

public interface CastServices {
	public Movie addCastforMovie(Long movieID,Cast cast)throws MovieException;
}
