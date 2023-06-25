package ar.com.rorra.ui.turnos;

import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.ui.Panel;

import javax.swing.*;

public class PanelTurnos extends Panel {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelTurnos(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return Administrador.class;
  }
}
