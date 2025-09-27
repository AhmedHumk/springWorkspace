package com.addo.basicserver.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addo.basicserver.house.Rooms;

@RestController
public class RoomController {
	// http://localhost:8080/room
	@GetMapping("/room")
	public Rooms GetRoom() {
		return new Rooms("Room", 1);
	}
}
