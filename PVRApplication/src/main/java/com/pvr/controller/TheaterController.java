package com.pvr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvr.entity.Theater;
import com.pvr.exceptions.TheaterException;
import com.pvr.services.TheaterService;

@RestController
@RequestMapping("/pvr")
public class TheaterController {
	private TheaterService theaterService;
	@Autowired
	public TheaterController (TheaterService theaterService) {
		this.theaterService = theaterService;
	}
	@PostMapping("/admin/add/theater")
	public ResponseEntity<Theater>Add_Theater_To_Database(@RequestBody @Valid Theater theater) throws TheaterException{
		Theater savedTheater=theaterService.addNewTheater(theater);
		return new ResponseEntity<Theater>(savedTheater,HttpStatus.CREATED);
	}
	@GetMapping("/admin/theater/movie/update")
	public ResponseEntity<Theater>Uodate_Running_Movie_From_Theater(@RequestParam String movieName,@RequestParam String theaterName) throws TheaterException{
		Theater savedTheater=theaterService.updateRunningMovie(movieName, theaterName);
		return new ResponseEntity<Theater>(savedTheater,HttpStatus.OK);
	}
	@DeleteMapping("/admin/theater/delete")
	public ResponseEntity<Theater>Delete_Theater_From_Database(@RequestParam String theaterName) throws TheaterException{
		Theater deletedTheater=theaterService.deleteTheater(theaterName);
		return new ResponseEntity<Theater>(deletedTheater,HttpStatus.OK);
	}
	@GetMapping("/theater/all/movie/")
	public ResponseEntity<List<Theater>>Get_Theater_By_MovieName(@RequestParam String movieName) throws TheaterException{
		List<Theater> theaterList=theaterService.findTheaterByMovie(movieName);
		return new ResponseEntity<List<Theater>>(theaterList,HttpStatus.OK);
	}
	@GetMapping("/theater/all/")
	public ResponseEntity<List<Theater>>Get_All_Theaters() throws TheaterException{
		List<Theater> theaterList=theaterService.findAllTheater();
		return new ResponseEntity<List<Theater>>(theaterList,HttpStatus.OK);
	}
	@GetMapping("/theater/all/place")
	public ResponseEntity<List<Theater>>Get_All_Theaters_Place(@RequestParam String placeName) throws TheaterException{
		List<Theater> theaterList=theaterService.findTheaterByPlace(placeName);
		return new ResponseEntity<List<Theater>>(theaterList,HttpStatus.OK);
	}
}
