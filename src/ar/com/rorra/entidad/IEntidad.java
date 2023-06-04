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
}
