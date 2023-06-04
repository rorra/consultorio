package ar.com.rorra.bo;

import ar.com.rorra.dao.PacienteDAO;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.exceptions.DBException;

import java.util.ArrayList;

public class PacienteBO extends BaseBO<Paciente, PacienteDAO> {
  public PacienteBO() {
    super(new PacienteDAO());
  }

  public void validar(Paciente paciente) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (existeDNI(paciente.getDni())) {
      errores.add("Ya existe un paciente con el DNI " + paciente.getDni());
    }

    if (paciente.getNombre().length() == 0) {
      errores.add("Debe ingresar un nombre válido para el paciente");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  private boolean existeDNI(long dni) {
    try {
      return dao.getByDni(dni) != null;
    } catch (DBException e) {
      return false;
    }
  }
}
