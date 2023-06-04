package ar.com.rorra.dao;

import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.db.DBConnection;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementación del patrón DAO para un Paciente
 */
public class PacienteDAO extends BaseDAO<Paciente> implements IPacienteDAO {
  @Override
  protected String getEntidad() {
    return "pacientes";
  }

  /**
   * DAO referente a la obra social
   */
  IObraSocialDAO obraSocialDAO;

  /**
   * Constructor
   */
  public PacienteDAO() {
    this.obraSocialDAO = new ObraSocialDAO();
  }

  /**
   * Query para guardar un paciente en la base de datos
   *
   * @param paciente Entidad a insertar
   * @return String con el query
   */
  @Override
  protected String saveSQL(Paciente paciente) {
    return "INSERT INTO pacientes (email, password, dni, nombre, telefono, obra_social_id) VALUES ('" +
      paciente.getEmail() + "', '" +
      paciente.getPassword() + "', " +
      paciente.getDni() + ", '" +
      paciente.getNombre() + "', '" +
      paciente.getTelefono() + "', " +
      paciente.getObraSocial().getId() + ")";
  }

  /**
   * Query para actualizar un paciente en la base de datos
   *
   * @param paciente Entidad a actualizar
   * @return String con el query
   */
  @Override
  protected String updateSQL(Paciente paciente) {
    return "UPDATE pacientes set email = '" + paciente.getEmail() +
      "', password = '" + paciente.getPassword() +
      "', dni = " + paciente.getDni() +
      ", nombre = '" + paciente.getNombre() +
      "', telefono = '" + paciente.getTelefono() +
      "', obra_social_id = " + paciente.getObraSocial().getId() +
      " WHERE id = " + paciente.getId();
  }

  /**
   * Obtiene un Paciente de la base de datos a partir de su email.
   *
   * @param dni Dni del paciente a obtener
   * @return Paciente de la base de datos
   * @throws DBException
   */
  @Override
  public Paciente getByDni(long dni) throws DBException {
    return getByField("DNI", String.valueOf(dni));
  }

  /**
   * Transforma un RecordSet en un paciente
   *
   * @param rs RecordSet a transformar
   * @return Paciente
   * @throws DBException
   */
  protected Paciente objectFromRS(ResultSet rs) throws DBException {
    try {
      return new Paciente(
        rs.getInt("id"),
        rs.getString("email"),
        rs.getString("password"),
        rs.getInt("dni"),
        rs.getString("nombre"),
        rs.getString("telefono"),
        obraSocialDAO.get(rs.getInt("obra_social_id"))
      );
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }
  }
}
