package ar.com.rorra.bo;

import ar.com.rorra.dao.IConsultorioDAO;
import ar.com.rorra.dao.ConsultorioDAO;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.exceptions.BOException;

import java.util.ArrayList;

public class ConsultorioBO extends BaseBO<Consultorio, ConsultorioDAO> {
  public ConsultorioBO() {
    super(new ConsultorioDAO());
  }

  @Override
  public void validar(Consultorio entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (entidad.getNombre().isEmpty()) {
      errores.add("El nombre no puede estar vacío");
    }

    if (entidad.getDirección().isEmpty()) {
      errores.add("La dirección no puede estar vacía");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }
}