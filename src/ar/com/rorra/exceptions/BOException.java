package ar.com.rorra.exceptions;

import java.util.List;

/**
 * Exceptiones de los business objects
 */
public class BOException extends Exception {
  /**
   * Constructor
   */
  public BOException() {
    super();
  }

  /**
   * Constructor con mensaje
   * @param message Mensaje de la excepción
   */
  public BOException(String message) {
    super(message);
  }

  public BOException(List<String> errores) {
    super(String.join("\n", errores));
  }
}