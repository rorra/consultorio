package ar.com.rorra.bo;

import ar.com.rorra.dao.AdministradorDAO;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.exceptions.DBException;
import ar.com.rorra.validations.StringValidations;

import java.util.ArrayList;

public class AdministradorBO extends BaseBO<Administrador, AdministradorDAO> {
  public AdministradorBO() {
    super(new AdministradorDAO());
  }

  @Override
  public void validar(Administrador entidad) throws BOException {
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
      errores.add("Ya existe un administrador con el legajo " + entidad.getLegajo());
    }

    if (StringValidations.isEmpty(entidad.getNombre())) {
      errores.add("El nombre no puede estar vacío");
    }

    if (StringValidations.isEmpty(entidad.getTelefono())) {
      errores.add("El teléfono no puede estar vacío");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }

  private boolean existeLegajo(Administrador entidad) {
    try {
      Administrador otro = dao.getByLegajo(entidad.getLegajo());
      if (otro != null && otro.getId() != entidad.getId()) {
        return true;
      }
    } catch (DBException e) {
    }
    return false;
  }

  /**
   * Elimina un administrador, a no ser que sea el unico administrador en la base de datos
   *
   * @param entidad Entidad a eliminar
   * @throws BOException
   */
  public void delete(Administrador entidad) throws BOException {
    try {
      if (getAll().size() == 1) {
        throw new BOException("No se puede eliminar el único administrador");
      }
      dao.delete(entidad);
    } catch (DBException e) {
      throw new BOException("Error al eliminar la entidad en la base de datos: " + e.getMessage());
    }
  }
}
