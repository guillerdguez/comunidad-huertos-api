package com.huertos.comunidad_huertos_api.exception;

public class PlotNotFoundException extends RuntimeException {
  public PlotNotFoundException(String message) {
    super(message);
  }
}
