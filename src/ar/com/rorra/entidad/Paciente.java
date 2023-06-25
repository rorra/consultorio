package ar.com.rorra.entidad;

public class Paciente extends Usuario implements IEntidad {
  /**
   * DNI del paciente
   */
  private int dni;

  /**
   * Nombre del paciente
   */
  private String nombre;

  /**
   * Teléfono del paciente
   */
  private String telefono;

  /**
   * Obra social del paciente
   */
  private ObraSocial obraSocial;

  /**
   * Constructor por defecto
   */
  public Paciente() {
  }

  /**
   * Constructor con parámetros
   *
   * @param email      email del paciente
   * @param password   password del paciente
   * @param dni        dni del paciente
   * @param nombre     nombre del paciente
   * @param telefono   teléfono del paciente
   * @param obraSocial obra social del paciente
   */
  public Paciente(String email, String password, int dni, String nombre, String telefono, ObraSocial obraSocial) {
    super(email, password);
    this.dni = dni;
    this.nombre = nombre;
    this.telefono = telefono;
    this.obraSocial = obraSocial;
  }

  /**
   * Constructor con parámetros
   *
   * @param id         id del paciente
   * @param email      email del paciente
   * @param password   password del paciente
   * @param dni        dni del paciente
   * @param nombre     nombre del paciente
   * @param telefono   teléfono del paciente
   * @param obraSocial obra social del paciente
   */
  public Paciente(int id, String email, String password, int dni, String nombre, String telefono, ObraSocial obraSocial) {
    super(id, email, password);
    this.dni = dni;
    this.nombre = nombre;
    this.telefono = telefono;
    this.obraSocial = obraSocial;
  }

  /**
   * Obtiene el DNI del paciente
   *
   * @return DNI del paciente
   */
  public int getDni() {
    return dni;
  }

  /**
   * Establece el DNI del paciente
   *
   * @param dni DNI del paciente
   */
  public void setDni(int dni) {
    this.dni = dni;
  }

  /**
   * Obtiene el nombre del paciente
   *
   * @return nombre del paciente
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Establece el nombre del paciente
   *
   * @param nombre nombre del paciente
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Obtiene el teléfono social del paciente
   *
   * @return teléfono del paciente
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Establece el teléfono del paciente
   *
   * @param telefono teléfono del paciente
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Obtiene la obra social del paciente
   *
   * @return obra social del paciente
   */
  public ObraSocial getObraSocial() {
    return obraSocial;
  }

  /**
   * Establece la obra social del paciente
   *
   * @param obraSocial obra social del paciente
   */
  public void setObraSocial(ObraSocial obraSocial) {
    this.obraSocial = obraSocial;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Paciente)) {
      return false;
    }

    return ((Paciente)other).getId() == this.getId();
  }

  @Override
  public String toString() {
    return dni + " - " + nombre;
  }

  /**
   * Obtiene el nombre de la entidad
   * @return nombre de la entidad
   */
  public static String getEntityName() {
    return "Paciente";
  }

  /**
   * Obtiene el nombre de la entidad en plural
   * @return nombre de la entidad en plural
   */
  public static String getEntityNamePlural() {
    return "Pacientes";
  }
}
