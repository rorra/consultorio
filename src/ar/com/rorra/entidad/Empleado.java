package ar.com.rorra.entidad;

/**
 * Empleados de la clinica
 * Un empleado puede loguearse al sistema
 * Un empleado tiene un legajo, nombre y telefono
 */
public abstract class Empleado extends Usuario implements IEntidad {
  /**
   * Todos los empleados tienen un legajo único en el sistema
   */
  private long legajo;

  /**
   * Nombre del doctor
   */
  private String nombre;

  /**
   * Teléfono del doctor
   */
  private String telefono;

  /**
   * Constructor por defecto
   */
  public Empleado() {
  }

  /**
   * Constructor con parámetros
   *
   * @param email    email del empleado
   * @param password password del empleado
   * @param legajo   legajo del empleado
   * @param nombre   nombre del empleado
   * @param telefono telefono del empleado
   */
  public Empleado(String email, String password, long legajo, String nombre, String telefono) {
    super(email, password);
    this.legajo = legajo;
    this.nombre = nombre;
    this.telefono = telefono;
  }

  /**
   * Constructor con parámetros
   *
   * @param id       id del empleado
   * @param email    email del empleado
   * @param password password del empleado
   * @param legajo   legajo del empleado
   * @param nombre   nombre del empleado
   * @param telefono telefono del empleado
   */
  public Empleado(int id, String email, String password, long legajo, String nombre, String telefono) {
    super(id, email, password);
    this.legajo = legajo;
    this.nombre = nombre;
    this.telefono = telefono;
  }

  /**
   * Devuelve el legajo del empleado
   *
   * @return
   */
  public long getLegajo() {

    return legajo;
  }

  /**
   * Setea el legajo del empleado
   *
   * @param legajo legajo del empleado
   */
  public void setLegajo(long legajo) {

    this.legajo = legajo;
  }

  /**
   * Devuelve el nombre del empleado
   *
   * @return nombre del empleado
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Setea el nombre del empleado
   *
   * @param nombre nombre del empleado
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Devuelve el telefono del empleado
   *
   * @return telefono del empleado
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Setea el telefono del empleado
   *
   * @param telefono telefono del empleado
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Override
  public String toString() {
    return legajo + " - " + nombre;
  }
}
