package com.pvr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pvr.entity.Theater;

public interface TheaterDAO extends JpaRepository<Theater, Long>{
	Optional<Theater> findByName(String name);
	List<Theater> findByRunningMovie(String runningMovie);
	@Query(value="SELECT * FROM theater WHERE theaterid IN (SELECT theaterid FROM address WHERE city=?1 OR district=?1 OR state=?1)",nativeQuery = true)
	public List<Theater>findTheaterByPlace(String placeName);
}
