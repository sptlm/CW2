package org.example;

public class InvalidDateException extends RuntimeException {
  public InvalidDateException(String message) {
    super(message);
  }
}
