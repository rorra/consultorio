package ar.com.rorra.bo;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.dao.DoctorDAO;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.validations.FloatValidations;
import ar.com.rorra.validations.StringValidations;

import java.util.ArrayList;

public class DoctorBO extends BaseBO<Doctor, DoctorDAO> {
  public DoctorBO() {
    super(new DoctorDAO());
  }

  public void validar(Doctor entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (StringValidations.isEmpty(entidad.getEmail())) {
      errores.add("El email no puede estar vacío");
    } else if (!StringValidations.validEmail(entidad.getEmail())) {
      errores.add("El email es inválido");
    }

    if (StringValidations.isEmpty(entidad.getPassword())) {
      errores.add("El password no puede estar vacío");
    }

    if (existeLegajo(entidad)) {
      errores.add("Ya existe un doctor con el legajo " + entidad.getLegajo());
    }

    if (StringValidations.isEmpty(entidad.getNombre())) {
      errores.add("Debe ingresar un nombre válido para el doctor");
    }

    if (StringValidations.isEmpty(entidad.getTelefono())) {
      errores.add("Debe ingresar un teléfono para el doctor");
    }

    if (!FloatValidations.isMinFloat(entidad.getTarifa().floatValue(), 9)) {
      errores.add("Debe ingresar una tarifa válida para el doctor, superior a 9.00");
    }

    if (entidad.getConsultorio() == null) {
      errores.add("Debe ingresar un consultorio para el doctor");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  private boolean existeLegajo(Doctor entidad) {
    try {
      Doctor otro = dao.getByLegajo(entidad.getLegajo());
      if (otro != null && otro.getId() != entidad.getId()) {
        return true;
      }
    } catch (DBException e) {
    }
    return false;
  }
}
