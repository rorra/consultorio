package ar.com.rorra.entidad;

/**
 * Un paciente puede tener una obra social, y obtener un descuento por la misma.
 * En caso de descuento, es la obra social quién paga el resto de la consulta.
 * El descuento es un porcentaje del total de la consulta, de 0 a 100.
 */
public class ObraSocial extends Entidad implements IEntidad {
  /**
   * nombre de la obra social
   */
  private String nombre;

  /**
   * descuento que se aplica a la obra social
   */
  private int descuento;

  /**
   * Constructor por defecto
   */
  public ObraSocial() {
  }

  /**
   * Constructor con parámetros
   *
   * @param nombre    nombre de la obra social
   * @param descuento descuento que se aplica a la obra social
   */
  public ObraSocial(String nombre, int descuento) {
    this.nombre = nombre;
    this.descuento = descuento;
  }

  /**
   * Constructor con parámetros
   *
   * @param id        id de la obra social
   * @param nombre    nombre de la obra social
   * @param descuento descuento que se aplica a la obra social
   */
  public ObraSocial(int id, String nombre, int descuento) {
    super(id);
    this.nombre = nombre;
    this.descuento = descuento;
  }

  /**
   * Devuelve el nombre de la obra social
   *
   * @return nombre de la obra social
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Setea el nombre de la obra social
   *
   * @param nombre nombre de la obra social
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Devuelve el descuento que se aplica a la obra social
   *
   * @return descuento que se aplica a la obra social
   */
  public int getDescuento() {
    return descuento;
  }

  /**
   * Setea el descuento que se aplica a la obra social
   *
   * @param descuento descuento que se aplica a la obra social
   */
  public void setDescuento(int descuento) {
    this.descuento = descuento;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof ObraSocial)) {
      return false;
    }

    return ((ObraSocial)other).getId() == this.getId();
  }

  @Override
  public String toString() {
    return this.nombre;
  }

  /**
   * Devuelve el nombre de la entidad
   * @return nombre de la entidad
   */
  public static String getEntityName() {
    return "Obra Social";
  }

  /**
   * Devuelve el nombre de la entidad en plural
   * @return nombre de la entidad en plural
   */
  public static String getEntityNamePlural() {
    return "Obras Sociales";
  }
}
