package com.pvr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.entity.Movie;
import com.pvr.exceptions.MovieException;
import com.pvr.services.MovieServices;

@RestController
@RequestMapping("pvr")
public class MovieController {
	private MovieServices movieServices;
	@Autowired
	public MovieController(MovieServices movieServices) {
		this.movieServices=movieServices;
	}
	//Content Negotiation
	@PostMapping(value="/admin/create",consumes= {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Movie>Add_Movie_To_Database(@RequestBody Movie movie) throws MovieException{
		Movie savedMovie = movieServices.createMovie(movie);
		return new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
	}
	@GetMapping("/all/movies")
	public ResponseEntity<Page<Movie>>Get_All_Movies_With_Pagination(@RequestParam Integer pageNo) throws MovieException{
		Page<Movie>allMovies=movieServices.getMovieByPagination(pageNo);
		return new ResponseEntity<Page<Movie>>(allMovies,HttpStatus.OK);
	}
	@GetMapping("/all/movies/today")
	public ResponseEntity<List<Movie>>Todays_Movies_List(@RequestParam Integer limit,@RequestParam Integer offset) throws MovieException{
		List<Movie>todaysMovies=movieServices.TodaysMovies(limit, offset);
		return new ResponseEntity<List<Movie>>(todaysMovies,HttpStatus.OK);
	}
	@GetMapping("/all/movies/upcoming")
	public ResponseEntity<List<Movie>>Upcomming_Movies_List(@RequestParam Integer limit,@RequestParam Integer offset) throws MovieException{
		List<Movie>upcommingMovies=movieServices.UpcommingMovies(limit, offset);
		return new ResponseEntity<List<Movie>>(upcommingMovies,HttpStatus.OK);
	}
	@GetMapping("/all/movies/running")
	public ResponseEntity<List<Movie>>Running_Movies_List(@RequestParam Integer limit,@RequestParam Integer offset) throws MovieException{
		List<Movie>runningMovies=movieServices.RunningMovies(limit, offset);
		return new ResponseEntity<List<Movie>>(runningMovies,HttpStatus.OK);
	}
}
