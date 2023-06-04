package ar.com.rorra.dao;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.exceptions.DBException;

/**
 * Interfaz DAO del Doctor
 */
public interface IDoctorDAO extends IBaseDAO<Doctor> {
  /**
   * Obtiene un doctor a partir de su legajo.
   *
   * @param legajo Legajo del doctor a obtener
   * @return Doctor
   * @throws DBException
   */
  Doctor getByLegajo(long legajo) throws DBException;
}
