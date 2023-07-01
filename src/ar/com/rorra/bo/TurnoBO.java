package ar.com.rorra.bo;

import ar.com.rorra.dao.TurnoDAO;
import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.exceptions.BOException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TurnoBO extends BaseBO<Turno, TurnoDAO> {
  public TurnoBO() {
    super(new TurnoDAO());
  }
  public void validar(Turno entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (entidad.getDoctor() == null) {
      errores.add("El turno debe tener un doctor");
    }

    if (entidad.getPaciente() == null) {
      errores.add("El turno debe tener un paciente");
    }

    if (entidad.getDoctor() != null && entidad.getConsultorio() != null) {
      Turno otro = dao.getByDoctorFecha(entidad.getDoctor().getId(), entidad.getFecha());
      if (otro != null && otro.getId() != entidad.getId()) {
        errores.add("El doctor ya tiene un turno asignado para la fecha " + entidad.getFecha());
      }
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  /**
   * Obtiene la lista de turnos para un doctor entre dos fechas
   * @param doctor Doctor para filtrar los turnos
   * @param desde Fecha desde
   * @param hasta Fecha hasta
   * @return Lista de turnos
   */
  public ArrayList<Turno> getAllBetweenDates(Doctor doctor, LocalDateTime desde, LocalDateTime hasta) {
    return dao.getAllBetweenDates(doctor, desde, hasta);
  }
}
