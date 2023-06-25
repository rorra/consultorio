package ar.com.rorra.entidad;

/**
 * Un usuario puede loguearse al sistema
 */
public abstract class Usuario extends Entidad implements IEntidad {
  /**
   * Email, utilizado para loguearse al sistema
   */
  private String email;

  /**
   * Password, utilizado para loguearse al sistema
   */
  private String password;

  /**
   * Constructor por defecto
   */
  public Usuario() {
  }

  /**
   * Constructor con parámetros
   *
   * @param id       ID del usuario
   * @param email    Email para loguearse al sistema
   * @param password Password para loguearse al sistema
   */
  public Usuario(int id, String email, String password) {
    super(id);
    this.email = email;
    this.password = password;
  }


  /**
   * Constructor con parámetros
   *
   * @param email    Email para loguearse al sistema
   * @param password Password para loguearse al sistema
   */
  public Usuario(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * Devuelve el email del usuario
   *
   * @return Email del usuario
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setea el email del usuario
   *
   * @param email Email del usuario
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Devuelve el password del usuario
   *
   * @return Password del usuario
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setea el password del usuario
   *
   * @param password Password del usuario
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Devuelve el nombre de la entidad
   * @return Nombre de la entidad
   */
  public static String getEntityName() {
    return "Usuario";
  }

  /**
   * Devuelve el nombre de la entidad en plural
   * @return Nombre de la entidad en plural
   */
  public static String getEntityNamePlural() {
    return "Usuarios";
  }
}
