package ar.com.rorra.bo;

import ar.com.rorra.dao.ObraSocialDAO;
import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.exceptions.BOException;
import ar.com.rorra.validations.IntegerValidations;
import ar.com.rorra.validations.StringValidations;

import java.util.ArrayList;

public class ObraSocialBO extends BaseBO<ObraSocial, ObraSocialDAO> {
  public ObraSocialBO() {
    super(new ObraSocialDAO());
  }

  @Override
  public void validar(ObraSocial entidad) throws BOException {
    ArrayList<String> errores = new ArrayList<>();

    if (StringValidations.isEmpty(entidad.getNombre())) {
      errores.add("El nombre no puede estar vacío");
    }

    if (!IntegerValidations.isInRangeInteger(entidad.getDescuento(), 0, 100)) {
      errores.add("El descuento debe estar entre 0 y 100");
    }

    if (!errores.isEmpty()) {
      throw new BOException(errores);
    }
  }
}
