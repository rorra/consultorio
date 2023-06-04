package ar.com.rorra.bo;

import ar.com.rorra.dao.AdministradorDAO;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.exceptions.BOException;

import java.util.ArrayList;

public class AdministradorBO extends BaseBO<Administrador, AdministradorDAO> {
  public AdministradorBO() {
    super(new AdministradorDAO());
  }

  @Override
  public void validar(Administrador entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (entidad.getEmail().isEmpty()) {
      errores.add("El email no puede estar vacío");
    }

    if (entidad.getPassword().isEmpty()) {
      errores.add("El password no puede estar vacía");
    }

    if (entidad.getNombre().isEmpty()) {
      errores.add("El nombre no puede estar vacío");
    }

    if (entidad.getTelefono().isEmpty()) {
      errores.add("El teléfono no puede estar vacío");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }
}
