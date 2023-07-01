package ar.com.rorra.entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import ar.com.rorra.util.Dates;

public class Turno extends Entidad implements IEntidad {
  public static final String FORMATO_FECHA = "yyyy/MM/dd HH:mm";

  /**
   * Doctor que atiende el turno
   */
  private Doctor doctor;

  /**
   * Paciente que tiene el turno
   */
  private Paciente paciente;

  /**
   * Fecha y hora del turno
   */
  private LocalDateTime fecha;

  /**
   * Constructor por defecto
   */
  public Turno() {
  }

  /**
   * Constructor con todos los atributos menos el ID
   *
   * @param doctor   Doctor que atiende el turno
   * @param paciente Paciente que tiene el turno
   * @param fecha    Fecha y hora del turno
   */
  public Turno(Doctor doctor, Paciente paciente, LocalDateTime fecha) {
    this.doctor = doctor;
    this.paciente = paciente;
    this.fecha = fecha;
  }

  /**
   * Constructor con todos los atributos
   *
   * @param id       ID del turno
   * @param doctor   Doctor que atiende el turno
   * @param paciente Paciente que tiene el turno
   * @param fecha    Fecha y hora del turno
   */
  public Turno(int id, Doctor doctor, Paciente paciente, LocalDateTime fecha) {
    super(id);
    this.doctor = doctor;
    this.paciente = paciente;
    this.fecha = fecha;
  }

  /**
   * Devuelve el doctor que atiende el turno
   *
   * @return Doctor que atiende el turno
   */
  public Doctor getDoctor() {
    return doctor;
  }

  /**
   * Setea el doctor que atiende el turno
   *
   * @param doctor Doctor que atiende el turno
   */
  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  /**
   * Devuelve el paciente que tiene el turno
   *
   * @return Paciente que tiene el turno
   */
  public Paciente getPaciente() {
    return paciente;
  }

  /**
   * Setea el paciente que tiene el turno
   *
   * @param paciente Paciente que tiene el turno
   */
  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  /**
   * Devuelve el consultorio donde se atiende el turno
   *
   * @return Consultorio donde se atiende el turno
   */
  public Consultorio getConsultorio() {
    if (doctor == null) { return null; }
    return doctor.getConsultorio();
  }

  /**
   * Devuelve la fecha y hora del turno
   *
   * @return Fecha y hora del turno
   */
  public LocalDateTime getFecha() {
    return fecha;
  }

  /**
   * Setea la fecha y hora del turno
   *
   * @param fecha Fecha y hora del turno
   */
  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  @Override
  public String toString() {
    return Dates.format(fecha) + " - " + doctor.getNombre() + " - " + paciente.getNombre() + " (" + paciente.getDni() + ")";
  }

  /**
   * Devuelve el nombre de la entidad
   * @return Nombre de la entidad
   */
  public static String getEntityName() {
    return "Turno";
  }

  /**
   * Devuelve el nombre de la entidad en plural
   * @return Nombre de la entidad en plural
   */
  public static String getEntityNamePlural() {
    return "Turnos";
  }

  /**
   * Devuelve el costo del turno
   * @return Costo del turno
   */
  public BigDecimal getCosto() {
    return new BigDecimal(100);
  }
}
