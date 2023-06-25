package ar.com.rorra.ui.pacientes;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Paciente;
import ar.com.rorra.ui.PanelLista;

public class PanelPacientes extends PanelLista {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelPacientes(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return Paciente.class;
  }
}
