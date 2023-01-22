package com.pvr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.entity.Cast;
import com.pvr.entity.Movie;
import com.pvr.exceptions.MovieException;
import com.pvr.services.CastServices;

@RestController
@RequestMapping("/pvr/admin")
public class CastController {
	private CastServices castServices;
	@Autowired
	public CastController (CastServices castServices) {
		this.castServices = castServices;
	}
	@PostMapping(value="/add/cast",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Movie>Add_Cast_For_Movie(@RequestParam Long movieID,@RequestBody @Valid Cast cast) throws MovieException{
		Movie movie=castServices.addCastforMovie(movieID, cast);
		return new ResponseEntity<Movie>(movie,HttpStatus.OK);
	}
}
