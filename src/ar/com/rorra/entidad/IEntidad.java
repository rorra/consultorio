package ar.com.rorra.entidad;

/**
 * Interfaz para las entidades
 */
public interface IEntidad {
  /**
   * Retorna el id de la entidad
   *
   * @return Id de la entidad
   */
  public int getId();

  /**
   * Setea el id de la entidad
   *
   * @param id Id de la entidad
   */
  public void setId(int id);

  /**
   * Retorna si la entidad es nueva o no
   *
   * @return
   */
  public boolean isNew();

  /**
   * Retorna el nombre de la entidad
   *
   * @return Nombre de la entidad
   */
  public static String getEntityName() {
    return "Entidad";
  }

  /**
   * Retorna el nombre de la entidad en plural
   *
   * @return Nombre de la entidad en plural
   */
  public static String getEntityNamePlural() {
    return "Entidades";
  }
}
