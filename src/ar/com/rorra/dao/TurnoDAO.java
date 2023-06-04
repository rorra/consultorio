package ar.com.rorra.dao;

import ar.com.rorra.entidad.Turno;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.exceptions.DBExceptionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Implementación del patrón DAO para un Turno
 */
public class TurnoDAO extends BaseDAO<Turno> implements ITurnoDAO {
  @Override
  protected String getEntidad() {
    return "turnos";
  }

  /**
   * DAO referente al paciente
   */
  IPacienteDAO pacienteDAO;

  /**
   * DAO referente al doctor
   */
  IDoctorDAO doctorDAO;

  /**
   * Constructor
   */
  public TurnoDAO() {
    this.pacienteDAO = new PacienteDAO();
    this.doctorDAO = new DoctorDAO();
  }

  /**
   * Query para guardar un turno en la base de datos
   *
   * @param turno Entidad a insertar
   * @return String con el query
   */
  @Override
  protected String saveSQL(Turno turno) {
    return "INSERT INTO turnos (doctor_id, paciente_id, fecha) VALUES (" +
      turno.getDoctor().getId() + ", " +
      turno.getPaciente().getId() + ", '" +
      turno.getFecha() + "')";
  }

  /**
   * Query para actualizar un turno en la base de datos
   *
   * @param turno Entidad a actualizar
   * @return String con el query
   */
  @Override
  protected String updateSQL(Turno turno) {
    return "UPDATE turnos set doctor_id = " + turno.getDoctor().getId() +
      ", paciente_id = " + turno.getPaciente().getId() +
      ", fecha = '" + turno.getFecha() +
      "' WHERE id = " + turno.getId();
  }

  /**
   * Transforma un RecordSet en un turno
   *
   * @param rs RecordSet a transformar
   * @return Turno
   * @throws DBException
   */
  protected Turno objectFromRS(ResultSet rs) throws DBException {
    try {
      return new Turno(
        rs.getInt("id"),
        doctorDAO.get(rs.getInt("doctor_id")),
        pacienteDAO.get(rs.getInt("paciente_id")),
        rs.getTimestamp("fecha").toLocalDateTime()
      );
    } catch (SQLException ex) {
      throw new DBException(DBExceptionType.QUERY_ERROR, ex.getMessage());
    }
  }

  public Turno getByDoctorFecha(int doctorId, LocalDateTime fecha) throws DBException {
    Map params = Map.of("doctor_id", Integer.toString(doctorId), "fecha", fecha.toString());
    return getByField(params);
  }
}
