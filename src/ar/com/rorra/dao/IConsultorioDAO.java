package ar.com.rorra.dao;

import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.exceptions.DBException;


/**
 * Interfaz DAO de la obra social
 */
public interface IConsultorioDAO extends IBaseDAO<Consultorio> {
  /**
   * Obtiene un Consultorio de la base de datos a partir de su nombre.
   *
   * @param nombre Consultorio del objeto a obtener
   * @return
   * @throws DBException
   */
  Consultorio getByNombre(String nombre) throws DBException;
}

