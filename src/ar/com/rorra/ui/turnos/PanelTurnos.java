package ar.com.rorra.ui.turnos;

import ar.com.rorra.controlador.Controlador;
import ar.com.rorra.entidad.IEntidad;
import ar.com.rorra.entidad.Turno;
import ar.com.rorra.ui.PanelLista;

import java.util.List;

public class PanelTurnos extends PanelLista {
  /**
   * Constructor
   *
   * @param controlador controlador principal de la aplicación
   */
  public PanelTurnos(Controlador controlador) {
    super(controlador);
  }

  /**
   * Devuelve la lista de entidades desde el almacenamiento de datos
   * @return lista de entidades
   * @param <T> tipo de entidad
   */
  @Override
  protected <T extends IEntidad> List<T> listarEntidades() {
    return controlador.listarEntidades(this.getEntityClass(), "fecha");
  }

  /**
   * Devuelve la clase de la entidad que gestiona el panel
   *
   * @return clase de la entidad que gestiona el panel
   */
  @Override
  public Class getEntityClass() {
    return Turno.class;
  }
}
