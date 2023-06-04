package ar.com.rorra.exceptions;

/**
 * Excepciones referentes a la base de datos
 */
public class DBException extends RuntimeException {
  /**
   * Tipos de excepciones
   */
  private DBExceptionType type;

  /**
   * Constructor
   *
   * @param type    Tipo de excepción
   * @param message Mensaje de error
   */
  public DBException(DBExceptionType type, String message) {
    super(message);
    this.type = type;
  }

  /**
   * Devuelve el tipo de excepción
   *
   * @return Tipo de excepción
   */
  public DBExceptionType getType() {
    return type;
  }

  /**
   * Setea el tipo de excepción
   *
   * @param type Tipo de excepción
   */
  public void setType(DBExceptionType type) {
    this.type = type;
  }
}

