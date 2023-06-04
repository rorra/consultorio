package ar.com.rorra.dao;

import ar.com.rorra.entidad.Turno;
import ar.com.rorra.exceptions.DBException;

import java.time.LocalDateTime;

/**
 * Interfaz DAO del Turno
 */
public interface ITurnoDAO extends IBaseDAO<Turno> {
  Turno getByDoctorFecha(int id, LocalDateTime fecha) throws DBException;
}
