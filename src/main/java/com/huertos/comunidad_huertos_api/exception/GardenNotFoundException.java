package com.huertos.comunidad_huertos_api.exception;

public class GardenNotFoundException extends RuntimeException {
	public GardenNotFoundException(String message) {
		super(message);
	}
}