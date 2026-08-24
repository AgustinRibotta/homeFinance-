package com.homeFinance.homeFinance.exeption;

/**
 * InnerForbiddenOperationException
 */
public class ForbiddenOperationException extends RuntimeException {
  public ForbiddenOperationException(String message) {
    super(message);
  }
}
