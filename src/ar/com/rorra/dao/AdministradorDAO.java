package ar.com.rorra.dao;

import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO extends BaseDAO<Administrador> implements IAdministradorDAO {
  @Override
  protected String getEntidad() {
    return "administradores";
  }

  /**
   * Constructor
   */
  public AdministradorDAO() {
  }

  /**
   * Query para guardar un Administrador en la base de datos
   *
   * @param administrador Entidad a insertar
   * @return String con el query
   */
  @Override
  protected String saveSQL(Administrador administrador) {
    return "INSERT INTO administradores (email, password, legajo, nombre, telefono) VALUES ('" +
      administrador.getEmail() + "', '" +
      administrador.getPassword() + "', '" +
      administrador.getLegajo() + "', '" +
      administrador.getNombre() + "', '" +
      administrador.getTelefono() + "')";
  }

  /**
   * Query para actualizar un Administrador en la base de datos
   *
   * @param administrador Entidad a actualizar
   * @return String con el query
   */
  @Override
  protected String updateSQL(Administrador administrador) {
    return "UPDATE administradores set email = '" + administrador.getEmail() +
      "', password = '" + administrador.getPassword() +
      "', legajo = '" + administrador.getLegajo() +
      "', nombre = '" + administrador.getNombre() +
      "', telefono = '" + administrador.getTelefono() +
      "' WHERE id = " + administrador.getId();
  }

  /**
   * Transforma un RecordSet en un Administrador
   *
   * @param rs RecordSet a transformar
   * @return Administrador
   * @throws DBException
   */
  protected Administrador objectFromRS(ResultSet rs) throws DBException {
    try {
      return new Administrador(
        rs.getInt("id"),
        rs.getString("email"),
        rs.getString("password"),
        rs.getInt("legajo"),
        rs.getString("nombre"),
        rs.getString("telefono")
      );
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }
  }
}
