package ar.com.rorra.dao;

import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.exceptions.DBException;

public interface IAdministradorDAO extends IBaseDAO<Administrador> {
  /**
   * Obtiene un administrador a partir de su legajo.
   *
   * @param legajo Legajo del administrador a obtener
   * @return Doctor
   * @throws DBException
   */
  Administrador getByLegajo(int legajo) throws DBException;
}
