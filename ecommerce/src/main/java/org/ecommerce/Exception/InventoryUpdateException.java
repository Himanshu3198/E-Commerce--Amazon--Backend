package org.ecommerce.Exception;

public class InventoryUpdateException extends RuntimeException {
  public InventoryUpdateException(String message, Throwable cause) {
    super(message, cause);
  }

  public InventoryUpdateException(String message) {
    super(message);
  }
}