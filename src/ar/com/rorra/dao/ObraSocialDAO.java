package ar.com.rorra.dao;

import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementación del patrón DAO para una Obra Social
 */
public class ObraSocialDAO extends BaseDAO<ObraSocial> implements IObraSocialDAO {
  @Override
  protected String getEntidad() {
    return "obras_sociales";
  }

  /**
   * Constructor
   */
  public ObraSocialDAO() {
  }

  /**
   * Query para guardar una ObraSocial en la base de datos
   *
   * @param obraSocial Entidad a insertar
   * @return String con el query
   */
  @Override
  protected String saveSQL(ObraSocial obraSocial) {
    return "INSERT INTO obrasSociales (nombre, descuento) VALUES ('" +
      obraSocial.getNombre() + "', " +
      obraSocial.getDescuento() + ")";
  }

  /**
   * Query para actualizar una ObraSocial en la base de datos
   *
   * @param obraSocial Entidad a actualizar
   * @return String con el query
   */
  @Override
  protected String updateSQL(ObraSocial obraSocial) {
    return "UPDATE obrasSociales set nombre = '" + obraSocial.getNombre() +
      "', descuento = " + obraSocial.getDescuento() +
      " WHERE id = " + obraSocial.getId();
  }

  /**
   * Transforma un RecordSet en una ObraSocial
   *
   * @param rs RecordSet a transformar
   * @return ObraSocial
   * @throws DBException
   */
  protected ObraSocial objectFromRS(ResultSet rs) throws DBException {
    try {
      return new ObraSocial(
        rs.getInt("id"),
        rs.getString("nombre"),
        rs.getInt("descuento")
      );
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }
  }

  /**
   * Retorna la obra social particular
   *
   * @return ObraSocial particular
   * @throws DBException
   */
  public ObraSocial getParticular() throws DBException {
    return getByField("nombre", "Particular");
  }
}
