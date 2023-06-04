package ar.com.rorra.dao;

import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.exceptions.DBException;


/**
 * Interfaz DAO del Paciente
 */
public interface IPacienteDAO extends IBaseDAO<Paciente> {
  /**
   * Obtiene un paciente a partir de su dni.
   *
   * @param dni Dni del paciente a obtener
   * @return Paciente
   * @throws DBException
   */
  Paciente getByDni(long dni) throws DBException;
}

