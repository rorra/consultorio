package ar.com.rorra.dao;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.db.DBConnection;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del patrón DAO para un Doctor
 */
public class DoctorDAO extends BaseDAO<Doctor> implements IDoctorDAO {
  @Override
  protected String getEntidad() {
    return "doctores";
  }

  /**
   * DAO referente al consultorio
   */
  IConsultorioDAO consultorioDAO;

  /**
   * Constructor
   */
  public DoctorDAO() {
    this.consultorioDAO = new ConsultorioDAO();
  }

  /**
   * Query para guardar un paciente en la base de datos
   *
   * @param doctor Entidad a insertar
   * @return String con el query
   */
  @Override
  protected String saveSQL(Doctor doctor) {
    return "INSERT INTO doctores (email, password, legajo, nombre, telefono, consultorio_id, tarifa) VALUES ('" +
      doctor.getEmail() + "', '" +
      doctor.getPassword() + "', " +
      doctor.getLegajo() + ", '" +
      doctor.getNombre() + "', '" +
      doctor.getTelefono() + "', " +
      doctor.getConsultorio().getId() + ", " +
      doctor.getTarifa() +")";
  }

  /**
   * Query para actualizar un paciente en la base de datos
   *
   * @param doctor Entidad a actualizar
   * @return String con el query
   */
  @Override
  protected String updateSQL(Doctor doctor) {
    return "UPDATE doctores set email = '" + doctor.getEmail() +
      "', password = '" + doctor.getPassword() +
      "', legajo = " + doctor.getLegajo() +
      ", nombre = '" + doctor.getNombre() +
      "', telefono = '" + doctor.getTelefono() +
      "', consultorio_id = " + doctor.getConsultorio().getId() +
      ", tarifa = " + doctor.getTarifa() +
      " WHERE id = " + doctor.getId();
  }

  /**
   * Obtiene un Doctor de la base de datos a partir de su legajo.
   *
   * @param legajo Legajo del objeto a obtener
   * @return
   * @throws DBException
   */
  public Doctor getByLegajo(int legajo) throws DBException {
    return getByField("legajo", String.valueOf(legajo));
  }

  /**
   * Transforma un RecordSet en un doctor
   *
   * @param rs RecordSet a transformar
   * @return Doctor
   * @throws DBException
   */
  protected Doctor objectFromRS(ResultSet rs) throws DBException {
    try {
      return new Doctor(
        rs.getInt("id"),
        rs.getString("email"),
        rs.getString("password"),
        rs.getInt("legajo"),
        rs.getString("nombre"),
        rs.getString("telefono"),
        this.consultorioDAO.get(rs.getInt("consultorio_id")),
        rs.getBigDecimal("tarifa")
      );
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }
  }
}
