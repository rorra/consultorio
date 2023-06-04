package ar.com.rorra.entidad;

public abstract class Entidad implements IEntidad {
  /**
   * Todas las entidades tienen un ID
   */
  private int id;

  /**
   * Constructor por defecto
   */
  public Entidad() { }

  /**
   * Constructor con parámetros
   *
   * @param id
   */
  public Entidad(int id) {
    this.id = id;
  }

  /**
   * Devuelve el ID del usuario
   *
   * @return ID del usuario
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Setea el ID del usuario
   *
   * @param id ID del usuario
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retorna si la entidad es nueva o no
   *
   * @return
   */
  @Override
  public boolean isNew() {
    return this.id == 0;
  }
}
