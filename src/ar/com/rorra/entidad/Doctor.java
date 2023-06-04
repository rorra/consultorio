package ar.com.rorra.entidad;

import java.math.BigDecimal;

/**
 * Un doctor puede loguearse al sistema
 * Un doctor puede ver los turnos de los pacientes
 */
public class Doctor extends Empleado implements IEntidad {
  /**
   * Consultorio asignado al doctor
   */
  private Consultorio consultorio;

  /**
   * Tarifa por turno del doctor
   */
  private BigDecimal tarifa;

  /**
   * Constructor por defecto
   */
  public Doctor() {
    super();
  }

  public Doctor(String email, String password, int legajo, String nombre, String telefono, Consultorio consultorio, BigDecimal tarifa) {
    super(email, password, legajo, nombre, telefono);
    this.consultorio = consultorio;
    this.tarifa = tarifa;
  }

  /**
   * Constructor con parámetros
   *
   * @param id       id del doctor
   * @param email    email del doctor
   * @param password password del doctor
   * @param legajo   legajo del doctor
   * @param nombre   nombre del doctor
   * @param telefono telefono del doctor
   */
  public Doctor(int id, String email, String password, int legajo, String nombre, String telefono, Consultorio consultorio, BigDecimal tarifa) {
    super(id, email, password, legajo, nombre, telefono);
    this.consultorio = consultorio;
    this.tarifa = tarifa;
  }

  /**
   * Devuelve el consultorio asignado al doctor
   *
   * @return consultorio asignado al doctor
   */
  public Consultorio getConsultorio() {
    return consultorio;
  }

  /**
   * Setea el consultorio asignado al doctor
   *
   * @param consultorio consultorio asignado al doctor
   */
  public void setConsultorio(Consultorio consultorio) {
    this.consultorio = consultorio;
  }

  /**
   * Devuelve la tarifa por turno del doctor
   * @return torifa por hora
   */
  public BigDecimal getTarifa() {
    return tarifa;
  }

  /**
   * Stea la tarifa por hora del doctor
   * @param tarifa tarifa por hora
   */
  public void setTarifa(BigDecimal tarifa) {
    this.tarifa = tarifa;
  }

  /**
   * Devuelve una tarifa por defecto
   * @return Tarifa por defecto
   */
  public static BigDecimal getTarifaPorDefecto() {
    return new BigDecimal(100.0);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Doctor)) {
      return false;
    }

    return ((Doctor)other).getId() == this.getId();
  }
}
