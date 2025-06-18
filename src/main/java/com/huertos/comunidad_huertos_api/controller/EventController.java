package com.huertos.comunidad_huertos_api.controller;

import java.awt.Event;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huertos.comunidad_huertos_api.services.EventService;

@RestController
@RequestMapping(path = "/event")
public class EventController {
	private final EventService service;
	private static final Logger logger = Logger.getLogger(EventController.class.getName());

//	public EventController(EventService service) {
//		super();
//		this.service = service;
//	};

	@PostMapping("/event")
	public ResponseEntity<List<Event>> listar(
			
			
			
			
			
			
			)
	

}
