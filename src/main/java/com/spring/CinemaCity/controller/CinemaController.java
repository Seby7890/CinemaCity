package com.spring.CinemaCity.controller;

import com.spring.CinemaCity.DTO.AddCinemaRoomDTO;
import com.spring.CinemaCity.model.CinemaRoom;
import com.spring.CinemaCity.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaRoomService cinemaRoomService;

    @Autowired
    public CinemaController(CinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }

    @PostMapping("/add")
    public CinemaRoom addCinemaRoom(@RequestBody AddCinemaRoomDTO addCinemaRoomDTO) {
        return cinemaRoomService.addCinemaRoom(addCinemaRoomDTO);
    }
}