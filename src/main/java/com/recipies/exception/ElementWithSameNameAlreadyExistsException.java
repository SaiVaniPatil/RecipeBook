package com.recipies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementWithSameNameAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 7246983447306271525L;

  public ElementWithSameNameAlreadyExistsException(String message) {
    super(message);
  }
}