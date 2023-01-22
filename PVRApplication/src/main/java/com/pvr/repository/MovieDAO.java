package com.pvr.repository;
import java.util.List;
import java.util.Optional;

//R3
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pvr.entity.Movie;

public interface MovieDAO extends PagingAndSortingRepository<Movie, Long>{
	Page<Movie>findAll();
	@Query(value="SELECT * FROM MOVIE WHERE release_date<=CURDATE() ORDER BY release_date DESC LIMIT ?1 OFFSET ?2",nativeQuery = true)
	public List<Movie>runningMovies(Integer limit,Integer offset);
	@Query(value="SELECT * FROM MOVIE WHERE release_date>CURDATE() LIMIT ?1 OFFSET ?2",nativeQuery = true)
	public List<Movie>upcommingMovies(Integer limit,Integer offset);
	@Query(value="SELECT * FROM MOVIE WHERE release_date =  CURDATE() LIMIT ?1 OFFSET ?2",nativeQuery = true)
	public List<Movie>todaysMovies(Integer limit,Integer offset);
	Optional<Movie> findByName(String name);
}
