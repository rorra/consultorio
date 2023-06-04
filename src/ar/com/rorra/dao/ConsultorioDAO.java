package ar.com.rorra.dao;

import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación del patrón DAO para una Consultorio
 */
public class ConsultorioDAO extends BaseDAO<Consultorio> implements IConsultorioDAO {
  @Override
  protected String getEntidad() {
    return "consultorios";
  }

  /**
   * Constructor
   */
  public ConsultorioDAO() {
  }

  /**
   * Query para guardar una Consultorio en la base de datos
   *
   * @param consultorio Entidad a insertar
   * @return String con el query
   */
  @Override
  protected String saveSQL(Consultorio consultorio) {
    return "INSERT INTO consultorios (nombre, direccion) VALUES ('" +
      consultorio.getNombre() + "', '" +
      consultorio.getDirección() + "')";
  }

  /**
   * Query para actualizar una Consultorio en la base de datos
   *
   * @param consultorio Entidad a actualizar
   * @return String con el query
   */
  @Override
  protected String updateSQL(Consultorio consultorio) {
    return "UPDATE consultorios set nombre = '" + consultorio.getNombre() +
      "', direccion = '" + consultorio.getDirección() +
      "' WHERE id = " + consultorio.getId();
  }

  /**
   * Obtiene un Consultorio de la base de datos a partir de su nombre.
   *
   * @param nombre Consultorio del objeto a obtener
   * @return
   * @throws DBException
   */
  public Consultorio getByNombre(String nombre) throws DBException {
    return getByField("nombre", nombre);
  }


  /**
   * Transforma un RecordSet en un Consultorio
   *
   * @param rs
   * @return
   * @throws DBException
   */
  protected Consultorio objectFromRS(ResultSet rs) throws DBException {
    try {
      return new Consultorio(
        rs.getInt("id"),
        rs.getString("nombre"),
        rs.getString("direccion")
      );
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }
  }
}
