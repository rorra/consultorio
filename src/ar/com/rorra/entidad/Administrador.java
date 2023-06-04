package ar.com.rorra.entidad;

/**
 * Un administrador puede loguearse al sistema
 * Un administrador puede gestionar doctores, pacientes y turnos
 */
public class Administrador extends Empleado implements IEntidad {
  /**
   * Constructor por defecto
   */
  public Administrador() {
    super();
  }

  /**
   * Constructor con parámetros
   * @param email email del administrador
   * @param password password del administrador
   * @param legajo legajo del administrador
   * @param nombre nombre del administrador
   * @param telefono telefono del administrador
   */
  public Administrador(String email, String password, int legajo, String nombre, String telefono) {
    super(email, password, legajo, nombre, telefono);
  }

  /**
   * Constructor con parámetros
   * @param id id del administrador
   * @param email email del administrador
   * @param password password del administrador
   * @param legajo legajo del administrador
   * @param nombre nombre del administrador
   * @param telefono telefono del administrador
   */
  public Administrador(int id, String email, String password, int legajo, String nombre, String telefono) {
    super(id, email, password, legajo, nombre, telefono);
  }
}
