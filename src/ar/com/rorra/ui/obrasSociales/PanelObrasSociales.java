package ar.com.rorra.ui.obrasSociales;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.ObraSocial;
import ar.com.rorra.ui.Panel;

public class PanelObrasSociales extends Panel {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelObrasSociales(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return ObraSocial.class;
  }
}
