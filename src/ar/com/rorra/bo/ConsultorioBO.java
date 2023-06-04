package ar.com.rorra.bo;

import ar.com.rorra.dao.IConsultorioDAO;
import ar.com.rorra.dao.ConsultorioDAO;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.validations.StringValidations;

import java.util.ArrayList;

public class ConsultorioBO extends BaseBO<Consultorio, ConsultorioDAO> {
  public ConsultorioBO() {
    super(new ConsultorioDAO());
  }

  @Override
  public void validar(Consultorio entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (StringValidations.isEmpty(entidad.getNombre())) {
      errores.add("El nombre no puede estar vacío");
    } else if (existeNombre(entidad)) {
      errores.add("Ya existe un consultorio con ese nombre");
    }

    if (StringValidations.isEmpty(entidad.getDirección())) {
      errores.add("La dirección no puede estar vacía");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  private boolean existeNombre(Consultorio entidad) {
    try {
      Consultorio otro = dao.getByNombre(entidad.getNombre());
      if (otro != null && otro.getId() != entidad.getId()) {
        return true;
      }
    } catch (DBException e) {
    }
    return false;
  }
}