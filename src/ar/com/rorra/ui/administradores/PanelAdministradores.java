package ar.com.rorra.ui.administradores;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.Administrador;
import ar.com.rorra.ui.PanelLista;

public class PanelAdministradores extends PanelLista {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelAdministradores(Controlador controlador) {
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
