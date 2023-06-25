package ar.com.rorra.ui.consultorios;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Consultorio;
import ar.com.rorra.ui.Panel;

public class PanelConsultorios extends Panel {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelConsultorios(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return Consultorio.class;
  }
}
