package ar.com.rorra.dao;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.exceptions.DBException;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interfaz DAO del Turno
 */
public interface ITurnoDAO extends IBaseDAO<Turno> {
  Turno getByDoctorFecha(int id, LocalDateTime fecha) throws DBException;

  ArrayList<Turno> getAllBetweenDates(Doctor doctor, LocalDateTime desde, LocalDateTime hasta) throws DBException;
}
