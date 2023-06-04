package ar.com.rorra.bo;

import ar.com.rorra.dao.TurnoDAO;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.exceptions.BOException;

import java.util.ArrayList;

public class TurnoBO extends BaseBO<Turno, TurnoDAO> {
  public TurnoBO() {
    super(new TurnoDAO());
  }
  public void validar(Turno turno) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (dao.getByDoctorFecha(turno.getDoctor().getId(), turno.getFecha()) != null) {
      errores.add("El doctor ya tiene un turno asignado para la fecha " + turno.getFecha());
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }
}
