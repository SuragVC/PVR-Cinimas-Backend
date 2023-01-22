package com.pvr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pvr.entity.Cast;
import com.pvr.entity.Movie;
import com.pvr.exceptions.MovieException;
import com.pvr.services.MovieServices;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PvrApplicationTests {
	@Mock
	public MovieServices service;
	Cast cast=new Cast(1L,"Sam Worthington","https://www.tvguide.com/a/img/resize/2b06ee00b917b055533824a8c9f87cfe676d843f/catalog/provider/10/9/10-A77CFF12-34D0-43CF-90B8-67885093C1A6.png?auto=webp&fit=crop&height=300&width=200","Zoe Saldaña","http://t0.gstatic.com/licensed-image?q=tbn:ANd9GcRpvvoYY4EnR1LS-yCAvOtrl6WKM-q44tz6E-gRm2RIsEIWkBzJdawalQLP_cIL_mi53nXa1sw091fjntI");
	Movie testMovie_1=new Movie(1L,"Avatar 2",LocalDate.of(2021, 12, 16),"ENGLISH","ACTION","Avatar the way of water.",cast);
	
	@Test
	public void CreateMovie() throws MovieException {
		when(service.createMovie(testMovie_1)).thenReturn(testMovie_1);
		Movie response = service.createMovie(testMovie_1);
		assertEquals(response,testMovie_1);
	}
}
