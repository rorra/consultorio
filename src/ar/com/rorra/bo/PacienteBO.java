package ar.com.rorra.bo;

import ar.com.rorra.dao.PacienteDAO;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.validations.IntegerValidations;
import ar.com.rorra.validations.StringValidations;

import java.util.ArrayList;

public class PacienteBO extends BaseBO<Paciente, PacienteDAO> {
  public PacienteBO() {
    super(new PacienteDAO());
  }

  public void validar(Paciente entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (StringValidations.isEmpty(entidad.getEmail())) {
      errores.add("El email no puede estar vacío");
    } else if (!StringValidations.validEmail(entidad.getEmail())) {
      errores.add("El email es inválido");
    }

    if (StringValidations.isEmpty(entidad.getPassword())) {
      errores.add("El password no puede estar vacío");
    }

    if (!IntegerValidations.isPositiveInteger(entidad.getDni())) {
      errores.add("El número de DNI no puede ser negativo y debe ser mayor a cero");
    } else if (existeDNI(entidad)) {
      errores.add("Ya existe un paciente con el DNI " + entidad.getDni());
    }

    if (entidad.getNombre().length() == 0) {
      errores.add("Debe ingresar un nombre válido para el paciente");
    }

    if (StringValidations.isEmpty(entidad.getTelefono())) {
      errores.add("Debe ingresar un teléfono para el doctor");
    }

    if (entidad.getObraSocial() == null) {
      errores.add("Debe ingresar una obra social para el paciente");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  private boolean existeDNI(Paciente entidad) {
    try {
      Paciente otro = dao.getByDni(entidad.getDni());
      if (otro != null && otro.getId() != entidad.getId()) {
        return true;
      }
    } catch (DBException e) {
    }
    return false;
  }
}
