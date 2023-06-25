package ar.com.rorra.ui.doctores;

import ar.com.rorra.entidad.Doctor;
import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.ui.Panel;

public class PanelDoctores extends Panel {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelDoctores(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return Doctor.class;
  }
}
