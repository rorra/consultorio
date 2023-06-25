package ar.com.rorra.entidad;

/**
 * Un médico trabaja en un consultorio determinado
 */
public class Consultorio extends Entidad implements IEntidad {
  /**
   * nombre del consultorio
   */
  private String nombre;

  /**
   * dirección del consultorio
   */
  private String dirección;

  /**
   * Constructor por defecto
   */
  public Consultorio() {}

  /**
   * Constructor con parámetros
   * @param nombre nombre del consultorio
   * @param dirección dirección del consultorio
   */
  public Consultorio(String nombre, String dirección) {
    this.nombre = nombre;
    this.dirección = dirección;
  }

  /**
   * Constructor con parámetros
   * @param id id del consultorio
   * @param nombre nombre del consultorio
   * @param dirección dirección del consultorio
   */
  public Consultorio(int id, String nombre, String dirección) {
    super(id);
    this.nombre = nombre;
    this.dirección = dirección;
  }

  /**
   * Devuelve el nombre del consultorio
   * @return nombre del consultorio
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Setea el nombre del consultorio
   * @param nombre nombre del consultorio
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Devuelve la dirección del consultorio
   * @return dirección del consultorio
   */
  public String getDirección() {
    return dirección;
  }

  /**
   * Setea la dirección del consultorio
   * @param dirección dirección del consultorio
   */
  public void setDirección(String dirección) {
    this.dirección = dirección;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Consultorio)) {
      return false;
    }

    return ((Consultorio)other).getId() == this.getId();
  }

  /**
   * Retorna una representación de la entidad para visualizar
   * @return Representación de la entidad para visualizar
   */
  @Override
  public String toString() {
    return nombre + " - " + dirección;
  }

  /**
   * Retorna el nombre de la entidad
   * @return Nombre de la entidad
   */
  public static String getEntityName() {
    return "Consultorio";
  }

  /**
   * Retorna el nombre de la entidad en plural
   * @return Nombre de la entidad en plural
   */
  public static String getEntityNamePlural() {
    return "Consultorios";
  }
}
