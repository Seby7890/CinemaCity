package com.spring.CinemaCity.service;

import com.spring.CinemaCity.DTO.AddMovieDTO;
import com.spring.CinemaCity.model.*;
import com.spring.CinemaCity.repository.CinemaRoomRepository;
import com.spring.CinemaCity.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CinemaRoomRepository cinemaRoomRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    public Movie addMovie(AddMovieDTO addMovieDTO) {
        Movie movie = new Movie();
        movie.setMovieName(addMovieDTO.getMovieName());
        CinemaRoom foundCinemaRoom = cinemaRoomRepository.findById(addMovieDTO.getCinemaRoomId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The cinema was not found"));
        movie.setCinemaRoom(foundCinemaRoom);
        addMovieDTO.getDates().forEach(projectionsDTO -> {
            Projection projection = new Projection();
            projection.setStartTime(projectionsDTO.getStartTime());
            projection.setEndTime(projectionsDTO.getEndTime());
            projection.setMovie(movie);
            movie.getProjectionList().add(projection);

            for (Seat seat : foundCinemaRoom.getSeatList()) {
                Ticket ticket = new Ticket();
                ticket.setAvailable(true);
                ticket.setProjection(projection);
                projection.getTicketList().add(ticket);
                ticket.setSeat(seat);
            }
        });
        return movieRepository.save(movie);
    }
}