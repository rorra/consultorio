package ar.com.rorra.bo;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.dao.DoctorDAO;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.exceptions.DBException;

import java.util.ArrayList;

public class DoctorBO extends BaseBO<Doctor, DoctorDAO> {
  public DoctorBO() {
    super(new DoctorDAO());
  }

  public void validar(Doctor doctor) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (existeLegajo(doctor.getLegajo())) {
      errores.add("Ya existe un doctor con el legajo " + doctor.getLegajo());
    }
    if (doctor.getNombre().length() == 0) {
      errores.add("Debe ingresar un nombre válido para el doctor");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  private boolean existeLegajo(long legajo) {
    try {
      return dao.getByLegajo(legajo) != null;
    } catch (DBException e) {
      return false;
    }
  }
}
